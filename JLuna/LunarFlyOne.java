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
    float startHeight = 0;
    float startSpeed = 0;
    int startFuelMass = 0;
    int startFlightTime = 0;

    // flight variables
    float height;
    float speed;
    float fuelMass;
    float flightTime;

    public static void main(String[] args) {
        new LunarFlyOne();
    }
}
