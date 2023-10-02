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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.Objects;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  The tests for the method
 *  {@link org.tquadrat.foundation.lang.Objects#hashCode(Object)}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestHashCode.java 1061 2023-09-25 16:32:43Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestHashCode.java 1061 2023-09-25 16:32:43Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestHashCode" )
public class TestHashCode extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link org.tquadrat.foundation.lang.Objects#hashCode(Object)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#nullTestArgumentProvider" )
    public final void testHashCode( final Object candidate )
    {
        skipThreadTest();

        final var expected = candidate.hashCode();
        assertEquals( expected, Objects.hashCode( candidate ) );
        assertEquals( java.util.Objects.hashCode( candidate ), Objects.hashCode( candidate ) );
    }   //  testHashCode()

    /**
     *  Test method for
     *  {@link org.tquadrat.foundation.lang.Objects#hashCode(Object)}.
     */
    @Test
    final void testHashCode()
    {
        skipThreadTest();

        assertEquals( java.util.Objects.hashCode( null ), Objects.hashCode( null ) );
        assertEquals( 0, Objects.hashCode( null ) );
    }   //  testHashCode()
}
//  class TestHashCode

/*
 *  End of File
 */