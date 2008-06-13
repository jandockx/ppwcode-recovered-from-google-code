/*<license>
  Copyright 2004, PeopleWare n.v.
  NO RIGHTS ARE GRANTED FOR THE USE OF THIS SOFTWARE, EXCEPT, IN WRITING,
  TO SELECTED PARTIES.
</license>*/

package be.peopleware.bean_V;

import org.ppwcode.bean_VI.PropertyException;


/**
 * <p>ConstraintException are exceptions that carry with them information
 *   about the property for which they occurred. They are usually thrown
 *   by the underlying database checking the constraints. If the property name
 *   is <code>null</code>, it means that the exception could not be attributed
 *   to a specific property of {@link #getOrigin()}.</p>
 * <p>Localized messages are sougth
 *   in a <kbd>*.properties</kbd> file for the class of the origin. The
 *   properties files should be in the directory next to the bean class, with
 *   name <kbd><var>{@link #getOrigin()}<code>.getClass().getName()</code></var>
 *   <var>_locale_identification</var>.properties</kbd>. Alternatively,
 *   messages can be added to a properties file that comes with the
 *   exception.</p>
 * <p>The keys for the localized messages have to have the form
 *   <code><var>this.getClass().getName()</var>.
 *         <var>{@link #getPropertyName()}</var>.
 *         <var>{@link #getMessage()}</var></code>.
 *   See {@link #getLocalizedMessageKeys()}.</p>
 *
 * @invar     getOrigin() != null;
 * @invar     (getPropertyName() == null) || ! getPropertyName().equals("");
 * @invar     (getMessage() == null) || ! getMessage().equals("");
 *
 * @author    Jan Dockx
 * @author    David Van Keer
 * @author    PeopleWare n.v.
 *
 * @todo (dvankeer): We propably need to move this Exception to a better package
 */
public class ConstraintException extends PropertyException {

  /*<section name="Meta Information">*/
  //------------------------------------------------------------------

  /** {@value} */
  public static final String CVS_REVISION = "$Revision:1123 $"; //$NON-NLS-1$
  /** {@value} */
  public static final String CVS_DATE = "$Date:2008-06-12 22:26:03 +0200 (Thu, 12 Jun 2008) $"; //$NON-NLS-1$
  /** {@value} */
  public static final String CVS_STATE = "$State$"; //$NON-NLS-1$
  /** {@value} */
  public static final String CVS_TAG = "$Name$"; //$NON-NLS-1$

  /*</section>*/

  /**
   * @param     origin
   *            The bean that has thrown this exception.
   * @param     propertyName
   *            The name of the property of which the setter has thrown
   *            this exception because parameter validation failed.
   * @param     message
   *            The message that describes the exceptional circumstance.
   * @param     cause
   *            The exception that occurred, causing this exception to be
   *            thrown, if that is the case.
   *
   * @pre       origin != null;
   * @pre       (propertyName != null) ==> ! propertyName.equals("");
   * @pre       (message != null) ==> ! message.equals("");
   * @post      new.getOrigin() == origin;
   * @post      (propertyName != null) ==>
   *                new.getpropertyName().equals(propertyName);
   * @post      (propertyName == null) ==> new.getpropertyName() == null;
   * @post      (message != null) ==> new.getMessage().equals(message);
   * @post      (message == null) ==> new.getMessage() == null;
   * @post      new.getCause() == cause ;
   * @post      new.getElementExceptions().isEmpty();
   * @post      !new.isClosed();
   */
  public ConstraintException(final Object origin,
                             final String propertyName,
                             final String message,
                             final Throwable cause) {
    super(origin, propertyName, message, cause);
  }

  /**
   * @since IV
   */
  public ConstraintException(final Class originType,
                             final String propertyName,
                             final String message,
                             final Throwable cause) {
    super(originType, propertyName, message, cause);
  }

}
