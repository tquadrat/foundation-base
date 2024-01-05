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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_String_ARRAY;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.lang.Objects;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  It looks like that
 *  {@link Objects#requireNotEmptyArgument(Object, String)}
 *  will not identify an empty array as empty.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: BugHunt_20201230_001.java 1084 2024-01-03 15:31:20Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: BugHunt_20201230_001.java 1084 2024-01-03 15:31:20Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.BugHunt_20201230_001" )
public class BugHunt_20201230_001 extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link Objects#requireNotEmptyArgument(Object,String)}.
     */
    @Test
    public final void testRequireNotEmptyArgumentWithEmptyArgument()
    {
        skipThreadTest();

        Object candidate;

        final Class<? extends Throwable> expectedException = EmptyArgumentException.class;

        candidate = EMPTY_String_ARRAY;
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

        //noinspection ZeroLengthArrayAllocation
        candidate = new Boolean [0];
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

        candidate = Map.of();
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

        candidate = List.of();
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
    }   //  testRequireNotEmptyArgumentWithEmptyArgument()
}
//  class BugHunt_20201230_001

/*
 *  End of File
 */