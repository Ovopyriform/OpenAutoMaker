package org.openautomaker.environment.preference.printer;

import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;
import org.openautomaker.environment.preference.product.ProductIdPreference;
import org.openautomaker.environment.preference.product.ProductKeyPreference;
import org.openautomaker.environment.preference.product.VendorIdPreference;
import org.openautomaker.environment.properties.NativeProperties;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Retrieves the printer detector native executable and provides parameter formatter
 */
@Singleton
public class PrinterDetectorPreference extends APathPreference {

	private static final String DETECTOR_EXECUTABLE = "openautomaker.native.detector.executable";
	private static final String DETECTOR_PARAMS = "openautomaker.native.detector.params";

	protected static final String NATIVE = "native";

	private final NativeProperties nativeProperties;
	private final VendorIdPreference vendorIdPreference;
	private final ProductIdPreference productIdPreference;
	private final ProductKeyPreference productKeyPreference;
	private final HomePathPreference homePathPreference;

	@Inject
	protected PrinterDetectorPreference(
			NativeProperties nativeProperties,
			VendorIdPreference vendorIdPreference,
			ProductIdPreference productIdPreference,
			ProductKeyPreference productKeyPreference,
			HomePathPreference homePathPreference) {

		this.nativeProperties = nativeProperties;
		this.vendorIdPreference = vendorIdPreference;
		this.productIdPreference = productIdPreference;
		this.productKeyPreference = productKeyPreference;
		this.homePathPreference = homePathPreference;

	}

	@Override
	public Path getValue() {
		return homePathPreference.getAppValue().resolve(OPENAUTOMAKER).resolve(NATIVE).resolve(nativeProperties.get(DETECTOR_EXECUTABLE));
	}

	@Override
	public void addChangeListener(PreferenceChangeListener listener) {
		throw new UnsupportedOperationException("addChangeListener not implemented for preference: " + getClass().getSimpleName());
	}

	@Override
	protected Preferences getNode() {
		throw new UnsupportedOperationException("getNode not implemented for preference: " + getClass().getSimpleName());
	}

	@Override
	public void setValue(Path value) {
		throw new UnsupportedOperationException("setValue not implemented for preference: " + getClass().getSimpleName());
	}

	/**
	 * Formats the parameters for the device detector
	 * 
	 * @return Formatted parameters based on the OS this is running on. Format stored in native/openautomaker.native.properties
	 */
	public String[] getParams() {
		return MessageFormat.format(
				nativeProperties.get(DETECTOR_PARAMS).replace(" ", "|"),
				vendorIdPreference.getValue(),
				productIdPreference.getValue(),
				productKeyPreference.getValue()).split("\\|");
	}

}
