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

import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.stream.StreamUtils;

@ClassVersion( sourceVersion = "$Id: UnfoldTest.java 995 2022-01-23 01:09:35Z tquadrat $" )
public class UnfoldTest
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    @SuppressWarnings( "static-method" )
    @Test
    public final void unfolds_a_seed_until_empty_is_returned()
    {
        final var unfolded = StreamUtils.unfold( 1, i -> (i < 10) ? Optional.of( i + 1 ) : Optional.empty() );

        assertThat( unfolded.collect( Collectors.toList() ), contains( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ) );
    }   //  unfolds_a_seed_until_empty_is_returned()
}
//  class UnfoldTest

/*
 *  End of File
 */