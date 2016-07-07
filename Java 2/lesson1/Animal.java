package lesson1;

/**
 * abstract class Animal
 */
public abstract class Animal {
    protected String name;
    protected String animType;
    protected float maxRunDistance;
    protected boolean onDistance;

    public boolean isOnDistance() {
        return onDistance;
    }

    public void setOnDistance(boolean onDistance) {
        this.onDistance = onDistance;
    }

    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return animType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void info(){
        System.out.println(animType + " " + name);
    }

    public void cross(float dist){
        if (dist < maxRunDistance){
            System.out.println(animType + " crossed");
        } else {
            getOutOfDistance("cross");
        }
    }

    public void getOutOfDistance(String reason){
        System.out.println(animType + " " + name + " " + reason + " failed");
        onDistance = false;
    }
}