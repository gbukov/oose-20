package name.panitz.game.files;

import name.panitz.game.files.creatures.Player;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class SetOfLevels {

  String[][] getLevel(Player player) {
    switch (player.dungeonLevel) {
      case 0:
        return this.level0;
      case 1:
        return this.level1;
      case 2:
        return this.level2;
      case 3:
      case 4:
      case 5:
      case 6:
        return this.generateRandomLevel();
      case 7:
        return this.level7;
      case 8:
        return this.level8;
      default:
        return null;
    }
  }

  private String[][] generateRandomLevel() {
    // Basic border (top and down)
    String[][] newLevel = basicBorder;
    // Generate den Eingang und den Ausgang
    int enter = ThreadLocalRandom.current().nextInt(2, 10 + 1);
    int exit  = ThreadLocalRandom.current().nextInt(2, 10 + 1);
    for (int i = 2; i < 11; i++) {
      if (i != enter) newLevel[i][0] = "swd";
      else newLevel[enter][0] = "sde";
    }
    for (int i = 2; i < 11; i++) {
      if (i != exit) newLevel[i][15] = "swd";
      else newLevel[exit][15] = "sda";
    }
    // Generate right side
    int randomPartRight = new Random().nextInt(this.rightSide.length);
    for (int i = 2; i < 11; i++) {
      for (int j = 2; j < 6; j++) {
        newLevel[i][j] = this.rightSide[randomPartRight][i - 2][j - 2];
      }
    }
    // Generate middle
    int randomPartMiddle = new Random().nextInt(this.middle.length);
    for (int i = 2; i < 11; i++) {
      for (int j = 6; j < 10; j++) {
        newLevel[i][j] = this.middle[randomPartMiddle][i - 2][j - 6];
      }
    }
    // Generate left side
    int randomPartLeft = new Random().nextInt(this.leftSide.length);
    for (int i = 2; i < 11; i++) {
      for (int j = 10; j < 14; j++) {
        newLevel[i][j] = this.leftSide[randomPartLeft][i - 2][j - 10];
      }
    }
    return newLevel;
  }

  /*
  * All levels is based on the follow scheme:
  *   Width  : 16
  *   Height : 12
  */
  private String[][] basicBorder = {
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd"},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd"}
  };
  private String[][][] rightSide = {
          {
                  {"swd", "swd", "swd", "swd"},
                  {"swd", "swd", "swd", "dwn"},
                  {"wsf", "swd", "dbb", "mag"},
                  {"...", "...", "...", "swd"},
                  {"swd", "swd", "...", "..."},
                  {"swd", "swd", "swd", "..."},
                  {"...", "dwn", "swd", "dbb"},
                  {"dwn", "swd", "swd", "mag"},
                  {"swd", "swd", "swd", "..."}
          },
          {
                  {"swd", "swd", "swd", "..."},
                  {"swd", "swd", "dwn", "..."},
                  {"wsf", "swd", "mag", "..."},
                  {"...", "dbb", "swd", "..."},
                  {"dwn", "dbb", "swd", "swd"},
                  {"swd", "swd", "swd", "..."},
                  {"swd", "swd", "mag", "..."},
                  {"...", "...", "...", "dbb"},
                  {"swd", "swd", "swd", "swd"}
          },
          {
                  {"swd", "dwn", "...", "swd"},
                  {"swd", "abs", "mag", "ddo"},
                  {"swd", "swd", "swd", "swd"},
                  {"wsf", "...", "...", "swd"},
                  {"...", "sbb", "...", "swd"},
                  {"...", "swd", "dwn", "swd"},
                  {"sts", "swd", "mag", "..."},
                  {"...", "swd", "swd", "swd"},
                  {"dwn", "dbb", "swd", "..."}
          },
          {
                  {"swd", "...", "dwn", "dwn"},
                  {"wsf", "swd", "...", "..."},
                  {"...", "mag", "swd", "..."},
                  {"...", "swd", "...", "..."},
                  {"...", "sbb", "swd", "sts"},
                  {"...", "...", "...", "..."},
                  {"...", "...", "swd", "..."},
                  {"...", "swd", "sbb", "mag"},
                  {"swd", "...", "dwn", "..."}
          }
  };
  private String[][][] middle = {
          {
                  {"dwn", "dwn", "swd", "..."},
                  {"dwn", "sbb", "swd", "..."},
                  {"...", "swd", "swd", "..."},
                  {"...", "mag", "...", "..."},
                  {"...", "...", "swd", "..."},
                  {"...", "sbb", "swd", "mag"},
                  {"...", "...", "...", "..."},
                  {"...", "swd", "swd", "..."},
                  {"swd", "swd", "...", "dwn"}
          },
          {
                  {"...", "swd", "swd", "dwn"},
                  {"...", "...", "...", "..."},
                  {"...", "swd", "swd", "..."},
                  {"...", "mag", "...", "phs"},
                  {"...", "swd", "sbb", "..."},
                  {"...", "...", "...", "..."},
                  {"...", "swd", "sbb", "mag"},
                  {"...", "...", "...", "..."},
                  {"dwn", "swd", "swd", "abs"}
          },
          {
                  {"...", "swd", "sbb", "dwn"},
                  {"...", "...", "swd", "mag"},
                  {"...", "...", "...", "..."},
                  {"...", "sbb", "dwn", "..."},
                  {"...", "...", "swd", "..."},
                  {"...", "swd", "mag", "..."},
                  {"...", "...", "...", "swd"},
                  {"...", "...", "swd", "..."},
                  {"swd", "sbb", "dwn", "..."}
          },
          {
                  {"swd", "swd", "dbb", "swd"},
                  {"dwn", "swd", "mag", "..."},
                  {"dwn", "swd", "swd", "..."},
                  {"...", "...", "...", "..."},
                  {"...", "swd", "swd", "..."},
                  {"...", "...", "swd", "..."},
                  {"...", "swd", "swd", "..."},
                  {"...", "...", "mag", "..."},
                  {"swd", "swd", "dwn", "swd"}
          }
  };
  private String[][][] leftSide = {
          {
                  {"...", "...", "swd", "swd"},
                  {"...", "swd", "swd", "swd"},
                  {"dwn", "...", "swd", "mag"},
                  {"...", "...", "...", "dbb"},
                  {"...", "swd", "...", "..."},
                  {"...", "...", "...", "swd"},
                  {"...", "...", "swd", "swd"},
                  {"...", "dbb", "swd", "dwn"},
                  {"...", "dwn", "swd", "swd"}
          },
          {
                  {"swd", "dwn", "mag", "swd"},
                  {"swd", "...", "abs", "swd"},
                  {"ddo", "sts", "dbb", "swd"},
                  {"swd", "swd", "swd", "swd"},
                  {"...", "...", "...", "swd"},
                  {"swd", "swd", "mag", "swd"},
                  {"...", "swd", "...", "..."},
                  {"...", "swd", "swd", "swd"},
                  {"...", "dwn", "swd", "swd"}
          },
          {
                  {"dwn", "dwn", "swd", "swd"},
                  {"dwn", "...", "...", "swd"},
                  {"...", "sts", "...", "mag"},
                  {"...", "...", "...", "swd"},
                  {"...", "mag", "swd", "..."},
                  {"...", "...", "...", "swd"},
                  {"...", "...", "swd", "swd"},
                  {"...", "dbb", "swd", "swd"},
                  {"...", "swd", "swd", "swd"}
          }
  };
  private String[][] level0 = {
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd"},
          {"...", "...", "...", "...", "mag", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "mag", "...", "...", "...", "..."},
          {"swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd"}
  };
  private String[][] level1 = {
          {"wsc", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt"},
          {"swt", "swt", "swt", "swt", "sfg", "swt", "sfg", "sfg", "swt", "sfg", "sfg", "swt", "sfg", "sfg", "swt", "swt"},
          {"swt", "swt", "sfg", "sfg", "sfg", "sfg", "sfg", "sfg", "sfg", "swt", "sfg", "sfg", "sfg", "sfg", "sfg", "swt"},
          {"swt", "swt", "sfg", "sfg", "swt", "sfg", "sfg", "swt", "sfg", "sfg", "sfg", "sfg", "swt", "swd", "swd", "swd"},
          {"swt", "swt", "sfg", "sfg", "swt", "sfg", "sfg", "swt", "swt", "sfg", "sfg", "sfg", "sfg", "swd", "...", "sda"},
          {"swt", "sfg", "sfg", "sfg", "sfg", "sfg", "swt", "sfg", "sfg", "swt", "sfg", "sfg", "sfg", "swd", "...", "swd"},
          {"sdr", "sfg", "sfg", "sfg", "sfg", "sfg", "sfg", "sfg", "sfg", "sfg", "sfg", "sfg", "sfg", "ddo", "...", "swd"},
          {"sfg", "sfg", "sfg", "sfg", "swt", "sfg", "swt", "swt", "sfg", "sfg", "swt", "sfg", "sfg", "swd", "...", "swd"},
          {"swt", "swt", "sfg", "sfg", "swt", "sfg", "sfg", "swt", "sfg", "sfg", "swt", "sfg", "sfg", "swd", "swd", "swd"},
          {"swt", "swt", "swt", "sfg", "sfg", "sfg", "sfg", "swt", "swt", "sfg", "sfg", "swt", "sfg", "sfg", "swt", "swt"},
          {"swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt", "swt"}
  };
  private String[][] level2 = {
          {"wsf", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd"},
          {"swd", "dwn", "swd", "dwn", "...", "...", "dbb", "dbb", "swd", "dbb", "mag", "...", "...", "...", "...", "swd"},
          {"swd", "...", "swd", "...", "...", "...", "...", "dbb", "swd", "swd", "...", "...", "...", "swd", "...", "swd"},
          {"swd", "...", "...", "...", "...", "...", "...", "...", "swd", "swd", "...", "...", "swd", "swd", "mag", "sda"},
          {"swd", "...", "swd", "...", "...", "...", "...", "...", "swd", "swd", "swd", "sts", "...", "swd", "swd", "swd"},
          {"swd", "...", "swd", "dbb", "...", "...", "...", "...", "...", "swd", "swd", "...", "...", "dwn", "phs", "swd"},
          {"swd", "...", "swd", "swd", "ddo", "swd", "...", "...", "...", "swd", "swd", "...", "...", "...", "...", "swd"},
          {"sde", "...", "swd", "mag", "...", "swd", "...", "...", "...", "...", "swd", "swd", "swd", "swd", "...", "swd"},
          {"swd", "...", "swd", "...", "...", "swd", "...", "...", "...", "...", "...", "...", "...", "...", "...", "swd"},
          {"swd", "dwn", "swd", "wss", "phs", "swd", "dbb", "...", "...", "mag", "swd", "...", "...", "...", "swd", "swd"},
          {"swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd"}
  };
  private String[][] level7 = {
          {"wsf", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd"},
          {"swd", "...", "swd", "sfg", "sfg", "sfg", "sfg", "swt", "sfg", "sfg", "sfg", "sfg", "swd", "swd", "swd", "swd"},
          {"swd", "...", "...", "swd", "sfg", "swt", "sfg", "sfg", "sfg", "sfg", "swt", "swd", "phs", "swd", "swd", "swd"},
          {"swd", "dbb", "...", "dwn", "swd", "sfg", "swt", "sfg", "sfg", "sfg", "swd", "dwn", "dbb", "swd", "swd", "swd"},
          {"swd", "...", "...", "...", "...", "swd", "swd", "ddc", "swd", "swd", "...", "...", "...", "...", "swd", "swd"},
          {"sde", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "sda"},
          {"swd", "...", "...", "...", "...", "swd", "swd", "ddc", "swd", "swd", "dbb", "...", "...", "...", "swd", "swd"},
          {"swd", "...", "...", "...", "swd", "sfg", "sfg", "sfg", "sfg", "sfg", "swd", "...", "...", "swd", "swd", "swd"},
          {"swd", "...", "dwn", "swd", "sfg", "swt", "sfg", "sfg", "sfg", "sfg", "sfg", "swd", "phs", "swd", "swd", "swd"},
          {"swd", "dwn", "swd", "sfg", "sfg", "sfg", "sfg", "swt", "sfg", "sfg", "swt", "sfg", "swd", "swd", "swd", "swd"},
          {"swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd"}
  };
  private String[][] level8 = {
          {"wsf", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."},
          {"swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd"},
          {"swd", "swd", "swd", "swd", "swd", "...", "...", "dbb", "swd", "swd", "dbb", "dwn", "...", "...", "...", "swd"},
          {"swd", "swd", "swd", "swd", "dwn", "...", "...", "...", "...", "swd", "swd", "swd", "...", "swd", "...", "dwn"},
          {"swd", "swd", "swd", "...", "...", "...", "...", "...", "...", "...", "swd", "swd", "...", "swd", "...", "swd"},
          {"swd", "swd", "...", "...", "...", "...", "...", "...", "...", "...", "dwn", "swd", "...", "swd", "...", "swd"},
          {"sde", "...", "...", "...", "...", "...", "mb1", "...", "...", "...", "...", "swd", "...", "swd", "dwn", "swd"},
          {"swd", "swd", "...", "...", "...", "...", "...", "...", "...", "...", "...", "ddo", "...", "swd", "...", "dbb"},
          {"swd", "swd", "swd", "...", "...", "...", "...", "...", "...", "...", "swd", "swd", "swd", "swd", "...", "swd"},
          {"swd", "swd", "swd", "swd", "...", "...", "...", "...", "...", "swd", "swd", "swd", "swd", "swd", "dwn", "ddc"},
          {"swd", "swd", "swd", "swd", "swd", "dbb", "...", "dwn", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd"},
          {"swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd", "swd"}
  };

}
