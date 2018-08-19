package com.argonaut;

import com.argonaut.utils.ObjectProperties;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by vladi on 07.06.2018.
 */

public class Item extends BaseActor {

    public Item(TextureRegion texture, float x, float y) {
        super(texture, x, y);
        setWidth(ObjectProperties.getItemWidth());
        setHeight(ObjectProperties.getItemWidth());
        isStatic = true;
    }

    @Override
    public void doAction() {

    }
}
