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

import java.io.Serial;
import java.math.BigDecimal;
import java.util.function.BiPredicate;

import static java.lang.String.format;
import static java.util.Locale.ROOT;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.hash;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;

/**
 *  <p>{@summary An abstract base implementation for the interface
 *  {@link DimensionedValue}
 *  that is intended as the base for concrete implementations of value
 *  types.}</p>
 *  <p>The {@code validator} argument of the constructors is an instance of
 *  {@link BiPredicate}
 *  that takes the
 *  {@linkplain Dimension unit}
 *  with that the instance is initialised, and the value, converted to the base
 *  unit. It returns {@code true} if the given combination is valid, otherwise
 *  it returns {@code false}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ValueBase.java 1195 2026-04-15 21:33:40Z tquadrat $
 *  @since 0.25.1
 *
 *  @param  <D> The dimension.
 *  @param  <I> The implementing type.
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: ValueBase.java 1195 2026-04-15 21:33:40Z tquadrat $" )
@API( status = STABLE, since = "0.25.1" )
public abstract non-sealed class ValueBase<D extends Dimension, I extends DimensionedValue<D>> implements DimensionedValue<D>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The default validator.
     */
    @SuppressWarnings( "rawtypes" )
    protected static final BiPredicate DEFAULT_VALIDATOR = ( _, _ ) -> true;

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The unit for the value.
     *
     *  @serial
     */
    private D m_Unit;

    /**
     *  The validation function.
     *
     *  @serial
     */
    private final BiPredicate<D,BigDecimal> m_Validator;

    /**
     *  The numerical value for this instance.
     *
     *  @serial
     */
    private final BigDecimal m_Value;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     */
    @Serial
    private static final long serialVersionUID = -2075489505691464486L;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code ValueBase} instance.
     *
     *  @param  unit   The unit.
     *  @param  value   The value; only absolute (positive) values are allowed,
     *      a sign will be stripped.
     *  @param  validator   The validator function; can be {@code null}.
     */
    protected ValueBase( final D unit, final BigDecimal value, final BiPredicate<D,BigDecimal> validator )
    {
        requireNonNullArgument( value, "value" );
        setUnit( unit );
        final var conversion = unit.toBase();
        m_Validator = requireNonNullArgument( validator, "validator" );
        m_Value = validate( conversion.apply( value ) ).abs().stripTrailingZeros();
    }   //  ValueBase()

    /**
     *  Creates a new {@code ValueBase} instance.
     *
     *  @param  unit   The unit.
     *  @param  value   The value; it must be possible to parse the given
     *      String into a
     *      {@link BigDecimal}.
     *  @param  validator   The validator function.
     *  @throws NumberFormatException   The provided value cannot be converted
     *      into a {@code BigDecimal}.
     */
    protected ValueBase( final D unit, final String value, final BiPredicate<D,BigDecimal> validator ) throws NumberFormatException
    {
        this( unit, new BigDecimal( requireNotEmptyArgument( value, "value" ) ), validator );
    }   //  ValueBase()

    /**
     *  Creates a new {@code ValueBase} instance.
     *
     *  @param  <N> The type of {@code value}.
     *  @param  unit   The unit.
     *  @param  value   The value.
     *  @param  validator   The validator function.
     */
    protected <N extends Number> ValueBase( final D unit, final N value, final BiPredicate<D,BigDecimal> validator )
    {
        this( unit, new BigDecimal( requireNonNullArgument( value, "value" ).toString() ), validator );
    }   //  ValueBase()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final BigDecimal baseValue() { return m_Value; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public I clone()
    {
        final I retValue;
        try
        {
            @SuppressWarnings( "unchecked" )
            final var clone = (I) super.clone();
            retValue = clone;
        }
        catch( final CloneNotSupportedException e )
        {
            throw new UnexpectedExceptionError( e );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  clone()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final I copy()
    {
        final var retValue = clone();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  copy()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final I copy( final D unit )
    {
        final var retValue = copy();
        retValue.setUnit( unit ); // Does the null check ...

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  copy()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean equals( final Object obj )
    {
        var retValue = this == obj;
        if( !retValue && (obj instanceof final ValueBase<?,?> other ) )
        {
            if( baseUnit() == other.baseUnit() )
            {
                retValue = baseValue().compareTo( other.baseValue() ) == 0;
            }
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  equals()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final D getUnit() { return m_Unit; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int hashCode() { return hash( baseValue(), baseUnit() ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void setUnit( final D dimension ) { m_Unit = requireNonNullArgument( dimension, "dimension" ); }

    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "CallToNumericToString" )
    @Override
    public final String toString() { return format( ROOT, "%s %s", convert( m_Unit ).toString(), m_Unit.unitSymbol() ); }

    /**
     *  <p>{@summary Validates the given value, based on the type of the
     *  dimension.}</p>
     *  <p>This method will be called by the constructors always with the
     *  {@linkplain #baseUnit() base unit}
     *  value just before the value will be initialised.</p>
     *
     *  @param  value   The value in base units.
     *  @return The value.
     *  @throws IllegalArgumentException    The validation failed.
     */
    private final BigDecimal validate( final BigDecimal value ) throws IllegalArgumentException
    {
        if( !m_Validator.test( m_Unit, requireNonNullArgument( value, "value" ) ) )
        {
            //noinspection CallToNumericToString
            throw new IllegalArgumentException( "Value %s is invalide".formatted( value.toString() ) );
        }

        //---* Done *----------------------------------------------------------
        return value;
    }   //  validate()
}
//  class ValueBase

/*
 *  End of File
 */