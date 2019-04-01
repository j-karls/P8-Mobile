from bluetooth import *
import os
import sys
from threading import Thread

def APIService(client, addr):
	print("Ready for comms...")
	while(True):
		data = client.recv(1024)
		str = data.decode('utf-8')
		print('Received: ' + str)
		if (str == 'GET'):
			client.send("sending requested data...")
		elif (str == 'PUT'):
			client.send("You wish to give data?")
		else:
			client.send("Unknown command")

def handleClient(client, addr):
	#On new client, run API service, listening for commands
	print('Connection from: ', addr)
	try:
		APIService(client, addr)
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
