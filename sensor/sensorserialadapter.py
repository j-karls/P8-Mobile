# This program facilitates a server, listening
# for sensor clients, on a localhost socket.

import os
import sys
import socket
import _thread
import serial
import io
import sqlite3
from sqlite3 import Error


# Make sure database has a trigger, to delete old data
# Make sure database has tables 'shortterm', 'longterm'

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

try:
	ser = serial.Serial()
	ser.baudrate = 19200
	ser.port = 'COM1'
	ser.open()
except Exception as e:
	print("Error: " + str(e))
	sys.exit()


print("Reading on serial port: " + ser.port + " at baudrate: " + str(ser.baudrate))

while(ser.is_open):
	#read and handle data lines
	a=False
	
