package name.panitz.game.files.creatures;

import name.panitz.game.framework.Vertex;

public class GoblinWarrior<I> extends Creature<I> {

  public GoblinWarrior(Vertex position, int currentHP, int attackPower, int defensive, int experience, int rangeOfView) {
    super("creatureGoblinWarrior.png", position, new Vertex(0, 0), TypeOfCreatures.MONSTER, currentHP, attackPower, defensive, experience, rangeOfView);
    this.name = "Goblin Warrior";
    this.sortOfMonster = SortOfMonster.GOBLIN;
    this.maxHP = currentHP;
  }

}
