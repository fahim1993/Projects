import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static java.lang.System.exit;

import java.util.Random;
import javax.swing.*;


public final class Snake implements ActionListener, WindowListener {

    private JFrame frame;
    private DrawPanel drawPanel;

    private int pauseGame = 0;
    private int showPause = 0;
    private int running = 0;
    private int endGame = 0;
    private int gStart = 1;


    private Keyboard keyboardExample;

    private int speed = 70;
    private int a;
    private int oneX = 81;
    private int oneY = 96;
    private int ballCount = 3;

    private int tScore = 0;
    private int prevStep = 2;
    private int newFood = 1;
    private int foodX = 0;
    private int[][] food = new int[500][2];
    private int[][] position = new int[4000][2];

    private Font gameEnd;
    private Font startF;
    private Font startG;
    private Random rndX;
    private Random rndY;


    public static void main(String... args) {
        Snake s = new Snake();
        s.go();
    }

    private void newGame() {
        if (JOptionPane.showConfirmDialog(frame, "Start new game?", "New Game", JOptionPane.YES_NO_OPTION) == 0) {
            drawPanel.repaint();
            pauseGame = 1;
            showPause = 1;
            running = 0;
            endGame = 0;
            a = 5;
            speed = 100;
            oneX = 81;
            oneY = 96;
            ballCount = 3;
            tScore = 0;
            prevStep = 2;
            newFood = 1;
            foodX = 0;
            for (int j = 0; j < 3; j++) {
                position[j][0] = oneX - (j * 14);
                position[j][1] = oneY;
            }
            keyboardExample.state = 5;
            keyboardExample.prevState = 2;
            drawPanel.repaint();

            if (gStart != 0) gStart = 0;
        }

    }

    private void go() {

        rndX = new Random();
        rndY = new Random();
        MenuBar mb = new MenuBar();
        keyboardExample = new Keyboard();
        keyboardExample.state = 5;
        Menu file = new Menu("File");
        Menu highScore = new Menu("High Scores");
        MenuItem newG = new MenuItem("New Game");
        MenuItem pause = new MenuItem("Pause");
        MenuItem save = new MenuItem("Save");
        MenuItem load = new MenuItem("Load");
        MenuItem quit = new MenuItem("Quit");

        newG.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newGame();
                    }
                });
        pause.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        keyboardExample.state = 5;
                        a = 5;
                    }
                });
        save.addActionListener(this);
        load.addActionListener(this);
        file.add(newG);
        file.add(pause);
        file.add(save);
        file.add(load);
        file.addSeparator();
        file.add(quit);
        mb.add(file);
        mb.add(highScore);
        frame = new JFrame("Snake");
        frame.addWindowListener(this);


        gameEnd = new Font("Serif", Font.BOLD, 50);
        startF = new Font("Serif", Font.BOLD, 40);
        startG = new Font("Serif", Font.BOLD, 20);

        frame.add(keyboardExample);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        drawPanel = new DrawPanel();
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.setResizable(false);
        frame.setSize(618, 675);
        frame.setLocationByPlatform(true);
        frame.setMenuBar(mb);

        frame.setVisible(true);

        moveIt(keyboardExample);


        exit(0);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    @Override
    public void windowOpened(WindowEvent e) {
        drawPanel.repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        pauseGame = 1;
        showPause = 1;
        keyboardExample.state = 5;
        a = 5;
        drawPanel.repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        pauseGame = 1;
        showPause = 0;
        keyboardExample.state = 5;
        a = 5;
        // drawPanel.repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class DrawPanel extends JPanel {
        private static final long serialVersionUID = 1L;


        public void paintComponent(Graphics g)

        {
            if (gStart == 1) {
                newG(g);


            } else if (endGame == 1) {
                runninG(g);
                endG(g);

            } else if (pauseGame == 1) {
                runninG(g);
                pausedG(g);

            } else if (running == 1) {
                runninG(g);
            }
        }

        void runninG(Graphics g) {
            if (newFood == 1) {
                food[foodX][0] = rndX.nextInt(42);
                food[foodX][1] = rndY.nextInt(42);
                food[foodX][0] *= 14;
                food[foodX][1] *= 14;
                food[foodX][0] += 11;
                food[foodX][1] += 26;
                newFood = 0;
                foodX++;
            }
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Color.WHITE);
            g.drawString("Score: " + tScore, 500, 17);
            g.fillRect(11, 26, 590, 590);
            g.setColor(Color.RED);

            //g.fillOval(food[foodX-1][0] , food[foodX-1][1], 15, 15);

            //pollute panel with circles [as snake body] (set each position from position array)
            for (int i = ballCount - 1; i > 0; i--) {
                g.fillOval(position[i][0], position[i][1], 15, 15);
            }

            //Head (Useful if the game is paused)
            if ((position[0][0] == food[foodX - 1][0] && position[0][1] == food[foodX - 1][1] - 14)
                    || (position[0][0] + 14 == food[foodX - 1][0] && position[0][1] == food[foodX - 1][1])
                    || (position[0][0] == food[foodX - 1][0] && position[0][1] == food[foodX - 1][1] + 14)
                    || (position[0][0] - 14 == food[foodX - 1][0] && position[0][1] == food[foodX - 1][1])
                    ) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLUE);
            }
            g.fillOval(position[0][0], position[0][1], 15, 15);


            g.setColor(Color.BLACK);
            for (int i = 0; i < foodX; i++) {
                g.fillOval(food[i][0] + 2, food[i][1] + 2, 12, 12);
            }

            if (position[0][0] == food[foodX - 1][0] && position[0][1] == food[foodX - 1][1]) {
                newFood = 1;
                tScore++;
            }
            if (position[ballCount - 1][0] == food[0][0] && position[ballCount - 1][1] == food[0][1]) {
                ballCount++;
                for (int i = 0; i < foodX; i++) {
                    food[i][0] = food[i + 1][0];
                    food[i][1] = food[i + 1][1];
                }
                foodX--;

            }
        }

        void newG(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.BLACK);
            g.setFont(startG);
            g.drawString("* Start new game from File", 85, 287);
            g.drawString("* Press SpaceBar to pause or resume", 85, 317);
            g.drawString("* Avoid running into walls or the snake's body", 85, 347);
            g.drawString("* Collect Foods to get points and grow bigger", 85, 377);
            g.drawString("* Have Fun!", 85, 407);

            g.setFont(startF);
            g.setColor(Color.BLUE);
            g.drawString("*WELCOME TO SNAKE*", 70, 217);
        }

        void pausedG(Graphics g) {
            if (showPause == 1) {

                g.setColor(Color.BLACK);
                g.fillRect(150, 70, 330, 150);
                g.setColor(Color.CYAN);
                g.fillRect(160, 80, 310, 130);
                g.setColor(Color.BLACK);
                g.setFont(gameEnd);
                g.drawString("Paused", 240, 150);
            }
        }

        void endG(Graphics g) {
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }

            g.setColor(Color.BLACK);
            g.fillRect(100, 120, 410, 200);
            g.setColor(Color.CYAN);
            g.fillRect(110, 130, 390, 180);
            g.setColor(Color.BLACK);
            g.setFont(gameEnd);
            g.drawString("GAME OVER", 150, 200);
            g.drawString("Score: " + tScore, 210, 270);
        }

        ;


    }

    private void moveIt(Keyboard keyboardExample) {

        int j;
        while (true) {
            if (running == 1) {

                a = keyboardExample.state;
                //if(a!=5)gameState=0;

                //Prevent from going back in its body
                if (prevStep == 1 && a == 3) a = 1;        // cant go down while going up
                else if (prevStep == 2 && a == 4) a = 2;   // cant go right while going left
                else if (prevStep == 3 && a == 1) a = 3;   // cant go up while going down
                else if (prevStep == 4 && a == 2) a = 4;   // cant go left while going right

                //determine which way to go for each keypress (updating oneX and oneY)

                switch (a) {
                    // 1 for going up
                    case 1:
                        oneX = position[0][0];
                        oneY = position[0][1] - 14;
                        break;

                    // 2 for going left
                    case 2:
                        oneX = position[0][0] + 14;
                        oneY = position[0][1];
                        break;

                    // 3 for going down
                    case 3:
                        oneX = position[0][0];
                        oneY = position[0][1] + 14;
                        break;

                    // 4 for going right
                    case 4:
                        oneX = position[0][0] - 14;
                        oneY = position[0][1];
                        break;

                    case 5:

                        pauseGame = 1;
                        running = 0;
                        endGame = 0;


                        break;

                }

                //Setting up side parameters. Snake must be inside this parameter;
                if (a != 5) {
                    if (oneX < 11 || oneY < 26 || oneX > 590 || oneY > 605) {
                        pauseGame = 0;
                        running = 0;
                        endGame = 1;
                    }

                    //update snake body in position array
                    for (j = ballCount - 2; j >= 0; j--) {
                        position[j + 1][0] = position[j][0];
                        position[j + 1][1] = position[j][1];
                    }

                    //set new position for the head
                    position[0][0] = oneX;
                    position[0][1] = oneY;

                    for (j = 4; j < ballCount; j++) {
                        if (oneX == position[j][0]) {
                            if (oneY == position[j][1]) {
                                pauseGame = 0;
                                running = 0;
                                endGame = 1;
                            }
                        }
                    }
                }


                //remembering last direction
                if (a != 5) prevStep = a;


                drawPanel.repaint();
                try {
                    //System.out.println("2 "+ speed);
                    Thread.sleep(speed);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //

            } else if (pauseGame == 1) {

                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int a;
                a = keyboardExample.state;
                if (a != 5) {
                    pauseGame = 0;
                    running = 1;
                    endGame = 0;
                }
                drawPanel.repaint();


            } else if (endGame == 1) {

                drawPanel.repaint();
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Keyboard extends JPanel {

        private static final long serialVersionUID = 1L;
        int state = 5;
        String tm;
        String ts = "0";
        private int lstsped = speed;
        int prevState = 2;
        private int count1 = 0;
        private int count2 = 0;
        private int count3 = 0;
        private int count4 = 0;
        private int speedtst = 1;

        Keyboard() {
            KeyListener listener = new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {

                    tm = KeyEvent.getKeyText(e.getKeyCode());
                    switch (tm) {
                        case "Up":
                            if (state != 5) {
                                state = 1;
                                count1++;
                                if (count1 > 3 && speedtst == 1) {
                                    lstsped = speed;
                                    ts = KeyEvent.getKeyText(e.getKeyCode());
                                    speed = speed - ((speed * 5) / 6);
                                    speedtst = 0;
                                }
                            }

                            break;
                        case "Right":
                            if (state != 5) state = 2;
                            count2++;
                            if (count2 > 3 && speedtst == 1) {
                                lstsped = speed;
                                ts = KeyEvent.getKeyText(e.getKeyCode());
                                speed = speed - ((speed * 5) / 6);
                                speedtst = 0;
                            }
                            break;
                        case "Down":
                            if (state != 5) state = 3;
                            count3++;
                            if (count3 > 3 && speedtst == 1) {
                                lstsped = speed;
                                ts = KeyEvent.getKeyText(e.getKeyCode());
                                speed = speed - ((speed * 5) / 6);
                                speedtst = 0;
                            }
                            break;
                        case "Left":
                            if (state != 5) state = 4;
                            count4++;
                            if (count4 > 3 && speedtst == 1) {
                                lstsped = speed;
                                ts = KeyEvent.getKeyText(e.getKeyCode());
                                speed = speed - ((speed * 5) / 6);
                                speedtst = 0;

                            }
                            break;
                        case "Space":
                            if (state != 5) state = 5;
                            else {
                                state = prevState;
                            }
                            break;
                        case "N":
                            newGame();
                            break;
                    }
                    if (state != 5) prevState = state;

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (ts.equals(KeyEvent.getKeyText(e.getKeyCode()))) {
                        speedtst = 1;
                        speed = lstsped;
                        ts = "0";
                    }
                    count1 = 0;
                    count2 = 0;
                    count3 = 0;
                    count4 = 0;
                }
            };
            addKeyListener(listener);
            setFocusable(true);
        }
    }
}
