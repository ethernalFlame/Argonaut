package com.argonaut.actors;

import com.argonaut.BaseActor;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vladi on 15.03.2018.
 */

public class Wall extends BaseActor {
    public Wall(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
        this.setOccupied(true);
    }

    @Override
    public void doAction() {

    }
}
