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

package org.tquadrat.foundation.stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toMap;
import static org.apiguardian.api.API.Status.STABLE;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.stream.internal.DefaultMapStream;

/**
 *  A stream of
 *  {@link java.util.Map.Entry Map.Entry&lt;K,V&gt;}.
 *
 *  @param  <K> The type of the map keys.
 *  @param  <V> The type of the map values.
 *
 *  @author Alexis Cartier (alexcrt)
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: MapStream.java 635 2020-02-04 12:08:02Z tquadrat $
 *  @since 0.0.7
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "ClassWithTooManyMethods" )
@ClassVersion( sourceVersion = "$Id: MapStream.java 635 2020-02-04 12:08:02Z tquadrat $" )
@API( status = STABLE, since = "0.0.7" )
public interface MapStream<K,V> extends Stream<Entry<K,V>>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns a
     *  {@link Map}
     *  from the stream. If there are identical keys in the stream,
     *  {@link #mergeKeys()}
     *  should be called before. Alternatively,
     *  {@link #collect(BinaryOperator)}
     *  could be used.
     *
     *  @return A map from the values of the stream.
     */
    public default Map<K,V> collect() { return collect( toMap( Entry::getKey, Entry::getValue ) ); }

    /**
     *  Returns a
     *  {@link Map}
     *  from the stream. If there are similar keys in the stream, the merge
     *  function will be applied to merge the values of those keys.
     *
     *  @param  mergeFunction   The function that is used to merge the values
     *      with identical keys.
     *  @return A map from the values of the stream.
     */
    public default Map<K,V> collect( final BinaryOperator<V> mergeFunction ) { return collect( toMap( Entry::getKey, Entry::getValue, mergeFunction ) ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public MapStream<K,V> distinct();

    /**
     *  Returns a stream consisting only of the values of underlying map for
     *  this {@code MapStream}.<br>
     *  <br>This is an intermediate operation.
     *
     * @return The new stream.
     */
    public default Stream<V> dropKeys() { return map( Entry::getValue ); }

    /**
     *  Returns a stream consisting only of the keys of underlying map for
     *  this {@code MapStream}.<br>
     *  <br>This is an intermediate operation.
     *
     * @return The new stream.
     */
    public default Stream<K> dropValues() { return map( Entry::getKey ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public MapStream<K,V> filter( Predicate<? super Entry<K,V>> predicate );

    /**
     *  Returns a stream consisting of the elements of this stream that match
     *  the given predicate.<br>
     *  <br>This is an intermediate operation.
     *
     *  @param  predicate   A non-interfering, stateless predicate to apply to
     *      each element to determine if it should be included.
     *  @return The new stream.
     *
     *  @see #filter(Predicate)
     */
    public default MapStream<K,V> filter( final BiPredicate<? super K, ? super V> predicate )
    {
        return filter( entry -> predicate.test( entry.getKey(), entry.getValue() ) );
    }   //  filter()

    /**
     *  Performs an action for each entry of the
     *  {@link Map}
     *  that was used to construct this stream.<br>
     *  <br>This is a terminal operation.<br>
     *  <br>The behaviour of this operation is explicitly nondeterministic. For
     *  parallel stream pipelines, this operation does <em>not</em> guarantee
     *  to respect the encounter order of the stream, as doing so would
     *  sacrifice the benefit of parallelism. For any given element, the action
     *  may be performed at whatever time and in whatever thread the library
     *  chooses. If the action accesses shared state, it is responsible for
     *  providing the required synchronisation.
     *
     *  @param  action  A non-interfering action to perform on the entries.
     */
    public default void forEach( final BiConsumer<K,V> action )
    {
        forEach( entry -> action.accept( entry.getKey(), entry.getValue() ) );
    }   //  forEach()

    /**
     * Returns a {@code MapStream} from which the key and values are reversed.
     *
     * @return A new map stream.
     */
    public default MapStream<V,K> inverseMapping()
    {
        final MapStream<V,K> retValue = new DefaultMapStream<>( map( e -> new SimpleImmutableEntry<>( e.getValue(), e.getKey() ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  inverseMapping()

    /**
     *  {@inheritDoc}
     */
    @Override
    public MapStream<K,V> limit( long n );

    /**
     *  Returns a stream consisting of the results of applying the given
     *  function to the elements of this stream.<br>
     *  <br>This is an intermediate operation.
     *
     *  @param  <R> The element type of the new stream.
     *  @param  mapper  A non-interfering, stateless function to apply to each
     *      element.
     * @return The new stream.
     */
    public default <R> Stream<R> map( final BiFunction<K,V,? extends R> mapper )
    {
        return map( e -> mapper.apply( e.getKey(), e.getValue() ) );
    }   //  map()

    /**
     *  <p>{@summary Applies the mapping for each key and value in the
     *  map.}</p>
     *  <p>If the mapping function is not bijective for the keys,
     *  {@link #mergeKeys()}
     *  or
     *  {@link #mergeKeys(BinaryOperator)}
     *  needs to be called before, or a merge function has to be provided
     *  when calling
     *  {@link #collect(BinaryOperator) collect()}.</p>
     *
     *  @param  <K1> The type of the map keys.
     *  @param  <V1> The type of the map values.
     *  @param  keyMapper   The key mapping to be applied.
     *  @param  valueMapper The value mapping to be applied.
     *  @return A new map stream.
     */
    public default <K1,V1> MapStream<K1,V1> mapEntries( final Function<? super K,? extends K1> keyMapper, final Function<? super V,? extends V1> valueMapper )
    {
        final MapStream<K1,V1> retValue = new DefaultMapStream<>( map( e -> new SimpleImmutableEntry<>( keyMapper.apply( e.getKey() ), valueMapper.apply( e.getValue() ) ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  mapEntries()

    /**
     *  Applies the mapping for each key in the map. If the mapping function is
     *  not bijective,
     *  {@link #mergeKeys()}
     *  or
     *  {@link #mergeKeys(BinaryOperator)}
     *  needs to be called before, or a merge function has to be provided
     *  when calling
     *  {@link #collect(BinaryOperator) collect()}.
     *
     *  @param  <K1> The type of the map keys.
     *  @param  mapper  The key mapping to be applied.
     *  @return A new map stream.
     */
    public default <K1> MapStream<K1,V> mapKeys( final Function<? super K,? extends K1> mapper )
    {
        final MapStream<K1,V> retValue = new DefaultMapStream<>( map( e -> new SimpleImmutableEntry<>( mapper.apply( e.getKey() ), e.getValue() ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  mapKeys()

    /**
     *  Applies the mapping for each value in the map.
     *
     *  @param  <V1> The type of the map values.
     *  @param  mapper  The value mapping to be applied.
     *  @return A new map stream.
     */
    public default <V1> MapStream<K,V1> mapValues( final Function<? super V,? extends V1> mapper )
    {
        final MapStream<K,V1> retValue = new DefaultMapStream<>( map( e -> new SimpleImmutableEntry<>( e.getKey(), mapper.apply( e.getValue() ) ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  mapValues()

    /**
     *  Merges the keys of the stream into a new stream.
     *
     *  @return A new map stream.
     */
    public default MapStream<K,List<V>> mergeKeys() { return of( collect( groupingBy( Entry::getKey, mapping( Entry::getValue, Collectors.toList() ) ) ) ); }

    /**
     *  Merges keys of the stream into a new stream with the provided merge
     *  function.
     *
     *  @param  mergeFunction   The merge function.
     *  @return A new map stream.
     */
    public default MapStream<K,V> mergeKeys( final BinaryOperator<V> mergeFunction ) { return of( collect( mergeFunction ) ); }

    /**
     *  Factory for a {@code MapStream<K,V>}; constructs a stream from a single
     *  key-value pair.
     *
     *  @param  <K> The type of the map keys.
     *  @param  <V> The type of the map values.
     *  @param  key The key.
     *  @param  value   The value.
     *  @return A new {@code MapStream<K,V>}.
     */
    @API( status = STABLE, since = "0.0.4" )
    public static <K,V> MapStream<K,V> of( final K key, final V value )
    {
        final MapStream<K,V> retValue = new DefaultMapStream<>( Stream.of( new SimpleImmutableEntry<>( key, value ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  of()

    /**
     *  Factory for a {@code MapStream<K,V>}; constructs a stream from two
     *  key-value pairs
     *
     *  @param  <K> The type of the map keys.
     *  @param  <V> The type of the map values.
     *  @param  key1    The first key.
     *  @param  value1  The first value.
     *  @param  key2    The second key.
     *  @param  value2  The second value.
     *  @return A new {@code MapStream<K,V>}.
     */
    @API( status = STABLE, since = "0.0.4" )
    public static <K,V> MapStream<K,V> of( final K key1, final V value1, final K key2, final V value2 )
    {
        final MapStream<K,V> retValue = new DefaultMapStream<>(
            Stream.of(
                new SimpleImmutableEntry<>( key1, value1 ),
                new SimpleImmutableEntry<>( key2, value2 ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  of()

    /**
     *  Factory for a {@code MapStream<K,V>}; constructs a stream from three
     *  key-value pairs
     *
     *  @param  <K> The type of the map keys.
     *  @param  <V> The type of the map values.
     *  @param  key1    The first key.
     *  @param  value1  The first value.
     *  @param  key2    The second key.
     *  @param  value2  The second value.
     *  @param  key3    The third key.
     *  @param  value3  The third value.
     *  @return A new {@code MapStream<K,V>}.
     */
    @API( status = STABLE, since = "0.0.4" )
    public static <K,V> MapStream<K,V> of( final K key1, final V value1, final K key2, final V value2, final K key3, final V value3 )
    {
        final MapStream<K,V> retValue = new DefaultMapStream<>(
            Stream.of(
                new SimpleImmutableEntry<>( key1, value1 ),
                new SimpleImmutableEntry<>( key2, value2 ),
                new SimpleImmutableEntry<>( key3, value3 ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  of()

    /**
     *  Factory for a {@code MapStream<K,V>}; constructs a stream from four
     *  key-value pairs
     *
     *  @param  <K> The type of the map keys.
     *  @param  <V> The type of the map values.
     *  @param  key1    The first key.
     *  @param  value1  The first value.
     *  @param  key2    The second key.
     *  @param  value2  The second value.
     *  @param  key3    The third key.
     *  @param  value3  The third value.
     *  @param  key4    The third key.
     *  @param  value4  The third value.
     *  @return A new {@code MapStream<K,V>}.
     */
    @API( status = STABLE, since = "0.0.4" )
    public static <K,V> MapStream<K,V> of( final K key1, final V value1, final K key2, final V value2, final K key3, final V value3, final K key4, final V value4 )
    {
        final MapStream<K,V> retValue = new DefaultMapStream<>(
            Stream.of(
                new SimpleImmutableEntry<>( key1, value1 ),
                new SimpleImmutableEntry<>( key2, value2 ),
                new SimpleImmutableEntry<>( key3, value3 ),
                new SimpleImmutableEntry<>( key4, value4 ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  of()

    /**
     *  Factory for a {@code MapStream<K,V>}; constructs a stream from five
     *  key-value pairs
     *
     *  @param  <K> The type of the map keys.
     *  @param  <V> The type of the map values.
     *  @param  key1    The first key.
     *  @param  value1  The first value.
     *  @param  key2    The second key.
     *  @param  value2  The second value.
     *  @param  key3    The third key.
     *  @param  value3  The third value.
     *  @param  key4    The third key.
     *  @param  value4  The third value.
     *  @param  key5    The third key.
     *  @param  value5  The third value.
     *  @return A new {@code MapStream<K,V>}.
     */
    @API( status = STABLE, since = "0.0.4" )
    public static <K,V> MapStream<K,V> of( final K key1, final V value1, final K key2, final V value2, final K key3, final V value3, final K key4, final V value4, final K key5, final V value5 )
    {
        final MapStream<K,V> retValue = new DefaultMapStream<>(
            Stream.of(
                new SimpleImmutableEntry<>( key1, value1 ),
                new SimpleImmutableEntry<>( key2, value2 ),
                new SimpleImmutableEntry<>( key3, value3 ),
                new SimpleImmutableEntry<>( key4, value4 ),
                new SimpleImmutableEntry<>( key5, value5 ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  of()

    /**
     *  Factory for a {@code MapStream<K,V>}; constructs a stream from some
     *  key-value pairs
     *
     *  @param  <K> The type of the map keys.
     *  @param  <V> The type of the map values.
     *  @param  nvps    The key-value-pairs.
     *  @return A new {@code MapStream<K,V>}.
     */
    @API( status = STABLE, since = "0.0.4" )
    @SafeVarargs
    public static <K,V> MapStream<K,V> of( final Map.Entry<K,V>... nvps )
    {
        final MapStream<K,V> retValue = new DefaultMapStream<>( Arrays.stream( nvps ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  of()

    /**
     *  Factory for a {@code MapStream<K,V>}; constructs a stream from a
     *  {@link Collection}
     *  of key-value pairs
     *
     *  @param  <K> The type of the map keys.
     *  @param  <V> The type of the map values.
     *  @param  nvps    The key-value-pairs.
     *  @return A new {@code MapStream<K,V>}.
     */
    @API( status = STABLE, since = "0.0.4" )
    public static <K,V> MapStream<K,V> of( final Collection<Map.Entry<K,V>> nvps )
    {
        final MapStream<K,V> retValue = new DefaultMapStream<>( nvps.stream() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  of()

    /**
     *  Factory for a {@code MapStream<K,V>}; constructs a stream from an
     *  instance of
     *  {@link Map}.
     *
     *  @param  <K> The type of the map keys.
     *  @param  <V> The type of the map values.
     *  @param  map The map to build the stream from.
     *  @return A new {@code MapStream<K,V>}.
     */
    @API( status = STABLE, since = "0.0.4" )
    public static <K,V> MapStream<K,V> of( final Map<K,V> map ) { return new DefaultMapStream<>( map.entrySet().stream() ); }

    /**
     *  Factory for a {@code MapStream<K,V>}; constructs a stream from several
     *  instances of
     *  {@link Map}.
     *
     *  @param  <K> The type of the map keys.
     *  @param  <V> The type of the map values.
     *  @param  maps    The maps to build the stream from.
     *  @return A new {@code MapStream<K,V>}.
     */
    @API( status = STABLE, since = "0.0.4" )
    @SafeVarargs
    public static <K,V> MapStream<K,V> ofMaps( final Map<K,V>... maps )
    {
        final MapStream<K,V> retValue = new DefaultMapStream<>( Stream.of( maps ).flatMap( m -> m.entrySet().stream() ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  ofMaps()

    /**
     *  {@inheritDoc}
     */
    @Override
    public MapStream<K,V> onClose( Runnable closeHandler );

    /**
     *  {@inheritDoc}
     */
    @Override
    public MapStream<K,V> parallel();

    /**
     *  {@inheritDoc}
     */
    @Override
    public MapStream<K,V> peek( Consumer<? super Entry<K,V>> action );

    /**
     *  {@inheritDoc}
     */
    @Override
    public MapStream<K,V> sequential();

    /**
     *  {@inheritDoc}
     */
    @Override
    public MapStream<K,V> skip( long n );

    /**
     *  {@inheritDoc}
     */
    @Override
    public MapStream<K,V> sorted();

    /**
     *  {@inheritDoc}
     */
    @Override
    public MapStream<K,V> sorted( Comparator<? super Entry<K,V>> comparator );

    /**
     *  {@inheritDoc}
     */
    @Override
    public MapStream<K,V> unordered();
}
//  interface MapStream

/*
 *  End of File
 */