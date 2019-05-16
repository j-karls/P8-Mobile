# This is the "mother" program
# It starts all other programs on the system, and restart them if they crash

from subprocess import Popen
import sys
from threading import Thread
import time as tm

files = ['BT/btserver.py', 'serialreader.py', 'dbmaintainer.py']

def run(filename):
	while True:
		print("-- Starting " + filename)
		p = Popen("python3 " + filename, shell=True)
		p.wait()

for file in files:
	try:
		t = Thread(target=run, args=(file,))
		t.start()
		tm.sleep(0.5)
	except Exception as e:
		print(e)
		pass
