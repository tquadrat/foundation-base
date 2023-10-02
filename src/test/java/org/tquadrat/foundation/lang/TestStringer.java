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

package org.tquadrat.foundation.lang;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;
import static org.tquadrat.foundation.lang.Stringer.BYTEARRAY_STRINGER;
import static org.tquadrat.foundation.lang.Stringer.CLASS_STRINGER;
import static org.tquadrat.foundation.lang.Stringer.DEFAULT_STRINGER;
import static org.tquadrat.foundation.lang.Stringer.FILEOBJECT_STRINGER;
import static org.tquadrat.foundation.lang.Stringer.OBJECTCLASS_STRINGER;
import static org.tquadrat.foundation.lang.Stringer.OBJECT_STRINGER;
import static org.tquadrat.foundation.lang.Stringer.OPTIONAL_STRINGER;
import static org.tquadrat.foundation.lang.Stringer.STRING_STRINGER;
import static org.tquadrat.foundation.lang.Stringer.URL_STRINGER;
import static org.tquadrat.foundation.lang.Stringer.asFunction;

import java.io.File;
import java.time.Instant;
import java.util.Formattable;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.helper.FormattableObject;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Test for the interface
 *  {@link org.tquadrat.foundation.lang.Stringer}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestStringer.java 1061 2023-09-25 16:32:43Z tquadrat $
 *
 *  @see org.tquadrat.foundation.lang.Objects#toString(Object, Stringer, String)
 */
@ClassVersion( sourceVersion = "$Id: TestStringer.java 1061 2023-09-25 16:32:43Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.TestStringer" )
public class TestStringer extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Test for the method
     *  {@link Stringer#asFunction(Stringer)}.
     */
    @Test
    final void testAsFunction()
    {
        skipThreadTest();

        final var function = asFunction( URL_STRINGER );
        assertNotNull( function );
    }   //  testAsFunction()

    /**
     *  Tests the predefined instances of
     *  {@link org.tquadrat.foundation.lang.Stringer}.
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testStringers() throws Exception
    {
        skipThreadTest();

        String actual, expected;

        {
            final var candidate = new byte [] { 1, 2, 3 };
            expected = format( "byte [%d]", candidate.length );
            actual = Objects.toString( candidate, BYTEARRAY_STRINGER, NULL_STRING );
            assertEquals( expected, actual );
        }

        {
            final Class<?> candidate = String.class;
            expected = candidate.getName();
            actual = Objects.toString( candidate, CLASS_STRINGER, NULL_STRING );
            assertEquals( expected, actual );
        }

        {
            final var candidate = Instant.now();
            expected = Objects.toString( candidate );
            actual = Objects.toString( candidate, DEFAULT_STRINGER, NULL_STRING );
            assertEquals( expected, actual );
        }

        {
            final var now = Instant.now();
            final Formattable candidate = new FormattableObject( now );
            expected = Objects.toString( candidate );
            actual = Objects.toString( candidate, DEFAULT_STRINGER, NULL_STRING );
            assertEquals( expected, actual );
        }

        {
            final var candidate = new File( "." ).getAbsoluteFile().getCanonicalFile();
            expected = candidate.getAbsolutePath();
            actual = Objects.toString( candidate, FILEOBJECT_STRINGER, NULL_STRING );
            assertEquals( expected, actual );
        }

        {
            final var candidate = Instant.now();
            expected = Objects.toString( candidate );
            actual = Objects.toString( candidate, OBJECT_STRINGER, NULL_STRING );
            assertEquals( expected, actual );
        }

        {
            final var now = Instant.now();
            final Formattable candidate = new FormattableObject( now );
            expected = Objects.toString( candidate );
            actual = Objects.toString( candidate, OBJECT_STRINGER, NULL_STRING );
            assertNotEquals( expected, actual );
            expected = format( "%s", candidate );
            assertEquals( expected, actual );
        }

        {
            final var candidate = NULL_STRING;
            expected = candidate.getClass().getName();
            actual = Objects.toString( candidate, OBJECTCLASS_STRINGER, NULL_STRING );
            assertEquals( expected, actual );
        }

        {
            var value = NULL_STRING;
            var candidate = Optional.of( value );
            expected = format( "Optional: %s = %s", value.getClass().getName(), value );
            actual = Objects.toString( candidate, OPTIONAL_STRINGER, NULL_STRING );
            assertEquals( expected, actual );

            value = null;
            //noinspection OptionalOfNullableMisuse
            candidate = Optional.ofNullable( value );
            expected = "Optional: [empty]";
            actual = Objects.toString( candidate, OPTIONAL_STRINGER, NULL_STRING );
            assertEquals( expected, actual );
        }

        {
            final var candidate = NULL_STRING;
            expected = candidate;
            actual = Objects.toString( candidate, STRING_STRINGER, NULL_STRING );
            assertEquals( expected, actual );
        }

        //noinspection UnnecessaryCodeBlock
        {
            final var candidate = new File( "." ).getAbsoluteFile().getCanonicalFile().toURI().toURL();
            expected = candidate.toExternalForm();
            actual = Objects.toString( candidate, URL_STRINGER, NULL_STRING );
            assertEquals( expected, actual );
        }
    }   //  testStringers()
}
//  class TestStringer

/*
 *  End of File
 */