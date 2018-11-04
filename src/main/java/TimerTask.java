import javafx.concurrent.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimerTask extends Task<Void> {

    private long start;
    final DateFormat formatter = new SimpleDateFormat("HH:mm:ss");


    public TimerTask() {
        start = System.currentTimeMillis();
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            long now = System.currentTimeMillis() - start;

            long second = TimeUnit.MILLISECONDS.toSeconds(now);
            long minute = TimeUnit.MILLISECONDS.toMinutes(now);
            long hour = TimeUnit.MILLISECONDS.toHours(now);

            updateMessage(String.format("%02d:%02d:%02d", hour, minute, second));
            Thread.sleep(1000);
        }
    }
}
