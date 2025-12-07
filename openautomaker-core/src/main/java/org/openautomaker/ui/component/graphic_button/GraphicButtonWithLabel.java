package org.openautomaker.ui.component.graphic_button;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GraphicButtonWithLabel extends Pane {

	private final VBox container;
	private final Label label;
	private GraphicButton button;

	public GraphicButtonWithLabel() {
		configureLayout();

		button = new GraphicButton();

		label = new Label();
		configureLabel();

		container = new VBox();
		configureContainer();

		getChildren().add(container);
	}

	public GraphicButtonWithLabel(String fxmlFileName, String labelText) {
		configureLayout();

		button = new GraphicButton(fxmlFileName);

		label = new Label(labelText);
		configureLabel();

		container = new VBox();
		configureContainer();

		getChildren().add(container);
	}

	private void configureLayout() {
		setPrefWidth(80);
		setPrefHeight(80);
		setMinWidth(USE_PREF_SIZE);
		setMaxWidth(USE_PREF_SIZE);
		setMinHeight(USE_PREF_SIZE);
		setMaxHeight(USE_PREF_SIZE);
	}

	private void configureContainer() {
		container.setAlignment(Pos.CENTER);
		container.setPrefWidth(80);
		container.setPrefHeight(80);
		container.setMinWidth(USE_PREF_SIZE);
		container.setMaxWidth(USE_PREF_SIZE);
		container.setMinHeight(USE_PREF_SIZE);
		container.setMaxHeight(USE_PREF_SIZE);

		//Add elements
		container.getChildren().add(button);
		container.getChildren().add(label);
	}

	private void configureLabel() {
		label.getStyleClass().add("graphic-button-label");
	}

	public String getFxmlFileName() {
		return button.getFxmlFileName();
	}

	public void setFxmlFileName(String fxmlFileName) {
		button.setFxmlFileName(fxmlFileName);
	}

	public StringProperty getFxmlFileNameProperty() {
		return button.getFxmlFileNameProperty();
	}

	public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
		return button.onActionProperty();
	}

	public final void setOnAction(EventHandler<ActionEvent> value) {
		button.onActionProperty().set(value);
	}

	public final EventHandler<ActionEvent> getOnAction() {
		return button.onActionProperty().get();
	}

	public String getLabelText() {
		return label.getText();
	}

	public void setLabelText(String text) {
		label.setText(text);
	}

	public StringProperty getLabelTextProperty() {
		return label.textProperty();
	}
}
