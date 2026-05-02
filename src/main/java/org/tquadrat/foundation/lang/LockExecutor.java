/*
 * ============================================================================
 *  Copyright © 2002-2026 by Thomas Thrien.
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
 *  <p>{@summary Allows to execute an operation with an obtained lock.}</p>
 *  <p>Use this class like below:</p>
 *  <div class="source-container"><pre>…
 *  final var lock = new ReentrantLock();
 *  final var executor = LockExecutor.of( lock );
 *  …
 *  executor.execute( () -> doSomething() );
 *  …</pre></div>
 *
 *  @note   If your program is using {@code AutoLock}, you should use the
 *      corresponding methods from there.
 *
 *  @version $Id: LockExecutor.java 1185 2026-04-06 10:26:47Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@ClassVersion( sourceVersion = "$Id: LockExecutor.java 1185 2026-04-06 10:26:47Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public sealed interface LockExecutor
    permits LockExecutorImpl
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Evaluates the given condition.
     *
     *  @param  constraint  The constraint.
     *  @return The result of the evaluation of the condition.
     *  @throws ExecutionFailedException    The evaluation failed for some
     *      reason.
     */
    @SuppressWarnings( "BooleanMethodNameMustStartWithQuestion" )
    public boolean evaluate( final Constraint constraint ) throws ExecutionFailedException;

    /**
     *  Executes the given action.
     *
     *  @param  action  The action.
     *  @throws ExecutionFailedException    The action failed for some
     *      reason.
     *  @deprecated Use
     *      {@link #perform(Action)}
     *      instead; basically this method was renamed to avoid the unintended
     *      use of
     *      {@link #execute(Operation)}
     *      when an
     *      {@link Action}
     *      should be used instead.
     */
    @Deprecated( since = "0.25.3", forRemoval = true )
    public default void execute( final Action action ) throws ExecutionFailedException
    {
        perform( action );
    }   //  execute()

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

    /**
     *  <p>{@summary Performs the given action after obtaining the lock.}</p>
     *  <p>This differs from
     *  {@link #execute(Operation)}
     *  as
     *  {@link Action}
     *  does not return something, and a warning regarding &quot;Result of
     *  assignment expression used&quot; can be avoided in case the
     *  {@code action} is used to set an attribute.</p>
     *
     *  @param  action  The action.
     *  @throws ExecutionFailedException    The action failed for some reason.
     */
    public void perform( final Action action ) throws ExecutionFailedException;
}
//  interface LockExecutor

/*
 *  End of File
 */