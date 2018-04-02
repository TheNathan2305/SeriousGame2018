package com.mygdx.game.Screens;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Bodies.BloodStream;
import com.mygdx.game.Bodies.Capsula;
import com.mygdx.game.Bodies.CocaCola;
import com.mygdx.game.Bodies.Glucosa;
import com.mygdx.game.Bodies.Personaje;
import com.mygdx.game.Bodies.DynamicFloor;
import com.mygdx.game.Bodies.Floor;
import com.mygdx.game.Classes.Constants;
import com.mygdx.game.Classes.Controller;
import com.mygdx.game.Classes.Escudo;

public class GameScreen implements Screen{
    public static Stage stage;
    Game game;
    private World world;
    private Box2DDebugRenderer debugRenderer;
   // private Pancreas pancreas;
    private Personaje personaje;
    Floor floor1, floor2;
    DynamicFloor dynamicFloor;
    private Skin skin;
    Controller controller;
    Escudo escudo;
    Glucosa glucosa;
    Capsula capsula;
    CocaCola cocaCola;
    BloodStream bloodStream;
    public static final float GRAVITY = -9.8f;
    //CuerpoFlotante cuerpoFlotante;

    public GameScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new StretchViewport(Constants.DEVICE_WIDTH/Constants.PPM,Constants.DEVICE_HEIGHT/Constants.PPM));
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, GRAVITY), true);
        //floor1 =new Floor(world,0f,0f,Constants.DEVICE_WIDTH,Constants.DEVICE_HEIGHT/15);
        bloodStream=new BloodStream(world,Constants.DEVICE_WIDTH/2f/Constants.PPM,Constants.DEVICE_WIDTH/4f/Constants.PPM,
                0f,0f,"bloodstream_curvo2.png");

        floor2 =new Floor(world,Constants.DEVICE_WIDTH+300f,300f,Constants.DEVICE_WIDTH/2,Constants.DEVICE_HEIGHT/15);
        dynamicFloor =new DynamicFloor(world,2*Constants.DEVICE_WIDTH+400f,600f);
        cocaCola=new CocaCola(world,Constants.DEVICE_WIDTH/2,Constants.DEVICE_HEIGHT);
        personaje=new Personaje(world,Constants.DEVICE_WIDTH/4, Constants.DEVICE_HEIGHT/2);
        glucosa=new Glucosa(world,bloodStream);

       // Glucosa.generateArrayGlucose(glucosa);

       // capsula=new Capsula(world,DEVICE_WIDTH/2,DEVICE_HEIGHT/2);


        controller = new Controller();
        stage.addActor(bloodStream);
        stage.addActor(floor2);
        stage.addActor(dynamicFloor);
        stage.addActor(personaje);
        stage.addActor(cocaCola);
        if (TimeUtils.nanoTime()-Glucosa.lastGlucosa>1000000000){
            stage.addActor(Glucosa.generateArrayGlucose(glucosa));
        }

        //stage.addActor(bloodStream);
    }

    @Override
    public void show() {
        Gdx.app.log("MainScreen","show");
    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        debugRenderer.render(world, stage.getCamera().combined);
        world.step(delta, 6, 2);
        controller.draw();
       if (personaje.getBody().getPosition().x>Constants.DEVICE_WIDTH/Constants.PPM/2)
        {
            stage.getCamera().position.x=personaje.getBody().getPosition().x;
        }
      // cuerpoFlotante.updateImage_Body();
      // cuerpoFlotante.Flotar(torrentes);
        dynamicFloor.updateImage_Body();
        glucosa.updateImage_Body();
        dynamicFloor.DesplazarBody(floor2);
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        stage.getCamera().update();
        controller.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void handleInput(){
        if(controller.isRightPressed()){
            personaje.getBody().setLinearVelocity(new Vector2(5f,  personaje.getBody().getLinearVelocity().y));
        }
        else if (controller.isLeftPressed()) {
            personaje.getBody().setLinearVelocity(new Vector2(-5f, personaje.getBody().getLinearVelocity().y));
        }
        else
            personaje.getBody().setLinearVelocity(new Vector2(0,  personaje.getBody().getLinearVelocity().y));
        if (controller.isUpPressed() &&  personaje.getBody().getLinearVelocity().y == 0)
            personaje.getBody().applyLinearImpulse(new Vector2(0, 8f),  personaje.getBody().getWorldCenter(), true);
        if (controller.isDownPressed()){
                if (Escudo.escudoBody_activado.booleanValue()==false){
                    escudo=new Escudo(world,personaje);
                    stage.addActor(escudo);
                }
        }
    }

}
