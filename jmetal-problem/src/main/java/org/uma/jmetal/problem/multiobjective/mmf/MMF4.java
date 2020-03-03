package org.uma.jmetal.problem.multiobjective.mmf;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import java.util.ArrayList;
import java.util.List;

public class MMF4 extends AbstractDoubleProblem {

    public MMF4() {
        setName("MMF4");
        setNumberOfVariables(2);
        setNumberOfObjectives(2);

        List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
        lowerLimit.add(-1.0);
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
        return Math.abs(x1);
    }

    private double function2(double x1, double x2) {

        double firstPart = Math.pow(x1, 2.0);

        double secondPart = 0.0;
        if (0 <= x2 && x2 < 1) {
            secondPart = Math.pow( x2 - Math.sin( Math.PI * Math.abs(x1) ), 2.0);
        } else if (1 <= x2 && x2 <= 2) {
            secondPart = Math.pow( x2 - 1 - Math.sin( Math.PI * Math.abs(x1) ), 2.0);
        }

        return 1.0 - firstPart + 2.0 * secondPart;
    }
}