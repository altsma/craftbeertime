package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class CraftbeerPhotoFactory extends PhotoFactory {
    private static final Logger log = Logger.getLogger(PhotoFactory.class.getName());
    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static PhotoFactory instance = null;

    /**
     *@methodtype constructor
     */
    public CraftbeerPhotoFactory() {
        // do nothing
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     * Public singleton access method.
     * @methodtype get
     */
    public static synchronized PhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic PhotoFactory").toString());
            setInstance(new CraftbeerPhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     * @methodtype set
     */
    protected static synchronized void setInstance(CraftbeerPhotoFactory craftbeerPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize PhotoFactory twice");
        }

        instance = craftbeerPhotoFactory;
    }

    /**
     * @methodtype factory
     */
    @Override
    public Photo createPhoto() {
        return new CraftbeerPhoto();
    }

    /**
     * @methodtype factory
     */
    @Override
    public Photo createPhoto(PhotoId id) {
        return new CraftbeerPhoto(id);
    }
}
