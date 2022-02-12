package name.panitz.game.files.items;

import name.panitz.game.framework.Vertex;

public class ArmorBodyIron<I> extends Armor<I> {

  public ArmorBodyIron(Vertex position) {
    super("itemArmorBodyIron1.png", position, new Vertex(0, 0), TypeOfItem.ARMOR_BODY, TypeOfCell.ARMOR_BODY);
    this.defense = 5;
  }

}
