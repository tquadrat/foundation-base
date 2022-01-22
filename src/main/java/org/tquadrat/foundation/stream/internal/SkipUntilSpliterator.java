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
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  An implementation of
 *  [@link Spliterator}
 *  that skips elements on the stream that do not match the provided condition.
 *
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: SkipUntilSpliterator.java 635 2020-02-04 12:08:02Z tquadrat $
 *  @since 0.0.7
 *
 *  @param  <T> The type over which the given streams stream.
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: SkipUntilSpliterator.java 635 2020-02-04 12:08:02Z tquadrat $" )
@API( status = INTERNAL, since = "0.0.7" )
public final class SkipUntilSpliterator<T> implements Spliterator<T>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The condition.
     */
    private final Predicate<T> m_Condition;

    /**
     *  The flag that indicates whether the condition was met or not.
     */
    private boolean m_ConditionMet = false;

    /**
     *  The source.
     */
    private final Spliterator<T> m_Source;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code SkipUntilSpliterator} instance.
     *
     *  @param  source  The source stream.
     *  @param  condition   The condition to apply to elements of the source
     *      stream.
     */
    private SkipUntilSpliterator( final Spliterator<T> source, final Predicate<T> condition )
    {
        m_Source = requireNonNullArgument( source, "source" );
        m_Condition = requireNonNullArgument( condition, "condition" );
    }   //  SkipUntilSpliterator()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Factory method for instances of {@code SkipUntilSpliterator}-
     *
     *  @param  <T> The type of elements for the source spliterators.
     *  @param  source  The source stream.
     *  @param  condition   The condition to apply to elements of the source
     *      stream.
     *  @return The instance.
     */
    @SuppressWarnings( "UseOfConcreteClass" )
    public static final <T> SkipUntilSpliterator<T> over( final Spliterator<T> source, final Predicate<T> condition )
    {
        final var retValue = new SkipUntilSpliterator<>( source, condition );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  over()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int characteristics()
    {
        final var retValue = m_Source.characteristics() & ~SIZED;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  characteristics()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final long estimateSize() { return m_ConditionMet ? m_Source.estimateSize() : Long.MAX_VALUE; }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void forEachRemaining( final Consumer<? super T> action )
    {
        if( !m_ConditionMet )
        {
            tryAdvance( action );
        }
        if( m_ConditionMet )
        {
            m_Source.forEachRemaining( action );
        }
    }   //  forEachRemaining()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean tryAdvance( final Consumer<? super T> action )
    {
        final boolean retValue;
        if( m_ConditionMet )
        {
            retValue = m_Source.tryAdvance( action );
        }
        else
        {
            while( !m_ConditionMet && m_Source.tryAdvance( e ->
            {
                if( (m_ConditionMet = m_Condition.test( e )) == true )
                {
                    action.accept( e );
                }
            }) )
            { /* Does nothing! */ }
            retValue = m_ConditionMet;
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
//  class SkipUntilSpliterator

/*
 *  End of File
 */