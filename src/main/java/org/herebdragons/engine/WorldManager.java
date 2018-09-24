package org.herebdragons.engine;


import org.herebdragons.gameobjects.Actor;
import org.herebdragons.graphics.canvas.notSoSimpleCanvas;
import org.herebdragons.graphics.objects.Text;
import org.herebdragons.graphics.objects.notSoSimpleObject;

import java.util.LinkedList;
import java.util.List;

public class WorldManager {

    private List<Actor> world = new LinkedList<Actor>();

    private notSoSimpleCanvas canvas;

    WorldManager() {
    }

    WorldManager(notSoSimpleCanvas canvas) {
        this.canvas = canvas;
    }

    public void addObject(Actor... actors) {
        for (Actor actor : actors) {
            System.out.println("Adding Actor " + actor);
            canvas.addObject(actor.getObject());
            world.add(actor);
        }
    }

    public void hideObject(Actor... actors) {
        for (Actor actor : actors) {
            canvas.hideObject(actor.getObject());
            world.remove(actor);
        }
    }

    public void tick() {
        for (Actor actor : world) {
            actor.tick();
        }
    }

    public notSoSimpleCanvas getCanvas() {
        return canvas;
    }

    public void setCanvas(notSoSimpleCanvas canvas) {
        this.canvas = canvas;
    }
}
