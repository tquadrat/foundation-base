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
 *  {@link java.util.function.BiConsumer}
 *  that represents an operation that accepts two input arguments and returns
 *  no result. This is the two-arity specialisation of
 *  {@link TCEConsumer}.
 *  Unlike most other functional interfaces, {@code BiConsumer} is expected to
 *  operate via side-effects.<br>
 *  <br>Different from the method
 *  {@link java.util.function.BiConsumer#accept(Object,Object) BiConsumer.accept()}
 *  the method
 *  {@link #accept(Object,Object)}
 *  of this interface declares to throw a
 *  {@linkplain Exception checked exception}.<br>
 *  <br>This is a functional interface whose functional method is
 *  {@link #accept(Object,Object)}.
 *
 *  @param <T1> The type of the first argument to the operation.
 *  @param <T2> The type of the second argument to the operation.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TCEBiConsumer.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "ProhibitedExceptionDeclared" )
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: TCEBiConsumer.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface TCEBiConsumer<T1,T2>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  An empty array of {@code TCEBiConsumer} objects.
     */
    @SuppressWarnings( "rawtypes" )
    public static final TCEBiConsumer [] EMPTY_TCEBiConsumer_ARRAY = new TCEBiConsumer [0];

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Performs this operation on the given argument.
     *
     *  @param  arg1    The first input argument
     *  @param  arg2    The second input argument
     *  @throws Exception   Something went wrong.
     */
    public void accept( T1 arg1, T2 arg2 ) throws Exception;
}
//  interface TCEBiConsumer

/*
 *  End of File
 */