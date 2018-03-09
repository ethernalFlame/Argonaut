package com.argonaut.actors;

import com.argonaut.BaseActor;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vladi on 12.02.2018.
 */

public class Enemy extends BaseActor {
    private float hp;
    private Protagonist protagonist;

    public Enemy(float x, float y, float width, float height, Protagonist protagonist) {
        super(new Texture("enemy.png"), x, y, width, height);
        this.protagonist = protagonist;
    }

    @Override
    public void doAction() {
        moveToProtagonist();
    }

    public void findNearestDoor(){
        moveToProtagonist();
    }

    private void moveToProtagonist(){
        float tmpX = this.getX() - protagonist.getX();
        float tmpY = this.getY() - protagonist.getY();

        if (tmpX < 0)
             this.setX(getX()+getWidth());
         else if (tmpX > 0)
             this.setX(getX()-getWidth());

        if (tmpY < 0)
            this.setY(getY()+getHeight());
        else if (tmpY > 0)
            this.setY(getY()-getHeight());
    }
}
