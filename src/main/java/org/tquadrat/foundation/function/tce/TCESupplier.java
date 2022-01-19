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
 *  {@link java.util.function.Supplier}
 *  that represents a supplier of results. <br>
 *  <br>Different from the method
 *  {@link java.util.function.Supplier#get() Supplier.get()}
 *  the method
 *  {@link #get()}
 *  of this interface declares to throw a
 *  {@linkplain Exception checked exception}.<br>
 *  <br>As for the original interface, there is no requirement that a new or
 *  distinct result be returned each time the supplier is invoked.<br>
 *  <br>This is a functional interface whose functional method is
 *  {@link #get()}.
 *
 *  @param <T> The type of results supplied by this supplier.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TCESupplier.java 993 2022-01-19 22:26:20Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: TCESupplier.java 993 2022-01-19 22:26:20Z tquadrat $" )
@API( status = STABLE, since = "0.0.5" )
public interface TCESupplier<T>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns a result.
     *
     *  @return The result.
     *  @throws Exception   Something went wrong.
     */
    @SuppressWarnings( "ProhibitedExceptionDeclared" )
    public T get() throws Exception;
}
//  interface TCESupplier

/*
 *  End of File
 */