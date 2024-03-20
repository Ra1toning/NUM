class RobotChat {
    public static void main(String[] args) {
        final Robot robot1 = new Robot("Робот 1");
        final Robot robot2 = new Robot("Робот 2");

        Thread thread1 = new Thread(() -> {
            synchronized (robot1) {
                System.out.println(robot1.getName() + ": Сайн уу?");
                try {
                    Thread.sleep(1000);
                    robot1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot1.notify();
                System.out.println(robot1.getName() + ": Таны ажил сайн биз дээ");
                try {
                    Thread.sleep(1000);
                    robot1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot1.notify();
                System.out.println(robot1.getName() + ": Миний ажил маш сайн байгаа");
                try {
                    Thread.sleep(1000);
                    robot1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot1.notify();
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (robot1) {
                System.out.println(robot2.getName() + ": Сайн.");
                robot1.notify();
                try {
                    Thread.sleep(1000);
                    robot1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(robot2.getName() + ": Тийм ээ сайн. Харин таны ажил сайн уу?");
                robot1.notify();
                try {
                    Thread.sleep(1000);
                    robot1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(robot2.getName() + ": За ашгүй дээ.");
            }
        });

        thread1.start();
        thread2.start();
    }
}

class Robot {
    private String name;

    public Robot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
