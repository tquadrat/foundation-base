/*
 * ============================================================================
 * Copyright © 2002-2024 by Thomas Thrien.
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

package org.tquadrat.foundation.exception;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

import java.io.Serial;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;

/**
 *  Use the application error to signal the abort of an application.<br>
 *  <br>This implementation of
 *  {@link Error}
 *  allows to set a flag that indicates whether this instance was already
 *  logged or not. The flag is honoured by some methods in Foundation Logging.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ApplicationError.java 1119 2024-03-16 09:03:57Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "ClassWithTooManyConstructors" )
@ClassVersion( sourceVersion = "$Id: ApplicationError.java 1119 2024-03-16 09:03:57Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public class ApplicationError extends Error
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  A flag that indicates if the causing exception or the error condition
     *  was already logged.
     *
     *  @serial
     */
    private boolean m_IsAlreadyLogged = false;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     *
     *  @hidden
     */
    @Serial
    private static final long serialVersionUID = 1L;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code ApplicationError} instance. The 'isLogged' flag
     *  is set to {@code false}.
     *
     *  @param  message The detail message. It is saved for later retrieval by
     *      the
     *      {@link #getMessage()}
     *      method.
     *
     *  @see Error#Error(String)
     */
    public ApplicationError( final String message ) { this( message, false ); }

    /**
     *  Creates a new {@code ApplicationError} instance.
     *
     *  @param  message The detail message. It is saved for later retrieval by
     *      the
     *      {@link #getMessage()}
     *      method.
     *  @param  isLogged    {@code true} if the error condition or the
     *      causing exception was already logged, {@code false} if it
     *      still has to be logged.
     */
    public ApplicationError( final String message, final boolean isLogged )
    {
        super( requireNotEmptyArgument( message, "message" ) );

        m_IsAlreadyLogged = isLogged;
    }   //  ApplicationError()

    /**
     *  Creates a new {@code ApplicationError} instance. The '{@code isLogged}'
     *  flag is set to {@code false}.
     *
     *  @param  cause   The cause (which is saved for later retrieval by the
     *      {@link #getCause()}
     *      method). Different from
     *      {@link Error#Error(Throwable)}
     *      is {@code null} not a valid value.
     *
     *  @see Error#Error(Throwable)
     */
    public ApplicationError( final Throwable cause ) { this( cause, false ); }

    /**
     *  Creates a new {@code ApplicationError} instance.
     *
     *  @param  cause   The cause (which is saved for later retrieval by the
     *      {@link #getCause()}
     *      method). Different from
     *      {@link Error#Error(Throwable)}
     *      is {@code null} not a valid value.
     *  @param  isLogged    {@code true} if the error condition or the
     *      causing exception was already logged, {@code false} if it
     *      still has to be logged.
     */
    public ApplicationError( final Throwable cause, final boolean isLogged )
    {
        super( requireNonNullArgument( cause, "cause" ) );

        m_IsAlreadyLogged = isLogged;
    }   //  ApplicationError()

    /**
     *  Creates a new {@code ApplicationError} instance. The '{@code isLogged}'
     *  flag is set to {@code false}.
     *
     *  @param  message The detail message. It is saved for later retrieval by
     *      the
     *      {@link #getMessage()}
     *      method.
     *  @param  cause   The cause (which is saved for later retrieval by the
     *      {@link #getCause()}
     *      method). Different from
     *      {@link Error#Error(Throwable)}
     *      is {@code null} not a valid value.
     *
     *  @see Error#Error(String, Throwable)
     */
    public ApplicationError( final String message, final Throwable cause ) { this( message, cause, false ); }

    /**
     *  Creates a new {@code ApplicationError} instance.
     *
     *  @param  message The detail message. It is saved for later retrieval by
     *      the
     *      {@link #getMessage()}
     *      method.
     *  @param  cause   The cause (which is saved for later retrieval by the
     *      {@link #getCause()}
     *      method). Different from
     *      {@link Error#Error(Throwable)}
     *      is {@code null} not a valid value.
     *  @param  isLogged    {@code true} if the error condition or the
     *      causing exception was already logged, {@code false} if it
     *      still has to be logged.
     */
    public ApplicationError( final String message, final Throwable cause, final boolean isLogged )
    {
        super( requireNotEmptyArgument( message, "message" ), requireNonNullArgument( cause, "cause" ) );

        m_IsAlreadyLogged = isLogged;
    }   //  ApplicationError()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns the 'is logged' flag.
     *
     *  @return {@code true} if the error condition or the causing
     *      exception were already logged, {@code false} otherwise.
     */
    @SuppressWarnings("PublicMethodNotExposedInInterface")
    public final boolean isLogged() { return m_IsAlreadyLogged; }

    /**
     *  Sets the 'is logged' flag to {@code true}.
     */
    @SuppressWarnings("PublicMethodNotExposedInInterface")
    public final void setLogged() { m_IsAlreadyLogged = true; }
}
//  class ApplicationError

/*
 *  End of File
 */