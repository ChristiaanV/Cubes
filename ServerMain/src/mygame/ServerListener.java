/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.network.Client;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

/**
 *
 * @author Christiaan
 */
public class ServerListener implements MessageListener<HostedConnection>{

    public void messageReceived(HostedConnection source, Message m) {
	if(m instanceof MessageText){
	    MessageText message = (MessageText) m;
	    System.out.println(message.ToString());
	}
   }   
}
