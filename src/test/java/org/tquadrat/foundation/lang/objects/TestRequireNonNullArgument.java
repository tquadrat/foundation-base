/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.lang.Objects;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  The tests for the method
 *  {@link org.tquadrat.foundation.lang.Objects#requireNonNullArgument(Object, String)}
 *  and
 *  {@link org.tquadrat.foundation.lang.Objects#requireNonNullArgument(Object, Object, String, String)}
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestRequireNonNullArgument.java 820 2020-12-29 20:34:22Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestRequireNonNullArgument.java 820 2020-12-29 20:34:22Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestRequireNonNullArgument" )
public class TestRequireNonNullArgument extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link Objects#requireNonNullArgument(Object,String)}.
     */
    @Test
    final void testRequireNonNullArgument1()
    {
        skipThreadTest();

        final var arg = "arg";
        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            requireNonNullArgument( null, arg );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            assertTrue( t.getMessage().contains( arg ) );
        }
    }   //  testRequireNonNullArgument1()

    /**
     *  Test method for
     *  {@link Objects#requireNonNullArgument(Object, Object, String, String)}.
     */
    @Test
    final void testRequireNonNullArgument2()
    {
        skipThreadTest();

        final var arg1 = "arg1";
        final var arg2 = "arg2";
        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            requireNonNullArgument( null, null, arg1, arg2 );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            assertTrue( t.getMessage().contains( arg1 ) );
            assertTrue( t.getMessage().contains( arg2 ) );
        }
    }   //  testRequireNonNullArgument2()

    /**
     *  Test method for
     *  {@link Objects#requireNonNullArgument(Object,String)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#nullTestArgumentProvider" )
    final void testRequireNonNullArgument1( final Object candidate )
    {
        skipThreadTest();

        assertSame( candidate, requireNonNullArgument( candidate, "message" ) );
    }   //  testRequireNonNullArgument1()

    /**
     *  Test method for
     *  {@link Objects#requireNonNullArgument(Object, Object, String, String)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#nullTestArgumentProvider" )
    final void testRequireNonNullArgument2( final Object candidate )
    {
        skipThreadTest();

        assertSame( candidate, requireNonNullArgument( candidate, "String", "arg1", "arg2" ) );
        assertSame( candidate, requireNonNullArgument( candidate, null, "arg1", "arg2" ) );
        var value = new Object();
        assertSame( value, requireNonNullArgument( value, candidate, "arg1", "arg2" ) );
        value = null;
        assertNull( requireNonNullArgument( value, candidate, "arg1", "arg2" ) );
    }   //  testRequireNonNullArgument2()

    /**
     *  Test method for
     *  {@link Objects#requireNonNullArgument(Object,String)}.
     */
    @Test
    final void testRequireNonNullArgument1WithEmptyMessage()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = EmptyArgumentException.class;
        try
        {
            requireNonNullArgument( "value", EMPTY_STRING );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNonNullArgument1WithEmptyMessage()

    /**
     *  Test method for
     *  {@link Objects#requireNonNullArgument(Object, Object, String, String)}.
     */
    @Test
    final void testRequireNonNullArgument2WithEmptyMessage()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = EmptyArgumentException.class;

        String arg1, arg2;

        arg1 = EMPTY_STRING;
        arg2 = "arg2";
        try
        {
            requireNonNullArgument( "value1", "value2", arg1, arg2 );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        arg1 = "arg1";
        arg2 = EMPTY_STRING;
        try
        {
            requireNonNullArgument( "value1", "value2", arg1, arg2 );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNonNullArgument2WithEmptyMessage()

    /**
     *  Test method for
     *  {@link Objects#requireNonNullArgument(Object,String)}.
     */
    @Test
    final void testRequireNonNullArgument1WithNullMessage()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            requireNonNullArgument( "value", null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNonNullArgument1WithNullMessage()

    /**
     *  Test method for
     *  {@link Objects#requireNonNullArgument(Object, Object, String, String)}.
     */
    @Test
    final void testRequireNonNullArgument2WithNullMessage()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;

        String arg1, arg2;

        arg1 = null;
        arg2 = "arg2";
        try
        {
            requireNonNullArgument( "value1", "value2", arg1, arg2 );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        arg1 = "arg1";
        arg2 = null;
        try
        {
            requireNonNullArgument( "value1", "value2", arg1, arg2 );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNonNullArgument2WithNullMessage()
}
//  class TestRequireNonNullArgument

/*
 *  End of File
 */