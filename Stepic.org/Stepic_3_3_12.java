class Stepic_3_3_12 {

    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static void main(String[] args) {
        new Stepic_3_3_12().test();
    }

    void test() {
        Robot robot = new Robot();
        moveRobot(robot, 3, 0);
        System.out.println(robot.getX() + ":" + robot.getY());
    }

    void moveRobot(Robot robot, int toX, int toY) {
        int x = robot.getX();
        int y = robot.getY();

        if (x > toX){
            while (robot.getDirection() != Direction.LEFT) {
                robot.turnLeft();
            }
            while (x != toX) {
                robot.stepForward();
                x--;
            }
        } else if (x < toX) {
            while (robot.getDirection() != Direction.RIGHT) {
                robot.turnLeft();
            }
            while (x != toX) {
                robot.stepForward();
                x++;
            }
        }
        if (y > toY) {
            while (robot.getDirection() != Direction.DOWN) {
                robot.turnLeft();
            }
            while (y != toY) {
                robot.stepForward();
                y--;
            }
        } else if (y < toY) {
            while (robot.getDirection() != Direction.UP) {
                robot.turnLeft();
            }
            while(y != toY) {
                robot.stepForward();
                y++;
            }
        }
    }

    class Robot {
        int x, y;
        Direction direction;

        // constructor
        Robot() {
            x = y = 0;
            direction = Direction.UP;
        }

        // the current direction of view
        Direction getDirection() {
            return direction;
        }

        // the current coordinate X
        int getX() {
            return x;
        }

        // the current coordinate Y
        int getY() {
            return y;
        }

        // rotate 90 degrees counterclockwise
        void turnLeft() {
            switch (direction) {
                case UP:
                    direction = Direction.LEFT;
                    break;
                case RIGHT:
                    direction = Direction.UP;
                    break;
                case DOWN:
                    direction = Direction.RIGHT;
                    break;
                case LEFT:
                    direction = Direction.DOWN;
                    break;
            }
        }

        // rotate 90 degrees clockwise
        void turnRight() {
            switch (direction) {
                case UP:
                    direction = Direction.RIGHT;
                    break;
                case RIGHT:
                    direction = Direction.DOWN;
                    break;
                case DOWN:
                    direction = Direction.LEFT;
                    break;
                case LEFT:
                    direction = Direction.UP;
                    break;
            }
        }

        // a step in the direction of view
        // in one step the robot modifies its coordinate 1 unit
        void stepForward() {
            switch (direction) {
                case UP:
                    y++;
                    break;
                case RIGHT:
                    x++;
                    break;
                case DOWN:
                    y--;
                    break;
                case LEFT:
                    x--;
                    break;
            }
        }
    }
}