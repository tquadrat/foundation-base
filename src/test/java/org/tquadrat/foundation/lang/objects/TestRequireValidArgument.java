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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.requireValidArgument;
import static org.tquadrat.foundation.lang.internal.SharedFormatter.format;

import java.util.function.Predicate;

import org.apiguardian.api.API;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.exception.ValidationException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some tests for the method
 *  {@link org.tquadrat.foundation.lang.Objects#requireValidArgument(Object, String, Predicate)}
 *  from
 *  {@link org.tquadrat.foundation.lang.Objects}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestRequireValidArgument.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: TestRequireValidArgument.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestRequireValidArgument" )
public class TestRequireValidArgument extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Some tests for the method
     *  {@link org.tquadrat.foundation.lang.Objects#requireValidArgument(Object, String, Predicate)}.
     */
    @Test
    final void testRequireValidArgument()
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

        final var name = "name";

        final Class<? extends Throwable> expectedException = ValidationException.class;

        String actual;

        actual = requireValidArgument( validCandidate, name, predicate );
        assertNotNull( actual );
        assertSame( validCandidate, actual );

        actual = requireValidArgument( nullCandidate, name, predicate );
        assertNull( actual );

        try
        {
            requireValidArgument( invalidCandidate, name, predicate );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            assertTrue( t.getMessage().contains( name ) );
        }
    }   //  testRequireValidArgument()

    /**
     *  Some tests for the method
     *  {@link org.tquadrat.foundation.lang.Objects#requireValidArgument(Object, String, Predicate)}.
     */
    @Test
    final void testRequireValidArgumentWithEmptyNameArgument()
    {
        skipThreadTest();

        final var candidate = "candidate";
        final Predicate<String> predicate = s -> isNull( s ) || "valid".equals( s );
        final var name = EMPTY_STRING;

        final Class<? extends Throwable> expectedException = EmptyArgumentException.class;

        try
        {
            requireValidArgument( candidate, name, predicate );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireValidArgumentWithEmptyNameArgument()

    /**
     *  Some tests for the method
     *  {@link org.tquadrat.foundation.lang.Objects#requireValidArgument(Object, String, Predicate)}.
     */
    @Test
    final void testRequireValidArgumentWithNullNameArgument()
    {
        skipThreadTest();

        final var candidate = "candidate";
        final Predicate<String> predicate = s -> isNull( s ) || "valid".equals( s );
        final String name = null;

        final Class<? extends Throwable> expectedException = NullArgumentException.class;

        try
        {
            requireValidArgument( candidate, name, predicate );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireValidArgumentWithNullNameArgument()

    /**
     *  Some tests for the method
     *  {@link org.tquadrat.foundation.lang.Objects#requireValidArgument(Object, String, Predicate)}.
     */
    @Test
    final void testRequireValidArgumentWithNullPredicateArgument()
    {
        skipThreadTest();

        final var candidate = "candidate";
        final Predicate<String> predicate = null;
        final var name = "name";

        final Class<? extends Throwable> expectedException = NullArgumentException.class;

        try
        {
            requireValidArgument( candidate, name, predicate );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException ) t.printStackTrace( out );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testRequireValidArgumentWithNullPredicateArgument()
}
//  class TestRequireValidArgument

/*
 *  End of File
 */