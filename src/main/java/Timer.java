import javafx.scene.control.Label;

public class Timer implements Runnable {

    private Label out;
    private Thread t;
    private long start;

    public Timer(Label out) {
        this.out = out;
        start = System.currentTimeMillis();
        t = new Thread(this);
        t.start();
    }


    @Override
    public void run() {
        while (true) {

            long now = System.currentTimeMillis() - start;

            out.setText(String.valueOf(now));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Thread getT() {
        return t;
    }
}
