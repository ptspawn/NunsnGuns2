package org.herebdragons.util;

import org.herebdragons.gConfig;
import org.herebdragons.gameobjects.AbstractActor;
import org.herebdragons.graphics.objects.Text;
import org.herebdragons.graphics.objects.notSoSimpleObject;
import org.herebdragons.utils.FrameRate;

import java.awt.*;

public class FPSCounter extends AbstractActor {

    private FrameRate frameRate;

    public FPSCounter(FrameRate frameRate) {
        object = new Text(new Dimension(200, 50), new Point(30, 30), "");
        this.frameRate = frameRate;
        this.frameRate.setDebug(gConfig.DEBUG);
    }

    public void tick() {
        ((Text) object).setText(frameRate.getResult());
    }


    public int getUPS() {
        return frameRate.getUpdatesPerSecond();
    }


}
