package uk.wjdp.javan.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Calendar;
import java.util.List;

/**
 * Created by will on 11/07/16.
 */
@Table(name = "Drivers")
public class LogItem extends Model {
    @Column(name = "driver_id")
    public Long driver_id;

    @Column(name = "start_calendar")
    public Calendar start_calendar;
    @Column(name = "start_mileage")
    public Integer start_mileage;

    @Column(name = "end_calendar")
    public Calendar end_calendar;
    @Column(name = "end_mileage")
    public Integer end_mileage;


    public LogItem(){
        super();
    }

    public LogItem(Driver driver, Integer start_mileage){
        super();
        this.driver_id = driver.getId();
        this.start_mileage = start_mileage;
        this.start_calendar = Calendar.getInstance();
    }

    public static List<LogItem> getAll() {
        return new Select()
                .from(LogItem.class)
                .orderBy("start_calendar ASC")
                .execute();
    }

    public static LogItem getById(long id) {
        return new Select()
                .from(LogItem.class)
                .where("id = ?", id)
                .executeSingle();
    }

    @Override
    public String toString() {
        return this.driver_id.toString();
    }
}
