package cz.cvut.fit.niadp.mvcgame.config;

public class MvcGameConfig {
    public static final int MIN_X = 0;
    public static final int MIN_Y = 0;
    public static final int MAX_X = 1920;
    public static final int MAX_Y = 1080;
    public static final int MOVE_STEP = 10;
    public static final int CANNON_POS_X = 300;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final int GAMEINF_POS_X = MAX_X - 300;
    public static final int GAMEINF_POS_Y = 200;
    public static final double INIT_ANGLE = 0;
    public static final int INIT_POWER = 10;
    public static final double ANGLE_STEP = Math.PI / 18;
    public static final int POWER_STEP = 1;
    public static final double GRAVITY = 9.81;
    public static final int MAGIC_TIME_CONST = 100;
    public static final int MIN_POWER = 1;
    public static final int MAX_POWER = 50;

    public static final int STARTING_NORMAL_ENEMIES = 5;
    public static final int STARTING_LARGE_ENEMIES = 2;

    public static final int MISSILE_COLLIDER_WIDTH = 30;
    public static final int MISSILE_COLLIDER_HEIGHT = 30;
    public static final int ENEMY_NORMAL_COLLIDER_HEIGHT = 30;
    public static final int ENEMY_NORMAL_COLLIDER_WIDTH = 30;
    public static final int ENEMY_LARGE_COLLIDER_HEIGHT = 40;
    public static final int ENEMY_LARGE_COLLIDER_WIDTH = 45;
}