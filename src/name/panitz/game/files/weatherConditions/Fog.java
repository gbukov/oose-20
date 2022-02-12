package name.panitz.game.files.weatherConditions;

import name.panitz.game.framework.Vertex;

import java.util.Random;

public class Fog<I> extends Weather<I> {

  private static String[] imageNames = {"weatherFog1.png"};

  public Fog(Vertex position, Vertex motion) {
    super(imageNames[new Random().nextInt(imageNames.length)], position, motion);
  }

}
