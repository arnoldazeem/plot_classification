/**
 * Created by adaboo on 4/19/18.
 */
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class linechart {

    public static void main(String[] args) throws Exception {

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

                        System.out.println(yAxis);
                        yAxis = entry.getValue();
                        //clear the series everytime
                        //xAxis.size()
                        for (int j = 1; j < 3 ; j++) {
                            String trim = xAxis.get(j).trim();
                            String trim2 = yAxis.get(j).trim();
                            float a = Float.parseFloat(trim);
                            float b = Float.parseFloat(trim2);
                            list.add(a);
                            lister.add(b);
                        }



                                // Create Chart
                        XYChart chart = new XYChart(640, 480);
                        chart.setTitle("Sample Chart");
                        chart.setXAxisTitle("X");
                        chart.setXAxisTitle("Y");
                        XYSeries series = chart.addSeries("y(x)", null, list,lister);
                        series.setMarker(SeriesMarkers.CIRCLE);

                        BitmapEncoder.saveBitmap(chart,"line" +  xAxis.get(0) + ".", BitmapEncoder.BitmapFormat.JPG);

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





