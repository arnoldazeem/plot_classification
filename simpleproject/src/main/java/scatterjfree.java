import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.knowm.xchart.BitmapEncoder;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class scatterjfree {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                File dir = new File("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/simpleproject/java data");
                File[] directoryListing = dir.listFiles();
                if (directoryListing != null) {
                    for (File child : directoryListing) {
                        // Do something with child

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
                        //Prepare XYChart.Series objects by setting data
                        List<Float> list = new ArrayList<Float>();
                        List<Float> lister = new ArrayList<Float>();


                        for (Map.Entry<Integer,ArrayList<String>> entry : some.entrySet()){

                            if(check){
                                xAxis = entry.getValue();
                                check = false;

                            }else{

                                XYSeriesCollection datasets = new XYSeriesCollection();

                                XYSeries series1 = new XYSeries("dummy1");

                                XYSeries series2 = new XYSeries("dummy2");

                                XYSeries series3 = new XYSeries("dummy3");
                                //Boys (Age,weight) series

                                yAxis = entry.getValue();
                                //clear the series everytime
                                //xAxis.size()
                                for (int j = 1; j < 15 ; j++) {

                                    String trim = xAxis.get(j).trim();
                                    String trim2 = yAxis.get(j).trim();

                                    String trim3 = xAxis.get(j+2).trim();
                                    String trim4 = yAxis.get(j+2).trim();

                                    String trim5 = xAxis.get(j+3).trim();
                                    String trim6 = yAxis.get(j+3).trim();

                                    float a = Float.parseFloat(trim);
                                    float b = Float.parseFloat(trim2);

                                    float c = Float.parseFloat(trim3);
                                    float d = Float.parseFloat(trim4);

                                    float e = Float.parseFloat(trim5);
                                    float f = Float.parseFloat(trim6);


                                    series1.add(a, b);

                                    series2.add(c, d);

                                    series3.add(e, a);
                                }
                                datasets.addSeries(series1);

                                datasets.addSeries(series2);

                                datasets.addSeries(series3);

                                // Create chart
                                JFreeChart chart = ChartFactory.createScatterPlot(
                                        "Boys VS Girls weight comparison chart",
                                        "dummy1", "dummy2", datasets);


                                //Changes background color
                                XYPlot plot = (XYPlot)chart.getPlot();
                                plot.setBackgroundPaint(new Color(255,228,196));

                                // Create Panel
                                ChartPanel panel = new ChartPanel(chart);

                                File BarChart = new File( xAxis.get(0) +"jfreebar" + ".jpg" );

                                try{

                                    ChartUtilities.saveChartAsJPEG( BarChart , chart , 640 , 480 );

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