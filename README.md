# IntelliJControlVoice

Плагин для IntelliJ IDEA с поддержкой голосового управления фукнкционалом IDE.

![Logo.png](images/Logo.png "JControl")

# Функции

Плагин поддерживает следующие функции:

## Actions

- Открытие и закрытие файлов
- Работа с системой контроля версий
- Работа и перемещение по вкладкам среды разработки
- Создание классов и пакетов
- Скрытие и показ элементов UI 

## Модель распознавания голоса

Для распознавания голоса возможно использовать как OpenAI API, так и локальную модель.

### OpenAPI

При использовании OpenAI API необходимо предоставить API ключ и иметь включенный VPN (при использовании в РФ).

## Сервер с моделью (локально)

Для распознавания голоса локальной моделью требуется запустить скрипт:

```shell
sh ./src/main/java/org/ru/itmo/WhisperServer/run_server.sh
```

Будет автоматически развернут локальный сервер, на который будут поступать запросы.

# Запуск

Для запуска и тестирования плагина необходимо запустить `intellij::runIde` Gradle task.

**Для подключения в IDE:**

- Запустить `Build::jar` Gradle task
- Подключить к IntelliJ
- Произвести настройку в зависимости от выбранного способа распознавания речи

Имеется возможность задать свои голосовые команды для вызова поддерживаемых команд.

# Команда

| Участник          | Роль               |
|-------------------|--------------------|
| Мулюкова Амина    | Developer, Manager |
| Шкандюк Денис     | Developer          |
| Ворожбитов Никита | Developer          |
| Поляков Андрей    | TeamLead           |
