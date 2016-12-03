public class ExampleQueue {

    public static void main(String[] args) {
        //Queue queue = new Queue(5);
        DimamicQueue<Integer> queue = new DimamicQueue<Integer>();

        queue.add(10);
        queue.add(20);
        queue.add(30);
        System.out.println("Added 10, 20, 30: " + queue);

        queue.add(40);
        queue.add(50);
        System.out.println("Added 40, 50: " + queue);

        System.out.println("Extract: " + queue.extract() + " " + queue);
        System.out.println("Extract: " + queue.extract() + " " + queue);
        System.out.println("Extract: " + queue.extract() + " " + queue);

        queue.add(60);
        System.out.println("Added 60: " + queue);
    }
}