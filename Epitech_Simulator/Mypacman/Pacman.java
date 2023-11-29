package Mypacman;

public class Pacman extends Componant {

    private long lastAnimationTime = System.currentTimeMillis();
    private boolean mouthOpen = true;

    public boolean isMouthOpen() {
        return mouthOpen;
    }

    public Pacman(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(char direction) {
        switch (direction) {
            case 'R':
            //pour fix le bug d'image qui superpose les murs on check aussi les 4 coins de la case tout après le &&
                if (isValid(x + cellSize, y)&& isValid(x + cellSize, y+cellSize-5))
                    x += speed;
                    //retour par la gauche
                else if(y>178 && y<182 && x==380)
                    x=20;
                index = 1;
                break;
            case 'L':
                if (isValid(x - speed, y) && isValid(x-speed, y+cellSize-5)) 
                    x -= speed;
                else if(y>178 && y<182 && x==20)//retour par la droite
                    x=380;
                index = 0; 
                break;
            case 'U':
                if (isValid(x, y - speed)&& isValid(x+cellSize-5, y - speed)) 
                    y -= speed;
                index = 2;
                break;
            case 'D':
                if (isValid(x, y + cellSize)&& isValid(x+cellSize-5, y + cellSize))
                    y += speed;
                index = 3;
                break;
            }

            // Check if it's time to animate the mouth

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastAnimationTime >= 250) { // 250 milliseconds = 0.25 seconds
                mouthOpen = !mouthOpen; // Toggle the mouth state
                lastAnimationTime = currentTime; // Reset the animation timer
        }
    }
}
