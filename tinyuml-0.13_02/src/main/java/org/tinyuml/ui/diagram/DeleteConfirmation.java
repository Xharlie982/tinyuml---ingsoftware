package org.tinyuml.ui.diagram;

import java.awt.Component;
import java.text.MessageFormat;
import javax.swing.JOptionPane;
import org.tinyuml.util.ApplicationResources;

/**
 * Asks the user to confirm the removal of several elements (RF-004).
 */
public final class DeleteConfirmation {

  private DeleteConfirmation() { }

  /**
   * Asks for confirmation, telling how many elements will be removed.
   * Closing the dialog counts as cancelling.
   * @param parent the component the dialog is centered on
   * @param count the number of elements that will be removed
   * @return true if the user accepted, false if the user cancelled
   */
  public static boolean confirm(Component parent, int count) {
    ApplicationResources resources = ApplicationResources.getInstance();
    String message = MessageFormat.format(
      resources.getString("confirm.delete.message"), count);
    return JOptionPane.showConfirmDialog(parent, message,
      resources.getString("confirm.delete.title"),
      JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)
      == JOptionPane.YES_OPTION;
  }
}