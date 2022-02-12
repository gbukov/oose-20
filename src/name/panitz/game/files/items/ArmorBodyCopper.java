package name.panitz.game.files.items;

import name.panitz.game.framework.Vertex;

public class ArmorBodyCopper<I> extends Armor<I> {

  public ArmorBodyCopper(Vertex position) {
    super("itemArmorBodyCopper1.png", position, new Vertex(0, 0), TypeOfItem.ARMOR_BODY, TypeOfCell.ARMOR_BODY);
    this.defense = 8;
  }

}
