package org.herebdragons;

import org.herebdragons.engine.GameEngine;
import org.herebdragons.graphics.canvas.Canvas;
import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.ObjectManager;
import org.herebdragons.util.Strings;

import java.awt.*;

public class NunsnGuns {

    public static void main(String[] args) {

        //Canvas canvas = CanvasFactory.createCanvas("NunsnGuns");

        Canvas canvas = CanvasFactory.createCanvas(Strings.GAME_NAME,new Dimension(800,600) ,WindowBehaviour.EXIT_ON_CLOSE,true);

        canvas.setObjectManager(new ObjectManager());

        GameEngine gameEngine = GameEngine.getInstance(canvas);

        gameEngine.start();

    }
}
