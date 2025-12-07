package org.openautomaker.mock.inject;

import org.openautomaker.mock.component.printer_side_panel.MockComponentIsolationInterface;
import org.openautomaker.ui.component.printer_side_panel.ComponentIsolationInterface;
import org.openautomaker.ui.inject.UIModule;

public class UITestModule extends UIModule {

	/**
	 * These bindings are overridden in the test environment
	 */
	@Override
	protected void overrideBindings() {

	}

	@Override
	public void configure() {
		super.configure();

		bind(ComponentIsolationInterface.class).to(MockComponentIsolationInterface.class);

		//Probably don't need this now there is a test environment.
		//		install(new FactoryModuleBuilder()
		//				.implement(GCodeOutputWriter.class, MockGCodeOutputWriter.class)
		//				.build(GCodeOutputWriterFactory.class));
	}

}
