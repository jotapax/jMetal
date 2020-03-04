package org.uma.jmetal.problem.multiobjective.mmf;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import java.util.ArrayList;
import java.util.List;

public class MMF1e extends AbstractDoubleProblem {

    private static final int A = 3;

    public MMF1e() {
        setName("MMF1e");
        setNumberOfVariables(2);
        setNumberOfObjectives(2);

        List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
        lowerLimit.add(1.0);
        lowerLimit.add(-Math.pow(A, 3));

        List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());
        upperLimit.add(3.0);
        upperLimit.add(Math.pow(A, 3));

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
        return Math.abs(x1 - 2);
    }

    private double function2(double x1, double x2) {

        double firstPart = Math.sqrt(Math.abs(x1 - 2.0));
        double secondPart = 0;
        if (1 <= x1 && x1 < 2) {
            secondPart = Math.pow(x2 - Math.sin(6.0 * Math.PI * Math.abs(x1 - 2.0) + Math.PI), 2.0);
        } else if (2 <= x1 && x1 < 3) {
            secondPart = Math.pow(x2 - Math.pow(A, x1) * Math.sin(6.0 * Math.PI * Math.abs(x1 - 2.0) + Math.PI), 2.0);
        }

        return 1.0 - firstPart + 2.0 * secondPart;
    }
}
