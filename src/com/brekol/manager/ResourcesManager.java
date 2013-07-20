package com.brekol.manager;

import android.graphics.Color;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.BuildableTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * User: Breku
 * Date: 20.07.13
 */
public class ResourcesManager extends BaseManager {

    private static final ResourcesManager INSTANCE = new ResourcesManager();
    private BaseGameActivity activity;
    private Engine engine;
    private Camera camera;
    private VertexBufferObjectManager vertexBufferObjectManager;

    private BitmapTextureAtlas splashTextureAtlas,fontTextureAtlas;
    private BuildableBitmapTextureAtlas gameTextureAtlas, menuTextureAtlas;

    private ITextureRegion splashTextureRegion, buttonAboutTextureRegion, buttonExitTextureRegion, buttonNewGameTextureRegion,
            buttonOptionsTextureRegion, darkBackgroundTextureRegion;
    private ITiledTextureRegion playerRegion;

    private Font mediumFont;


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

    public void unloadSplashScreen(){
        splashTextureAtlas.unload();
        splashTextureRegion = null;
    }

    public void loadMainMenuResources(){
        loadMainMenuGraphics();
        loadMainMenuFonts();
    }

    private void loadMainMenuFonts(){
        FontFactory.setAssetBasePath("font/");
        fontTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),256,256,TextureOptions.BILINEAR);
        mediumFont = FontFactory.createStrokeFromAsset(activity.getFontManager(),fontTextureAtlas,activity.getAssets(),"mediumFont.ttf",50,true, Color.WHITE,2,Color.WHITE);
        mediumFont.load();
    }

    private void loadMainMenuGraphics(){
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
        menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(),1024,1024,TextureOptions.BILINEAR);

        darkBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas,activity,"background.jpg");
        buttonAboutTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas,activity,"buttonAbout.png");
        buttonExitTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas,activity,"buttonExit.png");
        buttonNewGameTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas,activity,"buttonNewGame.png");
        buttonOptionsTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas,activity,"buttonOptions.png");

        try {
            menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,0,1));
            menuTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }
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

    public ITiledTextureRegion getPlayerRegion() {
        return playerRegion;
    }

    public ITextureRegion getButtonAboutTextureRegion() {
        return buttonAboutTextureRegion;
    }

    public ITextureRegion getButtonExitTextureRegion() {
        return buttonExitTextureRegion;
    }

    public ITextureRegion getButtonNewGameTextureRegion() {
        return buttonNewGameTextureRegion;
    }

    public ITextureRegion getButtonOptionsTextureRegion() {
        return buttonOptionsTextureRegion;
    }

    public ITextureRegion getDarkBackgroundTextureRegion() {
        return darkBackgroundTextureRegion;
    }

    public Font getMediumFont() {
        return mediumFont;
    }
}
