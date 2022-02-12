package name.panitz.game.files.weatherConditions;

import name.panitz.game.framework.Vertex;

import java.util.Random;

public class Cloud<I> extends Weather<I> {

  private static String[] imageNames = {"weatherCloud1.png", "weatherCloud2.png", "weatherCloud3.png"};

  public Cloud(Vertex position, Vertex motion) {
    super(imageNames[new Random().nextInt(imageNames.length)], position, motion);
  }

}
