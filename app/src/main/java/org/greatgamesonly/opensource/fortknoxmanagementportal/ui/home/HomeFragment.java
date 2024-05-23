package org.greatgamesonly.opensource.fortknoxmanagementportal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import okhttp3.*;
import org.greatgamesonly.opensource.fortknoxmanagementportal.R;
import org.greatgamesonly.opensource.fortknoxmanagementportal.databinding.FragmentHomeBinding;
import org.greatgamesonly.opensource.fortknoxmanagementportal.model.dto.AlarmManagementResponseMessage;
import org.greatgamesonly.opensource.fortknoxmanagementportal.model.services.AlarmManagementService;
import org.greatgamesonly.opensource.fortknoxmanagementportal.model.services.implementation.AlarmManagementServiceImpl;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Button buttonSendRequest;
    private TextView textViewResponse;

    private AlarmManagementService alarmManagementService;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buttonSendRequest = root.findViewById(R.id.trigger_alarm_btn);
        textViewResponse = root.findViewById(R.id.trigger_alarm_output);

        buttonSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManagementService.triggerAlarmNow(response -> textViewResponse.setText(response.getResponseMsg()));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}