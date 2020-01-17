
import org.newdawn.slick.Image;


public abstract class Thrown {
    protected Image img1, img2, img3;
    protected int xloc, yloc;
    public Thrown(/*Image a, Image b, Image c,*/ int x, int y){
        //img1 = a;
        //img2 = b;
        //img3 = c;
        xloc = x;
        yloc = y;
    }
    public abstract void move();
        
    
}
