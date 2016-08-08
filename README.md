# Websocket
Apresentação sobre Websockets desenvolvida com websocket em html5 + jquery + jetty-9.

Para testar basta:

  -- compilar com bower <br />
  bower install

  -- compilar com maven <br />
  mvn clean install eclipse:clean eclipse:eclipse -Dmaven.test.skip <br />

Após, para subir a aplicação:

  mvn jetty:run

Acessar admin: http://localhost:9280/websocket?admin <br />
Acessar client: http://localhost:9280/websocket
