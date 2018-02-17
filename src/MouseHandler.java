import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MouseHandler extends MouseAdapter {

    private WorldView view;

    public MouseHandler(WorldView view) {

        this.view = view;

    }

    /**
     * Create a bowling ball at the current mouse position.
     * @param e event object containing the mouse position
     */
    public void mousePressed(MouseEvent e) {

        Crate crate = new Crate(view.getWorld(), view.viewToWorld(e.getPoint()));
        Random rnd = new Random();
        crate.rotate(rnd.nextInt(4));
        System.out.println(view.viewToWorld(e.getPoint()));

    }

}
