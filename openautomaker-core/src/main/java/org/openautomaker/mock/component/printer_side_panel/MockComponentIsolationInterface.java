package org.openautomaker.mock.component.printer_side_panel;

import org.openautomaker.ui.component.printer_side_panel.ComponentIsolationInterface;
import org.openautomaker.ui.component.printer_side_panel.printer.PrinterComponent;

import jakarta.inject.Inject;

public class MockComponentIsolationInterface implements ComponentIsolationInterface {

	@Inject
	protected MockComponentIsolationInterface() {

	}

	@Override
	public void interruptibilityUpdated(PrinterComponent component) {
		// TODO Auto-generated method stub

	}

}
