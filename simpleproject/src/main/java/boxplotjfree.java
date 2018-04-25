import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCalculator;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerXYDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.date.DateUtilities;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by adaboo on 4/22/18.
 */



public class boxplotjfree {

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                File dir = new File("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/simpleproject/java data");
                File[] directoryListing = dir.listFiles();
                if (directoryListing != null) {
                    for (File child : directoryListing) {
                        // Do something with child
                        System.out.println(child);

                        Map<Integer,ArrayList<String>> some= null;

                        try {

                            final BufferedReader input =  new BufferedReader(new FileReader(child));
                            String line = null;
                            int cnt = 0;
                            String[] topRow = new String[0];
                            int i=0;
                            List<String[]> templist = new ArrayList<>();

                            while (( line = input.readLine()) != null){

                                String[] l = line.split(",");

                                //put alles in ein arraylist
                                templist.add(l);

                            }
                            //System.out.println(labelx);
                            // System.out.println(l[0]);
                            int colsize = templist.get(0).length;//this is the column size

                            String[][] col =  new String[templist.size()][colsize];

                            some = new ConcurrentHashMap<>();
                            ArrayList<String> somerow = new ArrayList<>();

                            for(int d = 0; d< colsize; d++){
                                somerow = new ArrayList<String>();

                                for (int c = 0; c< templist.size(); c++)   {
                                    somerow.add(templist.get(c)[d]);
                                }
                                some.put(d,somerow);
                            }


                        } catch (IOException e) {
                            System.err.println("Caught IOException: " + e.getMessage());
                        }

                        for (Map.Entry<Integer,ArrayList<String>> entry : some.entrySet()){

                            ArrayList<String> s = (ArrayList<String>)entry.getValue();

                            String pattern = "[^\\d]";
                            try{

                                Float.parseFloat(s.get(1));

                            }catch (Exception e){

                                some.remove(entry.getKey());
                            }

                        }

                        Boolean check = true;
                        List<String> xAxis = new ArrayList<String>();
                        List<String> yAxis = new ArrayList<String>();


                        for (Map.Entry<Integer,ArrayList<String>> entry : some.entrySet()){

                            if(check){
                                xAxis = entry.getValue();
                                check = false;

                            }else{


                                DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();

                                List val = new ArrayList();


                                yAxis = entry.getValue();
                                //clear the series everytime
                                //xAxis.size()

                                final int seriesCount = 6;
                                final int categoryCount = 4;

                                for (int i = 1; i < seriesCount; i++) {

                                    for (int k = 1; k < categoryCount; k++) {
                                        final List list = new ArrayList();
                                        // add some values...
                                        for (int j = 1; j < 10; j++) {
                                            String trim = xAxis.get(j).trim();
                                            String trim2 = yAxis.get(j).trim();

                                            String trim3 = xAxis.get(j+2).trim();
                                            String trim4 = yAxis.get(j+2).trim();


                                            String trim5 = xAxis.get(j+3).trim();
                                            String trim6 = yAxis.get(j+3).trim();


                                            Double a = Double.parseDouble(trim);
                                            Double b = Double.parseDouble(trim2);

                                            Double c = Double.parseDouble(trim3);
                                            Double d = Double.parseDouble(trim4);

                                            Double e = Double.parseDouble(trim5);
                                            Double f = Double.parseDouble(trim6);


                                            val.add(new Double(a));
                                            val.add(new Double(b));

                                        }

                                        dataset.add(val, "Series " + i, " Type " + k);
                                    }

                                }

                                dataset.add(val, "Series " , " Type ");


                                final CategoryAxis xxis = new CategoryAxis("Type");
                                final NumberAxis yxis = new NumberAxis("Value");
                                yxis.setAutoRangeIncludesZero(false);
                                final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
                                renderer.setFillBox(false);
                                renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
                                final CategoryPlot plot = new CategoryPlot(dataset, xxis, yxis, renderer);

                                final JFreeChart chart = new JFreeChart(
                                        "Box-and-Whisker Demo",
                                        new Font("SansSerif", Font.BOLD, 14),
                                        plot,
                                        true
                                );
                                final ChartPanel chartPanel = new ChartPanel(chart);
                                chartPanel.setPreferredSize(new java.awt.Dimension(640, 480));

                                File outputfile = new File(xAxis.get(0) + "boxes34" + ".jpg");

                                try{
                                    ChartUtilities.saveChartAsJPEG(outputfile, chart, 640, 480);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                                xAxis = yAxis;


                            }

                        }


                    }
                }

            }
        });

    }


}