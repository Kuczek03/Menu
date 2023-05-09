import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private static final int BOARD_WIDTH = 300;
    private static final int BOARD_HEIGHT = 300;

    private static final int SQUARE_SIZE = 10;

    private static final int MAX_SQUARES = (BOARD_WIDTH / SQUARE_SIZE) * (BOARD_HEIGHT / SQUARE_SIZE);

    private static final int GAME_SPEED = 100;

    private ArrayList<Point> snake = new ArrayList<Point>();

    // Kierunek węża
    private int dx = 0;
    private int dy = 0;

    // Aktualne położenie jedzienia
    private Point food = new Point();

    private int score = 0;

    private boolean gameOver = false;

    public SnakeGame(){
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initGame();
        Timer timer = new Timer(GAME_SPEED, this);
        timer.start();
    }

    private void initGame() {

        snake.clear();
        snake.add(new Point(BOARD_WIDTH / 2, BOARD_HEIGHT / 2));
        placeFood();


        score = 0;
        gameOver = false;
    }

    private void placeFood() {
        Random rand = new Random();
        int x = rand.nextInt(BOARD_WIDTH / SQUARE_SIZE) * SQUARE_SIZE;
        int y = rand.nextInt(BOARD_HEIGHT / SQUARE_SIZE) * SQUARE_SIZE;
        food.setLocation(x, y);
    }

    private void move() {

        Point head = snake.get(0);
        int newX = head.x + dx * SQUARE_SIZE;
        int newY = head.y + dy * SQUARE_SIZE;
        head.setLocation(newX, newY);

        // Sprawdzanie kolizji
        if (head.x < 0 || head.x >= BOARD_WIDTH || head.y < 0 || head.y >= BOARD_HEIGHT) {
            gameOver = true;
        }

        // Sprawdzanie kolizji z jedzeniem
        if (head.equals(food)) {
            snake.add(new Point());
            placeFood();
            score++;
        }

        // Sprawdzanie kilizji z ciałem
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
            }
        }

        for (int i = snake.size() - 1; i > 0; i--) {
            Point prev = snake.get(i - 1);
            Point curr = snake.get(i);
            curr.setLocation(prev);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        BufferedImage snakeImg = null;
        try {
            snakeImg = ImageIO.read(new File(".\\images\\snakeBody.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage foodImg = null;
        try {
            foodImg = ImageIO.read(new File(".\\images\\food.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        for (Point p : snake) {
            //g.fillRect(p.x, p.y, SQUARE_SIZE, SQUARE_SIZE);
            g.drawImage(snakeImg, p.x, p.y, SQUARE_SIZE, SQUARE_SIZE, null);
        }

        //g.setColor(Color.RED);
        //g.fillRect(food.x, food.y, SQUARE_SIZE, SQUARE_SIZE);
        g.drawImage(foodImg, food.x, food.y, SQUARE_SIZE, SQUARE_SIZE, null);

        g.setColor(Color.WHITE);
        g.drawString("Wynik: " + score, 10, 20);


        if (gameOver) {
            g.setColor(Color.WHITE);
            g.drawString("Przegrałeś! Naciśnij R aby zacząć od nowa.", BOARD_WIDTH / 3 - 70, BOARD_HEIGHT / 2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && dx == 0) {
            dx = -1;
            dy = 0;
        }
        if (key == KeyEvent.VK_RIGHT && dx == 0) {
            dx = 1;
            dy = 0;
        }
        if (key == KeyEvent.VK_UP && dy == 0) {
            dx = 0;
            dy = -1;
        }
        if (key == KeyEvent.VK_DOWN && dy == 0) {
            dx = 0;
            dy = 1;
        }
        if (key == KeyEvent.VK_R && gameOver) {
            initGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

   
