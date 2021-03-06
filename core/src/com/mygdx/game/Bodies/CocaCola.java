package com.mygdx.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Classes.BodyEditorLoader;
import com.mygdx.game.Classes.Constants;


public class CocaCola extends Image {


    private Body body;
    private World world;

    public Body getBody() {
        return body;
    }

    public CocaCola(World aWorld, float pos_x, float pos_y){
        super(new Texture("coca_cola.png"));
        //setTouchable(Touchable.enabled);
        this.setSize(Constants.DEVICE_WIDTH/10/Constants.PPM,Constants.DEVICE_HEIGHT/4.1f/Constants.PPM);

        this.setPosition(pos_x/ Constants.PPM,pos_y/Constants.PPM);

        world = aWorld;

        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("body.json"));
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());
        /*effect = new ParticleEffect();
        effect.load(Gdx.files.internal("bubleNote.p"), SeriousGame.textureAtlas);
        effect.start();
        effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());*/


        // Create a body in the world using our definition
        body = world.createBody(bodyDef);




        // Now define the dimensions of the physics shape
        // PolygonShape shape = new PolygonShape();
        // We are a box, so this makes sense, no?
        // Basically set the physics polygon to a box with the same dimensions as our sprite
        //  shape.setAsBox(this.getWidth()/2, this.getHeight()/2);

        // FixtureDef is a confusing expression for physical properties
        // Basically this is where you, in addition to defining the shape of the body
        // you also define it's properties like density, restitution and others we will see shortly
        // If you are wondering, density and area are used to calculate over all mass
        FixtureDef fixtureDef = new FixtureDef();
        // fixtureDef.shape = shape;
        //fixtureDef.density = 5f;

        //fixtureDef.friction = 0f;
        //fixtureDef.restitution= 1f;
        //Fixture fixture = body.createFixture(fixtureDef);
        fixtureDef.filter.categoryBits= Constants.CATEGORY_ENEMIES; //is a
        fixtureDef.filter.maskBits=(short)(Constants.CATEGORY_BLOODSTREAM|Constants.CATEGORY_PERSONAJE);//collides with

        fixtureDef.filter.groupIndex=1;


        loader.attachFixture(body, "coca_cola", fixtureDef,getWidth());

        body.setUserData(this);
        // Shape is the only disposable of the lot, so get rid of it
        // shape.dispose();
        //this.setOrigin(this.getWidth()/2,this.getHeight()/2);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        //effect.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setPosition(body.getPosition().x,body.getPosition().y);
        // effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());
        // effect.update(delta);

    }




}
