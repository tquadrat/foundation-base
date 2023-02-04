/*
 * ============================================================================
 *  Copyright © 2002-2023 by Thomas Thrien.
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

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.internal.SharedFormatter.format;

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
 *  {@link Status#getOrElseThrow(ErrorHandler)}.
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version TestIsFailure: HexUtils.java 747 2020-12-01 12:40:38Z tquadrat $
 */
@ClassVersion( sourceVersion = "TestIsFailure: HexUtils.java 747 2020-12-01 12:40:38Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.status.TestGetOrElse" )
public class TestGetOrElse extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Some tests for
     *  {@link Status#getOrElseThrow(ErrorHandler)}.
     */
    @Test
    final void testGetOrElseFailure()
    {
        skipThreadTest();

        final var errorCode = new ValidationException();
        final Class<? extends RuntimeException> expectedException = errorCode.getClass();

        final var candidate = new Status<String,RuntimeException>( null, errorCode );
        assertNotNull( candidate.errorCode() );
        assertTrue( candidate.isFailure() );

        try
        {
            candidate.getOrElseThrow( e -> e );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testGetOrElseFailure()

    /**
     *  Some tests for
     *  {@link Status#getOrElseThrow(ErrorHandler)}.
     */
    @Test
    final void testGetOrElseSuccess()
    {
        skipThreadTest();

        final var expected = "result";
        final var candidate = new Status<String,RuntimeException>( expected,null );
        assertNull( candidate.errorCode() );
        assertTrue( candidate.isSuccess() );

        try
        {
            assertEquals( expected, candidate.getOrElseThrow( e -> e ) );
        }
        catch( final Throwable t )
        {
            fail( () -> format( MSG_ExceptionThrown, t.getClass().getName() ) );
        }
    }   //  testGetOrElseSuccess()

    /**
     *  Some tests for
     *  {@link Status#getOrElseThrow(ErrorHandler)}.
     */
    @Test
    final void testGetOrElseWithNullArgument()
    {
        skipThreadTest();

        final var errorCode = new ValidationException();

        final var candidate1 = new Status<String,RuntimeException>( null, errorCode );
        assertNotNull( candidate1.errorCode() );
        assertTrue( candidate1.isFailure() );

        assertThrows( NullArgumentException.class, () -> candidate1.getOrElseThrow( null ) );

        final var candidate2 = new Status<>( "result", null );
        assertNull( candidate2.errorCode() );
        assertTrue( candidate2.isSuccess() );

        assertThrows( NullArgumentException.class, () -> candidate2.getOrElseThrow( null ) );
    }   //  testGetOrElseWithNullArgument()
}
//  class TestGetOrElse

/*
 *  End of File
 */