package uk.wjdp.javan.fragment;

import android.app.Fragment;
import android.os.Bundle;

import uk.wjdp.javan.activity.MainActivity;

/**
 * Created by will on 11/07/16.
 */
public abstract class MainActivityFragment extends Fragment {
    MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mainActivity = (MainActivity)getActivity();
        super.onCreate(savedInstanceState);
    }
}
