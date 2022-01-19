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
 *  {@link java.util.function.Consumer}
 *  that represents an operation that accepts a single input argument and
 *  returns no result. Unlike most other functional interfaces,
 *  {@code Consumer} is expected to operate via side effects.<br>
 *  <br>Different from the method
 *  {@link java.util.function.Consumer#accept(Object) Consumer.accept()}
 *  the method
 *  {@link #accept(Object)}
 *  of this interface declares to throw a
 *  {@linkplain Exception checked exception}.<br>
 *  <br>This is a functional interface whose functional method is
 *  {@link #accept(Object)}.
 *
 *  @param <T> The type of the input to the operation.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TCEConsumer.java 993 2022-01-19 22:26:20Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: TCEConsumer.java 993 2022-01-19 22:26:20Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface TCEConsumer<T>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Performs this operation on the given argument.
     *
     *  @param  arg The input argument
     *  @throws Exception   Something went wrong.
     */
    @SuppressWarnings( "ProhibitedExceptionDeclared" )
    public void accept( T arg ) throws Exception;
}
//  interface TCEConsumer

/*
 *  End of File
 */