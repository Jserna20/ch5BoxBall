import java.awt.Color;
import java.util.Random;
import java.util.Arrays;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BoxBall   
{
    private Canvas ballsInBox;
    private boolean finished;
    private Random randGetter;
    private int ballAmount;
    private BoxBounce[] ballCount;
    private int ground;
    private int sky;
    private int leftWall;
    private int rightWall;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BoxBall()
    {
        ballsInBox = new Canvas("BoxBall", 600, 500);
    }
    
    public void setballNumber(int ballNum)
    {
        ballAmount = ballNum;
    }
    
    public int getBallAmount()
    {
        return ballAmount;
    }
    
    public void makeBox(int floor, int ceiling, int lWall, int rWall)
    {
        ground = floor;
        sky = ceiling;
        leftWall = lWall;
        rightWall = rWall;
    }
    
    public int getGround()
    {
        return ground;
    }
    
        public int getSky()
    {
        return sky;
    }
    
        public int getleftWall()
    {
        return leftWall;
    }
    
        public int getRightWall()
    {
        return rightWall;
    }
    

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        getGround();   // position of the ground line
        getSky();        // posititon of the sky line
        getleftWall();      // position of left wall
        getRightWall();     // position of right wall

        ballsInBox.setVisible(true);
        
        randGetter = new Random();
        int xStart = randGetter.nextInt(400);
        int yStart = randGetter.nextInt(300);
        int diameter = randGetter.nextInt(60) + 5;
        Color color = new Color(randGetter.nextInt(256),randGetter.nextInt(256),randGetter.nextInt(256));
        
        ballCount = new BoxBounce[ballAmount];
        int i = 0;
        for(i = 0; i < ballCount.length; i++) {
            ballCount[i] = new BoxBounce(xStart, yStart, 16, color, ground, sky, leftWall, rightWall, ballsInBox);
            ballCount[i].draw();
            xStart = randGetter.nextInt(400);
            yStart = randGetter.nextInt(300);
            diameter = randGetter.nextInt(60) + 5;
            color = new Color(randGetter.nextInt(256),randGetter.nextInt(256),randGetter.nextInt(256));
        }
    

        // make them bounce
        finished =  false;
        while(!finished) {
            ballsInBox.wait(50);           // small delay
            for(i = 0; i < ballCount.length; i++) {
                ballCount[i].move();
            }

            // To allow loop to loop
            if(ballCount[0].getXPosition() == 40 && ballCount[0].getXPosition() == 30) {
                finished = true;
            }
        ballsInBox.drawLine(leftWall, ground, rightWall, ground);
        ballsInBox.drawLine(leftWall, ground, leftWall, sky);
        ballsInBox.drawLine(leftWall, sky, rightWall, sky);
        ballsInBox.drawLine(rightWall, ground, rightWall, sky);
        }
        
        
    }
}
