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

package org.ppwcode.value_III.legacy;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;


/**
 * Converter for {@link VATNumber}. The {@link #getAsObject(FacesContext, UIComponent, String)}
 * method is very lenient: separators can be left out, or any of the characters
 * <code><var>space</var>-/|*:</code>.
 *
 * To activate this converter, the following entry has to appear in <kbd>faces-config.xml</kbd>:
 * <pre>
 * &lt;converter&gt;
 *   &lt;converter-for-class&gt;be.peopleware.value_II.VATNumber&lt;/converter-for-class&gt;
 *   &lt;converter-class&gt;be.peopleware.value_II.VATNumberConverter&lt;/converter-class&gt;
 * &lt;/converter&gt;
 * </pre>
 *
 * @author nsmeets
 * @author Peopleware n.v.
 */
public class VATNumberConverter implements Converter {

  /**
   * Convert the specified string value, which is associated with the
   * specified UIComponent, into a model data object that is appropriate for
   * being stored during the Apply Request Values phase of the request
   * processing lifecycle.
   *
   * @result  value == null || value.length == 0
   *            ==> result == null;
   * @result  result == a new VAT number created using the given string;
   * @throws  ConverterException
   *          The VAT number could not be created from the given string.
   * @todo  formal comment
   */
  public Object getAsObject(final FacesContext context, final UIComponent component,
      final String value) throws ConverterException {
    if ((value == null) || (value.length() == 0)) {
      return null;
    }
    else {
      try {
        return new VATNumber(value);
      }
      catch (Throwable e) {
        throw new ConverterException("VAT number " + value + "could not be created");
      }
    }
  }

  /**
   * The empty string.
   *
   * <strong>= &quot;&quot;</strong>
   */
  public static final String EMPTY = "";

  /**
   * Convert the specified model object value, which is associated with the
   * specified UIComponent, into a String that is suitable for being included
   * in the response generated during the Render Response phase of the request
   * processing lifecycle.
   *
   * @result  value == null
   *            ==> result == EMPTY;
   * @result  value != null && value instanceof VATNumber
   *            ==> result == ((VATNumber)value).toString();
   * @throws  ConverterException
   *          value != null && !(value instanceof VATNumber)
   */
  public String getAsString(final FacesContext context, final UIComponent component,
      final Object value) throws ConverterException {
    if (value == null) {
      return EMPTY;
    }
    if (!(value instanceof VATNumber)) {
      throw new ConverterException("value is not a VATNumber");
    }
    return ((VATNumber)value).toString();
  }
}

