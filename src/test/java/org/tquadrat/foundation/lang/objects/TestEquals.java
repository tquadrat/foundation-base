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
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.Objects;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  The tests for the method
 *  {@link org.tquadrat.foundation.lang.Objects#equals(Object, Object)}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestEquals.java 1061 2023-09-25 16:32:43Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestEquals.java 1061 2023-09-25 16:32:43Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestEquals" )
public class TestEquals extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link org.tquadrat.foundation.lang.Objects#equals(Object,Object)}.
     */
    @Test
    final void testEquals()
    {
        skipThreadTest();

        assertEquals( EMPTY_STRING.equals( EMPTY_STRING ), Objects.equals( EMPTY_STRING, EMPTY_STRING ) );
        assertEquals( EMPTY_STRING.equals( NULL_STRING ), Objects.equals( EMPTY_STRING, NULL_STRING ) );
        final var candidate = "null";
        assertEquals( NULL_STRING.equals( candidate ), Objects.equals( NULL_STRING, candidate ) );
    }   //  testEquals()
}
//  class TestEquals

/*
 *  End of File
 */