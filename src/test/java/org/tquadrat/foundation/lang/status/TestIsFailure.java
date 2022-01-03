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

package org.tquadrat.foundation.lang.status;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.Status;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some tests for
 *  {@link Status#isFailure()}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version TestIsFailure: HexUtils.java 747 2020-12-01 12:40:38Z tquadrat $
 */
@SuppressWarnings( "MisorderedAssertEqualsArguments" )
@ClassVersion( sourceVersion = "TestIsFailure: HexUtils.java 747 2020-12-01 12:40:38Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.status.TestIsFailure" )
public class TestIsFailure extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Some tests for
     *  {@link Status#isFailure()}.
     */
    @Test
    final void testIsFailure()
    {
        skipThreadTest();

        var candidate = new Status<String,Integer>( null, 1 );
        assertNotNull( candidate.errorCode() );
        assertTrue( candidate.isFailure() );

        candidate = new Status<>( null, 0 );
        assertNotNull( candidate.errorCode() );
        assertTrue( candidate.isFailure() );

        candidate = new Status<>( "result", null );
        assertNull( candidate.errorCode() );
        assertFalse( candidate.isFailure() );
    }   //  testIsFailure()
}
//  class TestIsFailure

/*
 *  End of File
 */