package uk.wjdp.javan.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import uk.wjdp.javan.R;
import uk.wjdp.javan.model.Driver;

/**
 * Created by will on 11/07/16.
 */
public class DriverSelectFragment extends DriverListBaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for fragment
        return inflater.inflate(R.layout.fragment_driver_list, container, false);
    }

    @Override
    public void driverSelect(Driver driver) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", driver.getId());
        mainActivity.doStepFragmentNavigation(new MileageInputFragment(), bundle);
    }

}
