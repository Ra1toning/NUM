class AlphabetPrinter {
    public static void main(String[] args) {
        AlphabetRunnable alphaRunnable = new AlphabetRunnable();
        Thread thread1 = new Thread(alphaRunnable, "Thread 1");
        Thread thread2 = new Thread(alphaRunnable, "Thread 2");
        Thread thread3 = new Thread(alphaRunnable, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class AlphabetRunnable implements Runnable {
    private static final Object lock = new Object();
    private static char currentChar = 'A';
    private final int charCount = 100;

    @Override
    public void run() {
        for (int i = 0; i < charCount; i++) {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + ": " + currentChar);
                currentChar = (char) (currentChar + 1);
                if (currentChar > 'Z') {
                    currentChar = 'A';
                }
            }
        }
    }
}
