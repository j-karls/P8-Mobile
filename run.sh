#!/bin/sh
pkill python
systemctl restart bluetooth
python3 run.py
