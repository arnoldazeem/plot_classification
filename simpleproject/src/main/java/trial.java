import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by adaboo on 3/9/18.
 */
public class trial {

    public static void main(String[] args) throws Exception {

        double[] yData = new double[] { 2.0, 1.0, 0.0 };

        // Create Chart
        XYChart chart = new XYChart(500, 400);
        chart.setTitle("Sample Chart");
        chart.setXAxisTitle("X");
        chart.setXAxisTitle("Y");

        XYSeries series = chart.addSeries("y(x)", null, yData);
        series.setMarker(SeriesMarkers.CIRCLE);

        BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);
        //BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.JPG);
       // BitmapEncoder.saveJPGWithQuality(chart, "./Sample_Chart_With_Quality.jpg", 0.95f);
       // BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.BMP);
       // BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.GIF);

       // BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapEncoder.BitmapFormat.PNG, 300);
       // BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapEncoder.BitmapFormat.JPG, 300);
      //  BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapEncoder.BitmapFormat.GIF, 300);

      //  VectorGraphicsEncoder.saveVectorGraphic(chart, "./Sample_Chart", VectorGraphicsEncoder.VectorGraphicsFormat.EPS);
      //  VectorGraphicsEncoder.saveVectorGraphic(chart, "./Sample_Chart", VectorGraphicsEncoder.VectorGraphicsFormat.PDF);
      //  VectorGraphicsEncoder.saveVectorGraphic(chart, "./Sample_Chart", VectorGraphicsEncoder.VectorGraphicsFormat.SVG);

    }
}
