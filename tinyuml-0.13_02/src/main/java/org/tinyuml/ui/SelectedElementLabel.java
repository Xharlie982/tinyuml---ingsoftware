package org.tinyuml.ui;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JLabel;
import org.tinyuml.draw.DiagramElement;
import org.tinyuml.draw.LabelSource;

/**
 * Status bar label (RF-002) that shows the category and the name of the
 * selected element. Shows "Sin seleccion" when nothing is selected.
 */
public class SelectedElementLabel extends JLabel {

  private static final long serialVersionUID = 1L;
  private static final String NO_SELECTION = "Sin selecci\u00f3n";
  private static final String SEVERAL = "Varios elementos";
  private static final String NO_TEXT = "(sin texto)";
  private static final String NO_NAME = "(sin nombre)";
  private static final int MAX_NOTE_TEXT = 30;
  private static final int LABEL_WIDTH = 230;

  /**
   * Constructor. Fixed width, so the rest of the status bar does not move
   * when the text changes.
   */
  public SelectedElementLabel() {
    super(NO_SELECTION, JLabel.LEFT);
    setPreferredSize(new Dimension(LABEL_WIDTH, getPreferredSize().height));
  }

  /**
   * Updates the text according to the specified selection.
   * @param selected the selected elements of the active editor
   */
  public void refresh(List<DiagramElement> selected) {
    String text = buildText(selected);
    setText(text);
    setToolTipText(text);
  }

  /**
   * Builds the text. The diagram itself is not an element, so selecting it
   * counts as no selection.
   * @param selected the selected elements
   * @return the text to show
   */
  private String buildText(List<DiagramElement> selected) {
    DiagramElement item = null;
    int count = 0;
    for (DiagramElement element : selected) {
      if (ElementCategory.of(element) != null) {
        item = element;
        count++;
      }
    }
    if (count == 0) return NO_SELECTION;
    if (count > 1) return SEVERAL;
    ElementCategory category = ElementCategory.of(item);
    return category.getDisplayName() + ": " + nameOf(category, item);
  }

  /**
   * Returns the name of the element. Relations are shown without a name.
   * @param category the category of the element
   * @param item the element
   * @return the name to show
   */
  private String nameOf(ElementCategory category, DiagramElement item) {
    if (category == ElementCategory.RELATION) return NO_NAME;
    String text = ((LabelSource) item).getLabelText();
    if (category == ElementCategory.NOTE) return summarize(text);
    return (text == null || text.trim().isEmpty()) ? NO_NAME : text;
  }

  /**
   * Shortens the text of a note to its first line, cut to a maximum length.
   * @param content the text of the note
   * @return the short text
   */
  private String summarize(String content) {
    if (content == null || content.trim().isEmpty()) return NO_TEXT;
    String[] lines = content.trim().split("\r?\n");
    String first = lines[0].trim();
    boolean cut = lines.length > 1;
    if (first.length() > MAX_NOTE_TEXT) {
      first = first.substring(0, MAX_NOTE_TEXT);
      cut = true;
    }
    return cut ? first + "..." : first;
  }
}