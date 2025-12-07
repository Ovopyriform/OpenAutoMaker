package org.openautomaker.ui.state;

import com.google.inject.Singleton;

import celtech.appManager.Project;
import jakarta.inject.Inject;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Simple object wrapper to ease injection
 */
@Singleton
public class SelectedProject extends SimpleObjectProperty<Project> {

	@Inject
	protected SelectedProject() {
		super();
	}

}
