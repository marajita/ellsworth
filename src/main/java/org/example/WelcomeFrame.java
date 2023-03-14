package org.example;

import javax.swing.*;
import java.awt.*;

public class WelcomeFrame extends JFrame {
    private JPanel mainPanel;

    public WelcomeFrame(){
       add(mainPanel);

       setTitle("Hello World");
       setSize(450, 400);
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       setVisible(true);
    }

    public static void main(String[] args){
        WelcomeFrame myFrame = new WelcomeFrame();
    }
}
