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
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Tests for the interface
 *  {@link Lazy}
 *  and its implementation
 *  {@link org.tquadrat.foundation.lang.internal.LazyImpl}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestLazy.java 1061 2023-09-25 16:32:43Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestLazy.java 1061 2023-09-25 16:32:43Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.util.TestLazy" )
public class TestLazy extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Tests for the method
     *  {@link Lazy#of(Object)}.
     */
    @Test
    final void testOf()
    {
        skipThreadTest();

        Optional<String> mapResult;

        final var l1 = Lazy.of( null );
        assertNotNull( l1 );
        assertTrue( l1.isPresent() );
        l1.ifPresent( Assertions::assertNull );
        assertNull( l1.get() );
        assertEquals( l1.getAsString(), NULL_STRING );
        mapResult = l1.map( Objects::toString );
        assertTrue( mapResult.isPresent() );
        assertEquals( mapResult.get(), NULL_STRING );

        final var list = List.of( "eins", "zwei", "drei" );
        final var l2 = Lazy.of( list );
        assertNotNull( l2 );
        assertTrue( l2.isPresent() );
        l2.ifPresent( Assertions::assertNotNull );
        l2.ifPresent( v -> assertEquals( list.size(), v.size() ) );
        l2.ifPresent( v -> assertSame( list, v ) );
        assertNotNull( l2.get() );
        assertSame( list, l2.get() );
        assertEquals( Objects.toString( list ), l2.getAsString() );
        mapResult = l2.map( Objects::toString );
        assertTrue( mapResult.isPresent() );
        assertEquals( Objects.toString( list ), mapResult.get() );
    }   //  testOf()

    /**
     *  Tests for the method
     *  {@link Lazy#use(java.util.function.Supplier)}.
     */
    @Test
    final void testUse()
    {
        skipThreadTest();

        final Supplier<List<String>> supplier = ArrayList::new;

        final var lazy = Lazy.use( supplier );
        assertNotNull( lazy );
        assertFalse( lazy.isPresent() );

        lazy.ifPresent( l -> fail( "Should not be called" ) );

        final Class<? extends Throwable> expectedException = IllegalStateException.class;
        try
        {
            lazy.orElseThrow( IllegalStateException::new );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            {
                t.printStackTrace( out );
            }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        var value = lazy.get();
        assertNotNull( value );
        assertTrue( lazy.isPresent() );

        lazy.ifPresent( Assertions::assertNotNull );

        value = lazy.orElseThrow( IllegalStateException::new );
        assertNotNull( value );
    }   //  testUse()

    /**
     *  Tests for the method
     *  {@link Lazy#use(java.util.function.Supplier)}.
     */
    @Test
    final void testUseWithNullArgument()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            Lazy.use( null );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            if( !isExpectedException )
            {
                t.printStackTrace( out );
            }
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testUseWithNullArgument()
}
//  class TestLazy

/*
 *  End of File
 */