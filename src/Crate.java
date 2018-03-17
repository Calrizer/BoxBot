
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Crate extends DynamicBody{

    private int damageGiven = 10;

    public Crate(World world, Vec2 position) {

        super(world, new BoxShape(0.5f,0.5f));
        this.addImage(new BodyImage("assets/env/crate.png", 1));
        this.setPosition(position);

        this.setName("Crate");

    }

    public int getDamageGiven() {
        return damageGiven;
    }
}