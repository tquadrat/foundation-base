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
import static org.hamcrest.Matchers.hasSize;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.stream.StreamUtils;

@ClassVersion( sourceVersion = "$Id: TakeWhileTest.java 995 2022-01-23 01:09:35Z tquadrat $" )
@DisplayName( "com.codepoetics.protonpack.TakeWhileTest" )
public class TakeWhileTest
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    @SuppressWarnings( "static-method" )
    @Test
    public final void take_while_takes_items_while_condition_is_met()
    {
        final var infiniteIntValues = Stream.iterate( 0, i -> i + 1 );
        final var finiteIntValues = StreamUtils.takeWhile( infiniteIntValues, i -> i < 10 );

        assertThat( finiteIntValues.collect( Collectors.toList() ), hasSize( 10 ) );
    }   //  take_while_takes_items_while_condition_is_met()

    @SuppressWarnings( "static-method" )
    @Test
    public final void take_until_takes_items_until_condition_is_met()
    {
        final var infiniteIntValues = Stream.iterate( 0, i -> i + 1 );
        final var finiteIntValues = StreamUtils.takeUntil( infiniteIntValues, i -> i > 10 );

        assertThat( finiteIntValues.collect( Collectors.toList() ), hasSize( 11 ) );
    }   //  take_until_takes_items_until_condition_is_met()
}
//  class TakeWhileTest

/*
 *  End of File
 */