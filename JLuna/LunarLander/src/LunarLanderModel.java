/**
 * Java. Lunar lander simple mathematical model
 *
 * @author Sergey Iryupin
 * @version 0.1.2 dated Nov 08, 2018
 */

public class LunarLanderModel {

    // flight constants
    private float accelOfGravity = 1.62f;      // m/s^2, at Moon surface
    private int dryWeight = 2000 + 150;         // kg, lunarfly and pilot
    private int exhaustSpeed = 3660;            // m/s, from the engine
    private float accelLimit = 3 * 9.81f;       // 3G, G is earth acceleration of gravity
    private float speedLimit = 5;               // m/s, landing speed limit

    // flight variables
    private float fuel;                         // kg, fuel consumption
    private float duration;                     // sec, maneuver time
    private float speed;                        // m/s^2, current speed
    private float height;                       // m, current height
    private float acceleration;                 // m/c^2
    private float fuelWeight;                   // kg, total fuel weight
    private float flightTime;                   // sec, total flight time
    private boolean isLanding;                  // sign of landing

    // init variables
    float startSpeed = 0;
    float startHeight = 0;
    int startFuelWeight = 400;
    float startFlightTime = 0;

    public LunarLanderModel() {
           speed = startSpeed;
           height = startHeight;
        fuelWeight = startFuelWeight;
           flightTime = startFlightTime;
           isLanding = false;
    }

    public float getFuel() {
       return fuel;
    }

    public int getIntFuel() {
       return (int) fuel;
    }

    public float getDuration() {
        return duration;
    }

    public float getFuelWeight() {
       return fuelWeight;
    }

    public float getTotalWeight() {
       return dryWeight + fuelWeight;
    }

    public float getFlightTime() {
       return flightTime;
    }

    public float getSpeed() {
       return speed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public float getHeight() {
       return height;
    }

    public void addFuel(float fuel) {
        this.fuel += fuel;
        if (this.fuel < 0)
            this.fuel = 0;
    }

    public void addDuration(float duration) {
        this.duration += duration;
        if (this.duration < 0.001)
            this.duration = 0;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    private String showVariables() {
        return String.format(
                "f = %5.3f t = %5.3f v = %5.3f h = %5.3f a = %5.3f F = %5.3f T = %5.3f",
                fuel, duration, speed, height, acceleration, fuelWeight, flightTime);
    }

    private float nextHeiht(float height, float duration, float speed, float acceleration) {
        float nextSpeed = speed + (acceleration - accelOfGravity) * duration;
        height += (speed + nextSpeed) / 2 * duration;
        return height;
    }

    private boolean isAlmostLanded(float height) {
        return Math.abs(height) < 0.00001;
	}

    public void simulate(float reverse) {
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
}
