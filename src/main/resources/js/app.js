var stompClient = null;

$(function () {
   $("form").on("submit", function(e) {
       e.preventDefault();
   })
    $("#connect").click(function() {
        connect();
    })
    $("#disconnect").click(function() { disconnect(); });
    $("#send").click(function() { sendName(); });
});

function connect() {
    var socket = new SockJS("/websocket");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log("Connected : " + frame);
        stompClient.subscribe("/topic/greetings", function(greeting) {
            showGreeting(JSON.parse(greeting.body).connect());
        });
    });
}

function disconnect() {
    if(stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}