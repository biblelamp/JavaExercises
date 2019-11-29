class Hammer {
    public static void main(String[] args) {
        int count = 0;
        float nail = 10f;
        float cmPerHit = 1.95f;
        while (nail > 0) {
            count++;
            nail = nail - cmPerHit;
            System.out.println("Hit #" + count);
        }
    }
}