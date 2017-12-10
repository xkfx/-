package chatroom.client.ui.enums;

public enum  LabelEunm {

    NICKNAME("你的昵称：");

    private String expression;

    LabelEunm(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
