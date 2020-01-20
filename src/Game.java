
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
    
    int which;
    int percent;
    ArrayList<Rectangle> xa;

    public Game() {
        super(null);
    }
    int stock;
    private int fg, bpx, bpy, velx, vely, a, pm, lasx;
    private Rectangle box, plat, plat1, plat2, plat3, temp;
    private ArrayList<Rectangle> pls;
    int ju, time, handmovetimer, shoottimer;
    Color bcg;
    Rectangle Hfull, hloss;
    int health;
    int canhittime;
    Image fox1, fox2, fox3, imghand;
    private Image plimg;
    ArrayList <Rectangle> please;
boolean gameover;
    public Game(String title) {
        super(title);

    }

    public void init(GameContainer gc) throws SlickException {
        bcg = new Color(40, 92, 12);
        percent = 0;
        health = 100;
        Hfull = new Rectangle(900, 100, 20, 100);
        hloss = new Rectangle(900, 100, 20, health);
//        fox1 = new Image("images/foxstang.png");
  //      fox2 = new Image("images/Foxrun1.png");
    //    fox3 = new Image("images/Foxrun2.png");
      //  imghand = new Image("images/Handimg.png");
        bpx = 50;
        stock = 4;
        xa = new ArrayList();
        canhittime = 0;
        canhit = true;
        bpy = 50;
        velx = 0;
        vely = 0;
         please = new ArrayList();
        gameover = false;
        box = new Rectangle(10, 10, 50, 50);
        ju = 0;
        me = new Me(bpx, bpy, 0, 10, 10, fox1, fox2, fox3); 
        shoottimer = 1;
        plimg = new Image("images/platform.png");
        time = 0;
        hand = new Hand(1500, 300, 300, imghand, fox1, fox2);
        // plat = new Rectangle(0, 300, 100, 50);
        // plat1 = new Rectangle(300, 400, 100, 50);
        //plat2 = new Rectangle(400, 200, 100, 50);
        //plat3 = new Rectangle(0,500, 1000,1000);
        fg = 1;
        handmovetimer = 1;
        isFalling = true;
        lasx = 10;
        canmove = true;
        pls = new ArrayList();
        //pls.add(plat);
        // pls.add(plat1);
        //pls.add(plat2);
        //pls.add(plat3);
        which = 0;
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
        gameover = hand.gg();
        for (int j = 0; j < hand.getList().size(); j++) {
            if (me.testSmack(hand.getShot(j)) || canhit) {
                percent += 1;
                vely -= percent;
                velx -= percent;
                canhit = false;
            }

            System.out.println(velx + "," + vely);
        }
        if (!canhit) {
            canhittime++;
            if (canhittime == 120) {
                canhit = true;

            }

        }
        time++;
        me.setVelx(velx);
        me.setVely(vely);
        me.setXloc(bpx);
        me.setYloc(bpy);
        if (time == 50 * shoottimer) {
            hand.shoot();
            shoottimer++;
            System.out.println("bruh");
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
            percent = 0;
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

        if (box.getY() > gc.getHeight() || box.getX() > gc.getWidth() || box.getX() < 0) {
            bpy = 50;
            percent = 0;
            stock--;
            if(stock == 0) gameover = true;
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
            which++;
            if(which>2) which=0;
        } else {
            if (velx > 0) {
                velx--;
            }
        }
        if (in.isKeyDown(Input.KEY_LEFT)) {
            if (velx > -15) {
                velx--;

            }
            which--;
            if(which<0) which = 2;
        } else {
            if (velx < 0) {
                velx++;
            }
            
        }
        if (in.isKeyPressed(Input.KEY_DOWN)) {
            isFalling = true;
            bpy += 100;
        }

        if (in.isKeyPressed(Input.KEY_A)) {
            //me.shoot();   
            Rectangle Temp = new Rectangle(bpx, bpy, 30, 5);
            if (please.size()<=20) {
                please.add(Temp);
            }
            
        }
        for (int j = 0; j < please.size(); j++) {
                float pos = please.get(j).getX();
                if(pos>1680) please.remove(j);
                else{
                    pos+=10;
                    please.get(j).setX(pos);
                }
               // if(hand.getHitbox().contains(please.get(i))){
                    //hand.dmg();
                  //  health--;
               // }
            }
        if (in.isKeyPressed(Input.KEY_SPACE) && (!isFalling || ju < 2)) {
            bpy -= 10;
            vely = -20;
            isFalling = true;
            bpx+=5;
            ju++;
        }
        //if(in.isKeyPressed(Input.KEY_A)){
        //  hand.shoot();      
        //}

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(Color.blue);
        g.fillRect(0, 0, 1680, 1050);
        g.setColor(Color.red);
        g.draw(box);
        hand.blast(g);
        hand.draw(g);
       // me.draw(g, which);
        for (int i = 0; i < please.size(); i++) {
            g.draw(please.get(i));
        }
        for (int i = 0; i < pls.size(); i++) {
            //g.draw(pls.get(i));
            plimg.draw(pls.get(i).getX(), pls.get(i).getY());
        }
        g.setColor(Color.green);
        g.fill(Hfull);
        g.setColor(Color.red);
        g.fill(hloss);

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
