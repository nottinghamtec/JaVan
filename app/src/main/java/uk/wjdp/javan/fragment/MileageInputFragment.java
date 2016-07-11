package uk.wjdp.javan.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.wjdp.javan.R;

/**
 * Created by will on 11/07/16.
 */
public class MileageInputFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for fragment
        return inflater.inflate(R.layout.fragment_driver_list, container, false);
    }
}
