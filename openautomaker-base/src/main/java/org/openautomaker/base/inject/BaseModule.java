package org.openautomaker.base.inject;

import org.openautomaker.base.comms.print_server.PrintServerConnection;
import org.openautomaker.base.comms.print_server.PrintServerConnectionMap;
import org.openautomaker.base.configuration.fileRepresentation.PrinterSettingsOverrides;
import org.openautomaker.base.configuration.profilesettings.PrintProfileSettings;
import org.openautomaker.base.configuration.slicer.Cura4ConfigWriter;
import org.openautomaker.base.configuration.slicer.Cura4PlusConfigConvertor;
import org.openautomaker.base.importers.twod.svg.StylusMetaToGCodeEngine;
import org.openautomaker.base.inject.camera_control.CameraTriggerManagerFactory;
import org.openautomaker.base.inject.comms.HardwareCommandInterfaceFactory;
import org.openautomaker.base.inject.comms.PrintServerConnectionFactory;
import org.openautomaker.base.inject.comms.PrintServerMapFactory;
import org.openautomaker.base.inject.comms.RoboxRemoteCommandInterfaceFactory;
import org.openautomaker.base.inject.comms.VirtualPrinterCommandInterfaceFactory;
import org.openautomaker.base.inject.configuration.file_representation.PrinterSettingsOverridesFactory;
import org.openautomaker.base.inject.configuration.profile_settings.PrintProfileSettingsFactory;
import org.openautomaker.base.inject.exporters.AMFOutputConverterFactory;
import org.openautomaker.base.inject.exporters.STLOutputConverterFactory;
import org.openautomaker.base.inject.importer.StylusMetaToGCodeEngineFactory;
import org.openautomaker.base.inject.postprocessor.GCodeOutputWriterFactory;
import org.openautomaker.base.inject.postprocessor.PostProcessorFactory;
import org.openautomaker.base.inject.postprocessor.PostProcessorTaskFactory;
import org.openautomaker.base.inject.postprocessor.UtilityMethodsFactory;
import org.openautomaker.base.inject.printer_control.CalibrationPrinterErrorHandlerFactory;
import org.openautomaker.base.inject.printer_control.CalibrationSingleNozzleHeightActionsFactory;
import org.openautomaker.base.inject.printer_control.CalibrationXAndYActionsFactory;
import org.openautomaker.base.inject.printer_control.HardwarePrinterFactory;
import org.openautomaker.base.inject.printer_control.NozzleHeightStateTransitionManagerFactory;
import org.openautomaker.base.inject.printer_control.NozzleOpeningStateTransitionManagerFactory;
import org.openautomaker.base.inject.printer_control.PrintEngineFactory;
import org.openautomaker.base.inject.printer_control.PurgeActionsFactory;
import org.openautomaker.base.inject.printer_control.PurgePrinterErrorHandlerFactory;
import org.openautomaker.base.inject.printer_control.SingleNozzleHeightStateTransitionManagerFactory;
import org.openautomaker.base.inject.printer_control.XAndYStateTransitionManagerFactory;
import org.openautomaker.base.inject.printer_control.model.HeadFactory;
import org.openautomaker.base.inject.printer_control.model.ReelFactory;
import org.openautomaker.base.inject.printing.PrintJobFactory;
import org.openautomaker.base.inject.printing.SFTPUtilsFactory;
import org.openautomaker.base.inject.printing.TransferGCodeToPrinterTaskFactory;
import org.openautomaker.base.inject.service.FirmwareLoadTaskFactory;
import org.openautomaker.base.inject.service.GCodeGeneratorTaskFactory;
import org.openautomaker.base.inject.slicer.Cura4PlusConfigConvertorFactory;
import org.openautomaker.base.inject.slicer.CuraDefaultSettingsEditorFactory;
import org.openautomaker.base.inject.slicer.SlicerConfigWriterFactory;
import org.openautomaker.base.inject.slicer.SlicerTaskFactory;
import org.openautomaker.base.inject.state_transition.CalibrationNozzleHeightActionsFactory;
import org.openautomaker.base.inject.state_transition.CalibrationNozzleOpeningActionsFactory;
import org.openautomaker.base.inject.state_transition.PurgeStateTransitionManagerFactory;
import org.openautomaker.base.inject.utils.models.PrintableMeshesFactory;
import org.openautomaker.base.postprocessor.GCodeOutputWriter;
import org.openautomaker.base.postprocessor.LiveGCodeOutputWriter;
import org.openautomaker.base.postprocessor.nouveau.PostProcessor;
import org.openautomaker.base.postprocessor.nouveau.UtilityMethods;
import org.openautomaker.base.printerControl.PrintJob;
import org.openautomaker.base.printerControl.model.HardwarePrinter;
import org.openautomaker.base.printerControl.model.Head;
import org.openautomaker.base.printerControl.model.PrintEngine;
import org.openautomaker.base.printerControl.model.Reel;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.CalibrationNozzleHeightActions;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.CalibrationNozzleOpeningActions;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.CalibrationPrinterErrorHandler;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.CalibrationSingleNozzleHeightActions;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.CalibrationXAndYActions;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.NozzleHeightStateTransitionManager;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.NozzleOpeningStateTransitionManager;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.SingleNozzleHeightStateTransitionManager;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.XAndYStateTransitionManager;
import org.openautomaker.base.printerControl.model.statetransitions.purge.PurgeActions;
import org.openautomaker.base.printerControl.model.statetransitions.purge.PurgePrinterErrorHandler;
import org.openautomaker.base.printerControl.model.statetransitions.purge.PurgeStateTransitionManager;
import org.openautomaker.base.services.camera.CameraTriggerManager;
import org.openautomaker.base.services.firmware.FirmwareLoadTask;
import org.openautomaker.base.services.gcodegenerator.GCodeGeneratorTask;
import org.openautomaker.base.services.postProcessor.PostProcessorTask;
import org.openautomaker.base.services.printing.SFTPUtils;
import org.openautomaker.base.services.printing.TransferGCodeToPrinterTask;
import org.openautomaker.base.services.slicer.SlicerTask;
import org.openautomaker.base.slicer.SlicerConfigWriter;
import org.openautomaker.base.task_executor.LiveTaskExecutor;
import org.openautomaker.base.task_executor.TaskExecutor;
import org.openautomaker.base.utils.cura.CuraDefaultSettingsEditor;
import org.openautomaker.base.utils.exporters.AMFOutputConverter;
import org.openautomaker.base.utils.exporters.STLOutputConverter;
import org.openautomaker.base.utils.models.PrintableMeshes;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import celtech.roboxbase.comms.HardwareCommandInterface;
import celtech.roboxbase.comms.VirtualPrinterCommandInterface;
import celtech.roboxbase.comms.remote.RoboxRemoteCommandInterface;

public class BaseModule extends AbstractModule {

	/**
	 * Bindings which can be overridden
	 */
	protected void overrideBindings() {
		bind(TaskExecutor.class).to(LiveTaskExecutor.class);
	}

	@Override
	public void configure() {
		overrideBindings();

		bind(SlicerConfigWriter.class).to(Cura4ConfigWriter.class);

		install(new FactoryModuleBuilder()
				.implement(GCodeOutputWriter.class, LiveGCodeOutputWriter.class)
				.build(GCodeOutputWriterFactory.class));

		install(new FactoryModuleBuilder()
				.implement(HardwareCommandInterface.class, HardwareCommandInterface.class)
				.build(HardwareCommandInterfaceFactory.class));

		install(new FactoryModuleBuilder()
				.implement(VirtualPrinterCommandInterface.class, VirtualPrinterCommandInterface.class)
				.build(VirtualPrinterCommandInterfaceFactory.class));

		install(new FactoryModuleBuilder()
				.implement(RoboxRemoteCommandInterface.class, RoboxRemoteCommandInterface.class)
				.build(RoboxRemoteCommandInterfaceFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PostProcessor.class, PostProcessor.class)
				.build(PostProcessorFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PostProcessorTask.class, PostProcessorTask.class)
				.build(PostProcessorTaskFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PurgePrinterErrorHandler.class, PurgePrinterErrorHandler.class)
				.build(PurgePrinterErrorHandlerFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PurgeActions.class, PurgeActions.class)
				.build(PurgeActionsFactory.class));

		install(new FactoryModuleBuilder()
				.implement(HardwarePrinter.class, HardwarePrinter.class)
				.build(HardwarePrinterFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PrintEngine.class, PrintEngine.class)
				.build(PrintEngineFactory.class));

		install(new FactoryModuleBuilder()
				.implement(CalibrationNozzleHeightActions.class, CalibrationNozzleHeightActions.class)
				.build(CalibrationNozzleHeightActionsFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PurgeStateTransitionManager.class, PurgeStateTransitionManager.class)
				.build(PurgeStateTransitionManagerFactory.class));

		install(new FactoryModuleBuilder()
				.implement(CalibrationNozzleOpeningActions.class, CalibrationNozzleOpeningActions.class)
				.build(CalibrationNozzleOpeningActionsFactory.class));

		install(new FactoryModuleBuilder()
				.implement(TransferGCodeToPrinterTask.class, TransferGCodeToPrinterTask.class)
				.build(TransferGCodeToPrinterTaskFactory.class));

		install(new FactoryModuleBuilder()
				.implement(StylusMetaToGCodeEngine.class, StylusMetaToGCodeEngine.class)
				.build(StylusMetaToGCodeEngineFactory.class));

		install(new FactoryModuleBuilder()
				.implement(CalibrationPrinterErrorHandler.class, CalibrationPrinterErrorHandler.class)
				.build(CalibrationPrinterErrorHandlerFactory.class));

		install(new FactoryModuleBuilder()
				.implement(CalibrationSingleNozzleHeightActions.class, CalibrationSingleNozzleHeightActions.class)
				.build(CalibrationSingleNozzleHeightActionsFactory.class));

		install(new FactoryModuleBuilder()
				.implement(CalibrationXAndYActions.class, CalibrationXAndYActions.class)
				.build(CalibrationXAndYActionsFactory.class));

		install(new FactoryModuleBuilder()
				.implement(NozzleHeightStateTransitionManager.class, NozzleHeightStateTransitionManager.class)
				.build(NozzleHeightStateTransitionManagerFactory.class));

		install(new FactoryModuleBuilder()
				.implement(NozzleOpeningStateTransitionManager.class, NozzleOpeningStateTransitionManager.class)
				.build(NozzleOpeningStateTransitionManagerFactory.class));

		install(new FactoryModuleBuilder()
				.implement(SingleNozzleHeightStateTransitionManager.class, SingleNozzleHeightStateTransitionManager.class)
				.build(SingleNozzleHeightStateTransitionManagerFactory.class));

		install(new FactoryModuleBuilder()
				.implement(XAndYStateTransitionManager.class, XAndYStateTransitionManager.class)
				.build(XAndYStateTransitionManagerFactory.class));

		install(new FactoryModuleBuilder()
				.implement(GCodeGeneratorTask.class, GCodeGeneratorTask.class)
				.build(GCodeGeneratorTaskFactory.class));

		install(new FactoryModuleBuilder()
				.implement(SlicerConfigWriter.class, Cura4ConfigWriter.class)
				.build(SlicerConfigWriterFactory.class));

		install(new FactoryModuleBuilder()
				.implement(STLOutputConverter.class, STLOutputConverter.class)
				.build(STLOutputConverterFactory.class));

		install(new FactoryModuleBuilder()
				.implement(AMFOutputConverter.class, AMFOutputConverter.class)
				.build(AMFOutputConverterFactory.class));

		install(new FactoryModuleBuilder()
				.implement(SlicerTask.class, SlicerTask.class)
				.build(SlicerTaskFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PrintJob.class, PrintJob.class)
				.build(PrintJobFactory.class));

		install(new FactoryModuleBuilder()
				.implement(CuraDefaultSettingsEditor.class, CuraDefaultSettingsEditor.class)
				.build(CuraDefaultSettingsEditorFactory.class));

		install(new FactoryModuleBuilder()
				.implement(CameraTriggerManager.class, CameraTriggerManager.class)
				.build(CameraTriggerManagerFactory.class));

		install(new FactoryModuleBuilder()
				.implement(UtilityMethods.class, UtilityMethods.class)
				.build(UtilityMethodsFactory.class));

		install(new FactoryModuleBuilder()
				.implement(Cura4PlusConfigConvertor.class, Cura4PlusConfigConvertor.class)
				.build(Cura4PlusConfigConvertorFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PrintServerConnection.class, PrintServerConnection.class)
				.build(PrintServerConnectionFactory.class));

		install(new FactoryModuleBuilder()
				.implement(SFTPUtils.class, SFTPUtils.class)
				.build(SFTPUtilsFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PrintServerConnectionMap.class, PrintServerConnectionMap.class)
				.build(PrintServerMapFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PrinterSettingsOverrides.class, PrinterSettingsOverrides.class)
				.build(PrinterSettingsOverridesFactory.class));

		install(new FactoryModuleBuilder()
				.implement(Head.class, Head.class)
				.build(HeadFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PrintableMeshes.class, PrintableMeshes.class)
				.build(PrintableMeshesFactory.class));

		install(new FactoryModuleBuilder()
				.implement(PrintProfileSettings.class, PrintProfileSettings.class)
				.build(PrintProfileSettingsFactory.class));

		install(new FactoryModuleBuilder()
				.implement(Reel.class, Reel.class)
				.build(ReelFactory.class));

		install(new FactoryModuleBuilder()
				.implement(FirmwareLoadTask.class, FirmwareLoadTask.class)
				.build(FirmwareLoadTaskFactory.class));
	}
}
