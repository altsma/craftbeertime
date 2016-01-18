package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Entity;
import org.wahlzeit.services.DataObject;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Craftbeer extends DataObject{
    private CraftbeerType craftbeerType;
    private Date brewDate;
    private Date bestBefore;
    private Date buyingDate;
    private int batchNumber;
    private int bottleNumber;

    /**
     * @methodtype constructor
     */
    public Craftbeer(CraftbeerType craftbeerType, Date brewDate, Date bestBefore, Date buyingDate, int batchNumber, int bottleNumber) {
        setCraftbeerType(craftbeerType);
        setBrewDate(brewDate);
        setBestBefore(bestBefore);
        setBuyingDate(buyingDate);
        setBatchNumber(batchNumber);
        setBottleNumber(bottleNumber);
    }

    /**
     * @methodtype get
     */
    public CraftbeerType getCraftbeerType() {
        return craftbeerType;
    }

    /**
     * @methodtype set
     */
    public void setCraftbeerType(CraftbeerType craftbeerType) {
        this.craftbeerType = craftbeerType;
    }

    /**
     * @methodtype get
     */
    public Date getBrewDate() {
        return brewDate;
    }

    /**
     * @methodtype set
     */
    public void setBrewDate(Date brewDate) {
        assertIsValidDate(brewDate);
        this.brewDate = brewDate;
    }

    /**
     * @methodtype get
     */
    public Date getBestBefore() {
        return bestBefore;
    }

    /**
     * @methodtype set
     */
    public void setBestBefore(Date bestBefore) {
        assertIsValidDate(bestBefore);
        this.bestBefore = bestBefore;
    }

    /**
     * @methodtype get
     */
    public Date getBuyingDate() {
        return buyingDate;
    }

    /**
     * @methodtype set
     */
    public void setBuyingDate(Date buyingDate) {
        this.buyingDate = buyingDate;
    }

    /**
     * @methodtype get
     */
    public int getBatchNumber() {
        return batchNumber;
    }

    /**
     * @methodtype set
     */
    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    /**
     * @methodtype get
     */
    public int getBottleNumber() {
        return bottleNumber;
    }

    /**
     * @methodtype set
     */
    public void setBottleNumber(int bottleNumber) {
        this.bottleNumber = bottleNumber;
    }

    /**
     * @methodtype conversion
     */
    protected static int getHash(Date buyingDate) {
        return buyingDate.hashCode();
    }

    /**
     * @methodtype conversion
     */
    public int getHash() {
        return Craftbeer.getHash(buyingDate);
    }

    /**
     * @methodtype assertion
     */
    private void assertIsValidDate(Date date) {
        if (Calendar.getInstance().getTime().before(date)) {
            throw new IllegalArgumentException("The specified date is in the future!");
        }
    }
}
