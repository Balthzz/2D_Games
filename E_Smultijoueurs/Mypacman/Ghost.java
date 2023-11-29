
package Mypacman;
import java.util.HashSet;
import java.util.Set;

public class Ghost extends Componant{
    public Ghost(int x, int y){
        this.x=x;
        this.y=y;
        direction = 'L'; //attention char seul avec '' sinon ca error :(
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
