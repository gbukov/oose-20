package name.panitz.game.files;

import name.panitz.game.files.weatherConditions.Cloud;
import name.panitz.game.files.weatherConditions.Fog;
import name.panitz.game.files.weatherConditions.Weather;
import name.panitz.game.framework.Vertex;

import java.util.ArrayList;
import java.util.List;

class WeatherController<I> {

  private List<Weather<I>> mainLayer;

  WeatherController() {
    this.mainLayer = new ArrayList<>();
  }

  List<Weather<I>> getWeatherConditions(String code) {
    switch (code) {
      case "wsc":
        this.prepareCloudyWeatherNormal();
        break;
      case "wsf":
        this.prepareFogNormal();
        break;
    }
    return this.mainLayer;
  }

  private void prepareCloudyWeatherNormal() {
    mainLayer.add(new Cloud<>(new Vertex(800, 100),  new Vertex(-0.5, 0)));
    mainLayer.add(new Cloud<>(new Vertex(1600, 200), new Vertex(-0.75, 0)));
    mainLayer.add(new Cloud<>(new Vertex(300, 150),  new Vertex(-0.4, 0)));
    mainLayer.add(new Cloud<>(new Vertex(50, 450),   new Vertex(-0.1, 0)));
    mainLayer.add(new Cloud<>(new Vertex(1100, 400), new Vertex(-0.5, 0)));
    mainLayer.add(new Cloud<>(new Vertex(900, 650),  new Vertex(-0.3, 0)));
  }

  private void prepareFogNormal() {
    mainLayer.add(new Fog<>(new Vertex(800, 100),  new Vertex(-0.5, 0)));
    mainLayer.add(new Fog<>(new Vertex(1600, 200), new Vertex(-0.75, 0)));
    mainLayer.add(new Fog<>(new Vertex(300, 150),  new Vertex(-0.4, 0)));
    mainLayer.add(new Fog<>(new Vertex(50, 450),   new Vertex(-0.1, 0)));
    mainLayer.add(new Fog<>(new Vertex(1100, 400), new Vertex(-0.5, 0)));
    mainLayer.add(new Fog<>(new Vertex(900, 650),  new Vertex(-0.3, 0)));
  }

  static void moveElements(List<? extends Weather> weatherConditions) {
    if (weatherConditions == null) return;
    for (Weather weather : weatherConditions) {
      if (weather.getPos().x < -250) weather.setPos(new Vertex(weather.getPos().x + 1100, weather.getPos().y));
    }
  }

}
