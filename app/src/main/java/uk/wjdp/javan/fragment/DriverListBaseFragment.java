package uk.wjdp.javan.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import uk.wjdp.javan.R;
import uk.wjdp.javan.activity.MainActivity;
import uk.wjdp.javan.model.Driver;

/**
 * Created by will on 11/07/16.
 */
public abstract class DriverListBaseFragment extends MainActivityFragment {
    private ListView driverListView;
    private List<Driver> driverItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        driverItems = Driver.getAll();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        driverListView = (ListView)getView().findViewById(R.id.driver_list_view);
        initListView(driverListView);
        super.onActivityCreated(savedInstanceState);
    }

    public void reloadData() {
        driverItems = Driver.getAll();
        initListView(driverListView);
    }

    public void initListView(ListView listView) {
        ArrayAdapter<Driver> debugAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, driverItems);
        listView.setAdapter(debugAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                driverSelect((Driver)parent.getItemAtPosition(position));
            }
        });
    }

    public void driverSelect(Driver driver) {
        // Override this
    }
}
