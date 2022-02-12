package name.panitz.game.files.weatherConditions;

import name.panitz.game.framework.ImageObject;
import name.panitz.game.framework.Vertex;

public class Weather<I> extends ImageObject<I> {

  public Weather(String imageFileName, Vertex position, Vertex motion) {
    super(imageFileName, position, motion);
  }

}
