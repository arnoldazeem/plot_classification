import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;

public class stackedbarchart extends Application {

    final CategoryAxis xxis = new CategoryAxis();
    final NumberAxis yxis = new NumberAxis();


    StackedBarChart<String, Number> sbc =
            new StackedBarChart<String, Number>(xxis, yxis);


    @Override
    public void start(Stage stage) {



        stage.setTitle("Bar Chart Sample");
        sbc.setTitle("Country Summary");
        xxis.setLabel("Country");
        yxis.setLabel("Value");

        xxis.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList("1", "2", "3")));


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


                for (Map.Entry<Integer,ArrayList<String>> entry : some.entrySet()){

                    if(check){
                        xAxis = entry.getValue();
                        check = false;

                    }else{

                        final DefaultCategoryDataset dataset =
                                new DefaultCategoryDataset( );

                        XYChart.Series series1 = new XYChart.Series();

                        XYChart.Series series2 = new XYChart.Series();

                        XYChart.Series series3 = new XYChart.Series();

                        yAxis = entry.getValue();
                        //clear the series everytime
                        //xAxis.size()
                        for (int j = 1; j < 3; j++) {

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


                            series1.setName("first");
                            series1.getData().add(new XYChart.Data<String, Number>("1", a));
                            series1.getData().add(new XYChart.Data<String, Number>("1", b));
                            series1.getData().add(new XYChart.Data<String, Number>("1",c));


                            series2.setName("second");
                            series2.getData().add(new XYChart.Data<String, Number>("2",d));
                            series2.getData().add(new XYChart.Data<String, Number>("2",e));
                            series2.getData().add(new XYChart.Data<String, Number>("2",f));


                            series3.setName("third");
                            series3.getData().add(new XYChart.Data<String, Number>("3", a));
                            series3.getData().add(new XYChart.Data<String, Number>("3", d));
                            series3.getData().add(new XYChart.Data<String, Number>("3",c));

                        }


                        sbc.getData().addAll(series1, series2, series3);

                        Scene scene = new Scene(sbc, 640, 480);

                        stage.setScene(scene);
                        saveAsPng(scene, xAxis.get(0) +"stacked" + ".png");
                        stage.show();

                        final CategoryAxis x1Axis = new CategoryAxis();
                        final NumberAxis y1Axis = new NumberAxis();

                        sbc = new StackedBarChart(x1Axis, y1Axis);

                        xAxis = yAxis;


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