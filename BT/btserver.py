from bluetooth import *
import os
import sys
from threading import Thread
from config import Configuration
from antlr.compiler import Compile
import json
import sqlite3
from sqlite3 import Error
import time as t
import aqicalculator as aqi

# Absolute path to sqlite3 database
DBFILE = '/home/pi/Desktop/data.sqlite'

def APIService(client, addr, cfg):
	print("Ready for comms with client " , addr[0])
	while True:
		# If client has a config for wanthing alerts, then dispatch alert thread
		if cfg.getAlertSetting() == 1:
			try:
				alertThread.is_alive()
			except NameError:
				alertThread = Thread(target=alert, args=(client, cfg))
				alertThread.start()
				print('Client ' + addr[0] + ' is subscribed to receive alerts.')

		data = client.recv(1024)
		if not data:
			break
		str = data.decode('utf-8')
		print('Received data: ' + str)

		# Compile string to a SQL query
		sqlcommand = None
		responseType = None
		try:
			sqlcommand, responseType = Compile(str, cfg)
		except:
			print('Query translation failed, syntax error in input: ' + str)
			client.send('SYNTAX_ERROR'.encode('utf-8'))

		#sqlcommand = 'SELECT * FROM config WHERE mac = "{}"'.format(addr[0])
		if sqlcommand is not None and responseType is not None:
			print('SQL query: ' + sqlcommand)
			try:
				# Execute generated sql command, against local db
				result = readFromDatabase(sqlcommand)
				# Convert result (rows) to a json object
				jsonObj = json.dumps(result)
				if responseType[:3] == 'GET':
					payload = responseType[4:] + '/' + jsonObj
				elif responseType[:3] == 'SET':
					payload = 'ACK'
				print('Sending: ' + payload)
				client.send(payload.encode('utf-8'))
			except BluetoothError as e:
				print(e)
	client.close()

def alert(client, cfg):
	while True:
		# Fetch and check if values are beyond limits...
		# if so, send alert message(s) to client
		if cfg.getAlertSetting() == 0:
			#print('Client ' + cfg.getMAC() + ' is no longer subscribed to receive alerts.')
			break

		# Generate SQL queries
		# The [0] means we're assigning the SQL -- we don't need the responseType here
		co2sql = Compile('GET CO2 status', cfg)[0]
		cosql = Compile('GET CO status', cfg)[0]
		tempsql = Compile('GET Temp status', cfg)[0]
		humsql = Compile('GET Hum status', cfg)[0]

		# Now we read from the database, and exctract the value
		# We use [0] to extract the first (and only) element in the array
		# We then use [1] to extract the value, which is the second collumn in the database
		co2val = readFromDatabase(co2sql)[0][1]
		coval = readFromDatabase(cosql)[0][1]
		tempval = readFromDatabase(tempsql)[0][1]
		humval = readFromDatabase(humsql)[0][1]

		res = aqi.aqicompare(co2val, coval, tempval, humval)
		if res['problem'] != 'ALL_FACTORS_OK':
			try:
				client.getpeername()
			except:
				break
			print('Problem:\t' + str(res['problem']))
			print('Solution:\t'+ str(res['solution']))
			jsonObj = json.dumps(res)
			payload = 'ALT' + '/' + jsonObj
			client.send(payload.encode('utf-8'))

		t.sleep(60)

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
