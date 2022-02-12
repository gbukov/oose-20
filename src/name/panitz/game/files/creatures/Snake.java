package name.panitz.game.files.creatures;

import name.panitz.game.framework.Vertex;

public class Snake<I> extends Creature<I> {

  public Snake(Vertex position, int currentHP, int attackPower, int defensive, int experience, int rangeOfView) {
    super("creatureSnake1.png", position, new Vertex(0, 0), TypeOfCreatures.MONSTER, currentHP, attackPower, defensive, experience, rangeOfView);
    this.name = "Snake";
    this.sortOfMonster = SortOfMonster.SNAKE;
    this.maxHP = currentHP;
    this.imgForAnimation = new String[]{"creatureSnake1.png", "creatureSnake2.png"};
  }

}
