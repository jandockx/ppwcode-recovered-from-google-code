package be.peopleware.jsf_II.persistence;


import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.peopleware.i18n_I.Properties;
import be.peopleware.i18n_I.ResourceBundleLoadStrategy;
import be.peopleware.jsf_II.FatalFacesException;
import be.peopleware.jsf_II.RobustCurrent;
import be.peopleware.jsf_II.i18n.BasenameResourceBundleMap;
import be.peopleware.jsf_II.i18n.I18nPropertyLabelMap;
import be.peopleware.persistence_II.PersistentBean;
import be.peopleware.persistence_II.dao.Dao;
import be.peopleware.servlet.navigation.NavigationInstance;


/**
 * Common functionality of JavaServer Faces backing beans that process requests
 * for {@link PersistentBean PersistentBeans}. This provides code for an
 * {@link Dao} and a {@link CrudSecurityStrategy}.
 * These are stateful instances, that should be stored in request scope.
 *
 * @note There is no reason for now to make this an interface.
 *
 * @author    Jan Dockx
 * @author    Peopleware n.v.
 *
 * @invar (getPersistentBeanType() != null)
 *            ? PersistentBean.class.isAssignableFrom(getPersistentBeanType())
 *            : true;
 * @idea to save memory, cache and share labels for the same type and
 *       locale on a higher level
 */
public abstract class PersistentBeanHandler extends AsyncCrudDaoHandler implements NavigationInstance {

  /*<section name="Meta Information">*/
  //------------------------------------------------------------------
  /** {@value} */
  public static final String CVS_REVISION = "$Revision$";
  /** {@value} */
  public static final String CVS_DATE = "$Date$";
  /** {@value} */
  public static final String CVS_STATE = "$State$";
  /** {@value} */
  public static final String CVS_TAG = "$Name$";
  /*</section>*/



  private static final Log LOG = LogFactory.getLog(PersistentBeanHandler.class);



  /*<property name="viewMode">*/
  //------------------------------------------------------------------

  /** {@value} */
  public final static String VIEWMODE_DISPLAY = "display";
  /** {@value} */
  public final static String VIEWMODE_EDIT = "edit";

  /**
   * { {@link #VIEWMODE_DISPLAY}, {@link #VIEWMODE_EDIT} };
   */
  public final static String[] VIEWMODES
      = {VIEWMODE_DISPLAY, VIEWMODE_EDIT};

  /**
   * Does <code>viewMode</code> represent a valid view mode?
   *
   * @param   viewMode
   *          The viewMode to be checked.
   * @return  Arrays.asList(VIEWMODES).contains(s);
   */
  public boolean isViewMode(String viewMode) {
    return Arrays.asList(VIEWMODES).contains(viewMode);
  }

  /**
   * The view mode of the handler.
   *
   * @basic
   * @init VIEWMODE_DISPLAY
   */
  public final String getViewMode() {
    return $viewMode;
  }

  /**
   * Set the view mode to the given string.
   *
   * @param   viewMode
   *          The view mode to set.
   * @post    (viewMode == null)
   *             ? new.getViewMode().equals(VIEWMODE_DISPLAY)
   *             : new.getViewMode().equals(viewMode);
   * @throws  IllegalArgumentException
   *          ! isViewMode(viewMode);
   */
  public final void setViewMode(String viewMode) throws IllegalArgumentException {
    if (! isViewMode(viewMode)) {
      throw new IllegalArgumentException("\"" + viewMode + "\" is not a valid view mode; " +
                                         "it must be one of " + VIEWMODES);
    }
    // set the view mode
    $viewMode = viewMode;
  }

  /**
   * @invar ($viewMode != null)
   *            ? isViewMode($viewMode)
   *            : true;
   */
  private String $viewMode = VIEWMODE_DISPLAY;

  /**
   * Returns true when the handler is in a state where we want the page
   * to show data as fields, i.e., the data is editable. This is implemented
   * in this package based on {@link #getViewMode()}, but might be extended
   * in specific subclasses.
   *
   * This method is introduces to avoid writing
   * <code>myHandler.viewmode eq 'edit' or myHandler.viewmode eq 'editNew'</code>
   * and
   * <code>myHandler.viewmode neq 'edit' and myHandler.viewmode neq 'editNew'</code>
   * as value of the rendered attributes of in- and output fields in JSF pages,
   * which is cumbersome.
   * Now we can write
   * <code>myHandler.showFields</code>
   * and
   * <code>not myHandler.showFields</code>.
   *
   * @result  false ? (! getViewMode().equals(PersistentBeanHandler.VIEWMODE_EDIT));
   * @throws  FatalFacesException
   *          getViewMode() == null;
   */
  public boolean isShowFields() throws FatalFacesException {
    if (getViewMode() == null) {
      RobustCurrent.fatalProblem("ViewMode is null", LOG);
    }
    return getViewMode().equals(VIEWMODE_EDIT);
  }

  /*</property>*/



  /*<property name="securityStrategy">*/
  //------------------------------------------------------------------

  /**
   * @basic
   * @init null;
   */
  public final CrudSecurityStrategy getSecurityStrategy() {
    return $securityStrategy;
  }

  /**
   * @post new.getSecurityStragegy() == securityStrategy;
   */
  public final void setSecurityStrategy(final CrudSecurityStrategy securityStrategy) {
    LOG.debug("CrudSecurityStrategy set: "
              + ((securityStrategy == null) ? "null" : securityStrategy.toString()));
    $securityStrategy = securityStrategy;
  }

  private CrudSecurityStrategy $securityStrategy;

  /*</property>*/



  /*<property name="editable">*/
  //------------------------------------------------------------------

  /**
   * Can we edit the object represented by this form?
   *
   * @basic
   * @init      false;
   */
  public final boolean isEditable() {
    return $editable;
  }

  /**
   * @param     editable
   *            Marks the persistent bean as a object that can be edited.
   * @post      new.isEditable() == editable;
   */
  protected final void setEditable(final boolean editable) {
    $editable = editable;
  }

  private boolean $editable;

  /*</property>*/



  /*<property name="deleteable">*/
  //------------------------------------------------------------------

  /**
   * Can we delete the object represented by this form?.
   *
   * @basic
   * @init      false;
   */
  public final boolean isDeleteable() {
    return $deleteable;
  }

  /**
   * @param     deleteable
   *            Marks the persistent bean as a object that can be deleted.
   * @post      new.isDeleteable() == deleteable;
   */
  protected final void setDeleteable(final boolean deleteable) {
    $deleteable = deleteable;
  }

  private boolean $deleteable;

  /*</property>*/



  /*<property name="type">*/
  //------------------------------------------------------------------

  /**
   * The type of the {@link PersistentBean} that will be handled
   * by the requests. The type is never {@link #skim() skimmed}.
   *
   * @basic
   * @init null;
   */
  public final Class getPersistentBeanType() {
    return $persistentBeanType;
  }

  /**
   * Set the type of the {@link PersistentBean} that will be handled
   * by the requests.
   *
   * @param  type
   *         The type to be set.
   * @pre (type != null) ? PersistentBean.class.isAssignableFrom(type);
   * @post   getPersistentBeanType() == type;
   *
   * @todo (jand) doc: and set the label maps
   */
  public final void setPersistentBeanType(Class type) {
    // pre and not exception, because this is a programmatic error
    assert ((type != null) ? PersistentBean.class.isAssignableFrom(type) : true)
            : "Type must be a subtype of " + PersistentBean.class +
              " (and " + type + " isn't).";
    $persistentBeanType = type;
    LOG.debug("type of " + this + " set to Class " + type.getName());
    LOG.debug("loading label properties");
    $labels = new I18nPropertyLabelMap(type, false);
    $shortLabels = new I18nPropertyLabelMap(type, true);
    LOG.debug("label properties loaded");
  }

  /**
   * Set the type of the {@link PersistentBean} that will be handled
   * by the requests, as text.
   *
   * @todo This method is here for the faces-config managed bean stuff. Apparently,
   *       converters are not used during creation and property setting of managed
   *       beans. So, with the {@link #setPersistentBeanType(Class)} method, we would be trying
   *       to push a String into a Class type parameter (actually, it seems, but
   *       we are not sure, that JSF attempts to <em>instantiate</em> the class
   *       for which the name is given, which is pretty weird). When this gets
   *       solved, or when we no longer need this method here, this method should
   *       be removed.
   *
   * @param   typeName
   *          The fully qualified name of the type to be set.
   * @post    getPersistentBeanType() == Class.forName(typeName);
   * @throws  FacesException
   *          {@link Class#forName(String)}
   */
  public void setTypeAsString(String typeName) throws FacesException {
    Class type;
    try {
      type = Class.forName(typeName);
    }
    catch (LinkageError lErr) {
      throw new FacesException("cannot convert String to Class", lErr);
    }
    catch (ClassNotFoundException cnfExc) {
      throw new FacesException("cannot convert String to Class", cnfExc);
    }
    setPersistentBeanType(type);
  }

  /**
   * The type of the {@link PersistentBean} that will be handled
   * by the requests.
   */
  private Class $persistentBeanType;

  /*</property>*/



  /*<property name="labels">*/
  //------------------------------------------------------------------

  /**
   * The i18n short labels for the properties of {@link #getPersistentBeanType()}
   * according to the rules in
   * {@link Properties#i18nPropertyLabel(java.lang.String, java.lang.Class, boolean, ResourceBundleLoadStrategy)},
   * as a map. This property makes it easy to use this i18n strategy in JSF
   * fashion, without the need for a <code>f:loadBundle</code>
   * tag in every page.
   */
  public Map getLabels() {
    return $labels;
  }

  /**
   * @invar (getPersistentBeanType() != null) ==> ($labels != null);
   * @invar (getPersistentBeanType() != null)
   *           ==> ($labels.getType().equals(getPersistentBeanType()));
   */
  private I18nPropertyLabelMap $labels;

  /*</property>*/



  /*<property name="shortLabels">*/
  //------------------------------------------------------------------

  /**
   * The i18n short labels for the properties of {@link #getPersistentBeanType()}
   * according to the rules in
   * {@link Properties#i18nPropertyLabel(java.lang.String, java.lang.Class, boolean, ResourceBundleLoadStrategy)},
   * as a map. This property makes it easy to use this i18n strategy in JSF
   * fashion, without the need for a <code>f:loadBundle</code>
   * tag in every page.
   */
  public Map getShortLabels() {
    return $shortLabels;
  }


  /**
   * @invar (getPersistentBeanType() != null) ==> ($shortLabels != null);
   * @invar (getPersistentBeanType() != null)
   *          ==> ($shortLabels.getType().equals(getPersistentBeanType()));
   */
  private I18nPropertyLabelMap $shortLabels;

  /*</property>*/


  /*<property name="typeLabel">*/
  //------------------------------------------------------------------

  /**
   * Return the type label of this PeristentBean.
   *
   * @basic
   */
  public String getTypeLabel() {
    return Properties.i18nTypeLabel(getPersistentBeanType(),
                                    false,
                                    RobustCurrent.JSF_RESOURCE_BUNDLE_LOAD_STRATEGY);
  }

  /*</property>*/


  /*<property name="pluralTypeLabel">*/
  //------------------------------------------------------------------

  /**
   * Return the plural type label of this PeristentBean.
   *
   * @basic
   */
  public String getPluralTypeLabel() {
    return Properties.i18nTypeLabel(getPersistentBeanType(),
                                    true,
                                    RobustCurrent.JSF_RESOURCE_BUNDLE_LOAD_STRATEGY);
  }

  /*</property>*/

  /*<property name="buttonLabels">*/
  //------------------------------------------------------------------

  /**
   * @todo (jand) doc
   */
  public Map getButtonLabels() {
    if ($buttonLabels == null) {
      $buttonLabels = new BasenameResourceBundleMap(BUTTON_LABEL_RESOURCE_BUNDLE_BASENAME);
    }
    return $buttonLabels;
  }

  /**
   * <strong>= {@value}</strong>
   */
  private final static String BUTTON_LABEL_RESOURCE_BUNDLE_SIMPLE_BASENAME =
    "ButtonLabels";

  /**
   * <strong>= {@value}</strong>
   */
  private final static String DOT = ".";

  /**
   * <strong>= PersistentBeanHandler.class.getPackage().getName() +
   *   DOT + BUTTON_LABEL_RESOURCE_BUNDLE_SIMPLE_BASENAME;</strong>
   */
  private final static String BUTTON_LABEL_RESOURCE_BUNDLE_BASENAME =
    PersistentBeanHandler.class.getPackage().getName() +
    DOT + BUTTON_LABEL_RESOURCE_BUNDLE_SIMPLE_BASENAME;

  /**
   */
  private Map $buttonLabels;

  /*</property>*/


  /*<property name="navigationString">*/
  //------------------------------------------------------------------

  /**
   * The navigation string used in faces-config.xml to come to this page.
   *
   * @basic
   * @init null;
   */
  public final String getNavigationString() {
    return $navigationString;
  }

  /**
   * Set the navigation string to the given string.
   *
   * @param   navigationString
   *          The navigation string to set.
   * @post    (navigationString == null)
   *            ? new.getNavigationString() == null;
   *            : new.getNavigationString().equals(navigationString);
   */
  public void setNavigationString(String navigationString) {
    $navigationString = navigationString;
  }

  /**
   * The navigation string used in faces-config.xml to come to this page.
   */
  private String $navigationString;

  /*</property>*/



  /*<section name="viewId's">*/
  //------------------------------------------------------------------

  public static final String VIEW_ID_PREFIX = "/jsf/";
  public static final String VIEW_ID_SUFFIX = ".jspx";

  /*</section>*/


  /**
   * Create a new instance of type {@link #getPersistentBeanType()} with the default
   * constructor. {@link #createInstance()} is called on the fresh
   * bean to do whatever configuration necessary.
   *
   * @post new.getInstance() isfresh
   * @post new.getInstance() == getPersistentBeanType().newInstance();
   */
  protected PersistentBean createInstance() throws FatalFacesException {
    LOG.debug("creating new instance of type \"" + getPersistentBeanType() + "\"");
    PersistentBean result = null;
    try {
      result = (PersistentBean)getPersistentBeanType().newInstance();
      LOG.debug("fresh instance: " + result);
      LOG.debug("Calling postCreateInstance");
      postCreateInstance(result);
      LOG.debug("fresh instance after postCreateInstance: " + result);
    }
    // all exceptions are programmatic errors here, in subclass, in config or in JSF
    catch (InstantiationException iExc) {
      RobustCurrent.fatalProblem("could not create fresh instance of type " + getPersistentBeanType(), iExc, LOG);
    }
    catch (IllegalAccessException iaExc) {
      RobustCurrent.fatalProblem("could not create fresh instance of type " + getPersistentBeanType(), iaExc, LOG);
    }
    catch (ExceptionInInitializerError eiiErr) {
      RobustCurrent.fatalProblem("could not create fresh instance of type " + getPersistentBeanType(), eiiErr, LOG);
    }
    catch (SecurityException sExc) {
      RobustCurrent.fatalProblem("could not create fresh instance of type " + getPersistentBeanType(), sExc, LOG);
    }
    catch (ClassCastException ccExc) {
      RobustCurrent.fatalProblem("could not create fresh instance of type " + getPersistentBeanType(), ccExc, LOG);
    }
    return result;
  }

  /**
   * Method called by {@link #createInstance()},
   * to do additional configuration of fresh beans of type
   * {@link #getPersistentBeanType()}.
   * This default does nothing, but subclasses should overwrite
   * when needed.
   *
   * @pre pb != null;
   * @pre pb.getClass() == getPersistentBeanType()
   */
  protected void postCreateInstance(PersistentBean pb) {
    // NOP
  }


  /**
   * <p>This method should be called to navigate to the page
   *   for this handler in <code>viewMode</code>.</p>
   * <p>This handler is made available to the JSP/JSF page in session scope,
   *   as a variable with name
   *   {@link PersistentBeanHandlerResolver}.{@link PersistentBeanHandlerResolver#handlerVariableNameFor(Class) .PersistentBeanHandlerResolver#handlerVariableNameFor(getType())}.
   *   And we navigate to {@link #getViewId()}.</p>
   * <p>The {@link #getPersistentBeanType() type}, and possibly more, should
   *   be set before this method is called.</p>
   *
   * @post    isViewMode(viewMode) ? new.getViewMode().equals(viewMode)
   *                             : new.getViewMode().equals(VIEWMODE_DISPLAY);
   * @post    RobustCurrent.lookup(RESOLVER.handlerVariableNameFor(getType())) == this;
   * @throws  FatalFacesException
   *          getPersistentBeanType() == null;
   *
   * @mudo (jand) security
   */
  public void navigateHere(String viewMode) throws FatalFacesException {
    LOG.debug("navigate called");
    if (getPersistentBeanType() == null) {
      LOG.fatal("cannot navigate to detail, because no type is set (" +
                this);
    }
    setViewMode(isViewMode(viewMode) ? viewMode : VIEWMODE_DISPLAY);
    // put this handler in request scope, under an agreed name, create new view & navigate
    putInSessionScope();
    FacesContext context = RobustCurrent.facesContext();
    UIViewRoot viewRoot = RobustCurrent.viewHandler().createView(context, getViewId());
    context.setViewRoot(viewRoot);
    context.renderResponse();
  }

  public abstract String getViewId();

  public abstract void putInSessionScope();

  /**
   * <p>Action listener method to navigate to the page
   *   for this handler in {@link #VIEWMODE_DISPLAY},
   *   via a call to {@link #navigateHere(String)}.</p>
   * <p>This handler is made available to the JSP/JSF page in request scope,
   *   as a variable with name
   *   {@link PersistentBeanHandlerResolver}.{@link PersistentBeanHandlerResolver#handlerVariableNameFor(Class) .PersistentBeanHandlerResolver#handlerVariableNameFor(getType())}.
   *   And we navigate to {@link #getViewId()}.</p>
   * <p>The {@link #getPersistentBeanType() type}, and possibly more, should
   *   be set before this method is called.</p>
   *
   * @post    new.getViewMode().equals(VIEWMODE_DISPLAY);
   * @post    RobustCurrent.lookup(RESOLVER.handlerVariableNameFor(getType())) == this;
   * @throws  FatalFacesException
   *          getType() == null;
   * @throws  FatalFacesException
   *          in more circumstances
   */
  public final void navigateHere(ActionEvent aEv) throws FatalFacesException {
    navigateHere(VIEWMODE_DISPLAY);
  }

  /*<section name="skimmable">*/
  //------------------------------------------------------------------

  /* no code here;
   * security issues are not used yet.
   * persistent bean type is not to be skimmed
   * i18n label maps should be cached at a higher level
   */

  /*</section>*/



  /*<section name="navigationInstance">*/
  //------------------------------------------------------------------


  /*<property name="LastRenderedTime">*/
  //------------------------------------------------------------------

  public final Date getLastRenderedTime() {
    return $lastRenderedTime;
  }

  /**
   * @post new.getTime().equals(NOW);
   */
  protected void resetLastRenderedTime() {
    $lastRenderedTime = new Date();
  }

  /**
   * @invar $lastRenderedTime != null;
   */
  private Date $lastRenderedTime = new Date();

  /*</property>*/

  public final void navigateHere() throws FatalFacesException {
    LOG.debug("request to navigate back to this handler instance (" +
              this + ")");
    navigateHere(VIEWMODE_DISPLAY);
  }

  /*</section>*/

}

