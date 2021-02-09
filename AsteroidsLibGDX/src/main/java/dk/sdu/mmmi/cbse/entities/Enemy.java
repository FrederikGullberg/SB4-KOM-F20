package dk.sdu.mmmi.cbse.entities;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends SpaceObject {
    private ArrayList<EnemyBullet> bullets;

    private float maxSpeed;
    private float acceleration;
    private float deceleration;

    public Enemy(ArrayList<EnemyBullet> bullets){
        this.bullets = bullets;

        x = Game.WIDTH / 1.5f;
        y = Game.HEIGHT / 1.5f;

        maxSpeed = 100;
        acceleration = 100;
        deceleration = 30;

        shapex = new float[4];
        shapey = new float[4];

        radians = 3.1415f / 2;
        rotationSpeed = 3;
    }

    public void shoot() {
        bullets.add(new EnemyBullet(x,y, radians));
    }
    double delay = 0f;
        public void update(float dt) {

        if (MathUtils.random(1,10) == 5){
            if(delay <= System.currentTimeMillis()){
                delay = System.currentTimeMillis() + 500;
                shoot();
            }
        }

            dx += MathUtils.cos(radians) * acceleration * dt;
            dy += MathUtils.sin(radians) * acceleration * dt;

            if (MathUtils.random(-1,2) == 1) {

                radians -= rotationSpeed * dt;
            }else{
                radians += rotationSpeed * dt;
            }

            float vec = (float) Math.sqrt(dx * dx + dy * dy);
            if(vec > 0) {
                dx -= (dx / vec) * deceleration * dt;
                dy -= (dy / vec) * deceleration * dt;
            }
            if(vec > maxSpeed) {
                dx = (dx / vec) * maxSpeed;
                dy = (dy / vec) * maxSpeed;
            }
            // set position
            x += dx * dt;
            y += dy * dt;

            // set shape
            setShape();

            // screen wrap
            wrap();
        }

    public void draw(ShapeRenderer sr) {

        sr.setColor(1, 0, 0, 1);

        sr.begin(ShapeRenderer.ShapeType.Line);

        for(int i = 0, j = shapex.length - 1;
            i < shapex.length;
            j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }

        sr.end();

    }

    private void setShape() {
        shapex[0] = x + MathUtils.cos(radians) * 4;
        shapey[0] = y + MathUtils.sin(radians) * 4;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 10) * 12;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 10) * 12;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

        shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 10) * 12;
        shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 10) * 12;
    }
}
