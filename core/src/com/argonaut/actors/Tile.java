package com.argonaut.actors;

import com.argonaut.BaseActor;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vladi on 08.02.2018.
 */

public class Tile extends BaseActor implements Cloneable{

    public Tile(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
    }
    public Tile clone(){
        return new Tile(this.texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void print() {
        System.out.println("Я ПЛИТКА");
    }
}
