/*
 * ============================================================================
 *  Copyright © 2002-2020 by Thomas Thrien.
 *  All Rights Reserved.
 * ============================================================================
 *  Licensed to the public under the agreements of the GNU Lesser General Public
 *  License, version 3.0 (the "License"). You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations
 *  under the License.
 */

package org.tquadrat.foundation.lang.internal;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.nanoTime;
import static java.lang.System.out;
import static java.util.Locale.GERMANY;
import static java.util.Locale.ROOT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.internal.SharedFormatter.format;

import java.time.Instant;
import java.util.Locale;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.helper.FormattableObject;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some tests for the class
 *  {@link org.tquadrat.foundation.lang.internal.SharedFormatter}
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestSharedFormatter.java 820 2020-12-29 20:34:22Z tquadrat $
 */
@SuppressWarnings( "MisorderedAssertEqualsArguments" )
@ClassVersion( sourceVersion = "$Id: TestSharedFormatter.java 820 2020-12-29 20:34:22Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.internal.TestSharedFormatter" )
public class TestSharedFormatter extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Tests for
     *  {@link SharedFormatter#format(String,Object...)}
     *  and
     *  {@link SharedFormatter#format(Locale,String,Object...)}
     */
    @Test
    final void testFormat()
    {
        skipThreadTest();

        String actual, expected, format;
        final Object [] args;

        format = "String";
        args = new Object [] { "Text", Instant.now() };
        actual = format( format, args );
        expected = String.format( format, args );
        assertEquals( expected, actual );
        actual = format( GERMANY, format, args );
        expected = String.format( GERMANY, format, args );
        assertEquals( expected, actual );

        format = "%2$s %1$s";
        actual = format( format, args );
        expected = String.format( format, args );
        assertEquals( expected, actual );
        actual = format( GERMANY, format, args );
        expected = String.format( GERMANY, format, args );
        assertEquals( expected, actual );
    }   //  testFormat()

    /**
     *  A performance test that compares
     *  {@link String#format(String,Object...)}
     *  with
     *  {@link SharedFormatter#format(String,Object...)}.
     */
    @Test
    @Disabled
    final void testFormat1Performance()
    {
        skipThreadTest();

        final var executions = 1_000_000;
        final var format = "This is the execution %07d! %07d";

        long start = 0;

        //---* String.format() *-----------------------------------------------
        start = nanoTime();
        for( var i = 0; i < executions; ++i )
        {
            @SuppressWarnings( "unused" )
            final var s = String.format( format, i, currentTimeMillis() );
        }
        final var stringTime = (nanoTime() - start) / 1_000_000;

        //---* SharedFormatter.format() *--------------------------------------
        start = nanoTime();
        for( var i = 0; i < executions; ++i )
        {
            @SuppressWarnings( "unused" )
            final var s = format( format, i, currentTimeMillis() );
        }
        final var stringUtilsTime = (nanoTime() - start) / 1_000_000;

        //---* The result *----------------------------------------------------
        out.printf( """
                    Implicit Locale
                    Execution for String.format()         : % 8d ms
                    Execution for SharedFormatter.format(): % 8d ms
                    """, stringTime, stringUtilsTime );
    }   //  testFormat1Performance()

    /**
     *  A performance test that compares
     *  {@link String#format(Locale,String,Object...)}
     *  with
     *  {@link SharedFormatter#format(Locale,String, Object...)}.
     */
    @Test
    @Disabled
    final void testFormat2Performance()
    {
        skipThreadTest();

        final var executions = 1_000_000;
        final var format = "This is the execution %07d! %07d";
        final var locale = ROOT;

        long start = 0;

        //---* String.format() *-----------------------------------------------
        start = nanoTime();
        for( var i = 0; i < executions; ++i )
        {
            @SuppressWarnings( "unused" )
            final var s = String.format( locale, format, i, currentTimeMillis() );
        }
        final var stringTime = (nanoTime() - start) / 1_000_000;

        //---* SharedFormatter.format() *--------------------------------------
        start = nanoTime();
        for( var i = 0; i < executions; ++i )
        {
            @SuppressWarnings( "unused" )
            final var s = format( locale, format, i, currentTimeMillis() );
        }
        final var stringUtilsTime = (nanoTime() - start) / 1_000_000;

        //---* The result *----------------------------------------------------
        out.printf( """
                    Explicit Locale
                    Execution for String.format()         : % 8d ms
                    Execution for SharedFormatter.format(): % 8d ms
                    """, stringTime, stringUtilsTime );
    }   //  testFormat2Performance()

    /**
     *  Tests for
     *  {@link SharedFormatter#format(String,Object...)}
     *  and
     *  {@link SharedFormatter#format(Locale,String,Object...)}
     */
    @Test
    final void testFormatFormattable()
    {
        skipThreadTest();

        String actual;
        String expected;
        final String format;
        final Object [] args;

        final var now = Instant.now();
        format = "Some Text before %2$s Some Text in between %1$s Some Text after";
        args = new Object [] { new FormattableObject( now ), now };
        expected = "Classname: "+ args [0].getClass().getName() + ": Now: " + now.toString() + " (never too late!)";
        assertEquals( expected + expected, format( "%s", args [0] ) );
        assertEquals( expected + expected, String.format( "%s", args [0] ) );

        actual = format( format, args );
        expected = String.format( format, args );
        assertEquals( expected, actual );
        actual = format( GERMANY, format, args );
        expected = String.format( GERMANY, format, args );
        assertEquals( expected, actual );
    }   //  testFormatFormattable()

    /**
     *  Tests for
     *  {@link SharedFormatter#format(String,Object...)}
     *  and
     *  {@link SharedFormatter#format(Locale,String,Object...)}
     */
    @Test
    final void testFormatWithNullFormatArgument()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullPointerException.class;
        try
        {
            format( (String) null, "String", 6 );
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
            format( ROOT, null, "String", 6 );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testFormatWithNullFormatArgument()

    /**
     *  Validates whether the class is static.
     */
    @Test
    final void validateClass()
    {
        assertTrue( validateAsStaticClass( SharedFormatter.class ) );
    }   //  validateClass()
}
//  class TestSharedFormatter

/*
 *  End of File
 */