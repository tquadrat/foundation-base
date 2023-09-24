/*
 * ============================================================================
 * Copyright © 2002-2023 by Thomas Thrien.
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
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;

import java.io.Serial;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  This is a specialized implementation for the
 *  {@link IllegalArgumentException}
 *  that should be used instead of the latter in cases where a
 *  {@link CharSequence}
 *  provided as a method argument is too long according to some external
 *  constraints, like the column definition of a database.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: CharSequenceTooLongException.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: CharSequenceTooLongException.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public final class CharSequenceTooLongException extends ValidationException
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
     *  Creates a new instance of {@code CharSequenceTooLongException}.
     *
     *  @param  argName The name of the argument whose value was too long.
     *  @param  maxLength   The maximum length.
     */
    public CharSequenceTooLongException( final String argName, final int maxLength )
    {
        super( "The text for argument '%1$s' is too long; the maximum length is %2$d characters".formatted( requireNotEmptyArgument( argName, "argName" ), checkLength( maxLength ) ) );
    }   //  CharSequenceTooLongException()

    /**
     *  Creates a new instance of {@code CharSequenceTooLongException}.
     *
     *  @param  maxLength   The maximum length.
     */
    public CharSequenceTooLongException( final int maxLength )
    {
        super( "The text is too long; the maximum length is %d characters".formatted( checkLength( maxLength ) ) );
    }   //  CharSequenceTooLongException()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Checks the given value.
     *
     *  @param  maxLength   The provided legal length for a text.
     *  @return The given value.
     *  @throws ValidationException The given value was less than 1.
     */
    private static int checkLength( final int maxLength ) throws ValidationException
    {
        if( maxLength <= 0 ) throw new ValidationException( "The given maximum length is 0 or less than 0: %d".formatted( maxLength ) );

        //---* Done *----------------------------------------------------------
        return maxLength;
    }   //  checkLength()
}
//  class CharSequenceTooLongException

/*
 *  End of File
 */