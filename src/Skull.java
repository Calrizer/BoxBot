import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Skull extends Walker implements CollisionListener, StepListener{

    private InterfaceRenderer interfaceRenderer;

    private int health = 10;
    private boolean walkStep;

    public Skull(InterfaceRenderer interfaceRenderer, World world, Vec2 position) {

        super(world, new BoxShape(0.75f,0.75f));

        this.interfaceRenderer = interfaceRenderer;

        this.addImage(new BodyImage("assets/skull/1.png", 1.5f));
        this.setPosition(position);

        this.setName("Blob");
        this.addCollisionListener(this);
        world.addStepListener(this);

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

        if (e.getStep() % 60 == 0){

            if (this.walkStep == false){
                this.addImage(new BodyImage("assets/blob/2.png", 1.5f));
                this.walkStep = true;
            }else{
                this.addImage(new BodyImage("assets/blob/1.png", 1.5f));
                this.walkStep = false;
            }

        }

    }

    @Override
    public void postStep(StepEvent e){

        //Just a stub.//

    }


}
