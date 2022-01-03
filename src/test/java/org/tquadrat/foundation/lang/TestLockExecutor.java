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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.lang.AutoLock.ExecutionFailedException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Tests for the interface
 *  {@link LockExecutor}
 *  and its implementation
 *  {@link org.tquadrat.foundation.lang.internal.LockExecutorImpl}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestLockExecutor.java 944 2021-12-21 21:56:24Z tquadrat $
 */
@SuppressWarnings( "MisorderedAssertEqualsArguments" )
@ClassVersion( sourceVersion = "$Id: TestLockExecutor.java 944 2021-12-21 21:56:24Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.lang.TestLockExecutor" )
public class TestLockExecutor extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Call the methods of
     *  {@link LockExecutor}.
     *  and
     *  {@link org.tquadrat.foundation.lang.internal.LockExecutorImpl}
     *  to reach the desired coverage ratio.
     */
    @SuppressWarnings( "resource" )
    @Test
    final void testExecute()
    {
        skipThreadTest();

        final var lock = AutoLock.of();
        final var candidate = LockExecutor.of( lock );

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

        final var lock = AutoLock.of();
        final var candidate = LockExecutor.of( lock );

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

        final Lock lock = null;
        assertThrows( expectedException, () -> LockExecutor.of( lock ) );

        final AutoLock autoLock = null;
        assertThrows( expectedException, () -> LockExecutor.of( autoLock ) );
    }   //  testOfWithNullArgument()
}
//  class TestLockExecutor

/*
 *  End of File
 */