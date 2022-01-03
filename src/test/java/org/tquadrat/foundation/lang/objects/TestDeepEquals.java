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

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.tquadrat.foundation.lang.Objects.deepEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.helper.IntTupel3;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some tests for the class
 *  {@link org.tquadrat.foundation.lang.Objects}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestDeepEquals.java 820 2020-12-29 20:34:22Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestDeepEquals.java 820 2020-12-29 20:34:22Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestDeepEquals" )
public class TestDeepEquals extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link org.tquadrat.foundation.lang.Objects#deepEquals(Object,Object)}.
     *
     *  @param  value   The test arguments.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#indexArgumentProvider" )
    final void testDeepEquals( final IntTupel3 value )
    {
        skipThreadTest();

        final int [] expected = { 0, 0, 0 };

        final int [] array = {value.v1(), value.v2(), value.v3()};

        assertEquals( deepEquals( expected, array ), java.util.Objects.deepEquals( expected, array ) );
        assertEquals( deepEquals( array, array ), java.util.Objects.deepEquals( array, array ) );
        assertEquals( deepEquals( singletonList( expected ), singletonList( array ) ), java.util.Objects.deepEquals( singletonList( expected ), singletonList( array ) ) );
        assertEquals( deepEquals( singletonList( array ), singletonList( array ) ), java.util.Objects.deepEquals( singletonList( array ), singletonList( array ) ) );
    }   //  testDeepEquals()
}
//  class TestDeepEquals

/*
 *  End of File
 */