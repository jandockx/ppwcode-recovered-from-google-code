/*<license>
Copyright 2004 - $Date$ by PeopleWare n.v..

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

package org.ppwcode.value_III.location;


import static org.ppwcode.metainfo_I.License.Type.APACHE_V2;

import org.ppwcode.metainfo_I.Copyright;
import org.ppwcode.metainfo_I.License;
import org.ppwcode.metainfo_I.vcs.SvnInfo;
import org.ppwcode.value_III.id11n.Identifier;


/**
 * <p>Postal codes are identifiers of geographic regions in the context of
 *   {@link PostalAddress postal addresses}. Postal codes are organized per
 *   {@link Country}.</p>
 *
 * @author    Jan Dockx
 * @author    PeopleWare NV
 */
@Copyright("2008 - $Date$, PeopleWare n.v.")
@License(APACHE_V2)
@SvnInfo(revision = "$Revision$",
         date     = "$Date$")
public interface PostalCode extends Identifier {

  public final static String EOL = "\n";

  Country getCountry();

  String localizedAddressRepresentation(PostalAddress postalAddress);

}
