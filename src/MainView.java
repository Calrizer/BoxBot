
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class MainView extends UserView{

    private Image background;

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

    @Override
    protected void paintBackground(Graphics2D g){

        g.drawImage(background, 0, 0, this);

    }

}
