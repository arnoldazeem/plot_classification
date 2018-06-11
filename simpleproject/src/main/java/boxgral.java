/**
 * Created by adaboo on 4/22/18.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import de.erichseifert.gral.graphics.*;
import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Label;
import de.erichseifert.gral.io.plots.DrawableWriter;
import de.erichseifert.gral.io.plots.DrawableWriterFactory;
import de.erichseifert.gral.plots.BoxPlot;
import de.erichseifert.gral.plots.BoxPlot.BoxWhiskerRenderer;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.XYPlot.XYNavigationDirection;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.axes.LinearRenderer2D;
import de.erichseifert.gral.plots.axes.LogarithmicRenderer2D;
import de.erichseifert.gral.plots.colors.LinearGradient;
import de.erichseifert.gral.plots.colors.ScaledContinuousColorMapper;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.DataUtils;
import de.erichseifert.gral.util.GraphicsUtils;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.io.IOUtils;

import javax.swing.*;


public class boxgral extends ExamplePanel {
    /** Version id for serialization. */
    private static final long serialVersionUID = 5228891435595348789L;
    private static final int SAMPLE_COUNT = 50;
    private static final Random random = new Random();


    @SuppressWarnings("unchecked")
    public boxgral() {
        setPreferredSize(new Dimension(800, 600));

        // Create example data
        DataTable data = new DataTable(Float.class, Float.class, Float.class,Float.class,Float.class,Float.class);

        File dir = new File("/home/adaboo/Desktop/Masters/sem4/thesis/plot_classification/simpleproject/java data");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with child

                Path path = Paths.get(child.toString());

                String  keep = path.getFileName().toString();

                String titl = keep.substring(0, keep.lastIndexOf('.'));

                System.out.println(titl);

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

                        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
                        yAxis = entry.getValue();
                        //clear the series everytime
                        //xAxis.size()
                        for (int j = 1; j < 10 ; j++) {

                            String trim = xAxis.get(j).trim();
                            String trim2 = yAxis.get(j).trim();

                            String trim3 = xAxis.get(j+2).trim();
                            String trim4 = yAxis.get(j+2).trim();

                            String trim5 = xAxis.get(j+3).trim();
                            String trim6 = yAxis.get(j+3).trim();

                            float a = Float.parseFloat(trim);
                            float b = Float.parseFloat(trim2);

                            float c = Float.parseFloat(trim3);
                            float d = Float.parseFloat(trim4);

                            float e = Float.parseFloat(trim5);
                            float f = Float.parseFloat(trim6);

                            data.add(a,b,c,d,e,f);

                        }


                        // Create new box-and-whisker plot
                        DataSource boxData = BoxPlot.createBoxData(data);
                        BoxPlot plot = new BoxPlot(boxData);

                        // Format plot
                        plot.setInsets(new Insets2D.Double(20.0, 60.0, 60.0, 40.0));

                        plot.setBackground(Color.WHITE);


                        // Format axes
                        AxisRenderer axisRendererX = new LinearRenderer2D();
                        AxisRenderer axisRendererY = plot.getAxisRenderer(XYPlot.AXIS_Y);
                        axisRendererX.setLabel(new Label(yAxis.get(0)));

                        plot.setAxisRenderer(XYPlot.AXIS_X, axisRendererX);



                        Label linearAxisLabel = new Label(xAxis.get(0));
                        linearAxisLabel.setRotation(90);
                        axisRendererY.setLabel(linearAxisLabel);

                         //xAxis.get(0)

                        plot.getTitle().setText("A three boxplot showing data from " + titl);

                        // Format axes
                        plot.getAxisRenderer(BoxPlot.AXIS_X).setCustomTicks(
                                DataUtils.map(
                                        new Double[] {1.0, 2.0, 3.0, 4.0,5.0,6.0},
                                        new String[] {"Column 1", "Column 2", "Column 3","Column 4","Column 5","Column 6"}
                                )
                        );

                        // Format boxes
                        Stroke stroke = new BasicStroke(2f);
                        ScaledContinuousColorMapper colors =
                                new LinearGradient(GraphicsUtils.deriveBrighter(COLOR1), Color.WHITE);
                        colors.setRange(1.0, 3.0);

                        BoxWhiskerRenderer pointRenderer =
                                (BoxWhiskerRenderer) plot.getPointRenderers(boxData).get(0);
                        pointRenderer.setWhiskerStroke(stroke);
                        pointRenderer.setBoxBorderStroke(stroke);
                        pointRenderer.setBoxBackground(colors);
                        pointRenderer.setBoxBorderColor(COLOR1);
                        pointRenderer.setWhiskerColor(COLOR1);
                        pointRenderer.setCenterBarColor(COLOR1);


                        //plot.setLegendVisible(true);

                        plot.getNavigator().setDirection(XYNavigationDirection.VERTICAL);


                        // Add plot to Swing component
                       // InteractivePanel panel = new InteractivePanel(plot);

                       // add(panel);
                        try{

                            String mimeType = "image/png";

                            DrawableWriter writer = DrawableWriterFactory.getInstance().get(mimeType);

                            File file = new File(xAxis.get(0) +"legend" + ".png");

                            FileOutputStream outStream = null;
                            try {
                                outStream = new FileOutputStream((file));
                                plot.setBounds(10, 10, 790, 590);
                                writer.write(plot, outStream, 800, 600);
                            } finally {
                                outStream.flush();
                                outStream.close();
                            }

                        }catch(Exception e){
                            e.printStackTrace();
                        }

                        xAxis = yAxis;


                    }

                }


            }

        }else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }


    }



    @Override
    public String getTitle() {
        return "Box-and-whisker plot";
    }

    @Override
    public String getDescription() {
        return String.format("Three box-and-whisker plots created from %d random samples", SAMPLE_COUNT);
    }

    public static void main(String[] args) {

        new boxgral().showInFrame();
    }
}

