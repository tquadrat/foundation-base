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

import java.util.function.Supplier;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary Represents a supplier of
 *  {@link Number}-valued
 *  results.} This is the {@code Number}-producing specialisation of
 *  {@link java.util.function.Supplier}.</p>
 *  <p>There is no requirement that a distinct result is returned each time the
 *  supplier is invoked.</p>
 *  <p>This is a
 *  {@linkplain java.lang.FunctionalInterface functional interface}
 *  whose functional method is
 *  {@link #getAsNumber()}.</p>
 *
 *  @see java.util.function.Supplier
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: NumberSupplier.java 993 2022-01-19 22:26:20Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: NumberSupplier.java 993 2022-01-19 22:26:20Z tquadrat $" )
@FunctionalInterface
@API( status = STABLE, since = "0.0.5" )
public interface NumberSupplier extends Supplier<Number>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public default Number get() { return getAsNumber(); }

    /**
     *  Gets a result.
     *
     *  @return The result; may be {@code null}.
     */
    public Number getAsNumber();
}
//  interface NumberSupplier

/*
 *  End of File
 */