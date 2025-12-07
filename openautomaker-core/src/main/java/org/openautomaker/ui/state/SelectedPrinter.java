package org.openautomaker.ui.state;

import org.openautomaker.base.printerControl.model.Printer;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Simple wrapper object for SimpleObjectProperty to ease singleton injection
 */
//TODO: Roll this into PrinterManager
@Singleton
public class SelectedPrinter extends SimpleObjectProperty<Printer> {

	@Inject
	protected SelectedPrinter() {
		super();
	}
}
