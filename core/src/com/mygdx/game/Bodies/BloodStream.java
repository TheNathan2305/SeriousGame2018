package com.mygdx.game.Bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Classes.BodyEditorLoader;
import com.mygdx.game.Classes.Constants;


public class BloodStream extends Image {

    World world;
    private Body bodyBoodStream;
    private float width,height;

    //1ER ESCENARIO : PÁNCREAS  NO PRODUCE INSULINA

    public BloodStream(World world,float w,float h,float posX, float posY,String png){

        super(new Texture(png));
        this.world=world;
        this.width= w;
        this.height=w;
        this.setSize(width,height);
        this.setPosition(posX/Constants.PPM,posY/Constants.PPM);

        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("body.json"));//FUNCIONA, DO NOT MODIFICAR
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(getX(), getY());
        /*effect = new ParticleEffect();
        effect.load(Gdx.files.internal("bubleNote.p"), SeriousGame.textureAtlas);
        effect.start();
        effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());*/
        // Create a body in the world using our definition
        bodyBoodStream = world.createBody(bodyDef);
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
        fixtureDef.filter.categoryBits= Constants.CATEGORY_BLOODSTREAM; //is a
        fixtureDef.filter.maskBits=(short) (Constants.CATEGORY_PERSONAJE|Constants.CATEGORY_GLUCOSA);//collides with
        fixtureDef.filter.groupIndex=0;
       if (png.equals("bloodstream_curvo.png")){
           loader.attachFixture(bodyBoodStream, "bloodstream_curvo", fixtureDef,this.getWidth());
       } else if (png.equals("bloodstream_horizontal.png")) {
           loader.attachFixture(bodyBoodStream, "bloodstream_horizontal", fixtureDef,this.getWidth());
       }else if (png.equals("bloodstream_curvo2.png")) {
           loader.attachFixture(bodyBoodStream, "bloodstream_curvo2", fixtureDef,this.getWidth());
       }

    }


    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }


    public Body getBodyBoodStream() {
        return bodyBoodStream;
    }
}
