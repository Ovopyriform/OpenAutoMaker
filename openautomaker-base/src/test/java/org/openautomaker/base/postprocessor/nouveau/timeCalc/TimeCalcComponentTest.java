package org.openautomaker.base.postprocessor.nouveau.timeCalc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TimeCalcComponentTest
{

    /**
     * Test of getTotal_duration method, of class TimeCalcComponent.
     */
    @Test
    public void testGetTotal_duration()
    {
        System.out.println("getTotal_duration");
        TimeCalcComponent instance = new TimeCalcComponent();
        instance.incrementDuration(0, 1);
        instance.incrementDuration(0, 1);
        instance.incrementDuration(0, 1);
        
        assertEquals(3, instance.getTotal_duration(), 0.0);
        assertEquals(3, instance.getLayerNumberToPredictedDuration().get(0), 0.0);
    }

    /**
     * Test of getTotal_duration method, of class TimeCalcComponent.
     */
    @Test
    public void testIncrement_multipleLayers()
    {
        System.out.println("increment multiple layers");
        TimeCalcComponent instance = new TimeCalcComponent();
        instance.incrementDuration(0, 1);
        instance.incrementDuration(0, 1);
        instance.incrementDuration(0, 1);
        instance.incrementDuration(1, 1.25);
        instance.incrementDuration(1, 2.25);
        instance.incrementDuration(1, 4.5);
        
        assertEquals(11, instance.getTotal_duration(), 0.0);
        assertEquals(3, instance.getLayerNumberToPredictedDuration().get(0), 0.0);
        assertEquals(8, instance.getLayerNumberToPredictedDuration().get(1), 0.0);
    }
    
}
