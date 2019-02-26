# This program simulates a sensor
# Data is derived from a sinus function, and
# sent over a local socket, to the sensorserver.
# This program may be completely replaced with a real 
# sensor harvester.

import matplotlib.pyplot as plt # For ploting
import numpy as np # to work with numerical data efficiently
import time as t
import socket

sensortype = "CO2"

def connect():
	server = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
	host = 'localhost'                          
	port = 9001
	server.connect((host, port))
	return server                        

samplerate = 200 # sample rate 
f = 1 # the frequency of the signal

factor = 100 # multiplication factor
outputinterval = 1 # seconds

# the points on the x axis for plotting
x = np.arange(samplerate)

# compute the value (amplitude) of the sin wave at the for each sample
y = np.sin(2*np.pi*f * (x/samplerate))

#print(y)

server = connect()

# While forever, send value for each outputinterval
# If connection to server is lost, keep attempting to
# reconnect.
while(True):
	for val in y:
		measurement = np.around(val*factor, 2)
		print(sensortype + '/' + str(measurement))
		try:
			server.send(str(sensortype + '/' +str(measurement)).encode())
		except socket.error:
			print("Failed to send")
			while(True):
				print("Attempting reconnect...")
				try:
					server = connect()
					break
				except:
					print("Attempt failed")
				t.sleep(3)


		t.sleep(outputinterval)

# showing the exact location of the smaples
#plt.stem(x,y, 'r', )
#plt.plot(x,y)
#plt.show()