package org.openautomaker.base.printerControl.comms.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FilenameFilter;

import org.junit.jupiter.api.Test;
import org.openautomaker.base.configuration.datafileaccessors.HeadContainer;

/**
 *
 * @author Ian
 */
public class MacroFilenameFilterTest
{
    /**
     * Test of accept method, of class MacroFilenameFilter.
     */
    @Test
    public void testDontAcceptWithWrongMacroName()
    {
        FilenameFilter testFilter = new MacroFilenameFilter("testFile",
                null,
                GCodeMacros.NozzleUseIndicator.DONT_CARE,
                GCodeMacros.SafetyIndicator.DONT_CARE);

        assertFalse(testFilter.accept(null, "fred.gcode"));
    }

    @Test
    public void testAcceptWithCorrectMacroName()
    {
        FilenameFilter testFilter = new MacroFilenameFilter("testFile",
                null,
                GCodeMacros.NozzleUseIndicator.DONT_CARE,
                GCodeMacros.SafetyIndicator.DONT_CARE);

        assertTrue(testFilter.accept(null, "testFile.gcode"));
    }

    @Test
    public void testDontAcceptWithCorrectMacroNameUnrequestedModifier()
    {
        FilenameFilter testFilter = new MacroFilenameFilter("testFile",
                null,
                GCodeMacros.NozzleUseIndicator.DONT_CARE,
                GCodeMacros.SafetyIndicator.DONT_CARE);

        assertFalse(testFilter.accept(null, "testFile#U.gcode"));
    }

    @Test
    public void testDontAcceptWithCorrectMacroNameRequestedModifierNotPresent()
    {
        FilenameFilter testFilter = new MacroFilenameFilter("testFile",
                HeadContainer.defaultHeadID,
                GCodeMacros.NozzleUseIndicator.DONT_CARE,
                GCodeMacros.SafetyIndicator.DONT_CARE);

        assertFalse(testFilter.accept(null, "testFile#U.gcode"));
    }

    @Test
    public void testAcceptWithCorrectMacroNameRequestedModifierPresent()
    {
        FilenameFilter testFilter = new MacroFilenameFilter("testFile",
                "RBX01-DM",
                GCodeMacros.NozzleUseIndicator.DONT_CARE,
                GCodeMacros.SafetyIndicator.DONT_CARE);

        assertTrue(testFilter.accept(null, "testFile#RBX01-DM.gcode"));
    }

    @Test
    public void testAcceptWithCorrectMacroNameMatchingModifierAndExtraModifier()
    {
        FilenameFilter testFilter = new MacroFilenameFilter("testFile",
                "RBX01-DM",
                GCodeMacros.NozzleUseIndicator.DONT_CARE,
                GCodeMacros.SafetyIndicator.DONT_CARE);

        assertFalse(testFilter.accept(null, "testFile#U#RBX01-DM.gcode"));
    }

    @Test
    public void testSafetyOff()
    {
        FilenameFilter testFilter = new MacroFilenameFilter("testFile",
                "RBX01-DM",
                GCodeMacros.NozzleUseIndicator.DONT_CARE,
                GCodeMacros.SafetyIndicator.SAFETIES_OFF);

        assertTrue(testFilter.accept(null, "testFile#U#RBX01-DM.gcode"));
    }
    
    @Test
    public void testSimulateSafetyFallback()
    {
        FilenameFilter testFilter = new MacroFilenameFilter("testFile",
                "RBX01-DM",
                GCodeMacros.NozzleUseIndicator.DONT_CARE,
                GCodeMacros.SafetyIndicator.SAFETIES_OFF);

        assertFalse(testFilter.accept(null, "testFile#RBX01-DM.gcode"));

        FilenameFilter testFilter2 = new MacroFilenameFilter("testFile",
                "RBX01-DM",
                GCodeMacros.NozzleUseIndicator.DONT_CARE,
                GCodeMacros.SafetyIndicator.DONT_CARE);

        assertTrue(testFilter2.accept(null, "testFile#RBX01-DM.gcode"));
    }
}
