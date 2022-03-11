/*
 * ============================================================================
 * Copyright © 2002-2022 by Thomas Thrien.
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_String_ARRAY;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;

import java.util.Arrays;
import java.util.Optional;

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
 *  The tests for the methods
 *  {@link org.tquadrat.foundation.lang.Objects#requireNotEmptyArgument(Object, String)}
 *  and
 *  {@link org.tquadrat.foundation.lang.Objects#requireNotEmptyArgument(Optional, String)}.
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestRequireNotEmptyArgument.java 1025 2022-03-11 16:26:00Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestRequireNotEmptyArgument.java 1025 2022-03-11 16:26:00Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestRequireNotEmptyArgument" )
public class TestRequireNotEmptyArgument extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link Objects#requireNotEmptyArgument(Object,String)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#notEmptyTestArgumentProvider" )
    public final void testRequireNotEmptyArgument1( final Object candidate )
    {
        skipThreadTest();

        assertEquals( candidate, requireNotEmptyArgument( candidate, "message" ) );
        assertSame( candidate, requireNotEmptyArgument( candidate, "message" ) );
    }   //  testRequireNotEmptyArgument1()

    /**
     *  Test method for
     *  {@link Objects#requireNotEmptyArgument(Object,String)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#emptyTestArgumentProvider" )
    public final void testRequireNotEmptyArgument1WithEmptyArgument( final Object candidate )
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = EmptyArgumentException.class;
        try
        {
            requireNotEmptyArgument( candidate, "arg" );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNotEmptyArgument1WithEmptyArgument()

    /**
     *  Test method for
     *  {@link Objects#requireNotEmptyArgument(Optional,String)}.
     */
    @SuppressWarnings( "OptionalAssignedToNull" )
    @Test
    final void testRequireNotEmptyArgument2()
    {
        skipThreadTest();

        final var value = "value";

        Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            //---* The message is null *---------------------------------------
            requireNotEmptyArgument( Optional.of( value ), null );
            fail( format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        expectedException = EmptyArgumentException.class;
        try
        {
            //---* The message is empty *--------------------------------------
            requireNotEmptyArgument( Optional.of( value ), EMPTY_STRING );
            fail( format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        expectedException = NullArgumentException.class;
        try
        {
            //---* The argument is null *--------------------------------------
            requireNotEmptyArgument( (Optional<?>) null, "arg" );
            fail( format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        expectedException = EmptyArgumentException.class;
        try
        {
            //---* The argument is empty *-------------------------------------
            requireNotEmptyArgument( Optional.empty(), "arg" );
            fail( format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNotEmptyArgument2()

    /**
     *  Test method for
     *  {@link Objects#requireNotEmptyArgument(Optional,String)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#notEmptyTestArgumentProvider" )
    public final void testRequireNotEmptyArgument2( final Object candidate )
    {
        skipThreadTest();

        final var value = Optional.of( candidate );
        assertEquals( candidate, requireNotEmptyArgument( value, "message" ) );
        assertSame( candidate, requireNotEmptyArgument( candidate, "message" ) );
    }   //  testRequireNotEmptyArgument2()

    /**
     *  Test method for
     *  {@link Objects#requireNotEmptyArgument(Object,String)}.
     */
    @Test
    final void testRequireNotEmptyArgumentWithEmptyMessage()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = EmptyArgumentException.class;
        try
        {
            requireNotEmptyArgument( "value", EMPTY_STRING );
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
            requireNotEmptyArgument( Optional.of( "value" ), EMPTY_STRING );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNotEmptyArgumentWithEmptyMessage()

    /**
     *  Test method for
     *  {@link Objects#requireNotEmptyArgument(Object,String)}
     *  and
     *  {@link Objects#requireNotEmptyArgument(Optional, String)}.
     */
    @SuppressWarnings( "OptionalAssignedToNull" )
    @Test
    final void testRequireNotEmptyArgumentWithNullArgument()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            requireNotEmptyArgument( (Object) null, "arg" );
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
            requireNotEmptyArgument( (Optional<?>) null, "arg" );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNotEmptyArgumentWithNullArgument()

    /**
     *  Test method for
     *  {@link Objects#requireNotEmptyArgument(Object,String)}
     *  and
     *  {@link Objects#requireNotEmptyArgument(Optional, String)}.
     */
    @Test
    final void testRequireNotEmptyArgumentWithNullMessage()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            requireNotEmptyArgument( "value", null );
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
            requireNotEmptyArgument( Optional.of( "value" ), null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNotEmptyArgumentWithNullMessage()

    /**
     *  Test method for
     *  {@link Objects#requireNotEmptyArgument(Object,String)}.
     */
    @SuppressWarnings( "SpellCheckingInspection" )
    @Test
    final void testRequireNotEmptyArgumentWithStringArrayArgument()
    {
        skipThreadTest();

        final var name = "XXmessageXX";
        final var exception = assertThrows( EmptyArgumentException.class, () -> requireNotEmptyArgument( EMPTY_String_ARRAY, name ) );
        assertTrue( exception.getMessage().contains( name ) );

        final var candidate = new String[] {NULL_STRING, EMPTY_STRING, "value"};
        assertTrue( Arrays.deepEquals( candidate, requireNotEmptyArgument( candidate, name ) ) );
    }   //  testRequireNotEmptyArgumentWithStringArrayArgument()
}
//  class TestRequireNotEmptyArgument

/*
 *  End of File
 */