/*
 * ============================================================================
 * Copyright © 2002-2022 by Thomas Thrien.
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

import java.io.Serial;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  This is a specialized implementation for the
 *  {@link IllegalArgumentException}
 *  that should be used instead of the latter in cases where a blank String is
 *  provided as an illegal argument value.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: BlankArgumentException.java 1025 2022-03-11 16:26:00Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: BlankArgumentException.java 1025 2022-03-11 16:26:00Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class BlankArgumentException extends NullArgumentException
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
     *  Creates a new instance of {@code BlankArgumentException}.
     */
    public BlankArgumentException() { this( null ); }

    /**
     *  Creates a new instance of {@code BlankArgumentException}.
     *
     *  @param  argName The name of the argument whose value was provided as
     *      blank; if {@code null} or the empty String, a default message
     *      is used that does not use the name of the argument.
     */
    public BlankArgumentException( final String argName )
    {
        super( argName, "Argument '%1$s' must not be blank", "Argument must not be blank" );
    }   //  BlankArgumentException()
}
//  class BlankArgumentException

/*
 *  End of File
 */