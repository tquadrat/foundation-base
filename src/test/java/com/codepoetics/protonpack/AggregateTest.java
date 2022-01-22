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

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.stream.StreamUtils;

@ClassVersion( sourceVersion = "$Id: AggregateTest.java 635 2020-02-04 12:08:02Z tquadrat $" )
@DisplayName( "com.codepoetics.protonpack.AggregateTest" )
public class AggregateTest
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    @SuppressWarnings( {"static-method", "unchecked"} )
    @Test
    public final void aggregate_on_bi_element_predicate()
    {
        final var stream = Stream.of( "a1", "b1", "b2", "c1" );
        final var aggregated = StreamUtils.aggregate( stream, ( e1, e2) -> e1.charAt( 0 ) == e2.charAt( 0 ) );
        assertThat( aggregated.collect( toList() ), contains(
                List.of( "a1" ),
                List.of( "b1", "b2" ),
                List.of( "c1" ) ) );
    }   //  aggregate_on_bi_element_predicate()

    @SuppressWarnings( {"static-method", "unchecked"} )
    @Test
    public void aggregate_on_size1()
    {
        final var stream = Stream.of( "a1", "b1", "b2", "c1" );
        final var aggregated = StreamUtils.aggregate( stream, 1 );
        assertThat( aggregated.collect( toList() ), contains(
                List.of( "a1" ),
                List.of( "b1" ),
                List.of( "b2" ),
                List.of( "c1" ) ) );
    }   //  aggregate_on_size1()

    @SuppressWarnings( {"static-method", "unchecked"} )
    @Test
    public void aggregate_on_size2()
    {
        final var stream = Stream.of( "a1", "b1", "b2", "c1" );
        final var aggregated = StreamUtils.aggregate( stream, 2 );
        assertThat( aggregated.collect( toList() ), contains(
                List.of( "a1", "b1" ),
                List.of( "b2", "c1" ) ) );
    }   //  aggregate_on_size2()

    @SuppressWarnings( {"static-method", "unchecked"} )
    @Test
    public void aggregate_on_size3()
    {
        final var stream = Stream.of( "a1", "b1", "b2", "c1" );
        final var aggregated = StreamUtils.aggregate( stream, 3 );
        assertThat( aggregated.collect( toList() ), contains(
                List.of( "a1", "b1", "b2" ),
                List.of( "c1" ) ) );
    }   //  aggregate_on_size3()
}
//  class AggregateTest

/*
 *  End of File
 */