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

package org.tquadrat.foundation.function.tce;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  The TCE version of the interface
 *  {@link org.tquadrat.foundation.function.TriFunction}
 *  that represents a function that accepts three arguments and produces a
 *  result.<br>
 *  <br>Different from the method
 *  {@link org.tquadrat.foundation.function.TriFunction#apply(Object, Object, Object) Function.apply()}
 *  the method
 *  {@link #apply(Object, Object, Object)}
 *  of this interface declares to throw a
 *  {@linkplain Exception checked exception}.<br>
 *  <br>This is a functional interface whose functional method is
 *  {@link #apply(Object, Object, Object)}.
 *
 *  @param  <A> The type of the first argument to the function.
 *  @param  <B> The type of the second argument to the function.
 *  @param  <C> The type of the third argument to the function.
 *  @param  <R> The type of the result of the function.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TCETriFunction.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "ProhibitedExceptionDeclared" )
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: TCETriFunction.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface TCETriFunction<A,B,C,R>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  An empty array of {@code TCEFunction} objects.
     */
    @SuppressWarnings( "rawtypes" )
    public static final TCETriFunction [] EMPTY_TCETriFunction_ARRAY = new TCETriFunction [0];

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
     *  @throws Exception   Something went wrong.
     */
    @SuppressWarnings( "ProhibitedExceptionDeclared" )
    public R apply( A a, B b, C c ) throws Exception;

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
    public default <R1> TCETriFunction<A,B,C,R1> andThen( final TCEFunction<? super R, ? extends R1> after )
    {
        final TCETriFunction<A,B,C,R1> retValue = (A a, B b, C c) -> requireNonNullArgument( after, "after" ).apply( apply( a, b, c ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  andThem()
}
//  interface TCETriFunction

/*
 *  End of File
 */