import city.cs.engine.Body;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class WorldRenderer {

    private World world;

    public WorldRenderer(World world){

        this.world = world;
        this.render();

    }

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
