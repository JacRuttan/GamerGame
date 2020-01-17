
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Me extends Entities {
private int xloc, yloc, perc, velx, vely, xlaser, ylaser;
private Rectangle shinebox;
Image img[];
ArrayList <Rectangle> x;
    public Me(int x, int y, int sx, int sy, int p, String ig1, String ig2, String ig3) throws SlickException {
        super(x, y, p, ig1, ig2, ig3);
        xloc = x;
        yloc = y;
        perc = p;
        velx = sx;
        vely = sy;
//        img[1] = new Image(ig1);
    //    img[2] = new Image(ig2);
     //   img[3] = new Image(ig3);
    }

    @Override
    public void shoot() {
       Rectangle temp = new Rectangle(xloc+50, yloc, 30, 10);
        if (x.size()<20) {
            x.add(temp);
        }
        for (int i = 0; i < x.size(); i++) {
            
        }
    }

    @Override
    public void heal() {
        
    }

    @Override
    public void draw(Graphics g) {
        
    }

    @Override
    public void move() {
        
    }
    public void shine(ArrayList <attk> bruh, boolean active){
        if(active){
        vely = 0;
        for (int i = 0; i < bruh.size(); i++) {
            if(bruh.get(i).isHitting(shinebox)){
                bruh.get(i).reverse();
            }
            }
        }
        
    }
    public void setXloc(int x){
        xloc = x;
    }
    public void setYloc(int y){
        yloc = y;
    }
    
    public void setVely(int s){
        vely = s;
    }
    public void setVelx(int s){
        velx = s;
    }
    public int getVely(){
            return vely;
        }
    public int getVelx(){
        return velx;
    }
    public void draw(Graphics g, int which){
        img[which].draw();
    }
    public boolean testSmack(attk s){
        Rectangle box = new Rectangle(xloc, yloc, 50, 50);
        return s.isHitting(box);
        
    }
}

