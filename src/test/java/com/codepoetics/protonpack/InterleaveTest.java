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

package com.codepoetics.protonpack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.stream.Selectors;
import org.tquadrat.foundation.stream.StreamUtils;

@ClassVersion( sourceVersion = "$Id: InterleaveTest.java 995 2022-01-23 01:09:35Z tquadrat $" )
@DisplayName( "com.codepoetics.protonpack.InterleaveTest" )
public class InterleaveTest
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    @SuppressWarnings( "static-method" )
    @Test
    public final void round_robin_interleaving()
    {
        final var streamA = Stream.of( "Peter", "Paul", "Mary" );
        final var streamB = Stream.of( "A", "B", "C", "D", "E" );
        final var streamC = Stream.of( "foo", "bar", "baz", "xyzzy" );

        final var interleaved = StreamUtils.interleave( Selectors.roundRobin(), streamA, streamB, streamC );

        assertThat( interleaved.collect( Collectors.toList() ), contains(
                "Peter", "A", "foo",
                "Paul", "B", "bar",
                "Mary", "C", "baz",
                "D", "xyzzy",
                "E" ) );
    }   //  round_robin_interleaving()

    @SuppressWarnings( "static-method" )
    @Test
    public final void sorted_interleaving()
    {
        final var streamA = Stream.of( "Peter", "B", "xyzzy" );
        final var streamB = Stream.of( "A", "Paul", "C", "baz", "E" );
        final var streamC = Stream.of( "foo", "bar", "D", "Mary" );

        final var interleaved = StreamUtils.interleave( Selectors.takeMin(), streamA.sorted(), streamB.sorted(), streamC.sorted() );

        final var collected = interleaved.collect( Collectors.toList() );

        assertThat( collected, contains( "A", "B", "C", "D", "E", "Mary", "Paul", "Peter", "bar", "baz", "foo", "xyzzy" ) );
    }   //  sorted_interleaving()

    @SuppressWarnings( "static-method" )
    @Test
    public final void prioritised_interleaving()
    {
        final var streamA = Stream.of( "1 A1", "1 A2", "2 A3" );
        final var streamB = Stream.of( "2 B1", "1 B2", "1 B3" );

        final var priority = (Function<String, Integer>) s -> Integer.valueOf( s.substring( 0, 1 ) );
        final var interleaved = StreamUtils.interleave( Selectors.takeMax( Comparator.comparing( priority ) ), streamA, streamB );

        final var collected = interleaved.collect( Collectors.toList() );

        assertThat( collected, contains( "2 B1", "1 A1", "1 B2", "1 A2", "2 A3", "1 B3" ) );
    }   //  prioritised_interleaving()
}
//  class InterleaveTest

/*
 *  End of File
 */