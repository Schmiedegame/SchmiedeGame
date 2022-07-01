package de.schmiedegame.schmiede;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import static com.badlogic.gdx.Gdx.gl;

public class ScoreScreen extends ScreenAdapter {

    //Konstanten
    private final int WIDTH = 1000;
    private final int HEIGHT = 700;


    private int score_furnace;
    private int score_anvil;
    private int score_casting;
    private int total_score;


    //Main-Game Objekt und MainGameScreen Objekt, von dem aus auf der Screen zugegriffen wurde.
    private Smithinggame game;
    private MainGameScreen gameScreen;

    private TextureRegion background;

    //Labels
    private Label scoreText1;
    private Label scoreText2;
    private Label scoreText3;
    private Label totalLabel;

    private Label scorePoint1;
    private Label scorePoint2;
    private Label scorePoint3;
    private Label totalScore;


    //Sonstige Grafikelemente
    private ImageButton leave_button;

    private ShapeRenderer rectangle;

    private Group group;
    private Stage stage;

    /**
     * Konstruktor der Grafik Klasse des Scores.
     *
     * @param game Main-Game Objekt
     * @param gameScreen MainGameScreen Objekt, von dem aus der Zugriff stattfand
     */
    public ScoreScreen(Smithinggame game, MainGameScreen gameScreen) {

        this.game = game;
        this.gameScreen = gameScreen;

        //Auslesung der gespeicherten Punktestände
        score_furnace = Score.getFurnaceScore();
        score_casting = Score.getCastingScore();
        score_anvil = Score.getAnvilScore();
        total_score = (score_anvil + score_furnace + score_casting)/3;


        group = new Group();
        group.setBounds(0, 0, WIDTH, HEIGHT);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        //Initialisierung aller Grafikelemente
        background = Assets.score_background_region;

        scoreText1 = new Label("Schmelzofen:", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        scoreText1.setBounds(0,0, 700, 100);
        scoreText1.setPosition(25,550);

        scoreText2 = new Label("Guss:", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        scoreText2.setBounds(0,0, WIDTH, 100);
        scoreText2.setPosition(25, 425);

        scoreText3 = new Label("Amboss:", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        scoreText3.setBounds(0,0, WIDTH, 100);
        scoreText3.setPosition(25, 300);

        totalLabel = new Label("Ergebnis:", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        totalLabel.setBounds(0,0, WIDTH, 100);
        totalLabel.setPosition(25, 150);


        scorePoint1 = new Label(score_furnace + "%", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        scorePoint1.setBounds(0,0, WIDTH, 100);
        scorePoint1.setPosition(760, 550);

        scorePoint2 = new Label(score_casting + "%", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        scorePoint2.setBounds(0,0, WIDTH, 100);
        scorePoint2.setPosition(760, 425);

        scorePoint3 = new Label(score_anvil + "%", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        scorePoint3.setBounds(0,0, WIDTH, 100);
        scorePoint3.setPosition(760, 300);

        totalScore = new Label(total_score + "%", new Label.LabelStyle(Assets.font_100, Color.WHITE));
        totalScore.setBounds(0,0, WIDTH, 100);
        totalScore.setPosition(760, 150);


        leave_button = new ImageButton(Assets.leave_button_states.get(0), Assets.leave_button_states.get(1), Assets.leave_button_states.get(2));
        leave_button.setPosition(350, 25);

        rectangle = new ShapeRenderer();
        rectangle.setColor(Color.WHITE);
        rectangle.setAutoShapeType(true);

        group.addActor(leave_button);
        group.addActor(scoreText1);
        group.addActor(scoreText2);
        group.addActor(scoreText3);
        group.addActor(totalLabel);
        group.addActor(scorePoint1);
        group.addActor(scorePoint2);
        group.addActor(scorePoint3);
        group.addActor(totalScore);

        stage.addActor(group);

    }

    /**
     * Wird jeden frame ausgeführt.
     * Stellt Grafikelemente dar.
     * @param delta Vergangene Zeit seit letztem Frame
     */
    @Override
    public void render(float delta){

        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();

        rectangle.begin(ShapeRenderer.ShapeType.Filled);
        rectangle.rect(50,270,900,10);
        rectangle.end();

        game.batch.begin();
        stage.draw();
        game.batch.end();

        //Logik
        if (leave_button.isChecked()) {
            MusicPlayer.save_main_track_position();
            game.setScreen(gameScreen);
            dispose();
        }
    }

    /**
     * Wird bei Löschen des Objekts aufgerufen.
     * Punktzahl wird gespeichert und dann zurückgesetzt.
     */
    @Override
    public void dispose() {
        Score.iterate();
        Score.reset();
    }

}