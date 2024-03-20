class RobotWalk {
    public static void main(String[] args) {
        Robot robot = new Robot();

        Thread leftThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                robot.walkLeft();
            }
        });

        Thread rightThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                robot.walkRight();
            }
        });

        leftThread.start();
        rightThread.start();
    }
}
class Robot {
    private boolean isLeftFoot;

    public void walkLeft() {
        synchronized (this) {
            while (isLeftFoot) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Left");
            isLeftFoot = true;
            notify();
        }
    }

    public void walkRight() {
        synchronized (this) {
            while (!isLeftFoot) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Right");
            isLeftFoot = false;
            notify();
        }
    }
}
