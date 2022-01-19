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

package org.tquadrat.foundation.lang;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.function.Supplier;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.internal.SoftLazyImpl;

/**
 *  Instances of the class {@code SoftLazy} allow to lazy load data that may be
 *  needed more than once, but not permanently. An example might be data that
 *  is needed during the startup phase of a program, but never again later.<br>
 *  <br>The initializer method that is provided to
 *  {@link #use(Supplier)}
 *  must return the same result for each invocation, and this may not be
 *  {@code null}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: SoftLazy.java 993 2022-01-19 22:26:20Z tquadrat $
 *  @since 0.0.5
 *
 *  @param  <T> The type of the cached data.
 */
@SuppressWarnings( "InterfaceMayBeAnnotatedFunctional" )
@ClassVersion( sourceVersion = "$Id: SoftLazy.java 993 2022-01-19 22:26:20Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public sealed interface SoftLazy<T>
    permits org.tquadrat.foundation.lang.internal.SoftLazyImpl
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns the value from this instance of {@code SoftLazy}.
     *
     *  @return The value.
     */
    public T get();

    /**
     *  Creates an instance of {@code SoftLazy}.
     *
     *  @param  <D> The type of the cached data.
     *  @param  initializer The initializer method.
     *  @return The new instance of  {@code SoftLazy}.
     */
    @SuppressWarnings( "ClassReferencesSubclass" )
    public static <D> SoftLazy<D> use( final Supplier<D> initializer )
    {
        final var retValue = new SoftLazyImpl<>( initializer );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  use()
}
//  class SoftLazy

/*
 *  End of File
 */