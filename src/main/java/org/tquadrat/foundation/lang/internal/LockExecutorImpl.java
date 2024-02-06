/*
 * ============================================================================
 *  Copyright © 2002-2021 by Thomas Thrien.
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

package org.tquadrat.foundation.lang.internal;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.util.Optional;
import java.util.concurrent.locks.Lock;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.Action;
import org.tquadrat.foundation.lang.AutoLock;
import org.tquadrat.foundation.lang.AutoLock.ExecutionFailedException;
import org.tquadrat.foundation.lang.LockExecutor;
import org.tquadrat.foundation.lang.Operation;
import org.tquadrat.foundation.lang.Constraint;

/**
 *  The implementation of
 *  {@link LockExecutor}.
 *
 *  @version $Id: LockExecutorImpl.java 944 2021-12-21 21:56:24Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@ClassVersion( sourceVersion = "$Id: LockExecutorImpl.java 944 2021-12-21 21:56:24Z tquadrat $" )
@API( status = INTERNAL, since = "0.1.0" )
public final class LockExecutorImpl implements LockExecutor
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The lock.
     */
    private final AutoLock m_Lock;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates an instance of {@code LockExecutorImpl}.
     *
     *  @param  lock    The lock.
     */
    private LockExecutorImpl( final AutoLock lock )
    {
        m_Lock = lock;
    }   //  LockExecutorImpl()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate( final Constraint constraint ) throws ExecutionFailedException
    {
        final var retValue = m_Lock.evaluate( constraint );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  execute()

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute( final Action action ) throws ExecutionFailedException { m_Lock.execute( action ); }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Optional<T> execute( final Operation<T> operation ) throws ExecutionFailedException
    {
        final var retValue = m_Lock.execute( operation );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  execute()

    /**
     *  Creates a new {@code LockExecutor} from the given
     *  {@link Lock}
     *  instance.
     *
     *  @param  lock    The lock.
     *  @return The new {@code LockExecutor}.
     */
    public static final LockExecutor of( final Lock lock )
    {
        final var autoLock = AutoLock.of( lock );
        final var retValue = new LockExecutorImpl( autoLock );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  of()

    /**
     *  Creates a new {@code LockExecutor} from the given
     *  {@link AutoLock}
     *  instance.
     *
     *  @param  lock    The lock.
     *  @return The new {@code LockExecutor}.
     */
    public static final LockExecutor of( final AutoLock lock )
    {
        final var retValue = new LockExecutorImpl( requireNonNullArgument( lock, "lock" ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  of()
}
//  class LockExecutorImpl

/*
 *  End of File
 */