package name.panitz.game.files.creatures;

import name.panitz.game.files.items.Item;
import name.panitz.game.files.items.TypeOfCell;
import name.panitz.game.framework.GameObject;
import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

import java.util.List;
import java.util.Map;

public class Creature<I> extends ImageObject<I> {

  public TypeOfCreatures typeOfCreatures;
  public SortOfMonster sortOfMonster;
  public int currentHP;
  public int maxHP;
  public int attackPower;
  public int experience;
  public String name;
  public int defense;
  public Map<TypeOfCell, Item<I>> inventory;
  public int rangeOfView;
  public long lastTime = System.nanoTime();
  public String[] imgForAnimation;

  /**
   * Standard constructor for all creature.
   *
   * @param imageName       - image name with extension
   * @param position        - coordinates of the upper left corner of the creature
   * @param motion          - creature speed and direction
   * @param typeOfCreatures - type of creature (PLAYER or MONSTER)
   * @param currentHP       - number of current HP
   * @param attackPower     - creature attack power
   * @param defense         - creature defense
   * @param experience      - creature experience
   * @param rangeOfView     - range of view (the square around the creature)
   */
  Creature(
          String imageName,
          Vertex position,
          Vertex motion,
          TypeOfCreatures typeOfCreatures,
          int currentHP,
          int attackPower,
          int defense,
          int experience,
          int rangeOfView) {
    super(imageName, position, motion);
    this.currentHP       = currentHP;
    this.maxHP           = currentHP;
    this.attackPower     = attackPower;
    this.typeOfCreatures = typeOfCreatures;
    this.experience      = experience;
    this.defense         = defense;
    this.rangeOfView     = rangeOfView;
  }

  private Creature(String imageName, Vertex vertex) {
    super(imageName, vertex, new Vertex(0, 0));
  }

  // -- -- -- -- Angriff und andere Methoden -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
  public void attack(Creature<I> creature) {
    if (this.attackPower - creature.defense < 0) {
      creature.currentHP -= 1;
    } else {
      creature.currentHP -= (this.attackPower - creature.defense);
    }
    // TODO animation for Angriff?
  }

  public boolean isObjectInRangeOfView(ImageObject<I> object) {
    Vertex rangeOfViewLeftCorner = new Vertex(this.getPos().x - 50 * this.rangeOfView, this.getPos().y - 50 * this.rangeOfView);
    Vertex rangeOfViewRightCorner = new Vertex(this.getPos().x + 100 * this.rangeOfView, this.getPos().y + 100 * rangeOfView);
    return object.getPos().x >= rangeOfViewLeftCorner.x && object.getPos().x <= rangeOfViewRightCorner.x &&
           object.getPos().y >= rangeOfViewLeftCorner.y && object.getPos().y <= rangeOfViewRightCorner.y;
  }

  /**
   * Die Methode fuer die Erschaffung der Animation
   */
  public void animate() {
    // check current time (weil Tik zu schnell laeuft)
    long currentTime = System.nanoTime();
    // 1 second = 1_000_000_000 nano seconds
    if (this.imgForAnimation != null && currentTime - this.lastTime > 700_000_000) {
      this.lastTime = currentTime;
      for (int i = 0; i < this.imgForAnimation.length; i++) {
        if (this.imgForAnimation[i].equals(this.getImageFileName())) {
          if (i + 1 < this.imgForAnimation.length) {
            this.setImageFileName(this.imgForAnimation[i + 1]);
          } else {
            this.setImageFileName(this.imgForAnimation[0]);
          }
          break;
        }
      }
    }
  }

  // -- -- -- -- Bewegung und Position -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
  public void moveRight() {
    getPos().moveTo(new Vertex(getPos().x + 50, getPos().y));
  }

  public Creature<I> getNextPosRight() {
    return new Creature<>(this.getImageFileName(), new Vertex(getPos().x + 50, getPos().y));
  }

  public void moveLeft() {
    getPos().moveTo(new Vertex(getPos().x - 50, getPos().y));
  }

  public Creature<I> getNextPosLeft() {
    return new Creature<>(this.getImageFileName(), new Vertex(getPos().x - 50, getPos().y));
  }

  public void moveUp() {
    getPos().moveTo(new Vertex(getPos().x, getPos().y - 50));
  }

  public Creature<I> getNextPosAbove() {
    return new Creature<>(this.getImageFileName(), new Vertex(getPos().x, getPos().y - 50));
  }

  public void moveDown() {
    getPos().moveTo(new Vertex(getPos().x, getPos().y + 50));
  }

  public Creature<I> getNextPosBelow() {
    return new Creature<>(this.getImageFileName(), new Vertex(getPos().x, getPos().y + 50));
  }

  public boolean isCollisionWithAnotherObj(GameObject obj) {
    return this.getPos().x == obj.getPos().x && this.getPos().y == obj.getPos().y;
  }

}
