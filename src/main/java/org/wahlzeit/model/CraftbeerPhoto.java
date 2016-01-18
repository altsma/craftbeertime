package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.utils.StringUtil;

@Subclass
public class CraftbeerPhoto extends Photo {
    private Craftbeer craftbeer;

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
    public Craftbeer getCraftbeer() {
        return craftbeer;
    }

    /**
     * @methodtype set
     */
    public void setCraftbeer(Craftbeer craftbeer) {
        this.craftbeer = craftbeer;
    }
}
