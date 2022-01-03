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
 *  The definition for a setter method.<br>
 *  <br>This is a
 *  {@linkplain java.lang.FunctionalInterface functional interface}
 *  whose functional method is
 *  {@link #set(Object)}.<br>
 *  <br>This interface does <i>not</i> extend the otherwise very similar
 *  interface
 *  {@link java.util.function.Consumer}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Setter.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.0.5
 *
 *  @param  <T> The value type.
 *
 *  @UMLGraph.link
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: Setter.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface Setter<T>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  An empty array of {@code Setter<T>} objects.
     */
    @SuppressWarnings( "rawtypes" )
    public static final Setter [] EMPTY_Setter_ARRAY = new Setter [0];

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