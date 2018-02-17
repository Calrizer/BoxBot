
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Crate extends DynamicBody{

    public Crate(World world, Vec2 position) {

        super(world, new BoxShape(1,1));
        this.addImage(new BodyImage("assets/env/crate.png", 2));
        this.setPosition(position);

        this.setName("Crate");

    }

}