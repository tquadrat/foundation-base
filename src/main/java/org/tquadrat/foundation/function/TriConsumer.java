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
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  Represents an operation that accepts three input arguments and returns no
 *  result. This is the three-arity specialisation of
 *  {@link java.util.function.Consumer}.
 *  Unlike most other functional interfaces, {@code TriConsumer} is expected to
 *  operate via side-effects.<br>
 *  <br>This is a
 *  {@linkplain java.lang.FunctionalInterface functional interface}
 *  whose functional method is
 *  {@link #accept(Object,Object,Object)}.
 *
 *  @param  <A> The type of the first argument to the operation.
 *  @param  <B> The type of the second argument to the operation.
 *  @param  <C> The type of the third argument to the operation.
 *
 *  @see java.util.function.Consumer
 *  @see java.util.function.BiConsumer
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TriConsumer.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: TriConsumer.java 820 2020-12-29 20:34:22Z tquadrat $" )
@FunctionalInterface
@API( status = STABLE, since = "0.0.5" )
public interface TriConsumer<A,B,C>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  An empty array of {@code TriConsumer} objects.
     */
    @SuppressWarnings( "rawtypes" )
    public static final TriConsumer [] EMPTY_TriConsumer_ARRAY = new TriConsumer [0];

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Performs this operation on the given arguments.
     *
     *  @param  a   The first input argument.
     *  @param  b   The second input argument.
     *  @param  c   The third input argument.
     */
    public void accept( final A a, final B b, final C c );

    /**
     *  Returns a composed {@code TriConsumer} that performs, in sequence,
     *  this operation followed by the {@code after} operation. If performing
     *  either operation throws an exception, it is relayed to the caller of
     *  the composed operation. If performing this operation throws an
     *  exception, the {@code after} operation will not be performed.
     *
     *  @param  after   The operation to perform after this operation.
     *  @return A composed {@code TriConsumer} that performs in sequence this
     *      operation followed by the {@code after} operation.
     */
    public default TriConsumer<A,B,C> andThen( final TriConsumer<? super A,? super B,? super C> after )
    {
        requireNonNullArgument( after, "after" );

        final TriConsumer<A,B,C> retValue = (a,b,c) ->
        {
            accept(a, b, c);
            after.accept(a, b, c );
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  andThen()
}
//  class TriConsumer

/*
 *  End of File
 */