
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class MainView extends UserView{

    private Image background;

    public MainView(World world, int width, int height) {

        super(world, width, height);
        this.background = new ImageIcon("assets/env/background.jpg").getImage();

    }

    @Override
    protected void paintBackground(Graphics2D g){

        g.drawImage(background, 0, 0, this);

    }

}
