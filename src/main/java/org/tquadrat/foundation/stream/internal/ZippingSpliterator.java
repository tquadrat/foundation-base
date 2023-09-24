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

import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  An implementation of
 *  {@link Spliterator}
 *  that zips two streams into one.
 *
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ZippingSpliterator.java 1031 2022-04-07 22:43:02Z tquadrat $
 *  @since 0.0.7
 *
 *  @param  <L> The type over which the &quot;left&quot; stream is streaming.
 *  @param  <R> The type over which the &quot;right&quot; stream is streaming.
 *  @param  <O> The type created by the combiner out of pairs of
 *      &quot;left&quot; and &quot;right&quot; values, over which the resulting
 *      stream streams.
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: ZippingSpliterator.java 1031 2022-04-07 22:43:02Z tquadrat $" )
@API( status = INTERNAL, since = "0.0.7" )
public final class ZippingSpliterator<L,R,O> implements Spliterator<O>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The combiner function.
     */
    private final BiFunction<L,R,O> m_Combiner;

    /**
     *  The left-hand source.
     */
    private final Spliterator<L> m_Lefts;

    /**
     *  Flag the indicates whether the right-hand source has the next value.
     */
    private boolean m_RightHadNext = false;

    /**
     *  The right-hand source.
     */
    private final Spliterator<R> m_Rights;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code ZippingSpliterator} instance.
     *
     *  @param  lefts   The left-hand source.
     *  @param  rights  The right-hand source.
     *  @param  combiner    The combiner.
     */
    private ZippingSpliterator( final Spliterator<L> lefts, final Spliterator<R> rights, final BiFunction<L,R,O> combiner )
    {
        m_Lefts = requireNonNullArgument( lefts, "lefts" );
        m_Rights = requireNonNullArgument( rights, "rights" );
        m_Combiner = requireNonNullArgument( combiner, "combiner" );
    }   //  ZippingSpliterator()

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
        final var retValue = m_Lefts.characteristics() & m_Rights.characteristics() & ~ (DISTINCT | SORTED);

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  characteristics()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final  long estimateSize() { return Math.min( m_Lefts.estimateSize(), m_Rights.estimateSize() ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean tryAdvance( final Consumer<? super O> action )
    {
        m_RightHadNext = false;
        final var leftHadNext = m_Lefts.tryAdvance( left ->
                m_Rights.tryAdvance( right -> {
                    m_RightHadNext = true;
                    action.accept( m_Combiner.apply( left, right ) );
                } ) );
        final var retValue = leftHadNext && m_RightHadNext;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  tryAdvance()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Spliterator<O> trySplit() { return null; }

    /**
     *  Factory for instances of {@code ZippingSpliterator}.
     *
     *  @param  <L> The type over which the &quot;left&quot; stream is
     *      streaming.
     *  @param  <R> The type over which the &quot;right&quot; stream is
     *      streaming.
     *  @param  <O> The type created by the combiner out of pairs of
     *      &quot;left&quot; and &quot;right&quot; values, over which the
     *      resulting stream streams.
     *  @param  lefts   The left-hand source.
     *  @param  rights  The right-hand source.
     *  @param  combiner    The combiner.
     *  @return The new instance.
     */
    public static final <L,R,O> Spliterator<O> zipping( final Spliterator<L> lefts, final Spliterator<R> rights, final BiFunction<L,R,O> combiner )
    {
        final var retValue = new ZippingSpliterator<>( lefts, rights, combiner );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  zipping()
}
//  class ZippingSpliterator

/*
 *  End of File
 */