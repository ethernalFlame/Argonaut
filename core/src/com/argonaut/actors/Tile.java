package com.argonaut.actors;

import com.argonaut.BaseActor;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vladi on 08.02.2018.
 */

public class Tile extends BaseActor implements Cloneable{
    public Tile(Texture texture, float x, float y) {
        super(texture, x, y);
    }
    public Tile clone(){
        return new Tile(this.texture, getX(), getY());
    }

    @Override
    public void doAction() {
        System.out.println("Я ПЛИТКА");
    }
}
