
package org.openautomaker.base.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.base.printerControl.model.Head;
import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.printerControl.model.PrinterListChangesListener;
import org.openautomaker.base.printerControl.model.PrinterListChangesNotifier;
import org.openautomaker.base.printerControl.model.Reel;
import org.openautomaker.mock.printer_control.model.MockPrinter;
import org.openautomaker.mock.printer_control.model.MockPrinterFactory;
import org.openautomaker.test_library.GuiceExtension;

import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tony
 */
@ExtendWith(GuiceExtension.class)
public class PrinterListChangesNotifierTest {

	@Inject
	MockPrinterFactory testPrinterFactory;

	@Test
	public void testWhenPrinterAdded() {
		ObservableList<Printer> printers = FXCollections.observableArrayList();
		PrinterListChangesNotifier notifier = new PrinterListChangesNotifier(printers);
		TestPrinterListChangesListener plcListener = new TestPrinterListChangesListener();
		notifier.addListener(plcListener);

		assertEquals(0, plcListener.addedPrinters.size());
		MockPrinter printer = testPrinterFactory.create();
		printers.add(printer);
		assertEquals(1, plcListener.addedPrinters.size());
	}

	@Test
	public void testWhenPrinterAddedAndRemoved() {
		ObservableList<Printer> printers = FXCollections.observableArrayList();
		PrinterListChangesNotifier notifier = new PrinterListChangesNotifier(printers);
		TestPrinterListChangesListener plcListener = new TestPrinterListChangesListener();
		notifier.addListener(plcListener);

		MockPrinter printer = testPrinterFactory.create();
		printers.add(printer);
		printers.remove(printer);
		assertEquals(0, plcListener.addedPrinters.size());
	}

	@Test
	public void testWhenPrinterAddedThenHeadAdded() {
		ObservableList<Printer> printers = FXCollections.observableArrayList();
		PrinterListChangesNotifier notifier = new PrinterListChangesNotifier(printers);
		TestPrinterListChangesListener plcListener = new TestPrinterListChangesListener();
		notifier.addListener(plcListener);

		assertEquals(0, plcListener.printersWithHeadAdded.size());
		MockPrinter printer = testPrinterFactory.create();
		printers.add(printer);
		printer.addHead();
		assertEquals(1, plcListener.printersWithHeadAdded.size());
	}

	@Test
	public void testWhenPrinterAddedThenHeadRemoved() {
		ObservableList<Printer> printers = FXCollections.observableArrayList();
		PrinterListChangesNotifier notifier = new PrinterListChangesNotifier(printers);
		TestPrinterListChangesListener plcListener = new TestPrinterListChangesListener();
		notifier.addListener(plcListener);

		assertEquals(0, plcListener.printersWithHeadRemoved.size());
		MockPrinter printer = testPrinterFactory.create();
		printers.add(printer);
		printer.addHead();
		printer.removeHead();
		assertEquals(1, plcListener.printersWithHeadRemoved.size());
	}

	@Test
	public void testWhenPrinterAddedThenHeadRemovedWithThreePrinters() {
		ObservableList<Printer> printers = FXCollections.observableArrayList();
		PrinterListChangesNotifier notifier = new PrinterListChangesNotifier(printers);
		TestPrinterListChangesListener plcListener = new TestPrinterListChangesListener();
		notifier.addListener(plcListener);

		assertEquals(0, plcListener.printersWithHeadRemoved.size());
		MockPrinter printer1 = testPrinterFactory.create();
		MockPrinter printer2 = testPrinterFactory.create();
		MockPrinter printer3 = testPrinterFactory.create();
		printers.add(printer1);
		printers.add(printer2);
		printers.add(printer3);
		printer1.addHead();
		printer2.addHead();
		printer2.removeHead();
		assertEquals(1, plcListener.printersWithHeadRemoved.size());
		assertEquals(printer2, plcListener.printersWithHeadRemoved.get(0));

		assertEquals(2, plcListener.printersWithHeadAdded.size());
		assertEquals(printer1, plcListener.printersWithHeadAdded.get(0));
		assertEquals(printer2, plcListener.printersWithHeadAdded.get(1));
	}

	@Test
	public void testWhenPrinterAddedThenReelAdded() {
		ObservableList<Printer> printers = FXCollections.observableArrayList();
		PrinterListChangesNotifier notifier = new PrinterListChangesNotifier(printers);
		TestPrinterListChangesListener plcListener = new TestPrinterListChangesListener();
		notifier.addListener(plcListener);

		assertEquals(0, plcListener.printersWithHeadAdded.size());
		MockPrinter printer = testPrinterFactory.create();
		printers.add(printer);
		printer.addReel(0);
		assertEquals(1, plcListener.printersWithReelAdded.size());
	}

	@Test
	public void testWhenPrinterAddedThenReelRemoved() {
		ObservableList<Printer> printers = FXCollections.observableArrayList();
		PrinterListChangesNotifier notifier = new PrinterListChangesNotifier(printers);
		TestPrinterListChangesListener plcListener = new TestPrinterListChangesListener();
		notifier.addListener(plcListener);

		assertEquals(0, plcListener.printersWithHeadAdded.size());
		MockPrinter printer = testPrinterFactory.create();
		printers.add(printer);
		printer.addReel(0);
		assertEquals(1, plcListener.printersWithReelAdded.size());
	}

	@Test
	public void testListenerRemovedWhenPrinterAddedThenRemoved() {
		ObservableList<Printer> printers = FXCollections.observableArrayList();
		PrinterListChangesNotifier notifier = new PrinterListChangesNotifier(printers);
		TestPrinterListChangesListener plcListener = new TestPrinterListChangesListener();
		notifier.addListener(plcListener);

		assertEquals(0, plcListener.addedPrinters.size());
		MockPrinter printer = testPrinterFactory.create();
		printers.add(printer);
		printers.remove(printer);
		assertEquals(0, plcListener.addedPrinters.size());

		printer.addHead();
		assertEquals(0, plcListener.printersWithHeadAdded.size());
	}

	private static class TestPrinterListChangesListener implements PrinterListChangesListener {

		public List<Printer> addedPrinters = new ArrayList<>();
		public List<Printer> printersWithHeadAdded = new ArrayList<>();
		public List<Printer> printersWithHeadRemoved = new ArrayList<>();
		public List<Printer> printersWithReelAdded = new ArrayList<>();
		public List<Printer> printersWithReelRemoved = new ArrayList<>();

		@Override
		public void whenPrinterAdded(Printer printer) {
			addedPrinters.add(printer);
		}

		@Override
		public void whenPrinterRemoved(Printer printer) {
			addedPrinters.remove(printer);
		}

		@Override
		public void whenHeadAdded(Printer printer) {
			printersWithHeadAdded.add(printer);
		}

		@Override
		public void whenHeadRemoved(Printer printer, Head head) {
			printersWithHeadRemoved.add(printer);
		}

		@Override
		public void whenReelAdded(Printer printer, int reelIndex) {
			printersWithReelAdded.add(printer);
		}

		@Override
		public void whenReelRemoved(Printer printer, Reel reel, int reelIndex) {
			printersWithReelRemoved.add(printer);
		}

		@Override
		public void whenReelChanged(Printer printer, Reel reel) {
		}

		@Override
		public void whenExtruderAdded(Printer printer, int extruderIndex) {
		}

		@Override
		public void whenExtruderRemoved(Printer printer, int extruderIndex) {
		}
	}

}
