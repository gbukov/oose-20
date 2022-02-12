package name.panitz.game.files.structures;

import name.panitz.game.framework.Vertex;

public class LevelEnter<I> extends Structure<I> {

  public LevelEnter(Vertex position) {
    super(TypeOfStructures.LEVEL_ENTER, "caveFloorEnter.png", position, new Vertex(0, 0), 0);
  }

}
