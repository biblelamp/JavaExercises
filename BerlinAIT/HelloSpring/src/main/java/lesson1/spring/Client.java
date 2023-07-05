package lesson1.spring;

import lesson1.Camera;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Camera camera = context.getBean(Camera.class);
        camera.doPhotograph();
    }
}
