
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;


public class attk extends Thrown {
    protected int speed, xdir, ydir,updown;
    public attk(int x, int y, int s, int xd, int yd, int u) {
        super(x, y);
        speed = s;
        xdir = xd;
        ydir = yd;
        updown = u;
        xloc = x;
        yloc = y;
    }

    @Override
    public void move() {
        while(((xdir*xdir)+(ydir+ydir))<=speed){
            xdir++;
            ydir++;
        }
        xloc-=xdir;
        //yloc-=ydir;
        if(updown >= 5) yloc-=ydir;
        else yloc+=ydir;
        
    }
    public int getxDir(){
        return xdir;
    }
    public int getyDir(){
        return ydir;
    }
    

    public void draw(Graphics g) {
        if(xloc>0&&yloc>0){
            if(xloc<1680&&yloc<1050){
             g.fillRect(xloc, yloc, 30, 30);
            } 
        }
                        
    }
    public int getX(){
        return xloc;
    }
    public int getY(){
        return yloc;
    }
    public boolean isHitting(Rectangle r){
        Rectangle thi = new Rectangle(xloc, yloc, 30, 30);
        return thi.intersects(r);
    }
    public void reverse(){
        xdir = -xdir;
        ydir = -ydir;
    }
}
