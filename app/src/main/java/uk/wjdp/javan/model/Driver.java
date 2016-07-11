package uk.wjdp.javan.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by will on 11/07/16.
 */
@Table(name = "Drivers")
public class Driver extends Model {
    @Column(name = "name")
    public String name;

    public Driver(){
        super();
    }

    public Driver(String name){
        super();
        this.name = name;
    }

    public static List<Driver> getAll() {
        return new Select()
                .from(Driver.class)
                .orderBy("name ASC")
                .execute();
    }

    public static Driver getById(long id) {
        return new Select()
                .from(Driver.class)
                .where("id = ?", id)
                .executeSingle();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
