package name.panitz.game.files.items;

import name.panitz.game.framework.Vertex;

public class SimpleSword<I> extends Weapon<I> {

  public SimpleSword(Vertex position, int attackPower) {
    super("itemSimplySword.png", position, new Vertex(0, 0), TypeOfItem.SWORD, TypeOfCell.LEFT_HAND);
    // todo random +-
    this.attackPower = attackPower;
  }

}
