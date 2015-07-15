package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.PQTorus;
import com.jme3.scene.shape.StripBox;
import com.jme3.texture.Texture;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
	flyCam.setMoveSpeed(5);
		
	/** An unshaded textured cube. 
	  *  Uses texture from jme3-test-data library! */ 
	PQTorus b = new PQTorus(3,8, 2f, 1f, 32, 32);
	Geometry boxGeo = new Geometry("A Textured Box", b); 
	Material boxMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); 
	Texture monkeyTex = assetManager.loadTexture("Interface/Logo/Monkey.jpg"); 
	boxMat.setTexture("ColorMap", monkeyTex); 
	boxGeo.setMaterial(boxMat); 
	rootNode.attachChild(boxGeo);
	rootNode.setCullHint(Spatial.CullHint.Never);
	
	/** A white ambient light source. */ 
        AmbientLight ambient = new AmbientLight();
	ambient.setColor(ColorRGBA.White);
	rootNode.addLight(ambient); 
	
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
