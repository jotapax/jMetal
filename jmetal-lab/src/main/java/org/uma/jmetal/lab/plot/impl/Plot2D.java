package org.uma.jmetal.lab.plot.impl;

import org.uma.jmetal.lab.plot.PlotFront;
import org.uma.jmetal.util.checking.Check;
import org.uma.jmetal.util.front.imp.ArrayFront;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;

import java.io.FileNotFoundException;

public class Plot2D implements PlotFront {

  private Figure.FigureBuilder figureBuilder;

  public Plot2D(double[][] matrix) {

    Check.isNotNull(matrix);
    Check.that(matrix.length >= 1, "The data matrix is empty");
    Check.that(matrix[0].length == 2, "The data matrix does not have two columns");

    int numberOfRows = matrix.length;
    double[] f1 = new double[numberOfRows];
    double[] f2 = new double[numberOfRows];

    for (int i = 0; i < numberOfRows; i++) {
      f1[i] = matrix[i][0];
      f2[i] = matrix[i][1];
    }

    ScatterTrace trace = ScatterTrace.builder(f1, f2).build();

    figureBuilder = new Figure.FigureBuilder();

    figureBuilder.addTraces(trace);
  }

  public void addParetoFront(String paretoFileName) {

    double[][] paretoFront = new double[0][];
    try {
      paretoFront = new ArrayFront(paretoFileName).getMatrix();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }

    int numberOfRows = paretoFront.length;
    double[] f1Pareto = new double[numberOfRows];
    double[] f2Pareto = new double[numberOfRows];

    for (int i = 0; i < numberOfRows; i++) {
      f1Pareto[i] = paretoFront[i][0];
      f2Pareto[i] = paretoFront[i][1];
    }

    ScatterTrace pareto = ScatterTrace.builder(f1Pareto, f2Pareto).mode(ScatterTrace.Mode.LINE).build();

    figureBuilder.addTraces(pareto);
  }

  public void addReferencePoint(double[][] referencePoints) {

    Check.isNotNull(referencePoints);
    Check.that(referencePoints.length >= 1, "The data matrix is empty");

    int numberOfRows = referencePoints.length;
    double[] f1 = new double[numberOfRows];
    double[] f2 = new double[numberOfRows];

    for (int i = 0; i < numberOfRows; i++) {
      double[] point = referencePoints[i];
      Check.that(point.length == 2, "The reference point " + i + " does not have 2 coordinates");

      f1[i] = point[0];
      f2[i] = point[1];
    }

    ScatterTrace references = ScatterTrace.builder(f1, f2).build();

    figureBuilder.addTraces(references);
  }

  @Override
  public void plot() {

    Plot.show(figureBuilder.build());
  }


}
