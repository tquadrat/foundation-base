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

package org.tquadrat.foundation.lang.objects;

import static java.lang.System.out;
import static org.apiguardian.api.API.Status.STABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.require;
import static org.tquadrat.foundation.lang.internal.SharedFormatter.format;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apiguardian.api.API;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.lang.Objects;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some tests for the methods
 *  {@link org.tquadrat.foundation.lang.Objects#require(Object, Predicate)},
 *  {@link org.tquadrat.foundation.lang.Objects#require(Object, String, Predicate)},
 *  {@link org.tquadrat.foundation.lang.Objects#require(Object, Supplier, Predicate)}
 *  and
 *  {@link org.tquadrat.foundation.lang.Objects#require(Object, Function, Predicate)}
 *  from
 *  {@link org.tquadrat.foundation.lang.Objects}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestRequire.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: TestRequire.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestRequire" )
public class TestRequire extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Some tests for the method
     *  {@link org.tquadrat.foundation.lang.Objects#require(Object, Predicate)}.
     */
    @Test
    final void testRequire1()
    {
        skipThreadTest();

        /*
         * null is valid …
         */
        final Predicate<String> predicate = s -> isNull( s ) || "valid".equals( s );

        final var validCandidate = "valid";
        assertTrue( predicate.test( validCandidate ) );
        final var invalidCandidate = "invalid";
        assertFalse( predicate.test( invalidCandidate ) );
        final String nullCandidate = null;
        assertTrue( predicate.test( nullCandidate ) );

        final Class<? extends Throwable> expectedException = IllegalArgumentException.class;

        String actual;

        actual = require( validCandidate, predicate );
        assertNotNull( actual );
        assertSame( validCandidate, actual );
        actual = require( nullCandidate, predicate );
        assertNull( actual );
        try
        {
            require( invalidCandidate, predicate );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequire1()

    /**
     *  Some tests for the method
     *  {@link org.tquadrat.foundation.lang.Objects#require(Object, String, Predicate)}.
     */
    @Test
    final void testRequire2()
    {
        skipThreadTest();

        /*
         * null is valid …
         */
        final Predicate<String> predicate = s -> isNull( s ) || "valid".equals( s );

        final var validCandidate = "valid";
        assertTrue( predicate.test( validCandidate ) );
        final var invalidCandidate = "invalid";
        assertFalse( predicate.test( invalidCandidate ) );
        final String nullCandidate = null;
        assertTrue( predicate.test( nullCandidate ) );

        final var message = "message";

        final Class<? extends Throwable> expectedException = IllegalArgumentException.class;

        String actual;

        actual = require( validCandidate, message, predicate );
        assertNotNull( actual );
        assertSame( validCandidate, actual );
        actual = require( nullCandidate, message, predicate );
        assertNull( actual );
        try
        {
            require( invalidCandidate, message, predicate );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            assertEquals( message, t.getMessage() );
        }
    }   //  testRequire2()

    /**
     *  Some tests for the method
     *  {@link org.tquadrat.foundation.lang.Objects#require(Object, Supplier, Predicate)}.
     */
    @Test
    final void testRequire3()
    {
        skipThreadTest();

        /*
         * null is valid …
         */
        final Predicate<String> predicate = s -> isNull( s ) || "valid".equals( s );

        final var validCandidate = "valid";
        assertTrue( predicate.test( validCandidate ) );
        final var invalidCandidate = "invalid";
        assertFalse( predicate.test( invalidCandidate ) );
        final String nullCandidate = null;
        assertTrue( predicate.test( nullCandidate ) );

        final var message = "message";
        final Supplier<String> messageSupplier = () -> message;

        final Class<? extends Throwable> expectedException = IllegalArgumentException.class;

        String actual;

        actual = require( validCandidate, messageSupplier, predicate );
        assertNotNull( actual );
        assertSame( validCandidate, actual );
        actual = require( nullCandidate, messageSupplier, predicate );
        assertNull( actual );
        try
        {
            require( invalidCandidate, messageSupplier, predicate );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            assertEquals( message, t.getMessage() );
        }
    }   //  testRequire3()

    /**
     *  Some tests for the method
     *  {@link org.tquadrat.foundation.lang.Objects#require(Object, Function, Predicate)}.
     */
    @Test
    final void testRequire4()
    {
        skipThreadTest();

        /*
         * null is valid …
         */
        final Predicate<String> predicate = s -> isNull( s ) || "valid".equals( s );

        final var validCandidate = "valid";
        assertTrue( predicate.test( validCandidate ) );
        final var invalidCandidate = "invalid";
        assertFalse( predicate.test( invalidCandidate ) );
        final String nullCandidate = null;
        assertTrue( predicate.test( nullCandidate ) );

        final var message = "message";
        final var messageFormat = "%2$s: %1$s";
        final Function<Object,String> messageFunction = v -> format( messageFormat, Objects.toString( v ), message );

        final Class<? extends Throwable> expectedException = IllegalArgumentException.class;

        String actual;

        actual = require( validCandidate, messageFunction, predicate );
        assertNotNull( actual );
        assertSame( validCandidate, actual );
        actual = require( nullCandidate, messageFunction, predicate );
        assertNull( actual );
        try
        {
            require( invalidCandidate, messageFunction, predicate );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            assertEquals( format( messageFormat, invalidCandidate, message ), t.getMessage() );
        }
    }   //  testRequire4()

    /**
     *  Some tests for the method
     *  {@link org.tquadrat.foundation.lang.Objects#require(Object, String, Predicate)}.
     */
    @Test
    final void testRequireWithNullMessageArgument()
    {
        skipThreadTest();

        final var candidate = "candidate";
        final Predicate<String> predicate = s -> isNull( s ) || "valid".equals( s );
        final String message = null;

        final Class<? extends Throwable> expectedException = NullArgumentException.class;

        try
        {
            require( candidate, message, predicate );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireWithNullMessageArgument

    /**
     *  Some tests for the method
     *  {@link org.tquadrat.foundation.lang.Objects#require(Object, String, Predicate)}.
     */
    @Test
    final void testRequireWithEmptyMessageArgument()
    {
        skipThreadTest();

        final var candidate = "candidate";
        final Predicate<String> predicate = s -> isNull( s ) || "valid".equals( s );
        final var message = EMPTY_STRING;

        final Class<? extends Throwable> expectedException = EmptyArgumentException.class;

        try
        {
            require( candidate, message, predicate );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireWithEmptyMessageArgument

    /**
     *  Some tests for the methods
     *  {@link org.tquadrat.foundation.lang.Objects#require(Object, Predicate)},
     *  {@link org.tquadrat.foundation.lang.Objects#require(Object, String, Predicate)},
     *  {@link org.tquadrat.foundation.lang.Objects#require(Object, Supplier, Predicate)}
     *  and
     *  {@link org.tquadrat.foundation.lang.Objects#require(Object, Function, Predicate)}.
     */
    @Test
    final void testRequireWithNullPredicateArgument()
    {
        skipThreadTest();

        final var candidate = "candidate";
        final Predicate<String> predicate = null;
        final var message = "message";
        final var messageFormat = "%2$s: %1$s";
        final Supplier<String> messageSupplier = () -> message;
        final Function<Object,String> messageFunction = v -> format( messageFormat, Objects.toString( v ), message );

        final Class<? extends Throwable> expectedException = NullArgumentException.class;

        try
        {
            require( candidate, predicate );
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
            require( candidate, message, predicate );
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
            require( candidate, messageSupplier, predicate );
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
            require( candidate, messageFunction, predicate );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireWithNullPredicateArgument
}
//  class TestRequire

/*
 *  End of File
 */