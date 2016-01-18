package org.wahlzeit.model;


import org.wahlzeit.utils.StringUtil;

public class CraftbeerType {
    private String beerName;
    private String beerStyle;
    private String breweryName;
    private String country;
    private Location breweryLocation;
    private double abv; // alcohol by volume (percentage-wise)
    private int baRating; // rating on beeradvocate.com

    /**
     * @methodtype constructor
     */
    public CraftbeerType(String beerName, String beerStyle, String breweryName, String country, Location breweryLocation, double abv, int baRating) {
        setBeerName(beerName);
        setBeerStyle(beerStyle);
        setBreweryName(breweryName);
        setCountry(country);
        setBreweryLocation(breweryLocation);
        setAbv(abv);
        setBaRating(baRating);
    }

    /**
     * @methodtype get
     */
    public String getBeerName() {
        return beerName;
    }

    /**
     * @methodtype set
     */
    public void setBeerName(String beerName) {
        assertIsValidString(beerName, "BeerName must not be null nor empty");
        this.beerName = beerName;
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
        assertIsValidString(beerStyle, "Beerstyle must not be null nor empty");
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
        assertIsValidString(breweryName, "BreweryName must not be null nor empty");
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
        assertIsValidString(country, "Country must not be null nor empty");
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

    /**
     * @methodtype conversion
     */
    protected static int getHash(String beerName) {
        return beerName.hashCode();
    }

    /**
     * @methodtype conversion
     */
    public int getHash() {
        return CraftbeerType.getHash(beerName);
    }

    /**
     * @methodtype assertion
     */
    private void assertIsValidString(String string, String s) {
        if (StringUtil.isNullOrEmptyString(string)) {
            throw new IllegalArgumentException(s);
        }
    }
}
