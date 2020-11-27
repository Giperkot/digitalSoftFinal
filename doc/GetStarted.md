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

    wget "https://github.com/neo4j-labs/neosemantics/releases/download/4.1.0.1/neosemantics-4.1.0.1.jar"

    docker cp "F:\development\leadersofdigital\Doc\data\CModel1.owl" mycontainer:/home/CModel11.owl

    CALL n10s.graphconfig.init();
    CREATE CONSTRAINT n10s_unique_uri ON (r:Resource)
    ASSERT r.uri IS UNIQUE;
    call n10s.graphconfig.init()
    call n10s.graphconfig.init( {  handleMultival: "ARRAY" })
    call n10s.graphconfig.init();
    call n10s.graphconfig.set( { keepLangTag: true, handleRDFTypes: "LABELS_AND_NODES" });
    
    docker cp "F:\development\leadersofdigital\Doc\data\CModel1.owl" bold_archimedes:/home/CModel1.owl
    
    
    CALL n10s.onto.import.fetch("file:/home/CModel11.owl","RDF/XML");