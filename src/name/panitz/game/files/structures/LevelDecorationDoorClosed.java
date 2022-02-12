package name.panitz.game.files.structures;

import name.panitz.game.framework.Vertex;

public class LevelDecorationDoorClosed<I> extends Structure<I> {

  public LevelDecorationDoorClosed(Vertex position) {
    super(TypeOfStructures.WALL, "doorClosed.png", position, new Vertex(0, 0), 0);
  }

}
