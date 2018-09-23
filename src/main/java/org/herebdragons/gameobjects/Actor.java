package org.herebdragons.gameobjects;

import org.herebdragons.graphics.objects.Drawable;
import org.herebdragons.graphics.objects.notSoSimpleObject;

import java.awt.*;

public interface Actor {

    void tick();

    abstract notSoSimpleObject getObject();

}
