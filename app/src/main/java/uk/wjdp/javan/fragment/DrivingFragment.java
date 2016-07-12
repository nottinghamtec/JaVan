package uk.wjdp.javan.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import uk.wjdp.javan.R;
import uk.wjdp.javan.model.Driver;
import uk.wjdp.javan.model.LogItem;

/**
 * Created by will on 11/07/16.
 */
public class DrivingFragment extends MainActivityFragment {
    protected Driver driver;
    protected LogItem logItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        logItem = LogItem.getCurrent();
        driver = logItem.getDriver();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for fragment
        return inflater.inflate(R.layout.fragment_driving, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        final Button button_drive = (Button)getView().findViewById(R.id.button_drive);
        final TextView text_driverName = (TextView)getView().findViewById(R.id.text_driverName);
        final TextView text_miles = (TextView)getView().findViewById(R.id.text_miles);

        button_drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("id", driver.getId());
                bundle.putBoolean("end", true);
                mainActivity.doStepFragmentNavigation(new MileageInputFragment(), bundle);
            }
        });

        text_driverName.setText(driver.name);
        text_miles.setText(String.format("%d miles", logItem.start_mileage));

        super.onActivityCreated(savedInstanceState);
    }
}
