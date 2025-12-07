package org.openautomaker.guice;

import org.openautomaker.environment.I18N;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import javafx.fxml.FXMLLoader;

/**
 * FXMLLoader provider for Guice. Requires injection of the GuiceGontext and I18N
 */
public class FXMLLoaderProvider implements Provider<FXMLLoader> {

	private final GuiceContext guiceContext;
	private final I18N i18n;

	@Inject
	public FXMLLoaderProvider(GuiceContext guiceContext, I18N i18n) {
		this.guiceContext = guiceContext;
		this.i18n = i18n;
	}

	@Override
	public FXMLLoader get() {
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(guiceContext::getInstance);
		loader.setResources(i18n.getResourceBundle());
		return loader;
	}

}