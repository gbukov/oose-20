package name.panitz.game.files.items;

import name.panitz.game.framework.Vertex;

public class HealthPotion<I> extends Support<I> {

  public int healthRestorePower;

  public HealthPotion(Vertex position, int healthRestorePower) {
    super("potionHealthSmall.png", position, new Vertex(0, 0), TypeOfItem.POTION_HEALTH, TypeOfCell.BELT);
    this.healthRestorePower = healthRestorePower;
  }

}
