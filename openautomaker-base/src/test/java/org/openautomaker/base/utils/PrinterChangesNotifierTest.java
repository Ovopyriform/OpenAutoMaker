
package org.openautomaker.base.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.base.printerControl.model.Head;
import org.openautomaker.base.printerControl.model.PrinterChangesListener;
import org.openautomaker.base.printerControl.model.PrinterChangesNotifier;
import org.openautomaker.base.printerControl.model.Reel;
import org.openautomaker.mock.printer_control.model.MockPrinter;
import org.openautomaker.mock.printer_control.model.MockPrinterFactory;
import org.openautomaker.test_library.GuiceExtension;

import jakarta.inject.Inject;

/**
 *
 * @author tony
 */
@ExtendWith(GuiceExtension.class)
public class PrinterChangesNotifierTest {

	@Inject
	MockPrinterFactory testPrinterFactory;

	@Test
	public void testWhenHeadAdded() {
		MockPrinter printer = testPrinterFactory.create();
		PrinterChangesNotifier notifier = new PrinterChangesNotifier(printer);
		TestPrinterChangesListener listener = new TestPrinterChangesListener();
		notifier.addListener(listener);

		printer.addHead();

		assertTrue(listener.headAdded);
	}

	@Test
	public void testWhenHeadRemoved() {
		MockPrinter printer = testPrinterFactory.create();
		PrinterChangesNotifier notifier = new PrinterChangesNotifier(printer);
		TestPrinterChangesListener listener = new TestPrinterChangesListener();
		notifier.addListener(listener);

		printer.addHead();
		printer.removeHead();

		assertTrue(listener.headAdded);
	}

	@Test
	public void testWhenReelAdded() {
		MockPrinter printer = testPrinterFactory.create();
		PrinterChangesNotifier notifier = new PrinterChangesNotifier(printer);
		TestPrinterChangesListener listener = new TestPrinterChangesListener();
		notifier.addListener(listener);

		printer.addReel(0);

		assertTrue(listener.reel0Added);
	}

	@Test
	public void testWhenReelRemoved() {
		MockPrinter printer = testPrinterFactory.create();
		PrinterChangesNotifier notifier = new PrinterChangesNotifier(printer);
		TestPrinterChangesListener listener = new TestPrinterChangesListener();
		notifier.addListener(listener);

		printer.addReel(0);
		printer.removeReel(0);

		assertTrue(listener.reel0Removed);
	}

	@Test
	public void testWhenReelChanged() {
		MockPrinter printer = testPrinterFactory.create();
		PrinterChangesNotifier notifier = new PrinterChangesNotifier(printer);
		TestPrinterChangesListener listener = new TestPrinterChangesListener();
		notifier.addListener(listener);

		printer.addReel(0);
		printer.changeReel(0);

		assertTrue(listener.reel0Changed);
	}

	private static class TestPrinterChangesListener implements PrinterChangesListener {

		public boolean headAdded = false;
		public boolean headRemoved = false;
		public boolean reel0Added = false;
		public boolean reel0Removed = false;
		public boolean reel0Changed = false;

		@Override
		public void whenHeadAdded() {
			headAdded = true;
		}

		@Override
		public void whenHeadRemoved(Head head) {
			headRemoved = true;
		}

		@Override
		public void whenReelAdded(int reelIndex, Reel reel) {
			reel0Added = true;
		}

		@Override
		public void whenReelRemoved(int reelIndex, Reel reel) {
			reel0Removed = true;
		}

		@Override
		public void whenReelChanged(Reel reel) {
			reel0Changed = true;
		}

		@Override
		public void whenExtruderAdded(int extruderIndex) {
		}

		@Override
		public void whenExtruderRemoved(int extruderIndex) {
		}

	}

}
