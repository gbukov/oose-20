package name.panitz.game.files.structures;

        import name.panitz.game.files.creatures.Creature;
        import name.panitz.game.framework.ImageObject;
        import name.panitz.game.framework.Vertex;

public class Structure<I> extends ImageObject<I> {

  public TypeOfStructures typeOfStructures;
  int attackPower;

  // -- -- -- -- Constructors -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
  public Structure(TypeOfStructures typeOfStructure, String imgName, Vertex position, Vertex motion, int attackPower) {
    super(imgName, position, motion);
    this.typeOfStructures = typeOfStructure;
    this.attackPower = attackPower;
  }

  // -- -- -- -- Angriff und andere Methoden -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
  public void damage(Creature<I> creature) {
    creature.currentHP -= this.attackPower;
  }

}
