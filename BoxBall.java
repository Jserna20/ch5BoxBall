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
    
    /**
     * Method name: setBallNumber
     * return type: void
     * @param ballNum The amount of balls bouncing
     * Sets the amount of balls bouncing
     */
    public void setBallNumber(int ballNum)
    {
        if(ballNum > 0)
        ballAmount = ballNum;
    }
    
    /**
     * Method name: getBallAmount
     * return type: int
     * @return ballAmount
     * Used for the value of ballAmount later on
     */
    public int getBallAmount()
    {
        return ballAmount;
    }
    
    /**
     * Method name: makeBox
     * return type: void
     * @param floor The bottom the box
     * @param ceiling The top of the box
     * @param lWall The left wall of the box
     * @param rWall The right wall of the box
     * This makes a box by using the value as points
     * for the drawLine method later on
     */
    public void makeBox(int floor, int ceiling, int lWall, int rWall)
    {
        ground = floor;
        sky = ceiling;
        leftWall = lWall;
        rightWall = rWall;
    }
    
    /**
     * Method name: getGround
     * return type: int
     * @return ground
     * Used for the value of ground later on
     */
    public int getGround()
    {
        return ground;
    }
    
    /**
     * Method name: getSky
     * return type: int
     * @return sky
     * Used for the value of sky later on
     */
        public int getSky()
    {
        return sky;
    }
    
    /**
     * Method name: getLeftWall
     * return type: int
     * @return lWall
     * Used for the value of lWall later on
     */
        public int getLeftWall()
    {
        return leftWall;
    }
    
    /**
     * Method name: getRightWall
     * return type: int
     * @return rWall
     * Used for the value of rWall later on
     */
        public int getRightWall()
    {
        return rightWall;
    }
    

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        if(getBallAmount() > 0)
        {
            getGround();   // position of the ground line
            getSky();        // posititon of the sky line
            getLeftWall();      // position of left wall
            getRightWall();     // position of right wall

            ballsInBox.setVisible(true);
        
            randGetter = new Random();
            int xStart = randGetter.nextInt(400);
            int yStart = randGetter.nextInt(300);
            int diameter = randGetter.nextInt(60) + 5;
            Color color = new Color(randGetter.nextInt(256),randGetter.nextInt(256),randGetter.nextInt(256));
        
            ballCount = new BoxBounce[getBallAmount()];
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
            while(!finished) 
            {
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
}
