/*
 * ============================================================================
 *  Copyright © 2002-2024 by Thomas Thrien.
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

package org.tquadrat.foundation.lang.status;

import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.Locale.ROOT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.Objects.isNull;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.exception.ValidationException;
import org.tquadrat.foundation.lang.ErrorHandler;
import org.tquadrat.foundation.lang.Status;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some tests for
 *  {@link Status#onSuccess(Consumer)},
 *  {@link Status#onSuccess(Function, ErrorHandler)}
 *  and
 *  {@link Status#onSuccess(Consumer, ErrorHandler)}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version TestIsFailure: HexUtils.java 747 2020-12-01 12:40:38Z tquadrat $
 */
@ClassVersion( sourceVersion = "TestIsFailure: HexUtils.java 747 2020-12-01 12:40:38Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.status.TestOnSuccess" )
public class TestOnSuccess extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Some tests for
     *  {@link Status#onSuccess(Consumer)} .
     */
    @Test
    final void testOnSuccess1()
    {
        skipThreadTest();

        final var target = new ArrayList<String>();

        final var errorCode = new ValidationException();
        final var result = "result";

        final var action = (Consumer<String>) target::add;

        var candidate = new Status<String,Throwable>( null, errorCode );
        assertNotNull( candidate.errorCode() );
        assertTrue( candidate.isFailure() );

        assertTrue( target.isEmpty() );
        candidate.onSuccess( action );
        assertTrue( target.isEmpty() );

        candidate = new Status<>( result,null );
        assertNull( candidate.errorCode() );
        assertTrue( candidate.isSuccess() );

        assertTrue( target.isEmpty() );
        candidate.onSuccess( action );
        assertFalse( target.isEmpty() );
        assertTrue( target.contains( result ) );
    }   //  testOnSuccess1()

    /**
     *  Some tests for
     *  {@link Status#onSuccess(Consumer, ErrorHandler)} .
     */
    @Test
    final void testOnSuccess2()
    {
        skipThreadTest();

        skipThreadTest();

        final var target = new ArrayList<String>();

        final var errorCode = new ValidationException();
        final Class<? extends RuntimeException> expectedException = errorCode.getClass();
        final var result = "result";

        final var action = (Consumer<String>) target::add;
        final var errorHandler = (ErrorHandler<RuntimeException>) e -> e;

        var candidate = new Status<String,RuntimeException>( null, errorCode );
        assertNotNull( candidate.errorCode() );
        assertTrue( candidate.isFailure() );

        assertTrue( target.isEmpty() );
        try
        {
            candidate.onSuccess( action, errorHandler );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
        assertTrue( target.isEmpty() );

        candidate = new Status<>( result,null );
        assertNull( candidate.errorCode() );
        assertTrue( candidate.isSuccess() );

        assertTrue( target.isEmpty() );
        candidate.onSuccess( action );
        assertFalse( target.isEmpty() );
        assertTrue( target.contains( result ) );
    }   //  testOnSuccess2()

    /**
     *  Some tests for
     *  {@link Status#onSuccess(Function, ErrorHandler)} .
     */
    @Test
    final void testOnSuccess3()
    {
        skipThreadTest();

        final var errorCode = new ValidationException();
        final Class<? extends Throwable> expectedException = errorCode.getClass();
        final var result = "result";
        final var expected = result.toUpperCase( ROOT );

        final var conversion = (Function<String, String>) s -> isNull( s ) ? EMPTY_STRING : s.toUpperCase( ROOT );
        final var errorHandler = (ErrorHandler<RuntimeException>) e -> e;

        var candidate = new Status<String,RuntimeException>( null, errorCode );
        assertNotNull( candidate.errorCode() );
        assertTrue( candidate.isFailure() );

        try
        {
            candidate.onSuccess( conversion, errorHandler );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        candidate = new Status<>( result,null );
        assertNull( candidate.errorCode() );
        assertTrue( candidate.isSuccess() );

        try
        {
            assertEquals( expected, candidate.onSuccess( conversion, errorHandler ) );
        }
        catch( final Throwable t )
        {
            fail( () -> format( MSG_ExceptionThrown, t.getClass().getName() ) );
        }
    }   //  testOnSuccess3()

    /**
     *  Some tests for
     *  {@link Status#onSuccess(Consumer)},
     *  {@link Status#onSuccess(Function, ErrorHandler)}
     *  and
     *  {@link Status#onSuccess(Consumer, ErrorHandler)}.
     */
    @Test
    final void testOnSuccessWithNullArgument()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;

        final var errorCode = new ValidationException();
        final var result = "result";

        final var action = (Consumer<String>) _ -> { /* Does nothing */ };
        final var conversion = (Function<String, String>) s -> isNull( s ) ? EMPTY_STRING : s.toUpperCase( ROOT );
        final var errorHandler = (ErrorHandler<RuntimeException>) e -> e;

        var candidate = new Status<String,RuntimeException>( null, errorCode );
        assertNotNull( candidate.errorCode() );
        assertTrue( candidate.isFailure() );

        try
        {
            candidate.onSuccess( null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        try
        {
            candidate.onSuccess( action, null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
        try
        {
            candidate.onSuccess( (Consumer<? super String>) null, errorHandler );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        try
        {
            candidate.onSuccess( conversion, null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
        try
        {
            candidate.onSuccess( (Function<? super String, ? extends Object>) null, errorHandler );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        //---* For the success case … *----------------------------------------
        candidate = new Status<>( result, null );
        assertNull( candidate.errorCode() );
        assertTrue( candidate.isSuccess() );

        try
        {
            candidate.onSuccess( null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        try
        {
            candidate.onSuccess( action, null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
        try
        {
            candidate.onSuccess( (Consumer<? super String>) null, errorHandler );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        try
        {
            candidate.onSuccess( conversion, null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
        try
        {
            candidate.onSuccess( (Function<? super String, ? extends Object>) null, errorHandler );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
                t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testOnSuccessWithNullArgument()
}
//  class TestOnSuccess

/*
 *  End of File
 */