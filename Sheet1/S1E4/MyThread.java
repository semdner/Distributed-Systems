public class MyThread extends Thread {

    int x;

    MyThread(int x) {
        this.x = x;
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(x);
                sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
