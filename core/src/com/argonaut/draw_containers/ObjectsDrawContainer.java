package com.argonaut.draw_containers;

import com.argonaut.BaseActor;
import com.argonaut.GameScreen;
import com.argonaut.actors.interface_markers.Decoration;
import com.argonaut.actors.interface_markers.Floor;
import com.argonaut.actors.interface_markers.Item;
import com.argonaut.actors.interface_markers.Mob;
import com.argonaut.actors.interface_markers.Ui;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectsDrawContainer {

    private static final String FLOOR = "floor";
    private static final String DECORATIONS = "decorations";
    private static final String MOBS = "mobs";
    private static final String ITEMS = "items";
    private static final String EFFECTS = "effects";
    private static final String UI = "ui";
    private Map<String, List<BaseActor>> actorMap;
    private GameScreen screen;

    public ObjectsDrawContainer(GameScreen screen) {
        this.screen = screen;
        this.actorMap = new HashMap<String, List<BaseActor>>();
        actorMap.put(FLOOR, new ArrayList<BaseActor>(1000));
        actorMap.put(DECORATIONS, new ArrayList<BaseActor>(1000));
        actorMap.put(MOBS, new ArrayList<BaseActor>(20));
        actorMap.put(ITEMS, new ArrayList<BaseActor>());
        actorMap.put(EFFECTS, new ArrayList<BaseActor>());
        actorMap.put(UI, new ArrayList<BaseActor>(20));
    }

    public void add(BaseActor actor) {
        if (actor instanceof Floor)
            actorMap.get(FLOOR).add(actor);
        else if (actor instanceof Decoration)
            actorMap.get(DECORATIONS).add(actor);
        else if (actor instanceof Mob)
            actorMap.get(MOBS).add(actor);
        else if (actor instanceof Item)
            actorMap.get(ITEMS).add(actor);
        else if (actor instanceof Ui)
            actorMap.get(UI).add(actor);
        else return;
    }

    public void drawObjects(long delta) {
        Batch batch = screen.getBatch();
        batch.setProjectionMatrix(screen.getCamera().combined);
        screen.getCamera().update();
        batch.begin();
        for (BaseActor a :
                actorMap.get(FLOOR)) {
            a.draw(batch, delta);
        }
        for (BaseActor a :
                actorMap.get(DECORATIONS)) {
            a.draw(batch, delta);
        }
        for (BaseActor a :
                actorMap.get(MOBS)) {
            a.draw(batch, delta);
        }
        for (BaseActor a :
                actorMap.get(ITEMS)) {
            a.draw(batch, delta);
        }
        for (BaseActor a :
                actorMap.get(EFFECTS)) {
            a.draw(batch, delta);
        }
        batch.end();
        batch.setProjectionMatrix(screen.getCamera().projection);
        batch.begin();
        for (BaseActor a :
                actorMap.get(UI)) {
            a.draw(batch, delta);
        }
        batch.end();
    }

}
