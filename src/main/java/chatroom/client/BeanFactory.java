package chatroom.client;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于方便获取对象、管理对象间的关系（架构级别的逻辑）
 * 把一些很普遍的关系从应用程序中抽离出来。
 * 需要用到单例
 * 为什么不用 Spring
 * 1、对 Spring 不够熟悉
 * 2、非必要不用，过多的框架会让整个程序变得繁杂，笨重
 */
public class BeanFactory {

    private Map<String, Object> beanMap = new HashMap<>();

    /**
     * 一个bean总是和若干个bean相关，单个bean没有办法形成完整功能
     */
    private void initBeans() {

    }

    public void getBean() {
        // 创建界面

        // 创建handler
        // 注册事件监听
    }

    public void init() {
    }
}
