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
    public int getballNumber()
    {
        return ballAmount;
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 300;   // position of the ground line
        int sky = 100;        // posititon of the sky line
        int wall1 = 100;      // position of left wall
        int wall2 = 200;     // position of right wall

        ballsInBox.setVisible(true);
        
        randGetter = new Random();
        int xStart = randGetter.nextInt(400);
        int yStart = randGetter.nextInt(300);
        int diameter = randGetter.nextInt(60) + 5;
        Color color = new Color(randGetter.nextInt(256),randGetter.nextInt(256),randGetter.nextInt(256));
        
        ballCount = new BoxBounce[ballAmount];
        int i = 0;
        for(i = 0; i < ballCount.length; i++) {
            ballCount[i] = new BoxBounce(xStart, yStart, 16, color, ground, sky, wall1, wall2, ballsInBox);
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

            // stop once ball has travelled a certain distance on x axis
            if(ballCount[0].getXPosition() == 40 && ballCount[0].getXPosition() == 30) {
                finished = true;
            }
        ballsInBox.drawLine(wall1, ground, wall2, ground);
        ballsInBox.drawLine(wall1, ground, wall1, sky);
        ballsInBox.drawLine(wall1, sky, wall2, sky);
        ballsInBox.drawLine(wall2, ground, wall2, sky);
        }
        
        
    }
}
