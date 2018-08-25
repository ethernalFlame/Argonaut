package com.argonaut.actors.decorations;

import com.argonaut.BaseActor;
import com.argonaut.actors.interface_markers.Decoration;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vladi on 15.03.2018.
 */

public class Wall extends BaseActor implements Decoration {
    public Wall(Texture texture, float x, float y) {
        super(texture, x, y);
        this.setOccupied(true);
    }

    @Override
    public void doAction() {

    }
}
