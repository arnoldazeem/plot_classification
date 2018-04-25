/**
 * Created by adaboo on 4/25/18.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class linehfreechart {

    public static void main( String[ ] args ) throws Exception {


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

                        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
                        yAxis = entry.getValue();
                        //clear the series everytime
                        //xAxis.size()
                        for (int j = 1; j < 25 ; j++) {

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

                            line_chart_dataset.addValue( a , "dummy1" , "1970" );
                            line_chart_dataset.addValue( b, "dummy1" , "1980" );
                            line_chart_dataset.addValue( c , "dummy2" , "1970" );
                            line_chart_dataset.addValue( d , "dummy2" , "1980" );
                            line_chart_dataset.addValue( e , "dummy3" , "1970" );
                            line_chart_dataset.addValue( f , "dummy3" , "1980" );
                        }


                        JFreeChart lineChartObject = ChartFactory.createLineChart(
                                "Schools Vs Years","Year",
                                "Schools Count",
                                line_chart_dataset,PlotOrientation.VERTICAL,
                                true,true,false);

                        int width = 640;    /* Width of the image */
                        int height = 480;   /* Height of the image */
                        File lineChart = new File( xAxis.get(0) +"jfreelin" + ".jpg");
                        ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);

                        xAxis = yAxis;


                    }

                }


            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }

    }
}