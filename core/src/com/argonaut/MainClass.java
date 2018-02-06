package com.argonaut;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MainClass extends Game implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera;
	float x = 0, y = 0, aspect;
	
	@Override
	public void create () {

		aspect = (float) Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getWidth()/aspect);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		aspect = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		aspect = (float)Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
		camera.viewportWidth = 1000;
		camera.viewportHeight = 1000/aspect;
	}

	@Override
	public void render () {
		Gdx.input.setInputProcessor(this);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		batch.begin();
		batch.draw(img, 0, 0, 1000,1000);
		batch.end();
		camera.position.set(x,y,0);
		if (x<1000) {
			x++;
			y++;
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 vector2 = camera.unproject(new Vector3(screenX, screenY, 0));
		System.out.println("x: " + screenX + " y: " + screenY);
		System.out.println("matrix x: " + vector2.x + " y: " + vector2.y + " z: " + vector2.z	);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
