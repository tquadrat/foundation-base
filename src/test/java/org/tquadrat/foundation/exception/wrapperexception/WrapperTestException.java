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
 *  @version $Id: WrapperTestException.java 1299 2026-11-21 11:18:41Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: WrapperTestException.java 1299 2026-11-21 11:18:41Z tquadrat $" )
public class WrapperTestException extends WrapperException
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
     * {@code WrapperTestException} with the specified detail message and
     * cause.}</p>
     * <p>Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated in this exception's detail
     * message.
     *
     * @param message The detail message that is saved for later
     *     retrieval by the
     *     {@link #getMessage()}
     *     method.
     * @param cause The cause that is saved for later retrieval by the
     *     {@link #getCause()}
     *     method.
     */
    public WrapperTestException( final String message, final Throwable cause ) { super( message, cause ); }   //  WrapperTestException()

    /**
     * <p>{@summary Constructs a new instance of this
     * {@code WrapperTestException} that wraps the specified cause.}</p>
     *
     * @param cause The cause that is saved for later retrieval by the
     *     {@link #getCause()}
     *     method.
     */
    public WrapperTestException( final Throwable cause ) { super( cause ); }
}
//  class WrapperTestException

/*
 *  End of File
 */