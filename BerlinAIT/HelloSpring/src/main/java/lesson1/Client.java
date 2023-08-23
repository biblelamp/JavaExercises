package lesson1;

public class Client {
    public static void main(String[] args) {
        CameraRoll cameraRoll = new CameraRoll();
        Camera camera = new Camera(cameraRoll);
        //camera.setCameraRoll(cameraRoll);
        camera.doPhoto();
    }
}
