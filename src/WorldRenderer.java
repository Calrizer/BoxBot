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

        for (int x = -26; x <= 26; x++){
            Environment grass = new Environment(this.world, new Vec2(x, -10), EnvTexture.GRASS);
        }

        for (int x = -26; x <= 26; x++){
            Environment stone = new Environment(this.world, new Vec2(x, -12), EnvTexture.STONE);
        }

        for (int y = -10; y <= 13; y++){
            Environment wall = new Environment(this.world, new Vec2(-26, y), EnvTexture.SHADOW100);
        }

        for (int y = -10; y <= 13; y++){
            Environment wall = new Environment(this.world, new Vec2(26, y), EnvTexture.SHADOW100);
        }

        for (int x = -26; x <= 26; x++){
            Environment wall = new Environment(this.world, new Vec2(x, 13.5f), EnvTexture.SHADOW100);
        }

    }

}
