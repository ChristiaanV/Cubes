package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.cubes.*;

public class CubeTest extends SimpleApplication {

    public static void main(String[] args) {
        CubeTest app = new CubeTest();
        app.start();
    }

    @Override
    public void simpleInitApp() {
	CubesSettings cs = new CubesSettings(this);
	cs.setBlockSize(1);
	cs.setChunkSizeX(16);
	cs.setChunkSizeY(256);
	cs.setChunkSizeZ(16);
	cs.setBlockMaterial(new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md"));
       
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