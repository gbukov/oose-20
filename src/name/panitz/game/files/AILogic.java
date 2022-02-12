package name.panitz.game.files;

import name.panitz.game.files.structures.Structure;
import name.panitz.game.files.structures.TypeOfStructures;
import name.panitz.game.files.creatures.Creature;
import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

import java.util.List;

class AILogic<I> {

  void turn(List<Creature<I>> creatures, Creature<I> player, List<Structure<I>> levelStructures, List<ImageObject<I>> healthBars) {
    // reset all health bars to default position
    healthBars.forEach(x -> x.setPos(new Vertex(-100, -100)));
    // If the player is in the field of view, then check the possibility
    // of a move for the opponent and calculate the direction
    for (Creature<I> creature : creatures) {
      if (creature.isObjectInRangeOfView(player)) {
        boolean movePermission;
        if (player.isLeftOf(creature)) {
          Creature<I> creatureInTheFuture = creature.getNextPosLeft();
          movePermission = this.checkPossibilityOfMove(creatureInTheFuture, creature, player, creatures, levelStructures);
          if (movePermission) creature.moveLeft();
        } else if (player.isRightOf(creature)) {
          Creature<I> creatureInTheFuture = creature.getNextPosRight();
          movePermission = this.checkPossibilityOfMove(creatureInTheFuture, creature, player, creatures, levelStructures);
          if (movePermission) creature.moveRight();
        } else if (player.isAbove(creature)) {
          Creature<I> creatureInTheFuture = creature.getNextPosAbove();
          movePermission = this.checkPossibilityOfMove(creatureInTheFuture, creature, player, creatures, levelStructures);
          if (movePermission) creature.moveUp();
        } else if (player.isBelow(creature)) {
          Creature<I> creatureInTheFuture = creature.getNextPosBelow();
          movePermission = this.checkPossibilityOfMove(creatureInTheFuture, creature, player, creatures, levelStructures);
          if (movePermission) creature.moveDown();
        }
      }
      this.updateHealthBars(creature, healthBars);
    }
  }

  private boolean checkPossibilityOfMove(
          Creature<I> creatureInTheFuture,
          Creature<I> creature,
          Creature<I> player,
          List<Creature<I>> creatures,
          List<Structure<I>> levelStructures) {
    if (player.isCollisionWithAnotherObj(creatureInTheFuture)) {
      creature.attack(player);
      return false;
    }
    // don't let monsters go through walls, barrels etc.
    for (Structure levelStructure : levelStructures) {
      boolean collision1 = creatureInTheFuture.isCollisionWithAnotherObj(levelStructure);
      boolean collision2 = levelStructure.typeOfStructures == TypeOfStructures.WALL;
      if (collision1 && collision2) return false;
    }
    // don't let monsters stack
    for (Creature anotherCreature : creatures) {
      if (!(anotherCreature.getPos().x == creature.getPos().x && anotherCreature.getPos().y == creature.getPos().y)
          && creatureInTheFuture.isCollisionWithAnotherObj(anotherCreature)) return false;
    }
    return true;
  }

  private void updateHealthBars(Creature<I> creature, List<ImageObject<I>> healthBars) {
    if (creature.currentHP > 0 && creature.currentHP < creature.maxHP) {
      int restHPInPercent = (int) (((double) creature.currentHP / creature.maxHP) * 100);
      String healthBarImgName = "";
      if (restHPInPercent >= 75) {
        healthBarImgName = "healthStatusGreen.png";
      } else if (restHPInPercent >= 50) {
        healthBarImgName = "healthStatusYellow.png";
      } else if (restHPInPercent >= 30) {
        healthBarImgName = "healthStatusOrange.png";
      } else if (restHPInPercent >= 10) {
        healthBarImgName = "healthStatusRed.png";
      }
      for (ImageObject<I> bar : healthBars) {
        if (bar.getPos().x == -100 && bar.getPos().y == -100 && bar.getImageFileName().equals(healthBarImgName)) {
          bar.setPos(new Vertex(creature.getPos().x, creature.getPos().y));
          break;
        }
      }
    }
  }

}
