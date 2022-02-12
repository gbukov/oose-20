package name.panitz.game.files.creatures;

import name.panitz.game.framework.Vertex;

public class Skeleton<I> extends Creature<I> {

  public Skeleton(Vertex position, int currentHP, int attackPower, int defensive, int experience, int rangeOfView) {
    super("creatureSkeleton1.png", position, new Vertex(0, 0), TypeOfCreatures.MONSTER, currentHP, attackPower, defensive, experience, rangeOfView);
    this.name = "Skeleton";
    this.sortOfMonster = SortOfMonster.SKELETON;
    this.maxHP = currentHP;
    this.imgForAnimation = new String[]{"creatureSkeleton1.png", "creatureSkeleton2.png"};
  }

}
