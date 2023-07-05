package lesson1.spring;

import lesson1.Camera;
import lesson1.CameraRoll;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CameraRoll cameraRoll() {
        return new CameraRoll() ;
    }

    @Bean
    public Camera camera(CameraRoll cameraRoll) {
        Camera camera = new Camera();
        camera.setCameraRoll(cameraRoll);
        return camera;
    }
}
