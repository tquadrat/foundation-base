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
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  An implementation of
 *  {@link Spliterator}
 *  that which takes a seed value and applies a generator to create the next
 *  value, feeding each new value back into the generator to create subsequent
 *  values. If the generator returns
 *  {@link Optional#empty()},
 *  then there are no more values.
 *
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: UnfoldSpliterator.java 1031 2022-04-07 22:43:02Z tquadrat $
 *  @since 0.0.7
 *
 *  @param  <T> The type of the stream elements.
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: UnfoldSpliterator.java 1031 2022-04-07 22:43:02Z tquadrat $" )
@API( status = INTERNAL, since = "0.0.7" )
public final class UnfoldSpliterator<T> implements Spliterator<T>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The current value.
     */
    @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" )
    private Optional<T> m_Current;

    /**
     *  The generator.
     */
    private final Function<T,Optional<T>> m_Generator;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code UnfoldSpliterator} instance.
     *
     *  @param  seed    The initial value;may be {@code null}.
     *  @param  generator   The generator.
     */
    private UnfoldSpliterator( final T seed, final Function<T,Optional<T>> generator )
    {
        m_Current = Optional.of( seed );
        m_Generator = requireNonNullArgument( generator, "generator" );
    }   //  UnfoldSpliterator()

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
        final var retValue = IMMUTABLE & NONNULL & ORDERED;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  characteristics()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final long estimateSize() { return Long.MAX_VALUE; }

    /**
     *  Factory for instances of {@code UnfoldSpliterator}.
     *
     *  @param  <T> The type of the stream elements.
     *  @param  seed    The initial value;may be {@code null}.
     *  @param  generator   The generator.
     *  @return The new instance.
     */
    @SuppressWarnings( "UseOfConcreteClass" )
    public static final <T> UnfoldSpliterator<T> over( final T seed, final Function<T,Optional<T>> generator )
    {
        final var retValue = new UnfoldSpliterator<>( seed, generator );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  over

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean tryAdvance( final Consumer<? super T> action )
    {
        m_Current.ifPresent( action );
        m_Current = m_Current.flatMap( m_Generator );
        final var retValue = m_Current.isPresent();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  tryAdvance()

    /**
     *  {@inheritDoc}
     */
    @Override
    public Spliterator<T> trySplit() { return null; }
}
//  class UnfoldSpliterator

/*
 *  End of File
 */