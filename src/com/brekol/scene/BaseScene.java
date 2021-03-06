package com.brekol.scene;

import android.app.Activity;
import com.brekol.manager.ResourcesManager;
import com.brekol.util.SceneType;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * User: Breku
 * Date: 20.07.13
 */
public abstract class BaseScene extends Scene{

    protected Engine engine;
    protected Activity activity;
    protected ResourcesManager resourcesManager;
    protected VertexBufferObjectManager vertexBufferObjectManager;
    protected Camera camera;

    public BaseScene() {
        this.resourcesManager = ResourcesManager.getInstance();
        this.engine = resourcesManager.getEngine();
        this.activity = resourcesManager.getActivity();
        this.vertexBufferObjectManager = resourcesManager.getVertexBufferObjectManager();
        this.camera = resourcesManager.getCamera();
        createScene();
    }


    public abstract void createScene();

    public abstract void onBackKeyPressed();

    public abstract SceneType getSceneType();

    public abstract void disposeScene();
}
