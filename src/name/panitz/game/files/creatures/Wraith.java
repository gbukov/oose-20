package name.panitz.game.files.creatures;

import name.panitz.game.framework.Vertex;

public class Wraith<I> extends Creature<I> {

  public Wraith(Vertex position, int currentHP, int attackPower, int defensive, int experience, int rangeOfView) {
    super("creatureWraith.png", position, new Vertex(0, 0), TypeOfCreatures.MONSTER, currentHP, attackPower, defensive, experience, rangeOfView);
    this.name = "Wraith";
    this.sortOfMonster = SortOfMonster.GHOST;
    this.maxHP = currentHP;
  }

}
