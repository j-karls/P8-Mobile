import os
import sys
from  datetime import datetime as dt
import time
import glob
import serial
import sqlite3
from threading import Thread

DBFILE = '/home/pi/Desktop/data.sqlite'
DBCOMMITRATE = 10 #Secs

#Bindings
bindings = [('CO', 'longterm'),
	('CO2', 'shortterm'),
	('NO', 'shortterm')]

def main():
	print('Initializing serial reader...')
	ports = findPorts()
	print('Found ports:')
	if ports is not None:
		for port in ports:
			print(port)
			try:
				t = Thread(target=reader, args=(port,))
				t.start()
			except Exception as e:
				print(e)
				pass
		print('Found ports: ', findPorts())
	else:
		print('No active ports found... trying again in 10 seconds')
		time.sleep(10)

def findPorts():
	ports = glob.glob('/dev/ttyACM[0-9]*')
	return ports

#start on new thread
def reader(port):
	#Database connection
	dbconn = dbCreateConnection(DBFILE)

	#Serial comms connection
	ser = serial.Serial(port)
	ser.baudrate = 115200

	print('in reader thread..')
	while(True):
		line = ser.readline().decode('utf-8')
		type, value = line.strip().split(',')
		print('Recieved: ', type, ' : ', value)
		handleData(type, value, dbconn)
		time.sleep(DBCOMMITRATE)

def handleData(type, value, dbconn):
	for x in bindings:
		_type, _table = x
		if(_type == type and _table == 'shortterm'):
			commitShort(type, value, dbconn)
		elif(_type == type and _table == 'longterm'):
			commitLong(type, value, dbconn)

def commitShort(type, value, dbconn):
	reading = (type, value, dt.now())
	sql = '''INSERT INTO shortterm (type, value, time) VALUES (?,?,?)'''
	cur = dbconn.cursor()
	try:
		cur.execute(sql, reading)
	except sqlite3.IntegrityError as e:
		print('sqlite error: ', e.args[0])
	dbconn.commit()

def commitLong(type, value, dbconn):
	reading = (type, value, dt.now())
	sql = '''INSERT INTO longterm (type, value, time) VALUES (?,?,?)'''
	cur = dbconn.cursor()
	try:
		cur.execute(sql, reading)
	except sqlite3.IntegrityError as e:
		print('sqlite error: ', e.args[0])
	dbconn.commit()

def dbCreateConnection(db_file):
	print('Connecting to local database...')
	try:
		conn = sqlite3.connect(db_file)
		print('Connection to database succesful!')
		return conn
	except Exception as e:
		print(e)
	return None

if __name__ == "__main__":
	main()
