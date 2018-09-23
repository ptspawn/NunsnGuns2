package org.herebdragons.tests;

import org.herebdragons.engine.GameEngine;
import org.herebdragons.gConfig;
import org.herebdragons.graphics.canvas.Canvas;
import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.ObjectManager;
import org.herebdragons.graphics.objects.Rectangle;
import org.herebdragons.graphics.objects.Text;
import org.herebdragons.util.Strings;
import org.herebdragons.utils.Logger;

import java.awt.*;

public class test {

    public static void main(String[] args) {
        Logger.setLogging(gConfig.DEBUG);

        Canvas canvas;

        if (gConfig.FULLSCREEN) {
            canvas = CanvasFactory.createCanvas(Strings.GAME_NAME);
        } else {
            canvas = CanvasFactory.createCanvas(Strings.GAME_NAME, gConfig.DEFAULT_DIMENSION, WindowBehaviour.EXIT_ON_CLOSE, false);
        }

        canvas.setObjectManager(new ObjectManager());

        canvas.addObject(new Text(new Dimension(100, 30), new Point(30, 30), "Teste"));
        canvas.addObject(new Rectangle(new Dimension(10,100),new Point(300,300)));

        Thread threadCanvas = new Thread(canvas);
        threadCanvas.run();

        Thread thread = new Thread(new Runnable() {
            public void run() {
                Logger.err("Here I am");
            }
        });

        thread.start();

        canvas.update();
        /*canvas.setObjectManager(new ObjectManager());

        GameEngine gameEngine = GameEngine.getInstance(canvas);

        gameEngine.start();*/
    }
}
