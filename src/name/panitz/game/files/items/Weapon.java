package name.panitz.game.files.items;

import name.panitz.game.framework.Vertex;

public class Weapon<I> extends Item<I> {

  public int attackPower;

  public Weapon(String imageFileName, Vertex position, Vertex motion, TypeOfItem typeOfItem, TypeOfCell typeOfCell) {
    super(imageFileName, position, motion, typeOfItem, typeOfCell);
  }

}
