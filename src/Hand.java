
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Hand extends Entities {

    ArrayList<attk> f = new ArrayList();
    private int xloc, yloc;
    int newp;

    public Hand(int x, int y, int p, String img1, String img2, String img3) throws SlickException {
        super(x, y, p, img1, img2, img3);
        xloc = x;
        yloc = y;

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

    public void blast(Graphics g) {
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

    public ArrayList getList() {
        return f;
    }

    public void delList(int d) {
        f.remove(d);
    }

    @Override
    public void heal() {

    }
    public attk getShot(int pl){
        return f.get(pl);
    }
    @Override
    public void draw(Graphics g) {
        g.fillOval(xloc, yloc, 100, 100, 100);
        System.out.println(xloc + ", " + yloc);
    }

    

    @Override
    public void move() {
        newp = (int) (Math.random() * 1000);
        yloc = newp;
    }
}
