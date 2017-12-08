package chatroom.ui.enums;

public enum  LabelEunm {

    SERVER_HOST("服务器 IP"),
    NICKNAME("你的昵称");

    private String expression;

    LabelEunm(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
