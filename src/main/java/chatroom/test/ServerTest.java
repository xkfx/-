package chatroom.test;

import chatroom.server.controller.ServerController;

public class ServerTest {
    public static void main(String[] args) {
        ServerController serverController = new ServerController();
        serverController.startup();
    }
}
