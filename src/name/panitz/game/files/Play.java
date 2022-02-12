package name.panitz.game.files;

import name.panitz.game.framework.swing.SwingGame;

public class Play {

  public static void main(String[] args) {
    SwingGame.startGame(new GameController<>());
  }

}
