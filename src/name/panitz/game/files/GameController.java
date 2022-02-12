package name.panitz.game.files;

import name.panitz.game.files.creatures.*;
import name.panitz.game.files.items.Item;
import name.panitz.game.files.structures.*;
import name.panitz.game.files.weatherConditions.Weather;
import name.panitz.game.framework.*;
import name.panitz.game.framework.swing.JavaSoundTool;

import javax.sound.sampled.*;
import java.util.*;

public class GameController<I, S> extends AbstractGame<I, S> {

  private LevelGenerator<I> levelGenerator   = new LevelGenerator<>();
  private List<Structure<I>> levelStructures = new ArrayList<>();
  private List<ImageObject<I>> healthBars    = new ArrayList<>();
  private List<Structure<I>> levelFloor      = new ArrayList<>();
  private UserInterface userInterface        = new UserInterface<>();
  private List<Creature<I>> creatures        = new ArrayList<>();
  private Collision<I, S> collision          = new Collision<>();
  private List<Item<I>> items                = new ArrayList<>();
  private AILogic<I> ai                      = new AILogic<>();

  private List<Weather<I>> weatherConditions;
  private Clip backgroundMusicMainScreen;
  // set to false for animation for title screen, soll spaeter repariert werden!
  private boolean isStopped = false;
  private Player<I> player;

  GameController() {
    super(new Player<>(), 800, 600);
    // Create a Player
    player = (Player<I>) getPlayer();
    // Show title page
    List<ImageObject<I>> titleScreen = new ArrayList<>();
    titleScreen.add(new ImageObject<>("titleScreenBg.jpg", new Vertex(0, 0), new Vertex(-0.3, -0.3)));
    titleScreen.add(new ImageObject<>("titleScreenNamesWhite.png", new Vertex(0, 0)));
    titleScreen.add(new ImageObject<>("titleScreenNamesBlack.png", new Vertex(0, 0)));
    titleScreen.add(new ImageObject<>("DungeonFighterLogoTS.png", new Vertex(90, -10)));
    getGOss().add(titleScreen);
    this.player.setPos(new Vertex(-100, 200));
    // Add main buttons
    getButtons().add(new Button("New game", () -> start()));
    getButtons().add(new Button("Exit",  () -> System.exit(0)));
    // Music for title screen playing
    //backgroundMusicMainScreen = JavaSoundTool.playMusic("oldultima-stones.wav");
  }

  @Override
  public void doChecks() {
    this.checkGameOver();
    WeatherController.moveElements(this.weatherConditions);
    // animation
    this.player.animate();
    this.creatures.forEach(Creature::animate);
  }

  @Override
  public boolean isStopped() {
    return this.isStopped;
  }

  @Override
  public void start() {
    //JavaSoundTool.stopMusic(backgroundMusicMainScreen);
    this.player.resurrect();
    loadLevel();
  }

  void loadLevel() {
    this.isStopped = false;
    this.player.dungeonLevel += 1;
    // Prepare new level
    Map<String, List<? extends GameObject<I>>> levelElements = levelGenerator.loadLevel(this.player);
    levelFloor        = (List<Structure<I>>)   levelElements.get("floor");
    levelStructures   = (List<Structure<I>>)   levelElements.get("structures");
    creatures         = (List<Creature<I>>)    levelElements.get("creatures");
    items             = (List<Item<I>>)        levelElements.get("items");
    weatherConditions = (List<Weather<I>>)     levelElements.get("weatherConditions");
    healthBars        = (List<ImageObject<I>>) levelElements.get("healthBars");
    // Prepare new GOss
    List<List<? extends GameObject<I>>> newGOss = new ArrayList<>();
    newGOss.add(levelFloor);
    newGOss.add(levelStructures);
    newGOss.add(creatures);
    newGOss.add(items);
    newGOss.add(healthBars);
    if (weatherConditions != null) newGOss.add(weatherConditions);
    newGOss.add(new ArrayList<>(player.inventory.values()));
    newGOss.add(userInterface.prepareStatusInterfaceFor(player));
    setGOss(newGOss);
  }

  private void checkGameOver() {
    if (player.currentHP <= 0) {
      List<GameObject<I>> centralMessage = new ArrayList<>();
      centralMessage.add(new TextObject<>(new Vertex(260, 400), "You scored " + player.experience + " points", "Helvetica", 30));
      getGOss().add(Arrays.asList(new ImageObject<>("GOScreen.png", new Vertex(0, 0))));
      getGOss().add(centralMessage);
      this.player.setPos(new Vertex(-100, 300));
      this.isStopped = true;
    }
  }

  private boolean playerTurn(Creature<I> playerNextPosition) {
    return collision.checkFor(playerNextPosition, this.player, this.levelStructures, this.creatures, this.items, this);
  }

  private void aiTurn() {
    ai.turn(creatures, player, levelStructures, healthBars);
  }

  private void updatePlayerInventory() {
    var newGOss = getGOss();
    newGOss.add(new ArrayList<>(player.inventory.values()));
    setGOss(newGOss);
  }

  @Override
  public void keyPressedReaction(KeyCode keycode) {
    if (keycode != null && !isStopped()) {
      this.userInterface.updateUserStatusInterface(this.player);
      switch (keycode) {
        case RIGHT_ARROW:
          if (playerTurn(player.getNextPosRight())) this.player.moveRight();
          aiTurn();
          break;
        case LEFT_ARROW:
          if (playerTurn(player.getNextPosLeft())) this.player.moveLeft();
          aiTurn();
          break;
        case DOWN_ARROW:
          if (playerTurn(player.getNextPosBelow())) this.player.moveDown();
          aiTurn();
          break;
        case UP_ARROW:
          if (playerTurn(player.getNextPosAbove())) this.player.moveUp();
          aiTurn();
          break;
        default:;
      }
      this.userInterface.updateUserStatusInterface(this.player);
      this.updatePlayerInventory();
    }
  }

}
