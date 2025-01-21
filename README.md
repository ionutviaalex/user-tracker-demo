Tech stack:
The project uses Spring Boot with Java 17, Maven and WebSockets.
Spring Boot is used in order to avoid extensive configurations and boiler plate, easy access to Tomcat server as well as an easy way to start the application.
WebSocket (using STOMP) was chosen since the requirement for the application was that the BE pushes data to all connected UI clients without repeated requests from the UI.
The UI is made using plain HTML and Javascript in order to avoid packet managers and make it easier to start/access the UI. It is served by the embedded Tomcat server from Spring Boot and it uses the StompJS in order to connect to the BE.

How to start:
After cloning the project locally, it can be started using an IDE (I used Intellij Community) and running the main method from UserTrackingDemoApplication.java.
This will start the project and it will serve the UI pages on localhost:8081.
In the UI page 2 button will be displayed: Connect and Disconnect.
When the Connect button is pressed the UI will initialize a WebSocket connection with the BE and a label below will become visible and display the number of connected users. This button will be disabled and then the Disconnect button will become usable.
When the Disconnect button is pressed the connection to the BE will be closed and the number of connected users will become hidden. This button will be disabled and then the Connect button will become usable again.
