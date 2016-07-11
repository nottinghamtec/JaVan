package uk.wjdp.javan.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import uk.wjdp.javan.R;
import uk.wjdp.javan.activity.MainActivity;
import uk.wjdp.javan.model.Driver;

/**
 * Created by will on 11/07/16.
 */
public class DriverListFragment extends Fragment {
    private ListView driverListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for fragment
        return inflater.inflate(R.layout.fragment_driver_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        driverListView = (ListView)getView().findViewById(R.id.driver_list_view);
        initListView(driverListView);
        super.onActivityCreated(savedInstanceState);
    }

    public void initListView(ListView listView) {
        String[] debugItems = {"debug item 1", "debug item 2", "debug item 3"};
        List<Driver> driverItems = Driver.getAll();
        ArrayList<String> listItems = new ArrayList<>();

        for (Driver d : driverItems) {
            listItems.add(d.name);
        }

        ArrayAdapter<Driver> debugAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, driverItems);
        listView.setAdapter(debugAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Driver d = (Driver)parent.getItemAtPosition(position);
                Bundle b = new Bundle();
                b.putLong("id", d.getId());
                MainActivity activity = (MainActivity)getActivity();
                activity.doStepFragmentNavigation(new DriverDetailFragment(), b);
            }
        });
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.driver_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_driver_add) {
            MainActivity mainActivity = (MainActivity)getActivity();
            Log.v("DriverListFragment", "driver_add pressed");
            showDriverAddModal();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDriverAddModal() {
        // Get layout and create view
        LayoutInflater li = LayoutInflater.from(getActivity());
        final View driverAddView = li.inflate(R.layout.modal_driver_add, null);

        // Create an alert dialog builder and set view
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(driverAddView);

        // Save ref to text field
        final EditText userInput = (EditText) driverAddView.findViewById(R.id.editTextDialogUserInput);

        // Build alert dialog and set add/cancel callbacks
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.v("DriverListFragment", userInput.getText().toString());
                                Driver new_driver = new Driver(userInput.getText().toString());
                                new_driver.save();
                                initListView(driverListView);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

        // Actually create the alert dialog and show it
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
