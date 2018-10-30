import java.util.Scanner;

/**
 * Java. Lunar ship simple simulator v1
 * based on flowchart in http://epizodyspace.ru/bibl/tm/1986/5/put.html
 *
 * @author Sergey Iryupin
 * @version 0.2.8 dated Oct 30, 2018
 */
public class LunarFlyOne {

    // flight constants
    float accelOfGravity = 1.62f;   // m/s^2, at Moon surface
    int dryWeight = 2000 + 150;     // kg, lunarfly and pilot
    int exhaustSpeed = 3660;        // m/s, from the engine
    float accelLimit = 3 * 9.81f;   // 3G, G is earth acceleration of gravity
    float speedLimit = 5;           // m/s, landing speed limit

    // init variables
    float startSpeed = 0;
    float startHeight = 0;
    int startFuelWeight = 400;
    float startFlightTime = 0;

    // flight variables
    float fuel;                     // kg, fuel consumption
    float duration;                 // s, maneuver time
    float speed;                    // m/s^2, current speed
    float height;                   // m, current height
    float acceleration;
    float fuelWeight;
    float flightTime;
    boolean isLanding;

    private String showConstants() {
        return String.format("g = %5.3f M = %d c = %d a = %5.3f m = %d",
            accelOfGravity, dryWeight, exhaustSpeed, accelLimit, startFuelWeight);
    }

    private String showVariables() {
        return String.format(
            "f = %5.3f t = %5.3f v = %5.3f h = %5.3f a = %5.3f F = %5.3f T = %5.3f",
            fuel, duration, speed, height, acceleration, fuelWeight, flightTime);
    }

    private void init() {
        speed = startSpeed;
        height = startHeight;
        fuelWeight = startFuelWeight;
        flightTime = startFlightTime;
        isLanding = false;
    }

    private float nextHeiht(float height, float duration, float speed, float acceleration) {
        float nextSpeed = speed + (acceleration - accelOfGravity) * duration;
        height += (speed + nextSpeed) / 2 * duration;
        return height;
    }

    private boolean isAlmostLanded(float height) {
        return Math.abs(height) < 0.00001;
    }

    private void simulate(float reverse) {
        while (duration > 0 && !isLanding) {

            if (Float.compare(speed, startSpeed) == 0)
                System.out.println("Start");

            if (fuel > fuelWeight) {  // overrun fuel
                duration *= fuelWeight / fuel;
                fuel = fuelWeight;
            }

            float consumption = fuel / duration;
            acceleration = reverse * consumption * exhaustSpeed /
                (dryWeight + fuelWeight);

            if (nextHeiht(height, duration, speed, acceleration) < 0) {
                duration = 2 * height /
                    (float)(Math.sqrt(Math.pow(speed, 2) + 2 * height * (accelOfGravity - acceleration)) - speed);
                fuel = consumption * duration;
            }

            height = nextHeiht(height, duration, speed, acceleration);
            speed += duration * (acceleration - accelOfGravity);
            fuelWeight -= fuel;
            flightTime += duration;

            System.out.println(showVariables());

            if (acceleration > accelLimit) {
                System.out.println("Overload");
                duration = acceleration - accelLimit;
                fuel = 0;
            } else if (fuelWeight == 0 && !isAlmostLanded(height)) {
                System.out.println("Fuel is over");
                duration = exhaustSpeed;
                fuel = 0;
            } else
                duration = 0;

            if (isAlmostLanded(height)) {
                System.out.println("Landing (" + height + ") is " +
                    ((Math.abs(speed) < speedLimit)? "successful" : "catastrophic"));
                isLanding = true;
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
