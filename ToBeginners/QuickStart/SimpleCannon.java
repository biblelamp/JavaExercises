class SimpleCannon {
    public static void main(String[] args) {
        float g = 9.81f;  // acceleration of gravity, m/c2
        int speed = 150;  // starting speed of cannon shell, m/c
        int angle;        // shot angle, degrees
                          // distance [199 .. 1146] m

        for (angle = 0; angle < 90; angle += 5) {
            System.out.println(angle + ": " + getDistance(speed, angle, g));
        }
    }

    static double getDistance(int speed, int angle, float g) {
        return Math.pow(speed, 2) * Math.sin(Math.toRadians(angle*2)) / (2 * g);
    }
}