import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static java.awt.Color.*;


public class Menu implements ActionListener {

    private final JFrame ramka;
    private final JPanel panel;
    private final JButton game1, game2, game3, exit;
    private final ImageIcon snakeIcon, ticTacToeIcon, icon3;
    private final JLabel snakeLabel, ticTacToeLabel, label3;

    public Menu() {
        ramka = new JFrame("Wybierz grę!");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setPreferredSize(new Dimension(300, 300));
        ramka.setResizable(true);

        panel = new JPanel(null);

        panel.setBackground(new Color(88, 163, 168));

        snakeIcon = new ImageIcon(".\\images\\snake.png");
        snakeLabel = new JLabel(snakeIcon);
        snakeLabel.setBounds(33, 22, 65, 65);
        panel.add(snakeLabel);


        ticTacToeIcon = new ImageIcon(".\\images\\tictactoe.png");
        ticTacToeLabel = new JLabel(ticTacToeIcon);
        ticTacToeLabel.setBounds(113, 22, 65, 65);
        panel.add(ticTacToeLabel);

        icon3 = new ImageIcon(".\\images\\pong.png");
        label3 = new JLabel(icon3);
        label3.setBounds(193, 22, 65, 65);
        panel.add(label3);


        game1 = new JButton();
        game1.setIcon(new ImageIcon(".\\images\\Gra1.png"));
        game1.setHorizontalTextPosition(SwingConstants.CENTER);
        game1.setVerticalTextPosition(SwingConstants.CENTER);
        game1.setBounds(30, 100, 70, 30);
        game1.setBackground(white);
        game1.addActionListener(this);
        panel.add(game1);

        game2 = new JButton();
        game2.setIcon(new ImageIcon(".\\images\\Gra2.png"));
        game2.setHorizontalTextPosition(SwingConstants.CENTER);
        game2.setVerticalTextPosition(SwingConstants.CENTER);
        game2.setBounds(110, 100, 70, 30);
        game2.setBackground(white);
        game2.addActionListener(this);
        panel.add(game2);

        game3 = new JButton();
        game3.setIcon(new ImageIcon(".\\images\\Gra3.png"));
        game3.setHorizontalTextPosition(SwingConstants.CENTER);
        game3.setVerticalTextPosition(SwingConstants.CENTER);
        game3.setBounds(190, 100, 70, 30);
        game3.setBackground(white);
        game3.addActionListener(this);
        panel.add(game3);

        exit = new JButton();
        exit.setIcon(new ImageIcon(".\\images\\EXIT.png"));
        exit.setHorizontalTextPosition(SwingConstants.CENTER);
        exit.setVerticalTextPosition(SwingConstants.CENTER);
        exit.setBounds(190,220,70,30);
        exit.setBackground(white);
        exit.addActionListener(this);
        panel.add(exit);

        ramka.add(panel);
        ramka.pack();
        ramka.setLocationRelativeTo(null);
        ramka.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent option) {
        if (option.getSource() == game1) {
            // Kod dla gry 1
            System.out.println("Wybrano grę 1");
        } else if (option.getSource() == game2) {
            // Kod dla gry 2
            System.out.println("Wybrano grę 2");
        } else if (option.getSource() == game3) {
            // Kod dla gry 3
            System.out.println("Wybrano grę 3");
        } else if (option.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Menu();
    }

}
