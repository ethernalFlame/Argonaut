package com.argonaut;

import com.argonaut.actors.Chest;
import com.argonaut.actors.Enemy;
import com.argonaut.actors.Protagonist;
import com.argonaut.actors.Tile;
import com.argonaut.actors.Wall;
import com.argonaut.factories.TileFactory;
import com.argonaut.factories.WallFactory;
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
    float x = 0, y = 0, aspect, offsetX, offsetY, toPointX, toPointY, cameraHeight, cameraWidth;
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
    float CAMERA_WIDTH = 12f;
    float CAMERA_HEIGHT = 10f;


    private static final int cameraMovesPerSecond = 30;

    public GameScreen(Game game) {


    }

    @Override
    public void show() {
        wall = new Wall(new Texture("wall_test.png"), 0, 64, 64,64);
        chest = new Chest(144,144,64,64);
        aspect = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        tiles = TileFactory.getTiles(10, 10, new Tile(new Texture("tile_clear.png"), 0, 0, 64, 64));
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                addActor(tiles[i][j]);
            }
        }
        protagonist = new Protagonist(0,0, 64, 64, 64);
        addActor(protagonist);
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(256,256,64,64, protagonist));
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
    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(this);
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
       // wall.draw(getBatch(),delta);
        getBatch().end();
        updateCameraPos(x,y);
        getCamera().position.set(x, y, 0);
    }

    public void updateCameraPos(float x, float y) {
        if (isCameraSummoned) {
            if (isCameraMoving) {
                offsetX = (x - this.x) / cameraMovesPerSecond;
                offsetY = (y - this.y) / cameraMovesPerSecond;
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
        System.out.println("resize");
        aspect = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        getCamera().viewportWidth = width;
        getCamera().viewportHeight = width / aspect;
        System.out.println(tiles[0][0].getWidth() + " : " + tiles[0][0].getHeight());
        System.out.println(aspect);
    }

    @Override
    public void pause() {
        System.out.println("PAUSE");
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
        try {
            currentActor = (BaseActor) hit(vector3Pos.x, vector3Pos.y, true);
                protagonist.doAction(currentActor);
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