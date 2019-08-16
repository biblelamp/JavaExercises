import java.util.Scanner;

class SimpleCannonGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float g = 9.81f;             // acceleration of gravity, m/c2
        int speed = 150;             // starting speed of cannon shell, m/c
        int damageRadius = 5;        // damage radius
        double distanceTarget = 850; // distance to target
        int angle;                   // shot angle, degrees
        double distance;             // distance [199 .. 1146] m

        while (true) {
            System.out.print("Cannon agle: ");
            angle = sc.nextInt();
            distance = Math.pow(speed, 2) * Math.sin(Math.toRadians(angle*2)) / (2 * g);
            double difference = distanceTarget - distance;
            if (Math.abs(difference) < damageRadius) {
                System.out.println("Target hit");
                break;
            } else {
                String result = (difference > 0)? "Under" : "Over";
                System.out.printf("%sshoot %.2f\n", result, Math.abs(difference));
            }
        }
    }
}