
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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class jfreebarchartexamples {

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

                                DefaultCategoryDataset my_bar_chart_dataset = new DefaultCategoryDataset();

                                yAxis = entry.getValue();
                                //clear the series everytime
                                //xAxis.size()
                                for (int j = 1; j < 8 ; j++) {

                                    String trim = xAxis.get(j).trim();
                                    String trim2 = yAxis.get(j).trim();

                                    String trim3 = xAxis.get(j+2).trim();
                                    String trim4 = yAxis.get(j+2).trim();


                                    String trim5 = xAxis.get(j+3).trim();
                                    String trim6 = yAxis.get(j+3).trim();


                                    String trim7 = xAxis.get(j+5).trim();
                                    String trim8 = yAxis.get(j+5).trim();


                                    Double a = Double.parseDouble(trim);
                                    Double b = Double.parseDouble(trim2);

                                    Double c = Double.parseDouble(trim3);
                                    Double d = Double.parseDouble(trim4);

                                    Double e = Double.parseDouble(trim5);
                                    Double f = Double.parseDouble(trim6);

                                    Double g = Double.parseDouble(trim7);
                                    Double h = Double.parseDouble(trim8);

                                    my_bar_chart_dataset.addValue(a, "Q1", "Rome");
                                    my_bar_chart_dataset.addValue(b, "Q1", "Cairo");
                                    my_bar_chart_dataset.addValue(c, "Q1", "ghana");
                                    my_bar_chart_dataset.addValue(d, "Q2", "Rome");
                                    my_bar_chart_dataset.addValue(e, "Q2", "Cairo");
                                    my_bar_chart_dataset.addValue(f, "Q2", "ghana");
                                    my_bar_chart_dataset.addValue(g, "Q3", "Rome");
                                    my_bar_chart_dataset.addValue(h, "Q3", "Cairo");
                                    my_bar_chart_dataset.addValue(h, "Q3", "ghana");


                                }

                                // Create Chart
                                JFreeChart BarChartObject=ChartFactory.createBarChart(xAxis.get(0),"Country",
                                        "Sales",my_bar_chart_dataset,PlotOrientation.VERTICAL,true,true,false);

                                File BarChart = new File(xAxis.get(0) + "grouped" +  ".jpg");

                                try{
                                    ChartUtilities.saveChartAsJPEG(BarChart,BarChartObject,640,480);
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