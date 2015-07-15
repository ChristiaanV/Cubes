package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 * test
 * @author normenhansen
 */
public class HelloNode extends SimpleApplication {

    public static void main(String[] args) {
        HelloNode app = new HelloNode();
        app.start();
    }

    @Override
    public void simpleInitApp() {	
	Box blueBox = new Box(1,1,1);
	Geometry blueGeom = new Geometry("Blue box",blueBox);
	blueGeom.setLocalTranslation(1, -1, 1);
	Material blueMat = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
	blueMat.setColor("Color", ColorRGBA.Blue);
	blueGeom.setMaterial(blueMat);
	
	Box redBox = new Box(1,1,1);
	Geometry redGeom = new Geometry("Red box", redBox);
	redGeom.setLocalTranslation(1, 3, 1);
	Material redMat = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
	redMat.setColor("Color", ColorRGBA.Red);
	redGeom.setMaterial(redMat);
	
	Node pivot = new Node("Pivot");
	pivot.attachChild(blueGeom);
	pivot.attachChild(redGeom);
	
	pivot.rotate(.4f,.4f,0f);
	
	rootNode.attachChild(pivot);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
