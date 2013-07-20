package com.brekol.manager;

import com.brekol.scene.BaseScene;
import com.brekol.scene.LoadingScene;
import com.brekol.scene.MainMenuScene;
import com.brekol.scene.SplashScene;
import com.brekol.util.SceneType;
import org.andengine.ui.IGameInterface;

/**
 * User: Breku
 * Date: 20.07.13
 */
public class SceneManager extends BaseManager {

    private static final SceneManager INSTANCE = new SceneManager();

    private SceneType currentSceneType = SceneType.SPLASH;
    private BaseScene currentScene;
    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
    private BaseScene loadingScene;
    private BaseScene optionsScene;
    private BaseScene aboutScene;

    public static SceneManager getInstance() {
        return INSTANCE;
    }

    public void setScene(BaseScene scene) {
        ResourcesManager.getInstance().getEngine().setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }


    public void setScene(SceneType sceneType) {
        switch (sceneType) {
            case MENU:
                setScene(menuScene);
                break;
            case GAME:
                setScene(gameScene);
                break;
            case SPLASH:
                setScene(splashScene);
                break;
            case LOADING:
                setScene(loadingScene);
                break;
            case ABOUT:
                setScene(aboutScene);
                break;
            case OPTIONS:
                setScene(optionsScene);
                break;
            default:
                break;
        }
    }


    public void createSplashScene(IGameInterface.OnCreateSceneCallback onCreateSceneCallback) {
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        onCreateSceneCallback.onCreateSceneFinished(splashScene);
    }

    public void createMenuScene() {
        ResourcesManager.getInstance().loadMainMenuResources();
        menuScene = new MainMenuScene();
        loadingScene = new LoadingScene();
        setScene(menuScene);
        disposeSplashScene();
    }

    private void disposeSplashScene() {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }

    public BaseScene getCurrentScene() {
        return currentScene;
    }
}
