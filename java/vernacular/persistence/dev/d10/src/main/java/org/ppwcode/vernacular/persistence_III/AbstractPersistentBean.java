/*<license>
Copyright 2005 - $Date$ by PeopleWare n.v..

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</license>*/

package org.ppwcode.vernacular.persistence_III;

import static org.ppwcode.metainfo_I.License.Type.APACHE_V2;

import java.io.Serializable;

import org.ppwcode.metainfo_I.Copyright;
import org.ppwcode.metainfo_I.License;
import org.ppwcode.metainfo_I.vcs.SvnInfo;
import org.ppwcode.vernacular.semantics_VI.bean.AbstractRousseauBean;


/**
 * A partial implementation of the interface {@link PersistentBean}.
 *
 * @author    Nele Smeets
 * @author    Ruben Vandeginste
 * @author    Jan Dockx
 * @author    PeopleWare n.v.
 */
@Copyright("2004 - $Date$, PeopleWare n.v.")
@License(APACHE_V2)
@SvnInfo(revision = "$Revision$",
         date     = "$Date$")
public abstract class AbstractPersistentBean<_Id_ extends Serializable, _Version_ extends Serializable>
    extends AbstractRousseauBean
    implements PersistentBean<_Id_, _Version_>, Serializable {

  /*<property name="id">*/
  //------------------------------------------------------------------

  public final _Id_ getId() {
    return $id;
  }

  public final boolean hasSameId(final PersistentBean<_Id_, _Version_> other) {
    return (other != null)
    && ((getId() == null)
        ? other.getId() == null
        : getId().equals(other.getId()));
  }

  public final void setId(final _Id_ id) {
    $id = id;
  }

  private _Id_ $id;

  /*</property>*/



  /*<property name="version">*/
  //------------------------------------------------------------------

  public final _Version_ getVersion() {
    return $version;
  }

  public final void setVersion(final _Version_ version) {
    $version = version;
  }

  private _Version_ $version;

  /*</property>*/




}
