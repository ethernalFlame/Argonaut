package com.argonaut.actors;

import com.argonaut.BaseActor;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vladi on 12.02.2018.
 */

public class Enemy extends BaseActor {
    private float hp, damage;
    private Protagonist protagonist;

    public Enemy(float x, float y, float width, float height, Protagonist protagonist) {
        super(new Texture("enemy_test.png"), x, y, width, height);
        this.protagonist = protagonist;
        setOccupied(true);
        hp = 100;
        damage = 10;
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
        if (hp <=0)
            dispose();
    }
    private boolean checkProtagonistInAttackRange() {
        if (Math.abs(protagonist.getX() - this.getX()) <= getWidth()) {
            if (Math.abs(protagonist.getY() - this.getY()) <= getHeight()) {
             //   attack(protagonist);
                return true;
            }
        }
        return false;
    }

    private void attack(Protagonist protagonist) {
        System.out.println("attack!!!!!!1");
        protagonist.setHp(protagonist.getHp() - damage);
        if (protagonist.getHp()<=0)
            protagonist.dispose();
    }
}
