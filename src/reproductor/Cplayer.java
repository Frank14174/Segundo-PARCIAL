/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor;

import java.util.Map;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;


/**
 *
 * @author Franklin
 */
public class Cplayer implements BasicPlayerListener{
    
    BasicPlayer player = new BasicPlayer();
    BasicController control = (BasicController) player; 
    VentanaIn vp;

    public Cplayer(VentanaIn v) {
        player.addBasicPlayerListener(this);
        vp = v;
    }

    @Override
    public void opened(Object o, Map properties) {
        System.out.println("opened : " + properties.toString());
    }

    @Override
    public void progress(int i, long l, byte[] bytes, Map properties) {

    }

    @Override
    public void stateUpdated(BasicPlayerEvent event) {
        
        if (player.getStatus() == BasicPlayer.STOPPED && vp.detenido == false) {
            vp.eventoSiguiente();
        }
    }

    @Override
    public void setController(BasicController controller) {
         
    }
    
}
