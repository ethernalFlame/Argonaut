package com.argonaut.actors;

import com.argonaut.BaseActor;
import com.badlogic.gdx.graphics.Texture;


public class Protagonist extends BaseActor {
    private float range;

    public Protagonist(float x, float y, float width, float height, float range) {
        super(new Texture("hero.png"), x, y, width, height);
        this.range = range;
    }
    public void move(Tile tile){
        if (Math.abs(tile.getX() - this.getX()) <= range&&Math.abs(tile.getY() - this.getY()) <= range) {
            this.setX(tile.getX());
            this.setY(tile.getY());
            }
        }

    @Override
    public void doAction() {

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
