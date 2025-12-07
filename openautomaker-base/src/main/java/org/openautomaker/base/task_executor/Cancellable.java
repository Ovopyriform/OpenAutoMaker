package org.openautomaker.base.task_executor;

import javafx.beans.property.BooleanProperty;

public interface Cancellable {
	public BooleanProperty cancelled();
}
