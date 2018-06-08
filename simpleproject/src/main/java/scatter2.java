/**
 * Created by adaboo on 4/23/18.
 *//*

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class scatter2 extends Application {
    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";

    @Override public void start(Stage stage) {
        stage.setTitle("Bar Chart Sample");

        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-100, 500, 100);

         ScatterChart<Number,Number> bc = new
                ScatterChart<Number,Number>(xAxis,yAxis);


        bc.setTitle("Country Summary");
        xAxis.setLabel("Country");
        yAxis.setLabel("Value");

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
                List<String> aAxis = new ArrayList<String>();
                List<String> bAxis = new ArrayList<String>();
                //Prepare XYChart.Series objects by setting data


                for (Map.Entry<Integer, ArrayList<String>> entry : some.entrySet()){

                    if(check){
                        aAxis = entry.getValue();
                        check = false;

                    }else{

                        final DefaultCategoryDataset dataset =
                                new DefaultCategoryDataset( );

                        XYChart.Series series1 = new XYChart.Series();

                        XYChart.Series series2 = new XYChart.Series();

                        XYChart.Series series3 = new XYChart.Series();

                        bAxis = entry.getValue();
                        //clear the series everytime
                        //xAxis.size()



                        for (int j = 1; j < 3; j++) {

                            String trim = aAxis.get(j).trim();
                            String trim2 = bAxis.get(j).trim();

                            String trim3 = aAxis.get(j+2).trim();
                            String trim4 = bAxis.get(j+2).trim();

                            String trim5 = aAxis.get(j+3).trim();
                            String trim6 = bAxis.get(j+3).trim();

                            Double a = Double.parseDouble(trim);
                            Double b = Double.parseDouble(trim2);

                            Double c = Double.parseDouble(trim3);
                            Double d = Double.parseDouble(trim4);

                            Double e = Double.parseDouble(trim5);
                            Double f = Double.parseDouble(trim6);


                            series1.setName(aAxis.get(0));
                            series1.getData().add(new XYChart.Data(a, b));
                            series1.getData().add(new XYChart.Data(c, d));
                            series1.getData().add(new XYChart.Data(e, f));


                            series2.setName(bAxis.get(0));
                            series2.getData().add(new XYChart.Data(e, f));
                            series2.getData().add(new XYChart.Data(c, d));
                            series2.getData().add(new XYChart.Data(b, c));



                            series3.setName("dummy3");
                            series3.getData().add(new XYChart.Data(c, a));
                            series3.getData().add(new XYChart.Data(b, f));
                            series3.getData().add(new XYChart.Data(e, d));


                        }

                        Scene scene  = new Scene(bc,640,480);
                        bc.setAnimated(false);
                        bc.getData().addAll(series1, series2);
                        saveAsPng(scene, aAxis.get(0) +"scatterlast" + ".png");
                        stage.setScene(scene);
//                        stage.show();

                        final NumberAxis x1Axis = new NumberAxis();
                        final NumberAxis y1Axis = new NumberAxis();

                        bc =
                                new ScatterChart<Number,Number>(x1Axis,y1Axis);

                        bc.setTitle("Dummy");
                        x1Axis.setLabel(aAxis.get(0));
                        y1Axis.setLabel(bAxis.get(0));


                        aAxis = bAxis;
                    }

                }
            }
        }

    }


    public void saveAsPng(Scene scene, String path) {
        WritableImage image = scene.snapshot(null);
        File file = new File("output/"+path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
*/
