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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.Objects.requireNonNull;

import java.util.function.Supplier;

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
 *  {@link org.tquadrat.foundation.lang.Objects#requireNonNull(Object)},
 *  {@link org.tquadrat.foundation.lang.Objects#requireNonNull(Object, String)}
 *  and
 *  {@link org.tquadrat.foundation.lang.Objects#requireNonNull(Object, Supplier)}
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestRequireNonNull.java 820 2020-12-29 20:34:22Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestRequireNonNull.java 820 2020-12-29 20:34:22Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestRequireNonNull" )
public class TestRequireNonNull extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link Objects#requireNonNull(Object)}.
     */
    @Test
    final void testRequireNonNull()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullPointerException.class;
        try
        {
            requireNonNull( null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNonNull()

    /**
     *  Test method for
     *  {@link Objects#requireNonNull(Object)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#nullTestArgumentProvider" )
    final void testRequireNonNull( final Object candidate )
    {
        skipThreadTest();

        assertSame( candidate, requireNonNull( candidate ) );
    }   //  testRequireNonNull()

    /**
     *  Test method for
     *  {@link Objects#requireNonNull(Object,String)}.
     */
    @Test
    final void testRequireNonNullWithMessage()
    {
        skipThreadTest();

        final var message = "message";
        final Class<? extends Throwable> expectedException = NullPointerException.class;
        try
        {
            requireNonNull( null, message );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            assertEquals( t.getMessage(), message );
        }
    }   //  testRequireNonNullWithMessage()

    /**
     *  Test method for
     *  {@link Objects#requireNonNull(Object,String)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#nullTestArgumentProvider" )
    final void testRequireNonNullWithMessage( final Object candidate )
    {
        skipThreadTest();

        assertSame( candidate, requireNonNull( candidate, "message" ) );
    }   //  testRequireNonNullWithMessage()

    /**
     *  Test method for
     *  {@link Objects#requireNonNull(Object,String)}.
     */
    @Test
    final void testRequireNonNullWithMessageEmpty()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = EmptyArgumentException.class;
        try
        {
            requireNonNull( "value", EMPTY_STRING );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNonNullWithMessageEmpty()

    /**
     *  Test method for
     *  {@link Objects#requireNonNull(Object,String)}.
     */
    @Test
    final void testRequireNonNullWithMessageNull()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            requireNonNull( "value", (String) null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNonNullWithMessageNull()

    /**
     *  Test method for
     *  {@link Objects#requireNonNull(Object,Supplier)}.
     */
    @Test
    final void testRequireNonNullWithSupplier()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullPointerException.class;
        try
        {
            requireNonNull( null, () -> "message" );
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
            //---* supplier is allowed to be null *----------------------------
            requireNonNull( null, (Supplier<String>) null );
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
            //---* supplier is allowed to return null *------------------------
            requireNonNull( null, () -> null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireNonNullWithSupplier()

    /**
     *  Test method for
     *  {@link Objects#requireNonNull(Object,Supplier)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#nullTestArgumentProvider" )
    final void testRequireNonNullWithSupplier( final Object candidate )
    {
        skipThreadTest();

        assertSame( candidate, requireNonNull( candidate, () -> "message" ) );

        //---* supplier is allowed to be null *--------------------------------
        assertSame( candidate, requireNonNull( candidate, (Supplier<String>) null ) );

        //---* supplier is allowed to return null *------------------------
        assertSame( candidate, requireNonNull( candidate, () -> null ) );
    }   //  testRequireNonNullWithSupplier()
}
//  class TestRequireNonNull

/*
 *  End of File
 */