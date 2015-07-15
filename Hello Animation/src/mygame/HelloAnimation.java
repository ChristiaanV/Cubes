package mygame;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class HelloAnimation extends SimpleApplication implements AnimEventListener{

    public static void main(String[] args){
	HelloAnimation app = new HelloAnimation();
	app.start();    
    }
    
    Node playerNode;
    AnimChannel playerFullBodyChannel;    
    AnimControl playerControl;
    
    @Override
    public void simpleInitApp() {
	viewPort.setBackgroundColor(ColorRGBA.LightGray);
	DirectionalLight dl = new DirectionalLight();
	dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
	dl.setColor(ColorRGBA.White);
	rootNode.addLight(dl);
	
	initKeys();
	
	playerNode = (Node) assetManager.loadModel("Models/Jaime/Jaime.j3o");
	rootNode.attachChild(playerNode);
	
	playerControl = playerNode.getControl(AnimControl.class);
	playerControl.addListener(this);
	playerFullBodyChannel.setAnim("Idle",0.5f);
	playerFullBodyChannel.setLoopMode(LoopMode.Loop);
	
	
	
    }
    
    private void initKeys(){
	inputManager.addMapping("Run", new KeyTrigger(keyInput.KEY_SPACE));
	inputManager.addListener(actionListener, "Run");
    }
    
    ActionListener actionListener = new ActionListener() {
	public void onAction(String name, boolean isPressed, float tpf) {
	    if (name.equals("Run") && !isPressed && !playerFullBodyChannel.getAnimationName().equals("Walk")){
		playerFullBodyChannel.setAnim("Walk",0.5f);
		System.out.println("Walking");
	    }
	}
    };
 

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
	playerFullBodyChannel.setAnim("Idle",0.5f);
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
    }
 

  }
  
	  