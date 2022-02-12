package name.panitz.game.files.structures;

import name.panitz.game.framework.Vertex;

import java.util.Random;

public class LevelTreeWithGrass<I> extends Structure<I> {

  private static String[] imageNames = {"grassAndTreeGreen.png", "grassAndTreeYellow.png"};

  // -- -- -- -- Constructors -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
  public LevelTreeWithGrass(Vertex position) {
    super(TypeOfStructures.WALL,
          LevelTreeWithGrass.imageNames[new Random().nextInt(LevelTreeWithGrass.imageNames.length)],
          position, new Vertex(0, 0), 0);
  }

}