/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
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

package org.tquadrat.foundation.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Tests for the class
 *  {@link Functions}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestFunctions.java 993 2022-01-19 22:26:20Z tquadrat $
 */
@SuppressWarnings( {"OverlyCoupledClass","MisorderedAssertEqualsArguments"} )
@ClassVersion( sourceVersion = "$Id: TestFunctions.java 993 2022-01-19 22:26:20Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.function.TestFunctions" )
public class TestFunctions extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Uses the functional interfaces in the package
     *  {@link org.tquadrat.foundation.function}
     *  mainly to gain the required coverage ratio.
     */
    @Test
    final void cover()
    {
        skipThreadTest();

        final Getter<String> getter = () -> NULL_STRING;
        assertEquals( NULL_STRING, getter.get() );

        final NumberSupplier supplier = () -> Integer.MAX_VALUE;
        assertEquals( Integer.MAX_VALUE, supplier.get() );
        assertEquals( Integer.MAX_VALUE, supplier.getAsNumber() );

        final Setter<String> setter = s -> {/* Does nothing */};
        setter.set( NULL_STRING );

        final TriConsumer<Integer,Integer,Integer> consumer = ( v1, v2, v3) -> {/* Does nothing */};
        consumer.accept( 1, 2, 3 );
        consumer.andThen( (v1, v2, v3) -> {/* Does nothing */} ).accept( 1, 2, 3 );

        final TriFunction<Integer,Integer,Integer,Integer> function = ( v1, v2, v3) -> v1 + v2 + v3;
        assertEquals( 6, function.apply( 1, 2, 3 ).intValue() );
    }   //  cover()

    /**
     *  Validates whether the class is static.
     */
    @Test
    final void validateClass()
    {
        assertTrue( validateAsStaticClass( Functions.class ) );
    }   //  validateClass()
}
//  class TestFunctions

/*
 *  End of File
 */