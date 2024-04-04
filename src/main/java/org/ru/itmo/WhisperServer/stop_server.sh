#!/bin/bash

PID=$(lsof -ti:5001)

if [ -n "$PID" ]; then
    echo "Останавливаем сервер с PID $PID..."
    kill "$PID"
    echo "Сервер остановлен."
else
    echo "Сервер, использующий порт 5001, не найден."
fi
