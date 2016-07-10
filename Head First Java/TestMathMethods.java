public class TestMathMethods {
    public static void main(String[] args) {

        System.out.println("Testing Math.*");
    
        double r1 = Math.random();
        int r2 = (int) (Math.random() * 5);
        System.out.println(String.format("%f %d", r1, r2));
        
        int i = Math.abs(-240);
        double d = Math.abs(240.45);
        System.out.println(String.format("%d %.2f", i, d));
        
        int x = Math.round(-24.8f);
        int y = Math.round(24.45f);
        System.out.println(String.format("%d %d", x, y));

        double dc = Math.ceil(12.19f);
        double df = Math.floor(12.89f);
        System.out.println(String.format("%f %f", dc, df));
        
        double sq1 = Math.sqrt(16);
        double sq2 = Math.sqrt(5);
        System.out.println(String.format("%.2f %f", sq1, sq2));

        int im = Math.min(24, 240);
        double dm = Math.min(90876.5, 90876.49);
        System.out.println(String.format("%d %.2f", im, dm));
        
        im = Math.max(24, 240);
        dm = Math.max(90876.5, 90876.49);
        System.out.println(String.format("%d %.2f", im, dm));
    }
}