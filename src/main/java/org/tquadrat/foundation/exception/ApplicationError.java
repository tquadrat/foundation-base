/*
 * ============================================================================
 * Copyright © 2002-2026 by Thomas Thrien.
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

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;

import java.io.Serial;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary Use the application error to signal the abort of an
 *  application.}</p>
 *  <p>This implementation of
 *  {@link Error}
 *  allows to set a flag that indicates whether this instance was already
 *  logged or not. The flag is honoured by some methods in Foundation
 *  Logging.</p>
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ApplicationError.java 1293 2026-10-16 13:12:15Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "ClassWithTooManyConstructors" )
@ClassVersion( sourceVersion = "$Id: ApplicationError.java 1293 2026-10-16 13:12:15Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public class ApplicationError extends Error
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  <p>{@summary A flag that indicates if the causing exception or the
     *  error condition was already logged.}</p>
     *
     *  @serial
     */
    @SuppressWarnings( "UnusedAssignment" )
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
     *  <p>{@summary {@constructor}} The 'isLogged' flag is set to
     *  {@false}.</p>
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
     *  <p>{@summary {@constructor}}</p>
     *
     *  @param  message The detail message. It is saved for later retrieval by
     *      the
     *      {@link #getMessage()}
     *      method.
     *  @param  isLogged    {@true} if the error condition or the
     *      causing exception was already logged, {@false} if it
     *      still has to be logged.
     */
    public ApplicationError( final String message, final boolean isLogged )
    {
        super( requireNotEmptyArgument( message, "message" ) );

        m_IsAlreadyLogged = isLogged;
    }   //  ApplicationError()

    /**
     *  <p>{@summary {@constructor}} The 'isLogged' flag is set to
     *  {@false}.</p>
     *
     *  @param  cause   The cause (which is saved for later retrieval by the
     *      {@link #getCause()}
     *      method). Different from
     *      {@link Error#Error(Throwable)}
     *      is {@null} not a valid value.
     *
     *  @see Error#Error(Throwable)
     */
    public ApplicationError( final Throwable cause ) { this( cause, false ); }

    /**
     *  <p>{@summary {@constructor}}</p>
     *
     *  @param  cause   The cause (which is saved for later retrieval by the
     *      {@link #getCause()}
     *      method). Different from
     *      {@link Error#Error(Throwable)}
     *      is {@null} not a valid value.
     *  @param  isLogged    {@true} if the error condition or the
     *      causing exception was already logged, {@false} if it
     *      still has to be logged.
     */
    public ApplicationError( final Throwable cause, final boolean isLogged )
    {
        super( requireNonNullArgument( cause, "cause" ) );

        m_IsAlreadyLogged = isLogged;
    }   //  ApplicationError()

    /**
     *  <p>{@summary {@constructor}} The 'isLogged' flag is set to
     *  {@false}.</p>
     *
     *  @param  message The detail message. It is saved for later retrieval by
     *      the
     *      {@link #getMessage()}
     *      method.
     *  @param  cause   The cause (which is saved for later retrieval by the
     *      {@link #getCause()}
     *      method). Different from
     *      {@link Error#Error(Throwable)}
     *      is {@null} not a valid value.
     *
     *  @see Error#Error(String, Throwable)
     */
    public ApplicationError( final String message, final Throwable cause ) { this( message, cause, false ); }

    /**
     *  <p>{@summary {@constructor}}</p>
     *
     *  @param  message The detail message. It is saved for later retrieval by
     *      the
     *      {@link #getMessage()}
     *      method.
     *  @param  cause   The cause (which is saved for later retrieval by the
     *      {@link #getCause()}
     *      method). Different from
     *      {@link Error#Error(Throwable)}
     *      is {@null} not a valid value.
     *  @param  isLogged    {@true} if the error condition or the
     *      causing exception was already logged, {@false} if it
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
     *  <p>{@summary Returns the 'is logged' flag.}</p>
     *
     *  @return {@true} if the error condition or the causing
     *      exception were already logged, {@false} otherwise.
     */
    @SuppressWarnings("PublicMethodNotExposedInInterface")
    public final boolean isLogged() { return m_IsAlreadyLogged; }

    /**
     *  <p>{@summary Sets the 'is logged' flag to {@true}.}</p>
     */
    @SuppressWarnings("PublicMethodNotExposedInInterface")
    public final void setLogged() { m_IsAlreadyLogged = true; }
}
//  class ApplicationError

/*
 *  End of File
 */