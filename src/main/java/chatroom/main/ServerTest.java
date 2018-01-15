package chatroom.main;

import chatroom.server.controller.Server;

public class ServerTest {
    public static void main(String[] args) {
        Server server = new Server();
        server.startup();
    }
}
