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
 *  <p>{@summary A variant of the interface
 *  {@link Runnable}
 *  with a
 *  {@link #run()}
 *  method that allows to throw an exception.}</p>
 *  <p>This is a functional interface whose functional method is
 *  {@link #run()}.</p>
 *
 *  @version $Id: Action.java 982 2022-01-08 09:40:32Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@SuppressWarnings( "ProhibitedExceptionDeclared" )
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: Action.java 982 2022-01-08 09:40:32Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public interface Action
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     * The action.
     *
     * @throws Exception Something went wrong.
     */
    public abstract void run() throws Exception;
}
//  interface Action

/*
 *  End of File
 */