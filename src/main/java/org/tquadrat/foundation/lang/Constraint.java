/*
 * ============================================================================
 *  Copyright © 2002-2021 by Thomas Thrien.
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
 *  <p>{@summary A functional interface whose
 *  {@link #evaluate()}
 *  method return a {@code boolean} and can throw an
 *  {@link Exception}
 *  in case of an error}.</p>
 *
 *  @version $Id: Constraint.java 1097 2024-02-06 20:10:12Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.4.1
 */
@SuppressWarnings( "ProhibitedExceptionDeclared" )
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: Constraint.java 1097 2024-02-06 20:10:12Z tquadrat $" )
@API( status = STABLE, since = "0.4.1" )
public interface Constraint
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  The operation.
     *
     *  @return The operation's result.
     *
     * @throws Exception Something went wrong.
     */
    @SuppressWarnings( "BooleanMethodNameMustStartWithQuestion" )
    public boolean evaluate() throws Exception;
}
//  interface Constraint

/*
 *  End of File
 */