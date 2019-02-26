# This program facilitates a server, listening
# for sensor clients, on a local socket.

import os
import socket
import _thread

def handleData(type, value):
	# do stuff here, push to database maybe?
	return 0

# Each new client gets s
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
		handleData(sensortype, val)
    
def startserver():
	serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	host = 'localhost'
	port = 9001
	serversocket.bind((host, port))                                  
	serversocket.listen(10)
	return serversocket


serversocket = startserver()

print("Ready...")

while(True):
   client,addr = serversocket.accept()   
   print("Got a connection from %s" % str(addr))
   _thread.start_new_thread(on_new_client,(client,addr))

s.close()