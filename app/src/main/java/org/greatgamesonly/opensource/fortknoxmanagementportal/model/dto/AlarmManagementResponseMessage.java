package org.greatgamesonly.opensource.fortknoxmanagementportal.model.dto;

public class AlarmManagementResponseMessage {
    boolean successful;
    String responseMsg;

    public AlarmManagementResponseMessage() {}

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
