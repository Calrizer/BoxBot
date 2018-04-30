import city.cs.engine.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * @author Callum Drain
 */

public class MouseHandler extends MouseAdapter {

    private WorldView view;

    /**
     * MouseHandler is used to add a new crate to to world where the player clicks.
     * @param view Add the current instance of the view.
     */

    public MouseHandler(WorldView view) {

        this.view = view;

    }

    /**
     * Add a crate where the player clicks on the screen.
     * @param e event object containing the mouse position.
     */

    @Override
    public void mousePressed(MouseEvent e) {

        Crate crate = new Crate(view.getWorld(), view.viewToWorld(e.getPoint()));
        Random rnd = new Random();
        crate.rotate(rnd.nextInt(4));

    }

}
