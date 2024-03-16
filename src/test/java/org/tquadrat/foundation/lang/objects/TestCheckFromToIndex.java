/*
 * ============================================================================
 * Copyright © 2002-2024 by Thomas Thrien.
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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.helper.IntTupel3;
import org.tquadrat.foundation.testutil.TestBaseClass;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.tquadrat.foundation.lang.Objects.checkFromToIndex;

/**
 *  The tests for the method
 *  {@link org.tquadrat.foundation.lang.Objects#checkFromToIndex(int, int, int)}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestCheckFromToIndex.java 1119 2024-03-16 09:03:57Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestCheckFromToIndex.java 1119 2024-03-16 09:03:57Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestCheckFromToIndex" )
public class TestCheckFromToIndex extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link org.tquadrat.foundation.lang.Objects#checkFromToIndex(int,int,int)}.<br>
     *  <br>The method under test must return the same values as
     *  {@link java.util.Objects#checkFromToIndex(int,int,int)}
     *
     *  @param  arguments   The test arguments.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#indexArgumentProvider" )
    final void testCheckFromToIndex( final IntTupel3 arguments )
    {
        skipThreadTest();

        final var fromIndex = arguments.v1();
        final var toIndex = arguments.v2();
        final var length = arguments.v3();

        if( (fromIndex < 0) || (fromIndex > toIndex) || (toIndex > length) || (length < 0) )
        {
            final Class<? extends Throwable> expectedException = IndexOutOfBoundsException.class;
            try
            {
                checkFromToIndex( fromIndex, toIndex, length );
                fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
            }
            catch( final AssertionError e ) { throw e; }
            catch( final Throwable t )
            {
                final var isExpectedException = expectedException.isInstance( t );
                assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            }
        }
        else
        {
            assertEquals( checkFromToIndex( fromIndex, toIndex, length ), java.util.Objects.checkFromToIndex( fromIndex, toIndex, length ) );
        }
    }   //  testCheckFromToIndex()
}
//  class TestCheckFromToIndex

/*
 *  End of File
 */