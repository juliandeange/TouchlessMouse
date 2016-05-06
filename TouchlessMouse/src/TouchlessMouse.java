import com.leapmotion.leap.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by juliandeangelis on 2016-04-29.
 */
public class TouchlessMouse {
    public static void main (String [] args) {

        CustomListener listener = new CustomListener();
        Controller c = new Controller();
        c.addListener(listener);

        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setVisible(true);

        try {
            System.in.read();
        } catch (Exception e) { }

        c.removeListener(listener);

    }
}
