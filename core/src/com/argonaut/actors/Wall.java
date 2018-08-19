package com.argonaut.actors;

import com.argonaut.BaseActor;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vladi on 15.03.2018.
 */

public class Wall extends BaseActor {
    public Wall(Texture texture, float x, float y) {
        super(texture, x, y);
        this.setOccupied(true);
    }

    @Override
    public void doAction() {

    }
}
