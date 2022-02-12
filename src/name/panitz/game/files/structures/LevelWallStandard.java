package name.panitz.game.files.structures;

import name.panitz.game.framework.Vertex;

import java.util.Random;

public class LevelWallStandard<I> extends Structure<I> {

  static String[] imageNames = {"caveWall1.png", "caveWall1.png", "caveWall1.png", "caveWall2.png", "caveWall3.png"};

  // -- -- -- -- Constructors -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
  public LevelWallStandard(Vertex position) {
    super(TypeOfStructures.WALL,
          LevelWallStandard.imageNames[new Random().nextInt(LevelWallStandard.imageNames.length)],
          position, new Vertex(0, 0), 0);
  }

}
