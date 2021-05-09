package uz.pdp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Game extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;

    private int playPos1 = 210;
    private int playPos2 = 240;
    private int yellow = 223;

    private Timer timer;

    private Color ovalColor =  new Color(255,114,32);
    private Color background =  new Color(255,255,200);

    private Color[] color;
    private boolean live = true;
    private double dir = 1;
    private int[] gamerY;

    private boolean second;
    private int stringLocation = 200;
    private int stringDir = 1;


    private double[] endPosX = new double[16];
    private double[] endPosY = new double[16];
    private double[] endDirX ={0,0.5,0.705,0.865,1,-0.865,-0.705,-0.5,0,-0.5,-0.705,-0.865,-1,0.865,0.705,0.5};
    private double[] endDirY = {-1,0.865,0.705,0.5,0,-0.5,-0.705,-0.865,1,0.865,0.705,0.5,0,-0.5,-0.705,-0.5};

    private String[] args;
    public Game(boolean first) {
        timer = new Timer((!first)?40:8,this);
        timer.start();

        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);

        color = new Color[300];
        gamerY = new int[300];
        for (int i = 0; i < color.length; i++) {
            color[i] = (int)(Math.random()*2) == 0 ? Color.BLACK : ovalColor;
            gamerY[i] = 5;
        }

        second = first;
    }

    @Override
    public void paint(Graphics g) {



        //Background
        g.setColor(background);
        g.fillRect(0,0,500,600);

        //If the first time
        if(!second) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("JokerMan",Font.BOLD,50));
            g.drawString("PRES",150, stringLocation);
            g.drawString("AN",180, stringLocation +50);


            g.setColor(ovalColor);
            g.drawString("S",285, stringLocation);
            g.drawString("D",270, stringLocation +50);
            g.drawString("HOLD",260, stringLocation +100);

        }
        //Players
        g.setColor(Color.BLACK);
        g.fillOval(playPos1,410,30,30);
        g.fillOval(playPos2,410,30,30);

        g.setColor(ovalColor);
        g.fillOval(yellow,500,30,30);

        // Balls
        for (int i = 0; i < color.length; i++) {
            g.setColor(color[i]);
            g.fillOval(227,gamerY[i],24,24);
        }


        //Score Panel
        g.setColor(background);
        g.fillRect(0,0,500,50);

        //Score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",Font.BOLD,25));
        g.drawString(String.valueOf(score),228,30);

        //The end
        if(!live) {
            for (int i = 0; i < 16; i++) {
                if(playPos1==1000) {
                    g.setColor(Color.BLACK);
                    g.fillOval((int)endPosX[i],(int)endPosY[i],10,10);
                    g.fillOval((int)endPosX[i]+40,(int)endPosY[i],10,10);
                } else {
                    g.setColor(ovalColor);
                    g.fillOval((int)endPosX[i],(int)endPosY[i],10,10);
                }
            }
            if(endPosY[0] < 10) {
                Menu.SetVisible();
                Main.sound.clip.close();
                Main.main(args);
            }
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();

        int playDir1 = -1;
        int playDir2 = 1;
        if(play && playPos1 > 190) {
            playPos1 += playDir1;
            playPos2 += playDir2;
        } else if(!play && playPos1 < 210) {
            playPos1 -= playDir1;
            playPos2 -= playDir2;
        }

        for (int i = 0; i < color.length; i++) {
            if(new Rectangle(230,gamerY[i],20,20).intersects(new Rectangle(playPos1,411,25,25))) {
                if(color[i].equals(Color.BLACK)) {
                    gamerY[i] = gamerY[i]+200;
                    score++;
                    new Sound("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\black.wav",false);
                } else {
                    if(live) new Sound("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\end.wav",false);
                    live = false;


                    playPos1=1000;
                    playPos2=1000;

                    if(Integer.parseInt(File.Read())<score)
                    File.Write(String.valueOf(score));
                    args = new String[]{File.Read(),String.valueOf(score)};//Read


                    Arrays.fill(endPosX,  210);
                    Arrays.fill(endPosY, 410);

                }
            } else if(new Rectangle(230,gamerY[i],20,20).intersects(new Rectangle(yellow,500,25,25))) {
                if(color[i].equals(Color.BLACK)) {
                    if(live) new Sound("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\end.wav",false);
                    live = false;


                    yellow = 1000;

                    if(Integer.parseInt(File.Read())<score)
                        File.Write(String.valueOf(score));
                    args =new String[]{File.Read(),String.valueOf(score)};//Read

                    Arrays.fill(endPosX, 235);
                    Arrays.fill(endPosY, 515);
                } else {
                    gamerY[i] = gamerY[i]+200;
                    score++;
                    new Sound("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\yellow.wav",false);
                }
            }
        }
        if(live && second) {
            for (int i = 0; i < gamerY.length; i++) {
                if(i>0) {
                    if(gamerY[i-1] > 50 + i) {
                        gamerY[i] += (int)dir;
                    }
                } else {
                    gamerY[i] += (int)dir;
                }
                if(score % 15 == 14) {
                    dir += 0.00004;
                }
            }
        }

        if(!second) {
            if(stringDir > 0) {
                stringLocation += stringDir;
                if(stringLocation == 230) stringDir = -1;
            }  else {
                stringLocation += stringDir;
                if(stringLocation == 170) stringDir = 1;
            }
        }

        if(!live) {
            for (int i = 0; i < 16; i++) {
                endPosX[i] += endDirX[i];
                endPosY[i] += endDirY[i];
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(live) play = true;
        if(!second) timer = new Timer(8,this);
        second=true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER && live) {
            play = false;
        }
    }
}
