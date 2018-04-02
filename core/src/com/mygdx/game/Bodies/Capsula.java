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

/**
 * Created by Jonathan on 24/03/2018.
 */

public class Capsula extends Image{


    //private ParticleEffect effect;
    private Body bodyCapsula;
    private World world;

    public Capsula(World aWorld, float pos_x, float pos_y){
        super(new Texture("capsula.png"));
        this.setSize(Constants.DEVICE_WIDTH/6/Constants.PPM,Constants.DEVICE_HEIGHT/4.8f/Constants.PPM);
        this.setPosition(pos_x/ Constants.PPM,pos_y/Constants.PPM);

        world = aWorld;
        /*effect = new ParticleEffect();
        effect.load(Gdx.files.internal("bubleNote.p"), SeriousGame.textureAtlas);
        effect.start();
        effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());*/
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("body.json"));

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());

        // Create a bodyCapsula in the world using our definition
        bodyCapsula = world.createBody(bodyDef);

        // Now define the dimensions of the physics shape
        //PolygonShape shape = new PolygonShape();
        // We are a box, so this makes sense, no?
        // Basically set the physics polygon to a box with the same dimensions as our sprite
        // shape.setAsBox(this.getWidth()/2, this.getHeight()/2);

        // FixtureDef is a confusing expression for physical properties
        // Basically this is where you, in addition to defining the shape of the bodyCapsula
        // you also define it's properties like density, restitution and others we will see shortly
        // If you are wondering, density and area are used to calculate over all mass
        FixtureDef fixtureDef = new FixtureDef();
        // fixtureDef.shape = shape;
       /* fixtureDef.density = 5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution= 1f;*/
        fixtureDef.filter.categoryBits= Constants.CATEGORY_CAPSULA; //is a
        fixtureDef.filter.maskBits=Constants.CATEGORY_BLOODSTREAM;//collides with
        fixtureDef.filter.maskBits=Constants.CATEGORY_PERSONAJE;
        fixtureDef.filter.groupIndex=1;
        //bodyCapsula.createFixture(fixtureDef);
        loader.attachFixture(bodyCapsula, "capsula", fixtureDef, getWidth());
        // Fixture fixture = bodyCapsula.createFixture(fixtureDef);

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
        // this.setRotation(bodyCapsula.getAngle()*  MathUtils.radiansToDegrees);

        this.setPosition(bodyCapsula.getPosition().x, bodyCapsula.getPosition().y);
        // effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());
        // effect.update(delta);

    }

    public Body getBodyCapsula() {
        return bodyCapsula;
    }


}
