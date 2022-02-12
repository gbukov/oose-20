package name.panitz.game.files.structures;

import name.panitz.game.framework.Vertex;

public class LevelFloorGrass<I> extends Structure<I> {

  // -- -- -- -- Constructors -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
  public LevelFloorGrass(Vertex position) {
    super(TypeOfStructures.FLOOR, "grass.png", position, new Vertex(0, 0), 0);
  }

}
