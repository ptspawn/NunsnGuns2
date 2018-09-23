package org.herebdragons.gameobjects;

import org.herebdragons.graphics.objects.Rectangle;
import org.herebdragons.graphics.objects.notSoSimpleObject;

import java.awt.*;

public abstract class AbstractActor implements Actor{

    protected notSoSimpleObject object;

    public void render(Graphics g) {
        object.render(g);
    }

    public notSoSimpleObject getObject() {
        return object;
    }

    public abstract void tick();
}
