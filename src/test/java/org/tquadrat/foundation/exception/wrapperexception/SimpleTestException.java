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

/**
 *  <p>{@summary Another implementation of
 *  {@link org.tquadrat.foundation.exception.WrapperException}
 *  for testing purposes.}</p>
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: SimpleTestException.java 1301 2026-11-22 10:56:12Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: SimpleTestException.java 1301 2026-11-22 10:56:12Z tquadrat $" )
public class SimpleTestException extends WrapperTestException
{
        /*---------------*\
    ====** Inner Classes **====================================================
        \*---------------*/

        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     * <p>{@summary Constructs a new instance of this
     * {@code SimpleTestException} without a detail message and a cause.}</p>
     */
    public SimpleTestException()
    {
        super( null, null, true );
    }   //  SimpleTestException

    /**
     * <p>{@summary Constructs a new instance of this
     * {@code SimpleTestException} with the specified detail message and
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
    public SimpleTestException( final String message, final Throwable cause )
    {
        super( message, cause, true );
    }   //  SimpleTestException

    /**
     * <p>{@summary Constructs a new instance of this
     * {@code SimpleTestException} that wraps the specified cause.}</p>
     *
     * @param cause The cause that is saved for later retrieval by the
     *     {@link #getCause()}
     *     method.
     */
    public SimpleTestException( final Throwable cause )
    {
        super( null, cause, true );
    }   //  SimpleTestException

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/

}
//  class SimpleTestException

/*
 *  End of File
 */