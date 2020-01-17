
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Graphics;

public class Game extends BasicGame {
    
    private boolean isFalling, canmove, canhit;
    private int leavel, leaver;
    Hand hand;
    Image img1, img2, img3;
    Me me;
    int percent;

    public Game() {
        super(null);
    }
    private int fg, bpx, bpy, velx, vely, a, pm;
    private Rectangle box, plat, plat1, plat2, plat3, temp;
    private ArrayList<Rectangle> pls;
    int ju, time, handmovetimer, shoottimer;
    Color bcg;

    private Image plimg;

    public Game(String title) {
        super(title);

    }

    public void init(GameContainer gc) throws SlickException {
        bcg = new Color(40, 92, 12);
        percent = 0;
        bpx = 50;
        canhit = true;
        bpy = 50;
        velx = 0;
        vely = 0;
        box = new Rectangle(10, 10, 50, 50);
        ju = 0;
        me = new Me(bpx, bpy, 0, 10, 10, "images/dianmond.png", "images/dianmond.png", "images/dianmond.png");
        shoottimer = 1;
        plimg = new Image("images/platform.png");
        time = 0;
        hand = new Hand(1500, 300, 300, "images/dianmond.png", "images/dianmond.png", "images/dianmond.png");
        // plat = new Rectangle(0, 300, 100, 50);
        // plat1 = new Rectangle(300, 400, 100, 50);
        //plat2 = new Rectangle(400, 200, 100, 50);
        //plat3 = new Rectangle(0,500, 1000,1000);
        fg = 1;
        handmovetimer = 1;
        isFalling = true;
        canmove = true;
        pls = new ArrayList();
        //pls.add(plat);
        // pls.add(plat1);
        //pls.add(plat2);
        //pls.add(plat3);
        for (int i = 0; i < 10; i++) {
            temp = new Rectangle((int) (Math.random() * 900) + 10, (int) (Math.random() * 900 + 100), 100, 50);

            pls.add(temp);
            for (int j = 0; j < pls.size(); j++) {
                if (temp.contains(pls.get(j))) {
                    temp = new Rectangle((int) (Math.random() * 400) + 1100, (int) (Math.random() * 950) + 100, 100, 50);
                }
            }
        }

    }

    public void update(GameContainer gc, int i) throws SlickException {
        Input in = gc.getInput();
        for (int j = 0; j < hand.getList().size(); j++) {
            if (me.testSmack(hand.getShot(j))||canhit) {
                percent += 1;
                vely -= percent;
                velx -= percent;
                canhit = false;
            }
            
            System.out.println(velx+","+vely);
        }
        if(!canhit){
               
            }
        time++;
        me.setVelx(velx);
        me.setVely(vely);
        me.setXloc(bpx);
        me.setYloc(bpy);
        if (time == 50 * shoottimer) {
            hand.shoot();
            shoottimer++;
        }
        if (time == 300 * handmovetimer) {

            hand.move();
            handmovetimer++;
        }
        for (int j = 0; j < pls.size(); j++) {
            pm = (int) pls.get(j).getX();
            pm--;
            pls.get(j).setX(pm);
            if (pls.get(j).getX() <= 0) {
                pls.remove(j);
                temp = new Rectangle((int) (Math.random() * 400) + 1100, (int) (Math.random() * 950) + 100, 100, 50);
                for (int k = 0; k < pls.size(); k++) {
                    if (temp.contains(pls.get(k))) {
                        temp = new Rectangle((int) (Math.random() * 400) + 1100, (int) (Math.random() * 950) + 100, 100, 50);
                    }
                }
                pls.add(temp);
                isFalling = true;
                ju++;
            }
        }
        //System.out.println(bpx + "," + bpy + "," + leavel);
        if (box.getX() == 0 || box.getX() + box.getWidth() == gc.getWidth()) {
            canmove = false;
            if (box.getX() == 0) {
                bpx = 1;
            } else {
                bpx--;
            }
        } else {
            canmove = true;
        }
        if (!canmove) {
            velx = 0;

        }

        bpx += velx;
        box.setY(bpy);

        if (box.getY() > gc.getHeight()||box.getX() > gc.getWidth()||box.getX()<0) {
            bpy = 50;
            bpx = 50;
            canmove = false;
            isFalling = false;
        }

        box.setX(bpx);
        for (int j = 0; j < pls.size(); j++) {
            if (box.intersects(pls.get(j)) || pls.get(j).contains(box)) {
                isFalling = false;
                ju = 0;
                vely = 0;
                leaver = (int) (pls.get(j).getX() + pls.get(j).getWidth());
                leavel = (int) pls.get(j).getX();
                bpy = (int) pls.get(j).getY() - (int) box.getHeight() + 1;

                if (box.getX() + 15 > leaver || box.getX() + box.getWidth() - 15 < leavel) {
                    isFalling = true;
                    ju++;
                }
                if (box.getY() == pls.get(j).getY() && box.getX() > leavel && box.getX() < leaver) {
                    ju = 0;
                }
                bpx--;
            }

        }
        if (isFalling) {
            vely += fg;
            bpy += vely;
            //bpy++;
        }
        if (in.isKeyDown(Input.KEY_RIGHT)) {
            if (velx < 15) {
                velx++;
            }
        } else {
            if (velx > 0) {
                velx--;
            }
        }
        if (in.isKeyDown(Input.KEY_LEFT)) {
            if (velx > -15) {
                velx--;
            }
        } else {
            if (velx < 0) {
                velx++;
            }
        }
        if (in.isKeyPressed(Input.KEY_DOWN)) {
            isFalling = true;
            bpy += 100;
        }
        if (in.isKeyPressed(Input.KEY_S)) {
            me.shine(hand.getList(), canmove);
            //vely = me.getVely();
        }
        //if(in.isKeyDown(Input.KEY_DOWN)) velx = 0;

        if (in.isKeyPressed(Input.KEY_SPACE) && (!isFalling || ju < 2)) {
            bpy -= 10;
            vely = -20;
            isFalling = true;
            bpx++;
            ju++;
        }
        //if(in.isKeyPressed(Input.KEY_A)){
        //  hand.shoot();      
        //}

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(bcg);
        g.fillRect(0, 0, 1680, 1050);
        g.setColor(Color.red);
        g.draw(box);
        hand.blast(g);
        hand.draw(g);
        for (int i = 0; i < pls.size(); i++) {
            //g.draw(pls.get(i));
            plimg.draw(pls.get(i).getX(), pls.get(i).getY());
        }

    }

    public static void main(String args[]) throws SlickException {
        Game game = new Game("Fox vs the big red box - retrun of his boxliness");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(1680, 1050, false);
        app.setShowFPS(true);
        app.setTargetFrameRate(60);
        app.start();
    }

}
