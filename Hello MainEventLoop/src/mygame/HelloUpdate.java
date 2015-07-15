package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.FastMath;

/**
 * test
 * @author normenhansen
 */
public class HelloUpdate extends SimpleApplication {

    public static void main(String[] args) {
        HelloUpdate app = new HelloUpdate();
        app.start();
    }
    Geometry geom;
    float angle = 0;
    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 1);
        geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
    }

    @Override
    public void simpleUpdate(float tpf) {
        geom.rotate(tpf,tpf,tpf);
	geom.setLocalScale(FastMath.sin(angle)*FastMath.sin(angle));
	angle += tpf;
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
