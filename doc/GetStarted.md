#Скачать и установить Docker

##Устанавливаем neo4j
В cmd выполнить

    docker pull neo4j


#Устанавливаем Доп ПО
    apt update
    apt install mc

Открыть консоль докера

Скачать плагин для загрузки данных
    wget "https://github.com/neo4j-labs/neosemantics/releases/download/4.1.0.1/neosemantics-4.1.0.1.jar"
    
Копируем вайл в  <NEO_HOME>/plugins

В вайл <NEO_HOME>/conf/neo4j.conf добавляем строку 

    dbms.unmanaged_extension_classes=n10s.endpoint=/rdf

Рестарт сервера
