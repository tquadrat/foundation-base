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

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  An implementation of
 *  {@link Spliterator}
 *  that aggregates the elements of the stream based on a provided
 *  {@link BiPredicate Predicate}.
 *
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: AggregatingSpliterator.java 995 2022-01-23 01:09:35Z tquadrat $
 *  @since 0.0.7
 *
 *  @param  <I> The type of the stream elements.
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: AggregatingSpliterator.java 995 2022-01-23 01:09:35Z tquadrat $" )
@API( status = INTERNAL, since = "0.0.7" )
public class AggregatingSpliterator<I> implements Spliterator<List<I>>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The predicate.
     */
    private final BiPredicate<List<I>,I> m_Condition;

    /**
     *  The current slide.
     */
    private List<I> m_CurrentSlide = new ArrayList<>();

    /**
     *  The source.
     */
    private final Spliterator<I> m_Source;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code AggregatingSpliterator} instance.
     *
     *  @param  source  The source.
     *  @param  predicate   The predicate.
     */
    public AggregatingSpliterator( final Spliterator<I> source, final BiPredicate<List<I>,I> predicate )
    {
        m_Source = requireNonNullArgument( source, "source" );
        m_Condition = requireNonNullArgument( predicate, "predicate" );
    }   //  AggregatingSpliterator()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final int characteristics()
    {
        final var retValue = m_Source.characteristics() & ~SIZED & ~CONCURRENT;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  characteristics()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final long estimateSize() { return m_Source.estimateSize(); }

    /**
     *  Checks whether the current element is on the current slide.
     *
     *  @param  currentElement  The current element.
     *  @return {@code true} if the current element is on the current slide.
     */
    private boolean isSameSlide( final I currentElement )
    {
        return m_CurrentSlide.isEmpty() || m_Condition.test( m_CurrentSlide, currentElement );
    }   //  isSameSlide()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean tryAdvance( final Consumer<? super List<I>> action )
    {
        final var retValue = m_Source.tryAdvance( currentElement ->
        {
            if( !isSameSlide( currentElement ) )
            {
                action.accept( m_CurrentSlide );
                m_CurrentSlide = new ArrayList<>();
            }
            m_CurrentSlide.add( currentElement );
        });

        if( !retValue && !m_CurrentSlide.isEmpty() )
        {
            action.accept( m_CurrentSlide );
            m_CurrentSlide = new ArrayList<>();
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  tryAdvance()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Spliterator<List<I>> trySplit() { return null; }
}
//  class AggregatingSpliterator

/*
 *  End of File
 */