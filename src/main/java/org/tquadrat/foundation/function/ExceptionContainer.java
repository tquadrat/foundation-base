/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 *
 * Licensed to the public under the agreements of the GNU Lesser General Public
 * License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.tquadrat.foundation.function;

import static org.apiguardian.api.API.Status.DEPRECATED;

import java.io.Serial;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.LambdaContainerException;

/**
 *  When a method that emits a checked exception is called inside a lambda
 *  function, this has to be caught inside that function; it is not possible
 *  to declare that exception for the method, as long as the underlying
 *  {@linkplain java.lang.FunctionalInterface functional interface}
 *  have not done that already.<br>
 *  <br>Unfortunately, the methods from the interfaces in the package
 *  {@link java.util.function}
 *  do not declare any exception. So the code below is not possible:
 *  <pre><code>  &hellip;
 *  Appendable appendable = &hellip;
 *  Consumer appender = s -&gt; appendable.append( s );
 *  appender.accept( "&hellip;" );
 *  &hellip;</code></pre>
 *  because
 *  {@link Appendable#append(CharSequence)}
 *  declares to throw an
 *  {@link java.io.IOException}.<br>
 *  <br>This class now is meant to wrap those exceptions and to allow them to
 *  bubble up to the caller:<pre><code>  &hellip;
 *  Appendable appendable = &hellip;
 *  Consumer appender =
 *  {
 *    try
 *    {
 *      s -&gt; appendable.append( s );
 *    }
 *    catch( IOException e )
 *    {
 *      throw new ExceptionContainer( e );
 *    }
 *  }
 *
 *  try
 *  {
 *    appender.accept( "&hellip;" );
 *  }
 *  catch( ExceptionContainer e )
 *  {
 *    throw (IOException) e.getCause();
 *  }
 *  &hellip;</code></pre>
 *  When said above that the methods from the functional interfaces in the
 *  {@code java.util.function} <i>unfortunately</i> do not declare any
 *  exception, this was only focused on their use with code that may emit
 *  checked exceptions. But in fact it is a good thing that the methods in
 *  these interfaces do not declare any exceptions, as this would have polluted
 *  any of the APIs that make use of these functional interfaces. And using the
 *  pattern above would be an alternative. Another would be the methods
 *  provided in the class
 *  {@link Functions}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ExceptionContainer.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 *  @deprecated Use
 *      {@link LambdaContainerException}
 *      instead.
 */
@Deprecated( since = "0.1.0", forRemoval = true )
@ClassVersion( sourceVersion = "$Id: ExceptionContainer.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = DEPRECATED, since = "0.0.5" )
public final class ExceptionContainer extends LambdaContainerException
{
        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     */
    @Serial
    private static final long serialVersionUID = 1L;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code ExceptionContainer} object for the given
     *  exception.
     *
     *  @param  e   The exception to wrap; <i>cannot</i> be {@code null}.
     */
    public ExceptionContainer( final Exception e )
    {
        super( e );
    }   //  ExceptionContainer()
}
//  class ExceptionContainer

/*
 *  End of File
 */