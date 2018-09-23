package org.herebdragons.engine;

import org.herebdragons.graphics.canvas.notSoSimpleCanvas;

import java.awt.*;

public class GameLoop implements notSoSimpleGameLoop {

    private notSoSimpleCanvas canvas;

    GameLoop(notSoSimpleCanvas canvas){
        this.canvas=canvas;
    }

    public void tick() {

    }

    public void render() {
        canvas.update();
    }
}
