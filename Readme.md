
- запускаем postgres docker-compose (лежит в корне)
- запускаем приложение (профиль remote для рандома с сайта random)
- далее запросы в соответствии с ТЗ

Создание пользователя
```aidl
curl --location --request POST 'localhost:8080/lottery/participant' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Вася",
    "age": 21,
    "city": "Светлоярск"
}'
```

Список пользователей
```aidl
curl --location --request GET 'localhost:8080/lottery/participant'
```

Старт рулетки
```aidl
curl --location --request POST 'localhost:8080/lottery/participant' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Вася",
    "age": 21,
    "city": "Светлоярск"
}'
```

победители
```aidl
curl --location --request GET 'localhost:8080/lottery/winners'
```

1* чтобы рандом тянулся с сайта, надо запустить с профилем random

2,3* Таблицу победителей сделал в соответствии с п. 2*, но по факту победители выводятся без нее.
