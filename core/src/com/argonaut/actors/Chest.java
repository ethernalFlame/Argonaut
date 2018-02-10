package com.argonaut.actors;

import com.argonaut.BaseActor;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vladi on 10.02.2018.
 */

public class Chest extends BaseActor {
    public Chest(float x, float y, float width, float height) {
        super(new Texture("chest.png"), x, y, width, height);
    }

    @Override
    public void doAction() {
        System.out.println("Я СУНДУК");
    }
}
