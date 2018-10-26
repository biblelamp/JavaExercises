import java.util.Scanner;

/**
 * Java. Implementation of LunarFly version 1
 * based on http://slavav.ru/way_to_earth/
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Oct 26, 2018
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
    int fuel;
    float duration;
    float speed;
    float height;
    float acceleration;
    int fuelMass;
    float flightTime;

    private String showConstants() {
        return String.format("g = %5.3f M = %d c = %d a = %5.3f m = %d",
            accelOfGravity, dryMass, exhaustSpeed, accelLimin, startfuelMass);
    }

    private String showVariables() {
        return String.format("f = %d t = %3.1f v = %5.3f h = %5.3f a = %5.3f F = %d T = %5.3f",
            fuel, duration, speed, height, acceleration, fuelMass, flightTime);
    }

    private void init() {
        speed = startSpeed;
        height = startHeight;
        fuelMass = startfuelMass;
        flightTime = startFlightTime;
    }

    private void simulate(float reverse, int fuel, float duration) {
        if (duration > 0) {
            if (fuelMass > 0) {

                float consumption = fuel / duration;
                acceleration = reverse * consumption * exhaustSpeed /
                    (dryMass + fuelMass);
                float averageSpeed = (speed + speed + duration * (acceleration - accelOfGravity)) / 2;
                speed += duration * (acceleration - accelOfGravity);
                height += duration * averageSpeed;
                fuelMass -= fuel;
                flightTime -= duration * timeCount;
                
                if (height < 0) {
                }

                System.out.println(showVariables());
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
