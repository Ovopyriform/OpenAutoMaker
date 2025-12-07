package org.openautomaker.inject;

import org.openautomaker.InterAppRequest;
import org.openautomaker.base.notification_manager.SystemNotificationManager;

import com.google.inject.AbstractModule;

import celtech.appManager.SystemNotificationManagerJavaFX;
import celtech.roboxbase.comms.interapp.AbstractInterAppRequest;

public class OpenAutomakerModule extends AbstractModule {

	@Override
	protected void configure() {
		//TODO: Not entirely sure this is the best place to do this.  Looks like comms should be handled elsewhere
		bind(AbstractInterAppRequest.class).to(InterAppRequest.class);
		bind(SystemNotificationManager.class).to(SystemNotificationManagerJavaFX.class);
	}
}
