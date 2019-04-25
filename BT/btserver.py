from bluetooth import *
import os
import sys
from threading import Thread
from config import Configuration
from compiler import Compile
import json
import pickle

# Absolute path to sqlite3 database
DBFILE = '/home/pi/Desktop/data.sqlite'

def APIService(client, addr, cfg):
	print("Ready for comms with client - MAC: " , addr)
	while(True):
		data = client.recv(1024)
		str = data.decode('utf-8')
		try:
			sqlcommnand = Compile(str)
			try:
				# Execute generated sql command, against local db
				result = readData(sqlcommnand)
				# Convert result (rows) to a json object
				jsonObj = json.dumps([dict(x) for x in result])
				# Serialize object to string
				#data = pickle.dumps(jsonObj)
				# Send
				client.send(jsonObj)
			except Exception as e:
				client.send('ERROR-2')
		except Exception as e:
			client.send('ERROR-1')


		# The "SET" parts of a command is handled immidiately within the compiler


		#print('Received: ' + str)
		#if (str == 'GET'):
		#	client.send("sending requested data...")
		#elif (str == 'PUT'):
		#	client.send("You wish to give data?")
		#else:
		#	client.send("Unknown command")

def readData(sql):
	try:
		conn = sqlite3.connect(DBFILE)
	except Exception as e:
		print(e)
	cur = dbconn.cursor()
	cur.execute(sql)
	data = cursor.fetchall()
	return data


def handleClient(client, addr):
	#On new client, run API service, listening for commands
	print('Connection from: ', addr)
	try:
		cfg = Configuration(addr)
		APIService(client, addr, cfg)
	except BluetoothError as e:
		print(e)
		pass

def main(args):
	server_sock = BluetoothSocket(RFCOMM)
	server_port = PORT_ANY
	server_address = '', server_port

	server_sock.bind(server_address)
	server_sock.listen(100)

	uuid = '94f39d29-7d6d-437d-973b-fba39e49d4ee'
	advertise_service(server_sock, name="CreamPi",service_id=uuid,
				service_classes=[SERIAL_PORT_CLASS],
				profiles=[SERIAL_PORT_PROFILE])

	print('Listening for client, on port ', server_port, '...')

	while(True):
		client_sock, addr = server_sock.accept()
		t = Thread(target=handleClient, args=(client_sock, addr))
		t.start()
		#client_sock.close();

	server_sock.close()


if __name__ == '__main__':
	main(sys.argv)
