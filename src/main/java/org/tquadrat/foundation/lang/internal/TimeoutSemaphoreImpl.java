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

package org.tquadrat.foundation.lang.internal;

import static java.util.UUID.randomUUID;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apiguardian.api.API.Status.INTERNAL;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.io.Serial;
import java.lang.ref.Cleaner;
import java.lang.ref.Cleaner.Cleanable;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.AutoLock;
import org.tquadrat.foundation.lang.AutoSemaphore;

/**
 *  An implementation for
 *  {@link org.tquadrat.foundation.lang.AutoSemaphore}
 *  that allows a timeout for the permits.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TimeoutSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $
 *  @since 0.25.2
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "SerializableDeserializableClassInSecureContext" )
@ClassVersion( sourceVersion = "$Id: TimeoutSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $" )
@API( status = INTERNAL, since = "0.25.2" )
public final class TimeoutSemaphoreImpl extends Semaphore implements AutoSemaphore
{
        /*---------------*\
    ====** Inner Classes **====================================================
        \*---------------*/
    /**
     *  <p>{@summary The {@code Janitor} for the owning
     *  {@link TimeoutSemaphoreImpl}
     *  instance.}</p>
     *
     *  @param  reaperExecutor The reference for the
     *     {@link TimeoutSemaphoreImpl#m_ReaperExecutor}.
     *
     * @version $Id: TimeoutSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $
     * @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
     * @UMLGraph.link
     * @since 0.25.2
     */
    @SuppressWarnings( "NewClassNamingConvention" )
    @ClassVersion( sourceVersion = "$Id: TimeoutSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $" )
    @API( status = INTERNAL, since = "0.25.2" )
    private record Janitor( ScheduledExecutorService reaperExecutor ) implements Runnable
    {
            /*---------*\
        ====** Methods **======================================================
            \*---------*/
        /**
         * Performs the housekeeping.
         */
        @Override
        public final void run()
        {
            //---* Kill the executor *-----------------------------------------
            reaperExecutor.close();
        }   //  run()
    }
    //  class Janitor

    /**
     *  <p>{@summary The reaper thread.}</p>
     *
     *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
     *  @version $Id: TimeoutSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $
     *  @since 0.25.2
     *
     *  @UMLGraph.link
     */
    @SuppressWarnings( {"resource", "NewClassNamingConvention"} )
    @ClassVersion( sourceVersion = "$Id: TimeoutSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $" )
    @API( status = INTERNAL, since = "0.25.2" )
    private final class Reaper extends TimerTask
    {
            /*--------------*\
        ====** Constructors **=================================================
            \*--------------*/
        /**
         *  Creates a new instance of {@code Reaper}.
         */
        public Reaper() { /* Just exists */ }

            /*---------*\
        ====** Methods **======================================================
            \*---------*/
        /**
         *  {@inheritDoc}
         */
        @Override
        public final void run()
        {
            final var now = Instant.now();
            try( final var _ = m_Lock.lock() )
            {
                final var tokens = List.copyOf( m_Registry.values() );
                for( final var token : tokens )
                {
                    if( token.getEndOfLife().isBefore( now ) )
                    {
                        m_Registry.remove( token.getId() );
                    }
                }
            }
        }   //  run()
    }
    //  class Reaper

    /**
     *  <p>{@summary The token that holds the permits to be released when a
     *  {@code try-with-resources} block is left or the timeout has
     *  expired.}</p>
     *
     *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
     *  @version $Id: TimeoutSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $
     *  @since 0.25.2
     *
     *  @UMLGraph.link
     */
    @ClassVersion( sourceVersion = "$Id: TimeoutSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $" )
    @API( status = INTERNAL, since = "0.25.2" )
    public final class TokenImpl implements AutoSemaphore.Token
    {
            /*------------*\
        ====** Attributes **===================================================
            \*------------*/
        /**
         *  The end-of-life for this permit.
         */
        private final Instant m_EndOfLife;

        /**
         *  The id for this permit.
         */
        @SuppressWarnings( "FieldNamingConvention" )
        private final UUID m_Id;

        /**
         *  The number of permits to release on close.
         */
        private final int m_Permits;

            /*--------------*\
        ====** Constructors **=================================================
            \*--------------*/
        /**
         *  Creates a new instance of {@code TokenImpl}.
         *
         *  @param  permits The number of the acquired permits.
         *  @param  endOfLife   The end-of-life for this permit.
         */
        public TokenImpl( final int permits, final Instant endOfLife )
        {
            m_Permits = permits;
            m_EndOfLife = requireNonNullArgument( endOfLife, "endOfLife" );
            m_Id = randomUUID();
        }   //  TokenImpl()

            /*---------*\
        ====** Methods **======================================================
            \*---------*/
        /**
         *  {@inheritDoc}
         */
        @Override
        public final void close()
        {
            TimeoutSemaphoreImpl.this.release( m_Id );
        }   //  close()

        /**
         *  Returns the time for the end-of-life.
         *
         *  @return The end-of-life instant.
         */
        public final Instant getEndOfLife() { return m_EndOfLife; }

        /**
         *  Returns the id of the token.
         *
         *  @return The id.
         */
        public final UUID getId() { return m_Id; }

        /**
         *  Returns the number of permits for this token.
         *
         *  @return The number of permits.
         */
        public final int getPermits() { return m_Permits; }

        /**
         *  {@inheritDoc}
         */
        @Override
        public final Semaphore getSemaphore() { return TimeoutSemaphoreImpl.this; }
    }
    //  class TokenImpl

        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The cycle time for the reaper thread in milliseconds: {@value} ms.
     */
    public static final long REAPER_CYCLE = 1000L;

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  <p>{@summary The
     *  {@link Cleanable}
     *  for this instance.} As instances of this class does not support
     *  {@code close()} or something similar, keeping the reference to the
     *  {@code Cleanable} is considered obsolete. But we keep it anyway, in
     *  case this changes.</p>
     */
    @SuppressWarnings( {"unused", "FieldCanBeLocal"} )
    private final transient Cleanable m_Cleanable;

    /**
     *  The janitor for instances of this class.
     */
    @SuppressWarnings( {"UseOfConcreteClass", "FieldCanBeLocal"} )
    private final transient Janitor m_Janitor;

    /**
     *  The lock that guards the access to
     *  {@link #m_Registry}.
     */
    private final transient AutoLock m_Lock;

    /**
     *  The timeout duration.
     *
     *  @serial
     */
    private final Duration m_Timeout;

    /**
     *  The executor for the reaper.
     */
    private final transient ScheduledExecutorService m_ReaperExecutor;

    /**
     *  The permit registry.
     */
    private final transient Map<UUID,TokenImpl> m_Registry = new LinkedHashMap<>();

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     *
     *  @hidden
     */
    @Serial
    private static final long serialVersionUID = 539879857L;

    /**
     *  The cleaner that is used for the instances of this class.
     */
    private static final Cleaner m_Cleaner;

    static
    {
        m_Cleaner = Cleaner.create();
    }

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates an {@code TimeoutSemaphoreImpl} instance with the given number
     *  of permits, the given timeout duration and non-fair fairness setting.
     *
     *  @param  permits The initial number of permits available. This value may
     *      be negative, in which case releases must occur before any acquires
     *      will be granted.
     *  @param  timeout The timeout.
     */
    public TimeoutSemaphoreImpl( final int permits, final Duration timeout )
    {
        this( permits, false, timeout );
    }   //  TimeoutSemaphoreImpl()

    /**
     *  Creates an {@code TimeoutSemaphoreImpl} instance with the given number
     *  of permits, the given timeout duration and the given fairness setting.
     *
     *  @param  permits The initial number of permits available. This value may
     *      be negative, in which case releases must occur before any acquires
     *      will be granted.
     *  @param  fair    {@code true} if this semaphore will guarantee first-in
     *      first-out granting of permits under contention, else {@code false}.
     *  @param  timeout The timeout.
     */
    public TimeoutSemaphoreImpl( final int permits, final boolean fair, final Duration timeout )
    {
        super( permits, fair );
        m_Timeout = requireNonNullArgument( timeout, "timeout" );
        m_Lock = AutoLock.of();

        m_ReaperExecutor = newSingleThreadScheduledExecutor();
        m_ReaperExecutor.scheduleWithFixedDelay( new Reaper(), REAPER_CYCLE, REAPER_CYCLE, MILLISECONDS );

        m_Janitor = new Janitor( m_ReaperExecutor );
        m_Cleanable = registerJanitor( m_Janitor );
    }   //  TimeoutSemaphoreImpl()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "ReturnOfInnerClass" )
    @Override
    public final Token acquireToken( final int permits ) throws InterruptedException, IllegalArgumentException
    {
        acquire( permits );
        final var retValue = createToken( permits );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  acquireToken()

    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "ReturnOfInnerClass" )
    @Override
    public final Token acquireTokenUninterruptibly( final int permits ) throws IllegalArgumentException
    {
        acquireUninterruptibly( permits );
        final var retValue = createToken( permits );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  acquireTokenUninterruptibly()

    /**
     *  Creates a token and adds to the registry.
     *
     *  @param  permits The number of permits to acquire.
     *  @return The new token.
     */
    private final Token createToken( final int permits )
    {
        final var endOfLife = Instant.now().plus( m_Timeout );
        final var retValue = new TokenImpl( permits, endOfLife );
        m_Lock.execute( () -> m_Registry.put( retValue.getId(), retValue ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createToken()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Semaphore getSemaphore() { return this; }

    /**
     *  Registers the janitor that is doing the housekeeping on garbage
     *  collection.
     *
     *  @param  janitor The janitor.
     *  @return The
     *      {@link Cleanable}
     *      for this instance.
     */
    private final Cleanable registerJanitor( final Runnable janitor )
    {
        final var retValue = m_Cleaner.register( this, requireNonNullArgument( janitor, "janitor" ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  registerJanitor()

    /**
     *  <p>{@summary Releases the number of permits associated with the token
     *  with the given id, returning them to the semaphore.}</p>
     *  <p>Releases that number of permits, increasing the number of available
     *  permits by that amount.</p>
     *  <p>Nothing happens if the token with given id had died already.</p>
     *
     *  @param  id  The id of the token.
     */
    @SuppressWarnings( "PublicMethodNotExposedInInterface" )
    public final void release( final UUID id )
    {
        m_Lock.execute( () -> m_Registry.remove( id ) )
            .ifPresent( token -> release( token.getPermits() ) );
    }   //  release()
}
//  class TimeoutSemaphoreImpl

/*
 *  End of File
 */