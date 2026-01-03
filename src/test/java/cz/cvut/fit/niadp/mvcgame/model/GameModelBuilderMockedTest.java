package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.builder.ModelBuilderRandomPositions;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class GameModelBuilderMockedTest {

    @Mocked
    private Random randomMock;

    @Test
    public void builderCollisionDetectionTest() {
        expectedSequence();

        ModelBuilderRandomPositions builder = new ModelBuilderRandomPositions(randomMock);

        //Tries to create two enemies on 100 100, resulting in collision but builder finds new position
        builder.createNormalEnemies(2);
        GameModel model =  builder.getProduct();

        Assert.assertEquals(2, model.getEnemies().size());
        //check if existing enemies collide
        Assert.assertTrue( !model.getEnemies().get(0).getCollider().collided( model.getEnemies().get(1).getCollider() ) );
    }

    private void expectedSequence() {
        new Expectations() {{
            randomMock.nextInt(anyInt);
            returns(
                    100, 100,
                    100, 100,
                    500, 500
            );
        }};
    }
}
