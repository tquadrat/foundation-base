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
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.stream.StreamUtils;

@ClassVersion( sourceVersion = "$Id: TapTest.java 635 2020-02-04 12:08:02Z tquadrat $" )
@DisplayName( "com.codepoetics.protonpack.TapTest" )
public class TapTest
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    @SuppressWarnings( "static-method" )
    @Test
    public final void elements_from_a_tapped_stream_are_sent_to_the_tap_as_they_are_released()
    {
        final var source = Stream.of( "foo", "bar", "baz" );
        final List<String> receivedByTap = new ArrayList<>();
        final var tapped = StreamUtils.tap( source, receivedByTap::add );
        final var collected = tapped.collect( Collectors.toList() );

        assertThat( receivedByTap, equalTo( collected ) );
    }   //  elements_from_a_tapped_stream_are_sent_to_the_tap_as_they_are_released()

    @SuppressWarnings( "static-method" )
    @Test
    public final void tap_receives_all_elements_from_the_tapped_stream()
    {
        final var source = Stream.of( "foo", "bar", "baz" );
        final List<String> receivedByTap = new ArrayList<>();
        final var tapped = StreamUtils.tap( source, receivedByTap::add );
        final var collected = tapped.filter( s -> s.startsWith( "b" ) ).collect( Collectors.toList() );

        assertThat( collected, contains( "bar", "baz" ) );
        assertThat( receivedByTap, contains( "foo", "bar", "baz" ) );
    }   //  tap_receives_all_elements_from_the_tapped_stream()

    @SuppressWarnings( "static-method" )
    @Test
    public final void tap_receives_modified_elements_from_modified_stream()
    {
        final var source = Stream.of( "foo", "bar", "baz" );
        final List<String> receivedByFirstTap = new ArrayList<>();
        final List<String> receivedBySecondTap = new ArrayList<>();

        final var tappedOriginal = StreamUtils.tap( source, receivedByFirstTap::add );
        final var modified = tappedOriginal.map( String::toUpperCase );
        final var tappedModified = StreamUtils.tap( modified, receivedBySecondTap::add );
        final var collectedModified = tappedModified.collect( Collectors.toList() );

        assertThat( receivedBySecondTap, equalTo( collectedModified ) );
        assertThat( receivedByFirstTap, contains( "foo", "bar", "baz" ) );
    }   //  tap_receives_modified_elements_from_modified_stream()
}
//  class TapTest

/*
 *  End of File
 */