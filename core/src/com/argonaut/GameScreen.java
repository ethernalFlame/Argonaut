package com.argonaut;

import com.argonaut.actors.Tile;
import com.argonaut.factories.TileFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by vladi on 06.02.2018.
 */

public class GameScreen extends Stage implements Screen {
    private BaseActor actor;
//    OrthographicCamera camera;
    float x = 0, y = 0, aspect;
    Vector3 vector3Pos = new Vector3();
    BaseActor currentActor;
    Tile[][] tiles;
    public GameScreen(Game game){


    }

    @Override
    public void show() {
        this.getCamera().viewportWidth = 1000;
        this.getCamera().viewportHeight = 1000;
        System.out.println("show");
        actor = new BaseActor(new Texture("badlogic.jpg"), 0, 0, 1000, 1000);
        addActor(actor);
        aspect = (float) Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
//        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth()/aspect);
        tiles = TileFactory.getTiles(10,10, new Tile(new Texture("tile.png"), 0,0, 64,64));
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                addActor(tiles[i][j]);
            }
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
       // actor.draw(getBatch(),delta);
        TileFactory.draw(tiles, getBatch(), delta);
        getBatch().end();
        getCamera().position.set(x,y,0);
    }
    public void updateCameraPos(int x, int y){
        float tmpX = x-this.x;
    }
    @Override
    public void resize(int width, int height) {
        System.out.println("resize");
        aspect = (float)Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
        getCamera().viewportWidth = 1000;
        getCamera().viewportHeight = 1000/aspect;
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

            if (currentActor!=null) {
                currentActor.print();
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