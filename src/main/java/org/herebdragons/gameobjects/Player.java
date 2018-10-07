package org.herebdragons.gameobjects;


import org.herebdragons.graphics.objects.Rectangle;

import java.awt.*;

public class Player extends AbstractActor {

    public Player() {
        object = new Rectangle(new Dimension(100, 100), new Point(100, 100));
     //   ((Rectangle) object).fill(new Color(255, 0, 0, 255));
    }


    public void tick() {
        //System.out.println(this + " - has been ticked");
        object.move(20, 0);
    }

    @Override
    public String toString() {
        return "Player {" +
                object.getDimension() +
                " " + object.getPosition() + "}";
    }
}
