package com.mygdx.game.Classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Bodies.Personaje;


/**
 * Created by Jonathan on 09/03/2018.
 */

public class Escudo extends Image
{
    World world;
   public Body bodyEscudo;
    Personaje personaje;
    public static Boolean escudoBody_activado =false;
  //  public static Boolean escudoImageVisible=false;
    public Escudo(World world, Personaje personaje){

        super(new Texture("escudo.png"));
        //setVisible(escudoImageVisible);
        this.world=world;
        this.personaje=personaje;
        this.setSize(Constants.DEVICE_WIDTH/Constants.PPM/5,Constants.DEVICE_WIDTH/Constants.PPM/5);
        this.setPosition(personaje.getBody().getPosition().x-(this.getWidth()-personaje.getWidth())/2.2f,
                personaje.getBody().getPosition().y);

        BodyDef escudoBodyDef = new BodyDef();
        // Set its world position
        escudoBodyDef.type= BodyDef.BodyType.KinematicBody;

        escudoBodyDef.position.set(getX()+getWidth()/2,getY()+getHeight()/2);

        // Create a body from the defintion and add it to the world
        bodyEscudo = world.createBody(escudoBodyDef);

        // Create a polygon shape
        CircleShape circleBox = new CircleShape();
        // Set the polygon shape as a box which is twice the size of our view port and 20 high

        circleBox.setRadius(this.getWidth()/2);
        // body.setTransform(this.getX()+this.getWidth()/2,this.getY()+this.getHeight()/2, (float)Math.toRadians(angle));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleBox;
        // Create a fixture from our polygon shape and add it to our ground body
        fixtureDef.filter.categoryBits= Constants.CATEGORY_ESCUDO; //is a
        fixtureDef.filter.maskBits=Constants.CATEGORY_ENEMIES;//collides with
        fixtureDef.filter.groupIndex=0;
        bodyEscudo.createFixture(fixtureDef);

        // Clean up after ourselves
        circleBox.dispose();
        escudoBody_activado =true;
        // escudoImageVisible=true;
        // setVisible(escudoImageVisible);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        bodyEscudo.setAngularVelocity(1f);
        bodyEscudo.setLinearVelocity(personaje.getBody().getLinearVelocity().x,personaje.getBody().getLinearVelocity().y);
        if (bodyEscudo.getLinearVelocity().y>0)
        {
            bodyEscudo.setType(BodyDef.BodyType.DynamicBody);
            System.out.println("CAMBIA A DINAMICO");
        }
        if (bodyEscudo.getType().equals(BodyDef.BodyType.DynamicBody)){
            if (bodyEscudo.getPosition().y<personaje.getBody().getPosition().y+personaje.getHeight()/2){
                bodyEscudo.setType(BodyDef.BodyType.KinematicBody);
                System.out.println("CAMBIA A KINEMATICO");
            }
        }

        this.setPosition(bodyEscudo.getPosition().x-getWidth()/2,bodyEscudo.getPosition().y-getHeight()/2);
    }

    public static Boolean getEscudoBody_activado() {
        return escudoBody_activado;
    }

    public static void setEscudoBody_activado(Boolean escudoBody_activado) {
        Escudo.escudoBody_activado = escudoBody_activado;
    }
}
