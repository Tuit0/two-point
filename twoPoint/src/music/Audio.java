package music;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {
    public static void main(String[] args) {
        File Clip = new File("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\music.wav");
        PlayMusic(Clip);
    }
    public static void PlayMusic(File Sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength()/1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
