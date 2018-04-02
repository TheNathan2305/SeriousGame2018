package com.mygdx.game.Bodies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Classes.Constants;

/**
 * Created by julienvillegas on 01/02/2017.
 */

public class Floor extends Image {

    private Body bodyFloor;
    private World world;
    private float width;

    public Floor(World aWorld, float pos_x, float pos_y, float aWidth, float aHeight/*, float angle*/){
        super(new Texture("wood.jpg"));
        this.width=aWidth/ Constants.PPM;
        this.setSize(width,aHeight/Constants.PPM);
        // this.setOrigin(this.getWidth()/2,this.getHeight()/2);
        //this.rotateBy(angle);
        this.setPosition(pos_x/Constants.PPM,pos_y/Constants.PPM);//getX() , getY()
        world = aWorld;
        BodyDef groundBodyDef = new BodyDef();
        // Set its world position
        groundBodyDef.type= BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(getX()+getWidth()/2,getY()+getHeight()/2);
        // Create a bodyFloor from the defintion and add it to the world
        bodyFloor = world.createBody(groundBodyDef);
        // Create a polygon shape
        PolygonShape groundBox = new PolygonShape();
        // Set the polygon shape as a box which is twice the size of our view port and 20 high
        // (setAsBox takes half-width and half-height as arguments)
        groundBox.setAsBox(this.getWidth()/2, this.getHeight()/2);
        // bodyFloor.setTransform(this.getX()+this.getWidth()/2,this.getY()+this.getHeight()/2, (float)Math.toRadians(angle));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        // Create a fixture from our polygon shape and add it to our ground bodyFloor
        fixtureDef.filter.categoryBits= Constants.CATEGORY_BLOODSTREAM; //is a
        fixtureDef.filter.maskBits=Constants.CATEGORY_PERSONAJE;//collides with
        fixtureDef.filter.groupIndex=1;
        bodyFloor.createFixture(fixtureDef);
        // Clean up after ourselves
        groundBox.dispose();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void updateImage_Body()
    {
        this.setPosition(bodyFloor.getPosition().x-this.getWidth()/2, bodyFloor.getPosition().y-this.getHeight()/2);
    }

    public Body getBodyFloor() {
        return bodyFloor;
    }


    @Override
    public float getWidth() {
        return width;
    }
}

