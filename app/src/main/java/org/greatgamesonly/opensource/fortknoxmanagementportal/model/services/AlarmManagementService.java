package org.greatgamesonly.opensource.fortknoxmanagementportal.model.services;

import org.greatgamesonly.opensource.fortknoxmanagementportal.model.dto.AlarmManagementResponseMessage;
import org.greatgamesonly.opensource.fortknoxmanagementportal.model.services.implementation.AlarmManagementServiceImpl;

public interface AlarmManagementService {

    public void triggerAlarmNow(AlarmManagementServiceImpl.AlarmManagementCallback response);
}
