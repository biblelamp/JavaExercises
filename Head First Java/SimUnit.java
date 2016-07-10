class SimUnit {
    String botType;
    SimUnit(String type) {
        botType = type;
        System.out.println(type + " created."); // to search for a problem
    }
    int powerUse() {
        if ("Retention".equals(botType)) {
            return 2;
        } else {
            return 4;
        }
    }
}