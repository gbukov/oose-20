package name.panitz.game.files.structures;

import name.panitz.game.framework.Vertex;

import java.util.Random;

public class LevelFloorStandard<I> extends Structure<I> {

  static String[] imageNames = {"caveFloorStandard.png"};

  // -- -- -- -- Constructors -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
  public LevelFloorStandard(Vertex position) {
    super(TypeOfStructures.FLOOR,
          LevelFloorStandard.imageNames[new Random().nextInt(LevelFloorStandard.imageNames.length)],
          position, new Vertex(0, 0), 0);
  }

}
