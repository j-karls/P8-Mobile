# This program facilitates a server, listening
# for sensor clients, on a local socket.

import os
import socket
import _thread
import sqlite3
from sqlite3 import Error

# TODO:
# Submit data to database
# Make sure database has a trigger, to delete old data
# Make sure database has tables 'shortterm', 'longterm'

def create_connection(db_file):
    try:
        conn = sqlite3.connect(db_file)
        return conn
    except Error as e:
        print(e)
    return None

def pushToShortTerm(type, value, db):
	cursor = db.cursor()
	cursor.execute('''INSERT INTO shortterm(type, value)
						VALUES(?,?)''',
		(type, value))
	db.commit()
	print("Pushed to shortterm")

def pushToLongTerm(type, value, db):
	cursor = db.cursor()
	cursor.execute('''INSERT INTO longterm(type, value)
						VALUES(?,?)''',
		(type, value))
	db.commit()
	print("Pushed to longterm")

# Where we actually do stuff with the recieved data
def handleData(type, value, db):
	# do stuff here, push to database maybe?
	if (type == 'CO2'):
		pushToShortTerm(type, value, db)
	elif (type == 'NO2'):
		pushToLongTerm(type, value, db)
	return 0

# Each new client gets a thread with this function
def on_new_client(client,addr):
	while(True):
		data = client.recv(256).decode()
		if not data:
			print("Lost connection from %s" % str(addr))
			client.close()
			break
		arr = data.split('/')
		sensortype = arr[0]
		val = arr[1]
		print("Sensor:\t" + sensortype + "\tValue:\t" + val)
		db = create_connection('../sensordb.db')
		handleData(sensortype, val, db)
    
# Server startup
def startserver():
	serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	host = 'localhost'
	port = 9001
	serversocket.bind((host, port))                                  
	serversocket.listen(10)
	return serversocket

serversocket = startserver()
print("Ready...")

# Accept clients, and start a new thread for each one
while(True):
   client,addr = serversocket.accept()   
   print("Got a connection from %s" % str(addr))
   _thread.start_new_thread(on_new_client,(client,addr))

s.close()