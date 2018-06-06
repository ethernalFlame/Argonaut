package com.argonaut.actors;

import com.argonaut.BaseActor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by vladi on 06.06.2018.
 */

public class InventoryInterface extends BaseActor{
    public boolean interfaceFlag = false;
    public InventoryInterface(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
    }

    @Override
    public void doAction() {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (interfaceFlag)
        super.draw(batch, parentAlpha);
    }
}
