/*
 * ============================================================================
 * Copyright © 2002-2023 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 * Licensed to the public under the agreements of the GNU Lesser General Public
 * License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.tquadrat.foundation.stream;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.util.Comparator;
import java.util.Iterator;
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
import org.tquadrat.foundation.annotation.MountPoint;

/**
 *  This class allows to intercept the calls to the methods of
 *  {@link Stream}. The default implementations of the methods will just
 *  delegate to the wrapped stream.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: StreamAdapter.java 995 2022-01-23 01:09:35Z tquadrat $
 *  @since 0.0.7
 *
 *  @param  <T> The type of the stream's elements.
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: StreamAdapter.java 995 2022-01-23 01:09:35Z tquadrat $" )
@API( status = STABLE, since = "0.0.7" )
public class StreamAdapter<T> implements Stream<T>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The wrapped stream.
     */
    private final Stream<T> m_Wrapped;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code StreamAdapter} instance.
     *
     *  @param  wrapped The stream that is wrapped by this adapter.
     */
    public StreamAdapter( final Stream<T> wrapped )
    {
        m_Wrapped = requireNonNullArgument( wrapped, "wrapped" );
    }   //  StreamAdapter()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public boolean allMatch( final Predicate<? super T> predicate ) { return m_Wrapped.allMatch( predicate ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public boolean anyMatch( final Predicate<? super T> predicate ) { return m_Wrapped.anyMatch( predicate ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public void close() { m_Wrapped.close(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public <R,A> R collect( final Collector<? super T,A,R> collector ) { return m_Wrapped.collect( collector ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public <R> R collect( final Supplier<R> supplier, final BiConsumer<R,? super T> accumulator, final BiConsumer<R,R> combiner ) { return m_Wrapped.collect( supplier, accumulator, combiner ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public long count() { return m_Wrapped.count(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> distinct() { return m_Wrapped.distinct(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> dropWhile( final Predicate<? super T> predicate ) { return m_Wrapped.dropWhile( predicate ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> filter( final Predicate<? super T> predicate ) { return m_Wrapped.filter( predicate ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Optional<T> findAny() { return m_Wrapped.findAny(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Optional<T> findFirst() { return m_Wrapped.findFirst(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public <R> Stream<R> flatMap( final Function<? super T,? extends Stream<? extends R>> mapper ) { return m_Wrapped.flatMap( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public DoubleStream flatMapToDouble( final Function<? super T,? extends DoubleStream> mapper ) { return m_Wrapped.flatMapToDouble( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public IntStream flatMapToInt( final Function<? super T,? extends IntStream> mapper ) { return m_Wrapped.flatMapToInt( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public LongStream flatMapToLong( final Function<? super T,? extends LongStream> mapper ) { return m_Wrapped.flatMapToLong( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public void forEach( final Consumer<? super T> action ) { m_Wrapped.forEach( action ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public void forEachOrdered( final Consumer<? super T> action ) { m_Wrapped.forEachOrdered( action ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public boolean isParallel() { return m_Wrapped.isParallel(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Iterator<T> iterator() { return m_Wrapped.iterator(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> limit( final long maxSize ) { return m_Wrapped.limit( maxSize ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public <R> Stream<R> map( final Function<? super T,? extends R> mapper ) { return m_Wrapped.map( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public DoubleStream mapToDouble( final ToDoubleFunction<? super T> mapper ) { return m_Wrapped.mapToDouble( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public IntStream mapToInt( final ToIntFunction<? super T> mapper ) { return m_Wrapped.mapToInt( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public LongStream mapToLong( final ToLongFunction<? super T> mapper ) { return m_Wrapped.mapToLong( mapper ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Optional<T> max( final Comparator<? super T> comparator ) { return m_Wrapped.max( comparator ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Optional<T> min( final Comparator<? super T> comparator ) { return m_Wrapped.min( comparator ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public boolean noneMatch( final Predicate<? super T> predicate ) { return m_Wrapped.noneMatch( predicate ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> onClose( final Runnable closeHandler ) { return m_Wrapped.onClose( closeHandler ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> parallel() { return m_Wrapped.parallel(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> peek( final Consumer<? super T> action ) { return m_Wrapped.peek( action ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Optional<T> reduce( final BinaryOperator<T> accumulator ) { return m_Wrapped.reduce( accumulator ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public T reduce( final T identity, final BinaryOperator<T> accumulator ) { return m_Wrapped.reduce( identity, accumulator ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public <U> U reduce( final U identity, final BiFunction<U,? super T,U> accumulator, final BinaryOperator<U> combiner ) { return m_Wrapped.reduce( identity, accumulator, combiner ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> sequential() { return m_Wrapped.sequential(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> skip( final long n ) { return m_Wrapped.skip( n ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> sorted() { return m_Wrapped.sorted(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> sorted( final Comparator<? super T> comparator ) { return m_Wrapped.sorted( comparator ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Spliterator<T> spliterator() { return m_Wrapped.spliterator(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> takeWhile( final Predicate<? super T> predicate ) { return m_Wrapped.takeWhile( predicate ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Object [] toArray() { return m_Wrapped.toArray(); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public <A> A [] toArray( final IntFunction<A []> generator ) { return m_Wrapped.toArray( generator ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public Stream<T> unordered() { return m_Wrapped.unordered(); }
}
//  class StreamAdapter

/*
 *  End of File
 */