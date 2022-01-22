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

package com.codepoetics.protonpack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.stream.MapStream;

@ClassVersion( sourceVersion = "$Id: MapStreamTest.java 682 2020-03-21 19:20:31Z tquadrat $" )
@DisplayName( "com.codepoetics.protonpack.MapStreamTest" )
public class MapStreamTest
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    private MapStream<String,Integer> m_Candidate;

    @BeforeEach
    public void setUp() { m_Candidate = MapStream.of( "John", 1, "Alice", 2 ); }

    @Test
    public final void testMapKeys()
    {
        final var map = m_Candidate.mapKeys( x -> x.substring( 2 ) ).collect();
        assertTrue( map.containsKey( "hn" ) );
        assertEquals( Integer.valueOf( 1 ), map.get( "hn" ) );
    }   //  testMapKeys()

    @Test
    public final void testMapValues()
    {
        final var map = m_Candidate.mapValues( x -> x + 5 ).collect();
        assertEquals( Integer.valueOf( 6 ), map.get( "John" ) );
    }   //  testMapValues()

    @Test
    public final void testMapEntries()
    {
        final var map = m_Candidate.mapEntries( x -> x + " Doe", i -> i % 2 ).collect();
        assertEquals( Integer.valueOf( 1 ), map.get( "John Doe" ) );
        assertEquals( Integer.valueOf( 0 ), map.get( "Alice Doe" ) );
    }   //  testMapEntries()

    @Test
    public final void testFailMergeKeys()
    {
        assertThrows( IllegalStateException.class, () -> m_Candidate.mapKeys( x -> Character.isUpperCase( x.charAt( 0 ) ) ).collect() );
    }   //  testFailMergeKeys()

    @Test
    public final void testMergeKeys()
    {
        final var map = m_Candidate.mapKeys( x -> Character.isUpperCase( x.charAt( 0 ) ) ).mergeKeys().collect();
        assertEquals( Arrays.asList( 1, 2 ), map.get( true ) );
        assertNull( map.get( false ) );
    }   //  testMergeKeys()

    @Test
    public final void testMergeKeysWithBinaryFunction()
    {
        final var map = m_Candidate.mapKeys( x -> Character.isUpperCase( x.charAt( 0 ) ) ).mergeKeys( Integer::sum ).collect();
        assertEquals( Integer.valueOf( 3 ), map.get( true ) );
        assertNull( map.get( false ) );
    }   //  testMergeKeysWithBinaryFunction()

    @Test
    public final void testReverseMapping()
    {
        final var map = m_Candidate.inverseMapping().collect();
        assertEquals( "John", map.get( 1 ) );
        assertEquals( "Alice", map.get( 2 ) );

        map.put( 3, "John" );

        final var mapReversed = MapStream.of( map ).inverseMapping().collect( Integer::sum );
        assertEquals( Integer.valueOf( 4 ), mapReversed.get( "John" ) );
        assertEquals( Integer.valueOf( 2 ), mapReversed.get( "Alice" ) );
    }   //  testReverseMapping()
}
//  class MapStreamTest

/*
 *  End of File
 */