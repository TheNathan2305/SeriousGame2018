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

public class DynamicFloor extends Image {

private World world;
private Body body;
private  float LINEAR_VELOCITY_X;

public DynamicFloor(World world, float posX, float posY) {
    super(new Texture("wood.jpg"));
    this.world=world;
    this.setSize(Constants.DEVICE_WIDTH/4/Constants.PPM,Constants.DEVICE_HEIGHT/15/Constants.PPM);
    this.setPosition(posX/Constants.PPM,posY/Constants.PPM);
    LINEAR_VELOCITY_X=-1.5f;
    BodyDef groundBodyDef = new BodyDef();
    // Set its world position
    groundBodyDef.type= BodyDef.BodyType.KinematicBody;
    groundBodyDef.position.set(getX()+getWidth()/2,getY()+getHeight()/2);
    // Create a bodyTorrente from the defintion and add it to the world
    body = world.createBody(groundBodyDef);
    // Create a polygon shape
    PolygonShape groundBox = new PolygonShape();
    // Set the polygon shape as a box which is twice the size of our view port and 20 high
    // (setAsBox takes half-width and half-height as arguments)
    groundBox.setAsBox(this.getWidth()/2, this.getHeight()/2);
    // bodyTorrente.setTransform(this.getX()+this.getWidth()/2,this.getY()+this.getHeight()/2, (float)Math.toRadians(angle));
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = groundBox;
    // Create a fixture from our polygon shape and add it to our ground bodyTorrente
    fixtureDef.filter.categoryBits= Constants.CATEGORY_BLOODSTREAM; //is a
    fixtureDef.filter.maskBits=Constants.CATEGORY_PERSONAJE;//collides with
    fixtureDef.filter.groupIndex=1;
    body.createFixture(fixtureDef);
    body.setLinearVelocity(LINEAR_VELOCITY_X,body.getLinearVelocity().y);
    body.setAngularVelocity(1f);
    System.out.println("DESPLAZANDO CUERPO A LA IZQUIERDA");

    // Clean up after ourselves
    groundBox.dispose();
}


    @Override
    public void act(float delta) {
        super.act(delta);

        //body.setLinearVelocity(-1f,body.getLinearVelocity().y);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void updateImage_Body()
    {
        this.setPosition(body.getPosition().x-this.getWidth()/2, body.getPosition().y-this.getHeight()/2);
    }

    public Body getBody() {
        return body;
    }

    public void DesplazarBody(Floor floor){

        if (body.getPosition().x<= floor.getBodyFloor().getPosition().x+ floor.getWidth()){
            LINEAR_VELOCITY_X=1.5f;
            body.setLinearVelocity(LINEAR_VELOCITY_X,body.getLinearVelocity().y);
            System.out.println("DESPLAZANDO CUERPO A LA DERECHA");
        }else if (body.getPosition().x>=2* floor.getBodyFloor().getPosition().x){
            LINEAR_VELOCITY_X=-1.5f;
            body.setLinearVelocity(LINEAR_VELOCITY_X,body.getLinearVelocity().y);
            System.out.println("DESPLAZANDO CUERPO A LA IZQUIERDA AGAIN");
        }
    }
}


