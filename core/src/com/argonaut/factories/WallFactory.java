package com.argonaut.factories;

import com.argonaut.actors.Tile;
import com.argonaut.actors.Wall;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by vladi on 15.03.2018.
 */

public class WallFactory {
    private WallFactory(){}

    public static ArrayList<Wall> getWallsAroundRoom(Tile[][] tiles){
        ArrayList <Wall> walls = new ArrayList<Wall>();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getX()==0||tiles[i][j].getY()==0)
                    walls.add(new Wall(new Texture("wall_test.png"), tiles[i][j].getX(),tiles[i][j].getY(), tiles[i][j].getWidth(), tiles[i][j].getHeight()));
            }
        }
        return walls;
    }
}
