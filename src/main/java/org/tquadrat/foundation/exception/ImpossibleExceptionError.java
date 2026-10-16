/*
 * ============================================================================
 * Copyright © 2002-2026 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
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

package org.tquadrat.foundation.exception;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.nonNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.io.Serial;
import java.io.UnsupportedEncodingException;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary This implementation of
 *  {@link Error}
 *  should be thrown in all cases where an exception was caught that seemed to
 *  be impossible to be thrown.} An example for this is the method
 *  {@link String#getBytes(String) String::getBytes(&nbsp;encoding&nbsp;)}
 *  for the <code>encoding&nbsp;==&nbsp;&quot;UTF-8&quot;</code> that should
 *  never throw an
 *  {@link UnsupportedEncodingException UnsupportedEncodingException}
 *  because the encoding {@code UTF-8} is mandatory for all implementations of
 *  Java.</p>
 *  <p>This is different from the
 *  {@link UnexpectedExceptionError}
 *  as that is possible in general, but not in the particular context.</p>
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ImpossibleExceptionError.java 1144 2024-09-08 22:35:47Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: ImpossibleExceptionError.java 1144 2024-09-08 22:35:47Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public final class ImpossibleExceptionError extends UnexpectedExceptionError
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The text for the default message.
     */
    public static final String MSG_ImpossibleException = "The Exception '%1$s' was not expected and should never occur in this context";

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     *
     *  @hidden
     */
    @Serial
    private static final long serialVersionUID = 2233464685783981770L;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  <p>{@summary {@constructor}} The message
     *  is a constant, only the causing exception can be given.</p>
     *
     *  @param  cause   The causing exception.
     */
    public ImpossibleExceptionError( final Throwable cause )
    {
        super( MSG_ImpossibleException.formatted( requireNonNullArgument( cause, "cause" ).getClass().getName() ) , cause );
    }   //  ImpossibleExceptionError()

    /**
     *  <p>{@summary {@constructor}} This
     *  constructor should be used in cases where an enhanced message is useful
     *  or necessary.</p>
     *
     *  @param  message The message for the error.
     *  @param  cause   The causing exception.
     */
    public ImpossibleExceptionError( final String message, final Throwable cause )
    {
        /*
         * StringUtils::isNotEmpty is not available here …
         */
        super( nonNull( message ) && !message.isEmpty() ? message : MSG_ImpossibleException.formatted( requireNonNullArgument( cause, "cause" ).getClass().getName() ), requireNonNullArgument( cause, "cause" ) );
    }   //  ImpossibleExceptionError()
}
//  class ImpossibleExceptionError

/*
 *  End of File
 */