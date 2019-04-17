# This is the "mother" program
# It starts all other programs on the system, and restart them if they crash

from subprocess import Popen
import sys
from threading import Thread

files = ['dbmaintainer.py', 'BT/btserver.py', 'serialreader.py']

def run(filename):
	while True:
		print("\nStarting " + filename)
		p = Popen("python3 " + filename, shell=True)
		p.wait()

for file in files:
	try:
		t = Thread(target=run, args=(file,))
		t.start()
	except Exception as e:
		print(e)
		pass