import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class UFO extends Walker implements CollisionListener, StepListener{

    private InterfaceRenderer interfaceRenderer;

    private int health = 50;
    private int time = 20;

    public UFO(InterfaceRenderer interfaceRenderer, World world, Vec2 position) {

        super(world, new BoxShape(0.75f,0.75f));

        this.interfaceRenderer = interfaceRenderer;

        this.addImage(new BodyImage("assets/ufo/ufo.png", 1.5f));
        this.setPosition(position);

        this.setName("Ufo");
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

            if (this.health <= 30){

                if (this.health <= 10){

                    this.removeAllImages();
                    this.addImage(new BodyImage("assets/skull/red.png", 1.5f));

                }else{

                    this.removeAllImages();
                    this.addImage(new BodyImage("assets/skull/0.png", 1.5f));

                }

            }

        }else if (e.getOtherBody().getName() == "wall"){

            this.destroy();
            interfaceRenderer.render(1,0, false);

        }else if (e.getOtherBody().getName() == "Bot"){

            this.destroy();
            interfaceRenderer.render(1,0, false);

        }

    }

    @Override
    public void preStep(StepEvent e){

        time++;

        if (time % 20 == 0 && this.health > 30){

            this.applyImpulse(new Vec2(-75, 150));

        }

    }

    @Override
    public void postStep(StepEvent e){

        //Just a stub.//

    }


}
