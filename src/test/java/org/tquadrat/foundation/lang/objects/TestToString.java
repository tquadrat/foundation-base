/*
 * ============================================================================
 * Copyright © 2002-2024 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 *
 * Licensed to the public under the agreements of the GNU Lesser General Public
 * License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.tquadrat.foundation.lang.objects;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_String_ARRAY;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;

import java.time.Instant;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.lang.Objects;
import org.tquadrat.foundation.lang.Stringer;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  The tests for the methods
 *  {@link org.tquadrat.foundation.lang.Objects#toString(Object)}
 *  and
 *  {@link org.tquadrat.foundation.lang.Objects#toString(Object,String)}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestToString.java 1084 2024-01-03 15:31:20Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestToString.java 1084 2024-01-03 15:31:20Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestToString" )
public class TestToString extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link Objects#toString(Object)}.
     */
    @Test
    final void testToStringArray()
    {
        skipThreadTest();

        final var flags = new boolean [] { true, false, true, true, false };
        assertEquals( Arrays.toString( flags ), Objects.toString( flags ) );

        final var bytes = new byte [] { 1, 2, 3 };
        assertEquals( Arrays.toString( bytes ), Objects.toString( bytes ) );

        final var chars = new char [] { 'a', 'b', 'c' };
        assertEquals( Arrays.toString( chars ), Objects.toString( chars ) );

        final var doubles = new double [] { 1.1, 2.2, 3.3 };
        assertEquals( Arrays.toString( doubles ), Objects.toString( doubles ) );

        final var floats = new float [] { 1.1f, 2.2f, 3.3f };
        assertEquals( Arrays.toString( floats ), Objects.toString( floats ) );

        final var integers = new int [] { 1, 2, 3 };
        assertEquals( Arrays.toString( integers ), Objects.toString( integers ) );

        final var longs = new long [] {1L, 2L, 3L};
        assertEquals( Arrays.toString( longs ), Objects.toString( longs ) );

        final var shorts = new short [] { 1, 2, 3 };
        assertEquals( Arrays.toString( shorts ), Objects.toString( shorts ) );
    }   //  testToStringArray()

    /**
     *  Test method for
     *  {@link Objects#toString(Object)}.
     */
    @Test
    final void testToStringArrayObject()
    {
        skipThreadTest();

        Object [] candidate;
        candidate = EMPTY_String_ARRAY;
        assertEquals( Arrays.toString( candidate ), Objects.toString( candidate ) );

        //noinspection ZeroLengthArrayAllocation
        candidate = new Integer [0];
        assertEquals( Arrays.toString( candidate ), Objects.toString( candidate ) );

        candidate = new String [] { "eins", "zwei", "drei" };
        assertEquals( Arrays.toString( candidate ), Objects.toString( candidate ) );

        candidate = new Integer [] { 1, 2, 3 };
        assertEquals( Arrays.toString( candidate ), Objects.toString( candidate ) );
    }   //  testToStringArrayObject()

    /**
     *  Test method for
     *  {@link Objects#toString(Object)}.
     */
    @Test
    final void testToStringNull()
    {
        skipThreadTest();

        assertEquals( NULL_STRING, Objects.toString( null ) );
    }   //  testToStringNull()

    /**
     *  Test method for
     *  {@link Objects#toString(Object,String)}.
     */
    @Test
    final void testToStringNullString()
    {
        skipThreadTest();

        final var nullString = "nullString";
        assertEquals( nullString, Objects.toString( null, nullString ) );
    }   //  testToStringNullString()

    /**
     *  Test method for
     *  {@link Objects#toString(Object)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#nullTestArgumentProvider" )
    public final void testToStringObject( final Object candidate )
    {
        skipThreadTest();

        assumeFalse( candidate.getClass().isArray() );

        assertEquals( java.util.Objects.toString( candidate ), Objects.toString( candidate ) );
    }   //  testToStringObject()

    /**
     *  Test method for
     *  {@link Objects#toString(Object,String)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#nullTestArgumentProvider" )
    final void testToStringObjectString( final Object candidate )
    {
        skipThreadTest();

        assumeFalse( candidate.getClass().isArray() );

        assertEquals( java.util.Objects.toString( candidate, NULL_STRING ), Objects.toString( candidate, NULL_STRING ), () -> format( "Candidate Class: %s", candidate.getClass().getName() ) );
    }   //  testToStringObjectString()

    /**
     *  Test method for
     *  {@link Objects#toString(Object,String)}.
     */
    @Test
    final void testToStringObjectStringNull()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            Objects.toString( Instant.now(), null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testToStringObjectStringNull()

    /**
     *  Test method for
     *  {@link Objects#toString(Object,org.tquadrat.foundation.lang.Stringer,String)}.
     */
    @Test
    final void testToStringStringer()
    {
        skipThreadTest();

        final var nullString = "nullString";
        final var stringer = Stringer.OBJECT_STRINGER;

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            //---* The stringer is null *--------------------------------------
            Objects.toString( Instant.now(), null, nullString );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        try
        {
            //---* The null string is null *-----------------------------------
            Objects.toString( Instant.now(), stringer, null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        assertEquals( nullString, Objects.toString( null, stringer, nullString ) );

        final var now = Instant.now();
        assertEquals( now.toString(), Objects.toString( now, stringer, nullString ) );
    }   //  testToStringStringer()
}
//  class TestToString

/*
 *  End of File
 */