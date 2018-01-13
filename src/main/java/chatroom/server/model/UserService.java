package chatroom.server.model;

import chatroom.common.entity.User;
import chatroom.common.message.Message;
import chatroom.server.dto.Login;
import chatroom.server.dto.Register;

import java.net.Socket;
import java.util.List;

public interface UserService {

    Socket getSocket(Long userId);

    User getUser(Socket socket);

    User getUser(Long userId);

    List<User> getFriendList(Long userId);

    Message register(Register reg);

    Message login(Socket socket, Login login);

    Message logout(Socket socket);
}
