package uk.wjdp.javan;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

import uk.wjdp.javan.model.Driver;

/**
 * Created by will on 11/07/16.
 */
public class JavanApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Specify model classes here so ActiveAndroid works with Instant Run
        Configuration.Builder config = new Configuration.Builder(this);
        config.addModelClasses(Driver.class);
        ActiveAndroid.initialize(config.create());
    }
}
