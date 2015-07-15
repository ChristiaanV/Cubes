package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.cinematic.MotionPath;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

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
        MotionPath path = new MotionPath();
	path.addWayPoint(Vector3f.ZERO);
	path.addWayPoint(new Vector3f(5, 0, 0));
	path.addWayPoint(new Vector3f(0, 5, 0));
	path.addWayPoint(new Vector3f(0, 0, 5));
	path.removeWayPoint(Vector3f.ZERO);
	path.setCycle(true);
	path.setCurveTension(1);
	path.enableDebugShape(assetManager, rootNode);
    }

    @Override
    public void simpleUpdate(float tpf) {
	
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
