# Websocket
Apresentação sobre Websockets desenvolvida com websocket em html5 + jquery + jetty-9.

Para testar basta: 
  
  -- inserir dependencia do jquery via bower <br />
  diretorio: ws-presentation/src/main/webapp/resources/js/vendor <br />
  bower install jquery <br />

  -- compilar com maven <br />
  mvn clean install eclipse:clean eclipse:eclipse -Dmaven.test.skip <br />

Após, para subir a aplicação: 

  mvn jetty:run
