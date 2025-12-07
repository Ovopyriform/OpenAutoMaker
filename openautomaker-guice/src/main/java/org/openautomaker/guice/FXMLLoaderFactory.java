package org.openautomaker.guice;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;

public interface FXMLLoaderFactory {

	public FXMLLoader create();

	public FXMLLoader create(URL location);

	public FXMLLoader create(URL location, Class<?> clazz);

	public FXMLLoader create(URL location, Class<?> clazz, ResourceBundle resourceBundle);

	public FXMLLoader create(URL location, ResourceBundle resourcebundle);

}
