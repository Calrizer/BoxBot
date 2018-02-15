import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Bot extends Walker {

    public Bot(World world, Vec2 position){

        super(world, new PolygonShape(1.2f,1.2f));
        this.addImage(new BodyImage("assets/bot/4.png", 2));
        this.setPosition(position);

    }

}
