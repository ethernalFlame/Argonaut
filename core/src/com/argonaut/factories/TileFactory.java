package com.argonaut.factories;

import com.argonaut.actors.floor.Tile;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by vladi on 08.02.2018.
 */

public class TileFactory {
    static float x = 0, y = 0;
    private TileFactory(){}


    public static Tile[][] getTiles(int width, int height, Tile tile){
        Tile[][] tiles = new Tile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
              tiles[i][j] = new Tile(tile.getTexture(), x, y);
              x+=tile.getWidth();
            }
            y+=tile.getHeight();
            x=0;
        }
        y=0;
        return tiles;
    }
    public static void draw(Tile[][] tiles, Batch batch, float parentAlpha){
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j].draw(batch,parentAlpha);
            }
        }
    }


}
