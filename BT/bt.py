import bluetooth
import time

target_name = "Oneplus 3"
target_address = None

def search():
	print("Searching for devices...")
	devices = bluetooth.discover_devices(duration = 5, lookup_names = True)
	return devices

while(True):
	res = search()
	if(res != None):
		print("Found devices:")
	if(res != None):
		for addr, name in res:
			print(name)
