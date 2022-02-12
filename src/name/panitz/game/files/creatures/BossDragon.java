package name.panitz.game.files.creatures;

import name.panitz.game.framework.Vertex;

public class BossDragon<I> extends Creature<I> {

  public BossDragon(Vertex position, int currentHP, int attackPower, int defensive, int experience, int rangeOfView) {
    super("creatureBoss1.png", position, new Vertex(0, 0), TypeOfCreatures.MONSTER, currentHP, attackPower, defensive, experience, rangeOfView);
    this.name = "Dragon";
    this.sortOfMonster = SortOfMonster.DRAGON;
    this.maxHP = currentHP;
  }

}
