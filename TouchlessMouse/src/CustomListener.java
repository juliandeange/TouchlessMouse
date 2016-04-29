import com.leapmotion.leap.*;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture.Type;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * Created by juliandeangelis on 2016-04-29.
 */
public class CustomListener extends Listener {

    public Robot robot;

    public void onConnect(Controller c) {
        c.enableGesture(Gesture.Type.TYPE_CIRCLE);
        c.enableGesture(Gesture.Type.TYPE_SWIPE);
        c.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
    }

    public void onFrame(Controller c) {
        try { robot = new Robot(); } catch (Exception e) { }
        Frame frame = c.frame();
        InteractionBox box = frame.interactionBox();
        for (Finger f : frame.fingers()) {
            if (f.type() == Finger.Type.TYPE_INDEX) {
                Vector fingerPos = f.stabilizedTipPosition();
                Vector boxFingerPos = box.normalizePoint(fingerPos);
                Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                robot.mouseMove((int)(screen.width * boxFingerPos.getX()), (int)(screen.height - boxFingerPos.getY() * screen.height));
            }
        }
        for (Gesture g : frame.gestures()) {
            if (g.type() == Type.TYPE_SCREEN_TAP) {
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
        }
    }

}
