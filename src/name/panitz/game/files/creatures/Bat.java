package name.panitz.game.files.creatures;

import name.panitz.game.framework.Vertex;

public class Bat<I> extends Creature<I> {

  public Bat(Vertex position, int currentHP, int attackPower, int defensive, int experience, int rangeOfView) {
    super("creatureBat1.png", position, new Vertex(0, 0), TypeOfCreatures.MONSTER, currentHP, attackPower, defensive, experience, rangeOfView);
    this.name = "Bat";
    this.sortOfMonster = SortOfMonster.BAT;
    this.maxHP = currentHP;
    this.imgForAnimation = new String[]{"creatureBat1.png", "creatureBat2.png"};
  }

}
