package chatroom.ui.enums;

/**
 * 使用枚举表述常量数据字段
 */
public enum ButtonEnum {

    CONNECT_TO_SERVER(0, "连接服务器"),
    SEND(1, "发送(S)"),
    CLOESE(2, "关闭(C)"),
    TEXT_STYLE(3, "字体");

    private int id;
    private String expression;

    ButtonEnum(int id, String expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public int getId() {
        return id;
    }
}
