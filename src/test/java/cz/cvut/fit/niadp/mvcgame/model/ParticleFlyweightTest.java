package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.flyweight.ParticleFlyweightFactory;
import cz.cvut.fit.niadp.mvcgame.flyweight.ParticleType;
import org.junit.Assert;
import org.junit.Test;

public class ParticleFlyweightTest {
    @Test
    public void flyweightCachingTest() {
        ParticleType fire1 = ParticleFlyweightFactory.getParticleType("fire", "fire.png", 10, 10);
        ParticleType fire2 = ParticleFlyweightFactory.getParticleType("fire", "fire.png", 10, 10);

        ParticleType smoke = ParticleFlyweightFactory.getParticleType("smoke", "fire.png", 5, 5);

        Assert.assertSame("Factory should return the cached instance for the same key", fire1, fire2);

        Assert.assertNotSame("Factory should return different instances for different keys", fire1, smoke);
    }
}
