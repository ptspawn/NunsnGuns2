package org.herebdragons.scenes;

public class SceneFactory {

    public static Scene buildScene(SceneType sceneType) {
        Scene scene = null;
        switch (sceneType) {
            case MENU:
                scene = new MainMenu();


        }
        return scene;
    }
}
