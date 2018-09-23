package org.herebdragons.gameobjects;


import org.herebdragons.graphics.objects.Rectangle;

import java.awt.*;

public class Player extends AbstractActor {

    public Player() {
        object = new Rectangle(new Dimension(100, 100), new Point(0,0));
        ((Rectangle) object).fill(new Color(255,255,255,1));
    }


    public void tick() {
        object.move(5,0);
    }


}
