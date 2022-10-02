/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 *
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

package org.tquadrat.foundation.function;

import static org.apiguardian.api.API.Status.STABLE;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary The definition for a getter method.}</p>
 *  <p>This interface does <i>not</i> extend the otherwise very similar
 *  interface
 *  {@link java.util.function.Supplier}.</p>
 *  <p>This is a
 *  {@linkplain java.lang.FunctionalInterface functional interface}
 *  whose functional method is
 *  {@link #get()}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Getter.java 1031 2022-04-07 22:43:02Z tquadrat $
 *  @since 0.0.5
 *
 *  @param  <T> The value type.
 *
 *  @UMLGraph.link
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: Getter.java 1031 2022-04-07 22:43:02Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface Getter<T>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns a value.
     *
     *  @return The value.
     */
    public T get();
}
//  interface Getter

/*
 *  End of File
 */