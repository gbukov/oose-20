package name.panitz.game.files.structures;

import name.panitz.game.framework.Vertex;

public class LevelDecorationWeb<I> extends Structure<I> {

  public LevelDecorationWeb(Vertex position) {
    super(TypeOfStructures.DECORATION, "web1.png", position, new Vertex(0, 0), 0);
  }

}
