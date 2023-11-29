package Mypacman;
import java.util.HashSet;
import java.util.Set;

public class Ghost extends Componant{

    private int pacmanX; // Pac-Man's X coordinate
    private int pacmanY; // Pac-Man's Y coordinate

    public Ghost(int x, int y){
        this.x=x;
        this.y=y;
        direction = 'L'; //attention char seul avec '' sinon ca error :(
    }

    public void setPacmanPosition(int px, int py) {
        this.pacmanX = px;
        this.pacmanY = py;
    }

    public boolean choice(){
        if(x%cellSize==0 && y%cellSize==0)
            return true;
        else    
            return false;
    }

    public char selectDirection(){
        int random;
        int newx=x;
        int newy=y;
        Set<Character> mySet=new HashSet<Character>();

        char backward='R';

        switch (direction){
            case 'R':
                backward='L'; 
                break;
            case 'L':
                backward='R';
                break;
            case 'U':
                backward='D';
                break;
            case 'D':
                backward='U';
                break;
        }
        char newDirection=backward;
        while(newDirection==backward||!isValid(newx,newy)){
            if (mySet.size()==3)
            {
                mySet.clear();
                newDirection=backward;
                break;
            } 

            random=(int)(Math.random()*4+1);

            if (random==1){
                newDirection='L';
                newx-=speed;
            }
            else if (random==2){ 
                newDirection='R';
                newx+=cellSize;
            }
            else if (random==3){
                newDirection='U';
                newy-=speed;
            }
            else if (random==4){
                newDirection='D';
                newy+=cellSize;
            }

            if (newDirection!=backward){
                mySet.add(newDirection);            
            }
            index=random%2;
        }
        return newDirection;
    }
//     public char selectDirection(){
//     if (!choice()) {
//         return direction; // Continue in the same direction if no choice is to be made
//     }

//     int diffX = pacmanX - x;
//     int diffY = pacmanY - y;
//     char[] directions;

//     if (Math.abs(diffX) > Math.abs(diffY)) {
//         directions = (diffX > 0) ? new char[]{'R', 'D', 'U', 'L'} : new char[]{'L', 'D', 'U', 'R'};
//     } else {
//         directions = (diffY > 0) ? new char[]{'D', 'R', 'L', 'U'} : new char[]{'U', 'R', 'L', 'D'};
//     }

//     for (char dir : directions) {
//         switch (dir) {
//             case 'R':
//                 if (isValid(x + cellSize, y))
//                     return 'R';
//                 break;
//             case 'L':
//                 if (isValid(x - speed, y))
//                     return 'L';
//                 break;
//             case 'U':
//                 if (isValid(x, y - speed))
//                     return 'U';
//                 break;
//             case 'D':
//                 if (isValid(x, y + cellSize))
//                     return 'D';
//                 break;
//         }
//     }

//     return direction; // marche pas :/
// }

    public void move()
    {
        if (choice()){
            direction=selectDirection();
        }

        switch(direction){
            case 'R':
                if (isValid(x+cellSize,y))
                    x+=speed;
                break;
            case 'L':
                if (isValid(x-speed,y))
                    x-=speed;
                break;
            case 'U':
                if (isValid(x,y-speed))
                    y-=speed;
                break;
            case 'D':
                if (isValid(x,y+cellSize))
                    y+=speed;
                break;
        }
    }

}
