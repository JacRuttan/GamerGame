
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
    ArrayList<Rectangle> xa;
    ArrayList <attk> f;
    public Me(int x, int y, int sx, int sy, int p, Image ig1, Image ig2, Image ig3) throws SlickException {
        super(x, y, p, ig1, ig2, ig3);
        xloc = x;
        yloc = y;
        perc = p;
        velx = sx;
        vely = sy;
        //img[0] = ig1;
        //img[1] = ig2;
        //img[2] = ig3;
        ArrayList<Rectangle> xa = new ArrayList();
        ArrayList<attk> f = new ArrayList();
    }

    @Override
    public void shoot() {
        int n;

        attk temp;
        n = (int) (Math.random() * 10) + 5;
        for (int i = 0; i < n; i++) {
            int xdir = (int) (Math.random() * 10);
            int ydir = (int) (Math.random() * 10);
            int upd = (int) (Math.random() * 10);
            temp = new attk(xloc, yloc, 100, xdir, ydir, upd);
            f.add(temp);
        }
    }
    public void blast(Graphics g){
        for (int i = 0; i < f.size(); i++) {
            f.get(i).move();
            f.get(i).draw(g);
            if (f.get(i).getX() < 0 || f.get(i).getY() < 0) {
                if (f.get(i).getX() > 1680 || f.get(i).getY() > 1050) {
                    f.remove(i);
                }
            }
        }
    }

    @Override
    public void heal() {

    }

    @Override
    public void draw(Graphics g, int t) {
        //for (int i = 0; i < xa.size(); i++) {
          //  g.draw(xa.get(i));
       // }
       // img[t].draw();
        
    }

    @Override
    public void move() {

    }

    public void shine(ArrayList<attk> bruh, boolean active) {
        if (active) {
            vely = 0;
            for (int i = 0; i < bruh.size(); i++) {
                if (bruh.get(i).isHitting(shinebox)) {
                    bruh.get(i).reverse();
                }
            }
        }

    }

    public void setXloc(int x) {
        xloc = x;
    }

    public void setYloc(int y) {
        yloc = y;
    }

    public void setVely(int s) {
        vely = s;
    }

    public void setVelx(int s) {
        velx = s;
    }

    public int getVely() {
        return vely;
    }

    public int getVelx() {
        return velx;
    }

    
    

    public boolean testSmack(attk s) {
        Rectangle box = new Rectangle(xloc, yloc, 50, 50);
        return s.isHitting(box);

    }

   
}
