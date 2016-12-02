public class ExampleQueue {

    public static void main(String[] args) {
        Queue queue = new Queue(5);

        queue.add(10);
        queue.add(20);
        queue.add(30);
        System.out.println("Queue: " + queue);

        queue.add(40);
        queue.add(50);
        System.out.println("Queue: " + queue);

        queue.extract();
        queue.extract();
        queue.extract();
        System.out.println("Queue: " + queue);

        queue.add(60);
        System.out.println("Queue: " + queue);
    }
}