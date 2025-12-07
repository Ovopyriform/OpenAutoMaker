package org.openautomaker.mock.inject;

import org.openautomaker.base.inject.BaseModule;
import org.openautomaker.base.notification_manager.DevNullNotificationManager;
import org.openautomaker.base.notification_manager.SystemNotificationManager;
import org.openautomaker.base.task_executor.DevNullTaskExecutor;
import org.openautomaker.base.task_executor.TaskExecutor;
import org.openautomaker.mock.printer_control.model.MockHead;
import org.openautomaker.mock.printer_control.model.MockHeadFactory;
import org.openautomaker.mock.printer_control.model.MockPrinter;
import org.openautomaker.mock.printer_control.model.MockPrinterFactory;

import com.google.inject.assistedinject.FactoryModuleBuilder;

public class BaseTestModule extends BaseModule {

	/**
	 * These bindings are overridden in the test environment
	 */
	@Override
	protected void overrideBindings() {
		bind(TaskExecutor.class).to(DevNullTaskExecutor.class);
		bind(SystemNotificationManager.class).to(DevNullNotificationManager.class);
	}

	@Override
	public void configure() {
		super.configure();

		// Testing specific bindings
		install(new FactoryModuleBuilder()
				.implement(MockHead.class, MockHead.class)
				.build(MockHeadFactory.class));

		install(new FactoryModuleBuilder()
				.implement(MockPrinter.class, MockPrinter.class)
				.build(MockPrinterFactory.class));
	}
}
