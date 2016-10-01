import java.util.*;

class Stepic_4_2_7 {

    Random random = new Random();

    public static void main(String[] args) {
        new Stepic_4_2_7().test();
    }

    void test() {
        RobotConnectManager rcm = new RobotConnectManager();
        for (int i = 1; i < 10; i++) {
            System.out.println(i +" attempt");
            moveRobot(rcm, i, 3);
        }
    }

    class RobotConnect implements RobotConnection {
        @Override
        public void moveRobotTo(int x, int y) {
            // implementation does not matter
        }
        @Override
        public void close() {
            // implementation does not matter
        }
    }

    class RobotConnectManager implements RobotConnectionManager {
        @Override
        public RobotConnect getConnection() {
            RobotConnect rc = null;
            boolean isConnect = random.nextBoolean();
            if (!isConnect) {
                System.out.println("No connection"); // for debug
                throw new RobotConnectionException("Not connect");
            } else {
                System.out.println("There is a connection"); // for debug
                rc = new RobotConnect();
            }
            return rc;
        }
    }

    /*
     * Defined interfaces and class of exception
     */

    interface RobotConnection extends AutoCloseable {
        void moveRobotTo(int x, int y);
        @Override
        void close();
    }

    interface RobotConnectionManager {
        RobotConnection getConnection();
    }

    class RobotConnectionException extends RuntimeException {
        public RobotConnectionException(String message) {
            super(message);
        }
        public RobotConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /*
     * Implement a method that establishes a connection with the robot,
     * giving him the command to move to the specified point, and then closes the connection
     * by performing repeat this sequence up to three times if necessary.
     */

    void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        int numberTry = 0; // number of attempts
        boolean isSuccessConnection = false; // successful connection and movement
        RobotConnection robotConnection = null;
        while ((isSuccessConnection != true) & (numberTry < 3)) {
            try {
                robotConnection = robotConnectionManager.getConnection();
                robotConnection.moveRobotTo(toX, toY);
                isSuccessConnection = true;
            } catch (RobotConnectionException e) {
                isSuccessConnection = false;
            } catch (Throwable e1) {
                isSuccessConnection = false;
                throw e1;
            } finally {
                if (robotConnection != null) {
                    try {
                        robotConnection.close();
                    } catch (RobotConnectionException e3) {
                        //throw e3;
                    } finally {
                        if (isSuccessConnection == false) numberTry++;
                    }
                } else numberTry++;
            }
        }
        if ((isSuccessConnection == false) & (numberTry == 3)) {
            throw new RobotConnectionException("Connection failure");
        }
    }
}