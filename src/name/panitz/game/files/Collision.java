package name.panitz.game.files;

import name.panitz.game.files.creatures.Creature;
import name.panitz.game.files.creatures.Player;
import name.panitz.game.files.creatures.TypeOfCreatures;
import name.panitz.game.files.items.HealthPotion;
import name.panitz.game.files.items.Item;
import name.panitz.game.files.items.TypeOfCell;
import name.panitz.game.files.items.TypeOfItem;
import name.panitz.game.files.structures.Structure;
import name.panitz.game.files.structures.TypeOfStructures;
import name.panitz.game.framework.SoundObject;
import name.panitz.game.framework.Vertex;

import java.util.List;

public class Collision<I, S> {

  /**
   * Ueberpruefen Kollision (mit Waende, Gegner usw.)
   *
   * @param playerNextPosition - ist die zukunftige Position des Spielers!!
   * @return darf der Spieler sich bewegen oder nicht, falls ja was genau mit ihm passiert
   */
  boolean checkFor(
          Creature<I> playerNextPosition,
          Player<I> player,
          List<Structure<I>> levelStructures,
          List<Creature<I>> creatures,
          List<Item<I>> items,
          GameController<I, S> gameController) {
    boolean movePermission = true;
    // Bildschirmgrenze
    if (playerNextPosition.getPos().x >= 800 || playerNextPosition.getPos().x < 0 ||
        playerNextPosition.getPos().y < 0    || playerNextPosition.getPos().y >= 600) {
      movePermission = false;
    }
    // Level collisions
    for (Structure<I> obj : levelStructures) {
      // With door to the next level
      if (obj.typeOfStructures == TypeOfStructures.LEVEL_EXIT && player.isCollisionWithAnotherObj(obj)) {
        // TODO наверно можно удалить такой вызов метоа
        gameController.loadLevel();
      }
      // With walls
      if (obj.typeOfStructures == TypeOfStructures.WALL && playerNextPosition.isCollisionWithAnotherObj(obj)) {
        movePermission = false;
      }
      // With traps
      if (obj.typeOfStructures == TypeOfStructures.TRAP && playerNextPosition.isCollisionWithAnotherObj(obj)) {
        obj.damage(player);
        gameController.playSound(new SoundObject<>("trapNormal.wav"));
        movePermission = true;
      }
    }
    // Creatures collisions
    for (Creature<I> creature : creatures) {
      // Monster TODO Refactor 7
      if (creature.typeOfCreatures == TypeOfCreatures.MONSTER && (
              playerNextPosition.isCollisionWithAnotherObj(creature))) {
        player.attack(creature);
        gameController.playSound(player.playAttackSound());
        if (creature.currentHP <= 0) {
          player.addExperience(creature.experience, gameController);
          creature.setPos(new Vertex(-300, -300)); // maybe remove? #1
        } else {
          creature.attack(player);
        }
        return false;
      }
    }
    // Items collision
    for (Item<I> item : items) {
      // With health potions (15.01 [@Deprecated soll sein]); (23.01 wieso? :( hab vergessen)
      if (item.typeOfItem == TypeOfItem.POTION_HEALTH && playerNextPosition.isCollisionWithAnotherObj(item)) {
        player.currentHP += ((HealthPotion<I>) item).healthRestorePower;
        if (player.currentHP > player.maxHP) {
          player.currentHP = player.maxHP;
        }
        item.setPos(new Vertex(-300, -300)); // maybe remove? #2
        movePermission = true;
      }
      // With Items for the left hand
      if (item.typeOfCell == TypeOfCell.LEFT_HAND && playerNextPosition.isCollisionWithAnotherObj(item)) {
        player.addItemToInventory(item);
        movePermission = true;
      }
      // With Items for the body
      if (item.typeOfCell == TypeOfCell.ARMOR_BODY && playerNextPosition.isCollisionWithAnotherObj(item)) {
        player.addItemToInventory(item);
        movePermission = true;
      }
    }
    return movePermission;
  }

}
