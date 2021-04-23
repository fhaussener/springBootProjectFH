package ch.fhnw.webec.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Place {

    @Id
    @GeneratedValue
    private long id;

    private String placeName;

    @Column(columnDefinition="LONGVARCHAR")
    private String pictureUrl;

    // Calculated in service, not populated directly
    private int avgCoffee;
    private int avgPowerPlug;
    private int avgInternet;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PLACE_ID")
    private final List<Rating> ratings = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAvgCoffee() {
        return avgCoffee;
    }

    public void setAvgCoffee(int avgCoffee) {
        this.avgCoffee = avgCoffee;
    }

    public int getAvgPowerPlug() {
        return avgPowerPlug;
    }

    public void setAvgPowerPlug(int avgPowerPlug) {
        this.avgPowerPlug = avgPowerPlug;
    }

    public int getAvgInternet() {
        return avgInternet;
    }

    public void setAvgInternet(int avgInternet) {
        this.avgInternet = avgInternet;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}
