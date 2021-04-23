package ch.fhnw.webec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rating {
    @Id
    @GeneratedValue
    private long id;

    private int coffee;
    private int powerPlug;
    private int internet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCoffee() {
        return coffee;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public int getPowerPlug() {
        return powerPlug;
    }

    public void setPowerPlug(int powerPlug) {
        this.powerPlug = powerPlug;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }
}
