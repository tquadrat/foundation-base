/*
 * ============================================================================
 *  Copyright © 2002-2023 by Thomas Thrien.
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

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;
import static java.lang.Thread.UncaughtExceptionHandler;
import static org.apiguardian.api.API.Status.INTERNAL;
import static org.tquadrat.foundation.lang.Objects.nonNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.util.StringJoiner;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntFunction;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.ValidationException;
import org.tquadrat.foundation.lang.Objects;
import org.tquadrat.foundation.lang.ThreadFactoryBuilder;

/**
 *  The implementation of
 *  {@link ThreadFactoryBuilder}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ThreadFactoryBuilderImpl.java 1035 2022-12-07 15:09:56Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: ThreadFactoryBuilderImpl.java 1035 2022-12-07 15:09:56Z tquadrat $" )
@API( status = INTERNAL, since = "0.1.0" )
public final class ThreadFactoryBuilderImpl implements ThreadFactoryBuilder
{
        /*---------------*\
    ====** Inner Classes **====================================================
        \*---------------*/
    /**
     *  The implementation of
     *  {@link ThreadFactoryBuilder}.
     *
     *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
     *  @version $Id: ThreadFactoryBuilderImpl.java 1035 2022-12-07 15:09:56Z tquadrat $
     *  @since 0.1.0
     *
     *  @UMLGraph.link
     */
    @ClassVersion( sourceVersion = "$Id: ThreadFactoryBuilderImpl.java 1035 2022-12-07 15:09:56Z tquadrat $" )
    @API( status = INTERNAL, since = "0.1.0" )
    private static final class ThreadFactoryImpl implements ThreadFactory
    {
            /*------------*\
        ====** Attributes **===================================================
            \*------------*/
        /**
         *  The context
         *  {@link ClassLoader}
         *  for the new threads. Can be {@code null}.
         */
        private final ClassLoader m_ContextClassLoader;

        /**
         *  If {@code true}, inherit initial values for inheritable
         *  thread-locals from the constructing thread, otherwise no initial
         *  values are inherited.
         */
        private final boolean m_InheritThreadLocals;

        /**
         *  {@code true} if the new threads are daemon thread, {@code false}
         *  otherwise.
         */
        private final boolean m_IsDaemon;

        /**
         *  The counter for the thread name.
         */
        private final AtomicInteger m_NameCounter = new AtomicInteger( 0 );

        /**
         *  The factory method for the thread names.
         */
        private final IntFunction<String> m_NameFactory;

        /**
         *  The priority for the new threads. A value of -1 indicates that no
         *  priority will be set explicitly.
         */
        private final int m_Priority;

        /**
         *  The desired stack size for the new threads, or zero to indicate
         *  that this parameter is to be ignored.
         */
        private final long m_StackSize;

        /**
         *  The thread group for the new threads. Can be {@code null}.
         */
        private final ThreadGroup m_ThreadGroup;

        /**
         *  The uncaught exception handler for the new threads; can be
         *  {@code null}.
         */
        private final UncaughtExceptionHandler m_UncaughtExceptionHandler;

            /*--------------*\
        ====** Constructors **=================================================
            \*--------------*/
        /**
         *  Creates a new instance of {@code ThreadFactoryImpl}.
         *
         *  @param  nameFactory The factory method for the thread names.
         *  @param  threadGroup The thread group for the new thread; can be
         *      {@code null}.
         *  @param  stackSize   The desired stack size for the new threads, or
         *      zero to indicate that this parameter is to be ignored.
         *  @param  inheritThreadLocals If {@code true}, inherit initial values
         *      for inheritable thread-locals from the constructing thread,
         *      otherwise no initial values are inherited.
         *  @param  contextClassLoader  The context class loader for the new
         *      threads; can be {@code null}.
         *  @param  isDaemon    {@code true} if the new threads are daemon
         *      threads, {@code false} otherwise.
         *  @param  priority    The priority for the new threads. A value of -1
         *      indicates that no priority will be set explicitly.
         *  @param  uncaughtExceptionHandler    The handler for uncaught
         *      exceptions for the new threads; can be {@code null}.
         */
        @SuppressWarnings( {"BooleanParameter", "ConstructorWithTooManyParameters"} )
        public ThreadFactoryImpl( final IntFunction<String> nameFactory, final ThreadGroup threadGroup, final long stackSize, final boolean inheritThreadLocals, final ClassLoader contextClassLoader, final boolean isDaemon, final int priority, final UncaughtExceptionHandler uncaughtExceptionHandler )
        {
            m_NameFactory = requireNonNullArgument( nameFactory, "nameFactory" );
            m_ThreadGroup = threadGroup;
            m_StackSize = stackSize;
            m_InheritThreadLocals = inheritThreadLocals;
            m_ContextClassLoader = contextClassLoader;
            m_IsDaemon = isDaemon;
            m_Priority = priority;
            m_UncaughtExceptionHandler = uncaughtExceptionHandler;
        }   //  ThreadFactoryImpl()

            /*---------*\
        ====** Methods **======================================================
            \*---------*/
        /**
         *  Returns the name for the new thread.
         *
         *  @return The new thread's name.
         */
        private final String generateName() { return m_NameFactory.apply( m_NameCounter.incrementAndGet() ); }

        /**
         *  {@inheritDoc}
         */
        @Override
        public final Thread newThread( final Runnable target )
        {
            final var retValue = new Thread( m_ThreadGroup, target, generateName(), m_StackSize, m_InheritThreadLocals );
            if( nonNull( m_ContextClassLoader ) ) retValue.setContextClassLoader( m_ContextClassLoader );
            retValue.setDaemon( m_IsDaemon );
            if( (MIN_PRIORITY <= m_Priority) && (m_Priority <= MAX_PRIORITY) ) retValue.setPriority( m_Priority );
            if( nonNull( m_UncaughtExceptionHandler ) ) retValue.setUncaughtExceptionHandler( m_UncaughtExceptionHandler );

            //---* Done *------------------------------------------------------
            return retValue;
        }   //  newThread()

        /**
         *  {@inheritDoc}
         */
        @Override
        public final String toString()
        {
            final var buffer = new StringJoiner( ", ", "%s[".formatted(  getClass().getName() ), "]" )
                .add( "ContextClassLoader='%s'".formatted(  Objects.toString( m_ContextClassLoader ) ) )
                .add( "InheritThreadLocals=%b".formatted(  m_InheritThreadLocals ) )
                .add( "IsDaemon=%b".formatted(  m_IsDaemon ) )
                .add( "NameFactory='%s' (returns \"%s\")".formatted(  Objects.toString( m_NameFactory ), m_NameFactory.apply( 1 ) ) )
                .add( "Priority=%d".formatted(  m_Priority ) )
                .add( "StackSize=%d byte".formatted(  m_StackSize ) )
                .add( "ThreadGroup='%s' (name: %s)".formatted(  Objects.toString( m_ThreadGroup ), nonNull( m_ThreadGroup ) ? m_ThreadGroup.getName() : "n/a" ) )
                .add( "UncaughtExceptionHandler='%s'".formatted(  Objects.toString( m_UncaughtExceptionHandler ) ) );

            //---* Compose the return value *--------------------------------------
            final var retValue = buffer.toString();

            //---* Done *----------------------------------------------------------
            return retValue;
        }   //  toString()
    }
    //  class ThreadFactoryImpl

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The context
     *  {@link ClassLoader}
     *  for the new threads. The default is {@code null}.
     */
    private ClassLoader m_ContextClassLoader = null;

    /**
     *  If {@code true}, inherit initial values for inheritable
     *  thread-locals from the constructing thread, otherwise no initial
     *  values are inherited. The default is {@code true}.
     */
    private boolean m_InheritThreadLocals = true;

    /**
     *  {@code true} if the new threads are daemon thread, {@code false}
     *  otherwise. The default is {@code false}.
     */
    private boolean m_IsDaemon = false;

    /**
     *  The factory method for the thread names. The default returns
     *  &quot;{@code Thread-#}&quot;, where &quot;#&quot; stands for a counter.
     */
    private IntFunction<String> m_NameFactory = "Thread-%d"::formatted;

    /**
     *  The priority for the new threads. The default value of -1 indicates
     *  that no priority will be set explicitly.
     */
    private int m_Priority = -1;

    /**
     *  The desired stack size for the new threads. The default value of zero
     *  indicates that this parameter is to be ignored.
     */
    private long m_StackSize = 0;

    /**
     *  The thread group for the new threads. The default is {@code null}.
     */
    private ThreadGroup m_ThreadGroup = null;

    /**
     *  The uncaught exception handler for the new threads. The default is
     *  {@code null}.
     */
    private UncaughtExceptionHandler m_UncaughtExceptionHandler = null;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@code ThreadFactoryBuilderImpl}.
     */
    public ThreadFactoryBuilderImpl() { /* Just exists */ }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final ThreadFactory build()
    {
        final var retValue = new ThreadFactoryImpl( m_NameFactory, m_ThreadGroup, m_StackSize, m_InheritThreadLocals, m_ContextClassLoader, m_IsDaemon, m_Priority, m_UncaughtExceptionHandler );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  build()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final ThreadFactoryBuilderImpl setContextClassLoader( final ClassLoader contextClassLoader )
    {
        m_ContextClassLoader = requireNonNullArgument( contextClassLoader, "contextClassLoader" );

        //---* Done *----------------------------------------------------------
        return this;
    }   //  setContextClassLoader()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final ThreadFactoryBuilderImpl setInheritThreadLocals( final boolean flag )
    {
        m_InheritThreadLocals = flag;

        //---* Done *----------------------------------------------------------
        return this;
    }   //  setInheritThreadLocals()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final ThreadFactoryBuilderImpl setDaemon( final boolean flag )
    {
        m_IsDaemon = flag;

        //---* Done *----------------------------------------------------------
        return this;
    }   //  setDaemon()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final ThreadFactoryBuilderImpl setNameFactory( final IntFunction<String> nameFactory )
    {
        m_NameFactory = requireNonNullArgument( nameFactory, "nameFactory" );

        //---* Done *----------------------------------------------------------
        return this;
    }   //  setNameFactory()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final ThreadFactoryBuilderImpl setPriority( final int priority ) throws ValidationException
    {
        if( (priority != -1) && !((MIN_PRIORITY <= priority) && (priority <= MAX_PRIORITY)) )
        {
            throw new ValidationException( "Priority value '%3$d' is invalid; it has to be -1, or in the range from %1$d to %2$d, included"
                .formatted( MIN_PRIORITY, MAX_PRIORITY, priority ) );
        }
        m_Priority = priority;

        //---* Done *----------------------------------------------------------
        return this;
    }   //  setPriority()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final ThreadFactoryBuilderImpl setStackSize( final long stackSize )
    {
        if( stackSize < 0 ) throw new ValidationException( "stackSize is less than zero" );
        m_StackSize = stackSize;

        //---* Done *----------------------------------------------------------
        return this;
    }   //  setStackSize()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final ThreadFactoryBuilderImpl setThreadGroup( final ThreadGroup threadGroup )
    {
        m_ThreadGroup = requireNonNullArgument( threadGroup, "threadGroup" );

        //---* Done *----------------------------------------------------------
        return this;
    }   //  setThreadGroup()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final ThreadFactoryBuilderImpl setUncaughtExceptionHandler( final UncaughtExceptionHandler uncaughtExceptionHandler )
    {
        m_UncaughtExceptionHandler = requireNonNullArgument( uncaughtExceptionHandler, "uncaughtExceptionHandler" );

        //---* Done *----------------------------------------------------------
        return this;
    }   //  setUncaughtExceptionHandler()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final var buffer = new StringJoiner( ", ", "%s[".formatted(  getClass().getName() ), "]" )
            .add( "ContextClassLoader='%s'".formatted(  Objects.toString( m_ContextClassLoader ) ) )
            .add( "InheritThreadLocals=%b".formatted(  m_InheritThreadLocals ) )
            .add( "IsDaemon=%b".formatted(  m_IsDaemon ) )
            .add( "NameFactory='%s' (returns \"%s\")".formatted(  Objects.toString( m_NameFactory ), m_NameFactory.apply( 1 ) ) )
            .add( "Priority=%d".formatted(  m_Priority ) )
            .add( "StackSize=%d byte".formatted(  m_StackSize ) )
            .add( "ThreadGroup='%s' (name: %s)".formatted(  Objects.toString( m_ThreadGroup ), nonNull( m_ThreadGroup ) ? m_ThreadGroup.getName() : "n/a" ) )
            .add( "UncaughtExceptionHandler='%s'".formatted(  Objects.toString( m_UncaughtExceptionHandler ) ) );

        //---* Compose the return value *--------------------------------------
        final var retValue = buffer.toString();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()
}
//  class ThreadFactoryBuilderImpl

/*
 *  End of File
 */