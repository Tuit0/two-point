package uz.pdp;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu extends JPanel implements ActionListener {
    private JButton start;
    private JButton about;

    private JButton sound;
    private JButton night;

    private int startState = 140;
    private int direction = 1;

    private Timer timer;


    private Color back = new Color(255,255,200);
    private Color button = new Color(255,114,32);
    private Color color1 = Color.BLACK;

    private boolean first;
    private String result;

    private Icon iconOn = new ImageIcon("src/uz/pdp/Sound.png");
    private Icon iconOff = new ImageIcon("src/uz/pdp/SoundOff.png\"");

    public Menu(boolean first,String result) {
        this.first = first;
        this.result = result;

        timer = new Timer(38,this);
        timer.start();

        setBackground(back);

        start = new JButton("Start");
        start.setFont(new Font("JokerMan",Font.BOLD,25));
        start.setForeground(back);
        start.setFocusable(false);
        start.setBorderPainted(false);
        start.setBackground(button);
        add(start);

        about = new JButton("About");
        about.setFont(new Font("JokerMan",Font.BOLD,25));
        about.setForeground(back);
        about.setFocusable(false);
        about.setBorderPainted(false);
        about.setBackground(Color.BLACK);
        add(about);

        sound = new JButton(iconOn);
        sound.setFont(new Font("JokerMan",Font.BOLD,10));
        sound.setForeground(back);
        sound.setFocusable(false);
        sound.setBorderPainted(false);
        sound.setBackground(Color.BLACK);
        add(sound);

        night = new JButton();
        night.setFont(new Font("JokerMan",Font.BOLD,20));
        night.setForeground(back);
        night.setFocusable(false);
        night.setBorderPainted(false);
        night.setBackground(button);
        add(night);

        night.addActionListener(this);
        about.addActionListener(this);
        start.addActionListener(this);
        sound.addActionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);


        g.setColor(Color.BLACK);
        g.setFont(new Font("JokerMan",Font.BOLD,40));
        g.drawString("Two Point",150,100);

        start.setBounds(startState,200,200,80);
        about.setBounds(150,300,200,80);
        sound.setBounds(150, 450, 80, 50);
        night.setBounds(270, 450, 80, 50);

        g.setColor(button);
        g.fillRect(270, 450, 80, 50);
        g.setColor(color1);
        g.fillOval(290, 457, 35, 35);
        g.setColor(button);
        g.fillOval(300, 450, 30, 30);

        if(first && result!=null) {
            g.setColor(Color.BLUE);
            g.setFont(new Font("JokerMan",Font.BOLD,15));
            g.drawString("Result",165,410);
            g.drawString("The best",275,410);

            g.setColor(Color.RED);
            g.setFont(new Font("JokerMan",Font.BOLD,20));
            g.drawString(result,180,439);

            g.setColor(Color.GREEN);
            g.drawString(File.Read(),298,439);
        }
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();

        if(startState >= 140 && direction>0) {
            startState += direction;
            if(startState == 160) direction = -direction;
        } else if(startState <= 160 && direction<0) {
            startState += direction;
            if(startState == 140) direction = -direction;
        }
        if(e.getSource() == night) {
            if(color1 == Color.BLACK) {
                color1 = back;
                setBackground(Color.DARK_GRAY);
            } else {
                color1 = Color.BLACK;
                setBackground(back);
            }
            new Sound("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\tap.wav",false);
        }

        if(e.getSource() == about) {
            new Sound("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\tap.wav",false);
            JOptionPane.showMessageDialog(getParent(),"This program was produced by" +
                    " Og'abek...", "Info",JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(getClass().getResource("Back.png")));

        }

        if(e.getSource() == start) {
            new Sound("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\tap.wav",false);
            Start();
            Main.Visible();
        }

        if(e.getSource() == sound) {
            if(sound.getIcon().equals(iconOn)) {
                Main.sound.clip.stop();
                sound.setIcon(iconOff);
            }
            else {
                Main.sound.clip.start();
                Main.sound.clip.loop(Clip.LOOP_CONTINUOUSLY);
                sound.setIcon(iconOn);
            }
            new Sound("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\tap.wav",false);
        }
    }
    public static JFrame obj;
    public void Start() {
        obj = new JFrame();
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            BufferedImage image = ImageIO.read(Main.class.getResourceAsStream("Back.png"));
            obj.setIconImage(image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Game game = new Game(first);
        obj.add(game);

        obj.setJMenuBar(new JMenuBar());
        obj.setTitle("Two Point");
        obj.setBounds(470,100,500,600);
        obj.setResizable(false);
        obj.setVisible(true);
    }
    public static void SetVisible() {
        obj.setVisible(false);
    }
}
