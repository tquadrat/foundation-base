/*
 * ============================================================================
 *  Copyright © 2002-2021 by Thomas Thrien.
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

package org.tquadrat.foundation.lang;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Stringer.DEFAULT_STRINGER;

import java.io.Serial;
import java.util.function.Function;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  This implementation of
 *  {@link StringConverter}
 *  allows to create an instance of {@code StringConverter} for an arbitrary
 *  type on the fly.
 *
 *  @param  <T> The Object type for the conversion.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: GenericStringConverter.java 897 2021-04-06 21:34:01Z tquadrat $
 *  @since 0.0.6
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: GenericStringConverter.java 897 2021-04-06 21:34:01Z tquadrat $" )
@API( status = STABLE, since = "0.0.6" )
public class GenericStringConverter<T> implements StringConverter<T>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The parser.
     */
    private final Function<CharSequence,T> m_Parser;

    /**
     *  The stringer.
     */
    private final Stringer<T> m_Stringer;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     *
     *  @hidden
     */
    @Serial
    private static final long serialVersionUID = 1L;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code GenericStringConverter} instance.
     *
     *  @param  parser  The function that translates a String to an object of
     *      type {@code T}.
     *  @param  stringer    The function that converts an object of type
     *      {@code T} to a String.
     */
    @SuppressWarnings( "unchecked" )
    public GenericStringConverter( final Function<? extends CharSequence,T> parser, final Stringer<T> stringer )
    {
        m_Parser = (Function<CharSequence,T>) requireNonNullArgument( parser, "parser" );
        m_Stringer = requireNonNullArgument( stringer, "stringer" );
    }   //  GenericStringConverter()

    /**
     *  Creates a new {@code GenericStringConverter} instance that uses
     *  {@link Stringer#DEFAULT_STRINGER}
     *  to convert an object of type {@code T} to a String.
     *
     *  @param  parser  The function that translates a String to an object of
     *      type {@code T}.
     */
    @SuppressWarnings( "unchecked" )
    public GenericStringConverter( final Function<? extends CharSequence,T> parser )
    {
        this( parser, (Stringer<T>) DEFAULT_STRINGER );
    }   //  GenericStringConverter()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final T fromString( final CharSequence source ) throws IllegalArgumentException
    {
        final var retValue = isNull( source ) ? null : m_Parser.apply( source );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  fromString()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String toString( final T source )
    {
        final var retValue = isNull( source ) ? null : m_Stringer.toString( source );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()
}
//  class GenericStringConverter

/*
 *  End of File
 */