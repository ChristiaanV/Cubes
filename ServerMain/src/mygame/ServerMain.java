package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.renderer.RenderManager;
import com.jme3.system.JmeContext;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * test
 * @author normenhansen
 */
public class ServerMain extends SimpleApplication implements ConnectionListener{

    public static void main(String[] args) {
        ServerMain app = new ServerMain();
        app.start(JmeContext.Type.Headless);
    }

    @Override
    public void simpleInitApp() {
	Server myServer;
	try {
	    myServer = Network.createServer(1234);
	    myServer.start();
	    myServer.addConnectionListener(this);
	    Serializer.registerClass(MessageText.class);
	    myServer.addMessageListener(new ServerListener(),MessageText.class);
	} catch (IOException ex) {
	    Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    public void connectionAdded(Server server, HostedConnection conn) {
	System.out.println("Connection added to server.");
    }

    public void connectionRemoved(Server server, HostedConnection conn) {
	System.out.println("Connection removed.");
    }
}
