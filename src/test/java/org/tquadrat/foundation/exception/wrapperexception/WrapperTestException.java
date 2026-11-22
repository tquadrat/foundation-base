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

package org.tquadrat.foundation.exception.wrapperexception;

import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.WrapperException;

/**
 *  <p>{@summary An implementation of
 *  {@link org.tquadrat.foundation.exception.WrapperException}
 *  for testing purposes.}</p>
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: WrapperTestException.java 1301 2026-11-22 10:56:12Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: WrapperTestException.java 1301 2026-11-22 10:56:12Z tquadrat $" )
public class WrapperTestException extends WrapperException
{
        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     * <p>{@summary Constructs a new instance of this
     * {@code WrapperTestException} with the specified detail message and
     * cause.}</p>
     *
     * @param message The detail message that is saved for later
     *     retrieval by the
     *     {@link #getMessage()}
     *     method.
     * @param cause The cause that is saved for later retrieval by the
     *     {@link #getCause()}
     *     method.
     */
    public WrapperTestException( final String message, final Throwable cause ) { super( message, cause, false ); }   //  WrapperTestException()

    /**
     * <p>{@summary Constructs a new instance of this
     * {@code WrapperTestException} that wraps the specified cause.}</p>
     *
     * @param cause The cause that is saved for later retrieval by the
     *     {@link #getCause()}
     *     method.
     */
    public WrapperTestException( final Throwable cause ) { super( null, cause, false ); }

    /**
     * <p>{@summary Constructs a new instance of {@class} with the specified
     * detail message, cause, and whether the stack trace should be
     * retrieved.}</p>
     * <p>If {@code retrieveStacktrace} is {@false}, the instance is a pure
     * wrapper or container for other exceptions; consequently {@code cause}
     * cannot be {@null} if {@code retrieveStacktrace} is {@false}. In that
     * case, if {@code cause} is an instance of {@class}, it will be replaced
     * by
     * {@linkplain Throwable#getCause() its cause}
     * if that is not {@null}. In that case, the additional information is
     * merged, too.</p>
     * <p>If {@code message} is {@null} and {@code cause} is an instance of
     * {@class}, the internal message of {@code cause} is used instead.</p>
     * <p>If {@code message} is {@null} and {@code cause} is another exception
     * type, the result of
     * {@link Throwable#toString() cause::toString}
     * is used as the detailed message for the new instance.</p>
     *
     * @param message The detail message that is saved for later
     *     retrieval by the
     *     {@link #getMessage()}
     *     method. It can be {@null}.
     * @param cause The cause that is saved for later retrieval by the
     *     {@link #getCause()}
     *     method.
     * @param retrieveStacktrace {@true} if the stack trace will be
     *     retrieved and is writable, {@false} if the instance should behave
     *     like a <i>regular</i> exception.
     */
    protected WrapperTestException( final String message, final Throwable cause, final boolean retrieveStacktrace )
    {
        super( message, cause, retrieveStacktrace );
    }   //  WrapperTestException()
}
//  class WrapperTestException

/*
 *  End of File
 */