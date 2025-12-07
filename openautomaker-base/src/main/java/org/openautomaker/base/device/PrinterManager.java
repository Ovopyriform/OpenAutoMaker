package org.openautomaker.base.device;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.printerControl.model.PrinterListChangesNotifier;
import org.openautomaker.base.task_executor.TaskExecutor;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Singleton
public class PrinterManager {

	Logger LOGGER = LogManager.getLogger();

	private final PrinterListChangesNotifier printerListChangesNotifier;
	private final ObservableList<Printer> connectedPrinters;

	private final TaskExecutor taskExecutor;

	@Inject
	protected PrinterManager(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
		this.connectedPrinters = FXCollections.observableArrayList();
		this.printerListChangesNotifier = new PrinterListChangesNotifier(connectedPrinters);
	}

	public ObservableList<Printer> getConnectedPrinters() {
		return connectedPrinters;
	}

	public PrinterListChangesNotifier getPrinterChangeNotifier() {
		return printerListChangesNotifier;
	}

	//TODO: So why are these specifically run on the GUI thread even though the add is synchronised?
	public void printerConnected(Printer printer) {
		taskExecutor.runOnGUIThread(() -> {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug(">>>Printer connection notification - " + printer);

			doPrinterConnect(printer);
		});
	}

	// Synchronised addition to the printers list
	private synchronized void doPrinterConnect(Printer printer) {
		connectedPrinters.add(printer);
	}

	public void printerDisconnected(Printer printer) {
		taskExecutor.runOnGUIThread(() -> {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("<<<Printer disconnection notification - " + printer);

			doPrinterDisconnect(printer);
		});
	}

	//Synchronised removal from the list
	private synchronized void doPrinterDisconnect(Printer printer) {
		connectedPrinters.remove(printer);
	}
}
