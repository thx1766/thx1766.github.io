
//package Code.simpleOutput;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

/**
 * HelloWorld.java
 * Very simple program that says something and sets the LED color
 * Author:  Tom Lauwers
 */
public class HelloWorld {
 public static void main(final String[] args)
   {
	  // Instantiating the Finch object
	 System.out.println("about to initialize Finch()");
      Finch myFinch = new Finch();
      System.out.println("Finch() Initialized");

      myFinch.saySomething("I'm feeling sky blue");
      myFinch.setLED(0, 255, 255);
      myFinch.sleep(5000);
      // Always end your program with finch.quit()
      myFinch.quit();
      System.exit(0);
    }
}
