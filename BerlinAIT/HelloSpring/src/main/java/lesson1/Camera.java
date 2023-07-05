package lesson1;

public class Camera {
    private CameraRoll cameraRoll;

    public Camera() {
    }

    public Camera(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    public CameraRoll getCameraRoll() {
        return cameraRoll;
    }

    public void setCameraRoll(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    public void doPhotograph() {
        System.out.println("Click!");
        cameraRoll.processing();
    }
}
