package com.flighstats.analytics.tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EntropyCalculatorTest {

    @Test
    public void testEntropy_50_50() throws Exception {
        EntropyCalculator testClass = new EntropyCalculator();
        Map<Object, Integer> positiveValues = new HashMap<>();
        positiveValues.put("sweet", 1);
        Map<Object, Integer> negativeValues = new HashMap<>();
        negativeValues.put("sweet", 0);

        List<LabeledItem> input = Arrays.asList(
                //first 3 don't match, so should be ignored
                new LabeledItem(new Item("one", negativeValues), true),
                new LabeledItem(new Item("one", negativeValues), true),
                new LabeledItem(new Item("one", negativeValues), true),

                new LabeledItem(new Item("one", positiveValues), true),
                new LabeledItem(new Item("one", positiveValues), true),
                new LabeledItem(new Item("one", positiveValues), true),
                new LabeledItem(new Item("one", positiveValues), false),
                new LabeledItem(new Item("one", positiveValues), false),
                new LabeledItem(new Item("one", positiveValues), false)
        );
        double entropy = testClass.entropy(input, "sweet", 1);
        assertEquals(1, entropy, 0.0001);
    }

    @Test
    public void testEntropy_allPositive() throws Exception {
        EntropyCalculator testClass = new EntropyCalculator();
        Map<Object, Integer> positiveValues = new HashMap<>();
        positiveValues.put("sweet", 1);
        Map<Object, Integer> negativeValues = new HashMap<>();
        negativeValues.put("sweet", 0);

        double entropy = testClass.entropy(Arrays.asList(
                        //first 3 don't match, so should be ignored
                        new LabeledItem(new Item("one", negativeValues), true),
                        new LabeledItem(new Item("one", negativeValues), true),
                        new LabeledItem(new Item("one", negativeValues), true),

                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true)
                ),
                "sweet", 1);
        assertEquals(0, entropy, 0.0001);
    }

    @Test
    public void testEntropy_allNegative() throws Exception {
        EntropyCalculator testClass = new EntropyCalculator();
        Map<Object, Integer> positiveValues = new HashMap<>();
        positiveValues.put("sweet", 1);
        Map<Object, Integer> negativeValues = new HashMap<>();
        negativeValues.put("sweet", 0);

        double entropy = testClass.entropy(Arrays.asList(
                        //first 3 don't match, so should be ignored
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),

                        new LabeledItem(new Item("one", negativeValues), false),
                        new LabeledItem(new Item("one", negativeValues), false),
                        new LabeledItem(new Item("one", negativeValues), false)
                ),
                "sweet", 0);
        assertEquals(0, entropy, 0.0001);
    }

    @Test
    public void testEntropy_9_5() throws Exception {
        EntropyCalculator testClass = new EntropyCalculator();
        Map<Object, Integer> negativeValues = new HashMap<>();
        negativeValues.put("sweet", 0);
        Map<Object, Integer> positiveValues = new HashMap<>();
        positiveValues.put("sweet", 1);

        double entropy = testClass.entropy(Arrays.asList(
                        //first 3 don't match, so should be ignored
                        new LabeledItem(new Item("one", negativeValues), true),
                        new LabeledItem(new Item("one", negativeValues), true),
                        new LabeledItem(new Item("one", negativeValues), true),

                        new LabeledItem(new Item("one", positiveValues), false),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), false),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), false),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), false),
                        new LabeledItem(new Item("one", positiveValues), false),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true)
                ),
                "sweet", 1);
        assertEquals(0.9403, entropy, 0.0001);
    }

    @Test
    public void testLabelEntropy_9_5() throws Exception {
        EntropyCalculator testClass = new EntropyCalculator();
        Map<Object, Integer> values = new HashMap<>();

        double entropy = testClass.labelEntropy(Arrays.asList(
                new LabeledItem(new Item("one", values), true),
                new LabeledItem(new Item("one", values), true),
                new LabeledItem(new Item("one", values), true),
                new LabeledItem(new Item("one", values), true),
                new LabeledItem(new Item("one", values), true),
                new LabeledItem(new Item("one", values), true),
                new LabeledItem(new Item("one", values), true),
                new LabeledItem(new Item("one", values), true),
                new LabeledItem(new Item("one", values), true),
                new LabeledItem(new Item("one", values), false),
                new LabeledItem(new Item("one", values), false),
                new LabeledItem(new Item("one", values), false),
                new LabeledItem(new Item("one", values), false),
                new LabeledItem(new Item("one", values), false)
        ));
        assertEquals(0.9403, entropy, 0.0001);
    }


    @Test
    public void testEntropyGain_9_5() throws Exception {
        EntropyCalculator testClass = new EntropyCalculator();
        Map<Object, Integer> negativeValues = new HashMap<>();
        negativeValues.put("sweet", 0);
        Map<Object, Integer> positiveValues = new HashMap<>();
        positiveValues.put("sweet", 1);

        double gain = testClass.entropyGain(Arrays.asList(
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", positiveValues), true),
                        new LabeledItem(new Item("one", negativeValues), true),
                        new LabeledItem(new Item("one", negativeValues), true),
                        new LabeledItem(new Item("one", negativeValues), true),

                        new LabeledItem(new Item("one", positiveValues), false),
                        new LabeledItem(new Item("one", positiveValues), false),
                        new LabeledItem(new Item("one", negativeValues), false),
                        new LabeledItem(new Item("one", negativeValues), false),
                        new LabeledItem(new Item("one", negativeValues), false)
                ),
                "sweet");
        assertEquals(0.0481, gain, 0.0001);
    }


}