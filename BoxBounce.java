import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Class BoxBounce - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 *
 * @version 2011.07.31
 */

public class BoxBounce
{
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of ground
    private final int skyPosition;         // y position of sky
    private final int leftWall;            // x position of left wall
    private final int rightWall;           // x position of right wall
    private Canvas canvas;
    public Random randGetter;
    private int ySpeed = 0;                // initial downward speed
    private int xSpeed = 0;
    private int counter;

    /**
     * Constructor for objects of class BoxBounce
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBounce(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int groundPos, int skyPos, int lWallPos, int rWallPos, 
                        Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        groundPosition = groundPos;
        skyPosition = skyPos;
        leftWall = lWallPos;
        rightWall = rWallPos;
        canvas = drawingCanvas;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            

        // compute new position
        if(counter == 1)
        {
            randGetter = new Random();
            while(ySpeed == 0)
            {
                ySpeed = randGetter.nextInt(15) - 7;
            }
            while(xSpeed == 0) 
            {
                xSpeed = randGetter.nextInt(15) - 7;
            }
            
        }
        counter++;
            
        yPosition += ySpeed;
        xPosition += xSpeed;

        // check if it has hit the ground
        if(yPosition >= (groundPosition - diameter)) {
            yPosition = (int)(groundPosition - diameter);
            ySpeed = -ySpeed; 
        }
        
        if(yPosition <= (skyPosition)) {
            yPosition = skyPosition;
            ySpeed = -ySpeed;
        }
        
        if(xPosition <= (leftWall)) {
            xPosition = (int)(leftWall + diameter);
            xSpeed = -xSpeed;
        }
        
        if(xPosition > (rightWall - diameter)) {
            xPosition = (int)((rightWall - diameter) - 10);
            xSpeed = -xSpeed;
        }

        // draw again at new position
        draw();
    }    

    /**
     * Method name: getXPosition
     * return type: int
     * @return xPostition
     * Used for the value of xPosition
     * which is the horizontal position
     * of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * Method name: getYPosition
     * return type: int
     * @return yPosition
     * Used for the value of yPosition
     * which is the vertical position
     * of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
