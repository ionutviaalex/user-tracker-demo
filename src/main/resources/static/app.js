const stompClient = new StompJs.Client({
    brokerURL: "ws://localhost:8081/user-tracker-websocket"
});

stompClient.onConnect = (frame) => {
    console.log("Connected: " + frame);
    setConnected(true);
    console.log("Subscribing to updates...");

    // Subscribing to updates.
    stompClient.subscribe("/all/users", (users) => {
        numberOfUsers = JSON.parse(users.body).totalUsers;
        console.log("Number of users received is:", numberOfUsers);
        showNumberOfUsers(numberOfUsers);
    });

    // Initial (and last) request for number of users.
    console.log("Subscribed to updates!");
    stompClient.publish({
        destination: "/app/users",
        body: JSON.stringify({"uuid": crypto.randomUUID()})
    });
};

stompClient.onWebSocketError = (error) => {
    console.error("Error with websocket", error);
};

stompClient.onStompError = (frame) => {
    console.error("Broker reported error: " + frame.headers["message"]);
    console.error("Additional details: " + frame.body);
};

function setConnected(connected) {
    $("#button_id_connect").prop("disabled", connected);
    $("#button_id_disconnect").prop("disabled", !connected);
    if (connected) {
        $("#input_id_number_of_users").show();
    }
    else {
        $("#input_id_number_of_users").hide();
    }
}

function connect() {
    console.log("Connecting...");
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected!");
}

function showNumberOfUsers(message) {
    $("#input_id_number_of_users").text(message);
}

$(function () {
    $("form").on("submit", (e) => e.preventDefault());
    $( "#button_id_connect" ).click(() => connect());
    $( "#button_id_disconnect" ).click(() => disconnect());
});