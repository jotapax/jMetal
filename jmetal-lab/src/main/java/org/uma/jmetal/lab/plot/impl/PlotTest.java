package org.uma.jmetal.lab.plot.impl;

import org.uma.jmetal.lab.plot.PlotFront;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;

import java.util.ArrayList;
import java.util.List;

public class PlotTest {

    public static void main(String[] args) {
        /*
        double[] x = {1, 2, 3, 4, 5, 6};
        double[] y = {0, 1, 6, 14, 25, 39};
        String[] labels = {"a", "b", "c", "d", "e", "f"};

        double[] xFront = {1, 2, 3, 4, 5, 6};
        double[] yFront = {0, 0, 2, 5, 15, 20};

        ScatterTrace trace = ScatterTrace.builder(x, y)
                .text(labels)
                .build();

        ScatterTrace front = ScatterTrace.builder(xFront, yFront)
                .mode(ScatterTrace.Mode.LINE)
                .text(labels)
                .build();

        Plot.show(new Figure(trace, front));

         */

        double[][] xy = {{1,0}, {2, 1}, {3, 6}, {4, 14}, {5,25}, {6,39}};
        double[][] pareto = {{1,0}, {2, 0}, {3, 2}, {4, 5}, {5,15}, {6,20}};
        double[][] referencePoints = {{2, 7}, {5, 10}};

        Plot2D plot = new Plot2D(xy);
        plot.addParetoFront("resources/referenceFronts/ZDT1.pf");
        plot.addReferencePoint(referencePoints);
        plot.plot();

        System.exit(0);
    }

}
