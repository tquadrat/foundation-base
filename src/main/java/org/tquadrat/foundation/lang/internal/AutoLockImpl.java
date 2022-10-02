/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
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

package org.tquadrat.foundation.lang.internal;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.Action;
import org.tquadrat.foundation.lang.AutoLock;
import org.tquadrat.foundation.lang.Operation;

/**
 *  The implementation of
 *  {@link AutoLockImpl}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: AutoLockImpl.java 1031 2022-04-07 22:43:02Z tquadrat $
 *  @since 0.1.0
 *
 *  @see java.util.concurrent.locks.Lock
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: AutoLockImpl.java 1031 2022-04-07 22:43:02Z tquadrat $" )
@API( status = INTERNAL, since = "0.1.0" )
public final class AutoLockImpl implements AutoLock
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The wrapped lock.
     */
    private final Lock m_Lock;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code AutoLockImpl} instance.
     *
     *  @param  lock    The wrapped lock.
     */
    public AutoLockImpl( final Lock lock )
    {
        super();
        m_Lock = requireNonNullArgument( lock, "lock" );
    }   //  AutoLockImpl()

    /**
     *  Creates a new {@code AutoLockImpl} instance with an internal lock
     *  object.
     */
    public AutoLockImpl() { this( new ReentrantLock() ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "ProhibitedExceptionCaught" )
    @Override
    public final void close()
    {
        try
        {
            m_Lock.unlock();
        }
        catch( final IllegalMonitorStateException ignored ) { /* Deliberately ignored */ }
    }   //  close()

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings( "OverlyBroadCatchBlock" )
    @Override
    public void execute( final Action action ) throws ExecutionFailedException
    {
        requireNonNullArgument( action, "action" );
        try( final var ignore = lock() )
        {
            action.run();
        }
        catch( final Throwable t )
        {
            throw new ExecutionFailedException( t );
        }
    }   //  execute()

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings( "OverlyBroadCatchBlock" )
    @Override
    public <R> Optional<R> execute( final Operation<? extends R> operation ) throws ExecutionFailedException
    {
        requireNonNullArgument( operation, "operation" );
        final Optional<R> retValue;
        try( final var ignore = lock() )
        {
            retValue = Optional.ofNullable( operation.get() );
        }
        catch( final Throwable t )
        {
            throw new ExecutionFailedException( t );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  execute()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Lock getWrappedLockInstance() { return m_Lock; }

    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "LockAcquiredButNotSafelyReleased" )
    @Override
    public final AutoLock lock()
    {
        m_Lock.lock();

        //---* Done *----------------------------------------------------------
        return this;
    }   //  lock()

    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "LockAcquiredButNotSafelyReleased" )
    @Override
    public final AutoLock lockInterruptibly() throws InterruptedException
    {
        m_Lock.lockInterruptibly();

        //---* Done *----------------------------------------------------------
        return this;
    }   //  lockInterruptibly()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Condition newCondition() { return m_Lock.newCondition(); }
}
//  class AutoLock

/*
 *  End of File
 */