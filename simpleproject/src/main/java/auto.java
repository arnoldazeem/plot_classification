/**
 * Created by adaboo on 3/5/18.
 */

import com.opencsv.CSVReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class auto extends Application {

    @Override public void start(Stage stage) {

        String labelx;
        String labely;
        String data = "";
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


//                for (Map.Entry<Integer,ArrayList<String>> entry : some.entrySet()){
//
//                    ArrayList<String> s = (ArrayList<String>)entry.getValue();
//
//                    String pattern = "[^\\d]";
//                    try{
//
//                        Float.parseFloat(s.get(1));
//
//                    }catch (Exception e){
//
//                        some.remove(entry.getKey());
//                    }
//
//
//                    System.out.println(entry.getValue());
//
//                }


//                Boolean check = true;
//                List<String> xAxis = new ArrayList<String>();
//                List<String> yAxis = new ArrayList<String>();
//                //Prepare XYChart.Series objects by setting data
//                XYChart.Series series = new XYChart.Series();
//                //Creating the Scatter chart
//
//                final NumberAxis xxis = new NumberAxis(0, 10, 1);
//                final NumberAxis yxis = new NumberAxis(-100, 500, 100);
//                final ScatterChart<Number,Number> sc = new
//                        ScatterChart<Number,Number>(xxis,yxis);
//
//                sc.setTitle("Investment Overview");

//                for (Map.Entry<Integer,ArrayList<String>> entry : some.entrySet()){
//
//                    System.out.println(entry.getValue());
//                    System.out.println(" ");
//
////                    if(check){
////                        xAxis = entry.getValue();
////                        check = false;
////
////                    }else{
////                        yAxis = entry.getValue();
////                        check = true;
////                        //plot here then
////                           xxis.setLabel(xAxis.get(0));
////                           yxis.setLabel(yAxis.get(0)));
////                        for (int j = 1; j < xAxis.size(); j++) {
////                            series.getData().add(new XYChart.Data(xAxis.get(j), yAxis.get(j)));
////                        }
////                    }
//                }


//                sc.getData().addAll(series);
              //  Scene scene  = new Scene(sc, 500, 400);
              //  stage.setScene(scene);
               // stage.show();

       ////////////     }

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
        XYChart.Series series = new XYChart.Series();
        //Creating the Scatter chart

        final NumberAxis xxis = new NumberAxis(0, 10, 1);
        final NumberAxis yxis = new NumberAxis(-100, 500, 100);
        final ScatterChart<Number,Number> sc = new
                ScatterChart<Number,Number>(xxis,yxis);

        sc.setTitle("Investment Overview");


        for (Map.Entry<Integer,ArrayList<String>> entry : some.entrySet()){

 //           System.out.println(entry.getValue());
           // System.out.println(" ");
                    if(check){
                        xAxis = entry.getValue();
                        check = false;
//
                    }else{
                        yAxis = entry.getValue();
                        check = true;
                        //plot here then
                           xxis.setLabel(xAxis.get(0));
                           yxis.setLabel(yAxis.get(0));

                        for (int j = 1; j < xAxis.size(); j++) {

                            series.getData().add(new XYChart.Data(xAxis.get(j), yAxis.get(j)));
                        }


                        sc.getData().addAll(series);
                        Scene scene  = new Scene(sc, 500, 400);
                        stage.setScene(scene);
                        stage.show();
                    }
        }

    }



    public static void main(String[] args) throws IOException {
        launch(args);

    }
}


