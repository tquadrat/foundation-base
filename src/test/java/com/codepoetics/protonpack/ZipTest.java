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

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.stream.Indexed;
import org.tquadrat.foundation.stream.StreamUtils;

@ClassVersion( sourceVersion = "$Id: ZipTest.java 1118 2024-03-15 16:14:15Z tquadrat $" )
@DisplayName( "com.codepoetics.protonpack.ZipTest" )
public class ZipTest
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    @SuppressWarnings( "static-method" )
    @Test
    public final void zips_a_pair_of_streams_of_same_length()
    {
        final var streamA = Stream.of( "A", "B", "C" );
        final var streamB = Stream.of( "Apple", "Banana", "Carrot" );

        //noinspection StandardVariableNames
        final var zipped = StreamUtils.zip( streamA, streamB, ( a, b ) -> a + " is for " + b ).collect( Collectors.toList() );

        assertThat( zipped, contains( "A is for Apple", "B is for Banana", "C is for Carrot" ) );
    }   //  zips_a_pair_of_streams_of_same_length()

    @SuppressWarnings( "static-method" )
    @Test
    public final void zips_a_pair_of_streams_where_first_stream_is_longer()
    {
        final var streamA = Stream.of( "A", "B", "C", "D" );
        final var streamB = Stream.of( "Apple", "Banana", "Carrot" );

        //noinspection StandardVariableNames
        final var zipped = StreamUtils.zip( streamA, streamB, ( a, b ) -> a + " is for " + b ).collect( Collectors.toList() );

        assertThat( zipped, contains( "A is for Apple", "B is for Banana", "C is for Carrot" ) );
    }   //  zips_a_pair_of_streams_where_first_stream_is_longer()

    @SuppressWarnings( "static-method" )
    @Test
    public final void zips_a_pair_of_streams_where_second_stream_is_longer()
    {
        final var streamA = Stream.of( "A", "B", "C" );
        final var streamB = Stream.of( "Apple", "Banana", "Carrot", "Doughnut" );

        //noinspection StandardVariableNames
        final var zipped = StreamUtils.zip( streamA, streamB, ( a, b ) -> a + " is for " + b ).collect( Collectors.toList() );

        assertThat( zipped, contains( "A is for Apple", "B is for Banana", "C is for Carrot" ) );
    }   //  zips_a_pair_of_streams_where_second_stream_is_longer()

    @SuppressWarnings( {"static-method", "unchecked"} )
    @Test
    public final void zips_a_stream_with_index()
    {
        final var source = Stream.of( "Foo", "Bar", "Baz" );

        final var zipped = StreamUtils.zipWithIndex( source ).collect( Collectors.toList() );

        assertThat( zipped, contains(
                Indexed.index( 0, "Foo" ),
                Indexed.index( 1, "Bar" ),
                Indexed.index( 2, "Baz" ) ) );
    }   //  zips_a_stream_with_index()
}
//  class ZipTest

/*
 *  End of File
 */