/*
 * ============================================================================
 * Copyright © 2002-2022 by Thomas Thrien.
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.Objects.requireNotBlankArgument;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.BlankArgumentException;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  The tests for the method
 *  {@link org.tquadrat.foundation.lang.Objects#TestRequireNotBlankArgument(Object, String)}.
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestRequireNotBlankArgument.java 1025 2022-03-11 16:26:00Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestRequireNotBlankArgument.java 1025 2022-03-11 16:26:00Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.objects.TestRequireNotBlankArgument" )
public class TestRequireNotBlankArgument extends TestBaseClass
{
       /*---------*\
    ====** Methods **==========================================================
       \*---------*/
    /**
     *  Test method for
     *  {@link Objects#TestRequireNotBlankArgument(Object,String)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#notBlankTestArgumentProvider" )
    public final void testRequireNotBlankArgument1( final Object candidate )
    {
        skipThreadTest();

        assumeTrue( candidate instanceof CharSequence );

        assertEquals( candidate, requireNotBlankArgument( (CharSequence) candidate, "message" ) );
        assertSame( candidate, requireNotBlankArgument( (CharSequence) candidate, "message" ) );
    }   //  testRequireNotBlankArgument1()

    /**
     *  Test method for
     *  {@link Objects#requireNotBlankArgument(Object,String)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#blankTestArgumentProvider" )
    public final void testRequireNotEmptyArgument1WithBlankArgument( final CharSequence candidate )
    {
        skipThreadTest();

        assertThrows( BlankArgumentException.class, () -> requireNotBlankArgument( candidate, "arg" ) );
    }   //  testRequireNotEmptyArgument1WithBlankArgument()

    /**
     *  Test method for
     *  {@link Objects#requireNotBlankArgument(Object,String)}.
     *
     *  @param  candidate   The value for the test.
     */
    @ParameterizedTest
    @MethodSource( "org.tquadrat.foundation.lang.helper.ValueProviders#emptyTestArgumentProvider" )
    public final void testRequireNotBlankArgument1WithEmptyArgument( final Object candidate )
    {
        skipThreadTest();

        assumeTrue( candidate instanceof CharSequence );

        assertThrows( EmptyArgumentException.class, () -> requireNotBlankArgument( (CharSequence) candidate, "arg" ) );
    }   //  testRequireNotBlankArgument1WithEmptyArgument()

    /**
     *  Test method for
     *  {@link Objects#requireNotBlankArgument(Object,String)}.
     */
    @Test
    final void testRequireNotBlankArgumentWithEmptyMessage()
    {
        skipThreadTest();

        final var exception = assertThrows( EmptyArgumentException.class, () -> requireNotBlankArgument( "value", EMPTY_STRING ) );
        assertTrue( exception.getMessage().contains ( "name" ) );
    }   //  testRequireNotBlankArgumentWithEmptyMessage()

    /**
     *  Test method for
     *  {@link Objects#requireNotBlankArgument(Object,String)}.
     */
    @SuppressWarnings( "SpellCheckingInspection" )
    @Test
    final void testRequireNotBlankArgumentWithNullArgument()
    {
        skipThreadTest();

        final var name = "XXargXX";
        final var exception = assertThrows( NullArgumentException.class, () -> requireNotBlankArgument( null, name ) );
        assertTrue( exception.getMessage().contains ( name ) );
    }   //  testRequireNotEmptyArgumentWithNullArgument()

    /**
     *  Test method for
     *  {@link Objects#requireNotBlankArgument(Object,String)}.
     */
    @Test
    final void testRequireNotBlankArgumentWithNullMessage()
    {
        skipThreadTest();

        final var exception = assertThrows( NullArgumentException.class, () -> requireNotBlankArgument( "value", EMPTY_STRING ) );
        assertTrue( exception.getMessage().contains ( "name" ) );
    }   //  testRequireNotBlankArgumentWithNullMessage()
}
//  class TestRequireNotBlankArgument

/*
 *  End of File
 */