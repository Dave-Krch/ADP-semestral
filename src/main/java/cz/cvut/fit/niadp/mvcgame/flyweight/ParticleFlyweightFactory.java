package cz.cvut.fit.niadp.mvcgame.flyweight;

import java.util.HashMap;
import java.util.Map;

public class ParticleFlyweightFactory {
    private static final Map<String, ParticleType> cache = new HashMap<>();

    public static ParticleType getParticleType(String name, String resource, int speed, int decay) {
        if (!cache.containsKey(name)) {
            cache.put(name, new ParticleType(resource, speed, decay));
        }
        return cache.get(name);
    }
}