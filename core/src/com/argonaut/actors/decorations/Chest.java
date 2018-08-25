package com.argonaut.actors.decorations;

import com.argonaut.BaseActor;
import com.argonaut.actors.interface_markers.Decoration;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vladi on 10.02.2018.
 */

public class Chest extends BaseActor implements Decoration {
    public Chest(float x, float y) {
        super(new Texture("chest.png"), x, y);
        setOccupied(true);
    }

    @Override
    public void doAction() {

    }
}
