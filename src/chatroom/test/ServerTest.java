package chatroom.test;

import chatroom.server.entity.Server;

public class ServerTest {
    public static void main(String[] args) {
        Server server = new Server();
        server.startup();
    }
}
