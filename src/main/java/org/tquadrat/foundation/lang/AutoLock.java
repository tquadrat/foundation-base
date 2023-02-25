/*
 * ============================================================================
 * Copyright © 2002-2023 by Thomas Thrien.
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

package org.tquadrat.foundation.lang;

import static org.apiguardian.api.API.Status.STABLE;

import java.io.Serial;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.internal.AutoLockImpl;

/**
 *  <p>{@summary A wrapper for locks that supports the
 *  {@code try-with-resources} feature of Java&nbsp;7.}</p>
 *  <p>Instead of</p>
 *  <div class="source-container"><pre>  m_Lock.lock();
 *  try
 *  {
 *      &hellip;
 *  }
 *  finally
 *  {
 *      m_Lock.unlock();
 *  }</pre></div>
 *  <p>you can write now</p>
 *  <div class="source-container"><pre>  private final AutoLock m_AutoLock = AutoLock.of();
 *
 *  &hellip;
 *
 *  try( final var ignored = m_AutoLock.lock() )
 *  {
 *      &hellip;
 *  }</pre></div>
 *  <p>The creation of the local reference to the wrapper object means some
 *  overhead but in very most scenarios this is negligible.</p>
 *  <p>{@code AutoLock} will only expose the methods
 *  {@link #lock()}
 *  and
 *  {@link #lockInterruptibly()}
 *  of the interface
 *  {@link java.util.concurrent.locks.Lock Lock},
 *  but with a return value. Exposing other methods is not reasonable.</p>
 *  <p>Calling
 *  {@link #close()}
 *  on the {@code AutoLock} instance or
 *  {@link Lock#unlock()}
 *  on the wrapped {@code Lock} object inside the {@code try} block may cause
 *  unpredictable effects.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: AutoLock.java 1046 2023-02-10 15:44:45Z tquadrat $
 *  @since 0.1.0
 *
 *  @see java.util.concurrent.locks.Lock
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: AutoLock.java 1046 2023-02-10 15:44:45Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public sealed interface AutoLock extends AutoCloseable
    permits org.tquadrat.foundation.lang.internal.AutoLockImpl
{
        /*---------------*\
    ====** Inner Classes **====================================================
        \*---------------*/
    /**
     *  <p>{@summary This exception is thrown when an operation fails.} The
     *
     *  @version $Id: AutoLock.java 1046 2023-02-10 15:44:45Z tquadrat $
     *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
     *  @UMLGraph.link
     *  @since 0.1.0
     */
    @SuppressWarnings( "InnerClassOfInterface" )
    @ClassVersion( sourceVersion = "$Id: AutoLock.java 1046 2023-02-10 15:44:45Z tquadrat $" )
    @API( status = STABLE, since = "0.1.0" )
    public static final class ExecutionFailedException extends RuntimeException
    {
            /*------------------------*\
        ====** Static Initialisations **=======================================
            \*------------------------*/
        /**
         *  The serial version UID for objects of this class: {@value}.
         *
         *  @hidden
         */
        @Serial
        private static final long serialVersionUID = 1L;

            /*--------------*\
        ====** Constructors **=================================================
            \*--------------*/
        /**
         *  Creates a new instance of {@code ExecutionFailedException} from the
         *  cause of the failure.
         *
         *  @param  cause   The failure cause.
         */
        public ExecutionFailedException( final Throwable cause ) { super( cause ); }
    }   //  class ExecutionFailedException

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public void close();

    /**
     *  Executes the given action after obtaining the lock.
     *
     *  @param  action  The action.
     *  @throws ExecutionFailedException    The action failed for some reason.
     */
    public void execute( final Action action ) throws ExecutionFailedException;

    /**
     *  Executes the given operation after obtaining the lock, and returns its
     *  result.
     *
     *  @param  <R> The type of the operation's result.
     *  @param  operation   The operation.
     *  @return An instance of
     *      {@link Optional}
     *      that holds the result of the operation.
     *  @throws ExecutionFailedException    The operation failed for some
     *      reason.
     */
    public <R> Optional<R> execute( final Operation<? extends R> operation ) throws ExecutionFailedException;

    /**
     *  Returns the wrapped lock.
     *
     *  @return The wrapped lock.
     *
     *  @deprecated The name {@code getLock()} might cause confusion as it is
     *      not obviously enough distinguished from
     *      {@link #lock()}.
     *      Therefore, it was decided to rename it. Use
     *      {@link #getWrappedLockInstance()}
     *      instead.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    public default Lock getLock() { return getWrappedLockInstance(); }

    /**
     *  Returns the wrapped lock.
     *
     *  @return The wrapped lock.
     */
    public Lock getWrappedLockInstance();

    /**
     *  Calls
     *  {@link java.util.concurrent.locks.Lock#lock() lock()}
     *  on the wrapped
     *  {@link java.util.concurrent.locks.Lock}
     *  instance.
     *
     *  @return The reference to this {@code AutoLock} instance.
     */
    public AutoLock lock();

    /**
     *  Calls
     *  {@link java.util.concurrent.locks.Lock#lockInterruptibly() lockInterruptibly()}
     *  on the wrapped
     *  {@link java.util.concurrent.locks.Lock}
     *  instance.
     *
     *  @return The reference to this {@code AutoLock} instance.
     *  @throws InterruptedException The current thread was interrupted while
     *      acquiring the lock (and interruption of lock acquisition is
     *      supported).
     */
    public AutoLock lockInterruptibly() throws InterruptedException;

    /**
     *  Returns a new
     *  {@link Condition}
     *  instance that is bound to the instance of
     *  {@link java.util.concurrent.locks.Lock}
     *  that is wrapped by this {@code AutoLock} instance.
     *
     *  @return The new condition.
     *  @throws UnsupportedOperationException   The wrapped lock's
     *      implementation does not support conditions.
     *
     *  @see  java.util.concurrent.locks.Lock#newCondition()
     */
    public Condition newCondition();

    /**
     *  Creates a new {@code AutoLock} instance with an internal lock object.
     *
     *  @return The new instance.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static AutoLock of() { return new AutoLockImpl(); }

    /**
     *  Creates a new {@code AutoLock} instance from the given
     *  {@link Lock}
     *  instance.
     *
     *  @param  lock    The wrapped lock.
     *  @return The new instance.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static AutoLock of( final Lock lock ) { return new AutoLockImpl( lock ); }
}
//  interface AutoLock

/*
 *  End of File
 */