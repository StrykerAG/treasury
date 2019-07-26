import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


    class CsvGenerator extends TimerTask {

        public void run() {
            ArrayList<Tax> allTaxes = new Document().getAllTaxes();
            FileWriter writer = null;
            try {
                writer = new FileWriter("treasury.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }


            for (Tax allTax : allTaxes) {
                try {
                    writer.write(String.valueOf(allTax));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
public class Main {
    public static void main(String[] args) {

        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        date.set(
                Calendar.DAY_OF_WEEK,
                Calendar.THURSDAY
        );
        date.set(Calendar.HOUR, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        timer.schedule(
                new CsvGenerator(),
                date.getTime(),
                1000 * 60 * 60 * 24 * 7
        );
    }
}
