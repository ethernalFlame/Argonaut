package com.argonaut.actors.mobs;

import com.argonaut.BaseActor;
import com.argonaut.Item;
import com.argonaut.actors.floor.Tile;
import com.argonaut.actors.interface_markers.Mob;
import com.argonaut.items.Wearable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;


public class Protagonist extends BaseActor implements Mob {
    private float range;
    public float hp, damage, greenHp, greenDamage;
    private boolean turnEnd = false;
    private static Protagonist protagonist;
    private ArrayList<Item> items;

    public static Protagonist getProtoganist(){
        if (protagonist == null)
            protagonist = new Protagonist(64, 64, 64);
        return protagonist;
    }
    private Protagonist(float x, float y, float range) {
        super(new Texture("argonaut_hero.png"), x, y);
        this.range = range;
        setOccupied(true);
        hp = 100;
        damage = 20;
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

    public void move(BaseActor actor) {
        if (Math.abs(actor.getX() - this.getX()) <= range && Math.abs(actor.getY() - this.getY()) <= range) {
                this.setX(actor.getX());
                this.setY(actor.getY());
                turnEnd = true;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void doAction() {

    }

    public void doAction(BaseActor actor) {
        if (actor instanceof Tile && !actor.isOccupied())
            move(actor);
        else if (actor instanceof Enemy)
            attack((Enemy) actor);
        else actor.doAction();
    }

    public boolean isTurnEnd() {
        return turnEnd;
    }

    public void setTurnEnd(boolean turnEnd) {
        this.turnEnd = turnEnd;
    }

    public void attack(Enemy enemy) {
        enemy.setHp(enemy.getHp() - damage);
        if (enemy.getHp() <= 0){
            }
        turnEnd = true;
    }

    public void equip(Wearable wearable){
        wearable.addStats();
    }

        /*АЛГОРИТМ ПОИСКА ПУТИ:
        СНАЧАЛА ИЩЕМ КООРДИНАТЫ ПРОТОГОНИСТА
        ЗАТЕМ СРАВНИВАЕМ КООРДИНАТЫ ПРОТИВНИКА И ГЕРОЯ
        ЕСЛИ ОНИ БОЛЬШЕ/МЕНЬШЕ -> ДВИГАЕМСЯ В НУЖНОМ НАПРАВЛЕНИИ
        НО НУЖНО УЗНАТЬ, ГДЕ РАЗНИЦА В КООРДИНАТАХ БОЛЬШЕ (Х, У), ГДЕ БОЛЬШЕ ТУДА И ИДЕМ СНАЧАЛА
        НО НУЖЕН МЕТОД findDoor, КОТОРЫЙ ИЩЕТ БЛИЗЖАЙШИЙ ВЫХОД, ТУДА СНАЧАЛА И ИДЕМ (moveTo(findDoor())
         */
        /*
        ВТОРОЙ ВАРИАНТ:
        МЫ ИМЕЕМ ДВУМЕРНЫЙ МАССИВ И ЧЕРЕЗ НЕГО ПЕРЕБОРОМ МЫ ИЩЕМ КРАТЧАЙШИЙ ПУТЬ ДО ДРУГОГО ЭЛЕМЕНТА ДВУМЕРНОГО МАССИВА (ПУТЕМ ПОДСЧЕТА КОЛИЧЕСТВА ШАГОВ
         */
}
