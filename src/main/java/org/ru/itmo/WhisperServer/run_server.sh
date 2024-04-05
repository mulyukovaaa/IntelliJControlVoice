#!/bin/bash

echo "Установка зависимостей..."
pip3.11 install -r ./req.txt

echo "Запуск сервера..."
python3.11 ./whisper_server.py >> log.txt 2>&1 &
