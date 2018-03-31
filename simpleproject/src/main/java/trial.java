import javax.swing.JFrame;

import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class trial extends JFrame {

    private static final long serialVersionUID = 1L;



    public trial(String applicationTitle, String chartTitle) {
        super(applicationTitle);

        // based on the dataset we create the chart
        JFreeChart pieChart = ChartFactory.createXYLineChart(chartTitle, "Category", "Score", createDataset(),PlotOrientation.VERTICAL, true, true, false);

        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(pieChart);

        // settind default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        // add to contentPane
        setContentPane(chartPanel);
    }




    private XYDataset createDataset() {

        String fileName = "3.csv";
        Map<Integer,ArrayList<String>> some= null;

        final XYSeriesCollection dataset = new XYSeriesCollection();

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

        for (Map.Entry<Integer,ArrayList<String>> entry : some.entrySet()){

            ArrayList<String> s = (ArrayList<String>)entry.getValue();

            String pattern = "[^\\d]";

            try{

                Float.parseFloat(s.get(1));

            }catch (Exception e){

                some.remove(entry.getKey());
            }

        }


    } catch (IOException e) {
        System.err.println("Caught IOException: " + e.getMessage());
    }


    Boolean check = true;
    List<String> xAxis = new ArrayList<String>();
    List<String> yAxis = new ArrayList<String>();
    //Prepare XYChart.Series objects by setting data

    final XYSeries series = new XYSeries("Firefox");
    //Creating the Scatter chart

    final NumberAxis xxis = new NumberAxis(0, 100, 10);
    final NumberAxis yxis = new NumberAxis(0, 100, 10);
    final ScatterChart<Number,Number> sc = new
            ScatterChart<Number,Number>(xxis,yxis);
        sc.setTitle("Investment Overview");




        return dataset;

    }


    public static void main(String[] args) {
        trial chart = new trial("Browser Usage Statistics", "Which Browser are you using?");
        chart.pack();
        chart.setVisible(true);
    }
}

