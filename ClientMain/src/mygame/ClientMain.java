package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.Network;
import com.jme3.network.serializing.Serializer;
import com.jme3.renderer.RenderManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * test
 * @author normenhansen
 */
public class ClientMain extends SimpleApplication {

    public static void main(String[] args) {
        ClientMain app = new ClientMain();
        app.start();
    }

    private Client myClient;
    private Message message;
    @Override
    public void simpleInitApp() {
	try {
	    myClient = Network.connectToServer("localhost", 1234);
	    myClient.start();
	    System.out.println("The client is active.");
	    Serializer.registerClass(MessageText.class);
	} catch (IOException ex) {
	    Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
	    this.destroy();
	}
	message = new MessageText("Started client!!!!!");
	myClient.send(message);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    @Override
    public void destroy(){
	myClient.close();
	super.destroy();
	
    }
}
