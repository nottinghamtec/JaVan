package uk.wjdp.javan.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import uk.wjdp.javan.R;
import uk.wjdp.javan.activity.MainActivity;
import uk.wjdp.javan.model.Driver;

/**
 * Created by will on 11/07/16.
 */
public class DriverDetailFragment extends Fragment {
    Driver driver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle arguments = this.getArguments();
        long driverId = arguments.getLong("id");
        Log.v("DriverDetailFragment", String.format("Driver id %d", driverId));
        driver = Driver.getById(driverId);
        Log.v("DriverDetailFragment", String.format("Driver name %s", driver.name));
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for fragment
        return inflater.inflate(R.layout.fragment_driver_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        final TextView textView_driverName = (TextView)getView().findViewById(R.id.textView_driverName);
        final Button button_delete = (Button)getView().findViewById(R.id.button_delete);

        textView_driverName.setText(driver.name);

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driver.delete();
                MainActivity activity = (MainActivity)getActivity();
                activity.doFragmentNavigation(new DriverListFragment());
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

}
