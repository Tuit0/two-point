package uz.pdp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static Sound sound;
    private static JFrame obj;

    public static void main(String[] args) {
        sound = new Sound("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\music.wav", true);

        obj = new JFrame();
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            BufferedImage image = ImageIO.read(Main.class.getResourceAsStream("Back.png"));
            obj.setIconImage(image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Menu menu = new Menu(args.length == 2, (args.length == 2) ? args[1] : null);//Read

        obj.add(menu);

        obj.setJMenuBar(new JMenuBar());
        obj.setTitle("Two Point");
        obj.setBounds(470, 100, 500, 600);
        obj.setResizable(false);
        obj.setVisible(true);

    }

    public static void Visible() {
        obj.setVisible(false);
    }
}
