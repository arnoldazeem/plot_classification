import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Log;
import org.jfree.util.LogContext;



public class BoxPlot extends ApplicationFrame {


    public BoxPlot(final String title) {

        super(title);

        final BoxAndWhiskerCategoryDataset dataset = createSampleDataset();

        final CategoryAxis xAxis = new CategoryAxis("Type");
        final NumberAxis yAxis = new NumberAxis("Value");
        yAxis.setAutoRangeIncludesZero(false);

        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(true);
        renderer.setSeriesPaint(0, Color.WHITE);
        renderer.setSeriesPaint(1, Color.LIGHT_GRAY);
        renderer.setSeriesOutlinePaint(0, Color.BLACK);
        renderer.setSeriesOutlinePaint(1, Color.BLACK);
        renderer.setMeanVisible(false);

        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

        final JFreeChart chart = new JFreeChart(
                "Box-and-Whisker Demo",
                new Font("SansSerif", Font.BOLD, 14),
                plot,
                true
        );
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(480, 640));
        setContentPane(chartPanel);

    }




    private BoxAndWhiskerCategoryDataset createSampleDataset() {

        final int seriesCount = 3;
        final int categoryCount = 4;
        final int entityCount = 22;

        final DefaultBoxAndWhiskerCategoryDataset dataset
                = new DefaultBoxAndWhiskerCategoryDataset();
        for (int i = 0; i < seriesCount; i++) {
            for (int j = 0; j < categoryCount; j++) {
                final List list = new ArrayList();
                // add some values...
                for (int k = 0; k < entityCount; k++) {
                    final double value1 = 10.0 + Math.random() * 3;
                    list.add(new Double(value1));
                    final double value2 = 11.25 + Math.random(); // concentrate values in the middle
                    list.add(new Double(value2));
                }
               // LOGGER.debug("Adding series " + i);
                //LOGGER.debug(list.toString());
                dataset.add(list, "Series " + i, " Type " + j);
            }

        }

        return dataset;
    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    *
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************

    public static void main(final String[] args) {

        //Log.getInstance().addTarget(new PrintStreamLogTarget(System.out));
        final BoxPlot demo = new BoxPlot("Box-and-Whisker Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}






