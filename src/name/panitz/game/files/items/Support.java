package name.panitz.game.files.items;

import name.panitz.game.framework.Vertex;

public class Support<I> extends Item<I> {
  public Support(String imageFileName, Vertex position, Vertex motion, TypeOfItem typeOfItem, TypeOfCell typeOfCell) {
    super(imageFileName, position, motion, typeOfItem, typeOfCell);
  }
}
