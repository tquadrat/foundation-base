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
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  An implementation of
 *  [@link Spliterator}
 *  that merges streams.
 *
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: MergingSpliterator.java 1031 2022-04-07 22:43:02Z tquadrat $
 *  @since 0.0.7
 *
 *  @param  <T> The type over which the merged streams stream.
 *  @param  <O> The type of the accumulator, over which the constructed
 *      stream streams.
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: MergingSpliterator.java 1031 2022-04-07 22:43:02Z tquadrat $" )
@API( status = INTERNAL, since = "0.0.7" )
public final class MergingSpliterator<T,O> implements Spliterator<O>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The merger function.
     */
    private final BiFunction<O,T,O> m_Merger;

    /**
     *  The sources.
     */
    private final Spliterator<T> [] m_Sources;

    /**
     *  The unit supplier.
     */
    private final Supplier<O> m_UnitSupplier;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code MergingSpliterator} instance.
     *
     *  @param  sources The sources.
     *  @param  unitSupplier    Supplies the initial &quot;zero&quot; or
     *      &quot;unit&quot; value for the accumulator.
     *  @param  merger  Merges each item from the collection of values taken
     *      from the source streams into the accumulator value.
     */
    private MergingSpliterator( final Spliterator<T> [] sources, final Supplier<O> unitSupplier, final BiFunction<O,T,O> merger )
    {
        m_Sources = requireNonNullArgument( sources, "sources" );
        m_UnitSupplier = requireNonNullArgument( unitSupplier, "unitSupplier" );
        m_Merger = requireNonNullArgument( merger, "merger" );
    }   //  MergingSpliterator()

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
        final long retValue = Stream.of( m_Sources )
            .map( Spliterator::estimateSize )
            .max( Long::compare )
            .orElse( 0L );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  estimateSize()

    /**
     *  Factory method for instances of {@code MergingSpliterator}.
     *
     *  @param  <O> The type of the elements in the resulting spliterator.
     *  @param  <T> The type of the elements in the source spliterators.
     *  @param  sources The sources.
     *  @param  unitSupplier    Supplies the initial &quot;zero&quot; or
     *      &quot;unit&quot; value for the accumulator.
     *  @param  merger  Merges each item from the collection of values taken
     *      from the source streams into the accumulator value.
     *  @return The new instance.
     */
    public static final <T,O> Spliterator<O> merging( final Spliterator<T> [] sources, final Supplier<O> unitSupplier, final BiFunction<O,T,O> merger )
    {
        final Spliterator<O> retValue = new MergingSpliterator<>( sources, unitSupplier, merger );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  merging()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean tryAdvance( final Consumer<? super O> action )
    {
        final List<T> mergeables = new ArrayList<>( m_Sources.length );
        Stream.of( m_Sources )
            .forEach( spliterator -> spliterator.tryAdvance( mergeables::add ) );

        final var retValue = !mergeables.isEmpty();
        if( retValue )
        {
            final var unit = m_UnitSupplier.get();

            //---* We never do this in parallel, so fuck it *------------------
            action.accept( mergeables.stream().reduce( unit, m_Merger, (l1,l2) -> l1 ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  tryAdvance()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Spliterator<O> trySplit() { return null; }
}
//  class MergingSpliterator

/*
 *  End of File
 */