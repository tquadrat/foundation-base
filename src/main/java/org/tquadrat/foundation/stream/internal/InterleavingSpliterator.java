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

package org.tquadrat.foundation.stream.internal;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.stream.Selector;

/**
 *  A selector function takes an array of values and returns the respective
 *  array index for the selected value. See
 *  {@link org.tquadrat.foundation.stream.Selectors}
 *  for some implementations of this interface.
 *
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: InterleavingSpliterator.java 1060 2023-09-24 19:21:40Z tquadrat $
 *  @since 0.0.7
 *
 *  @param  <T> The type of the values to select from.
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: InterleavingSpliterator.java 1060 2023-09-24 19:21:40Z tquadrat $" )
@API( status = INTERNAL, since = "0.0.7" )
public final class InterleavingSpliterator<T> implements Spliterator<T>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The element buffer.
     */
    private T [] m_Buffer = null;

    /**
     *  The supplier for the element buffer.
     */
    private final Supplier<T []> m_BufferSupplier;

    /**
     *  The selector function.
     */
    private final Function<T [],Integer> m_Selector;

    /**
     *  The source spliterators.
     */
    private final Spliterator<T> [] m_Spliterators;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code InterleavingSpliterator} instance.
     *
     *  @param  spliterators    The source spliterators.
     *  @param  bufferSupplier  The supplier for the element buffer.
     *  @param  selector    The selector function.
     */
    private InterleavingSpliterator( final Spliterator<T> [] spliterators, final Supplier<T []> bufferSupplier, final Function<T [],Integer> selector )
    {
        m_Spliterators = spliterators;
        m_BufferSupplier = bufferSupplier;
        m_Selector = selector;
    }   //  InterleavingSpliterator()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final int characteristics()
    {
        @SuppressWarnings( "ConstantExpression" )
        final var retValue = NONNULL & ORDERED & IMMUTABLE;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  characteristics()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final long estimateSize()
    {
        final var retValue = Stream.of( m_Spliterators ).anyMatch( spliterator -> spliterator.estimateSize() == Long.MAX_VALUE )
            ? Long.MAX_VALUE
            : Stream.of( m_Spliterators ).mapToLong( Spliterator::estimateSize ).sum();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  estimateSize()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final long getExactSizeIfKnown()
    {
        final var retValue = Stream.of( m_Spliterators ).allMatch( spliterator -> spliterator.hasCharacteristics( Spliterator.SIZED ) )
            ? Stream.of( m_Spliterators ).mapToLong( Spliterator::getExactSizeIfKnown ).sum()
            : -1;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getExactSizeIfKnown()

    /**
     *  Factory method for instances of {@code InterleavingSpliterator}.
     *
     *  @param  <T> The type of the values to select from.
     *  @param  spliterators    The source spliterators.
     *  @param  selector    The selector function.
     *  @return The interleaving spliterator.
     */
    public static final <T> Spliterator<T> interleaving( final Spliterator<T> [] spliterators, final Selector<T> selector )
    {
        final var bufferedValues = (Supplier<T[]>) () ->
        {
            @SuppressWarnings( {"unchecked", "SuspiciousArrayCast"} )
            final var values = (T []) new Object [spliterators.length];

            for( var i = 0; i < spliterators.length; ++i )
            {
                final var stableIndex = i;
                spliterators [i].tryAdvance( t -> values [stableIndex] = t );
            }

            return values;
        };

        final Spliterator<T> retValue = new InterleavingSpliterator<>( spliterators, bufferedValues, selector );

        //---* Done *----------------------------------------------------------
        return retValue;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean tryAdvance( final Consumer<? super T> action )
    {
        requireNonNullArgument( action, "action" );

        if( isNull( m_Buffer ) ) m_Buffer = m_BufferSupplier.get();

        final var retValue = !Stream.of( m_Buffer ).allMatch( Predicate.isEqual( null ) );

        if( retValue )
        {
            final var selected = m_Selector.apply( m_Buffer );
            action.accept( m_Buffer [selected] );

            if( !m_Spliterators [selected].tryAdvance( t -> m_Buffer [selected] = t ) )
            {
                //noinspection AssignmentToNull
                m_Buffer [selected] = null;
            }
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  tryAdvance()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Spliterator<T> trySplit() { return null; }
}
//  class InterleavingSpliterator

/*
 *  End of File
 */