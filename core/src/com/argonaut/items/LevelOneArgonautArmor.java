package com.argonaut.items;

import com.argonaut.Item;
import com.argonaut.actors.mobs.Protagonist;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by vladi on 07.06.2018.
 */

public class LevelOneArgonautArmor extends Item implements Wearable{
    private float armor = 100;
    public LevelOneArgonautArmor(float x, float y) {
        super(new TextureRegion(new Texture("items.png"), 1, 19, 16, 16), x, y);
    }

    @Override
    public void addStats() {
        Protagonist.getProtoganist().hp += armor;
    }

    @Override
    public void removeStats() {

    }
}
