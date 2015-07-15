/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author Christiaan
 */

@Serializable
public class MessageText extends AbstractMessage{
    private String sText;
    
    public MessageText(){
	sText = "Blank message";
    }
    
    public MessageText(String Message){
        sText = Message;
    }
    
    public String ToString(){
	return sText;
    };
}