package com.argonaut;

import com.argonaut.actors.Chest;
import com.argonaut.actors.Enemy;
import com.argonaut.actors.Protagonist;
import com.argonaut.actors.Tile;
import com.argonaut.factories.TileFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by vladi on 06.02.2018.
 */

public class GameScreen extends Stage implements Screen {
    float x = 0, y = 0, aspect, offsetX, offsetY, toPointX, toPointY;
    Vector3 vector3Pos = new Vector3();
    BaseActor currentActor;
    Tile[][] tiles;
    boolean isCameraMoving, isCameraSummoned;
    int countCameraMoves = 0;
    Chest chest;
    Protagonist protagonist;
    Enemy enemy;

    private static final int cameraMovesPerSecond = 30;

    public GameScreen(Game game) {


    }

    @Override
    public void show() {
        this.getCamera().viewportWidth = 1000;
        this.getCamera().viewportHeight = 1000;
        System.out.println("show");
        chest = new Chest(144,144,64,64);
        aspect = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        tiles = TileFactory.getTiles(10, 10, new Tile(new Texture("tile_clear.png"), 0, 0, 64, 64));
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                addActor(tiles[i][j]);
            }
        }
        addActor(chest);
        protagonist = new Protagonist(0,0, 64, 64, 64);
        addActor(protagonist);
        enemy = new Enemy(256,256,64,64, protagonist);
        addActor(enemy);
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
        enemy.draw(getBatch(), delta);
        chest.draw(getBatch(), delta);
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
        getCamera().viewportWidth = 1000;
        getCamera().viewportHeight = 1000 / aspect;
    }

    @Override
    public void pause() {
        System.out.println("PAUSE");
    }

    @Override
    public void resume() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        vector3Pos.set(getCamera().unproject(new Vector3(screenX, screenY, 0)));
        System.out.println("x: " + screenX + " y: " + screenY);
        System.out.println("matrix x: " + vector3Pos.x + " y: " + vector3Pos.y + " z: " + vector3Pos.z);
        try {

            currentActor = (BaseActor) hit(vector3Pos.x, vector3Pos.y, true);

            if (currentActor != null) {
                currentActor.doAction();
            }

            //ТЕСТ ПЕРЕМЕЩЕНИЯ КАМЕРЫ
            if (currentActor instanceof Tile&&!currentActor.isOccupied()) {
                protagonist.move (currentActor);
                enemy.doAction();
                isCameraMoving = true;
                isCameraSummoned = true;
                toPointX = currentActor.getX();
                toPointY = currentActor.getY();
                updateCameraPos(toPointX + currentActor.getWidth()/2, toPointY + currentActor.getHeight()/2);
            }
//            if (currentActor instanceof Enemy){
//                protagonist.attack((Enemy) currentActor);
//
//
//            }
            //ТЕСТ ПЕРЕМЕЩЕНИЯ КАМЕРЫ

        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public void hide() {

    }


}