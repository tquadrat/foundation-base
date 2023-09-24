/*
 * ============================================================================
 * Copyright © 2014 by Dominic Fox.
 * All Rights Reserved.
 * ============================================================================
 * The MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package org.tquadrat.foundation.stream;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.nonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  A value combined with an index, indicating its position in an ordered
 *  sequence.
 *
 *  @param  <T> The type of the indexed value.
 *
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Indexed.java 995 2022-01-23 01:09:35Z tquadrat $
 *  @since 0.0.7
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: Indexed.java 995 2022-01-23 01:09:35Z tquadrat $" )
@API( status = STABLE, since = "0.0.7" )
public final class Indexed<T> implements Map.Entry<Long,T>, Serializable
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The index.
     */
    private final long m_Index;

    /**
     *  The value.
     */
    private T m_Value;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code Indexed} instance.
     *
     *  @param  index   The index.
     *  @param  value   The value.
     */
    private Indexed( final long index, final T value )
    {
        m_Index = index;
        m_Value = value;
    }   //  Indexed()

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

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Factory method for instances of {@code Indexed}; it combines an index
     *  and a value into an indexed value.
     *
     *  @param  <T> The type of the value.
     *  @param  index   The index of the value.
     *  @param  value   The value indexed.
     *  @return The indexed value.
     */
    public static <T> Indexed<T> index( final long index, final T value )
    {
        final var retValue = new Indexed<>( index, value );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  index()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean equals( final Object obj )
    {
        var retValue = this == obj;
        if( !retValue && nonNull( obj ) && (getClass() == obj.getClass()) )
        {
            @SuppressWarnings( "rawtypes" )
            final var other = (Indexed) obj;
            retValue = (getIndex() == other.getIndex()) && Objects.equals( getValue(), other.getValue() );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  equals()

    /**
     *  Returns the index.
     *
     *  @return The index.
     */
    public final long getIndex() { return m_Index; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Long getKey() { return Long.valueOf( m_Index ); }

    /**
     *  The indexed value.
     *
     *  @return The value.
     */
    @Override
    public final T getValue() { return m_Value; }

    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "NonFinalFieldReferencedInHashCode" )
    public final int hashCode() { return Objects.hash( m_Index, m_Value ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final T setValue( final T value )
    {
        final var retValue = m_Value;
        m_Value = value;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  setValue()
}
//  class Indexed

/*
 *  End of File
 */