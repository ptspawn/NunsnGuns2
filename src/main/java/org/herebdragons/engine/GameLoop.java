package org.herebdragons.engine;

import org.herebdragons.graphics.canvas.notSoSimpleCanvas;
import org.herebdragons.utils.Logger;

public class GameLoop implements notSoSimpleGameLoop {

    private notSoSimpleCanvas canvas;
    private WorldManager worldManager;

    GameLoop() {
    }

    GameLoop(notSoSimpleCanvas canvas) {
        this.canvas = canvas;

    }

    public void tick() {
        worldManager.tick();
    }

    public void render() {
        Logger.log(this + " Rendering...");
        canvas.update();
    }

    public notSoSimpleCanvas getCanvas() {
        return canvas;
    }

    public void setCanvas(notSoSimpleCanvas canvas) {
        this.canvas = canvas;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public void setWorldManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }
}
