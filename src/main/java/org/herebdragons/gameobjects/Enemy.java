package org.herebdragons.gameobjects;

import org.herebdragons.graphics.objects.Rectangle;

import java.awt.*;

public class Enemy extends AbstractActor {

    public Enemy() {
        object = new Rectangle(new Dimension(500+500,100+100),new Point(500,500));
        ((Rectangle) object).fill(new Color(0, 255, 0, 255));
    }

    public void tick() {
        //System.out.println(this + " - has been ticked");
    }

    @Override
    public String toString() {
        return "Enemy{" +
                object.getDimension() +
                " " + object.getPosition() + "}";
    }
}
