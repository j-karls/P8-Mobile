import os
import sys
from  datetime import datetime as dt
import time
import glob
import serial
import sqlite3
from threading import Thread
from serial.serialutil import SerialException

DBFILE = '/home/pi/Desktop/data.sqlite'
DBCOMMITRATE = 10 #Secs

#Bindings
bindings = [('CO', 'shortterm'),
	('CO2', 'shortterm'),
	('Temp', 'shortterm'),
	('Hum', 'shortterm')]

def main():
	#print('Initializing serial reader...')
	ports = []
	threads = []
	while True:
		for port in findPorts():
			if port not in ports:
				ports.append(port)
				print('Device ' + port + ' connected!')
				for port in ports:
					try:
						t = Thread(target=reader, args=(port,))
						t.start()
						threads.append([t, port])
					except Exception as e:
						pass

		# Maintain which threads are active, and this which ports are connected
		for t, port in threads:
			if not t.is_alive():
				try:
					ports.remove(port)
					threads.remove([t, port])
				except Exception as e:
					pass

def findPorts():
	ports = glob.glob('/dev/ttyACM[0-9]*')
	return ports

#start on new thread
def reader(port):
	# Sleep for 5 minutes, letting the sensors heat up
	print('Serialreader: Waiting 5 minutes for sensors to heat...')
	time.sleep(60*5)
	print('Serialreader: Reading from sensors...')
	#Database connection
	dbconn = dbCreateConnection(DBFILE)
	#Serial comms connection
	try:
		ser = serial.Serial(port)
		ser.baudrate = 9600
	except SerialException as e:
			print('Serialreader: Device ' + port + ' disconnected!')

	while(True):
		try:
			line = ser.readline().decode('utf-8')
		except SerialException as e:
			print('Serialreader: Device ' + port + ' disconnected!')
			break
		try:
			type, value = line.strip().split(':')
			#print('Serialreader: Recieved: ', type, ' : ', value)
			handleData(type, value, dbconn)
		except ValueError as e:
			print('Serialreader: ' + str(e))
		#time.sleep(DBCOMMITRATE)

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
	#print('Connecting to local database...')
	try:
		conn = sqlite3.connect(db_file)
		#print('Connection to database succesful!')
		return conn
	except Exception as e:
		print(e)
	return None

if __name__ == "__main__":
	main()
