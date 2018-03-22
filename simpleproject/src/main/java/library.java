import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by adaboo on 3/14/18.
 */
    public class library extends JFrame {
        private static final long serialVersionUID = 6294689542092367723L;

        public library(String title) {
            super(title);

            // Create dataset
            XYDataset dataset = createDataset();

            JFreeChart chart = ChartFactory.createScatterPlot("look","AT","the", dataset, PlotOrientation.HORIZONTAL,
                    false, // legend
                    false, // tool tips
                    false // URLs
            );



            XYPlot plot = (XYPlot)chart.getPlot();
            plot.setBackgroundPaint(new Color(255,228,196));


            // Create Panel
            ChartPanel panel = new ChartPanel(chart);
            setContentPane(panel);
        }



    private XYDataset createDataset() {
            XYSeriesCollection dataset = new XYSeriesCollection();

        String fileName = "3.csv";
        Map<Integer,ArrayList<String>> some= null;

        try {


            final BufferedReader input =  new BufferedReader(new FileReader(fileName));

            String line = null;
            int cnt = 0;
            String[] topRow = new String[0];
            int i=0;
            java.util.List<String[]> templist = new ArrayList<>();

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
        java.util.List<String> xAxis = new ArrayList<String>();
        java.util.List<String> yAxis = new ArrayList<String>();
        //Prepare XYChart.Series objects by setting data

        //Creating the Scatter chart

        XYSeries series = new XYSeries("Boys");



        for (Map.Entry<Integer,ArrayList<String>> entry : some.entrySet()){
            if(check){
                xAxis = entry.getValue();
                check = false;
            }else{
                yAxis = entry.getValue();
                check = true;
                //plot here then
               // xxis.setLabel(xAxis.get(0));
               // yxis.setLabel(yAxis.get(0));
                //xAxis.size()
                for (int j = 1; j < 20 ; j++) {
                    String trim = xAxis.get(j).trim();
                    String trim2 = yAxis.get(j).trim();
                    float a = Float.parseFloat(trim);
                    float b = Float.parseFloat(trim2);
                    //series.getData().add(new XYChart.Data(a , b));

                    series.add(a,b);

                }


            }
        }

            //Boys (Age,weight) series
            XYSeries series1 = new XYSeries("Boys");
            series1.add(1, 72.9);
            series1.add(2, 81.6);

            //Girls (Age,weight) series
            XYSeries series2 = new XYSeries("Girls");
            series2.add(1, 72.5);

            dataset.addSeries(series);

            return dataset;
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                library example = new library("Scatter Chart Example | BORAJI.COM");
                example.setSize(800, 400);
                example.setLocationRelativeTo(null);
                example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                example.setVisible(true);
            });
        }
    }


