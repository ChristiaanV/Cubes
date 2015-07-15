package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.math.Vector2f;
import com.jme3.renderer.RenderManager;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Screen;
	
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
	app.start();
    }

public int winCount = 0;
private Screen screen;
 
public final void createNewWindow(String someWindowTitle) {
    Window nWin = new Window(
        screen,"Window" + winCount,
        new Vector2f( (screen.getWidth()/2)-175, (screen.getHeight()/2)-100 )
    );
    nWin.setWindowTitle(someWindowTitle);
    screen.addElement(nWin);
    winCount++;
}
 
public void simpleInitApp() {
    flyCam.setDragToRotate(true);
    screen = new Screen(this /*,"tonegod/gui/style/def/style_map.xml"*/);
    screen.initialize();
    guiNode.addControl(screen);
 
    // Add window
    Window win = new Window(screen, "win", new Vector2f(15, 15));
 
    // create button and add to window
    ButtonAdapter makeWindow = new ButtonAdapter( screen, "Btn1", new Vector2f(15, 55),new Vector2f(150,50) ) {
        @Override
        public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
            createNewWindow("New Window " + winCount);
        }
    };
    makeWindow.setText("Make a window");
 
    // Add it to our initial window
    win.addChild(makeWindow);
 
    // Add window to the screen
   screen.addElement(win);
    inputManager.setCursorVisible(true);
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
