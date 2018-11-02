/**
 * Java. Lunar lander simple mathematical model
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Nov 02, 2018
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
   private float fuelWeight = 400;             // kg, total fuel weight
   private float flightTime;                   // sec, total flight time
   private boolean isLanding;                  // sign of landing

   public LunarLanderModel() {
   }

   public float getFuel() {
      return fuel;
   }

   public int getIntFuel() {
      return (int) fuel;
   }

   public void addFuel(float fuel) {
      this.fuel += fuel;
      if (this.fuel < 0)
         this.fuel = 0;
   }

   public float getDuration() {
      return duration;
   }

   public void addDuration(float duration) {
      this.duration += duration;
      if (this.duration < 0.001)
         this.duration = 0;
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
}
