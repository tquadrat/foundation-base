/*
 * ============================================================================
 *  Copyright © 2002-2022 by Thomas Thrien.
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

package org.tquadrat.foundation.lang;

import static java.util.Map.entry;
import static org.apiguardian.api.API.Status.STABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.tquadrat.foundation.testutil.TestUtils.EMPTY_STRING;

import java.time.Instant;
import java.util.Map;
import java.util.Map.Entry;

import org.apiguardian.api.API;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Tests for the class
 *  {@link NameValuePair}.
 *
 *  @version $Id: TestNameValuePair.java 995 2022-01-23 01:09:35Z tquadrat $
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @since 0.1.0
 */
@ClassVersion( sourceVersion = "$Id: TestNameValuePair.java 995 2022-01-23 01:09:35Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
@DisplayName( "org.tquadrat.foundation.lang.TestNameValuePair" )
public class TestNameValuePair extends TestBaseClass
{
        /*---------------*\
    ====** Inner Classes **====================================================
        \*---------------*/
    /**
     *  An implementation of
     *  {@link java.util.Map.Entry}
     *  that allows {@code null} for key and value.
     */
    private static class NVPTestEntry implements Map.Entry<String,Instant>
    {
            /*------------*\
        ====** Attributes **===================================================
            \*------------*/
        /**
         *  The key.
         */
        private final String m_Key;

        /**
         *  The value.
         */
        private Instant m_Value;

            /*--------------*\
        ====** Constructors **=================================================
            \*--------------*/
        /**
         *  Creates a new instance of {@code NVPTestEntry}.
         *
         *  @param  key The key.
         *  @param  value   The value.
         */
        public NVPTestEntry( final String key, final Instant value )
        {
            m_Key = key;
            m_Value = value;
        }   //  NVPTestEntry()

            /*---------*\
        ====** Methods **======================================================
            \*---------*/
        /**
         *  {@inheritDoc}
         */
        @Override
        public final String getKey() { return m_Key; }

        /**
         *  {@inheritDoc}
         */
        @Override
        public final Instant getValue() { return m_Value; }

        /**
         *  {@inheritDoc}
         */
        @Override
        public final Instant setValue( final Instant value )
        {
            final var retValue = m_Value;
            m_Value = value;

            //---* Done *------------------------------------------------------
            return retValue;
        }   //  setValue()
    }
    //  class NVPTestEntry()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Test the constructors
     *  {@link NameValuePair#NameValuePair(String, Object)}
     *  and
     *  {@link NameValuePair#NameValuePair(Entry)}.
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testConstructors() throws Exception
    {
        skipThreadTest();

        final Map.Entry<String,Instant> nullEntry = null;
        assertThrows( NullArgumentException.class, () -> new NameValuePair<>( nullEntry ) );

        final var emptyNameEntry = entry( EMPTY_STRING, Instant.now() );
        assertThrows( EmptyArgumentException.class, () -> new NameValuePair<>( emptyNameEntry ) );

        /*
         * entry() does not allow null, neither for names nor for values, so we
         * have to use our own implementation.
         */
        final String nullName = null;
        final var nullNameEntry = new NVPTestEntry( nullName, Instant.now() );
        assertThrows( NullArgumentException.class, () -> new NameValuePair<>( nullNameEntry ) );

        assertThrows( NullArgumentException.class, () -> new NameValuePair<>( nullName, null ) );
        assertThrows( NullArgumentException.class, () -> new NameValuePair<>( nullName, Instant.now() ) );

        final var emptyName = EMPTY_STRING;
        assertThrows( EmptyArgumentException.class, () -> new NameValuePair<>( emptyName, null ) );
        assertThrows( EmptyArgumentException.class, () -> new NameValuePair<>( emptyName, Instant.now() ) );

        var candidate = new NameValuePair<>( "name", null );
        assertNotNull( candidate );

        candidate = new NameValuePair<>( new NVPTestEntry( "name", null ) );
        assertNotNull( candidate );
    }   //  testConstructors()

    /**
     *  Test the method
     *  {@link NameValuePair#newValue(Object)}.
     *
     *  @throws Exception   Something unexpected went wrong.
     */
    @Test
    final void testNewValue() throws Exception
    {
        skipThreadTest();

        final var name = "name";
        final var value = "value";
        final var newValue = "newValue";

        final var candidate = new NameValuePair<>( name, value );
        assertNotNull( candidate );
        assertEquals( name, candidate.name() );
        assertEquals( value, candidate.value() );

        var result = candidate.newValue( newValue );
        assertNotNull( result );
        assertNotSame( result, candidate );
        assertNotEquals( result, candidate );
        assertEquals( name, candidate.name() );
        assertEquals( value, candidate.value() );
        assertEquals( name, result.name() );
        assertEquals( newValue, result.value() );

        result = candidate.newValue( value );
        assertNotNull( result );
        assertNotSame( result, candidate );
        assertEquals( result, candidate );
        assertEquals( name, candidate.name() );
        assertEquals( value, candidate.value() );
        assertEquals( name, result.name() );
        assertEquals( value, result.value() );
    }   //  testNewValue()
}
//  class TestNameValuePair

/*
 *  End of File
 */