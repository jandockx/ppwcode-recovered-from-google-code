package be.peopleware.taglet.contract;


import be.peopleware.taglet.AbstractStandaloneTaglet;


/**
 * Taglet-class for custom taglet <code>@pre</code>
 *
 * @author    Jan Dockx
 * @author    David Van Keer
 * @author    René Clerckx
 * @author    Abdulvakhid Shoudouev
 * @author    Peopleware n.v.
 */
public class PreTaglet extends AbstractContractTaglet {
  /**
   * @see       AbstractTaglet#getName()
   */
  public String getName() {
    return "pre"; //$NON-NLS-1$
  }

  /**
   * @see       AbstractStandaloneTaglet#getHeader()
   */
  public String getHeader() {
    return "Pre Conditions:"; //$NON-NLS-1$
  }

  /**
   * @see       AbstractTaglet#AbstractTaglet()
   */
  public PreTaglet() {
    $inField       = false;
    $inConstructor = true;
    $inMethod      = true;
    $inOverview    = false;
    $inPackage     = false;
    $inType        = false;
  }

}
