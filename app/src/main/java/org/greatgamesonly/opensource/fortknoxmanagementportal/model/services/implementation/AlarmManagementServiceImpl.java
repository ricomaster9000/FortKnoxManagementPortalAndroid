package org.greatgamesonly.opensource.fortknoxmanagementportal.model.services.implementation;

import okhttp3.*;
import org.greatgamesonly.opensource.fortknoxmanagementportal.model.dto.AlarmManagementResponseMessage;
import org.greatgamesonly.opensource.fortknoxmanagementportal.model.services.AlarmManagementService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.os.Handler;
import android.os.Looper;

public class AlarmManagementServiceImpl implements AlarmManagementService {

    private final static String MAIN_ALARM_SYSTEM_WEB_URL = "http://your.endpoint.here"; // Update this with the actual URL
    private final OkHttpClient client = new OkHttpClient();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    public void triggerAlarmNow(AlarmManagementCallback callback) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                AlarmManagementResponseMessage result = new AlarmManagementResponseMessage();
                try {
                    Response response = triggerGet("");
                    result.setSuccessful(response.isSuccessful());
                    if (response.isSuccessful()) {
                        result.setResponseMsg("Alarm Successfully Triggered");
                    } else {
                        String responseMsg = response.body() != null ? "Alarm could not be Triggered: " + response.body().string() : "Alarm could not be Triggered: ";
                        responseMsg += "  (httpCode - " + response.code() + ")";
                        result.setResponseMsg(responseMsg);
                    }
                } catch (Exception e) {
                    result.setSuccessful(false);
                    result.setResponseMsg("Request Failed: " + e.getMessage());
                    throw new RuntimeException(e);
                }

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(result);
                    }
                });
            }
        });
    }

    private Response triggerGet(String subPath) throws Exception {
        String url = MAIN_ALARM_SYSTEM_WEB_URL + subPath; // Replace with your endpoint

        Request request = new Request.Builder()
                .url(url)
                .build();

        return client.newCall(request).execute(); // Perform the request synchronously and return the response
    }

    public interface AlarmManagementCallback {
        void onResponse(AlarmManagementResponseMessage response);
    }
}
