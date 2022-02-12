package name.panitz.game.framework;

import java.awt.*;

public class TextObject<I> extends AbstractGameObject<I> {
  public String text;
  public int style;
  public String fontName;
  public int fontSize;

  public TextObject(Vertex position, String text, String fntName, int fntSize) {
    super(0, 0, position);
    this.text = text;
    this.style = Font.PLAIN;
    this.fontName = fntName;
    this.fontSize = fntSize;
  }

  public TextObject(Vertex position, String text, String fntName, int fntSize, int style) {
    super(0, 0, position);
    this.text = text;
    this.style = style;
    this.fontName = fntName;
    this.fontSize = fntSize;
  }

  @Override
  public void paintTo(GraphicsTool<I> g) {
    g.drawString(getPos().x, getPos().y, fontSize, fontName, text, style);
  }
}

