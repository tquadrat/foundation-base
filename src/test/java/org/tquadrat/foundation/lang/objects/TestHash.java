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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.tquadrat.foundation.lang.Objects.hash;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  The tests for the method
 *  {@link org.tquadrat.foundation.lang.Objects#hash(Object...)}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestHash.java 1061 2023-09-25 16:32:43Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestHash.java 1061 2023-09-25 16:32:43Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestHash" )
public class TestHash extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link org.tquadrat.foundation.lang.Objects#hash(Object[])}.
     */
    @Test
    final void testHash1()
    {
        skipThreadTest();

        Object [] candidate = null;
        var expected = Arrays.hashCode( candidate );
        assertEquals( 0, expected );
        assertEquals( expected, hash( candidate ) );

        candidate = new String [] {null};
        expected = Arrays.hashCode( candidate );
        assertEquals( 31, expected );
        assertEquals( expected, hash( candidate ) );
        assertTrue( hash( "eins", "zwei", "drei" ) != expected );
    }   //  testHash()

    /**
     *  Test method for
     *  {@link org.tquadrat.foundation.lang.Objects#hash(Object[])}.<br>
     *  <br>The results of the method under test have to be identical to those
     *  from
     *  {@link java.util.Objects#hash(Object...)}.
     */
    @Test
    final void testHash2()
    {
        skipThreadTest();

        Object [] value;

        value = new Object [] { null };
        assertEquals( java.util.Objects.hash( value ), hash( value ) );

        value = new Object [] { Integer.valueOf( 0 ), "String", BigInteger.ONE };
        assertEquals( java.util.Objects.hash( value ), hash( value ) );

        value = new String [] { "eins", "zwei", "drei", "vier", "fünf" };
        assertEquals( java.util.Objects.hash( value ), hash( value ) );
    }   //  testHash()
}
//  class TestHash

/*
 *  End of File
 */