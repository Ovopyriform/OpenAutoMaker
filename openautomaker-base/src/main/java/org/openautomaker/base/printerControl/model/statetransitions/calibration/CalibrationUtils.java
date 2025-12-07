package org.openautomaker.base.printerControl.model.statetransitions.calibration;

import org.openautomaker.base.device.PrinterManager;
import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.printerControl.model.PrinterListChangesAdapter;
import org.openautomaker.base.task_executor.Cancellable;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 *
 * @author ianhudson
 */
@Singleton
public class CalibrationUtils {

	private final PrinterManager printerManager;

	@Inject
	protected CalibrationUtils(
			PrinterManager printerManager) {

		this.printerManager = printerManager;
	}

	public void setCancelledIfPrinterDisconnected(Printer printerToMonitor, Cancellable cancellable) {
		printerManager.getPrinterChangeNotifier().addListener(new PrinterListChangesAdapter() {
			@Override
			public void whenPrinterRemoved(Printer printer) {
				if (printerToMonitor == printer) {
					cancellable.cancelled().set(true);
				}
			}
		});
	}
}
