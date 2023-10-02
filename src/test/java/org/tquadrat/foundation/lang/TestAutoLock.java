/*
 * ============================================================================
 * Copyright © 2002-2021 by Thomas Thrien.
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.lang.AutoLock.ExecutionFailedException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Tests for the interface
 *  {@link AutoLock}
 *  and its implementation
 *  {@link org.tquadrat.foundation.lang.internal.AutoLockImpl}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestAutoLock.java 1061 2023-09-25 16:32:43Z tquadrat $
 */
@SuppressWarnings( "MisorderedAssertEqualsArguments" )
@ClassVersion( sourceVersion = "$Id: TestAutoLock.java 1061 2023-09-25 16:32:43Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.TestAutoLock" )
public class TestAutoLock extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Call the methods of
     *  {@link AutoLock}.
     *  and
     *  {@link org.tquadrat.foundation.lang.internal.AutoLockImpl}
     *  to reach the desired coverage ratio.
     */
    @DisplayName( "AutoLock cover" )
    @Test
    final void cover()
    {
        skipThreadTest();

        final var sourceLock = new ReentrantLock();

        @SuppressWarnings( "resource" )
        final var lock = AutoLock.of( sourceLock );
        assertNotNull( lock );

        try( final var localLock = lock.lock() )
        {
            assertNotNull( localLock );

            final var l = localLock.getWrappedLockInstance();
            assertNotNull( l );
            assertEquals( sourceLock, l );
            assertSame( sourceLock, l );

            assertEquals( sourceLock.getHoldCount(), 1 );
            assertTrue( sourceLock.isHeldByCurrentThread() );
            assertTrue( sourceLock.isLocked() );
        }

        assertEquals( sourceLock.getHoldCount(), 0 );
        assertFalse( sourceLock.isHeldByCurrentThread() );
        assertFalse( sourceLock.isLocked() );

        try( final var localLock = lock.lockInterruptibly() )
        {
            assertNotNull( localLock );

            final var l = localLock.getWrappedLockInstance();
            assertNotNull( l );
            assertEquals( sourceLock, l );
            assertSame( sourceLock, l );

            assertEquals( sourceLock.getHoldCount(), 1 );
            assertTrue( sourceLock.isHeldByCurrentThread() );
            assertTrue( sourceLock.isLocked() );
        }
        catch( @SuppressWarnings( "unused" ) final InterruptedException e ) { /* Deliberately ignored */ }

        assertEquals( sourceLock.getHoldCount(), 0 );
        assertFalse( sourceLock.isHeldByCurrentThread() );
        assertFalse( sourceLock.isLocked() );

        assertNotNull( lock.newCondition() );

        /*
         * Nothing should happen if AutoLock.close() is called without holding
         * the lock.
         */
        lock.close();
    }   //  cover()

    /**
     *  Tests for the method
     *  {@link AutoLock#of(java.util.concurrent.locks.Lock)}.
     */
    @SuppressWarnings( "resource" )
    @Test
    final void testExecute()
    {
        skipThreadTest();

        final var candidate = AutoLock.of();

        final var inputStream = InputStream.nullInputStream();

        final var result = candidate.execute( inputStream::readAllBytes );
        assertNotNull( result );

        //---* Close the stream, causing the exception below … *---------------
        candidate.execute( inputStream::close );

        final Throwable e = assertThrows( ExecutionFailedException.class, () -> candidate.execute( inputStream::readAllBytes ) );
        assertTrue( e.getCause() instanceof IOException );
    }   //  testExecute()

    /**
     *  Tests for the method
     *  {@link AutoLock#of(java.util.concurrent.locks.Lock)}.
     */
    @SuppressWarnings( "resource" )
    @Test
    final void testExecuteWithNullArgument()
    {
        skipThreadTest();

        final var candidate = AutoLock.of();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;

        final Action action = null;
        assertThrows( expectedException, () -> candidate.execute( action ) );

        final Operation<?> operation = null;
        assertThrows( expectedException, () -> candidate.execute( operation ) );
    }   //  testOfWithNullArgument()

    /**
     *  Tests for the method
     *  {@link AutoLock#of(java.util.concurrent.locks.Lock)}.
     */
    @SuppressWarnings( "resource" )
    @Test
    final void testOfWithNullArgument()
    {
        skipThreadTest();

        final Class<? extends Throwable> expectedException = NullArgumentException.class;
        try
        {
            AutoLock.of( null );
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
    }   //  testOfWithNullArgument()
}
//  class TestAutoLock

/*
 *  End of File
 */