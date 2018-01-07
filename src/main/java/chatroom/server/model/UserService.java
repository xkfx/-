package chatroom.server.model;

import chatroom.common.message.Message;
import chatroom.server.dto.Login;
import chatroom.server.dto.Register;
import chatroom.common.entity.User;

public interface UserService {

    Message register(Register reg);

    Message login(int socketCode, Login login);

    User getUser(int socketCode);

    User getUser(Long userId);

    Message logout(int socketCode);

    void sendPublicMessage();
}
