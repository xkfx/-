package chatroom.client.ui.component;

import javax.swing.*;
import java.awt.*;

public class UserList extends JPanel {
    public UserList() {
        String[] words = {"asd", "adasda", "weqewq", "vzcdaqw",
                "asd", "adasda", "weqewq", "vzcdaqw",
                "asd", "adasda", "weqewq", "vzcdaqw",
                "asd", "adasda", "weqewq", "vzcdaqw",
                "asd", "adasda", "weqewq", "vzcdaqw",
                "asd", "adasda", "weqewq", "vzcdaqw",
                "asd", "adasda", "weqewq", "vzcdaqw",
                "asd", "adasda", "weqewq", "vzcdaqw",
                "asd", "adasda", "weqewq", "vzcdaqw",
                "asd", "adasda", "weqewq", "vzcdaqw",
                "klaaw"};
        JList<String> wordList = new JList<>(words);
        JScrollPane scrollPane = new JScrollPane(wordList);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }
}
