package name.panitz.game.files;

import name.panitz.game.files.creatures.Player;
import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.TextObject;
import name.panitz.game.framework.Vertex;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class UserInterface<I> {

  private TextObject<I> currentHP;
  private TextObject<I> maxHP;
  private TextObject<I> attackPower;
  private TextObject<I> defense;
  private TextObject<I> experience;
  private TextObject<I> currentLevel;
  private TextObject<I> playerName;

  List<GameObject<I>> prepareStatusInterfaceFor(Player<I> player) {
    List<GameObject<I>> topMenu = new ArrayList<>();
    ImageObject<I> topMenuBackground = new ImageObject<>("topMenuGray.png" ,new Vertex(0, 0));
    playerName   = new TextObject<>(new Vertex(10, 22), player.name, "Helvetica", 18, Font.BOLD);
    currentHP    = new TextObject<>(new Vertex(10, 41), "HP: " + player.currentHP, "Helvetica", 18);
    maxHP        = new TextObject<>(new Vertex(65, 41), " / " + player.maxHP, "Helvetica", 18);
    attackPower  = new TextObject<>(new Vertex(280, 41), "AP: " + player.attackPower, "Helvetica", 18);
    defense      = new TextObject<>(new Vertex(170, 22), "DEF: " + player.defense, "Helvetica", 18);
    experience   = new TextObject<>(new Vertex(170, 41), "EXP: " + player.experience, "Helvetica", 18);
    currentLevel = new TextObject<>(new Vertex(280, 22), "Dungeon level: " + player.dungeonLevel, "Helvetica", 18, Font.BOLD);
    topMenu.add(topMenuBackground);
    topMenu.add(playerName);
    topMenu.add(currentHP);
    topMenu.add(maxHP);
    topMenu.add(attackPower);
    topMenu.add(defense);
    topMenu.add(experience);
    topMenu.add(currentLevel);
    return topMenu;
  }

  void updateUserStatusInterface(Player<I> player) {
    currentHP.text = "HP: " + player.currentHP;
    maxHP.text = " / " + player.maxHP;
    attackPower.text = "AP: " + player.attackPower;
    defense.text = "DEF: " + player.defense;
    experience.text = "EXP: " + player.experience;
    currentLevel.text = "Dungeon level: " + player.dungeonLevel;
  }


}
