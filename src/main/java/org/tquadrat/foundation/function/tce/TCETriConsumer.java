/*
 * ============================================================================
 * Copyright © 2002-2020by Thomas Thrien.
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
 *  {@link org.tquadrat.foundation.function.TriConsumer}
 *  that represents an operation that accepts three input arguments and returns
 *  no result. This is the three-arity specialisation of
 *  {@link TCEConsumer}.
 *  Unlike most other functional interfaces, {@code TriConsumer} is expected to
 *  operate via side-effects.<br>
 *  <br>Different from the method
 *  {@link org.tquadrat.foundation.function.TriConsumer#accept(Object,Object,Object) BiConsumer.accept()}
 *  the method
 *  {@link #accept(Object,Object,Object)}
 *  of this interface declares to throw a
 *  {@linkplain Exception checked exception}.<br>
 *  <br>This is a functional interface whose functional method is
 *  {@link #accept(Object,Object,Object)}.
 *
 *  @param <T1> The type of the first argument to the operation.
 *  @param <T2> The type of the second argument to the operation.
 *  @param <T3> The type of the third argument to the operation.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TCETriConsumer.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: TCETriConsumer.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface TCETriConsumer<T1,T2,T3>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  An empty array of {@code TCETriConsumer} objects.
     */
    @SuppressWarnings( "rawtypes" )
    public static final TCETriConsumer [] EMPTY_TCETriConsumer_ARRAY = new TCETriConsumer [0];

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Performs this operation on the given argument.
     *
     *  @param  arg1    The first input argument
     *  @param  arg2    The second input argument
     *  @param  arg3    The third input argument
     *  @throws Exception   Something went wrong.
     */
    @SuppressWarnings( "ProhibitedExceptionDeclared" )
    public void accept( T1 arg1, T2 arg2, T3 arg3 ) throws Exception;
}
//  interface TCETriConsumer

/*
 *  End of File
 */