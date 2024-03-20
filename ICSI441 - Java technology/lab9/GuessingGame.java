import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GuessingGame {
    public static final int MAX = 100;
    private int secretNumber;
    private List<GuessingThread> threads;

    public GuessingGame() {
        this.secretNumber = new Random().nextInt(MAX) + 1;
        this.threads = new ArrayList<>();
    }

    public synchronized void addThread(GuessingThread thread) {
        threads.add(thread);
    }

    public int getSecretNumber() {
        return secretNumber;
    }

    public void startGame() {
        for (GuessingThread thread : threads) {
            thread.start();
        }
    }

    public synchronized void signalOtherThreads(GuessingThread winnerThread) {
        for (GuessingThread thread : threads) {
            if (thread != winnerThread) {
                thread.interrupt();
            }
        }
        notifyAll();
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();

        for (int i = 1; i <= 3; i++) {
            GuessingThread thread = new GuessingThread(game, i);
            game.addThread(thread);
        }

        game.startGame();
    }
}

class GuessingThread extends Thread {
    private GuessingGame game;
    private int threadNumber;
    private List<Integer> guesses;

    public GuessingThread(GuessingGame game, int threadNumber) {
        this.game = game;
        this.threadNumber = threadNumber;
        this.guesses = new ArrayList<>();
    }

    @Override
    public void run() {
        synchronized (game) {
            try {
                while (true) {
                    int guess = new Random().nextInt(GuessingGame.MAX) + 1;
                    guesses.add(guess);

                    if (guess == game.getSecretNumber()) {
                        System.out.println("Thread " + threadNumber + ": " + guesses + " ТААСАН, " + guesses.size() + " удаа оролдлого хийв.");
                        game.signalOtherThreads(this);
                        break;
                    } else {
                        System.out.println("Thread " + threadNumber + ": " + guesses + " таагаагүй бай " + guesses.size() + " удаа таах оролдлого хийв. Дахин оролдлого хийх гэж байна...");

                        game.wait(100);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Thread " + threadNumber + " амжилттай саллаа");
                Thread.currentThread().interrupt();
            }
        }
    }
}
