package cz.cvut.fit.niadp.mvcgame.flyweight;

public class ParticleType {
    private final String imageResource;
    private final int speed;
    private final int decayRate;

    public ParticleType(String imageResource, int speed, int decayRate) {
        this.imageResource = imageResource;
        this.speed = speed;
        this.decayRate = decayRate;
    }

    public String getImageResource() {
        return imageResource;
    }

    public int getDecayRate() {
        return decayRate;
    }

}
