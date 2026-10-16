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

import static java.lang.String.format;
import static org.apiguardian.api.API.Status.MAINTAINED;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.Objects.nonNull;

import java.io.Serial;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary This is the common base class for specialised implementations
 *  of
 *  {@link IllegalArgumentException}.}
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: NullArgumentException.java 1258 2026-06-04 18:33:06Z tquadrat $
 *  @since 0.25.13
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: NullArgumentException.java 1258 2026-06-04 18:33:06Z tquadrat $" )
@API( status = MAINTAINED, since = "0.25.13" )
abstract sealed class InvalidArgumentException extends ValidationException
    permits BlankArgumentException, EmptyArgumentException, NullArgumentException
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
    private static final long serialVersionUID = 1174360235354917591L;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  <p>{@summary {@constructor}}</p>
    *
     *  @param  argName The name of the argument whose value was provided as
     *      {@null}; if {@null} or the empty String, a
     *      default message is used that does not use the name of the argument.
     *  @param  msgName The regular message.
     *  @param  msgNone The default message.
     */
    protected InvalidArgumentException( final String argName, final String msgName, final String msgNone )
    {
        super( nonNull( argName ) && !argName.isEmpty() ? format( msgName, argName ) : msgNone );
    }   //  InvalidArgumentException()
}
//  class InvalidArgumentException

/*
 *  End of File
 */