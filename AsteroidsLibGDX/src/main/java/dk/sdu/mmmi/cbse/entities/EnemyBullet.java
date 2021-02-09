package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class EnemyBullet extends Bullet{
    private float lifeTime;
    private float lifeTimer;

    private boolean remove;
    public EnemyBullet(float x, float y, float radians) {
        super(x, y, radians);
        this.x = x;
        this.y = y;
        this.radians = radians;
        float speed = 200;
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
        width = height = 4;

        lifeTimer = 0;
        lifeTime = 0.6f;
    }

    public boolean shouldRemove() {return this.remove;}

    @Override
    public void update(float dt) {
        x += dx * dt;
        y += dy * dt;

        wrap();

        lifeTimer += dt;
        if(lifeTimer > lifeTime){
            remove = true;
        }
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(1,0.5f,0.5f,1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.line(x-width,y-height,x-width+2,y-height+2);
        sr.end();
    }
}
