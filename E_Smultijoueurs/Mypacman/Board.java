package Mypacman;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.Timer;
import javax.swing.JFrame;


public class Board extends JPanel{

    int score;
    boolean multiplayer = true;

    public boolean isMultiplayer () {
        return multiplayer;
    }

    Image superDotTopLeft = Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/guillaume.png");
    Image superDotTopRight = Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/Guilhem.png");
    Image superDotBottomLeft = Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/fabien.png");
    Image superDotBottomRight = Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/Joshua.png");
    Image superDotMiddle=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/lois.png");


    Image titleScreen= Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/titleScreen.png");
    Image Over= Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/Gameover.png");
    Image Win= Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/Win.jpeg");
    //fantome
    Image red_ghost=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/paulD.png");
    Image red_ghost2=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/paulG.png");
    Image[] G_red={red_ghost,red_ghost2};

    Image yellow_ghost=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/julienD.png");
    Image yellow_ghost2=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/julienG.png");
    Image[] G_yellow={yellow_ghost,yellow_ghost2};

    Image blue_ghost=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/alexD.png");
    Image blue_ghost2=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/alexG.png");
    Image[] G_blue={blue_ghost,blue_ghost2};

    Image pink_ghost=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/QuentinD.jpg");
    Image pink_ghost2=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/QuentinG.jpg");
    Image[] G_pink={pink_ghost,pink_ghost2};

    //pacman
    Image pacmanImage=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/pacman.jpg");

    Image pacman_left=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/pacmanleft.jpg");
    Image pacman_right=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/pacmanright.jpg");
    Image pacman_up=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/pacmanup.jpg");
    Image pacman_down=Toolkit.getDefaultToolkit().getImage("E_Smultijoueurs/img/pacmandown.jpg");
    Image[] Pacman_images = {pacman_left, pacman_right, pacman_up, pacman_down};
    Pacman pacman= new Pacman(10*Componant.cellSize, 15*Componant.cellSize);
    Pacman pacmanMulti = new Pacman(10*Componant.cellSize, 15*Componant.cellSize);



    //flage superdot
    boolean superDotTopLeftActive = true;
    boolean superDotTopRightActive = true;
    boolean superDotBottomLeftActive = true;
    boolean superDotBottomRightActive = true;
    boolean superDotMiddleActive = true;



    //Ghost
    Ghost ghost1=new Ghost(10*Componant.cellSize,8*Componant.cellSize);
    Ghost ghost2=new Ghost(10*Componant.cellSize,9*Componant.cellSize);
    Ghost ghost3=new Ghost(11*Componant.cellSize,9*Componant.cellSize);
    Ghost ghost4=new Ghost(9*Componant.cellSize,9*Componant.cellSize);



    boolean title; 
    boolean balls[][];
    boolean states[][];
    int lives =3;

    public void drawlives(Graphics g){
        g.setColor(Color.red);
        for (int i=0;i<lives;i++)
            //g.fillOval(Componant.cellSize*i+15 ,Componant.max+10,Componant.cellSize,Componant.cellSize);
            g.drawImage(pacman_left,(Componant.cellSize+5)*i+15,Componant.max+10,null);
        }

    public void init(){

        for(int i=0;i<Componant.cellSize;i++){
            for(int j=0;j<Componant.cellSize;j++){
                balls[i][j]=true;
                states[i][j]=true;
            }
    }
    balls[10][8]=false;//aparition fantom
    balls[10][9]=false;//aparition fantom
    balls[9][9]=false;//aparition fantom
    balls[11][9]=false;//aparition fantom
    balls[10][15]=false;//aparition pacman
    }

    public Board(){
        title=true;
        balls=new boolean[Componant.cellSize][Componant.cellSize]; 
        states=new boolean[Componant.cellSize][Componant.cellSize]; 
        init(); 
    }

    public void update(Graphics g, int x, int y, int width, int height){
        g.fillRect(x, y, width, height);

        for(int i=x/20;i< x/20+width/20;i++){
            for(int j=y/20;j<y/20+height/20;j++){
                balls[i][j]=false;
                states[i-1][j-1]=false;
            }
        }
    }

    protected void paintSuperDot(Graphics g){
        int middleX = 10 * Componant.cellSize;
        int middleY = 9 * Componant.cellSize;
        
        int topLeftX = Componant.cellSize;
        int topLeftY = Componant.cellSize;
    
        int topRightX = (Componant.cellSize * (Componant.maxCell - 1));
        int topRightY = Componant.cellSize;
    
        int bottomLeftX = Componant.cellSize;
        int bottomLeftY = (Componant.cellSize * (Componant.maxCell - 1));
    
        int bottomRightX = (Componant.cellSize * (Componant.maxCell - 1));
        int bottomRightY = (Componant.cellSize * (Componant.maxCell - 1));
        if (superDotTopLeftActive) {
            g.drawImage(superDotTopLeft, topLeftX, topLeftY, null);
        }
        if (superDotTopRightActive) {
            g.drawImage(superDotTopRight, topRightX, topRightY, null);
        }
        if (superDotBottomLeftActive) {
            g.drawImage(superDotBottomLeft, bottomLeftX, bottomLeftY, null);
        }
        if (superDotBottomRightActive) {
            g.drawImage(superDotBottomRight, bottomRightX, bottomRightY, null);
        }
        if (superDotMiddleActive) {
            g.drawImage(superDotMiddle, middleX, middleY, null);
        }
    }

    public void checkSuperDotCollisions(Pacman pacman) {


        int middleX = 10 * Componant.cellSize;
        int middleY = 9 * Componant.cellSize;
        
        int topLeftX = Componant.cellSize;
        int topLeftY = Componant.cellSize;
    
        int topRightX = (Componant.cellSize * (Componant.maxCell - 1));
        int topRightY = Componant.cellSize;
    
        int bottomLeftX = Componant.cellSize;
        int bottomLeftY = (Componant.cellSize * (Componant.maxCell - 1));
    
        int bottomRightX = (Componant.cellSize * (Componant.maxCell - 1));
        int bottomRightY = (Componant.cellSize * (Componant.maxCell - 1));

        int collisionRange = Componant.cellSize / 2;

        // Check if Pac-Man is within the collision range of a super dot
        if (superDotTopLeftActive && Math.abs(pacman.x - topLeftX) < collisionRange && Math.abs(pacman.y - topLeftY) < collisionRange) {
            score += 100;
            superDotTopLeftActive = false;
        }
        if (superDotTopRightActive && Math.abs(pacman.x - topRightX) < collisionRange && Math.abs(pacman.y - topRightY) < collisionRange) {
            score += 100;
            superDotTopRightActive = false;
        }
        if (superDotBottomLeftActive && Math.abs(pacman.x - bottomLeftX) < collisionRange && Math.abs(pacman.y - bottomLeftY) < collisionRange) {
            score += 100;
            superDotBottomLeftActive = false;
        }
        if (superDotBottomRightActive && Math.abs(pacman.x - bottomRightX) < collisionRange && Math.abs(pacman.y - bottomRightY) < collisionRange) {
            score += 100;
            superDotBottomRightActive = false;
        }
        if (superDotMiddleActive && Math.abs(pacman.x - middleX) < collisionRange && Math.abs(pacman.y - middleY) < collisionRange) {
            lives++;
            superDotMiddleActive = false;
        }
        

    }
    
    


    public void drawBalls(Graphics g){
        g.setColor(Color.red);
        for(int i=1; i<Componant.cellSize; i++)
            for(int j=1; j<Componant.cellSize; j++)
                if(balls[i][j])
                    g.fillOval(i*20+8, j*20+8, 4, 4);
                
                }
    public void reset(){
        if (lives>0)
        lives--;
        
    ghost1.x=10*Componant.cellSize;
    ghost1.y=8*Componant.cellSize;

    ghost2.x=10*Componant.cellSize;
    ghost2.y=9*Componant.cellSize;

    ghost3.x=11*Componant.cellSize;
    ghost3.y=9*Componant.cellSize;

    ghost4.x=9*Componant.cellSize;
    ghost4.y=9*Componant.cellSize;

    pacman.x=10*Componant.cellSize;
    pacman.y=15*Componant.cellSize;

    Game.flag=true;

    }

    public void drawBoard(Graphics g){
        g.setColor(Color.white);
        g.drawRect(19, 19, 382, 382);

        g.setColor(Color.blue);
        update(g,40,40,60,20);
        update(g,120,40,60,20);
        update(g,200,20,20,40);
        update(g,240,40,60,20);
        update(g,320,40,60,20);
        update(g,40,80,60,20);
        update(g,160,80,100,20);
        update(g,200,80,20,60);
        update(g,320,80,60,20);
        update(g,20,120,80,60);
        update(g,320,120,80,60);
        update(g,20,200,80,60);
        update(g,320,200,80,60);
        update(g,160,160,40,20);
        update(g,220,160,40,20);
        update(g,160,180,20,20);
        update(g,160,200,100,20);
        update(g,240,180,20,20);
        update(g,120,120,60,20);
        update(g,120,80,20,100);
        update(g,280,80,20,100);
        update(g,240,120,60,20);
        update(g,280,200,20,60);    
        update(g,120,200,20,60); 
        update(g,160,240,100,20);
        update(g,200,260,20,40);
        update(g,120,280,60,20);
        update(g,240,280,60,20);
        update(g,40,280,60,20);
        update(g,80,280,20,60);
        update(g,320,280,60,20);
        update(g,320,280,20,60);
        update(g,20,320,40,20);
        update(g,360,320,40,20);
        update(g,160,320,100,20);
        update(g,200,320,20,60);
        update(g,40,360,140,20);
        update(g,240,360,140,20);
        update(g,280,320,20,60);
        update(g,120,320,20,60);

        repaint();
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, 420, 500);
        

        paintSuperDot(g);

        
        drawBoard(g);
        drawBalls(g);
        drawlives(g);

        Font font= new Font("Arial",Font.BOLD,20);
        g.setFont(font);

        g.drawString("Score: "+score, Componant.max/2+50, Componant.max+20);

        g.drawImage(G_red[ghost1.index], ghost1.x, ghost1.y, null);
        g.drawImage(G_yellow[ghost2.index], ghost2.x, ghost2.y, null);
        g.drawImage(G_pink[ghost3.index], ghost3.x, ghost3.y, null);
        g.drawImage(G_blue[ghost4.index], ghost4.x, ghost4.y, null);



        g.drawImage(Pacman_images[pacman.index], pacman.x, pacman.y, null);
        if(isMultiplayer()){
            g.drawImage(Pacman_images[pacmanMulti.index], pacmanMulti.x, pacmanMulti.y, null);
        }
        
        if(title)
            g.drawImage(titleScreen, 0, 0, null);
        if (lives==0)
            g.drawImage(Over, 0, 0, null);
        if (check())
            g.drawImage(Win, 0, 0, null) ;


    }

    public boolean check() {
        for (int i = 1; i < Componant.cellSize; i++) {
            for (int j = 1; j < Componant.cellSize; j++) {
                if (balls[i][j])
                    return false;
            }
        }
        return true;
    }


        /*
        balls[10][8]=false;
        balls[10][9]=false;
        balls[9][9]=false;
        balls[11][9]=false;
        balls[10][15]=false;
         */
    }