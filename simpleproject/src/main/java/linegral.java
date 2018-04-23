import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.LinearGradientPaint;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.BarPlot;
import de.erichseifert.gral.plots.BarPlot.BarRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.GraphicsUtils;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Location;


public class linegral extends ExamplePanel {
    /** Version id for serialization. */
    private static final long serialVersionUID = -2793954497895054530L;

    @SuppressWarnings("unchecked")
    public linegral() {

    }

    @Override
    public String getTitle() {
        return "Bar plot";
    }

    @Override
    public String getDescription() {
        return "Bar plot with example data and color gradients";
    }

    public static void main(String[] args) {
        new linegral().showInFrame();


    }
}