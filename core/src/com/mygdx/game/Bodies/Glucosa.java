package com.mygdx.game.Bodies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Classes.Constants;


/**
 * Created by Jonathan on 20/03/2018.
 */

public class Glucosa extends Image {

    private Body bodyGlucosa;
    private World world;
    private float w,h;
    public static long lastGlucosa;



    public Glucosa(World aWorld, BloodStream bloodStream/*, float angle*/){
        super(new Texture("glucosa.png"));
        this.world=aWorld;
        this.w=Constants.DEVICE_WIDTH/48f/Constants.PPM;
        this.h=Constants.DEVICE_WIDTH/48f/Constants.PPM;
        this.setSize(w,h);
        // this.setOrigin(this.getWidth()/2,this.getHeight()/2);
        //this.rotateBy(angle);
        this.setPosition(bloodStream.getBodyBoodStream().getPosition().x+this.getWidth(),Constants.DEVICE_HEIGHT/Constants.PPM);
        world = aWorld;
        BodyDef groundBodyDef = new BodyDef();
        // Set its world position
        groundBodyDef.type= BodyDef.BodyType.DynamicBody;
        groundBodyDef.position.set(getX()+getWidth()/2,getY()+getHeight()/2);
        // Create a bodyGlucosa from the defintion and add it to the world
        bodyGlucosa = world.createBody(groundBodyDef);
        // Create a polygon shape
        CircleShape groundBox = new CircleShape();
        // Set the polygon shape as a box which is twice the size of our view port and 20 high
        // (setAsBox takes half-width and half-height as arguments)
        groundBox.setRadius(this.getWidth()/2);
        // bodyGlucosa.setTransform(this.getX()+this.getWidth()/2,this.getY()+this.getHeight()/2, (float)Math.toRadians(angle));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        // Create a fixture from our polygon shape and add it to our ground bodyGlucosa
        fixtureDef.filter.categoryBits= Constants.CATEGORY_GLUCOSA; //is a
        fixtureDef.filter.maskBits= (short) (Constants.CATEGORY_PERSONAJE | Constants.CATEGORY_BLOODSTREAM);//collides with
        fixtureDef.filter.groupIndex=1;
        bodyGlucosa.createFixture(fixtureDef);

        // Clean up after ourselves
        groundBox.dispose();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setPosition(bodyGlucosa.getPosition().x-this.getWidth()/2, bodyGlucosa.getPosition().y-this.getHeight()/2);
        if (bodyGlucosa.getLinearVelocity().y==0){
            bodyGlucosa.setLinearVelocity(1f,bodyGlucosa.getLinearVelocity().y);
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }


    public Body getBodyGlucosa() {
        return bodyGlucosa;
    }

    public void updateImage_Body()
    {
        this.setPosition(bodyGlucosa.getPosition().x-this.getWidth()/2, bodyGlucosa.getPosition().y-this.getHeight()/2);
    }

    public static Glucosa generateArrayGlucose(Glucosa glucosa){
        Glucosa aux_glucosa=glucosa;
        Array<Glucosa> glucosaArray=new Array<Glucosa>();
        glucosaArray.add(aux_glucosa);
        glucosaArray.iterator().next().setPosition(aux_glucosa.getBodyGlucosa().getPosition().x,aux_glucosa.getBodyGlucosa().getPosition().y);
        lastGlucosa=TimeUtils.nanoTime();
        return glucosaArray.iterator().next();
    }
}
