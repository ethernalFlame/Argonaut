package com.argonaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by vladi on 08.02.2018.
 */

public abstract class BaseActor extends Actor {

    protected Texture texture;
    protected TextureRegion region;
    private boolean isOccupied = false;

    public BaseActor(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        region = new TextureRegion(texture);
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setOriginX(0);
        this.setOriginY(0);
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public abstract void doAction();

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
    }

    public void resize() {

    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose() {
        texture.dispose();
    }
}
