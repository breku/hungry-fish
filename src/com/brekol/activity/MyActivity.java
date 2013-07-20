package com.brekol.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import com.brekol.manager.ResourcesManager;
import com.brekol.manager.SceneManager;
import com.brekol.util.ConstantsUtil;
import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.Constants;

import java.io.IOException;

public class MyActivity extends BaseGameActivity {

    private Camera camera;

    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions) {
        return new LimitedFPSEngine(pEngineOptions,30);
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new BoundCamera(0, 0, ConstantsUtil.SCREEN_WIDTH, ConstantsUtil.SCREEN_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        engineOptions.getAudioOptions().setNeedsMusic(true);
        engineOptions.getAudioOptions().setNeedsSound(true);
        engineOptions.getRenderOptions().setDithering(true);
        return engineOptions;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
        ResourcesManager.prepareManager(getEngine(),this,camera,getVertexBufferObjectManager());
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
        getEngine().registerUpdateHandler(new TimerHandler(0.2f,new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                getEngine().unregisterUpdateHandler(pTimerHandler);
                SceneManager.getInstance().createMenuScene();
            }
        }));

        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
        }
        return false;
    }
}
