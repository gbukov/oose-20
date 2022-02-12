package name.panitz.game.files.creatures;

import name.panitz.game.files.GameController;
import name.panitz.game.files.items.*;
import name.panitz.game.framework.SoundObject;
import name.panitz.game.framework.Vertex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;

public class Player<I> extends Creature<I> {

  public int dungeonLevel;
  private String[] namesForPlayer = {
          "Henry Sa", "Sascha G", "Elena Vi", "Mueller Fu",
          "Sofia Ur", "Mark Grh", "Joschua Po", "Evan Misk"};

  public Player() {
    super("playerGreen1.png", new Vertex(0, 400), new Vertex(0, 0), TypeOfCreatures.PLAYER, 1, 2, 0, 0, 1);
    this.dungeonLevel = 0;
    this.name = namesForPlayer[new Random().nextInt(namesForPlayer.length)];
    this.inventory = new HashMap<>();
    this.imgForAnimation = new String[]{"playerGreen1.png", "playerGreen2.png"/*, "playerGreen3.png"*/};
  }

  public void addItemToInventory(Item<I> item) {
    switch (item.typeOfCell) {
      case LEFT_HAND:
        // zuerst subtrahieren, wenn es noetig ist
        var weapon = (Weapon<I>) this.inventory.get(TypeOfCell.LEFT_HAND);
        if (weapon != null) this.attackPower -= weapon.attackPower;
        // dann addieren
        this.attackPower += ((Weapon<I>) item).attackPower;
        item.setPos(new Vertex(500, 0));
        this.inventory.put(item.typeOfCell, item);
        break;
      case ARMOR_BODY:
        // zuerst subtrahieren, wenn es noetig ist
        var armor = (Armor<I>) this.inventory.get(TypeOfCell.ARMOR_BODY);
        if (armor != null) this.defense -= armor.defense;
        // dann addieren
        this.defense += ((Armor<I>) item).defense;
        item.setPos(new Vertex(550, 0));
        this.inventory.put(item.typeOfCell, item);
        break;
    }
  }

  public SoundObject playAttackSound() {
    for (Item item : this.inventory.values().stream().collect(Collectors.toList())) {
      if (item.typeOfItem == TypeOfItem.SWORD) {
        return new SoundObject<>("playerAttackSword.wav");
      }
    }
    return new SoundObject<>("playerAttackFaust.wav");
  }

  public void addExperience(int experience, GameController gameController) {
    if ((this.experience < 200  && this.experience + experience >= 200 ) ||
        (this.experience < 500  && this.experience + experience >= 500 ) ||
        (this.experience < 1100 && this.experience + experience >= 1100) ||
        (this.experience < 2100 && this.experience + experience >= 2100) ||
        (this.experience < 3000 && this.experience + experience >= 3000) ||
        (this.experience < 5000 && this.experience + experience >= 5000) ||
        (this.experience < 9999 && this.experience + experience >= 9999)) {
      this.maxHP += 5;
      this.currentHP = this.maxHP;
      this.attackPower += 1;
      this.defense += 1;
      gameController.playSound(new SoundObject("levelUp.wav"));
    }
    this.experience += experience;
  }

  // TODO (etw ist schief gegangen, deswegen habe ich die Methode geschaffen)
  public void resurrect() {
    this.name = namesForPlayer[new Random().nextInt(namesForPlayer.length)];
    this.inventory = new HashMap<>();
    this.dungeonLevel = 0;
    this.currentHP = 20;
    this.maxHP = 20;
    this.attackPower = 1;
    this.defense = 0;
    this.experience = 0;
  }

}
