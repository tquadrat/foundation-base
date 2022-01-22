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
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.internal.SharedFormatter.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.BaseStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.UtilityClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;
import org.tquadrat.foundation.exception.ValidationException;
import org.tquadrat.foundation.stream.internal.AggregatingSpliterator;
import org.tquadrat.foundation.stream.internal.InterleavingSpliterator;
import org.tquadrat.foundation.stream.internal.MergingSpliterator;
import org.tquadrat.foundation.stream.internal.SkipUntilSpliterator;
import org.tquadrat.foundation.stream.internal.TakeWhileSpliterator;
import org.tquadrat.foundation.stream.internal.UnfoldSpliterator;
import org.tquadrat.foundation.stream.internal.ZippingSpliterator;

/**
 *  Utility class providing static methods for performing various operations on
 *  Streams.
 *
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: StreamUtils.java 635 2020-02-04 12:08:02Z tquadrat $
 *  @since 0.0.7
 *
 *  @UMLGraph.link
 */
@UtilityClass
@ClassVersion( sourceVersion = "$Id: StreamUtils.java 635 2020-02-04 12:08:02Z tquadrat $" )
public final class StreamUtils
{
        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance allowed for this class.
     */
    private StreamUtils() { throw new PrivateConstructorForStaticClassCalledError( StreamUtils.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Aggregates items from source stream into list of items while supplied
     *  predicate is {@code true} when evaluated on previous and current
     *  item.<br>
     *  <br>Can by seen as streaming alternative to
     *  {@link java.util.stream.Collectors#groupingBy(Function) Collectors.groupingBy()}
     *  when source stream is sorted by key.
     *
     *  @param  <T> The element type of the stream.
     *  @param  source  The source stream.
     *  @param  predicate   The predicate specifying boundary between groups of
     *      items.
     *  @return A
     *      {@link Stream}
     *      of
     *      {@link java.util.List List&lt;T&gt;}
     *      aggregated according to the provided predicate.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<List<T>> aggregate( final Stream<T> source, final BiPredicate<? super T, ? super T> predicate )
    {
        final var retValue = StreamSupport.stream
        (
            new AggregatingSpliterator<>
            (
                requireNonNullArgument( source, "source" ).spliterator(),
                (a,e) -> a.isEmpty() || requireNonNullArgument( predicate, "predicate" ).test( a.get( a.size() - 1 ), e )
            ),
            false
        );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  aggregate()

    /**
     *  Aggregates items from the source stream into a list of items with fixed
     *  size.
     *
     *  @param  <T> The element type of the stream.
     *  @param  source  The source stream.
     *  @param  size    The size of the aggregated list.
     *  @return A
     *      {@link Stream}
     *      of
     *      {@link java.util.List List&lt;T&gt;}
     *      with all lists of {@code size} with the possible exception of the
     *      last <code>List&lt;T&gt;</code>.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<List<T>> aggregate( final Stream<T> source, final int size )
    {
        if( size <= 0 )
        {
            throw new ValidationException( format( "Positive value expected for the size; it is %1$d", size ) );
        }

        final var retValue = StreamSupport.stream( new AggregatingSpliterator<>( requireNonNullArgument( source, "source" ).spliterator(), (a,e) -> a.size() < size ), false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  aggregate()

    /**
     *  Aggregates items from source stream. Similar to
     *  {@link #aggregate(Stream, BiPredicate)},
     *  but uses different predicate, evaluated on all items aggregated so far
     *  and next item from source stream.
     *
     *  @param  <T> The element type of the stream.
     *  @param  source  The source stream.
     *  @param  predicate   The predicate specifying boundary between groups of
     *      items.
     *  @return  A
     *      {@link Stream}
     *      of
     *      {@link java.util.List List&lt;T&gt;}
     *      aggregated according to the provided predicate.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<List<T>> aggregateOnListCondition( final Stream<T> source, final BiPredicate<List<T>,T> predicate )
    {
        final var retValue = StreamSupport.stream( new AggregatingSpliterator<>( requireNonNullArgument( source, "source" ).spliterator(), requireNonNullArgument( predicate, "predicate" ) ), false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  aggregateOnListCondition()

    /**
     *  Constructs an infinite (although in practice bounded by
     *  {@link Long#MAX_VALUE})
     *  stream of longs {@code 0, 1, 2, 3 ...} for use as indices.
     *
     *  @return A stream of longs.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static LongStream indices()
    {
        final var retValue = LongStream.iterate( 0L, l -> l + 1 );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  indices()

    /**
     *  Constructs a stream which interleaves the supplied streams, picking
     *  items using the supplied selector function.<br>
     *  <br>The selector function will be passed an array containing one value
     *  from each stream, or {@code null} if that stream has no more values,
     *  and must return the integer index of the value to accept. That value
     *  will become part of the interleaved stream, and the source stream at
     *  that index will advance to the next value.<br>
     *  <br>See the
     *  {@link Selectors}
     *  class for ready-made selectors for round-robin and sorted item
     *  selection.
     *
     *  @param  <T> The type over which the interleaved streams stream.
     *  @param  selector    The selector function to use.
     *  @param  streams The streams to interleave.
     *  @return An interleaved stream.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<T> interleave( final Selector<T> selector, final List<? extends Stream<T>> streams )
    {
        requireNonNullArgument( selector, "selector" );

        @SuppressWarnings( "unchecked" )
        final Spliterator<T> [] spliterators = requireNonNullArgument( streams, "streams" ).stream()
            .map( BaseStream::spliterator )
            .toArray( Spliterator []::new );
        final var retValue = StreamSupport.stream( InterleavingSpliterator.interleaving( spliterators, selector ), false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  interleave()

    /**
     *  Constructs a stream which interleaves the supplied streams, picking
     *  items using the supplied selector function.<br>
     *  <br>The selector function will be passed an array containing one value
     *  from each stream, or {@code null} if that stream has no more values,
     *  and must return the integer index of the value to accept. That value
     *  will become part of the interleaved stream, and the source stream at
     *  that index will advance to the next value.<br>
     *  <br>See the
     *  {@link Selectors}
     *  class for ready-made selectors for round-robin and sorted item
     *  selection.
     *
     *  @param  <T> The type over which the interleaved streams stream.
     *  @param  selector    The selector function to use.
     *  @param  streams The streams to interleave.
     *  @return An interleaved stream.
     */
    @API( status = STABLE, since = "0.0.7" )
    @SafeVarargs
    public static <T> Stream<T> interleave( final Selector<T> selector, final Stream<T>... streams )
    {
        requireNonNullArgument( selector, "selector" );

        @SuppressWarnings( "unchecked" )
        final Spliterator<T> [] spliterators = Stream.of( requireNonNullArgument( streams, "streams" ) )
            .map( BaseStream::spliterator )
            .toArray( Spliterator []::new );
        final var retValue = StreamSupport.stream( InterleavingSpliterator.interleaving( spliterators, selector ), false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  interleave()

    /**
     *  Constructs a stream which merges together values from the supplied
     *  streams, somewhat in the manner of the stream constructed by
     *  {@link #zip(java.util.stream.BaseStream, java.util.stream.BaseStream, java.util.function.BiFunction)},
     *  but for an arbitrary number of streams and using a merger to merge the
     *  values from multiple streams into an accumulator.
     *
     *  @param  <T> The type over which the merged streams stream.
     *  @param  <O> The type of the accumulator, over which the constructed
     *      stream streams.
     *  @param  unitSupplier    Supplies the initial &quot;zero&quot; or
     *      &quot;unit&quot; value for the accumulator.
     *  @param  merger  Merges each item from the collection of values taken
     *      from the source streams into the accumulator value.
     *  @param  streams The streams to merge.
     *  @return A merging stream.
     */
    @API( status = STABLE, since = "0.0.7" )
    @SafeVarargs
    public static <T,O> Stream<O> merge( final Supplier<O> unitSupplier, final BiFunction<O,T,O> merger, final Stream<T>... streams )
    {
        requireNonNullArgument( unitSupplier, "unitSupplier" );
        requireNonNullArgument( merger, "merger" );

        @SuppressWarnings( "unchecked" )
        final Spliterator<T> [] spliterators = Stream.of( requireNonNullArgument( streams, "streams" ) )
            .map( BaseStream::spliterator )
            .toArray( Spliterator []::new );
        final var retValue = StreamSupport.stream( MergingSpliterator.merging( spliterators, unitSupplier, merger ), false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   // merge()

    /**
     *  Constructs a stream which merges together values from the supplied
     *  streams into lists of values, somewhat in the manner of the stream
     *  constructed by
     *  {@link #zip(java.util.stream.BaseStream, java.util.stream.BaseStream, java.util.function.BiFunction)},
     *  but for an arbitrary number of streams.
     *
     *  @param  <T> The type over which the merged streams stream.
     *  @param  streams The streams to merge.
     *  @return A merging stream of lists of {@code T}.
     */
    @API( status = STABLE, since = "0.0.7" )
    @SafeVarargs
    public static <T> Stream<List<T>> mergeToList( final Stream<T>... streams )
    {
        final Stream<List<T>> retValue = merge( ArrayList::new, (l,x) ->
        {
            l.add( x );
            return l;
        }, streams );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  mergeToList()

    /**
     *  Filters with the condition negated. Will throw away any members of the
     *  source stream that match the condition.
     *
     *  @param  <T> The type over which the stream streams.
     *  @param  source  The source stream.
     *  @param  predicate   The filter condition.
     * @return A rejecting stream.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<T> reject( final Stream<T> source, final Predicate<? super T> predicate )
    {
        final var retValue = source.filter( predicate.negate() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  reject()

    /**
     *  Constructs a stream which skips values from the source stream for as
     *  long as they do not meet the supplied condition, then streams every
     *  remaining value as soon as the first value is found which does meet the
     *  condition.
     *
     *  @param  <T> The type over which the stream streams.
     *  @param  source  The source stream.
     *  @param  condition   The condition to apply to elements of the source
     *      stream.
     * @return An element-skipping stream.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<T> skipUntil( final BaseStream<T, Stream<T>> source, final Predicate<T> condition )
    {
        final var retValue = StreamSupport.stream( SkipUntilSpliterator.over( source.spliterator(), condition), false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  skipUntil()

    /**
     *  Constructs a stream which skips values from the source stream for as
     *  long as they meet the supplied condition, then streams every remaining
     *  value as soon as the first value is found which does not meet the
     *  condition.
     *
     *  @param  <T> The type over which the stream streams.
     *  @param  source  The source stream.
     *  @param  condition   The condition to apply to elements of the source
     *      stream.
     *  @return An element-skipping stream.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<T> skipWhile( final BaseStream<T, Stream<T>> source, final Predicate<T> condition )
    {
        final var retValue = StreamSupport.stream( SkipUntilSpliterator.over( source.spliterator(), condition.negate() ), false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  skipWhile()

    /**
     *  Construct a stream which takes values from the source stream until one
     *  of them meets the supplied condition, and then stops.
     *
     *  @param  <T> The type over which the stream streams.
     *  @param  source  The source stream.
     *  @param  condition   The condition to apply to elements of the source
     *      stream.
     *  @return A condition-bounded stream.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<T> takeUntil( final BaseStream<T, Stream<T>> source, final Predicate<T> condition )
    {
        final var retValue = takeWhile( source, condition.negate() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  takeUntil()

    /**
     *  Construct a stream which takes values from the source stream for as
     *  long as they meet the supplied condition, and stops as soon as a value
     *  is encountered which does not meet the condition.
     *
     *  @param  <T> The type over which the stream streams.
     *  @param  source  The source stream.
     *  @param  condition   The condition to apply to elements of the source
     *      stream.
     * @return A condition-bounded stream.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<T> takeWhile( final BaseStream<T, Stream<T>> source, final Predicate<T> condition )
    {
        final var retValue = StreamSupport.stream( TakeWhileSpliterator.over( source.spliterator(), condition ), false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  takeWhile()

    /**
     *  Taps a stream so that as each item in the stream is released from the
     *  underlying spliterator, it is also sent to the tap.
     *
     *  @param  <T> The type over which the stream streams.
     *  @param  source  The source stream.
     *  @param  tap The tap which will consume each item that passes through
     *      the stream.
     * @return A tapped stream.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<T> tap( final Stream<T> source, final Consumer<? super T> tap )
    {
        final var retValue = source.peek( tap );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  tap()

    /**
     *  Constructs a stream which takes the seed value and applies the
     *  generator to create the next value, feeding each new value back into
     *  the generator to create subsequent values. If the generator returns
     *  {@link Optional#empty()},
     *  then the stream has no more values.
     *
     *  @param  <T> The type over which the stream streams.
     *  @param  seed    The seed value.
     *  @param  generator   The generator to use to create new values.
     *  @return An unfolding stream.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<T> unfold( final T seed, final Function<T,Optional<T>> generator )
    {
        final var retValue = StreamSupport.stream( UnfoldSpliterator.over( seed, generator ), false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  unfold()

    /**
     *  Zips together the &quot;left&quot; and &quot;right&quot; streams until
     *  either runs out of values.<br>
     *  <br>Each pair of values is combined into a single value using the
     *  supplied combiner function.
     *
     *  @param  <L> The type over which the &quot;left&quot; stream is
     *      streaming.
     *  @param  <R> The type over which the &quot;right&quot; stream is
     *      streaming.
     *  @param  <O> The type created by the combiner out of pairs of
     *      &quot;left&quot; and &quot;right&quot; values, over which the
     *      resulting stream streams.
     *  @param  lefts   The &quot;left&quot; stream to zip.
     *  @param  rights  The &quot;right&quot; stream to zip.
     *  @param  combiner    The function to combine &quot;left&quot; and
     *      &quot;right&quot; values.
     *  @return A stream of zipped values.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <L,R,O> Stream<O> zip( final BaseStream<L, Stream<L>> lefts, final BaseStream<R, Stream<R>> rights, final BiFunction<L,R,O> combiner )
    {
        final var retValue = StreamSupport.stream( ZippingSpliterator.zipping( lefts.spliterator(), rights.spliterator(), combiner ), false );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  zip()

    /**
     *  Zips the source stream together with the stream of indices to provide a
     *  stream of indexed values.
     *
     *  @param <T> The type over which the source stream is streaming.
     *  @param  source  The source stream.
     *  @return A stream of indexed values.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Stream<Indexed<T>> zipWithIndex( final BaseStream<T, Stream<T>> source )
    {
        final var retValue = zip( indices().boxed(), source, Indexed::index );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  zipWithIndex()
}
//  class StreamUtils

/*
 *  End of File
 */