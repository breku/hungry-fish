package com.brekol.manager;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * User: Breku
 * Date: 20.07.13
 */
public class ResourcesManager {

    private static final ResourcesManager INSTANCE = new ResourcesManager();
    private BaseGameActivity activity;
    private Engine engine;
    private Camera camera;
    private VertexBufferObjectManager vertexBufferObjectManager;


    private BitmapTextureAtlas splashTextureAtlas;
    private ITextureRegion splashTextureRegion;


    public static void prepareManager(Engine engine, BaseGameActivity activity, Camera camera, VertexBufferObjectManager vertexBufferObjectManager) {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vertexBufferObjectManager = vertexBufferObjectManager;
    }


    public void loadSplashScreen() {

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.jpg", 0, 0);
        splashTextureAtlas.load();


    }

    public static ResourcesManager getInstance() {
        return INSTANCE;
    }

    public BaseGameActivity getActivity() {
        return activity;
    }

    public Engine getEngine() {
        return engine;
    }

    public Camera getCamera() {
        return camera;
    }

    public VertexBufferObjectManager getVertexBufferObjectManager() {
        return vertexBufferObjectManager;
    }

    public ITextureRegion getSplashTextureRegion() {
        return splashTextureRegion;
    }
}
