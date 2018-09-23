package org.herebdragons.engine;

import org.herebdragons.graphics.canvas.notSoSimpleCanvas;
import org.herebdragons.graphics.objects.Text;
import org.herebdragons.util.Strings;
import org.herebdragons.utils.FrameRate;

import java.awt.*;

public class GameEngine implements Runnable {

    private GameLoop gameLoop;
    private static GameEngine engine;
    private notSoSimpleCanvas canvas;
    private volatile boolean running;
    private Thread gameThread;
    private FrameRate frameRate;

    public static GameEngine getInstance(notSoSimpleCanvas canvas) {
        if (engine == null)
            return new GameEngine(canvas);

        return engine;

    }

    private GameEngine(notSoSimpleCanvas canvas) {
        gameLoop = new GameLoop(canvas);
    }

    public void run() {

        canvas.addObject(new Text(new Dimension(200,50),new Point(30,30),frameRate.toString()));

        running = true;

        while (running){

            gameLoop.render();

            sleep(60);

        }


    }

    public void start() {
        //if (!running) {
        //    return;
        //}

        running = true;

        gameThread = new Thread(this, Strings.GAME_NAME + " - Main Thread");

        gameThread.start();
    }

    public void stop() {
        if (!running)
            return;
        running = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);

    }

    private void sleep(long milSec){
        try {
            Thread.sleep(milSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
