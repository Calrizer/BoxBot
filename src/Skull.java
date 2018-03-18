import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Skull extends Walker implements CollisionListener, StepListener{

    private InterfaceRenderer interfaceRenderer;

    private int health = 10;
    private int time;

    public Skull(InterfaceRenderer interfaceRenderer, World world, Vec2 position) {

        super(world, new BoxShape(0.75f,0.75f));

        this.interfaceRenderer = interfaceRenderer;

        this.addImage(new BodyImage("assets/skull/1.png", 1.5f));
        this.setPosition(position);

        this.setName("Skull");
        this.addCollisionListener(this);
        world.addStepListener(this);

        GhostlyFixture fixture = new GhostlyFixture(this, new BoxShape(0.75f,0.75f));
        fixture.setDensity(0.5f);

        this.startWalking(-7);

    }

    public int getHealth() {
        return health;
    }

    public void giveDamage(int damage) {
        this.health = this.health - damage;
        if (this.health <= 0){
            this.destroy();
        }
    }

    @Override
    public void collide(CollisionEvent e){

        if (e.getOtherBody().getName() == "flyingCrate"){
            this.giveDamage(10);
        }else if (e.getOtherBody().getName() == "wall"){
            this.destroy();
            interfaceRenderer.render(1,0, false);
        }

    }

    @Override
    public void preStep(StepEvent e){

        time++;

        if (time % 40 == 0){

            this.applyImpulse(new Vec2(-100, 200));

        }

    }

    @Override
    public void postStep(StepEvent e){

        //Just a stub.//

    }


}
