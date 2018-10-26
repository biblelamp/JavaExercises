import java.util.Scanner;

/**
 * Java. Implementation of LunarFly version 1
 * based on http://slavav.ru/way_to_earth/
 *
 * @author Sergey Iryupin
 * @version 0.2.1 dated Oct 26, 2018
 */
public class LunarFlyOne {

    // flight constants
    float accelOfGravity = 1.62f;   // m/s^2, at Moon surface
    int dryMass = 2150;             // kg, lunarfly and pilot
    int exhaustSpeed = 3660;        // m/s, from the engine
    float accelLimin = 3 * 9.81f;   // 3G, G is earth acceleration of gravity
    int timeCount = -1;
    float startSpeed = 0;
    float startHeight = 0;
    int startfuelMass = 400;
    float startFlightTime = 0;

    // flight variables
    float fuel;
    float duration;
    float speed;
    float height;
    float acceleration;
    float fuelMass;
    float flightTime;

    private String showConstants() {
        return String.format("g = %5.3f M = %d c = %d a = %5.3f m = %d",
            accelOfGravity, dryMass, exhaustSpeed, accelLimin, startfuelMass);
    }

    private String showVariables() {
        return String.format("f = %5.3f t = %5.3f v = %5.3f h = %5.3f a = %5.3f F = %5.3f T = %5.3f",
            fuel, duration, speed, height, acceleration, fuelMass, flightTime);
    }

    private void init() {
        speed = startSpeed;
        height = startHeight;
        fuelMass = startfuelMass;
        flightTime = startFlightTime;
    }

    private float addHeiht(float duration, float speed, float acceleration) {
        float averageSpeed = (speed + speed + duration * (acceleration - accelOfGravity)) / 2;
        return duration * averageSpeed;
    }

    private boolean isAlmostLanded(float height) {
        return Math.abs(height) < 0.00001;
    }

    private void simulate(float reverse, float fuel, float duration) {
        if (duration > 0) {
            if (fuelMass > 0) {

                float consumption = fuel / duration;
                acceleration = reverse * consumption * exhaustSpeed /
                    (dryMass + fuelMass);
                if (height + addHeiht(duration, speed, acceleration) < 0) {
                    float fullAcceleration = acceleration - accelOfGravity;
                    double d = Math.pow(speed, 2) - 2 * fullAcceleration * height;
                    double t1 = (-speed - Math.sqrt(d)) / fullAcceleration;
                    double t2 = (-speed + Math.sqrt(d)) / fullAcceleration;
                    if (t1 > 0) {
                        duration = (float) t1;
                    }
                    if (t2 > 0 && t2 < duration) {
                        duration = (float) t2;
                    }
                    this.duration = duration;
                    fuel = consumption * duration;
                    this.fuel = fuel;
                    acceleration = reverse * consumption * exhaustSpeed /
                        (dryMass + fuelMass);
                }
                height += addHeiht(duration, speed, acceleration);
                speed += duration * (acceleration - accelOfGravity);
                fuelMass -= fuel;
                flightTime -= duration * timeCount;

                System.out.println(showVariables());
                if (isAlmostLanded(height)) {
                    System.out.println("Landing (" + height + ")");
                    speed = 0;
                    height = 0;
                    flightTime = 0;
                }
            }
        }
    }

    private void command() {
        Scanner sc = new Scanner(System.in);
        System.out.println(showConstants());
        init();
        while (true) {
            fuel = sc.nextInt();
            duration = sc.nextFloat();
            simulate(Math.signum(fuel), Math.abs(fuel), duration);
        }
    }

    public static void main(String[] args) {
        new LunarFlyOne().command();
    }
}
