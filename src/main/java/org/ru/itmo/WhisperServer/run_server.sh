#!/bin/bash

echo "Установка зависимостей..."
pip install -r ./src/main/java/org/ru/itmo/WhisperServer/req.txt

echo "Запуск сервера..."
python ./src/main/java/org/ru/itmo/WhisperServer/whisper_server.py >> log.txt 2>&1 &
