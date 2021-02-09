package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.entities.*;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
	private Player player;
	private ArrayList<Bullet> bulletsPly;
	private Enemy enemy;
	private ArrayList<EnemyBullet> bulletsEnemy;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		sr = new ShapeRenderer();
		bulletsPly = new ArrayList<Bullet>();
		bulletsEnemy = new ArrayList<EnemyBullet>();
		player = new Player(bulletsPly);
		enemy = new Enemy(bulletsEnemy);
	}


	public void update(float dt) {
		
		handleInput();
		
		player.update(dt);
		for(int i = 0; i < bulletsPly.size(); i++){
			bulletsPly.get(i).update(dt);
			if (bulletsPly.get(i).shouldRemove()) {
				bulletsPly.remove(i);
				i--;
			}
		}

		enemy.update(dt);
		for(int i = 0; i < bulletsEnemy.size(); i++){
			bulletsEnemy.get(i).update(dt);
			if (bulletsEnemy.get(i).shouldRemove()) {
				bulletsEnemy.remove(i);
				i--;
			}
		}
	}
	
	public void draw() {
		player.draw(sr);

		enemy.draw(sr);

		for(int i = 0; i < bulletsPly.size(); i++){
			bulletsPly.get(i).draw(sr);
		}
		for(int i = 0; i < bulletsEnemy.size(); i++){
			bulletsEnemy.get(i).draw(sr);
		}
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
		player.setShoot(GameKeys.isDown(GameKeys.SPACE));

	}
	
	public void dispose() {}
	
}









