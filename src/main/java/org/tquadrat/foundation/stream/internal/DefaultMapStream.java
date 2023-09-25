/*
 * ============================================================================
 * Copyright © 2014 by Alexis Cartier, Dominic Fox.
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

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.stream.MapStream;

/**
 *  The default implementation for
 *  {@link MapStream MapStream}.
 *
 *  @param  <K> The type of the map keys.
 *  @param  <V> The type of the map values.
 *
 *  @author Alexis Cartier (alexcrt)
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: DefaultMapStream.java 1060 2023-09-24 19:21:40Z tquadrat $
 *  @since 0.0.7
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: DefaultMapStream.java 1060 2023-09-24 19:21:40Z tquadrat $" )
@API( status = INTERNAL, since = "0.0.7" )
public final class DefaultMapStream<K,V> implements MapStream<K,V>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The wrapped stream.
     */
    private final Stream<Entry<K,V>> m_Delegate;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code DefaultMapStream} instance from the provided
     *  stream.
     *
     *  @param  stream  The stream to wrap.
     */
    public DefaultMapStream( final Stream<Entry<K,V>> stream ) { m_Delegate = requireNonNullArgument( stream, "stream" ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean allMatch( final Predicate<? super Entry<K,V>> predicate ) { return m_Delegate.allMatch( predicate ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean anyMatch( final Predicate<? super Entry<K,V>> predicate ) { return m_Delegate.anyMatch( predicate ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void close() { m_Delegate.close(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final <R,A> R collect( final Collector<? super Entry<K,V>,A,R> collector ) { return m_Delegate.collect( collector ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final <R> R collect( final Supplier<R> supplier, final BiConsumer<R,? super Entry<K,V>> accumulator, final BiConsumer<R,R> combiner )
    {
        return m_Delegate.collect( supplier, accumulator, combiner );
    }   //  collect()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final long count() { return m_Delegate.count(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final MapStream<K,V> distinct() { return new DefaultMapStream<>( m_Delegate.distinct() ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final MapStream<K,V> filter( final Predicate<? super Entry<K,V>> predicate )
    {
        return new DefaultMapStream<>( m_Delegate.filter( predicate ) );
    }   //  filter()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Optional<Entry<K,V>> findAny() { return m_Delegate.findAny(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Optional<Entry<K,V>> findFirst() { return m_Delegate.findFirst(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final <R> Stream<R> flatMap( final Function<? super Entry<K,V>,? extends Stream<? extends R>> mapper )
    {
        return m_Delegate.flatMap( mapper );
    }   //  flatMap()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final DoubleStream flatMapToDouble( final Function<? super Entry<K,V>,? extends DoubleStream> mapper )
    {
        return m_Delegate.flatMapToDouble( mapper );
    }   //  flatMapToDouble()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final IntStream flatMapToInt( final Function<? super Entry<K,V>,? extends IntStream> mapper )
    {
        return m_Delegate.flatMapToInt( mapper );
    }   //  flatMapToInt()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final LongStream flatMapToLong( final Function<? super Entry<K,V>,? extends LongStream> mapper )
    {
        return m_Delegate.flatMapToLong( mapper );
    }   //  flatMapToLong()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void forEach( final Consumer<? super Entry<K,V>> action ) { m_Delegate.forEach( action ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final void forEachOrdered( final Consumer<? super Entry<K,V>> action ) { m_Delegate.forEachOrdered( action ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean isParallel() { return m_Delegate.isParallel(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Iterator<Entry<K,V>> iterator() { return m_Delegate.iterator(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final MapStream<K,V> limit( final long maxSize ) { return new DefaultMapStream<>( m_Delegate.limit( maxSize ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final <R> Stream<R> map( final Function<? super Entry<K,V>,? extends R> mapper ) { return m_Delegate.map( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final DoubleStream mapToDouble( final ToDoubleFunction<? super Entry<K,V>> mapper ) { return m_Delegate.mapToDouble( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final IntStream mapToInt( final ToIntFunction<? super Entry<K,V>> mapper ) { return m_Delegate.mapToInt( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final LongStream mapToLong( final ToLongFunction<? super Entry<K,V>> mapper ) { return m_Delegate.mapToLong( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Optional<Entry<K,V>> max( final Comparator<? super Entry<K,V>> comparator ) { return m_Delegate.max( comparator ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Optional<Entry<K,V>> min( final Comparator<? super Entry<K,V>> comparator ) { return m_Delegate.min( comparator ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean noneMatch( final Predicate<? super Entry<K,V>> predicate ) { return m_Delegate.noneMatch( predicate ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final MapStream<K,V> onClose( final Runnable closeHandler ) { return new DefaultMapStream<>( m_Delegate.onClose( closeHandler ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final MapStream<K,V> parallel() { return new DefaultMapStream<>( m_Delegate.parallel() ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final MapStream<K,V> peek( final Consumer<? super Entry<K,V>> action ) { return new DefaultMapStream<>( m_Delegate.peek( action ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Optional<Entry<K,V>> reduce( final BinaryOperator<Entry<K,V>> accumulator ) { return m_Delegate.reduce( accumulator ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Entry<K,V> reduce( final Entry<K,V> identity, final BinaryOperator<Entry<K,V>> accumulator )
    {
        return m_Delegate.reduce( identity, accumulator );
    }   //  reduce()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final <U> U reduce( final U identity, final BiFunction<U,? super Entry<K,V>,U> accumulator, final BinaryOperator<U> combiner )
    {
        return m_Delegate.reduce( identity, accumulator, combiner );
    }   //  reduce()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final MapStream<K,V> sequential() { return new DefaultMapStream<>( m_Delegate.sequential() ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final MapStream<K,V> skip( final long n ) { return new DefaultMapStream<>( m_Delegate.skip( n ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final MapStream<K,V> sorted() { return new DefaultMapStream<>( m_Delegate.sorted() ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final MapStream<K,V> sorted( final Comparator<? super Entry<K,V>> comparator ) { return new DefaultMapStream<>( m_Delegate.sorted( comparator ) ); }

    /**
     *  {@inheritDoc}
     *  @see java.util.stream.BaseStream#spliterator()
     */
    @Override
    public final Spliterator<Entry<K,V>> spliterator() { return m_Delegate.spliterator(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Object [] toArray() { return m_Delegate.toArray(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final <A> A [] toArray( final IntFunction<A []> generator ) { return m_Delegate.toArray( generator ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final MapStream<K,V> unordered() { return new DefaultMapStream<>( m_Delegate.unordered() ); }
}
//  class DefaultMapStream

/*
 *  End of File
 */