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

package org.tquadrat.foundation.lang;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.Optional;
import java.util.concurrent.locks.Lock;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.AutoLock.ExecutionFailedException;
import org.tquadrat.foundation.lang.internal.LockExecutorImpl;

/**
 *  Allows to execute an operation with an obtained lock.
 *
 *  @version $Id: LockExecutor.java 944 2021-12-21 21:56:24Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@ClassVersion( sourceVersion = "$Id: LockExecutor.java 944 2021-12-21 21:56:24Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public sealed interface LockExecutor
    permits LockExecutorImpl
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Executes the given action.
     *
     *  @param  action  The action.
     *  @throws ExecutionFailedException    The action failed for some
     *      reason.
     */
    public void execute( final Action action ) throws ExecutionFailedException;

    /**
     *  Executes the given operation.
     *
     *  @param  <R> The type of the operation's result.
     *  @param  operation   The operation.
     *  @return An instance of
     *      {@link Optional}
     *      that holds the result of the operation.
     *  @throws ExecutionFailedException    The operation failed for some
     *      reason.
     */
    public <R> Optional<R> execute( final Operation<R> operation ) throws ExecutionFailedException;

    /**
     *  Creates a new {@code LockExecutor} from the given
     *  {@link Lock}
     *  instance.
     *
     *  @param  lock    The lock.
     *  @return The new {@code LockExecutor}.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static LockExecutor of( final Lock lock )
    {
        final var retValue = LockExecutorImpl.of( lock );

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
    @API( status = STABLE, since = "0.1.0" )
    public static LockExecutor of( final AutoLock lock )
    {
        final var retValue = LockExecutorImpl.of( lock );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  of()
}
//  interface LockExecutor

/*
 *  End of File
 */