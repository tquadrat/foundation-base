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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.Objects.requireNonNullElseGet;

import java.util.function.Supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.lang.Objects;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  The tests for the method
 *  {@link org.tquadrat.foundation.lang.Objects#requireNonNullElseGet(Object, Supplier)}
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestNonNullElseGet.java 1061 2023-09-25 16:32:43Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestNonNullElseGet.java 1061 2023-09-25 16:32:43Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestRequireNonNullElseGet" )
public class TestNonNullElseGet extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link Objects#requireNonNullElseGet(Object,Supplier)}.
     */
    @Test
    final void testRequireNonNullElseGet()
    {
        skipThreadTest();

        final var defaultValue = "default";
        var expected = "value";

        Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            //---* Supplier is null *------------------------------------------
            requireNonNullElseGet( expected, null );
            fail( format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        expectedException = NullPointerException.class;
        try
        {
            //---* Supplier is null *------------------------------------------
            requireNonNullElseGet( null, () -> null );
            fail( format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        expected = defaultValue;
        assertEquals( expected, requireNonNullElseGet( null, () -> defaultValue ) );

        expected = "value";
        assertEquals( expected, requireNonNullElseGet( expected, () -> defaultValue ) );
    }   //  testRequireNonNullElseGet()
}
//  class TestNonNullElseGet

/*
 *  End of File
 */