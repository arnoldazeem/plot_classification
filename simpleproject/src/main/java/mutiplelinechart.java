
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class mutiplelinechart {

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
                        double [] lis = new double[5];
                        double [] liss = new double[5];

                        double [] lis2 = new double[5];
                        double [] liss2 = new double[5];

                        double [] lis3 = new double[5];
                        double [] liss3 = new double[5];



                        for (Map.Entry<Integer,ArrayList<String>> entry : some.entrySet()){

                            if(check){
                                xAxis = entry.getValue();
                                check = false;

                            }else{

                                DefaultXYDataset ds = new DefaultXYDataset();
                                yAxis = entry.getValue();
                                //clear the series everytime
                                //xAxis.size()
                                for (int j = 1; j < 2 ; j++) {

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


                                    lis[j] = (a);
                                    liss[j] =  (b);

                                    lis2[j] = (c);
                                    liss2[j] =  (d);

                                    lis3[j] = (e);
                                    liss3[j] =  (f);
                                }


                                double [][] data = { lis,liss };

                                double [][] data2 = { lis2,liss2 };

                                double [][] data3 = { lis3,liss3 };

                                ds.addSeries("series1", data);

                                ds.addSeries("series2", data2);

                                ds.addSeries("series3", data3);

                                // Create Chart

                                JFrame frame = new JFrame("Charts");

                                frame.setSize(640, 480);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                               // frame.setVisible(true);

                                JFreeChart chart = ChartFactory.createXYLineChart("Test Chart",
                                        "x", "y", ds, PlotOrientation.VERTICAL, true, true,
                                        false);

                                ChartPanel cp = new ChartPanel(chart);

                                 frame.getContentPane().add(cp);

                               File outputfile = new File(xAxis.get(0) + ".jpg");

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