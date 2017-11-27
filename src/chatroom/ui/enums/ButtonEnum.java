package chatroom.ui.enums;

/**
 * 使用枚举表述常量数据字段
 */
public enum ButtonEnum {

    SEND(0, "发送(S)"),
    CLOESE(1, "关闭(C)");

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
