/*
 * ============================================================================
 *  Copyright © 2002-2022 by Thomas Thrien.
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

import java.util.Map;
import java.util.Map.Entry;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary The implementation of a tupel.}</p>
 *  <p>Both values are allowed to be {@code null}.</p>
 *
 *  @param  <L> The type for the left value.
 *  @param  <R> The type for the right value.
 *  @param  left    The left value of the pair.
 *  @param  right   The right value of the pair.
 *
 *  @version $Id: HexUtils.java 747 2020-12-01 12:40:38Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@ClassVersion( sourceVersion = "$Id: HexUtils.java 747 2020-12-01 12:40:38Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public record Pair<L,R>( L left, R right )
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  <p>{@summary Returns this instance of {@code Pair} as an instance of
     *  {@link Entry}.}
     *  The left value is the
     *  {@link Entry#getKey() key},
     *  the right value is the
     *  {@link Entry#getValue() value}.</p>
     *  <p>The method
     *  {@link Entry#setValue(Object)}
     *  is not implemented and will throw an exception when called.</p>
     *
     *  @return A reference to this instance as an {@code Entry}.
     */
    public final Map.Entry<L,R> asEntry()
    {
        final var retValue = new Entry<L,R>()
        {
            /**
             *  {@inheritDoc}
             */
            @Override
            public final L getKey() { return left(); }

            /**
             *  {@inheritDoc}
             */
            @Override
            public final R getValue() { return right(); }

            /**
             *  {@inheritDoc}
             */
            @Override
            public final R setValue( final R value ) { throw new UnsupportedOperationException( "setValue()" ); }
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  asEntry()

    /**
     *  Creates a new instance of {@code Pair} with the right value from this
     *  one and the given new left value.
     *
     *  @param  newLeft The new left value; can be {@code null}.
     *  @return A new instance of {@code Pair}.
     */
    public final Pair<L,R> left( final L newLeft ) { return new Pair<>( newLeft, right ); }

    /**
     *  Creates a new instance of {@code Pair} with the left value from this
     *  one and the given new right value.
     *
     *  @param  newRight The new right value; can be {@code null}.
     *  @return A new instance of {@code Pair}.
     */
    public final Pair<L,R> right( final R newRight ) { return new Pair<>( left, newRight ); }
}
//  record Pair

/*
 *  End of File
 */