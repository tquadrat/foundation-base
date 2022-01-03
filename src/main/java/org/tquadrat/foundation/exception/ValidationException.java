/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
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
import static org.tquadrat.foundation.lang.Objects.isNull;

import java.io.Serial;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  This is a specialized implementation for the
 *  {@link IllegalArgumentException}
 *  that is meant as the root for a hierarchy of exceptions caused by
 *  validation errors.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ValidationException.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: ValidationException.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public class ValidationException extends IllegalArgumentException
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The message text key for a validation error.
     */
    public static final String MSG_ValidationFailed = "Validation failed";

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     *
     * @hidden
     */
    @Serial
    private static final long serialVersionUID = 1174360235354917591L;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code ValidationException} instance.
     */
    public ValidationException()
    {
        super( MSG_ValidationFailed );
    }   //  ValidationException()

    /**
     *  Creates a new {@code ValidationException} instance.
     *
     *  @param  message The message that provides details on the failed
     *      validation.
     */
    public ValidationException( final String message )
    {
        super( isNull( message ) && message.isEmpty() ? MSG_ValidationFailed : message );
    }   //  ValidationException()

    /**
     *  Creates a new {@code ValidationException} instance.
     *
     *  @param  message The message that provides details on the failed
     *      validation.
     *  @param  cause   The exception that is related to the validation error.
     */
    public ValidationException( final String message, final Throwable cause )
    {
        super( isNull( message ) && message.isEmpty() ? MSG_ValidationFailed : message, cause );
    }   //  ValidationException()

    /**
     *  Creates a new {@code ValidationException} instance.
     *
     *  @param  cause   The exception that is related to the validation error.
     */
    public ValidationException( final Throwable cause )
    {
        super( MSG_ValidationFailed, cause );
    }   //  ValidationException()
}
//  class ValidationException

/*
 *  End of File
 */