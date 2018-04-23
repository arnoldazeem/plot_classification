import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.io.*;
import org.jfree.data.category.DefaultCategoryDataset; /* We will use this dataset class to populate data for our bar chart */
import org.jfree.chart.ChartFactory; /* used to create a chart object */
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities; /* We will use this class to convert the chart to a PNG image file */


public class barjfree {
       public static void main(String[] args){
                try {

                            /* Step - 1: Define the data for the bar chart  */
                            DefaultCategoryDataset my_bar_chart_dataset = new DefaultCategoryDataset();
                            my_bar_chart_dataset.addValue(34, "Q1", "Rome");
                            my_bar_chart_dataset.addValue(45, "Q1", "Cairo");
                            my_bar_chart_dataset.addValue(22, "Q2", "Rome");
                            my_bar_chart_dataset.addValue(12, "Q2", "Cairo");
                            my_bar_chart_dataset.addValue(56, "Q3", "Rome");
                            my_bar_chart_dataset.addValue(98, "Q3", "Cairo");
                            my_bar_chart_dataset.addValue(2, "Q4", "Rome");
                            my_bar_chart_dataset.addValue(15, "Q4", "Cairo");

                            /* Step -2:Define the JFreeChart object to create bar chart */
                            JFreeChart BarChartObject=ChartFactory.createBarChart("CountryVsSales - Bar Chart","Country",
                                    "Sales",my_bar_chart_dataset,PlotOrientation.VERTICAL,true,true,false);

                             /* Step -3: Write the output as PNG file with bar chart information */
                             int width=640; /* Width of the image */
                             int height=480; /* Height of the image */
                             File BarChart=new File("output_chart.png");
                             ChartUtilities.saveChartAsPNG(BarChart,BarChartObject,width,height);
                     }
                 catch (Exception i)
                 {
                     System.out.println(i);
                 }
             }
 }