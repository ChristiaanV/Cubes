package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import jme3tools.converters.ImageToAwt;
 
/**
 * This demo shows a terrain with collision detection, 
 * that you can walk around in with a first-person perspective.
 * This code combines HelloCollision and HelloTerrain.
 */
public class TerrainAndCollisions extends SimpleApplication
        implements ActionListener {
 
  private BulletAppState bulletAppState;
  private RigidBodyControl landscape;
  private CharacterControl player;
  private Vector3f walkDirection = new Vector3f();
  private boolean left = false, right = false, up = false, down = false;
  private TerrainQuad terrain;
  private Material mat_terrain;
 
  public static void main(String[] args) {
      AppSettings settings = new AppSettings(true);
      settings.setFrequency(60);
      settings.setFrameRate(60);
      settings.setVSync(true);
    TerrainAndCollisions app = new TerrainAndCollisions();
    app.setSettings(settings);
    app.start();
    
  }
 
  @Override
  public void simpleInitApp() {
    //create sky
    viewPort.setBackgroundColor(ColorRGBA.Cyan.multLocal(2));
    /** Set up Physics */
    bulletAppState = new BulletAppState();
    stateManager.attach(bulletAppState);
    bulletAppState.getPhysicsSpace().enableDebug(assetManager);
 
    flyCam.setMoveSpeed(100);
    setUpKeys();
 
    /** 1. Create terrain material and load four textures into it. */
    mat_terrain = new Material(assetManager, 
            "Common/MatDefs/Terrain/Terrain.j3md");
 
    /** 1.1) Add ALPHA map (for red-blue-green coded splat textures) */
    mat_terrain.setTexture("Alpha", assetManager.loadTexture(
            "Textures/Terrain/splat/alphamap.png"));
 
    /** 1.2) Add GRASS texture into the red layer (Tex1). */
    Texture grass = assetManager.loadTexture(
            "Textures/Terrain/splat/grass.jpg");
    grass.setWrap(WrapMode.Repeat);
    mat_terrain.setTexture("Tex1", grass);
    mat_terrain.setFloat("Tex1Scale", 64f);
 
    /** 1.3) Add DIRT texture into the green layer (Tex2) */
    Texture dirt = assetManager.loadTexture(
            "Textures/Terrain/splat/dirt.jpg");
    dirt.setWrap(WrapMode.Repeat);
    mat_terrain.setTexture("Tex2", dirt);
    mat_terrain.setFloat("Tex2Scale", 32f);
 
    /** 1.4) Add ROAD texture into the blue layer (Tex3) */
    Texture rock = assetManager.loadTexture(
            "Textures/Terrain/splat/road.jpg");
    rock.setWrap(WrapMode.Repeat);
    mat_terrain.setTexture("Tex3", rock);
    mat_terrain.setFloat("Tex3Scale", 128f);
 
    /** 2. Create the height map */
    AbstractHeightMap heightmap = null;
    Texture heightMapImage = assetManager.loadTexture(
            "Textures/Terrain/splat/mountains512.png");
    heightmap = new ImageBasedHeightMap(heightMapImage.getImage());
    heightmap.load();
 
    /** 3. We have prepared material and heightmap. 
     * Now we create the actual terrain:
     * 3.1) Create a TerrainQuad and name it "my terrain".
     * 3.2) A good value for terrain tiles is 64x64 -- so we supply 64+1=65.
     * 3.3) We prepared a heightmap of size 512x512 -- so we supply 512+1=513.
     * 3.4) As LOD step scale we supply Vector3f(1,1,1).
     * 3.5) We supply the prepared heightmap itself.
     */
    terrain = new TerrainQuad("my terrain", 65, 513, heightmap.getHeightMap());
 
    /** 4. We give the terrain its material, position & scale it, and attach it. */
    terrain.setMaterial(mat_terrain);
    terrain.setLocalTranslation(0, -100, 0);
    terrain.setLocalScale(2f, 1f, 2f);
    rootNode.attachChild(terrain);
 
    /** 5. The LOD (level of detail) depends on were the camera is: */
    List<Camera> cameras = new ArrayList<Camera>();
    cameras.add(getCamera());
    TerrainLodControl control = new TerrainLodControl(terrain, cameras);
    terrain.addControl(control);
 
    /** 6. Add physics: */ 
    // We set up collision detection for the scene by creating a static 
    //    RigidBodyControl with mass zero.
    terrain.addControl(new RigidBodyControl(0));
 
    // We set up collision detection for the player by creating
    // a capsule collision shape and a CharacterControl.
    // The CharacterControl offers extra settings for
    // size, stepheight, jumping, falling, and gravity.
    // We also put the player in its starting position.
    CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
    player = new CharacterControl(capsuleShape, 0.05f);
    player.setJumpSpeed(20);
    player.setFallSpeed(30);
    player.setGravity(30);
    player.setPhysicsLocation(new Vector3f(-10, 10, 10));
    // We attach the scene and the player to the rootnode and the physics space,
    // to make them appear in the game world.
    bulletAppState.getPhysicsSpace().add(terrain);
    bulletAppState.getPhysicsSpace().add(player);
 
  }
  /** We over-write some navigational key mappings here, so we can
   * add physics-controlled walking and jumping: */
  private void setUpKeys() {
    inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
    inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
    inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
    inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
    inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
    inputManager.addListener(this, "Left");
    inputManager.addListener(this, "Right");
    inputManager.addListener(this, "Up");
    inputManager.addListener(this, "Down");
    inputManager.addListener(this, "Jump");
  }
 
  /** These are our custom actions triggered by key presses.
   * We do not walk yet, we just keep track of the direction the user pressed. */
  public void onAction(String binding, boolean value, float tpf) {
    if (binding.equals("Left")) {
      if (value) { left = true; } else { left = false; }
    } else if (binding.equals("Right")) {
      if (value) { right = true; } else { right = false; }
    } else if (binding.equals("Up")) {
      if (value) { up = true; } else { up = false; }
    } else if (binding.equals("Down")) {
      if (value) { down = true; } else { down = false; }
    } else if (binding.equals("Jump")) {
      player.jump();
    }
  }
 
  /**
   * This is the main event loop--walking happens here.
   * We check in which direction the player is walking by interpreting
   * the camera direction forward (camDir) and to the side (camLeft).
   * The setWalkDirection() command is what lets a physics-controlled player walk.
   * We also make sure here that the camera moves with player.
   */
  Vector3f camForwardDir = new Vector3f();
  Vector3f camForwardHorizontalDir = new Vector3f();
  Vector3f camLeftDir = new Vector3f();
  Vector3f camLeftHorizontalDir = new Vector3f();
  
  @Override
  public void simpleUpdate(float tpf) {
    camForwardDir.set(cam.getDirection());
    camForwardHorizontalDir.set(camForwardDir.getX(), 0, camForwardDir.getZ()).normalizeLocal().multLocal(0.6f);
    camLeftDir.set(cam.getLeft().clone());
    camLeftHorizontalDir.set(camLeftDir.getX(), 0, camLeftDir.getZ()).normalizeLocal().multLocal(0.3f);
    walkDirection.set(0, 0, 0);
    if (up){ 
	walkDirection.addLocal(camForwardHorizontalDir); 
    }
    if (down){ 
	walkDirection.addLocal(camForwardHorizontalDir.negate()); 
    }
    if (left){ 
	walkDirection.addLocal(camLeftHorizontalDir); 
    }
    if (right){ 
	walkDirection.addLocal(camLeftHorizontalDir.negate()); 
    }
    player.setWalkDirection(walkDirection);
    cam.setLocation(player.getPhysicsLocation());
  }
}