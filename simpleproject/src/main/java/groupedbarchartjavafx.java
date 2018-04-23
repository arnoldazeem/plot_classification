/**
 * Created by adaboo on 4/23/18.
 */
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class groupedbarchartjavafx extends Application {
    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";

    @Override public void start(Stage stage) {
        stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
         BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
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


                for (Map.Entry<Integer,ArrayList<String>> entry : some.entrySet()){

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


                            series1.setName("2003");
                            series1.getData().add(new XYChart.Data(austria, a));
                            series1.getData().add(new XYChart.Data(brazil, b));
                            series1.getData().add(new XYChart.Data(france, c));
                            series1.getData().add(new XYChart.Data(italy, d));
                            series1.getData().add(new XYChart.Data(usa, e));


                            series2.setName("2004");
                            series2.getData().add(new XYChart.Data(austria, e));
                            series2.getData().add(new XYChart.Data(brazil, d));
                            series2.getData().add(new XYChart.Data(france, c));
                            series2.getData().add(new XYChart.Data(italy, b));
                            series2.getData().add(new XYChart.Data(usa, a));

                            series3.setName("2005");
                            series3.getData().add(new XYChart.Data(austria, f));
                            series3.getData().add(new XYChart.Data(brazil, b));
                            series3.getData().add(new XYChart.Data(france, d));
                            series3.getData().add(new XYChart.Data(italy, e));
                            series3.getData().add(new XYChart.Data(usa, c));


                        }

                        bc.getData().addAll(series1, series2, series3);

                        Scene scene  = new Scene(bc,640,480);


                        stage.setScene(scene);

                        stage.show();


                        saveAsPng(scene, aAxis.get(0) +"grouped" + ".png");

                        final CategoryAxis x1Axis = new CategoryAxis();
                        final NumberAxis y1Axis = new NumberAxis();

                        bc = new BarChart(x1Axis, y1Axis);

                        aAxis = bAxis;

                    }
                }

            }
        }

    }


    public void saveAsPng(Scene scene, String path) {
        WritableImage image = scene.snapshot(null);
        File file = new File(path);
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
