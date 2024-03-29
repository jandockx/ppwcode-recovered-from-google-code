package org.codehaus.mojo.was6;

import java.io.File;

/**
 * Common superclass for EJB related goals.
 * 
 * @author <a href="mailto:david@codehaus.org">David J. M. Karlsen</a>
 */
public abstract class AbstractEjbMojo
    extends AbstractWas6Mojo
{
    /**
     * Directory to hold generated sources.
     * 
     * @parameter expression="${project.build.directory}/generated-sources/was6-maven-plugin"
     * @required
     */
    private File generatedSourcesDirectory;

    /**
     * Directory to hold generated classes.
     * 
     * @parameter expression="${project.build.directory}/generated-classes/was6-maven-plugin"
     * @required
     */
    private File generatedClassesDirectory;
    
    /**
     * Directory containing generated sources.
     * @return
     */
    protected File getGeneratedSourcesDirectory()
    {
        return generatedSourcesDirectory;
    }

    /**
     * Directory containing generated classes.
     * @return
     */
    protected File getGeneratedClassesDirectory()
    {
        return generatedClassesDirectory;
    }

}
