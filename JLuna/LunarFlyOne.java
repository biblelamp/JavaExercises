import java.util.Scanner;

/**
 * Java. Lunar ship simple simulator v1
 * based on http://epizodyspace.ru/bibl/tm/1986/5/put.html
 *
 * @author Sergey Iryupin
 * @version 0.2.3 dated Oct 28, 2018
 */
public class LunarFlyOne {

    // flight constants
    float accelOfGravity = 1.62f;   // m/s^2, at Moon surface
    int dryMass = 2150;             // kg, lunarfly and pilot
    int exhaustSpeed = 3660;        // m/s, from the engine
    float accelLimit = 3 * 9.81f;   // 3G, G is earth acceleration of gravity
    float speedLimit = 5;           // m/s, landing speed limit
    int timeCount = -1;
    float startSpeed = 0;
    float startHeight = 0;
    int startfuelMass = 400;
    float startFlightTime = 0;

    // flight variables
    float fuel;                     // kg, fuel consumption
    float duration;                 // s, maneuver time
    float speed;                    // m/s^2, current speed
    float height;                   // m, current height
    float acceleration;
    float fuelMass;
    float flightTime;
    boolean isLanding;

    private String showConstants() {
        return String.format("g = %5.3f M = %d c = %d a = %5.3f m = %d",
            accelOfGravity, dryMass, exhaustSpeed, accelLimit, startfuelMass);
    }

    private String showVariables() {
        return String.format(
            "f = %5.3f t = %5.3f v = %5.3f h = %5.3f a = %5.3f F = %5.3f T = %5.3f",
            fuel, duration, speed, height, acceleration, fuelMass, flightTime);
    }

    private void init() {
        speed = startSpeed;
        height = startHeight;
        fuelMass = startfuelMass;
        flightTime = startFlightTime;
        isLanding = false;
    }

    private float addHeiht(float duration, float speed, float acceleration) {
        float averageSpeed = (speed + speed + duration * (acceleration - accelOfGravity)) / 2;
        return duration * averageSpeed;
    }

    private boolean isAlmostLanded(float height) {
        return Math.abs(height) < 0.00001;
    }

    private float calculateAcceleration(float reverse, float consumption,
            float exhaustSpeed, float mass) {
        return reverse * consumption * exhaustSpeed / mass;
    }

    private void calculateLanding(float reverse, float consumption) {
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
        fuel = consumption * duration;
        acceleration = calculateAcceleration(reverse, consumption,
            exhaustSpeed, dryMass + fuelMass);
    }

    private void simulate(float reverse) {
        if (duration > 0) {
            if (fuelMass > 0) {

                if (Float.compare(speed, startSpeed) == 0)
                    System.out.println("Starting...");

                if (fuel > fuelMass) {
                    duration *= fuelMass / fuel;
                    fuel = fuelMass;
                }

                float consumption = fuel / duration;
                acceleration = calculateAcceleration(reverse, consumption,
                    exhaustSpeed, dryMass + fuelMass);

                if (height + addHeiht(duration, speed, acceleration) < 0) {
                    calculateLanding(reverse, consumption);
                }

                height += addHeiht(duration, speed, acceleration);
                speed += duration * (acceleration - accelOfGravity);
                fuelMass -= fuel;
                flightTime -= duration * timeCount;

                if (acceleration > accelLimit)
                    System.out.println("Overload");

                System.out.println(showVariables());
                if (isAlmostLanded(height)) {
                    System.out.println("Landing (" + height + ")");
                    speed = 0;
                    height = 0;
                    flightTime = 0;
                    isLanding = true;
                }
            }
        }
    }

    private void command() {
        Scanner sc = new Scanner(System.in);
        System.out.println(showConstants());
        init();
        while (!isLanding) {
            fuel = sc.nextInt();
            duration = sc.nextFloat();
            float reverse = Math.signum(fuel);
            fuel = Math.abs(fuel);
            simulate(reverse);
        }
    }

    public static void main(String[] args) {
        new LunarFlyOne().command();
    }
}
