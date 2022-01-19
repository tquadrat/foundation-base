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
 *  <p>{@summary The definition for a setter method.}</p>
 *  <p>This is a
 *  {@linkplain java.lang.FunctionalInterface functional interface}
 *  whose functional method is
 *  {@link #set(Object)}.</p>
 *  <p>This interface does <i>not</i> extend the otherwise very similar
 *  interface
 *  {@link java.util.function.Consumer}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Setter.java 993 2022-01-19 22:26:20Z tquadrat $
 *  @since 0.0.5
 *
 *  @param  <T> The value type.
 *
 *  @UMLGraph.link
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: Setter.java 993 2022-01-19 22:26:20Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface Setter<T>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Takes the given argument and stores it somewhere.
     *
     *  @param  value   The value to store.
     */
    public void set( final T value );
}
//  interface Setter

/*
 *  End of File
 */