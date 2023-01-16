import java.awt.event.*;
public class KeyHandler implements KeyListener 
{



    public KeyHandler()
    {
        System.out.println("KeyHandler created");
    }




    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());


    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
