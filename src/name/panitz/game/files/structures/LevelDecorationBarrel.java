package name.panitz.game.files.structures;

import name.panitz.game.framework.Vertex;

public class LevelDecorationBarrel<I> extends Structure<I> {

  public LevelDecorationBarrel(Vertex position) {
    super(TypeOfStructures.WALL, "barrel1.png", position, new Vertex(0, 0), 0);
  }

}
