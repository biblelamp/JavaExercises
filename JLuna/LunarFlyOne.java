/**
 * Java. Implementation of LunarFly version 1
 * based on http://slavav.ru/way_to_earth/
 *
 * @author Sergey Iryupin
 * @version dated Oct 25, 2018
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
    int startFuelMass = 400;
    int startFlightTime = 0;

    // flight variables
    int fuel;
    int time;
    float speed;
    float height;
    float acceleration;
    int fuelMass;
    int flightTime;
    
    private String showConstants() {
        return String.format("g = %5.3f M = %d c = %d a = %5.3f m = %d",
            accelOfGravity, dryMass, exhaustSpeed, accelLimin, startFuelMass);
    }

    private String showVariables() {
        return String.format("f = %d t = %d v = %5.3f h = %5.3f a = %5.3f F = %d T = %d",
            fuel, time, speed, height, acceleration, fuelMass, flightTime);
    }

    private void command() {
        System.out.println(showVariables());
    }

    public static void main(String[] args) {
        new LunarFlyOne().command();
    }
}
