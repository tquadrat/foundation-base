/*
 * ============================================================================
 *  Copyright © 2002-2023 by Thomas Thrien.
 *  All Rights Reserved.
 * ============================================================================
 *  Licensed to the public under the agreements of the GNU Lesser General Public
 *  License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations
 *  under the License.
 */

package org.tquadrat.foundation.scratch;

import static java.lang.String.format;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireValidDoubleArgument;

import org.tquadrat.foundation.exception.UnexpectedExceptionError;

public record Length( Dimension dimension, double value ) implements Cloneable, Comparable<Length>
{
        /*---------------*\
    ====** Inner Classes **==========================================
        \*---------------*/
    public enum Dimension
    {
            /*------------------*\
        ====** Enum Declaration **===================================
            \*------------------*/
        MILLIMETER(     1.0, "mm" ),
        CENTIMETER(    10.0, "cm" ),
        METER     ( 1_000.0, "m" );

            /*------------*\
        ====** Attributes **=========================================
            \*------------*/
        private final double m_Factor;
        private final String m_Unit;

            /*--------------*\
        ====** Constructors **=======================================
            \*--------------*/
        private Dimension( final double factor, final String unit )
        {
            m_Factor = factor;
            m_Unit = unit;
        }   //  Dimension()

            /*---------*\
        ====** Methods **============================================
            \*---------*/
        public final double factor() { return m_Factor; }

        public final String unit() { return m_Unit; }
    }
    //  enum Dimension

        /*--------------*\
    ====** Constructors **===========================================
        \*--------------*/
    public Length
    {
        requireNonNullArgument( dimension, "dimension" );
        value = requireValidDoubleArgument( value, "value", v -> v > 0.0 ) * dimension.factor();
    }   //  Length()

        /*---------*\
    ====** Methods **================================================
        \*---------*/
    @Override
    public final Length clone()
    {
        final Length retValue;
        try
        {
            retValue = (Length) super.clone();
        }
        catch( final CloneNotSupportedException e )
        {
            throw new UnexpectedExceptionError( e );
        }

        //---* Done *------------------------------------------------
        return retValue;
    }   //  clone

    @Override
    public final int compareTo( final Length o ) { return Double.compare( value, o.value ); }

    @Override
    public final String toString() { return format( "%2$f %1$s", dimension.unit(), value( dimension() ) ); }

    public final double value( final Dimension otherDimension ) { return value / requireNonNullArgument( otherDimension, "otherDimension" ).factor(); }
}
//  record Length