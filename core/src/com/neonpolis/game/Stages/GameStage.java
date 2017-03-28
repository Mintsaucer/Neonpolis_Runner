package com.neonpolis.game.Stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.neonpolis.game.Actors.Enemy;
import com.neonpolis.game.Actors.Ground;
import com.neonpolis.game.Actors.Runner;
import com.neonpolis.game.Neonpolis;
import com.neonpolis.game.Screens.GameOverScreen;
import com.neonpolis.game.Screens.MenuScreen;
import com.neonpolis.game.Utils.BodyUtils;
import com.neonpolis.game.Utils.WorldUtils;

/**
 * Created by Lauri on 21.3.2017.
 */

public class GameStage extends Stage implements ContactListener {

    Neonpolis game;

    //This will be our viewport measurements while working with the debug renderer
    private static final int VIEWPORT_WIDTH = 35;
    private static final int VIEWPORT_HEIGHT = 16;

    private World world;
    private Ground ground;
    private Runner runner;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Rectangle screenRightSide;
    private Rectangle screenLeftSide;
    private Vector3 touchPoint;
    private Vector2 lastTouch = new Vector2();

    public GameStage(Neonpolis game) {
        this.game = game;
        setUpWorld();
        setupCamera();
        setupTouchControlAreas();
        renderer = new Box2DDebugRenderer();
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpGround();
        setUpRunner();
        createEnemy();
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }

    private void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world));
        addActor(runner);
    }

    public void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            update(body);
        }

        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }

    private void update(Body body) {
        if (!BodyUtils.bodyInBounds(body)) {
            if (BodyUtils.bodyIsEnemy(body) && !runner.isHit()) {
                createEnemy();
            }
            if (BodyUtils.bodyIsRunner(body)) {
                game.setScreen( new GameOverScreen(game));
            }
            world.destroyBody(body);

            if (runner.isHit()) {
                game.setScreen( new GameOverScreen(game));
            }
        }
    }

    private void createEnemy() {
        Enemy enemy = new Enemy(WorldUtils.createEnemy(world));
        if ( !runner.isHit())
        addActor(enemy);
    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        lastTouch.set(x, y);
        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);
            if (rightSideTouched(touchPoint.x, touchPoint.y)) {
                  runner.moveRight();
        } else if (leftSideTouched(touchPoint.x, touchPoint.y)) {
                runner.moveLeft();
            }
        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (runner.isDodging()) {
            runner.stopDodge();
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }

    private boolean rightSideTouched(float x, float y) {
        return screenRightSide.contains(x, y);
    }

    private boolean leftSideTouched( float x, float y) {
        return screenLeftSide.contains(x,y);
    }

    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    @Override
    public void beginContact (Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b)) ||
                (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b))) {
            runner.hit();
        } else if ((BodyUtils.bodyIsRunner(a) &&  BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b))) {
            runner.landed();
        }
    }
    @Override
    public boolean touchDragged(int x, int y, int pointer)  {
        Vector2 newTouch = new Vector2(x,y);
        // delta will now hold the difference between the last and the current touch positions
        Vector2 delta = newTouch.cpy().sub(lastTouch);
        if (delta.y < 0 && !runner.jumping) {
            runner.jump();
        }
        if (delta.y > 0 && !runner.jumping) {
            runner.dodge();
        }
        lastTouch = newTouch;
        return super.touchDragged(x,y,pointer);
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}



