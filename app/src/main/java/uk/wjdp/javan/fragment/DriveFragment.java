package uk.wjdp.javan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import uk.wjdp.javan.R;
import uk.wjdp.javan.model.LogItem;

/**
 * Created by will on 11/07/16.
 */
public class DriveFragment extends MainActivityFragment {
    LogItem logItemLast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        logItemLast = LogItem.getLast();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for fragment
        return inflater.inflate(R.layout.fragment_drive, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        final Button button_drive = (Button)getView().findViewById(R.id.button_drive);
        final TextView text_miles = (TextView)getView().findViewById(R.id.text_miles);

        button_drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.doStepFragmentNavigation(new DriverSelectFragment());
            }
        });

        if (logItemLast != null) {
            text_miles.setText(String.format("%d miles", logItemLast.start_mileage));
        }

        super.onActivityCreated(savedInstanceState);
    }
}
