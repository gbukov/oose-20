package name.panitz.game.files.items;

import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Item<I> extends ImageObject<I> {

  public TypeOfItem typeOfItem;
  public TypeOfCell typeOfCell;

  // -- -- -- -- Constructors -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
  public Item(String imageFileName, Vertex position, Vertex motion, TypeOfItem typeOfItem, TypeOfCell typeOfCell) {
    super(imageFileName, position, motion);
    this.typeOfItem = typeOfItem;
    this.typeOfCell = typeOfCell;
  }
}
