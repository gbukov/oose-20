package name.panitz.game.files.structures;

import name.panitz.game.framework.Vertex;

public class LevelTrapStandard<I> extends Structure<I> {

  // -- -- -- -- Constructors -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
  public LevelTrapStandard(Vertex position, int attackPower) {
    super(TypeOfStructures.TRAP, "caveFloorTrap.png", position, new Vertex(0, 0), attackPower);
  }

}
