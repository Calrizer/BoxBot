
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

/**
 * @author Callum Drain
 */

public class MainView extends UserView{

    private Image background;

    /**
     * Constructor for the MainView.
     * @param world The current instance of the World to render a view onto it.
     * @param width The width of the view to be rendered.
     * @param height The height of the view to be rendered.
     * @param level The current level to render correct background.
     */

    public MainView(World world, int width, int height, int level) {

        super(world, width, height);

        switch (level){

            case 1:
                this.background = new ImageIcon("assets/env/background1.jpg").getImage();
                break;
            case 2:
                this.background = new ImageIcon("assets/env/background2.jpg").getImage();
                break;
            case 3:
                this.background = new ImageIcon("assets/env/background3.jpg").getImage();
                break;
        }

    }

    /**
     * Renders the background image.
     * @param g Graphics context allowing background image to be rendered.
     */

    @Override
    protected void paintBackground(Graphics2D g){

        g.drawImage(background, 0, 0, this);

    }

}
