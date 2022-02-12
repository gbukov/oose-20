package name.panitz.game.files.structures;

import name.panitz.game.framework.Vertex;

public class LevelExit<I> extends Structure<I> {

  // -- -- -- -- Constructors -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
  public LevelExit(Vertex position) {
    super(TypeOfStructures.LEVEL_EXIT, "caveFloorExit.png", position, new Vertex(0, 0), 0);
  }

}
