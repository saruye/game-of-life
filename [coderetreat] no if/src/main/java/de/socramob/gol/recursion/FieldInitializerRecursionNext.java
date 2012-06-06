package de.socramob.gol.recursion;

import java.util.Map;
import java.util.Set;

import de.socramob.gol.WorldDimension;

public class FieldInitializerRecursionNext implements FieldInitializerRecursion {

    @Override
    public FieldInitializerRecursion initialize(int xCoordinate, int step, int upperBound,
            Set<WorldDimension> dimensionList, Map<Boolean, FieldInitializerRecursion> ruleMap) {

        dimensionList.add(new WorldDimension(xCoordinate, step));

        step++;
        FieldInitializerRecursion nextStep = ruleMap.get(step <= upperBound);

        return nextStep.initialize(xCoordinate, step, upperBound, dimensionList, ruleMap);
    }
}
