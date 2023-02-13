import java.io.File; 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicHandler {

    private Clip clip;
    private float volume;

    public MusicHandler(String soundPath)
    {
        try
        {
            Turtle.successLogs.write("Lecture du fichier audio");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
            this.setVolume(0.1f);
            this.clip.loop(this.clip.LOOP_CONTINUOUSLY);
            this.clip.start(); //start to play the clip
            this.volume = this.getVolume();  
            System.out.println(this.getVolume());
        } 
        catch (Exception e) 
        {
            Turtle.errorLogs.write("Erreur lors de la lecture du fichier audio");
            System.out.println(e.getMessage());
        }
    }

    public void stop()
    {
        this.clip.stop();
    }

    public void start()
    {
        this.clip.start();
    }

    public void mute()
    {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(0f));
    }

    public void unmute()
    {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(this.volume));
    }

    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }
    
    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
        {
            Turtle.errorLogs.write("Volume invalide: " + volume);
            throw new IllegalArgumentException("Volume invalide: " + volume);
        }
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
        this.volume = volume;
    }
}