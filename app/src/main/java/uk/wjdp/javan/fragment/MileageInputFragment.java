package uk.wjdp.javan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import uk.wjdp.javan.R;
import uk.wjdp.javan.model.Driver;
import uk.wjdp.javan.model.LogItem;

/**
 * Created by will on 11/07/16.
 */
public class MileageInputFragment extends MainActivityFragment implements View.OnClickListener {
    protected Driver driver;
    protected Integer mileage = 0;
    protected TextView textView_mileage;
    protected Boolean end;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        driver = Driver.getById(arguments.getLong("id"));
        end = arguments.getBoolean("end");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for fragment
        return inflater.inflate(R.layout.fragment_mileage_input, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        final TextView textView_driverName = (TextView)getView().findViewById(R.id.textView_driverName);
        textView_mileage = (TextView)getView().findViewById(R.id.textView_mileage);

        final Button button_0 = (Button)getView().findViewById(R.id.button_0);
        final Button button_1 = (Button)getView().findViewById(R.id.button_1);
        final Button button_2 = (Button)getView().findViewById(R.id.button_2);
        final Button button_3 = (Button)getView().findViewById(R.id.button_3);
        final Button button_4 = (Button)getView().findViewById(R.id.button_4);
        final Button button_5 = (Button)getView().findViewById(R.id.button_5);
        final Button button_6 = (Button)getView().findViewById(R.id.button_6);
        final Button button_7 = (Button)getView().findViewById(R.id.button_7);
        final Button button_8 = (Button)getView().findViewById(R.id.button_8);
        final Button button_9 = (Button)getView().findViewById(R.id.button_9);

        final ImageButton button_delete = (ImageButton)getView().findViewById(R.id.imageButton_delete);
        final ImageButton button_confirm = (ImageButton)getView().findViewById(R.id.imageButton_confirm);

        textView_driverName.setText(driver.name);
        textView_mileage.setText(mileage.toString());

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);

        button_delete.setOnClickListener(this);
        button_confirm.setOnClickListener(this);

        super.onActivityCreated(savedInstanceState);
    }

    public void onClick(View view) {
        if (String.valueOf(mileage).length() <= 6) {
            switch (view.getId()) {
                case R.id.button_0:
                    typeIntegerIntoMileage(0);
                    break;
                case R.id.button_1:
                    typeIntegerIntoMileage(1);
                    break;
                case R.id.button_2:
                    typeIntegerIntoMileage(2);
                    break;
                case R.id.button_3:
                    typeIntegerIntoMileage(3);
                    break;
                case R.id.button_4:
                    typeIntegerIntoMileage(4);
                    break;
                case R.id.button_5:
                    typeIntegerIntoMileage(5);
                    break;
                case R.id.button_6:
                    typeIntegerIntoMileage(6);
                    break;
                case R.id.button_7:
                    typeIntegerIntoMileage(7);
                    break;
                case R.id.button_8:
                    typeIntegerIntoMileage(8);
                    break;
                case R.id.button_9:
                    typeIntegerIntoMileage(9);
                    break;
            }
        }

        switch (view.getId()) {
            case R.id.imageButton_confirm:
                // TODO warn if mileage is below previous

                if (end) {
                    // Sign-out
                    LogItem logItem = LogItem.getCurrent();
                    logItem.signOut(mileage);
                    mainActivity.doDriveNavigate();
                }
                else {
                    // Sign-in
                    LogItem logItem = new LogItem(driver, mileage);
                    logItem.save();
                    mainActivity.doDriveNavigate();
                }
                break;
            case R.id.imageButton_delete:
                // Convert mileage to a string, remove last char and convert back to integer
                // Rather messy... yeah.
                String mileageString = String.valueOf(mileage);
                if (mileageString.length() > 1) {
                    mileage = Integer.valueOf(mileageString.substring(0, mileageString.length() - 1));
                }
                else {
                    mileage = 0;
                }
                break;
        }

        // Update mileage text, presume most button presses will change this.
        textView_mileage.setText(mileage.toString());
    }

    public void typeIntegerIntoMileage(Integer i) {
        if (mileage == 0) {
            mileage = i;
        }
        else {
            mileage = (mileage * 10) + i;
        }
    }


}
