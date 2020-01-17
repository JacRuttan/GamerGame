
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Entities {
   protected int xpos, ypos, percent;
   protected Image pic1, pic2, pic3;
   public Entities(int x, int y, int p, String img1, String img2, String img3) throws SlickException{
       xpos = x;
       ypos = y;
       percent = p;
       //pic1 = new Image(img1);
      // pic2 = new Image(img2);
       //pic3 = new Image(img3);
   }
   public abstract void shoot();
   public abstract void heal();
   public abstract void draw(Graphics g);
   public abstract void move();
}
