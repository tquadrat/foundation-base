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

package org.tquadrat.foundation.exception.lambdacontainerexception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.internal.SharedFormatter.format;

import java.io.FileNotFoundException;
import java.io.IOException;

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
@DisplayName( "org.tquadrat.foundation.exception.TestGetCheckedCause" )
@ClassVersion( sourceVersion = "$Id: TestGetCheckedCause.java 793 2020-12-19 10:51:52Z tquadrat $" )
public class TestGetCheckedCause extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Tests for
     *  {@link LambdaContainerException#getCheckedCause(Class)}.
     */
    @Test
    final void testGetCheckedCause()
    {
        skipThreadTest();

        final Exception expected = new FileNotFoundException( "file" );
        final var candidate = new LambdaContainerException( expected );

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
     *  {@link LambdaContainerException#getCheckedCause(Class)}.
     */
    @Test
    final void testGetCheckedCauseWithNullArgument()
    {
        skipThreadTest();

        final var candidate = new LambdaContainerException( new FileNotFoundException( "file" ) );

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
    }   //  testGetCheckedCauseWithNullArgument()
}
//  class TestGetCheckedCause

/*
 *  End of File
 */