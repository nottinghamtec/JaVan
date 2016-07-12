package uk.wjdp.javan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import uk.wjdp.javan.R;

/**
 * Created by will on 11/07/16.
 */
public class DrivingFragment extends MainActivityFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for fragment
        return inflater.inflate(R.layout.fragment_driving, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        final Button button_drive = (Button)getView().findViewById(R.id.button_drive);

        button_drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.doStepFragmentNavigation(new DriverSelectFragment());
            }
        });

        super.onActivityCreated(savedInstanceState);
    }
}
