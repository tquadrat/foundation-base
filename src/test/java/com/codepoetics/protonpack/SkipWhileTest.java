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
import org.tquadrat.foundation.stream.StreamUtils;

@ClassVersion( sourceVersion = "$Id: SkipWhileTest.java 635 2020-02-04 12:08:02Z tquadrat $" )
@DisplayName( "com.codepoetics.protonpack.SkipWhileTest" )
public class SkipWhileTest
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    @SuppressWarnings( "static-method" )
    @Test
    public void skip_while_skips_items_while_condition_is_met()
    {
        final var intValues = Stream.of( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 );
        final var skipped = StreamUtils.skipWhile( intValues, i -> i < 4 );

        final var collected = skipped.collect( Collectors.toList() );

        assertThat( collected, contains( 4, 5, 6, 7, 8, 9, 10 ) );
    }   //  skip_while_skips_items_while_condition_is_met()

    @SuppressWarnings( "static-method" )
    @Test
    public void skip_until_takes_items_until_condition_is_met()
    {
        final var intValues = Stream.of( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 );
        final var skipped = StreamUtils.skipUntil( intValues, i -> i > 4 );

        final var collected = skipped.collect( Collectors.toList() );

        assertThat( collected, contains( 5, 6, 7, 8, 9, 10 ) );
    }   //  skip_until_takes_items_until_condition_is_met()
}
//  class SkipWhileTest

/*
 *  End of File
 */