import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;
/**
 * Creates a simple Chart using QuickChart
 *
 */

/**
 * Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2011-2015 Xeiam LLC (http://xeiam.com) and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.knowm.xchart.demo.charts.bar;

import java.util.Arrays;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;

/**
 * Basic Bar Chart
 * <p>
 * Demonstrates the following:
 * <ul>
 * <li>Integer categories as List
 * <li>All positive values
 * <li>Single series
 * <li>Place legend at Inside-NW position
 * <li>Bar Chart Annotations
 */
public class BarChart01 implements ExampleChart<CategoryChart> {

    public static  void main(String[] args) {

        ExampleChart<CategoryChart> exampleChart = new BarChart01();
        CategoryChart chart = exampleChart.getChart();
        new SwingWrapper<CategoryChart>(chart).displayChart();
    }

    @Override
    public  CategoryChart getChart() {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Score Histogram").xAxisTitle("Score").yAxisTitle("Number").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setPlotGridLinesVisible(false);

        // Series
        chart.addSeries("test 1", Arrays.asList(0, 1, 2, 3, 4), Arrays.asList(4, 5, 9, 6, 5));

        return chart;
    }
}
















public class library  {

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


                //BitmapEncoder.saveBitmap(chart, xAxis + "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);

                xAxis = yAxis;

            }

        }

    }
}