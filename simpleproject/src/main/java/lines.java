import org.knowm.xchart.CSVImporter;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/**
 * Created by adaboo on 4/19/18.
 */
public class lines {


        public static void main(String[] args) throws Exception {

            // import chart from a folder containing CSV files
            XYChart chart = CSVImporter.getChartFromCSVDir("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/simpleproject/java data", CSVImporter.DataOrientation.Rows, 600, 400);

            // Show it
            new SwingWrapper(chart).displayChart();
        }
    }
