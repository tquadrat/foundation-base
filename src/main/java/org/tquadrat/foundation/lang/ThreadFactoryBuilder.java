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

import static org.apiguardian.api.API.Status.STABLE;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.function.IntFunction;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.ValidationException;
import org.tquadrat.foundation.lang.internal.ThreadFactoryBuilderImpl;

/**
 *  <p>{@summary A builder for an implementation of
 *  {@link ThreadFactory}.}</p>
 *  <p>All values are optional, but {@code null} is no valid argument value for
 *  any method in this API.</p>
 *  <p>The simplest use case to get a valid
 *  {@link ThreadFactory}
 *  instance is</p>
 *  <pre><code>final var threadFactory = ThreadFactoryBuilder.obtainBuilder()
 *    .build();</code></pre>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ThreadFactoryBuilder.java 1118 2024-03-15 16:14:15Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: ThreadFactoryBuilder.java 1118 2024-03-15 16:14:15Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public sealed interface ThreadFactoryBuilder
    permits ThreadFactoryBuilderImpl
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Creates the instance of
     *  {@link java.util.concurrent.ThreadFactory}.
     *
     *  @return The thread factory.
     */
    public ThreadFactory build();

    /**
     *  Returns an instance of the builder.
     *
     *  @return A thread factory builder.
     */
    public static ThreadFactoryBuilder obtainBuilder() { return new ThreadFactoryBuilderImpl(); }

    /**
     *  <p>{@summary Sets the context class loader for the new threads created
     *  by the new thread factory.}</p>
     *  <p>The context class loader allows the creator of the thread to provide
     *  the appropriate class loader, through
     *  {@link Thread#getContextClassLoader()},
     *  to code running in the thread when loading classes and resources.</p>
     *  <p>If not set, new threads will use the system class loader (or,
     *  failing that, the bootstrap class loader).</p>
     *
     *  @param  contextClassLoader  The context classloader.
     *  @return The builder.
     *
     *  @see Thread#setContextClassLoader(ClassLoader)
     */
    public ThreadFactoryBuilder setContextClassLoader( final ClassLoader contextClassLoader );

    /**
     *  <p>{@summary Sets the flag that controls whether the new threads,
     *  created by the new thread factory, will suppress, or not, the
     *  inheriting of initial values for
     *  {@linkplain InheritableThreadLocal inheritable thread-local variables}
     *  from the constructing thread.} This allows for finer grain control over
     *  inheritable thread-locals. Care must be taken when setting the flag to
     *  {@code false}, as it may lead to unexpected behavior if the new thread
     *  executes code that expects a specific thread-local value to be
     *  inherited.</p>
     *  <p>If not set, the value for the new threads will be {@code true}.</p>
     *
     *  @param  flag    {@code true} if the values for inheritable thread
     *      locals will be inherited, {@code false} to disable this feature.
     *  @return The builder.
     *
     *  @see Thread#Thread(ThreadGroup, Runnable, String, long, boolean)
     */
    public ThreadFactoryBuilder setInheritThreadLocals( final boolean flag );

    /**
     *  <p>{@summary Sets the flag whether the new thread created by the new
     *  thread factory should be a daemon thread.} Daemon threads will be
     *  aborted automatically when the last non-daemon thread terminates.</p>
     *  <p>If not set, the new threads are not daemon threads.</p>
     *
     *  @param  flag    {@code true} if the new threads are daemon threads,
     *      {@code false} if they are regular threads.
     *  @return The builder.
     *
     *  @see Thread#isDaemon()
     *  @see Thread#setDaemon(boolean)
     */
    public ThreadFactoryBuilder setDaemon( final boolean flag );

    /**
     *  <p>{@summary Sets the factory for the name of the threads created by
     *  the new thread factory.}</p>
     *  <p>The name factory method will be call with a numeric argument that is
     *  unique for each invocation by the new thread factory; in fact, it is a
     *  counter value.</p>
     *  <p>If not set, a name factory is used that returns names looking like
     *  this: &quot;{@code Thread-#}&quot; where the hash symbol will be
     *  replaced by the counter value.</p>
     *
     *  @param  nameFactory The name factory.
     *  @return The builder.
     */
    public ThreadFactoryBuilder setNameFactory( final IntFunction<String> nameFactory );

    /**
     *  <p>{@summary Sets the priority for the new threads created by the new
     *  thread factory.} Proper values are -1 (to indicate that the priority
     *  should not be set explicitly) and numbers between
     *  {@value Thread#MIN_PRIORITY}
     *  to
     *  {@value Thread#MAX_PRIORITY},
     *  inclusively.</p>
     *  <p>If not set, the value will be -1.</p>
     *  <p>If the thread factory will not be configured explicitly with a
     *  priority, the newly created threads will get their priority set equal
     *  to the priority of the thread calling
     *  {@link ThreadFactory#newThread(Runnable)},
     *  creating a new thread. The method
     *  {@link Thread#setPriority(int)}
     *  may be used to change the priority to a new value.</p>
     *
     *  @param  priority    The priority value.
     *  @return The builder.
     *  @throws ValidationException The given value is not valid.
     *
     *  @see Thread#setPriority(int)
     */
    public ThreadFactoryBuilder setPriority( final int priority ) throws ValidationException;

    /**
     *  <p>{@summary Sets the stack size for the new threads created by the new
     *  thread factory.} The stack size is the approximate number of bytes of
     *  address space that the virtual machine is to allocate for the new
     *  thread's stack. The effect of the {@code stackSize} parameter, if any,
     *  is highly platform dependent.</p>
     *  <p>On some platforms, specifying a higher value for the
     *  {@code stackSize} parameter may allow a thread to achieve greater
     *  recursion depth before throwing a
     *  {@link StackOverflowError}.
     *  Similarly, specifying a lower value may allow a greater number of
     *  threads to exist concurrently without throwing an
     *  {@link OutOfMemoryError}
     *  (or other internal error). The details of the relationship between the
     *  value of the {@code stackSize} parameter and the maximum recursion
     *  depth and concurrency level are platform-dependent. On some platforms,
     *  the value of the {@code stackSize} parameter may have no effect
     *  whatsoever.</p>
     *  <p>The virtual machine is free to treat the stackSize parameter as a
     *  suggestion. If the specified value is unreasonably low for the
     *  platform, the virtual machine may instead use some platform-specific
     *  minimum value; if the specified value is unreasonably high, the virtual
     *  machine may instead use some platform-specific maximum. Likewise, the
     *  virtual machine is free to round the specified value up or down as it
     *  sees fit (or to ignore it completely).</p>
     *  <p>Specifying a value of zero for the {@code stackSize} parameter will
     *  cause the new tread factory to not set the stack size for the new
     *  threads explicitly.</p>
     *
     *  @note Due to the platform-dependent nature of the behaviour regarding
     *      this configuration value, extreme care should be exercised in its
     *      use. The thread stack size necessary to perform a given computation
     *      will likely vary from one JRE implementation to another. In light
     *      of this variation, careful tuning of the stack size parameter may
     *      be required, and the tuning may need to be repeated for each JRE
     *      implementation on which an application is to run.
     *
     *  @param  stackSize   The stack size for the new threads.
     *  @return The builder.
     *
     *  @see Thread#Thread(ThreadGroup, Runnable, String, long, boolean)
     *  @see Thread#Thread(ThreadGroup, Runnable, String, long)
     */
    public ThreadFactoryBuilder setStackSize( final long stackSize );

    /**
     *  <p>{@summary Sets the thread group for the new threads created by the
     *  new thread factory.}</p>
     *  <p>If no thread group will be specified, the thread group for threads
     *  created by the new thread factory is set to the thread group of the
     *  thread that called .
     *  {@link ThreadFactory#newThread(Runnable)}.</p>
     *
     *  @param  threadGroup The thread group.
     *  @return The builder.
     *
     *  @see Thread#Thread(ThreadGroup, Runnable, String, long, boolean)
     *  @see Thread#Thread(ThreadGroup, Runnable, String, long)
     *  @see Thread#Thread(ThreadGroup, String)
     *  @see Thread#Thread(ThreadGroup, Runnable)
     *  @see Thread#Thread(ThreadGroup, Runnable, String)
     */
    public ThreadFactoryBuilder setThreadGroup( final ThreadGroup threadGroup );

    /**
     *  <p>{@summary Sets the
     *  {@link java.lang.Thread.UncaughtExceptionHandler UncaughtExceptionHandler}
     *  for the new threads created by the new thread factory.}</p>
     *  <p>No handler will be set if none is specified for the new factory.</p>
     *
     *  @param  uncaughtExceptionHandler    The uncaught exception handler.
     *  @return The builder.
     *
     *  @see Thread#setUncaughtExceptionHandler(UncaughtExceptionHandler)
     *  @see Thread#setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler)
     *  @see Thread#getUncaughtExceptionHandler()
     *  @see Thread#getDefaultUncaughtExceptionHandler()
     *  @see ThreadGroup#uncaughtException(Thread, Throwable)
     */
    public ThreadFactoryBuilder setUncaughtExceptionHandler( final UncaughtExceptionHandler uncaughtExceptionHandler );
}
//  interface ThreadFactoryBuilder

/*
 *  End of File
 */