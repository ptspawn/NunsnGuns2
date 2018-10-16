package org.herebdragons.engine;


import org.herebdragons.gConfig;
import org.herebdragons.gameobjects.Enemy;
import org.herebdragons.gameobjects.Player;
import org.herebdragons.graphics.canvas.notSoSimpleCanvas;
import org.herebdragons.graphics.objects.Rectangle;
import org.herebdragons.graphics.objects.notSoSimpleObject;
import org.herebdragons.utils.FPSCounter;
import org.herebdragons.utils.ThreadManager;
import org.herebdragons.utils.Logger;
import org.herebdragons.utils.FrameRate.FrameRate;

import java.awt.*;

public class GameEngine implements Runnable {

    private GameLoop gameLoop;
    private WorldManager worldManager;
    private static GameEngine engine;
    private notSoSimpleCanvas canvas;
    private volatile boolean running;
    private FrameRate frameRate;
    private ThreadManager threadManager;


    public static GameEngine getInstance(notSoSimpleCanvas canvas) {
        if (engine == null)
            engine = new GameEngine(canvas);

        return engine;

    }

    private GameEngine(notSoSimpleCanvas canvas) {

        Logger.setLogging(gConfig.DEBUG);

        Logger.log("creating Thread Manager");
        threadManager = new ThreadManager(2);

        Logger.log("Creating Canvas");
        this.canvas = canvas;

        threadManager.runTask(canvas);

        Logger.log("Creating World Manager");
        worldManager = new WorldManager();
        worldManager.setCanvas(canvas);

        Logger.log("Creating Game Loop");
        gameLoop = new GameLoop();
        gameLoop.setCanvas(canvas);
        gameLoop.setWorldManager(worldManager);

        Logger.log("Creating FrameDate");
        frameRate = new FrameRate(gConfig.FRAME_RATE);
        Logger.log(frameRate.toString());
    }

    public void run() {

        Logger.log("Entering run method from GameEngine");

        FPSCounter fps = new FPSCounter(frameRate);

        worldManager.addObject(fps);

        Player player = new Player();

        worldManager.addObject(player);

        Enemy box = new Enemy();

        worldManager.addObject(box);

        notSoSimpleObject object = new Rectangle(new Dimension(500, 100), new Point(500, 500));

        canvas.addObject(object);

        frameRate.initialize();

        while (running) {

            Logger.log("Entering Game Cycle");
            //Input

            //Update
            gameLoop.tick();

            //Render
            gameLoop.render();
            frameRate.calculate();

            sleep(frameRate.getRemainingInCyle());
            Logger.log(frameRate.toString());
        }


    }

    public void start() {

        Logger.log("Entering in Start Method of Game Engine");
        running = true;

        threadManager.runTask(this);

    }

    public void stop() {
        if (!running)
            return;

        running = false;

        threadManager.join();

        System.exit(0);

    }

    private void sleep(long milSec) {
        try {
            Thread.sleep(milSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
