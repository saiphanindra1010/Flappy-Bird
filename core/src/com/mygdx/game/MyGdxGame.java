package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background,bottemtube,toptube;
    Texture[] birds;
    int flapstate = 0;
    float birdy = 0;
    float birdx = 0;
    float velocity = 1;
    float gravity = 2;
    int state = 0;
    float gap=200;
    float tubevelocity=4;
    float distancebetweentubes;
    int numberoftubes=4;
    int[] tubex=new int[numberoftubes];
    int[] pipeOffset=new int[numberoftubes];


    public static int generateTicketNumber(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");
        bottemtube = new Texture("bottomtube.png");
        toptube = new Texture("toptube.png");
        birdy = Gdx.graphics.getHeight() / 2 - birds[0].getHeight() / 2;
        birdx = Gdx.graphics.getWidth() / 2 - birds[0].getWidth() / 2;
        distancebetweentubes=Gdx.graphics.getWidth()*3/4;
for(int i=0;i<numberoftubes;i++)
{
    pipeOffset[i]= (int) (generateTicketNumber(300, 800));
    tubex[i]= (int) (Gdx.graphics.getWidth() / 2 - toptube.getWidth() / 2+i*distancebetweentubes);
//    tubex[i]= (int) (Gdx.graphics.getWidth()  - toptube.getWidth()*distancebetweentubes);
}
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (state != 0) {


            if (Gdx.input.justTouched()) {
                velocity -= 40;



            }
            for(int i=0;i<numberoftubes;i++) {
                if(tubex[i]<-toptube.getWidth()){
                    tubex[i]= (int) (numberoftubes*distancebetweentubes);
                }
                else
                {
                    tubex[i]= (int) (tubex[i]-tubevelocity);
                }
                tubex[i]-= tubevelocity;
                batch.draw(toptube, tubex[i], Gdx.graphics.getHeight() / 2 + pipeOffset[i] / 2 + gap / 2);
                batch.draw(bottemtube, tubex[i], Gdx.graphics.getHeight() / 2 - pipeOffset[i] / 2 + gap / 2 - bottemtube.getHeight());
            }
            if (birdy > 0 || velocity < 0) {
                velocity += gravity;
                birdy-=velocity;

            }
        } else {
            if (Gdx.input.justTouched()) {

                Gdx.app.log("touched", "touch is responsible");
                state = 1;

            }
        }

        if (flapstate == 0) {
            flapstate = 1;
        } else {
            flapstate = 0;
        }

        batch.draw(birds[flapstate], birdx, birdy);

        batch.end();


    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }
}
