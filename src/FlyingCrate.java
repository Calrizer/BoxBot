import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class FlyingCrate extends Crate implements CollisionListener, StepListener {

    private float crateRotation = 0;

    public FlyingCrate(World world, Vec2 position) {
        super(world, position);
        this.setName("flyingCrate");
        this.addCollisionListener(this);
        world.addStepListener(this);
    }

    @Override
    public void collide(CollisionEvent e){

        if (e.getOtherBody().getName() == "grass" || e.getOtherBody().getName() == "Blob"){
            this.destroy();
        }

    }

    @Override
    public void preStep(StepEvent e){

        //Just a stub.//

    }

    @Override
    public void postStep(StepEvent e){

        this.rotateDegrees(crateRotation);
        crateRotation = crateRotation + 10;
        if (crateRotation == 360){
            crateRotation = 0;
        }

    }

}
