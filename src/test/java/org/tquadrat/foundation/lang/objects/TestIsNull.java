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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.tquadrat.foundation.lang.Objects.isNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  The tests for the method
 *  {@link org.tquadrat.foundation.lang.Objects#isNull(Object)}
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestIsNull.java 820 2020-12-29 20:34:22Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestIsNull.java 820 2020-12-29 20:34:22Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestIsNull" )
public class TestIsNull extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link org.tquadrat.foundation.lang.Objects#isNull(Object)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#nullTestArgumentProvider" )
    final void testIsNull( final Object candidate )
    {
        skipThreadTest();

        assertFalse( isNull( candidate ) );
    }   //  testIsNull()

    /**
     *  Test method for
     *  {@link org.tquadrat.foundation.lang.Objects#isNull(Object)}.
     */
    @Test
    final void testIsNull()
    {
        skipThreadTest();

        assertTrue( isNull( null ) );
    }   //  testIsNull()
}
//  class TestIsNull

/*
 *  End of File
 */