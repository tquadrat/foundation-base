/*
 * ============================================================================
 *  Copyright © 2002-2020 by Thomas Thrien.
 *  All Rights Reserved.
 * ============================================================================
 *  Licensed to the public under the agreements of the GNU Lesser General Public
 *  License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations
 *  under the License.
 */

package org.tquadrat.foundation.exception;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.io.Serial;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary A generic wrapper for checked exceptions.}</p>
 *  <p>Use this wrapper to emit a checked exception as a
 *  {@link RuntimeException}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: LambdaContainerException.java 1031 2022-04-07 22:43:02Z tquadrat $
 *  @since 0.2.3
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "ExceptionClassNameDoesntEndWithException" )
@ClassVersion( sourceVersion = "$Id: LambdaContainerException.java 1031 2022-04-07 22:43:02Z tquadrat $" )
@API( status = STABLE, since = "0.2.3" )
public sealed class CheckedExceptionWrapper extends RuntimeException
    permits LambdaContainerException
{
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
     *  Creates a new {@code CheckedExceptionWrapper} instance for the given
     *  exception.
     *
     *  @param  e   The exception to wrap; <i>cannot</i> be {@code null}.
     */
    public CheckedExceptionWrapper( final Exception e )
    {
        super( requireNonNullArgument( e, "e" ) );
    }   //  CheckedExceptionWrapper()
}
//  class CheckedExceptionWrapper

/*
 *  End of File
 */