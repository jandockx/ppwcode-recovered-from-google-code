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


package org.ppwcode.vernacular.persistence_III.sql.mysql;


import static org.ppwcode.metainfo_I.License.Type.APACHE_V2;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ppwcode.metainfo_I.Copyright;
import org.ppwcode.metainfo_I.License;
import org.ppwcode.metainfo_I.vcs.SvnInfo;
import org.ppwcode.vernacular.exception_II.ExternalError;
import org.ppwcode.vernacular.persistence_III.sql.SqlExceptionHandler;


/**
 * Growing implementation for handling general {@link SQLException SQLExceptions}
 * generated by MySQL.
 *
 * @author    Jan Dockx
 * @author    PeopleWare n.v.
 *
 * @mudo i18n
 */
@Copyright("2004 - $Date$, PeopleWare n.v.")
@License(APACHE_V2)
@SvnInfo(revision = "$Revision$",
         date     = "$Date$")
public class MySqlExceptionHandler implements SqlExceptionHandler {

  private static final Log LOG = LogFactory.getLog(MySqlExceptionHandler.class);


  /**
   * If the message of the given sql exception contains the string
   *   "Duplicate key or integrity constraint violation,  message from server: \"Duplicate entry"
   * or
   *   "Duplicate entry"
   * return a {@link DuplicateKeyException}.
   *
   * If the message of the given sql exception contains the string
   * "Duplicate key or integrity constraint violation,  "
   *             + "message from server: \"Cannot delete or update a "
   *             + "parent row: a foreign key constraint fails\""
   * return a {@link ConstraintException}.
   *
   * Never return null.
   *
   * @mudo walk through the chain of exceptions to find a match
   */
  public MySqlException handle(final SQLException sqlExc) {
    assert sqlExc != null;
    if (sqlExc.getMessage()
              .indexOf("Duplicate key or integrity constraint violation,  "
                       + "message from server: \"Duplicate entry") >= 0
        || sqlExc.getMessage().indexOf("Duplicate entry") >= 0) {
      // WATCH OUT: SQL Error message contains 'dual space' after ','.
      MySqlException dkExc = new MySqlException("VALUE_NOT_UNIQUE", sqlExc);
      LOG.debug("recognized MySQL duplicate key exception", dkExc);
      return dkExc;
    }
    if (sqlExc.getMessage()
        .indexOf("Duplicate key or integrity constraint violation,  "
                 + "message from server: \"Cannot delete or update a "
                 + "parent row: a foreign key constraint fails\"") >= 0) {
      // WATCH OUT: SQL Error message contains 'dual space' after ','.
      MySqlException cExc = new MySqlException("CONSTRAINT_FAILURE", sqlExc);
      LOG.debug("recognized MySQL constraint violation exception", cExc);
      return cExc;
    }
    throw new ExternalError("MySQL error not recognized as internal", sqlExc);
  }

}
