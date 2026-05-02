/*
 * ============================================================================
 * Copyright © 2002-2026 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
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

package org.tquadrat.foundation.lang.value;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.function.UnaryOperator;

import static org.apiguardian.api.API.Status.STABLE;

/**
 *  <p>{@summary The implementations of this interface are used to give
 *  (numerical) values a dimension and a measuring unit.} So {@code 3.1415} is
 *  just a number, but it could also stand for a length
 *  ({@code 3.1415&nbsp;m}), a time ({@code 3.1415&nbsp;s}) or even the fuel
 *  consumption of a car ({@code 3.1415&nbsp;l/100&nbsp;km}). Depending on the
 *  dimension, different values could be equal:</p>
 *  <pre><code>1.0&nbsp;m == 100.0&nbsp;cm</code></pre>
 *  <p>and should be treated as such.</p>
 *  <p>This interface should be implemented as
 *  {@link Enum enum}s,
 *  where the enum values are the units, and as such they should provide a
 *  method to retrieve an {@code enum} value (a unit) by the respective
 *  {@linkplain #unitSymbol() symbol}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Dimension.java 1195 2026-04-15 21:33:40Z tquadrat $
 *  @since 0.25.1
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: Dimension.java 1195 2026-04-15 21:33:40Z tquadrat $" )
@API( status = STABLE, since = "0.25.1" )
public interface Dimension extends Serializable
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  Message: Unknown Unit.
     */
    public static final String MSG_UnknownUnit = "Unknown unit: %s";

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  <p>{@summary Returns the base unit.}</p>
     *  <p>E.g. for length, the base unit would be Meter (m), for mass, it is
     *  Kilogram (kg), and so on.</p>
     *
     *  @param  <D> The implementing class for the interface.
     *  @return The base unit.
     */
    public <D extends Dimension> D baseUnit();

    /**
     *  {@inheritDoc}
     *  <p>Dimensions are equal when they are identical; therefore they
     *  should be implemented as <i>Multitons</i> (constants of the
     *  implementing class, without the option to create additional instances
     *  on runtime).</p>
     */
    @Override
    public boolean equals( Object o );

    /**
     *  <p>{@summary Returns the conversion that is used to convert a value
     *  from the base unit to this unit.}</p>
     *
     *  @return The conversion.
     */
    public UnaryOperator<BigDecimal> fromBase();

    /**
     *  Returns the default precision for this unit that is used when the
     *  respective value is converted to a String.
     *
     *  @return The mantissa length for a value with this unit.
     */
    public default int getPrecision() { return 1; }

    /**
     *  <p>{@summary Returns the conversion that is used to convert a value
     *  from this unit to the base unit.}</p>
     *
     *  @return The conversion.
     */
    public UnaryOperator<BigDecimal> toBase();

    /**
     *  Returns the internal name of the dimension.
     *
     *  @return The internal name.
     */
    public String name();

    /**
     *  {@inheritDoc}
     *
     *  @note {@code toString()} should be implemented to return the result of
     *      {@link #unitSymbol()}
     *      instead of
     *      {@link Enum#name()}, as it would be the default for {@code enum}s.
     */
    @Override
    public String toString();

    /**
     *  <p>{@summary Returns the unit symbol for the dimension as a single line
     *  string.}</p>
     *  <p>For a length, this would be &quot;m&quot;, for a speed
     *  &quot;km/h&quot;, and for an acceleration, it could be
     *  &quot;m/(s^2)&quot;.</p>
     *
     *  @return The unit.
     */
    public String unitSymbol();

    /**
     *  <p>{@summary Returns the unit symbol for the dimension still as a
     *  single line string, but with special characters.}</p>
     *  <p>For a length, this would still be &quot;m&quot;, for a speed
     *  &quot;km/h&quot;, but for an acceleration, it would be
     *  &quot;m/s²&quot;.</p>
     *  <p>For most dimensions, this is the same as the return value of
     *  {@link #unitSymbol()}.</p>
     *
     *  @return The unit beautified for printing.
     */
    public default String unitSymbolForPrinting() { return unitSymbol(); }
}
//  interface Dimension

/*
 *  End of File
 */