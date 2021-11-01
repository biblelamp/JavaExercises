// Класс управления питанием GPS

class GPSPower {
    public void powerOn() {
        System.out.println("Power ON");
    }

    public void powerOff() {
        System.out.println("Power OFF");
    }
}

// Получение информации о пробках на дорогах

class GPSNotifier {
    public void downloadRoadInfo() {
        System.out.println("Downloading road information...");
        System.out.println("Download complete!");
    }
}

// Обработка полученной информации и прокладка оптимального маршрута

class RoadAdvisor {
    public void route() {
        System.out.println("Create a route");
    }
}

// Действуем вручную: включение GPS, получение информации, прокладка маршрута, выключение GPS

class Manual {
    public void execute() {
        GPSPower power = new GPSPower();
        GPSNotifier notifier = new GPSNotifier();
        RoadAdvisor advisor = new RoadAdvisor();
        power.powerOn();
        notifier.downloadRoadInfo();
        advisor.route();
        power.powerOff();
    }
}

// Класс фасад, автоматизирующий ручную работу

class GPSInterface {
    private GPSPower power;
    private GPSNotifier notifier;
    private RoadAdvisor advisor;
    
    public GPSInterface(GPSPower power, GPSNotifier notifier, RoadAdvisor advisor){
        this.power = power;
        this.notifier = notifier;
        this.advisor = advisor;
    }

    public void activate(){
        power.powerOn();
        notifier.downloadRoadInfo();
        advisor.route();
    }
}

// Автоматизируем часть рутины с помощю фасада

class Automatic {
    public void execute() {
        GPSPower power = new GPSPower();
        GPSNotifier notifier = new GPSNotifier();
        RoadAdvisor advisor = new RoadAdvisor();
        GPSInterface gps = new GPSInterface(power, notifier, advisor);        
        gps.activate();
        power.powerOff();
    }
}

public class FacadeExample {
    public static void main(String[] args) {
        Manual manual = new Manual();
        manual.execute();
        
        Automatic automatic = new Automatic();
        automatic.execute();
    }
}