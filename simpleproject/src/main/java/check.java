/*
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;
*/
/**
 * Creates a simple Chart using QuickChart
 *//*

public class check {

    public static void main(String[] args) throws Exception {

//....
        String fileName = "3.csv";
        Map<Integer,ArrayList<String>> some= null;

        try {

            final BufferedReader input =  new BufferedReader(new FileReader(fileName));

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
                XYChart chart = QuickChart.getChart(fileName, xAxis.get(0), yAxis.get(0), xAxis.get(0), list, lister);
                // Show it
                new SwingWrapper(chart).displayChart();

                //BitmapEncoder.saveBitmap(chart, xAxis + "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);

                xAxis = yAxis;

            }

        }

    }
}*/
