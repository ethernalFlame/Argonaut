package com.argonaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by vladi on 08.02.2018.
 */

public class BaseActor extends Actor {

    protected Texture texture;
     protected TextureRegion region;

    public BaseActor(Texture texture, float x, float y, float width, float height){
        this.texture = texture;
        region = new TextureRegion(texture);
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setOriginX(0);
        this.setOriginY(0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
    public void print(){
        System.out.println("ITS ME");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
    }

    public void resize(){

    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose(){
        texture.dispose();
    }
}