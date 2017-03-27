package com.neonpolis.game.Utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Lauri on 21.3.2017.
 */

public class Constants {
    // Phone resolution
    public static final int APP_WIDTH = 1920;
    public static final int APP_HEIGHT = 1080;

    // Realistic gravity
    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    // World specs
    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 60f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;

    // Runner specs
    public static final float RUNNER_X = 2f;
    public static final float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float RUNNER_WIDTH = 1f;
    public static final float RUNNER_HEIGHT = 2f;

    public static final float RUNNER_GRAVITY_SCALE = 3f;
    public static float RUNNER_DENSITY = 0.5f;
    public static final float RUNNER_DODGE_X = 2f;
    public static final float RUNNER_DODGE_Y = 1.5f;
    public static final Vector2 RUNNER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 15f);
    public static final Vector2 RUNNER_WALKING_LEFT_LINEAR_IMPULSE = new Vector2(-3, 0f);
    public static final Vector2 RUNNER_WALKING_RIGHT_LINEAR_IMPULSE = new Vector2(3, 0f);
    public static final float RUNNER_HIT_ANGULAR_IMPULSE = 10f;

    // "enemy" specs
    public static final float ENEMY_X = 25f;
    public static final float ENEMY_DENSITY = RUNNER_DENSITY;
    public static final float RUNNING_SHORT_ENEMY_Y = 1.5f;
    public static final float RUNNING_LONG_ENEMY_Y = 2f;
    public static final float FLYING_ENEMY_Y = 3f;
    public static final Vector2 ENEMY_LINEAR_VELOCITY = new Vector2(-14f, 0);
}
