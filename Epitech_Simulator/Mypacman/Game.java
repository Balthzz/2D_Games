package Mypacman;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {

    Board board = new Board();
    Timer timer;
    char direction ; // Direction du Pacpac
    static boolean flag=true;//pour le bug de la touche espace qui lance le jeu



    public Game() {
        JFrame frame = new JFrame();
        frame.setTitle("EpitechSimulator");
        frame.setSize(420, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.add(board, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.addKeyListener(this); // Add the key listener
        
        // Timer to handle game updates
        timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!board.title && board.lives>0){//on bouge rien si on lance pas le jeu

                    if (flag){
                        try{
                        Thread.sleep(2000);
                        flag=false;
                        }
                        catch(Exception ex){
                            System.out.println(ex);
                        }
                    }
                    board.checkSuperDotCollisions();
                // Move the ghosts
                board.ghost1.move();
                if (board.ghost1.getShape().intersects(board.pacman.getShape())) {
                    board.reset();
                }
                board.ghost2.move();
                if (board.ghost2.getShape().intersects(board.pacman.getShape())){
                    board.reset();
                }
                board.ghost3.move();
                if (board.ghost3.getShape().intersects(board.pacman.getShape())){
                    board.reset();
                }
                board.ghost4.move();
                if (board.ghost4.getShape().intersects(board.pacman.getShape())){
                    board.reset();
                }

                // Update the state of the ghosts
                board.ghost1.updateState(board.states);
                board.ghost2.updateState(board.states);
                board.ghost3.updateState(board.states);
                board.ghost4.updateState(board.states);

                // Move Pacman based on the current direction
                board.pacman.move(direction);

                //score implemmm
                if(board.balls[board.pacman.x / 20][board.pacman.y / 20])
                    board.score+=10;

                // Eat the ball at the new position
                board.balls[board.pacman.x / 20][board.pacman.y / 20] = false;

                // Update the state of Pacman
                board.pacman.updateState(board.states);

                // Refresh the board
                board.repaint();
            }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new Game(); // Start the game
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Set the direction based on the key pressed
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = 'L';
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 'R';
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = 'U';
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = 'D';
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            board.title=false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but you need to implement it
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used, but you need to implement it
    }
}
