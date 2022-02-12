package name.panitz.game.files.structures;

import name.panitz.game.framework.Vertex;

public class LevelDecorationDoorOpen<I> extends Structure<I> {

  public LevelDecorationDoorOpen(Vertex position) {
    super(TypeOfStructures.DECORATION, "doorOpen.png", position, new Vertex(0, 0), 0);
  }

}
