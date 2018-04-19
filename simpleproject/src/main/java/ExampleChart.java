/**
 * Created by adaboo on 4/19/18.
 */

import org.knowm.xchart.internal.chartpart.Chart;

public interface ExampleChart<C extends Chart<?, ?>> {

    C getChart();
}