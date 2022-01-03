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
import static org.tquadrat.foundation.lang.Objects.checkFromIndexSize;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.helper.IntTupel3;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  The tests for the method
 *  {@link org.tquadrat.foundation.lang.Objects#checkFromIndexSize(int, int, int)}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestCheckFromIndexSize.java 820 2020-12-29 20:34:22Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestCheckFromIndexSize.java 820 2020-12-29 20:34:22Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestCheckFromIndexSize" )
public class TestCheckFromIndexSize extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link org.tquadrat.foundation.lang.Objects#checkFromIndexSize(int,int,int)}.<br>
     *  <br>The method under test must return the same values as
     *  {@link java.util.Objects#checkFromIndexSize(int,int,int)}
     *
     *  @param  arguments   The test arguments.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#indexArgumentProvider" )
    final void testCheckFromIndexSize( final IntTupel3 arguments )
    {
        skipThreadTest();

        final int fromIndex = arguments.v1();
        final int size = arguments.v2();
        final int length = arguments .v3();

        if( (fromIndex < 0) || (size < 0) || ((fromIndex + size) > length) || (length < 0) )
        {
            final Class<? extends Throwable> expectedException = IndexOutOfBoundsException.class;
            try
            {
                checkFromIndexSize( fromIndex, size, length );
                fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
            }
            catch( final AssertionError e ) { throw e; }
            catch( final Throwable t )
            {
                final boolean isExpectedException = expectedException.isInstance( t );
                assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
            }
        }
        else
        {
            assertEquals( checkFromIndexSize( fromIndex, size, length ), java.util.Objects.checkFromIndexSize( fromIndex, size, length ) );
        }
    }   //  testCheckFromIndexSize()
}
//  class testCheckFromIndexSize

/*
 *  End of File
 */