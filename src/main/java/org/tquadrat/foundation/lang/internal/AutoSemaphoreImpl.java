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

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;

import java.io.Serial;
import java.util.concurrent.Semaphore;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.AutoSemaphore;

/**
 *  <p>{@summary The implementation of
 *  {@link AutoSemaphore}.} At the same time, this class extends
 *  {@link Semaphore}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: AutoSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $
 *  @since 0.25.2
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: AutoSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $" )
@API( status = STABLE, since = "0.4.8" )
public final class AutoSemaphoreImpl extends Semaphore implements AutoSemaphore
{
        /*---------------*\
    ====** Inner Classes **====================================================
        \*---------------*/
    /**
     *  <p>{@summary The implementation of
     *  {@link AutoSemaphore.Token}.}</p>
     *
     *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
     *  @version $Id: AutoSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $
     *  @since 0.25.2
     *
     *  @UMLGraph.link
     */
    @ClassVersion( sourceVersion = "$Id: AutoSemaphoreImpl.java 1250 2026-05-25 16:55:30Z tquadrat $" )
    @API( status = INTERNAL, since = "0.25.2" )
    public final class TokenImpl implements AutoSemaphore.Token
    {
            /*------------*\
        ====** Attributes **===================================================
            \*------------*/
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
         */
        public TokenImpl( final int permits )
        {
            m_Permits = permits;
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
            AutoSemaphoreImpl.this.release( m_Permits );
        }   //  close()

        /**
         *  {@inheritDoc}
         */
        @Override
        public final Semaphore getSemaphore() { return AutoSemaphoreImpl.this; }
    }
    //  class TokenImpl

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

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates an {@code AutoSemaphoreImpl} instance with the given number of
     *  permits and non-fair fairness setting.
     *
     *  @param  permits The initial number of permits available. This value may
     *      be negative, in which case releases must occur before any acquires
     *      will be granted.
     */
    public AutoSemaphoreImpl( final int permits )
    {
        super( permits );
    }   //  AutoSemaphoreImpl()

    /**
     *  Creates an {@code AutoSemaphoreImpl} instance with the given number of
     *  permits and the given fairness setting.
     *
     *  @param  permits The initial number of permits available. This value may
     *      be negative, in which case releases must occur before any acquires
     *      will be granted.
     *  @param  fair    {@code true} if this semaphore will guarantee first-in
     *      first-out granting of permits under contention, else {@code false}.
     */
    public AutoSemaphoreImpl( final int permits, final boolean fair )
    {
        super( permits, fair );
    }   //  AutoSemaphoreImpl()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "ReturnOfInnerClass" )
    public final Token acquireToken( final int permits ) throws InterruptedException, IllegalArgumentException
    {
        acquire( permits );
        final var retValue = new TokenImpl( permits );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  acquireToken()

    /**
     *  {@inheritDoc}
     */
    @Override
    @SuppressWarnings( "ReturnOfInnerClass" )
    public final Token acquireTokenUninterruptibly( final int permits ) throws IllegalArgumentException
    {
        acquireUninterruptibly( permits );
        final var retValue = new TokenImpl( permits );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  acquireTokenUninterruptibly()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final Semaphore getSemaphore() { return this; }
}
//  class AutoSemaphore

/*
 *  End of File
 */