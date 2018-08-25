package com.argonaut;

import com.argonaut.actors.decorations.Chest;
import com.argonaut.actors.decorations.Wall;
import com.argonaut.actors.floor.Tile;
import com.argonaut.actors.mobs.Enemy;
import com.argonaut.actors.mobs.Protagonist;
import com.argonaut.actors.ui.InventoryIcon;
import com.argonaut.factories.TileFactory;
import com.argonaut.factories.WallFactory;
import com.argonaut.items.LevelOneArgonautArmor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

/**
 * Created by vladi on 06.02.2018.
 */

public class GameScreen extends Stage implements Screen {
    // TODO: 07.06.2018 надо разделить touchdown и touchup. Сохраняем объект из тачдауна,
    // потом если совпадает в тачдаун - исполняем уже действие

    private static final int cameraMovesPerSecond = 30;
    private static final float CAMERA_WIDTH = 384;

    float x = 0, y = 0, aspect, offsetX, offsetY;
    Vector3 vector3Pos = new Vector3();
    BaseActor currentActor;
    Tile[][] tiles;
    boolean isCameraMoving, isCameraSummoned;
    int countCameraMoves = 0;
    Chest chest;
    Protagonist protagonist;
    Wall wall;
    ArrayList<Enemy> enemies;
    ArrayList<Wall> walls;
    InventoryIcon inventoryIcon;
    LevelOneArgonautArmor levelOneArgonautArmor;

    public GameScreen(Game game) {

    }

    @Override
    public void show() {
        wall = new Wall(new Texture("wall_test.png"), 0, 64);
        chest = new Chest(144,144);
        aspect = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        tiles = TileFactory.getTiles(10, 10, new Tile(new Texture("tile_clear.png"), 0, 0));
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                addActor(tiles[i][j]);
            }
        }
        protagonist = Protagonist.getProtoganist();
        addActor(protagonist);
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(256,256, protagonist));
        chest.setX(tiles[0][2].getX());
        chest.setY(tiles[0][2].getY());
        addActor(chest);
        for (int i = 0; i < enemies.size(); i++) {
            addActor(enemies.get(i));
        }
        walls = WallFactory.getWallsAroundRoom(tiles);
        for (int i = 0; i < walls.size(); i++) {
            addActor(walls.get(i));
        }
        inventoryIcon = new InventoryIcon(50,50,30,30);
        addActor(inventoryIcon);

        x = protagonist.getX();
        y = protagonist.getY();

        levelOneArgonautArmor = new LevelOneArgonautArmor(180, 180);
        addActor(levelOneArgonautArmor);
        getCamera().position.set(x, y, 0);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        getBatch().setProjectionMatrix(getCamera().combined);
        getCamera().update();
        getBatch().begin();
        TileFactory.draw(tiles, getBatch(), delta);
        protagonist.draw(getBatch(), delta);
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(getBatch(), delta);
        }
        for (int i = 0; i < walls.size(); i++) {
            walls.get(i).draw(getBatch(),delta);
        }
        chest.draw(getBatch(), delta);

        getBatch().end();
        updateCameraPos(x,y);
        getCamera().position.set(x, y, 0);

        //ЭТО ШОБ НЕ В МИРОВЫХ КООРДИНАТАХ, А В СКРИНОВЫХ
        getBatch().setProjectionMatrix(getCamera().projection);
        getBatch().begin();

        inventoryIcon.draw(getBatch(), delta);
        levelOneArgonautArmor.draw(getBatch(), delta);
        getBatch().end();

    }

    public void updateCameraPos(float x, float y) {
        if (isCameraSummoned) {
            if (isCameraMoving) {
                offsetX = (x - this.x) / cameraMovesPerSecond;
                offsetY = (y - this.y) / cameraMovesPerSecond;
                countCameraMoves = 0;
                isCameraMoving = false;
            } else {
                if (countCameraMoves < cameraMovesPerSecond) {
                    this.x += offsetX;
                    this.y += offsetY;
                    countCameraMoves++;
                } else {
                    countCameraMoves = 0;
                    isCameraSummoned = false;
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        aspect = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        getCamera().viewportWidth = CAMERA_WIDTH;
        getCamera().viewportHeight = CAMERA_WIDTH / aspect;

        inventoryIcon.setX(getCamera().viewportWidth / 2 - inventoryIcon.getWidth());
        inventoryIcon.setY(getCamera().viewportHeight / 2 - inventoryIcon.getHeight());

        inventoryIcon.inventoryInterface.setX(getCamera().viewportWidth / 2 - inventoryIcon.inventoryInterface.getWidth());
        inventoryIcon.inventoryInterface.setY(getCamera().viewportHeight / 2 - inventoryIcon.inventoryInterface.getHeight() - 30);

        levelOneArgonautArmor.setX(getCamera().viewportWidth / 2 - levelOneArgonautArmor.getWidth() - 9);
        levelOneArgonautArmor.setY(getCamera().viewportHeight / 2 - levelOneArgonautArmor.getHeight() - 38);
    }

    @Override
    public void pause(){
    }

    @Override
    public void resume() {

    }

    public void moveCamera(){
        isCameraMoving = true;
        isCameraSummoned = true;
        updateCameraPos(protagonist.getX() + currentActor.getWidth()/2, protagonist.getY() + currentActor.getHeight() / 2);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        vector3Pos.set(getCamera().unproject(new Vector3(screenX, screenY, 0)));


        //ЭТО МАГИЯ ДЛЯ ВЕКТОРА, КОТОРЫЙ ИЩЕТ НА СКИНЕ СТАТИЧНЫЕ ОБЪЕКТЫ
        Vector3 tmp = new Vector3(vector3Pos);
        tmp.mul(getCamera().view);

        try {
            currentActor = (BaseActor) hit(tmp.x, tmp.y, true);
            if (currentActor != null && currentActor.isStatic)
                protagonist.doAction(currentActor);
            else {
                currentActor = (BaseActor) hit(vector3Pos.x, vector3Pos.y, true);
                if (!currentActor.isStatic)
                    protagonist.doAction(currentActor);
            }

            if (protagonist.isTurnEnd()) {
                    for (int i = 0; i < enemies.size(); i++) {
                        enemies.get(i).doAction();
                        if (enemies.get(i).getHp()<=0){
                            enemies.get(i).dispose();
                            enemies.remove(i);
                        }
                    }
                    moveCamera();
                    protagonist.setTurnEnd(false);
                }
            } catch (Exception e) {
            e.printStackTrace();
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public void hide() {

    }
}