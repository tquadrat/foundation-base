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
 *  {@link Spliterator}
 *  that takes elements from a stream while the given
 *  {@link Predicate Predicate}
 *  returns {@code true}.
 *
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TakeWhileSpliterator.java 995 2022-01-23 01:09:35Z tquadrat $
 *  @since 0.0.7
 *
 *  @param  <T> The type of the stream elements.
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: TakeWhileSpliterator.java 995 2022-01-23 01:09:35Z tquadrat $" )
@API( status = INTERNAL, since = "0.0.7" )
public final class TakeWhileSpliterator<T> implements Spliterator<T>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The condition for the spliterator.
     */
    private final Predicate<T> m_Condition;

    /**
     *  Flag that indicates whether the condition still returns {@code true}.
     */
    private boolean m_ConditionHolds = true;

    /**
     *  The source.
     */
    private final Spliterator<T> m_Source;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code TakeWhileSpliterator} instance.
     *
     *  @param  source  The source.
     *  @param  condition   The condition for the new instance.
     */
    private TakeWhileSpliterator( final Spliterator<T> source, final Predicate<T> condition )
    {
        m_Source = requireNonNullArgument( source, "source" );
        m_Condition = requireNonNullArgument( condition, "condition" );
    }   //  TakeWhileSpliterator()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
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
    public final long estimateSize() { return m_ConditionHolds ? m_Source.estimateSize() : 0; }

    /**
     *  Factory method for instances of {@code TakeWhileSpliterator}.
     *
     *  @param  <T> The type of the stream elements.
     *  @param  source  The source.
     *  @param  condition   The condition for the new instance.
     *  @return The new instance.
     */
    @SuppressWarnings( "UseOfConcreteClass" )
    public static final <T> TakeWhileSpliterator<T> over( final Spliterator<T> source, final Predicate<T> condition )
    {
        final var retValue = new TakeWhileSpliterator<>( source, condition );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  over()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean tryAdvance( final Consumer<? super T> action )
    {
        final var retValue = m_ConditionHolds
            && m_Source.tryAdvance( e ->
            {
                if( (m_ConditionHolds = m_Condition.test( e )) == true )
                {
                    action.accept( e );
                }
            });

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  tryAdvance()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Spliterator<T> trySplit(){ return null; }
}
//  class TakeWhileSpliterator

/*
 *  End of File
 */