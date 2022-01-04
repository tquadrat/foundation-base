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

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  The TCE version of the interface
 *  {@link java.util.function.BinaryOperator}
 *  that represents an operation upon two operands of the same type, producing
 *  a result of the same type as the operands. This is a specialisation of
 *  {@link java.util.function.BiFunction}
 *  for the case where the operands and the result are all of the same type.<br>
 *  <br>Different from the method
 *  {@link java.util.function.BinaryOperator#apply(Object, Object) BinaryOperator.apply()}
 *  the method
 *  {@link #apply(Object,Object)}
 *  of this interface declares to throw a
 *  {@linkplain Exception checked exception}.<br>
 *  <br>This is a functional interface whose functional method is
 *  {@link #apply(Object,Object)}.
 *
 *  @param  <T> The type of the operands and result of the operator.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TCEBinaryOperator.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: TCEBinaryOperator.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface TCEBinaryOperator<T> extends TCEBiFunction<T,T,T>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  An empty array of {@code TCEBiFunction} objects.
     */
    @SuppressWarnings( "rawtypes" )
    public static final TCEBinaryOperator [] EMPTY_TCEBinaryOperator_ARRAY = new TCEBinaryOperator [0];

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
    @Override
    public T apply( T arg1, T arg2 ) throws Exception;
}
//  interface TCEBiFunction

/*
 *  End of File
 */