package chatroom.server.model;

import chatroom.common.Message;
import chatroom.server.dto.Login;
import chatroom.server.dto.Register;

public interface UserService {

    Message register(Register reg);

    Message login(Login login);

    Message logout();

    void sendPublicMessage();


}
