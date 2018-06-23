import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
import smile.plot.BoxPlot;
import smile.plot.PlotCanvas;

/**
 *
 * @author Haifeng Li
 */
@SuppressWarnings("serial")
public class boxplot_new extends JPanel {


    public static void main(String[] args) {

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

                        double[][] data = new double[3][33];

                        yAxis = entry.getValue();
                        //clear the series everytime
                        //xAxis.size()

                            for (int i = 0; i < data.length; i++) {

                                for (int j = 1; j < data[i].length; j++) {

                                    String trim = yAxis.get(j).trim();
                                    Double a = Double.parseDouble(trim);

                                    data[i][j] = new Double(a);
                                }
                            }


                        JFrame frame = new JFrame("Box Plot");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setLocationRelativeTo(null);

                        PlotCanvas canvas = BoxPlot.plot(data, new String[] { yAxis.get(0), "Dummy C","Dummy D"});
                        canvas.setTitle("Box Plot " + yAxis.get(0));
                        canvas.getAxis(0).setRotation(-Math.PI / 3);
                        frame.getContentPane().add(canvas);
                        frame.setSize(700,700);
                        frame.setVisible(true);

                        try
                        {
                            BufferedImage image = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
                            Graphics2D graphics2D = image.createGraphics();
                            frame.paint(graphics2D);
                            ImageIO.write(image,"jpg", new File( xAxis.get(0) +"FFimle2" + ".jpg"));
                        }
                        catch(Exception exception)
                        {
                            //code
                        }

                    }

                        xAxis = yAxis;


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










