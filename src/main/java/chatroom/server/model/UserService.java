package chatroom.server.model;

import chatroom.common.entity.User;
import chatroom.common.message.Message;
import chatroom.server.dto.Login;
import chatroom.server.dto.Register;

import java.net.Socket;
import java.util.List;

public interface UserService {

    Message register(Register reg);

    Message login(Socket socket, Login login);

    User getUser(Socket socket);

    User getUser(Long userId);

    List<User> getFriendList(Long userId);

    Socket getSocket(Long userId);

    Message logout(Socket socket);

    void sendPublicMessage();
}
