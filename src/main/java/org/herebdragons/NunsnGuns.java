package org.herebdragons;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.*;
import org.herebdragons.engine.GameEngine;


import org.herebdragons.graphics.canvas.CanvasFactory;
import org.herebdragons.graphics.canvas.notSoSimpleCanvas;
import org.herebdragons.graphics.enums.RendererType;
import org.herebdragons.graphics.enums.WindowBehaviour;
import org.herebdragons.graphics.objects.ObjectManager;
import org.herebdragons.utils.Logger;
import org.herebdragons.utils.Strings;

public class NunsnGuns {

    public static void main(String[] args) {

        //parseCommands(args);

        Logger.setLogging(gConfig.DEBUG);

        notSoSimpleCanvas canvas;

        if (gConfig.FULLSCREEN) {
            canvas = CanvasFactory.createCanvas(Strings.GAME_NAME, RendererType.SWING);
        } else {
            canvas = CanvasFactory.createCanvas(Strings.GAME_NAME, gConfig.DEFAULT_DIMENSION, RendererType.SWING);
        }

        canvas.setObjectManager(new ObjectManager());

        GameEngine gameEngine = GameEngine.getInstance(canvas);

        gameEngine.start();

    }

    private static void parseCommands(String[] args) {

        ArgumentParser parser = ArgumentParsers.newFor(Strings.GAME_NAME).build().description(Strings.GAME_NAME + " argument parsing");
        Namespace ns = null;

        parser.addArgument("-d", "--debug")
                .nargs("?")
                .required(false)
                .setDefault(false)
                .action(Arguments.storeTrue())
                .dest("debug")
                .help("Enables debug mode");

        MutuallyExclusiveGroup fullScreened = parser.addMutuallyExclusiveGroup("FULLSCREEN").required(false);
        ;
        fullScreened.addArgument("-f", "--fullscreen")
                .action(Arguments.storeTrue())
                .nargs("?")
                .required(false)
                .setDefault(false)
                .dest("fullScreen")
                .help("Controls fullscreen");

        MutuallyExclusiveGroup windowed = parser.addMutuallyExclusiveGroup("WINDOWED").required(false);

        windowed.addArgument("width")
                .required(false)
                .nargs("?")
                .metavar("width")
                .type(Integer.class)
                .setDefault(gConfig.DEFAULT_DIMENSION.width)
                .help("width when windowed");
        windowed.addArgument("height")
                .required(false)
                .nargs("?")
                .metavar("height")
                .type(Integer.class)
                .setDefault(gConfig.DEFAULT_DIMENSION.height)
                .help("height when windowed");

        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            System.err.println("Problem parsing arguments " + e.getMessage());
            System.exit(1);
        }

        //FULLSCREEN = ns.get("fullScreen");

        if (ns.get("height") != null)
            gConfig.DEFAULT_DIMENSION.height = ns.get("height");

        if (ns.get("height") != null)
            gConfig.DEFAULT_DIMENSION.width = ns.get("width");

    }


}
