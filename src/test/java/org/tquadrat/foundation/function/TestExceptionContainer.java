/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
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

package org.tquadrat.foundation.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.internal.SharedFormatter.format;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Tests for the class
 *  {@link ExceptionContainer}
 */
@SuppressWarnings( {"ThrowableNotThrown", "removal"} )
@ClassVersion( sourceVersion = "$Id: TestExceptionContainer.java 793 2020-12-19 10:51:52Z tquadrat $" )
public class TestExceptionContainer extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Tests for
     *  {@link ExceptionContainer#checkIfExpected(Class...)},
     *  {@link ExceptionContainer#checkIfExpected(java.util.Collection)},
     *  and
     *  {@link ExceptionContainer#checkIfExpected(java.util.stream.Stream)}.
     */
    @DisplayName( "ExceptionContainer.checkIfExpected() with failing argument" )
    @Test
    final void testCheckIfExpectedFail()
    {
        skipThreadTest();

        final var candidate = new ExceptionContainer( new FileNotFoundException( "file" ) );

        /*
         * FileNotFoundException is derived from IOException, therefore it is
         * expected.
         */
        assertTrue( candidate.checkIfExpected( IOException.class ) );

        assertFalse( candidate.checkIfExpected( CloneNotSupportedException.class ) );
        assertFalse( candidate.checkIfExpected( CloneNotSupportedException.class, DataFormatException.class ) );

        assertFalse( candidate.checkIfExpected( List.of( CloneNotSupportedException.class, DataFormatException.class ) ) );
    }   //  testCheckIfExpectedFail()

    /**
     *  Tests for
     *  {@link ExceptionContainer#checkIfExpected(Class...)},
     *  {@link ExceptionContainer#checkIfExpected(java.util.Collection)},
     *  and
     *  {@link ExceptionContainer#checkIfExpected(java.util.stream.Stream)}.
     */
    @DisplayName( "ExceptionContainer.checkIfExpected() with null argument" )
    @Test
    final void testCheckIfExpectedNull()
    {
        skipThreadTest();

        final var candidate = new ExceptionContainer( new FileNotFoundException( "file" ) );

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            candidate.checkIfExpected( (Class<? extends Exception> []) null );
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
            candidate.checkIfExpected( (Collection<Class<? extends Exception>>) null );
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
            candidate.checkIfExpected( (Stream<Class<? extends Exception>>) null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testCheckIfExpectedNull()

    /**
     *  Tests for
     *  {@link ExceptionContainer#checkIfExpected(Class...)},
     *  {@link ExceptionContainer#checkIfExpected(java.util.Collection)},
     *  and
     *  {@link ExceptionContainer#checkIfExpected(java.util.stream.Stream)}.
     */
    @DisplayName( "ExceptionContainer.checkIfExpected() with success argument" )
    @Test
    final void testCheckIfExpectedSuccess()
    {
        skipThreadTest();

        final var candidate = new ExceptionContainer( new FileNotFoundException( "file" ) );

        assertTrue( candidate.checkIfExpected( FileNotFoundException.class ) );
        assertTrue( candidate.checkIfExpected( FileNotFoundException.class, CloneNotSupportedException.class ) );
        assertTrue( candidate.checkIfExpected( FileNotFoundException.class, CloneNotSupportedException.class, DataFormatException.class ) );

        assertTrue( candidate.checkIfExpected( List.of( FileNotFoundException.class, CloneNotSupportedException.class, DataFormatException.class ) ) );

        /*
         * FileNotFoundException is derived from IOException, therefore it is
         * expected.
         */
        assertTrue( candidate.checkIfExpected( IOException.class ) );
        assertTrue( candidate.checkIfExpected( IOException.class, CloneNotSupportedException.class ) );
        assertTrue( candidate.checkIfExpected( IOException.class, CloneNotSupportedException.class, DataFormatException.class ) );

        assertTrue( candidate.checkIfExpected( List.of( IOException.class, CloneNotSupportedException.class, DataFormatException.class ) ) );
    }   //  testCheckIfExpectedSuccess()

    /**
     *  Tests the construction failure.
     */
    @DisplayName( "ExceptionContainer() with null argument" )
    @Test
    final void testExceptionContainerNull()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            @SuppressWarnings( "unused" )
            final var exceptionContainer = new ExceptionContainer( null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testExceptionContainerNull()

    /**
     *  Tests for
     *  {@link ExceptionContainer#getCheckedCause(Class)}.
     */
    @DisplayName( "ExceptionContainer.getCheckedCause() with valid argument" )
    @Test
    final void testGetCheckedCause()
    {
        skipThreadTest();

        final Exception expected = new FileNotFoundException( "file" );
        final var candidate = new ExceptionContainer( expected );

        final Class<? extends Throwable> expectedException = RuntimeException.class;
        try
        {
            candidate.getCheckedCause( CloneNotSupportedException.class );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            assertNotNull( t.getCause() );
            assertEquals( expected, t.getCause() );
            assertSame( expected, t.getCause() );
        }

        Exception actual;
        actual = candidate.getCheckedCause( expected.getClass() );
        assertNotNull( actual );
        assertEquals( expected, actual );
        assertSame( expected, actual );

        actual = candidate.getCheckedCause( IOException.class );
        assertNotNull( actual );
        assertEquals( expected, actual );
        assertSame( expected, actual );
    }   //  testGetCheckedCause()

    /**
     *  Tests for
     *  {@link ExceptionContainer#getCheckedCause(Class)}.
     */
    @DisplayName( "ExceptionContainer.getCheckedCause() with null argument" )
    @Test
    final void testGetCheckedCauseNull()
    {
        skipThreadTest();

        final var candidate = new ExceptionContainer( new FileNotFoundException( "file" ) );

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            candidate.getCheckedCause( null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testGetCheckedCauseNull()
}
//  class TestExceptionContainer

/*
 *  End of File
 */