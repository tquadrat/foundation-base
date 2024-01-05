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

package org.tquadrat.foundation.exception.lambdacontainerexception;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.LambdaContainerException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Tests for the class
 *  {@link LambdaContainerException}
 */
@SuppressWarnings( "ThrowableNotThrown" )
@DisplayName( "org.tquadrat.foundation.exception.lambdacontainerexception.TestCheckIfExpected" )
@ClassVersion( sourceVersion = "$Id: TestCheckIfExpected.java 1084 2024-01-03 15:31:20Z tquadrat $" )
public class TestCheckIfExpected extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Tests for
     *  {@link LambdaContainerException#checkIfExpected(Class...)},
     *  {@link LambdaContainerException#checkIfExpected(Collection)},
     *  and
     *  {@link LambdaContainerException#checkIfExpected(Stream)}.
     */
    @Test
    final void testCheckIfExpected()
    {
        skipThreadTest();

        final var candidate = new LambdaContainerException( new FileNotFoundException( "file" ) );

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

        assertFalse( candidate.checkIfExpected( CloneNotSupportedException.class ) );
        assertFalse( candidate.checkIfExpected( CloneNotSupportedException.class, DataFormatException.class ) );

        assertFalse( candidate.checkIfExpected( List.of( CloneNotSupportedException.class, DataFormatException.class ) ) );
    }   //  testCheckIfExpected()

    /**
     *  Tests for
     *  {@link LambdaContainerException#checkIfExpected(Class...)},
     *  {@link LambdaContainerException#checkIfExpected(Collection)},
     *  and
     *  {@link LambdaContainerException#checkIfExpected(Stream)}.
     */
    @Test
    final void testCheckIfExpectedWithNullArgument()
    {
        skipThreadTest();

        final var candidate = new LambdaContainerException( new FileNotFoundException( "file" ) );

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
    }   //  testCheckIfExpectedWithNullArgument()
}
//  class TestCheckIfExpected

/*
 *  End of File
 */