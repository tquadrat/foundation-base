/*
 * ============================================================================
 * Copyright © 2002-2026 by Thomas Thrien.
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

import java.util.function.Predicate;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary The TCE version of the interface
 *  {@link Predicate}
 *  that represents a predicate (boolean-valued function) of one argument.}</p>
 *  <p>Different from the method
 *  {@link java.util.function.Predicate#test(Object) Predicate.test()}
 *  the method
 *  {@link #test(Object)}
 *  of this interface declares to throw a
 *  {@linkplain Exception checked exception}.</p>
 *  <p>This is a functional interface whose functional method is
 *  {@link #test(Object)}.</p>
 *
 *  @param <T> The type of the input to the predicate.
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TCEPredicate.java 1258 2026-06-04 18:33:06Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: TCEPredicate.java 1258 2026-06-04 18:33:06Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface TCEPredicate<T>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  <p>{@summary Evaluates this predicate on the given argument.}</p>
     *
     *  @param  arg The input argument
     *  @return {@true} if the input argument matches the predicate,
     *      otherwise {@false}.
     *  @throws Exception   Something went wrong.
     */
    @SuppressWarnings( {"ProhibitedExceptionDeclared", "BooleanMethodNameMustStartWithQuestion"} )
    public boolean test( T arg ) throws Exception;
}
//  interface TCEPredicate

/*
 *  End of File
 */