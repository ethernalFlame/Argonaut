package com.argonaut.actors;

import com.argonaut.BaseActor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by vladi on 06.06.2018.
 */

public class InventoryIcon extends BaseActor {
    public InventoryInterface inventoryInterface;

    public InventoryIcon(float x, float y, float width, float height) {
        super(new Texture("inventory_icon.png"), x, y, width, height);
        this.inventoryInterface = new InventoryInterface(new Texture("inventory.png"),0,0, 96, 384);
        isStatic = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        inventoryInterface.draw(batch, parentAlpha);
        super.draw(batch, parentAlpha);
       // System.out.println(getX() + " " + getY() + " " + getOriginX() + " " + getOriginY());

    }

    @Override
    public void doAction() {

        System.out.println("hit");
        inventoryInterface.interfaceFlag = !inventoryInterface.interfaceFlag;
    }

}
