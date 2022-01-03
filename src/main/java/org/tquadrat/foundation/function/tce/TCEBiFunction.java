/*
 * ============================================================================
 * Copyright © 2002-2021 by Thomas Thrien.
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

import java.util.function.BiFunction;
import java.util.function.Function;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary The TCE version of the interface
 *  {@link BiFunction}
 *  that represents a function that accepts two arguments and produces a
 *  result.} This is the two-arity specialisation of
 *  {@link Function}.</p>
 *  <p>Different from the method
 *  {@link java.util.function.BiFunction#apply(Object, Object) BiFunction.apply()}
 *  the method
 *  {@link #apply(Object,Object)}
 *  of this interface declares to throw a
 *  {@linkplain Exception checked exception}.</p>
 *  <p>This is a functional interface whose functional method is
 *  {@link #apply(Object,Object)}.</p>
 *
 *  @param  <T1>    The type of the first argument to the function.
 *  @param  <T2>    The type of the second argument to the function.
 *  @param  <R> The type of the result of the function.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TCEBiFunction.java 884 2021-03-22 18:02:51Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: TCEBiFunction.java 884 2021-03-22 18:02:51Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface TCEBiFunction<T1,T2,R>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Applies this function to the given arguments.
     *
     *  @param  arg1    The first function argument.
     *  @param  arg2    The second function argument.
     *  @return The function result.
     *  @throws Exception   Something went wrong.
     */
    @SuppressWarnings( "ProhibitedExceptionDeclared" )
    public R apply( T1 arg1, T2 arg2 ) throws Exception;
}
//  interface TCEBiFunction

/*
 *  End of File
 */