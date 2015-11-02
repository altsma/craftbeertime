package org.wahlzeit.model;

import org.wahlzeit.utils.StringUtil;

public class CraftbeerPhoto extends Photo {
    private String beerStyle;
    private String breweryName;
    private String country;
    private Location breweryLocation;
    private double abv; // alcohol by volume (percentage-wise)
    private int baRating; // rating on beeradvocate.com

    /**
     * @methodtype constructor
     */
    public CraftbeerPhoto() {
        super();
    }

    /**
     * @methodtype constructor
     */
    public CraftbeerPhoto(PhotoId myId) {
        super(myId);
    }

    /**
     * @methodtype get
     */
    public String getBeerStyle() {
        return beerStyle;
    }

    /**
     * @methodtype set
     */
    public void setBeerStyle(String beerStyle) {
        if (StringUtil.isNullOrEmptyString(beerStyle)) {
            throw new IllegalArgumentException("Beerstyle must not be null nor empty");
        }
        this.beerStyle = beerStyle;
    }

    /**
     * @methodtype get
     */
    public String getBreweryName() {
        return breweryName;
    }

    /**
     * @methodtype set
     */
    public void setBreweryName(String breweryName) {
        if (StringUtil.isNullOrEmptyString(breweryName)) {
            throw new IllegalArgumentException("BreweryName must not be null nor empty");
        }
        this.breweryName = breweryName;
    }

    /**
     * @methodtype get
     */
    public String getCountry() {
        return country;
    }

    /**
     * @methodtype set
     */
    public void setCountry(String country) {
        if (StringUtil.isNullOrEmptyString(country)) {
            throw new IllegalArgumentException("Country must not be null nor empty");
        }
        this.country = country;
    }

    /**
     * @methodtype get
     */
    public Location getBreweryLocation() {
        return breweryLocation;
    }

    /**
     * @methodtype set
     */
    public void setBreweryLocation(Location breweryLocation) {
        this.breweryLocation = breweryLocation;
    }

    /**
     * @methodtype get
     */
    public double getAbv() {
        return abv;
    }

    /**
     * @methodtype set
     */
    public void setAbv(double abv) {
        if (abv < 0.0 || abv > 100.0) {
            throw new IllegalArgumentException("Abv must be set between 0.0 and 100.0");
        }
        this.abv = abv;
    }

    /**
     * @methodtype get
     */
    public int getBaRating() {
        return baRating;
    }

    /**
     * @methodtype set
     */
    public void setBaRating(int baRating) {
        if (baRating < 0 || baRating > 100) {
            throw new IllegalArgumentException("BaRating must be set between 0 and 100");
        }
        this.baRating = baRating;
    }
}
