import java.util.Random;

public class Star {

    private final static Random gen = new Random();

    double w;
    double h;

    double x;
    double y;
    double z;
    double speed;

    double pz;

    public Star(double w, double h) {
        this.w = w;
        this.h = h;
        this.x = (2 * gen.nextFloat() * w) - w;
        this.y = (2 * gen.nextFloat() * h) - h;
        this.z = gen.nextFloat() * w;
        pz = z;
    }

    public void update() {
        z -= speed;
        if (z < 1) {
            z = w;
            this.x = (2 * gen.nextFloat() * w) - w;
            this.y = (2 * gen.nextFloat() * h) - h;
            pz = z;
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
