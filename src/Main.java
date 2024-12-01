public class Main {

    // Global variable to keep track of the count
    private static int count = 0;

    // Final variable to define the counting limit
    private static final int COUNTING_TO = 20;

    public static void main(String[] args) throws InterruptedException {
        // Create first thread
        Thread countUpThread = new Thread(new CountUpTask(COUNTING_TO));

        // create second thread
        Thread countDownThread = new Thread(new CountDownTask(COUNTING_TO));

        countUpThread.start();  // start counting up first
        countUpThread.join();   // wait for countUpThread to finish before starting countDownThread
        
        countDownThread.start(); // start counting down after countUpThread finishes
        countDownThread.join();  // wait for countDownThread to finish
    }

   

    static class CountUpTask implements Runnable {
        private final int limit;

        public CountUpTask(int limit) {
            this.limit = limit;
        }

        @Override
        public void run() {
            for (int i = 0; i < limit; i++) {
                incrementCount();
                System.out.println("Count up: " + count);
            }
        }

        private synchronized void incrementCount() {
            count++;
        }
    }

    static class CountDownTask implements Runnable {
        private final int limit;

        public CountDownTask(int limit) {
            this.limit = limit;
        }

        @Override
        public void run() {
            for (int i = limit; i > 0; i--) {
                decrementCount();
                System.out.println("Count down: " + count);
            }
        }

        private synchronized void decrementCount() {
            count--;
        }
    }
}