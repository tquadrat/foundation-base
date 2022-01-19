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

import java.util.function.Function;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary Represents an operation that accepts three input arguments and
 *  produces a result.} This is the three-arity specialisation of
 *  {@link java.util.function.Function}.</p>
 *  <p>This is a
 *  {@linkplain java.lang.FunctionalInterface functional interface}
 *  whose functional method is
 *  {@link #apply(Object,Object,Object)}.</p>
 *
 *  @param  <A> The type of the first argument to the function.
 *  @param  <B> The type of the second argument to the function.
 *  @param  <C> The type of the third argument to the function.
 *  @param  <R> The type of the result of the function.
 *
 *  @see java.util.function.Function
 *  @see java.util.function.BiFunction
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TriFunction.java 993 2022-01-19 22:26:20Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: TriFunction.java 993 2022-01-19 22:26:20Z tquadrat $" )
@FunctionalInterface
@API( status = STABLE, since = "0.0.5" )
public interface TriFunction<A,B,C,R>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Applies this function to the given arguments.
     *
     *  @param  a   The first function argument.
     *  @param  b   The second function argument.
     *  @param  c   The third function argument.
     *  @return The function result.
     */
    public R apply( final A a, final B b, final C c );

    /**
     *  Returns a composed function that first applies this function to its
     *  input, and then applies the after function to the result. If evaluation
     *  of either function throws an exception, it is relayed to the caller of
     *  the composed function.
     *
     *  @param  <R1>    The type of the output of the {@code after} function,
     *      and of the composed function.
     *  @param  after   The function to apply after this function is applied.
     *  @return A composed function that first applies this function and then
     *      applies the {@code after} function.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public default <R1> TriFunction<A,B,C,R1> andThen( final Function<? super R, ? extends R1> after )
    {
        @SuppressWarnings( "RedundantExplicitVariableType" )
        final TriFunction<A,B,C,R1> retValue = ( A a, B b, C c) -> requireNonNullArgument( after, "after" ).apply( apply( a, b, c ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  andThem()
}
//  class TriFunction

/*
 *  End of File
 */