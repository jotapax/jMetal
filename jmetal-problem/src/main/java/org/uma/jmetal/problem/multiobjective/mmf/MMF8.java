package org.uma.jmetal.problem.multiobjective.mmf;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import java.util.ArrayList;
import java.util.List;

public class MMF8 extends AbstractDoubleProblem {

    public MMF8() {
        setName("MMF8");
        setNumberOfVariables(2);
        setNumberOfObjectives(2);

        List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
        lowerLimit.add(-Math.PI);
        lowerLimit.add(0.0);

        List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());
        upperLimit.add(Math.PI);
        upperLimit.add(9.0);

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

        return Math.sin(Math.abs(x1));
    }

    private double function2(double x1, double x2) {

        double firstPart = Math.sqrt(1.0 - Math.pow( Math.sin( Math.abs(x1)), 2.0));

        double secondPart = 0.0;
        if (0 <= x2 && x2 <= 4) {
            secondPart = Math.pow( x2 - Math.sin( Math.abs(x1)) - Math.abs(x1), 2.0);
        } else if (4 < x2 && x2 <= 9) {
            secondPart = Math.pow( x2 - 4 - Math.sin( Math.abs(x1)) - Math.abs(x1), 2.0);
        }

        return firstPart + 2.0 * secondPart;
    }
}