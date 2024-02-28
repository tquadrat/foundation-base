/*
 * ============================================================================
 *  Copyright © 2002-2024 by Thomas Thrien.
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

package org.tquadrat.foundation.lang.objects;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.tquadrat.foundation.lang.Objects.mapFromNull;

import java.util.function.Supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Some tests for
 *  {@link org.tquadrat.foundation.lang.Objects#mapFromNull(Object, Supplier)}
 *  and
 *  {@link org.tquadrat.foundation.lang.Objects#mapFromNull(Object, Object)}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 */
@DisplayName( "org.tquadrat.foundation.lang.objects.TestMapFromNull" )
public class TestMapFromNull extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Tests
     *  {@link org.tquadrat.foundation.lang.Objects#mapFromNull(Object, Supplier)}
     *  and
     *  {@link org.tquadrat.foundation.lang.Objects#mapFromNull(Object, Object)}.
     *
     *  @throws Exception   Something went wrong unexpectedly.
     */
    @Test
    final void testMapFromNull() throws Exception
    {
        skipThreadTest();

        final var value = new Object();
        final var replacement = new Object();
        final Supplier<Object> supplier = () -> replacement;

        assertSame( value, mapFromNull( value, replacement ) );
        assertSame( value, mapFromNull( value, supplier ) );

        assertSame( replacement, mapFromNull( null, replacement ) );
        assertSame( replacement, mapFromNull( null, supplier ) );
    }   //  testMapFromNull()

    /**
     *  Tests
     *  {@link org.tquadrat.foundation.lang.Objects#mapFromNull(Object, Supplier)}
     *  and
     *  {@link org.tquadrat.foundation.lang.Objects#mapFromNull(Object, Object)}
     *  when providing {@code null} arguments.
     *
     *  @throws Exception   Something went wrong unexpectedly.
     */
    @Test
    final void testMapFromNull_WithNullArguments() throws Exception
    {
        skipThreadTest();

        final var value = new Object();

        assertThrows( NullArgumentException.class, () -> mapFromNull( value, (Object) null ) );
        assertThrows( NullArgumentException.class, () -> mapFromNull( value, (Supplier<Object>) null ) );
        assertThrows( NullArgumentException.class, () -> mapFromNull( null, (Object) null ) );
        assertThrows( NullArgumentException.class, () -> mapFromNull( null, (Supplier<Object>) null ) );
    }   //  testMapFromNull_WithNullArguments()
}
//  class TestMapFromNull

/*
 *  End of File
 */