
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * @author Callum Drain
 */

public class WorldRenderer {

    private World world;

    /**
     * WorldRenderer is used to render the environment of the game world.
     * @param world Add the current instance of the world that the environment should be rendered onto.
     */

    public WorldRenderer(World world){

        this.world = world;
        this.render();

    }

    /**
     * This is used to render the environment and the bounding walls of the game.
     */

    public void render(){

        Environment grass = new Environment(this.world, new Vec2(-26, -10), new Vec2(52, 0.9f), EnvTexture.GRASSLAYER);
        grass.setName("grass");

        Environment stone = new Environment(this.world, new Vec2(-26, -12), new Vec2(52, 0.9f), EnvTexture.STONE);

        for (int y = -10; y <= 13; y++){
            Environment wall = new Environment(this.world, new Vec2(-26, y), new Vec2(0.9f, 0.9f), EnvTexture.SHADOW100);
            wall.setName("wall");
        }

        for (int y = -10; y <= 13; y++){
            Environment wall = new Environment(this.world, new Vec2(26, y), new Vec2(0.9f, 0.9f), EnvTexture.SHADOW100);
        }

        for (int x = -26; x <= 26; x++){
            Environment wall = new Environment(this.world, new Vec2(x, 13.5f), new Vec2(0.9f, 0.9f), EnvTexture.SHADOW100);
        }

    }

}
