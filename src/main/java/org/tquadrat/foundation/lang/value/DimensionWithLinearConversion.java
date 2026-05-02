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

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.value.DimensionedValue.MATH_CONTEXT;

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
 *  <p>It is a generalisation for dimensions that are converted by simple
 *  division or multiplication of a factor, as for length or speed.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: DimensionWithLinearConversion.java 1195 2026-04-15 21:33:40Z tquadrat $
 *  @since 0.25.1
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: DimensionWithLinearConversion.java 1195 2026-04-15 21:33:40Z tquadrat $" )
@API( status = STABLE, since = "0.25.1" )
public interface DimensionWithLinearConversion extends Dimension
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
     *  <p>{@summary Returns the factor that is used to convert a value from
     *  this unit to the base unit.}</p>
     *  <p>For length, if you have to convert a Centimeter value to Meter, you
     *  will divide that by 100 or multiply it with a <i>factor</i> of
     *  0.01.</p>
     *  <p>For the base unit, the factor is 1.0.</p>
     *
     *  @return The factor.
     *
     *  @see #baseUnit()
     */
    public BigDecimal factor();

    /**
     * {@inheritDoc}
     */
    @Override
    public default UnaryOperator<BigDecimal> fromBase()
    {
        final UnaryOperator<BigDecimal> retValue = base -> base.divide( factor(), MATH_CONTEXT );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  fromBase()

    /**
     *  <p>{@summary Returns the conversion that is used to convert a value
     *  from this unit to the base unit.}</p>
     *
     *  @return The conversion.
     */
    @Override
    public default UnaryOperator<BigDecimal> toBase()
    {
        final UnaryOperator<BigDecimal> retValue = value -> value.multiply( factor() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toBase()
}
//  interface DimensionWithLinearConversion

/*
 *  End of File
 */