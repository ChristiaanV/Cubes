package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.cubes.*;
import com.cubes.test.CubesTestAssets;
import com.cubes.test.blocks.Block_Brick;
import com.cubes.test.blocks.Block_Grass;
import com.cubes.test.blocks.Block_Stone;
import com.cubes.test.blocks.Block_Water;
import com.cubes.test.blocks.Block_Wood;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public Main(){
        settings = new AppSettings(true);
        settings.setWidth(1280);
        settings.setHeight(720);
	settings.setFullscreen(true);
        settings.setTitle("Cubes Demo - Tutorial");
    }
 
    @Override
    public void simpleInitApp(){
        CubesTestAssets.registerBlocks();

        //This is your terrain, it contains the whole
        //block world and offers methods to modify it
        BlockTerrainControl blockTerrain = new BlockTerrainControl(CubesTestAssets.getSettings(this), new Vector3Int(2, 1, 2));
 
        //To set a block, just specify the location and the block object
        //(Existing blocks will be replaced)
        blockTerrain.setBlock(new Vector3Int(0, 6, 1), Block_Brick.class);
        blockTerrain.setBlock(new Vector3Int(1, 6, 0), Block_Water.class);
        blockTerrain.setBlock(new Vector3Int(1, 6, 1), Block_Stone.class);
        blockTerrain.setBlock(0, 6, 0, Block_Wood.class); //For the lazy users :P
 
        //You can place whole areas of blocks too: setBlockArea(location, size, block)
        blockTerrain.setBlockArea(new Vector3Int(0,0,0), new Vector3Int(16, 3, 16), Block_Grass.class);
        blockTerrain.setBlockArea(new Vector3Int(-16,0,-16), new Vector3Int(0, 3, 0), Block_Wood.class);
 
        //Removing a block works in a similar way
        blockTerrain.removeBlock(new Vector3Int(1, 2, 1));
 
        //The terrain is a jME-Control, you can add it
        //to a node of the scenegraph to display it
        Node terrainNode = new Node();
        terrainNode.addControl(blockTerrain);
        rootNode.attachChild(terrainNode);

 
        cam.setLocation(new Vector3f(-10, 10, 16));
        cam.lookAtDirection(new Vector3f(1, -0.56f, -1), Vector3f.UNIT_Y);
        flyCam.setMoveSpeed(50);
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
