package name.panitz.game.files.items;

import name.panitz.game.framework.Vertex;

public class Armor<I> extends Item<I> {

  public int defense;

  public Armor(String imageFileName, Vertex position, Vertex motion, TypeOfItem typeOfItem, TypeOfCell typeOfCell) {
    super(imageFileName, position, motion, typeOfItem, typeOfCell);
  }

}
