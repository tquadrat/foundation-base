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
import org.tquadrat.foundation.exception.UnexpectedExceptionError;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Formattable;
import java.util.Formatter;
import java.util.IllegalFormatException;
import java.util.Locale;

import static java.lang.String.format;
import static java.math.RoundingMode.HALF_EVEN;
import static java.util.FormattableFlags.LEFT_JUSTIFY;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;

/**
 *  <p>{@summary The definition for a value with a dimension.}</p>
 *  <p>Although the unit for the dimension may be changed, instances of
 *  classes implementing this interface can be assumed to be immutable as the
 *  <i>value</i> remains always the same. So at least the results of the
 *  methods
 *  {@link #equals(Object)},
 *  {@link #hashCode()},
 *  and
 *  {@link #compareTo(DimensionedValue)}
 *  will remain always the same
 *  (while the results from
 *  {@link #toString()}
 *  may differ after a call to
 *  {@link #setUnit(Dimension)}).</p>
 *  <p>All concrete (non-abstract) implementations of this interface should be
 *  {@code final}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: DimensionedValue.java 1195 2026-04-15 21:33:40Z tquadrat $
 *  @since 0.15.1
 *
 *  @param  <D> The dimension.
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "ClassWithTooManyMethods" )
@ClassVersion( sourceVersion = "$Id: DimensionedValue.java 1195 2026-04-15 21:33:40Z tquadrat $" )
@API( status = STABLE, since = "0.25.1" )
public sealed interface DimensionedValue<D extends Dimension> extends Cloneable, Comparable<DimensionedValue<D>>, Formattable, Serializable
    permits ValueBase
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The
     *  {@link MathContext}
     *  that is used for all the operations with dimensioned values.
     */
    public static final MathContext MATH_CONTEXT = new MathContext( 128, HALF_EVEN );

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns the base unit of the dimension for the value.
     *
     *  @return The base unit.
     */
    public default D baseUnit()
    {
        @SuppressWarnings( "unchecked" )
        final var retValue = (D) getUnit().baseUnit();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  baseUnit()

    /**
     *  <p>{@summary Returns the base value (this value, converted to the base
     *  unit).}</p>
     *  <p>According to the result, this is the same as calling</p>
     *  <div class="source-container"><pre>convert( baseUnit() );</pre></div>
     *
     *  @return The numerical value as for the base unit.
     *
     *  @see #convert(Dimension)
     */
    public BigDecimal baseValue();

    /**
     *  Creates a new copy of this value.
     *
     *  @return The copy.
     */
    public DimensionedValue<D> clone();

    /**
     *  {@inheritDoc}
     *  <p>The comparison is made based on the
     *  {@link #baseValue()}.</p>
     */
    @Override
    public default int compareTo( final DimensionedValue<D> other )
    {
        final var retValue = Integer.signum( baseValue().compareTo( requireNonNullArgument( other, "other" ).baseValue() ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  compareTo()

    /**
     *  Converts this value to the given unit and returns the numerical value.
     *
     *  @param  unit    The unit.
     *  @return The numerical value of this instance, based on the provided
     *      unit.
     *
     *  @see #baseValue()
     */
    public default BigDecimal convert( final D unit )
    {
        final var conversion = requireNonNullArgument( unit, "unit" ).fromBase();
        final var retValue = conversion.apply( baseValue() ).stripTrailingZeros();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  convert()

    /**
     *  Creates a new copy of this value.
     *
     *  @return The copy.
     *
     *  @see Object#clone()
     */
    public DimensionedValue<D> copy();

    /**
     *  Creates a new copy of this value.
     *
     *  @param  unit    The unit for the new copy.
     *  @return The copy.
     *
     *  @see Object#clone()
     */
    public DimensionedValue<D> copy( final D unit );

    /**
     *  Divides the value by a dimension-less value and returns the result
     *  without changing this instance.
     *
     *  @param  divisor The divisor.
     *  @return The new value.
     */
    public default DimensionedValue<D> divide( final BigDecimal divisor )
    {
        final var retValue = newInstance( baseUnit(), baseValue().divide( requireNonNullArgument( divisor, "divisor" ), MATH_CONTEXT ) );
        retValue.setUnit( getUnit() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  divide()

    /**
     *  Divides the value by a dimension-less value and returns the result
     *  without changing this instance.
     *
     *  @param  divisor The divisor.
     *  @return The new value.
     */
    public default DimensionedValue<D> divide( final Number divisor )
    {
        final var retValue = divide( new BigDecimal( requireNonNullArgument( divisor, "divisor" ).toString() ) );
        retValue.setUnit( getUnit() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  divide()

    /**
     *  Divides the value by a dimension-less value and returns the result
     *  without changing this instance.
     *
     *  @param  divisor The divisor; it must be possible to parse the given
     *      String into a
     *      {@link BigDecimal}.
     *  @return The new value.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     *
     *  @see BigDecimal#BigDecimal(String)
     */
    public default DimensionedValue<D> divide( final String divisor )
    {
        final var retValue = divide( new BigDecimal( requireNonNullArgument( divisor, "divisor" ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  divide()

    /**
     *  {@inheritDoc}
     *  <p>Two instances of a class implementing this interface are equals if
     *  they are of the <i>same</i> class and if their values, converted to the
     *  base dimension, are equals.</p>
     *
     *  @param  o   The other value.
     *  @return {@code true} if they are equal, {@code false} if not.
     *
     *  @see Dimension#baseUnit()
     */
    @Override
    public boolean equals( final Object o );

    /**
     *  {@inheritDoc}
     *  <p>The precision is applied to the numerical part only. The width
     *  includes the
     *  {@linkplain Dimension#unitSymbol() unit symbol},
     *  too.</p>
     *
     *  @note In case the {@code formatter} argument is {@code null}, this
     *      method throws a {@code NullPointerException} and <i>not</i> the
     *      usual {@code NullArgumentException}, because this method is usually
     *      called by instances of {@code java.util.Formatter}, and those do
     *      not know about our special exceptions.
     *
     *  @throws NullPointerException    The {@code formatter} argument is
     *      {@code null}.
     *
     *  @see Formatter
     */
    @SuppressWarnings( "ProhibitedExceptionThrown" )
    @Override
    public default void formatTo( final Formatter formatter, final int flags, final int width, final int precision )
    {
        if( isNull( formatter ) ) throw new NullPointerException( "formatter is null" );

        var string = toString( width, precision ).trim();
        final var len = string.length();

        if( ((flags & LEFT_JUSTIFY) == LEFT_JUSTIFY) && (width > len) )
        {
            string = string.concat( " ".repeat( width - len ) );
        }

        /*
         * We do not use Formatter.out().append() because we do not know how to
         * handle the IOException that could be thrown from
         * Appendable.append(). Using Formatter.format() assumes that Formatter
         * knows ...
         */
        formatter.format( "%s", string );
    }   //  formatTo()

    /**
     *  Returns the unit for the value.
     *
     *  @return The unit.
     */
    public D getUnit();

    /**
     *  {@inheritDoc}
     *  <p>The hash code is based on the
     *  {@linkplain #baseValue() base value}
     *  and
     *  {@linkplain #baseUnit() base unit}
     *  only.</p>
     */
    @Override
    public int hashCode();

    /**
     *  Multiplies the value by a dimension-less value and returns the result
     *  without changing this instance.
     *
     *  @param  multiplicand The multiplier.
     *  @return The new value.
     */
    public default DimensionedValue<D> multiply( final BigDecimal multiplicand )
    {
        final var retValue = newInstance( baseUnit(), baseValue().multiply( requireNonNullArgument( multiplicand, "multiplicand" ) ) );
        retValue.setUnit( getUnit() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  multiply()

    /**
     *  Multiplies the value by a dimension-less value and returns the result
     *  without changing this instance.
     *
     *  @param  multiplicand The multiplier.
     *  @return The new value.
     */
    public default DimensionedValue<D> multiply( final Number multiplicand )
    {
        final var retValue = multiply( new BigDecimal( requireNonNullArgument( multiplicand, "multiplicand" ).toString() ) );
        retValue.setUnit( getUnit() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  multiply()

    /**
     *  Multiplies the value by a dimension-less value and returns the result
     *  without changing this instance.
     *
     *  @param  multiplicand The multiplier; it must be possible to parse the
     *      given String into a
     *      {@link BigDecimal}.
     *  @return The new value.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     *
     *  @see BigDecimal#BigDecimal(String)
     */
    public default DimensionedValue<D> multiply( final String multiplicand )
    {
        final var retValue = multiply( new BigDecimal( requireNonNullArgument( multiplicand, "multiplicand" ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  multiply()

    /**
     *  Creates an instance for the class.
     *
     *  @param  dimension   The dimension for the new instance.
     *  @param  value   The value for the new instance.
     *  @return The new instance.
     */
    public default DimensionedValue<D> newInstance( final D dimension, final BigDecimal value )
    {
        @SuppressWarnings( "UnusedAssignment" )
        DimensionedValue<D> retValue = null;

        try
        {
            //---* Let's get the constructor *---------------------------------
            final Constructor<DimensionedValue<D>> constructor;
            if( requireNonNullArgument( dimension, "dimension" ) instanceof final Enum<?> enumConstant )
            {
                final var enumClass = enumConstant.getDeclaringClass();
                //noinspection unchecked
                constructor = (Constructor<DimensionedValue<D>>) getClass().getConstructor( enumClass, BigDecimal.class );
            }
            else
            {
                throw new IllegalArgumentException( "Invalid type for 'dimension': %s".formatted( dimension.getClass().getName() ) );
            }

            //---* Create the new value *----------------------------------
            retValue = constructor.newInstance( dimension, requireNonNullArgument( value, "value" ) );
        }
        catch( final NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException e )
        {
            throw new UnexpectedExceptionError( e );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  newInstance()

    /**
     *  Applies another unit for the value. This does not affect the results
     *  of
     *  {@link #equals(Object)},
     *  {@link #hashCode()}}
     *  and
     *  {@link #compareTo(DimensionedValue)},
     *  nor that of
     *  {@link #baseValue()}.
     *
     *  @param  unit    The new unit.
     */
    public void setUnit( D unit );

    /**
     *  Creates a new instance with the sum of this and the given value, and
     *  returns that.
     *
     *  @param  unit    The unit for the new instance.
     *  @param  summand The value to add.
     *  @return The instance with the sum.
     */
    public default DimensionedValue<D> sum( final D unit, final DimensionedValue<D> summand )
    {
        final var retValue = sum( summand );
        retValue.setUnit( unit );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  sum()

    /**
     *  Creates a new instance with the sum of this and the given value, and
     *  returns that. The unit for the new instance is that of this instance.
     *
     *  @param  summand The value to add.
     *  @return The instance with the sum.
     */
    public default DimensionedValue<D> sum( final DimensionedValue<D> summand )
    {
        final var newValue = baseValue().add( requireNonNullArgument( summand, "summand" ).baseValue(), MATH_CONTEXT );
        final var retValue = newInstance( baseUnit(), newValue );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   // sum()

    /**
     *  <p>{@summary Returns the String representation for this value};
     *  usually, this is in the format</p>
     *  <pre><code>&lt;<i>numerical value</i>&gt;&nbsp;&lt;<i>unit symbol</i>&gt;</code></pre>
     *  <p>like &quot;{@code 4.5 m}&quot;.</p>
     *  <p>The precision for the mantissa is
     *  provided by the
     *  {@linkplain Dimension#getPrecision() unit}.</p>
     *  <p>If more control over the output format is required, see
     *  {@link #toString(int, int)}.</p>
     */
    @Override
    public String toString();

    /**
     *  <p>{@summary Provides a String representation of this value}, in the
     *  format</p>
     *  <pre><code>&lt;<i>numerical value</i>&gt;&nbsp;&lt;<i>unit symbol</i>&gt;</code></pre>
     *  <p>for the given
     *  {@link Locale}
     *  that determines the decimal separator, like &quot;{@code 4.5 m}&quot;
     *  vs. &quot;{@code 4,5 m}&quot;.</p>
     *  <p>The precision is applied to the numerical part only. The width
     *  includes the
     *  {@linkplain Dimension#unitSymbol() unit symbol}, too.</p>
     *
     *  @param  locale  The locale to use.
     *  @param  width   The minimum number of characters to be written to the
     *      output. If the length of the converted value is less than the width
     *      then the output will be padded by '&nbsp;' until the total number
     *      of characters equals width. The padding is at the beginning, as
     *      numerical values are usually right justified. If {@code width} is
     *      -1 then there is no minimum.
     *  @param  precision – The number of digits for the mantissa of the value.
     *      If {@code precision} is -1 then there is no explicit limit on the
     *      size of the mantissa.
     *  @return The String representation for this value.
     */
    public default String toString( final Locale locale, final int width, final int precision )
    {
        final var retValue = toString( locale, width, precision, false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  <p>{@summary Provides a String representation of this value}, in the
     *  format</p>
     *  <pre><code>&lt;<i>numerical value</i>&gt;&nbsp;&lt;<i>unit symbol</i>&gt;</code></pre>
     *  <p>for the given
     *  {@link Locale}
     *  that determines the decimal separator, like &quot;{@code 4.5 m}&quot;
     *  vs. &quot;{@code 4,5 m}&quot;.</p>
     *  <p>The precision is applied to the numerical part only. The width
     *  includes the
     *  {@linkplain Dimension#unitSymbol() unit symbol}, too.</p>
     *
     *  @param  locale  The locale to use.
     *  @param  width   The minimum number of characters to be written to the
     *      output. If the length of the converted value is less than the width
     *      then the output will be padded by '&nbsp;' until the total number
     *      of characters equals width. The padding is at the beginning, as
     *      numerical values are usually right justified. If {@code width} is
     *      -1 then there is no minimum.
     *  @param  precision – The number of digits for the mantissa of the value.
     *      If {@code precision} is -1 then there is no explicit limit on the
     *      size of the mantissa.
     *  @param  useNiceUnit {@code true} if the method
     *      {@link Dimension#unitSymbolForPrinting() unitSymbolForPrinting()}
     *      should be used to retrieve the unit symbol, {@code false} if the
     *      usual one is sufficient.
     *  @return The String representation for this value.
     */
    public default String toString( final Locale locale, final int width, final int precision, final boolean useNiceUnit )
    {
        final var unitSymbol = useNiceUnit ? getUnit().unitSymbolForPrinting() : getUnit().unitSymbol();
        final var effectiveWidth = width - unitSymbol.length() - 1;

        final var format = new StringBuilder( "%" );
        if( effectiveWidth > 0 ) format.append( effectiveWidth );
        if( precision >= 0 ) format.append( "." ).append( precision );
        format.append( "f %s" );

        final var retValue = format( requireNonNullArgument( locale, "locale" ), format.toString(), value(), unitSymbol );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  <p>{@summary Provides a String representation of this value}, in the
     *  format</p>
     *  <pre><code>&lt;<i>numerical value</i>&gt;&nbsp;&lt;<i>unit symbol</i>&gt;</code></pre>
     *  <p>and for the
     *  {@linkplain Locale#getDefault() default Locale},
     *  like &quot;{@code 4.5 m}&quot;, where the Locale determines the decimal
     *  separator.</p>
     *  <p>The precision is applied to the numerical part only. The width
     *  includes the
     *  {@linkplain Dimension#unitSymbol() unit symbol}, too.</p>
     *
     *  @param  width   The minimum number of characters to be written to the
     *      output. If the length of the converted value is less than the width
     *      then the output will be padded by '&nbsp;' until the total number
     *      of characters equals width. The padding is at the beginning, as
     *      numerical values are usually right justified. If {@code width} is
     *      -1 then there is no minimum.
     *  @param  precision – The number of digits for the mantissa of the value.
     *      If {@code precision} is -1 then there is no explicit limit on the
     *      size of the mantissa.
     *  @return The String representation for this value.
     */
    public default String toString( final int width, final int precision )
    {
        final var retValue = toString( Locale.getDefault(), width, precision );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  <p>{@summary Provides a String representation of this value}, in the
     *  format that is defined by the provided format String.</p>
     *  <p>That format String must contain exactly one '%f' tag and one '%s'
     *  tag; the first takes the numerical value, the second the unit.</p>
     *  <p>The provided
     *  {@link Locale}
     *  determines the decimal separator and the optional thousands'
     *  separator.</p>
     *
     *  @param  locale  The locale to use.
     *  @param  format  The format String.
     *  @param  useNiceUnit {@code true} if the method
     *      {@link Dimension#unitSymbolForPrinting() unitSymbolForPrinting()}
     *      should be used to retrieve the unit symbol, {@code false} if the
     *      usual one is sufficient.
     *  @return The String representation for this value.
     *  @throws IllegalFormatException  The provided format String is invalid.
     *
     *  @see Formatter
     */
    public default String toString( final Locale locale, final String format, final boolean useNiceUnit ) throws IllegalFormatException
    {
        final var unitSymbol = useNiceUnit ? getUnit().unitSymbolForPrinting() : getUnit().unitSymbol();
        final var retValue = format( requireNonNullArgument( locale, "locale" ), requireNotEmptyArgument( format, "format" ), value(), unitSymbol );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  Returns the numerical value.
     *
     *  @return The numerical value, based on current dimension.
     */
    public default BigDecimal value() { return convert( getUnit() ); }
}
//  interface DimensionedValue

/*
 *  End of File
 */