package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
 
/** Sample 11 - how to create fire, water, and explosion effects. */
public class HelloEffects extends SimpleApplication {
 
  public static void main(String[] args) {
    HelloEffects app = new HelloEffects();
    app.start();
  }
 
  @Override
  public void simpleInitApp() {
      
     viewPort.setBackgroundColor(ColorRGBA.DarkGray);
 
    ParticleEmitter fire =new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 30);
    Material mat_red = new Material(assetManager,"Common/MatDefs/Misc/Particle.j3md");
    mat_red.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flame.png"));
    fire.setMaterial(mat_red);
    fire.setImagesX(2); 
    fire.setImagesY(2); // 2x2 texture animation
    fire.setEndColor(ColorRGBA.Cyan);   // red
    fire.setStartColor(ColorRGBA.Blue); // yellow
    fire.getParticleInfluencer().setInitialVelocity(new Vector3f(4, -2, 0));
    fire.setStartSize(0.5f);
    fire.setNumParticles(200);
    fire.setParticlesPerSec(5);
    fire.setEndSize(10f);
    fire.setGravity(0, 9.8f, 0);
    fire.setLowLife(7f);
    fire.setHighLife(7f);
    fire.getParticleInfluencer().setVelocityVariation(0.1f);
    rootNode.attachChild(fire);
 
    ParticleEmitter debris =new ParticleEmitter("Debris", ParticleMesh.Type.Triangle, 10);
    Material debris_mat = new Material(assetManager,"Common/MatDefs/Misc/Particle.j3md");
    debris_mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/Debris.png"));
    debris.setMaterial(debris_mat);
    debris.setImagesX(3); 
    debris.setImagesY(3); // 3x3 texture animation
    debris.setRotateSpeed(10);
    debris.setStartSize(0.2f);
    debris.setSelectRandomImage(true);
    debris.getParticleInfluencer().setInitialVelocity(new Vector3f(4, -4, 0));
    debris.setNumParticles(100);
    debris.setStartColor(ColorRGBA.White);
    debris.setGravity(0, 9.8f, 0);
    debris.getParticleInfluencer().setVelocityVariation(3);
    rootNode.attachChild(debris);
    debris.setParticlesPerSec(10);
  }
}