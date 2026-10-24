package org.tinyuml.ui;

import org.tinyuml.draw.Connection;
import org.tinyuml.draw.DiagramElement;
import org.tinyuml.umldraw.shared.NoteElement;
import org.tinyuml.umldraw.structure.ClassElement;
import org.tinyuml.umldraw.structure.ComponentElement;
import org.tinyuml.umldraw.structure.PackageElement;

/**
 * Category of a diagram element, as shown in the status bar (RF-002).
 */
public enum ElementCategory {
  PACKAGE("Package"),
  CLASS("Class"),
  COMPONENT("Component"),
  NOTE("Note"),
  RELATION("Relation");

  private final String displayName;

  /**
   * Constructor.
   * @param aDisplayName the text shown in the status bar
   */
  ElementCategory(String aDisplayName) {
    displayName = aDisplayName;
  }

  /**
   * Returns the text shown in the status bar.
   * @return the display name
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Returns the category of the specified element.
   * @param element the diagram element
   * @return the category, or null if the element is none of them (for
   * example the diagram itself)
   */
  public static ElementCategory of(DiagramElement element) {
    if (element instanceof PackageElement) return PACKAGE;
    if (element instanceof ClassElement) return CLASS;
    if (element instanceof ComponentElement) return COMPONENT;
    if (element instanceof NoteElement) return NOTE;
    if (element instanceof Connection) return RELATION;
    return null;
  }
}