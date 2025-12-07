package org.openautomaker.mock.printer_control.model;

public interface MockPrinterFactory {

	public MockPrinter create();

	public MockPrinter create(int numExtruders);
}
