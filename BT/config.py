# For loading and maintaining a configuration for each client, based on their
# unique MAC-address (Bluetooth).
# Instanciate the class, with a

import sqlite3
from sqlite3 import Error

# Use example:
# from config import Configuration
# clientconfig = Configuration('00:11:22:33:FF:EE')

class Configuration():

	def __init__(self, mac):
		self.__mac = mac[0]
		self.__alert = 0
		self.__guideline = ''

		# If the mac address has an existing config
		# associated with it, load it...
		# otherwise, create new config for this mac
		if self.configExists(mac[0]):
			print('Loading config for  ' + mac[0])
			self.load()
		else:
			print('No config found for ' + mac[0])
			print('Creating new config for ' + mac[0])
			self.__mac = mac[0]
			self.save()

	def getAlertSetting(self):
		return self.__alert

	# Load config from db
	def load(self):
		db = self.createConnection()
		cursor = db.cursor()
		cursor.execute("SELECT * FROM config WHERE mac=?", (self.__mac,))
		data = cursor.fetchone()
		try:
			self.__mac = data[0]
			self.__alert = data[1]
			self.__guideline = data[2]
		except:
			pass

	# Save current config to db
	def save(self):
		db = self.createConnection()
		cursor = db.cursor()
		row = (self.__mac, self.__alert, self.__guideline)
		cursor.execute('''INSERT INTO config(mac, alert, guideline) 
				VALUES (?,?,?)''', row)
		db.commit()

	def configExists(self, mac):
		db = self.createConnection()
		cursor = db.cursor()
		try:
			cursor.execute("SELECT mac FROM config WHERE mac=?", (mac,))
			data = cursor.fetchone()
			if data is not None:
				return True
		except:
			return False

	def setAlert(self, val):
		self.__alert = val
		self.save()

	def setGuideline(self, str):
		self.__guideline = str
		self.save()

	def createConnection(self):
		try:
			conn = sqlite3.connect('/home/pi/Desktop/data.sqlite')
			return conn
		except Error as e:
			print(e)
		return None
