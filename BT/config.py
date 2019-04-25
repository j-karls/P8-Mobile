# For loading and maintaining a configuration for each client, based on their
# unique MAC-address (Bluetooth).
# Instanciate the class, with a

import sqlite3
from sqlite3 import Error

# Use example:
# from config import Configuration
# clientconfig = Configuration('00:11:22:33:FF:EE')

class Configuration(MAC):

	def __init__(self, mac):
		self.__mac = mac
		self.__alert = 0
		self.__guideline = ''

		# If the mac address has an existing config
		# associated with it, load it...
		# otherwise, create new config for this mac
		if configExists(mac):
			self.load()
		else:
			self.__mac = mac
			self.save()

	# Load config from db
	def load(self):
		db = createConnection()
		cursor = db.cursor()
		cursor.execute("SELECT macaddr FROM config WHERE macaddr = ?", (self.__mac,))
		data = cursor.fetchone()
		self.__mac = data['macaddr']
		self.__alert = data['alert']
		self.__guideline = data['guide']

	# Save current config to db
	def save(self):
		db = createConnection()
		cursor = db.cursor()
		cursor.execute('''INSERT INTO config(mac, alert, guide)
							VALUES(?,?,?)''',
			(self.__mac, self.__alert, self.__guideline))
		db.commit()
	
	def configExists(mac):
		db = createConnection()
		cursor = db.cursor()
		cursor.execute("SELECT macaddr FROM config WHERE macaddr = ?", (mac,))
		data = cursor.fetchone()
		if data is None:
			return False
		else:
			return True

	def setAlert(val):
		self.__alert = val
		self.save()

	def setGuideline(str):
		self.__guideline = str
		self.save()

	def createConnection():
		try:
			conn = sqlite3.connect('../config.db')
			return conn
		except Error as e:
			print(e)
		return None