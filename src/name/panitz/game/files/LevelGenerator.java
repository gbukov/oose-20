package name.panitz.game.files;

import name.panitz.game.files.creatures.*;
import name.panitz.game.files.items.*;
import name.panitz.game.files.structures.*;
import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

import java.util.*;
import java.util.stream.IntStream;

class LevelGenerator<I> {

  /**
   * Die Methode bereitet alle wichtige Elemente fuer die neue Ebene.
   *
   * @param player - die noetige Ebennummer
   * @return - Map mit mindestens 3 wichtigen Elementen: "structures", "creatures" und "items".
   */
  Map<String, List<? extends GameObject<I>>> loadLevel(Player<I> player) {
    // Create all elements for new map
    Map<String, List<? extends GameObject<I>>> levelElements = new HashMap<>();
    List<Structure<I>> structures = new ArrayList<>();
    List<Creature<I>> creatures = new ArrayList<>();
    List<ImageObject<I>> healthBars = new ArrayList<>();
    List<Item<I>> items = new ArrayList<>();
    // Add them
    levelElements.put("floor", generateFloor());
    levelElements.put("structures", structures);
    levelElements.put("creatures", creatures);
    levelElements.put("items", items);
    levelElements.put("healthBars", healthBars);
    // Create (first level and level before the boss) or generate rlrg level
    var sof = new SetOfLevels();
    var level = sof.getLevel(player);
    // Parse map from exc source
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 16; j++) {
        int x = j * 50;
        int y = i * 50;
        switch (level[i][j]) {
          case "swd": // Die Schlosswand // s(tructure)w(all)d(ungeon)
            structures.add(new LevelWallStandard<>(new Vertex(x, y)));
            break;
          case "sde": // Der Eingang (standard dungeon) // s(tructure)d(oor)e(ingang)
            structures.add(new LevelEnter<>(new Vertex(x, y)));
            player.setPos(new Vertex(x, y));
            break;
          case "sdr": // Der Eingang (for 1 level) // s(tructure)d(oor)r(espawn)
            structures.add(new LevelFloorGrass<>(new Vertex(x, y)));
            player.setPos(new Vertex(x, y));
            break;
          case "sda": // Der Ausgang // s(tructure)d(oor)a(usgang)
            structures.add(new LevelExit<>(new Vertex(x, y)));
            break;
          case "sfg": // Grass // s(tructure)f(loor)g(rass)
            structures.add(new LevelFloorGrass<>(new Vertex(x, y)));
            break;
          case "swt": // Tree // s(tructure)w(all)t(ree)
            structures.add(new LevelTreeWithGrass<>(new Vertex(x, y)));
            break;
          case "sts": // Die Falle // s(tructure)t(rap)s(imple)
            structures.add(new LevelTrapStandard<>(new Vertex(x, y), 5));
            break;
          case "ddo": // Die oeffene Tuer // d(ecoration)d(oor)o(pen)
            structures.add(new LevelDecorationDoorOpen<>(new Vertex(x, y)));
            break;
          case "ddc": // Die geschlossene Tuer // d(ecoration)d(oor)c(losed)
            structures.add(new LevelDecorationDoorClosed<>(new Vertex(x, y)));
            break;
          case "dbb": // Das Fass // d(ecoration)b(arrel)b(rawn)
            structures.add(new LevelDecorationBarrel<>(new Vertex(x, y)));
            break;
          case "dwn": // derSpinngewebe // d(ecoration)w(eb)n(ormal)
            structures.add(new LevelDecorationWeb<>(new Vertex(x, y)));
            break;
          case "mag": // Monster // m(onster)a(uto)g(enerate)
            this.generateMonster(creatures, player, new Vertex(x, y));
            break;
          case "mb1": // Monster // m(onster)b(oss)1(the first one)
            creatures.add(new BossDragon<>(new Vertex(x, y), 50, 25, 0, 1000, 10));
            break;
          case "phs": // Health potion, small // p(otion)h(ealth)s(mall)
            items.add(new HealthPotion<>(new Vertex(x, y), 20));
            break;
          case "wss": // Simple sword // w(eapon)s(imple)s(word) // todo general weapon support usw.
            items.add(new SimpleSword<>(new Vertex(x, y), 5));
            break;
          case "abs": // Armor // a(rmor)b(ody)s(tandard)
            this.generateArmorItem(items, player, new Vertex(x, y));
            break;
          case "wsc": // Simply clouds // w(eather)s(imply)c(loud)
            levelElements.put("weatherConditions", new WeatherController<I>().getWeatherConditions(level[i][j]));
            break;
          case "wsf": // Simply fog // w(eather)s(imply)f(og)
            levelElements.put("weatherConditions", new WeatherController<I>().getWeatherConditions(level[i][j]));
            break;
        }
      }
    }
    this.generateHealthStatusBarsForEnemy(healthBars);
    return levelElements;
  }

  private void generateArmorItem(List<Item<I>> items, Player player, Vertex position) {
    int rndNumberForArmorOrNichts;
    switch (player.dungeonLevel) {
      // Die Wahrscheinlichkeit: Iron body 30%, Copper body 3%
      case 3:
        List<Item<I>> level3 = new ArrayList<>();
        IntStream.range(0, 7).forEach(x -> level3.add(new ArmorBodyIron<>(position)));
        IntStream.range(0, 3).forEach(x -> level3.add(new ArmorBodyCopper<>(position)));
        rndNumberForArmorOrNichts = new Random().nextInt(level3.size() * 3);
        if (rndNumberForArmorOrNichts < level3.size()) items.add(level3.get(rndNumberForArmorOrNichts));
        break;
      // Die Wahrscheinlichkeit: Iron body 40%, Copper body 10%
      case 4:
        List<Item<I>> level4 = new ArrayList<>();
        IntStream.range(0, 7).forEach(x -> level4.add(new ArmorBodyIron<>(position)));
        IntStream.range(0, 3).forEach(x -> level4.add(new ArmorBodyCopper<>(position)));
        rndNumberForArmorOrNichts = new Random().nextInt(level4.size() * 2);
        if (rndNumberForArmorOrNichts < level4.size()) items.add(level4.get(rndNumberForArmorOrNichts));
        break;
      // Die Wahrscheinlichkeit: Iron body 30%, Copper body 15%
      case 5:
        List<Item<I>> level5 = new ArrayList<>();
        IntStream.range(0, 6).forEach(x -> level5.add(new ArmorBodyIron<>(position)));
        IntStream.range(0, 3).forEach(x -> level5.add(new ArmorBodyCopper<>(position)));
        rndNumberForArmorOrNichts = new Random().nextInt(level5.size() * 2);
        if (rndNumberForArmorOrNichts < level5.size()) items.add(level5.get(rndNumberForArmorOrNichts));
        break;
      // Die Wahrscheinlichkeit: Iron body 50%, Copper body 50%
      case 6:
        List<Item<I>> level6 = new ArrayList<>();
        IntStream.range(0, 5).forEach(x -> level6.add(new ArmorBodyIron<>(position)));
        IntStream.range(0, 5).forEach(x -> level6.add(new ArmorBodyCopper<>(position)));
        rndNumberForArmorOrNichts = new Random().nextInt(level6.size());
        if (rndNumberForArmorOrNichts < level6.size()) items.add(level6.get(rndNumberForArmorOrNichts));
    }
  }

  private void generateMonster(List<Creature<I>> creatures, Player player, Vertex position) {
    switch (player.dungeonLevel) {
      case 1: // todo for tests
        List<Creature<I>> level1 = new ArrayList<>();
        IntStream.range(0, 10).forEach(x -> level1.add(new Snake<>(position, 5, 1, 0, 10, 2)));
        creatures.add(level1.get(new Random().nextInt(level1.size())));
        break;
      // Die Wahrscheinlichkeit: die Fledermaus 100%
      case 2:
        List<Creature<I>> level2 = new ArrayList<>();
        IntStream.range(0, 10).forEach(x -> level2.add(new Bat<>(position, 5, 1, 0, 40, 3)));
        creatures.add(level2.get(new Random().nextInt(level2.size())));
        break;
      // Die Wahrscheinlichkeit: die Schlange 50%, die Fledermaus 50%
      case 3:
        List<Creature<I>> level3 = new ArrayList<>();
        IntStream.range(0, 5).forEach(x -> level3.add(new Snake<>(position, 9, 2, 0, 70, 5)));
        IntStream.range(0, 5).forEach(x -> level3.add(new Bat<>(position, 7, 2, 0, 40, 3)));
        creatures.add(level3.get(new Random().nextInt(level3.size())));
        break;
      // Die Wahrscheinlichkeit: die Schlange 40%, das Skelett 60%
      case 4:
        List<Creature<I>> level4 = new ArrayList<>();
        IntStream.range(0, 3).forEach(x -> level4.add(new Snake<>(position, 8, 4, 0, 70, 5)));
        IntStream.range(0, 7).forEach(x -> level4.add(new Skeleton<>(position, 12, 5, 0, 150, 3)));
        creatures.add(level4.get(new Random().nextInt(level4.size())));
        break;
      // Die Wahrscheinlichkeit: die Schlange 10%, das Skelett 40%, der Geist 50%
      case 5:
        List<Creature<I>> level5 = new ArrayList<>();
        IntStream.range(0, 1).forEach(x -> level5.add(new Snake<>(position, 9, 5, 0, 70, 5)));
        IntStream.range(0, 3).forEach(x -> level5.add(new Skeleton<>(position, 13, 6, 0, 150, 3)));
        IntStream.range(0, 6).forEach(x -> level5.add(new Wraith<>(position, 18, 10, 0, 250, 5)));
        creatures.add(level5.get(new Random().nextInt(level5.size())));
        break;
      // Die Wahrscheinlichkeit: das Skelett 20%, der Geist 20%, der Goblin 60%
      case 6:
        List<Creature<I>> level6 = new ArrayList<>();
        IntStream.range(0, 2).forEach(x -> level6.add(new Skeleton<>(position, 16, 8, 0, 70, 6)));
        IntStream.range(0, 2).forEach(x -> level6.add(new Wraith<>(position, 20, 12, 0, 250, 3)));
        IntStream.range(0, 6).forEach(x -> level6.add(new GoblinWarrior<>(position, 30, 17, 0, 400, 5)));
        creatures.add(level6.get(new Random().nextInt(level6.size())));
        break;
    }
  }

  private void generateHealthStatusBarsForEnemy(List<ImageObject<I>> healthBars) {
    for (int i = 0; i < 40; i++) {
      healthBars.add(new ImageObject<>("healthStatusGreen.png", new Vertex(-100, -100)));
      healthBars.add(new ImageObject<>("healthStatusYellow.png", new Vertex(-100, -100)));
      healthBars.add(new ImageObject<>("healthStatusOrange.png", new Vertex(-100, -100)));
      healthBars.add(new ImageObject<>("healthStatusRed.png", new Vertex(-100, -100)));
    }
  }

  private List<Structure<I>> generateFloor() {
    List<Structure<I>> floor = new ArrayList<>();
    for (int j = 50; j < 600; j += 50) {
      for (int i = 0; i < 800; i += 50) {
        floor.add(new LevelFloorStandard<>(new Vertex(i, j)));
      }
    }
    return floor;
  }

}
