/*
 * ============================================================================
 * Copyright © 2002-2024 by Thomas Thrien.
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
 *  {@link java.util.function.Predicate}
 *  that represents a predicate (boolean-valued function) of one argument. <br>
 *  <br>Different from the method
 *  {@link java.util.function.Predicate#test(Object) Predicate.test()}
 *  the method
 *  {@link #test(Object)}
 *  of this interface declares to throw a
 *  {@linkplain Exception checked exception}.<br>
 *  <br>This is a functional interface whose functional method is
 *  {@link #test(Object)}.
 *
 *  @param <T> The type of the input to the predicate.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TCEPredicate.java 1118 2024-03-15 16:14:15Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: TCEPredicate.java 1118 2024-03-15 16:14:15Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface TCEPredicate<T>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Evaluates this predicate on the given argument.
     *
     *  @param  arg The input argument
     *  @return {@code true} if the input argument matches the predicate,
     *      otherwise {@code false}.
     *  @throws Exception   Something went wrong.
     */
    @SuppressWarnings( {"ProhibitedExceptionDeclared"} )
    public boolean test( T arg ) throws Exception;
}
//  interface TCEPredicate

/*
 *  End of File
 */