import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicHandler {

    private Clip backgroundClip,bonusClip,tpClip,wallBreakingClip;
    private float volume;
    private AudioInputStream inputStream;

    public MusicHandler()
    {
        try
        {
            this.inputStream = AudioSystem.getAudioInputStream(new File("res/music.wav").getAbsoluteFile());
            this.backgroundClip = AudioSystem.getClip();
            this.backgroundClip.open(this.inputStream);
            this.setVolume(0.1f);
            this.backgroundClip.loop(this.backgroundClip.LOOP_CONTINUOUSLY);
            this.backgroundClip.start(); //start to play the clip
            this.volume = this.getVolume();  
            System.out.println(this.getVolume());
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }

    public void stop()
    {
        this.backgroundClip.stop();
    }

    public void start()
    {
        this.backgroundClip.start();
    }

    public void mute()
    {
        FloatControl gainControl = (FloatControl) this.backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(0f));
    }

    public void unmute()
    {
        FloatControl gainControl = (FloatControl) this.backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(this.volume));
    }

    public float getVolume() {
        FloatControl gainControl = (FloatControl) this.backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);        
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }
    
    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
        {
            throw new IllegalArgumentException("Volume invalide: " + volume);
        }
        FloatControl gainControl = (FloatControl) this.backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
        this.volume = volume;
    }

    public void playBonusSfx()
    {
        try
        {
            this.inputStream = AudioSystem.getAudioInputStream(new File("res/bonus.wav").getAbsoluteFile());
            this.bonusClip = AudioSystem.getClip();
            this.bonusClip.open(this.inputStream);
            this.bonusClip.start(); //start to play the clip
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void playTeleporterSfx()
    {
        try
        {
            this.inputStream = AudioSystem.getAudioInputStream(new File("res/teleporter.wav").getAbsoluteFile());
            this.tpClip = AudioSystem.getClip();
            this.tpClip.open(this.inputStream);
            this.tpClip.start(); //start to play the clip
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void playWallBreakingSfx()
    {
        try
        {
            this.inputStream = AudioSystem.getAudioInputStream(new File("res/rock-smash.wav").getAbsoluteFile());
            this.wallBreakingClip = AudioSystem.getClip();
            this.wallBreakingClip.open(this.inputStream);
            this.wallBreakingClip.start(); //start to play the clip
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}