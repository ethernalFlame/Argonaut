package com.argonaut.actors;

import com.argonaut.BaseActor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by vladi on 12.02.2018.
 */

public class Enemy extends BaseActor {
    private float hp, damage, hpPosX, hpPosY, hpAspect;
    private Protagonist protagonist;
    private Texture hpLine;

    public Enemy(float x, float y, float width, float height, Protagonist protagonist) {
        super(new Texture("enemy_test.png"), x, y, width, height);
        this.protagonist = protagonist;
        setOccupied(true);
        hp = 100;
        damage = 10;
        hpLine = new Texture("hp_line.png");
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public void doAction() {
        moveToProtagonist();
        if (hp <=0)
            dispose();
    }

    public void findNearestDoor() {
        moveToProtagonist();
    }

    private void moveToProtagonist() {
        if (checkProtagonistInAttackRange()) {
            attack(protagonist);
        } else {
            float tmpX = this.getX() - protagonist.getX();
            float tmpY = this.getY() - protagonist.getY();

            if (tmpX < 0)
                this.setX(getX() + getWidth());
            else if (tmpX > 0)
                this.setX(getX() - getWidth());

            if (tmpY < 0)
                this.setY(getY() + getHeight());
            else if (tmpY > 0)
                this.setY(getY() - getHeight());
        }

    }
    private boolean checkProtagonistInAttackRange() {
        if (Math.abs(protagonist.getX() - this.getX()) <= getWidth()) {
            if (Math.abs(protagonist.getY() - this.getY()) <= getHeight()) {
                return true;
            }
        }
        return false;
    }

    private void attack(Protagonist protagonist) {
        protagonist.setHp(protagonist.getHp() - damage);
        if (protagonist.getHp()<=0)
            protagonist.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        hpPosX = getX();
        hpPosY = (float) (getY() + getHeight() + getHeight()*0.1);
        hpAspect = (float) (hp*0.01*getWidth());
        batch.draw(hpLine, hpPosX, hpPosY, hpAspect, (float) (getHeight()*0.1));
    }

    @Override
    public void dispose() {
        remove();
        setOccupied(false);
        super.dispose();
    }

}
