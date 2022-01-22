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

import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.stream.StreamUtils;

@ClassVersion( sourceVersion = "$Id: MergeTest.java 635 2020-02-04 12:08:02Z tquadrat $" )
public class MergeTest
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    @SuppressWarnings( {"unchecked", "static-method"} )
    @Test
    public final void merges_streams_to_list_with_unit_merger_and_combiner()
    {
        final var streamA = Stream.of( "A", "B", "C" );
        final var streamB = Stream.of( "apple", "banana", "carrot", "date" );
        final var streamC = Stream.of( "fritter", "split", "cake", "roll", "pastry" );

        final var merged = StreamUtils.mergeToList( streamA, streamB, streamC );

        assertThat( merged.collect( toList() ), contains(
                List.of( "A", "apple", "fritter" ),
                List.of( "B", "banana", "split" ),
                List.of( "C", "carrot", "cake" ),
                List.of( "date", "roll" ),
                List.of( "pastry" ) ) );
    }
}
//  class MergeTest

/*
 *  End of File
 */