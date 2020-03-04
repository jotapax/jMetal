package org.uma.jmetal.problem.multiobjective.mmf;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import java.util.ArrayList;
import java.util.List;

public class MMF2 extends AbstractDoubleProblem {

    public MMF2() {
        setName("MMF2");
        setNumberOfVariables(2);
        setNumberOfObjectives(2);

        List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
        lowerLimit.add(0.0);
        lowerLimit.add(0.0);

        List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());
        upperLimit.add(1.0);
        upperLimit.add(2.0);

        setVariableBounds(lowerLimit, upperLimit);
    }

    @Override
    public void evaluate(DoubleSolution solution) {

        double[] f = new double[getNumberOfObjectives()];

        f[0] = function1(solution.getVariable(0));
        f[1] = function2(solution.getVariable(0), solution.getVariable(1));

        solution.setObjective(0, f[0]);
        solution.setObjective(1, f[1]);
    }

    private double function1(double x1) {
        return x1;
    }

    private double function2(double x1, double x2) {

        double firstPart = Math.sqrt(x1);
        double secondPart = 0;
        if (1 <= x1 && x1 < 2) {
            secondPart = 4 * Math.pow(x2 - Math.sqrt(x1), 2.0) - 2*Math.cos( (20*(x2 - Math.sqrt(x1))*Math.PI ) / Math.sqrt(2) ) + 2;
        } else if (2 <= x1 && x1 < 3) {
            secondPart = 4 * Math.pow(x2 - 1 - Math.sqrt(x1), 2.0) - 2*Math.cos( (20*(x2 - 1 - Math.sqrt(x1))*Math.PI ) / Math.sqrt(2) ) + 2;
        }

        return 1.0 - firstPart + 2.0 * secondPart;
    }
}
