from bluetooth import *
import os
import sys
from threading import Thread
from config import Configuration
from antlr.compiler import Compile
import json
import pickle
import sqlite3
from sqlite3 import Error
import time as t

# Absolute path to sqlite3 database
DBFILE = '/home/pi/Desktop/data.sqlite'

def APIService(client, addr, cfg):
	print("Ready for comms with client " , addr[0])
	while True:
		#If client has a config for wanthing alerts, then dispatch alert thread
		if cfg.getAlertSetting() == 1:
			alertThread = Thread(target=alert, args=(client, cfg))
			alertThread.start()
			print('Client ' + addr[0] + ' is subscribed to receive alerts.')
		elif cfg.getAlertSetting() == 0:
			print('Client ' + addr[0] + ' is not subscribed to recieve alerts.')
			try:
				if alertThread.is_alive():
					alertThread.stop()
			except NameError:
				pass

		data = client.recv(1024)
		if not data:
			break
		str = data.decode('utf-8')
		print('Received data: ' + str)

		# Compile string to a SQL query
		try:
			sqlcommand = Compile(str, cfg)
		except:
			print('Query translation failed, syntax error in input: ' + str)
			client.send('SYNTAX_ERROR'.encode('utf-8'))

		#sqlcommand = 'SELECT * FROM config WHERE mac = "{}"'.format(addr[0])
		print('SQL query: ' + sqlcommand)
		try:
			# Execute generated sql command, against local db
			result = readFromDatabase(sqlcommand)
			# Convert result (rows) to a json object
			jsonObj = json.dumps(result)
			y = jsonObj
			print('Sending: ' + y)
			client.send(y.encode('utf-8'))
		except BluetoothError as e:
			print(e)
	client.close()

def alert(client, cfg):
	while True:
		# Fetch and check if values are beyond limits...
		# if so, send alert message(s) to client
		factors = ('CO2', 'CO', 'Temp', 'Hum')
		for f in factors:
			sql = Compile('GET '+f+' status', cfg)
			res = readFromDatabase(sql)[0]
			type = res[0]
			value = res[1]
			if limitExceeded(type, value):
				print('Limit exceeded for ' + type + ' with value: ' + str(value))
				client.send(('LIMIT_'+type).encode('utf-8'))
		t.sleep(10)
		# We break this loop by killing the thread

def limitExceeded(type, value):
	return True

def readFromDatabase(sql):
	try:
		conn = sqlite3.connect(DBFILE)
	except Exception as e:
		print(e)
	cur = conn.cursor()
	cur.execute(sql)
	data = cur.fetchall()
	return data


def handleClient(client, addr):
	#On new client, run API service, listening for commands
	print('Connection from: ', addr)
	try:
		cfg = Configuration(addr)
		APIService(client, addr, cfg)
	except BluetoothError as e:
		print('Client disconnected: ' + str(addr) + '\nError: ' + str(e))
		pass

def main(args):
	createDatabase(DBFILE)

	server_sock = BluetoothSocket(RFCOMM)
	server_port = PORT_ANY
	server_address = '', server_port

	server_sock.bind(server_address)
	server_sock.listen(100)

	uuid = '94f39d29-7d6d-437d-973b-fba39e49d4ee'
	#uuid = '00001101-0000-1000-8000-00805f9b34fb'
	advertise_service(server_sock, name="CreamPi",service_id=uuid,
				service_classes=[SERIAL_PORT_CLASS],
				profiles=[SERIAL_PORT_PROFILE])

	print('Listening for clients, on port ', server_port, '...')

	while(True):
		client_sock, addr = server_sock.accept()
		t = Thread(target=handleClient, args=(client_sock, addr))
		t.start()

	server_sock.close()

def create_connection(db_file):
	try:
		conn = sqlite3.connect(db_file)
		return conn
	except Error as e:
		print(e)
	return None

def create_table(conn, create_table_sql):
	try:
		c = conn.cursor()
		c.execute(create_table_sql)
	except Error as e:
		print(e)

def createDatabase(file):
	create_connection(file)

	table_shortterm = """ CREATE TABLE IF NOT EXISTS shortterm (
										id INTEGER PRIMARY KEY AUTOINCREMENT,
										type TEXT NOT NULL,
										value INTEGER NOT NULL,
										time DATE NOT NULL
									); """

	table_longterm = """ CREATE TABLE IF NOT EXISTS longterm (
										id INTEGER PRIMARY KEY AUTOINCREMENT,
										type TEXT NOT NULL,
										value INTEGER NOT NULL,
										time DATE NOT NULL
									); """

	table_config = """ CREATE TABLE IF NOT EXISTS config (
										mac TEXT PRIMARY KEY,
										alert INTEGER NOT NULL,
										guideline TEXT NOT NULL
									); """

	table_limits = """ CREATE TABLE IF NOT EXISTS limits (
										id INTEGER PRIMARY KEY AUTOINCREMENT,
										source TEXT NOT NULL,
										type TEXT NOT NULL,
										upperbound INTEGER,
										lowerbound INTEGER 
									); """

	 # create a database connection
	conn = create_connection(file)
	if conn is not None:
		# create projects table
		create_table(conn, table_shortterm)
		create_table(conn, table_longterm)
		create_table(conn, table_config)
		create_table(conn, table_limits)
	else:
		print("Error! cannot create the database connection.")

if __name__ == '__main__':
	main(sys.argv)
