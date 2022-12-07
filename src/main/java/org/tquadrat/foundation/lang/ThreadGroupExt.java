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
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotBlankArgument;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary An implementation of
 *  {@link ThreadGroup}
 *  that allows to configure the behaviour of
 *  {@link #uncaughtException(Thread, Throwable)}}.</p>
 *  <p>This class is not final, to allow further modifications.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ThreadGroupExt.java 1035 2022-12-07 15:09:56Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: ThreadGroupExt.java 1035 2022-12-07 15:09:56Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public class ThreadGroupExt extends ThreadGroup
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The
     *  {@link java.lang.Thread.UncaughtExceptionHandler}
     *  for this thread group. It can be {@code null}.
     */
    private final UncaughtExceptionHandler m_UncaughtExceptionHandler;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  <p>{@summary Constructs a new thread group.}</p>
     *  <p>The parent of this new group is the thread group of the currently
     *  running thread.</p>
     *  <p>The behaviour of
     *  {@link #uncaughtException(Thread, Throwable)}
     *  is that of the superclass.</p>
     *
     *  @param  name    The name of the new thread group.
     *  @throws IllegalArgumentException    The {@code name} argument is either
     *      {@code null}, empty or blank.
     *  @throws SecurityException   The current thread cannot create a thread
     *      in this thread group.
     */
    public ThreadGroupExt( final String name ) throws IllegalArgumentException
    {
        super( requireNotBlankArgument( name, "name" ) );
        m_UncaughtExceptionHandler = null;
    }   //  ThreadGroupExt()

    /**
     *  <p>{@summary Constructs a new thread group.}</p>
     *  <p>The parent of this new group is the specified thread group.</p>
     *  <p>The behaviour of
     *  {@link #uncaughtException(Thread, Throwable)}
     *  is that of the superclass.</p>
     *
     *  @param  parent  The parent thread group.
     *  @param  name    The name of the new thread group.
     *  @throws IllegalArgumentException    The {@code name} argument is either
     *      {@code null}, empty or blank, or the {@code parent} thread group
     *      argument is {@code null}.
     *  @throws SecurityException   The current thread cannot create a thread
     *      in this thread group.
     */
    public ThreadGroupExt( final ThreadGroup parent, final String name ) throws IllegalArgumentException
    {
        super( requireNonNullArgument( parent, "parent" ), requireNotBlankArgument( name, "name" ) );
        m_UncaughtExceptionHandler = null;
    }   //  ThreadGroupExt()

    /**
     *  <p>{@summary Constructs a new thread group.}</p>
     *  <p>The parent of this new group is the thread group of the currently
     *  running thread.</p>
     *  <p>The behaviour of
     *  {@link #uncaughtException(Thread, Throwable)}
     *  is determined by the provided handler.</p>
     *
     *  @param  name    The name of the new thread group.
     *  @param  handler The handler for the uncaught exceptions.
     *  @throws IllegalArgumentException    The {@code name} argument is either
     *      {@code null}, empty or blank, or the {@code handler} argument is
     *      {@code null}.
     *  @throws SecurityException   The current thread cannot create a thread
     *      in this thread group.
     */
    public ThreadGroupExt( final String name, final UncaughtExceptionHandler handler ) throws IllegalArgumentException
    {
        super( requireNotBlankArgument( name, "name" ) );
        m_UncaughtExceptionHandler = requireNonNullArgument( handler, "handler" );
    }   //  ThreadGroupExt()

    /**
     *  <p>{@summary Constructs a new thread group.}</p>
     *  <p>The parent of this new group is the specified thread group.</p>
     *  <p>The behaviour of
     *  {@link #uncaughtException(Thread, Throwable)}
     *  is determined by the provided handler.</p>
     *
     *  @param  parent  The parent thread group.
     *  @param  name    The name of the new thread group.
     *  @param  handler The handler for the uncaught exceptions.
     *  @throws IllegalArgumentException    The {@code name} argument is either
     *      {@code null}, empty or blank, or one of the {@code parent} thread
     *      group  or the {@code handler} arguments is {@code null}.
     *  @throws SecurityException   The current thread cannot create a thread
     *      in this thread group.
     */
    public ThreadGroupExt( final ThreadGroup parent, final String name, final UncaughtExceptionHandler handler ) throws IllegalArgumentException
    {
        super( requireNonNullArgument( parent, "parent" ), requireNotBlankArgument( name, "name" ) );
        m_UncaughtExceptionHandler = requireNonNullArgument( handler, "handler" );
    }   //  ThreadGroupExt()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final void uncaughtException( final Thread t, final Throwable e )
    {
        if( isNull( m_UncaughtExceptionHandler ) )
        {
            super.uncaughtException( t, e );
        }
        else
        {
            m_UncaughtExceptionHandler.uncaughtException( t, e );
        }
    }   //  uncaughtException()
}
//  class ThreadGroupExt

/*
 *  End of File
 */