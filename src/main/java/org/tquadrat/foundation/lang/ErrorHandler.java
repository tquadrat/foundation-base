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

package org.tquadrat.foundation.lang;

import static org.apiguardian.api.API.Status.STABLE;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  This is a
 *  {@link FunctionalInterface}
 *  meant to process a given error code and to throw an exception based on
 *  that, or to cause some side effects.
 *
 *  @param  <C> The type of the error code.
 *
 *  @extauthor Thomas Thrien-thomas.thrien@tquadrat.org
 *  @version $Id: ErrorHandler.java 993 2022-01-19 22:26:20Z tquadrat $
 *  @since 0.1.0
 *
 *  @see Status
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: ErrorHandler.java 993 2022-01-19 22:26:20Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
@FunctionalInterface
public interface ErrorHandler<C>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Handle the given error and act accordingly.
     *
     *  @param  errorCode The error code.
     *  @return The exception that was determined based on the given error
     *      code.
     */
    public Throwable handleError( final C errorCode );
}
//  interface ErrorHandler

/*
 *  End of File
 */