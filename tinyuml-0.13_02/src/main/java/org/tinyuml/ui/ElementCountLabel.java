package org.tinyuml.ui;

import javax.swing.JLabel;
import org.tinyuml.draw.AbstractCompositeNode;
import org.tinyuml.draw.DiagramElement;
import org.tinyuml.umldraw.shared.NoteElement;
import org.tinyuml.umldraw.structure.ClassElement;
import org.tinyuml.umldraw.structure.ComponentElement;
import org.tinyuml.umldraw.structure.PackageElement;

/**
 * Status bar label (RF-001) that shows the number of elements of the diagram,
 * in total and by category. Connections are not elements and are not counted.
 */
public class ElementCountLabel extends JLabel {

  private static final long serialVersionUID = 1L;
  private int packages, classes, components, notes;

  /**
   * Constructor. Starts showing zero in every category.
   */
  public ElementCountLabel() {
    super("", JLabel.CENTER);
    setText(buildText());
  }

  /**
   * Recounts the elements of the specified diagram and updates the text.
   * @param diagram the diagram of the active editor
   */
  public void refresh(AbstractCompositeNode diagram) {
    packages = 0;
    classes = 0;
    components = 0;
    notes = 0;
    countChildren(diagram);
    setText(buildText());
  }

  /**
   * Visits the children recursively, because packages can contain elements.
   * Connections are skipped: they match none of the four categories.
   * @param parent the node whose children are counted
   */
  private void countChildren(AbstractCompositeNode parent) {
    for (DiagramElement child : parent.getChildren()) {
      if (child instanceof PackageElement) {
        packages++;
      } else if (child instanceof ClassElement) {
        classes++;
      } else if (child instanceof ComponentElement) {
        components++;
      } else if (child instanceof NoteElement) {
        notes++;
      }
      if (child instanceof AbstractCompositeNode) {
        countChildren((AbstractCompositeNode) child);
      }
    }
  }

  /**
   * Builds the text shown in the status bar.
   * @return the text, e.g. "Total Items: 06 | Package: 02 | ..."
   */
  private String buildText() {
    int total = packages + classes + components + notes;
    return String.format("Total Items: %02d | Package: %02d | Class: %02d"
      + " | Component: %02d | Note: %02d",
      total, packages, classes, components, notes);
  }
}