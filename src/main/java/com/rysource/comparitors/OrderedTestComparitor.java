package com.rysource.comparitors;

import com.rysource.annotations.TestInformation;
import org.junit.runners.model.FrameworkMethod;

import java.util.Comparator;

public class OrderedTestComparitor implements Comparator<FrameworkMethod> {
    @Override
    public int compare(FrameworkMethod o1, FrameworkMethod o2) {
        int firstOrder = Integer.MAX_VALUE;
        int secondOrder = Integer.MAX_VALUE;

        TestInformation testInformationOne = o1.getAnnotation(TestInformation.class);
        TestInformation testInformationTwo = o2.getAnnotation(TestInformation.class);

        if (testInformationOne != null) {
            firstOrder = testInformationOne.testRunOrder();
        }

        if (testInformationTwo != null) {
            secondOrder = testInformationTwo.testRunOrder();
        }

        return firstOrder - secondOrder;
    }
}
