package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class CraftbeerManager {
    protected static final CraftbeerManager instance = new CraftbeerManager();

    protected Map<Integer, CraftbeerType> craftbeerTypeMap = new HashMap<>();
    protected Map<Integer, Craftbeer> craftbeerMap = new HashMap<>();

    /**
     * @methodtype constructor
     */
    public CraftbeerManager() {
    }

    /**
     * @methodtype get
     */
    public static final CraftbeerManager getInstance() {
        return instance;
    }

    /**
     * @methodtype factory
     */
    public synchronized CraftbeerType createCraftbeerType(String beerName, String beerStyle, String breweryName, String country, Location breweryLocation, double abv, int baRating) {
        CraftbeerType craftbeerType = getCraftbeerType(beerName);
        if (craftbeerType == null) {
            throw new IllegalArgumentException("CraftbeerType already exists");
        }

        craftbeerType = new CraftbeerType(beerName, beerStyle, breweryName, country, breweryLocation, abv, baRating);
        craftbeerTypeMap.put(craftbeerType.getHash(), craftbeerType);

        return craftbeerType;
    }

    /**
     * @methodtype get
     */
    public CraftbeerType getCraftbeerType(String beerName) {
        return craftbeerTypeMap.get(CraftbeerType.getHash(beerName));
    }

    /**
     * @methodtype factory
     */
    public synchronized Craftbeer createCraftbeer(CraftbeerType craftbeerType, Date brewDate, Date bestBefore, Date buyingDate, int batchNumber, int bottleNumber) {
        Craftbeer craftbeer = new Craftbeer(craftbeerType, brewDate, bestBefore, buyingDate, batchNumber, bottleNumber);
        craftbeerMap.put(craftbeer.getHash(), craftbeer);

        return craftbeer;
    }

    /**
     * @methodtype get
     */
    public Craftbeer getCraftbeer(String buyingDate) {
        return craftbeerMap.get(CraftbeerType.getHash(buyingDate));
    }
}
