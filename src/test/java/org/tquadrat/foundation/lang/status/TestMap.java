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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.Objects.isNull;

import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.exception.ValidationException;
import org.tquadrat.foundation.lang.Status;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some tests for
 *  {@link Status#map(Function)}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version TestIsFailure: HexUtils.java 747 2020-12-01 12:40:38Z tquadrat $
 */
@ClassVersion( sourceVersion = "TestIsFailure: HexUtils.java 747 2020-12-01 12:40:38Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.status.TestMap" )
public class TestMap extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Some tests for
     *  {@link Status#map(Function)}.
     */
    @Test
    final void testMapFailure()
    {
        skipThreadTest();

        final var errorCode = new ValidationException();

        final var candidate = new Status<String,Throwable>( null, errorCode );
        assertNotNull( candidate.errorCode() );
        assertTrue( candidate.isFailure() );

        final var actual = candidate.map( s -> isNull( s ) ? EMPTY_STRING : s.toUpperCase( ROOT ) );
        assertNotNull( actual );
        assertTrue( actual.isEmpty() );
    }   //  testMapFailure()

    /**
     *  Some tests for
     *  {@link Status#map(Function)}.
     */
    @Test
    final void testMapSuccess()
    {
        skipThreadTest();

        final var candidate = new Status<String,Throwable>( "result",null );
        assertNotNull( candidate.result() );
        final var expected = candidate.result().toUpperCase( ROOT );
        assertNull( candidate.errorCode() );
        assertTrue( candidate.isSuccess() );

        final var actual = candidate.map( s -> isNull( s ) ? EMPTY_STRING : s.toUpperCase( ROOT ) );
        assertNotNull( actual );
        assertTrue( actual.isPresent() );
        assertEquals( expected, actual.get() );
    }   //  testMapSuccess()

    /**
     *  Some tests for
     *  {@link Status#map(Function)}.
     */
    @Test
    final void testMapWithNullArgument()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;

        final var errorCode = new ValidationException();
        final var result = "result";

        var candidate = new Status<String,Throwable>( null, errorCode );
        assertNotNull( candidate.errorCode() );
        assertTrue( candidate.isFailure() );

        try
        {
            candidate.map( null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        candidate = new Status<>( result, null );
        assertNull( candidate.errorCode() );
        assertTrue( candidate.isSuccess() );

        try
        {
            candidate.map( null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testMapWithNullArgument()
}
//  class TestMap

/*
 *  End of File
 */