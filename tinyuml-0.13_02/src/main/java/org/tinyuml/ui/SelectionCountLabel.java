package org.tinyuml.ui;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JLabel;
import org.tinyuml.draw.DiagramElement;

/**
 * Status bar label (RF-003) that shows how many elements are selected.
 * The diagram itself is not an element, so selecting it counts as zero.
 */
public class SelectionCountLabel extends JLabel {

  private static final long serialVersionUID = 1L;
  private static final int LABEL_WIDTH = 110;

  /**
   * Constructor. Starts showing zero. Fixed width, so the rest of the
   * status bar does not move when the number changes.
   */
  public SelectionCountLabel() {
    super(buildText(0));
    setPreferredSize(new Dimension(LABEL_WIDTH, getPreferredSize().height));
  }

  /**
   * Updates the text according to the specified selection.
   * @param selected the selected elements of the active editor
   */
  public void refresh(List<DiagramElement> selected) {
    int count = 0;
    for (DiagramElement element : selected) {
      if (ElementCategory.of(element) != null) {
        count++;
      }
    }
    setText(buildText(count));
  }

  /**
   * Builds the text shown in the status bar.
   * @param count the number of selected elements
   * @return the text
   */
  private static String buildText(int count) {
    return "Seleccionados: " + count;
  }
}