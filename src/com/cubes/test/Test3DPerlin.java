/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes.test;

import Main.Noise.OpenSimplexNoise;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.cubes.*;
import com.jme3.renderer.queue.RenderQueue;
import java.util.Random;

public class Test3DPerlin extends SimpleApplication{
    private float evalSize = 40;
    private int xChunks =10;
    private int yChunks =10;
    private int zChunks =10;
    private int counter = 0;
    public static void main(String[] args){
        Logger.getLogger("").setLevel(Level.SEVERE);
        Test3DPerlin app = new Test3DPerlin();
        app.start();
    }

    public Test3DPerlin(){
        settings = new AppSettings(true);
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("3D perlin");
    }

    @Override
    public void simpleInitApp(){
	
	long startTime = System.nanoTime();
        CubesTestAssets.registerBlocks();
	CubesTestAssets.initializeEnvironment(this);
	CubesSettings settingss = new CubesSettings(this);
	settingss.setDefaultBlockMaterial("Textures/cubes/terrain.png");
	settingss.setChunkSizeX(16);
	settingss.setChunkSizeY(16);
	settingss.setChunkSizeZ(16);
	BlockTerrainControl blockTerrain = new BlockTerrainControl(settingss, new Vector3Int(xChunks, yChunks,zChunks));
	CubesTestAssets.initializeWater(this);
	
	
	OpenSimplexNoise noise = new OpenSimplexNoise(new Random().nextLong());
	
	for(int x =0; x<blockTerrain.getSettings().getChunkSizeX()*xChunks;x++){
	    for(int y=0;y<blockTerrain.getSettings().getChunkSizeY()*yChunks;y++){
		for(int z=0;z<blockTerrain.getSettings().getChunkSizeZ()*zChunks;z++){
		    if(noise.eval(x/evalSize, y/evalSize, z/evalSize)>((float)(y*2))/(blockTerrain.getSettings().getChunkSizeY()*yChunks)-1){
			blockTerrain.setBlock(x,y,z, CubesTestAssets.BLOCK_GRASS);
			
		    }counter++;
		}
	    }
	}
	
	
        Node terrainNode = new Node();
        terrainNode.addControl(blockTerrain);
	terrainNode.move(0, -yChunks*blockTerrain.getSettings().getChunkSizeY()/2, 0);
        terrainNode.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        rootNode.attachChild(terrainNode);
        
        cam.setLocation(new Vector3f(-10, 10, 16));
        cam.lookAtDirection(new Vector3f(1, -0.56f, -1), Vector3f.UNIT_Y);
        flyCam.setMoveSpeed(50);
	
	
	long endTime = System.nanoTime();
	System.out.println(endTime - startTime);
    }
}
