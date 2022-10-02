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
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.UtilityClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;
import org.tquadrat.foundation.lang.Objects;

/**
 *  Some useful implementations of the
 *  {@link Selector}
 *  interface.
 *
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Selectors.java 1031 2022-04-07 22:43:02Z tquadrat $
 *  @since 0.0.7
 *
 *  @UMLGraph.link
 */
@UtilityClass
@ClassVersion( sourceVersion = "$Id: Selectors.java 1031 2022-04-07 22:43:02Z tquadrat $" )
public final class Selectors
{
        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance allowed for this class!
     */
    private Selectors() { throw new PrivateConstructorForStaticClassCalledError( Selectors.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns a &quot;Round Robin&quot;
     *  {@link Selector}
     *  implementation. It will return always the next index for the given
     *  array where the respective value is not {@code null}, or {@code null}
     *  if the array contains only {@code null} values.
     *
     *  @param  <T> The type of the values to select from.
     *  @return The selector.
     */
    @SuppressWarnings( "OverlyComplexAnonymousInnerClass" )
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Selector<T> roundRobin()
    {
        @SuppressWarnings( "AnonymousInnerClass" )
        final Selector<T> retValue = new Selector<>()
        {
            /**
             *  The start index.
             */
            private int m_StartIndex = 0;

            /**
             *  {@inheritDoc}
             *
             *  @return The array index of the selected value, or {@code null}
             *      if all values in the provided array are {@code null}.
             */
            @SuppressWarnings( "MethodWithMultipleReturnPoints" )
            @Override
            public final Integer apply( final T [] options )
            {
                var result = m_StartIndex;
                while( isNull( options [result] ) )
                {
                    result = (result + 1) % options.length;
                    if( (result == m_StartIndex) && isNull( options [result] ) )
                    {
                        //---* All values in options are null *----------------
                        return null;
                    }
                }

                m_StartIndex = (result + 1) % options.length;
                return Integer.valueOf( result );
            }   //  apply()
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  roundRobin()

    /**
     *  Returns a
     *  {@link Selector}
     *  implementation that will always return the index for the greatest
     *  value from the given array, based on the natural order of the values.
     *
     *  @param  <T> The type of the values to select from.
     *  @return The selector.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T extends Comparable<T>> Selector<T> takeMax()
    {
        final Selector<T> retValue = takeMax( Comparator.naturalOrder() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  takeMax()

    /**
     *  Returns a
     *  {@link Selector}
     *  implementation that will always return the index for the greatest
     *  value from the given array, imposed by the given comparator.
     *
     *  @param  <T> The type of the values to select from.
     *  @param  comparator  The comparator.
     *  @return The selector.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Selector<T> takeMax( final Comparator<? super T> comparator )
    {
        final Selector<T> retValue = takeMin( requireNonNullArgument( comparator, "comparator" ).reversed() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  takeMax()

    /**
     *  Returns a
     *  {@link Selector}
     *  implementation that will always return the index for the smallest
     *  value from the given array, based on the natural order of the values.
     *
     *  @param  <T> The type of the values to select from.
     *  @return The selector.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T extends Comparable<T>> Selector<T> takeMin()
    {
        final Selector<T> retValue = takeMin( Comparator.naturalOrder() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  takeMin()

    /**
     *  Returns a
     *  {@link Selector}
     *  implementation that will always return the index for the smallest
     *  value from the given array, imposed by the given comparator.
     *
     *  @param  <T> The type of the values to select from.
     *  @param  comparator  The comparator.
     *  @return The selector.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <T> Selector<T> takeMin( final Comparator<? super T> comparator )
    {
        requireNonNullArgument( comparator, "comparator" );

        @SuppressWarnings( "AnonymousInnerClass" )
        final Selector<T> retValue = new Selector<>()
        {
            /**
             *  The start index.
             */
            private int m_StartIndex = 0;

            /**
             *  {@inheritDoc}
             *
             *  @throws NoSuchElementException  All elements of the given array
             *      are {@code null}.
             */
            @SuppressWarnings( "OptionalGetWithoutIsPresent" )
            @Override
            public final Integer apply( final T [] options )
            {
                final var smallest = Stream.of( options )
                    .filter( Objects::nonNull )
                    .min( comparator )
                    .get();

                var result = m_StartIndex;
                while( isNull( options [result] ) || (comparator.compare( smallest, options [result] ) != 0) )
                {
                    result = (result + 1) % options.length;
                }

                m_StartIndex = (result + 1) % options.length;
                return result;
            }   //  apply()
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  takeMin()
}
//  class Selectors

/*
 *  End of File
 */