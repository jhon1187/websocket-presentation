function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(";");
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == " ")
			c = c.substring(1);
		if (c.indexOf(name) != -1)
			return c.substring(name.length, c.length);
	}
	return "";
}

function setCookie(cname, cvalue) {
	document.cookie = cname + "=" + cvalue + "; Path=/; ";
}

function setCookieExpire(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; Path=/; " + expires;
}

//############################################################################### Presentation

var presentation = {};

presentation.presentations = $("#presentations").find(".presentation");

presentation.pageMin = 1;
presentation.pageMax = presentation.presentations.length;


presentation.page = presentation.pageMin;

presentation.changeActive = true;

presentation.prev = function(){
	if(presentation.page != presentation.pageMin){
		presentation.page -= 1;
		presentation.sendPage();
	}
}

presentation.next = function(){
	if(presentation.page != presentation.pageMax){
		presentation.page += 1;
		presentation.sendPage();
	}
}

presentation.changePage = function(){
	$("#page").html(presentation.page);

	$(".presentation").removeClass("active");

	$(presentation.presentations).each(function() {
		  if($(this).attr("id") == "presentation_" + presentation.page){
			 $(this).addClass("active");
		  }
	});

}

presentation.getPage = function(){
	$.ajax({
		url : "/websocket/rest/presentation",
		type : "GET",
		contentType : "text/plain; charset=utf-8",
		dataType : "text",
		success : function(page) {
			if(presentation.changeActive){
				presentation.page = Number(page);
				presentation.changePage();
			}
		}
	});
}

presentation.setChangeActive = function(){
	presentation.changeActive = !presentation.changeActive;

	if(presentation.changeActive){
		$("#btnChangeActive").addClass("activeTrue");
		$("#btnChangeActive").removeClass("activeFalse");

		presentation.getPage();
	}else{
		$("#btnChangeActive").addClass("activeFalse");
		$("#btnChangeActive").removeClass("activeTrue");
	}
}

presentation.sendPage = function(){

	presentation.changePage();

	if(presentation.changeActive){
		ws.send(String(presentation.page));
	}
}

//Alterar pagina com setas do teclado
$("body").keydown(function(e) {
  if(e.keyCode == 37) { // left
	  presentation.prev();
  }
  else if(e.keyCode == 39) { // right
	  presentation.next();
  }
});


//############################################################################### WS

var ws = {};

function setAdminPresentation(){
	if(location.search != null && location.search != ""){
		var paramsLocation = location.search.split("&");
		var paramAdmin = paramsLocation[0].replace("?", "");
		if(paramAdmin == "admin"){
			return paramAdmin;
		}else{
			return null;
		}
	}
}

function conectarWebSocket() {
	if ($.isEmptyObject(ws) || ws.readyState > 1) {
		ws = null;

		//VALIDA PROTOCOLO
		var ws_protocol = "ws://";
		if(location.protocol == "https:"){
		  ws_protocol = "wss://";
		}

		var ws_url = ws_protocol + location.host + "/websocket/webSocketEcho" + "?idUser=teste";

		var adminPresentation = setAdminPresentation();

		if(adminPresentation != null){
			ws_url += "&" + adminPresentation;
		}

		//VALIDA TIPO DE SUPORTE WS DO BROWSER E GERA UMA INSTANCIA
		if ("WebSocket" in window) {
			ws = new WebSocket(ws_url);
		}
		else if ("MozWebSocket" in window) {
			ws = new MozWebSocket(ws_url);
		}else{
			alert("Browser sem suporte a WebSocket!");
		}

		//INICIA O WS
		if(ws != null){
			ws.onopen = function(event) {
				presentation.getPage();
			}

			ws.onmessage = function(event) {
				var data = event.data;

				if(presentation.changeActive){
					presentation.page = Number(data);
					presentation.changePage();
				}
			}

			ws.onclose = function(event) {
			}
		}
	}
}
