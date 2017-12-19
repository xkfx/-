package chatroom.test;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class StyledDocumentTest extends JFrame implements DocumentListener {

    public StyledDocumentTest() {
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultStyledDocument document = new DefaultStyledDocument();
        JTextPane textPane = new JTextPane(document);

        AttributeSet attributeSet = new SimpleAttributeSet();
        System.out.println(textPane.getForeground());

        document.addDocumentListener(this);

        add(textPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame w = new StyledDocumentTest();
        w.setVisible(true);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        System.out.println("insertUpdate: " + e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        System.out.println("removeUpdate: " + e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("changedUpdate: " + e);
    }
}
