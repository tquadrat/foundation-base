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

package org.tquadrat.foundation.function.functions;

import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.tquadrat.foundation.function.Functions.wrapBiConsumer;
import static org.tquadrat.foundation.function.Functions.wrapBiFunction;
import static org.tquadrat.foundation.function.Functions.wrapBinaryOperator;
import static org.tquadrat.foundation.function.Functions.wrapConsumer;
import static org.tquadrat.foundation.function.Functions.wrapFunction;
import static org.tquadrat.foundation.function.Functions.wrapPredicate;
import static org.tquadrat.foundation.function.Functions.wrapSupplier;
import static org.tquadrat.foundation.function.Functions.wrapTriConsumer;
import static org.tquadrat.foundation.function.Functions.wrapTriFunction;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;
import static org.tquadrat.foundation.testutil.TestUtils.isNotEmptyOrBlank;

import java.time.Instant;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.LambdaContainerException;
import org.tquadrat.foundation.function.Functions;
import org.tquadrat.foundation.function.tce.TCEBiConsumer;
import org.tquadrat.foundation.function.tce.TCEBiFunction;
import org.tquadrat.foundation.function.tce.TCEBinaryOperator;
import org.tquadrat.foundation.function.tce.TCEConsumer;
import org.tquadrat.foundation.function.tce.TCEFunction;
import org.tquadrat.foundation.function.tce.TCEPredicate;
import org.tquadrat.foundation.function.tce.TCESupplier;
import org.tquadrat.foundation.function.tce.TCETriConsumer;
import org.tquadrat.foundation.function.tce.TCETriFunction;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Tests for the class
 *  {@link Functions}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestWrappers.java 1061 2023-09-25 16:32:43Z tquadrat $
 */
@ClassVersion( sourceVersion = "$Id: TestWrappers.java 1061 2023-09-25 16:32:43Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.function.functions.TestWrappers" )
public class TestWrappers extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Test the wrappers in
     *  {@link Functions}.
     */
    @SuppressWarnings( {"unused", "null", "ProhibitedExceptionThrown"} )
    @Test
    final void testWrappers()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = LambdaContainerException.class;

        TCEBiConsumer<String,Instant> tceConsumer2 = ( a1, a2) -> {/* Does nothing*/};
        var consumer2 = wrapBiConsumer( tceConsumer2 );
        consumer2.accept( NULL_STRING, Instant.now() );
        try
        {
            tceConsumer2 = (a1,a2) ->
            {
                throw new Exception();
            };
            consumer2 = wrapBiConsumer( tceConsumer2 );
            consumer2.accept( NULL_STRING, Instant.now() );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        TCEBiFunction<String,String,String> tceFunction2 = (v1,v2) -> v1 + v2;
        var function2 = wrapBiFunction( tceFunction2 );
        assertEquals( "nullnull", function2.apply( NULL_STRING, NULL_STRING ) );
        try
        {
            tceFunction2 = (v1,v2) ->
            {
                //noinspection StringEquality
                if( v1 == v2 ) throw new Exception();
                return null;
            };
            function2 = wrapBiFunction( tceFunction2 );
            function2.apply( NULL_STRING, NULL_STRING );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        TCEBinaryOperator<String> tceBinaryOperator = (v1,v2 ) -> v1 + v2;
        var operator = wrapBinaryOperator( tceBinaryOperator );
        assertEquals( "nullnull", operator.apply( NULL_STRING, NULL_STRING ) );
        try
        {
            tceBinaryOperator = (v1,v2) ->
            {
                //noinspection StringEquality
                if( v1 == v2 ) throw new Exception();
                return null;
            };
            operator = wrapBinaryOperator( tceBinaryOperator );
            operator.apply( NULL_STRING, NULL_STRING );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        TCEConsumer<String> tceConsumer = s -> {/* Does nothing*/};
        var consumer = wrapConsumer( tceConsumer );
        consumer.accept( NULL_STRING );
        try
        {
            tceConsumer = s -> {throw new Exception();};
            consumer = wrapConsumer( tceConsumer );
            consumer.accept( NULL_STRING );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        TCEFunction<String,String> tceFunction = s -> s;
        var function = wrapFunction( tceFunction );
        assertEquals( NULL_STRING, function.apply( NULL_STRING ) );
        try
        {
            tceFunction = s ->
            {
                if( isNotEmptyOrBlank( s ) ) throw new Exception();
                return null;
            };
            function = wrapFunction( tceFunction );
            function.apply( NULL_STRING );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        TCEPredicate<String> tcePredicate = Objects::isNull;
        var predicate = wrapPredicate( tcePredicate );
        assertFalse( predicate.test( NULL_STRING ) );
        try
        {
            tcePredicate = s ->
            {
                if( isNotEmptyOrBlank( s ) ) throw new Exception();
                return false;
            };
            predicate = wrapPredicate( tcePredicate );
            predicate.test( NULL_STRING );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        TCESupplier<String> tceSupplier = () -> NULL_STRING;
        var supplier = wrapSupplier( tceSupplier );
        assertEquals( NULL_STRING, supplier.get() );
        try
        {
            tceSupplier = () ->
            {
                if( nonNull( expectedException ) ) throw new Exception();
                return NULL_STRING;
            };
            supplier = wrapSupplier( tceSupplier );
            supplier.get();
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        TCETriConsumer<String,String,String> tceConsumer3 = (s1,s2,s3) ->  {/* Does nothing*/};
        var consumer3 = wrapTriConsumer( tceConsumer3 );
        consumer3.accept( NULL_STRING, NULL_STRING, NULL_STRING );
        try
        {
            tceConsumer3 = (a1,a2,a3) ->
            {
                throw new Exception();
            };
            consumer3 = wrapTriConsumer( tceConsumer3 );
            consumer3.accept( NULL_STRING, NULL_STRING, NULL_STRING );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }

        TCETriFunction<Integer,Integer,Integer,Integer> tceFunction3 = (v1, v2, v3) -> v1 + v2 + v3;
        var function3 = wrapTriFunction( tceFunction3 );
        assertEquals( 6, function3.apply( 1, 2, 3 ).intValue() );
        try
        {
            tceFunction3 = (v1,v2,v3) ->
            {
                //noinspection NumberEquality
                if( v1 == v2 ) throw new Exception();
                return v3;
            };
            function3 = wrapTriFunction( tceFunction3 );
            function3.apply( 1, 1, 2 );
            fail( () -> format( MSG_ExceptionNotThrown, expectedException.getName() ) );
        }
        catch( final AssertionError e ) { throw e; }
        catch( final Throwable t )
        {
            final var isExpectedException = expectedException.isInstance( t );
            assertTrue( isExpectedException, () -> format( MSG_WrongExceptionThrown, expectedException.getName(), t.getClass().getName() ) );
        }
    }   //  testWrappers()
}
//  class TestWrappers

/*
 *  End of File
 */