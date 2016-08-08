# Websocket
Apresentação sobre Websockets desenvolvida com websocket em html5 + jquery + jetty-9.

Para testar basta: 
  
  -- inserir dependencia do jquery via bower --
  diretorio: ws-presentation/src/main/webapp/resources/js/vendor
  bower install jquery

  -- compilar com maven  --
  mvn clean install eclipse:clean eclipse:eclipse -Dmaven.test.skip

Após, para subir a aplicação: 

  mvn jetty:run
