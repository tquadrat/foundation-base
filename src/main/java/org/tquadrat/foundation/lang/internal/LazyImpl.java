/*
 * ============================================================================
 * Copyright © 2002-2021 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
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
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.nonNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.util.Optional;
import java.util.function.Supplier;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.AutoLock;
import org.tquadrat.foundation.lang.Lazy;
import org.tquadrat.foundation.lang.Objects;

/**
 *  <p>{@summary The implementation of the interface
 *  {@link Lazy}.}</p>
 *  <p>An instance of this class holds a value that will be initialised by a
 *  call to the supplier (provided with the constructor
 *  {@link #LazyImpl(Supplier)})
 *  on a first call to
 *  {@link #get()}.</p>
 *  <p>For special purposes, the constructor
 *  {@link #LazyImpl(Object)}
 *  creates an already initialised instance of {@code Lazy}.</p>
 *  <p>Use
 *  {@link #isPresent()}
 *  to avoid unnecessary initialisation.</p>
 *  <p>As a lazy initialisation makes the value unpredictable, it is necessary
 *  that the implementations of
 *  {@link #equals(Object)}
 *  and
 *  {@link #hashCode()}
 *  force the initialisation.</p>
 *  <p>{@link #toString()} do not force the initialisation.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: LazyImpl.java 840 2021-01-10 21:37:03Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 *
 *  @param  <T> The type of the value for this instance of {@code Lazy}.
 */
@ClassVersion( sourceVersion = "$Id: LazyImpl.java 840 2021-01-10 21:37:03Z tquadrat $" )
@API( status = INTERNAL, since = "0.0.5" )
public final class LazyImpl<T> implements Lazy<T>
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The lock that protects the value creation. It will be set to
     *  {@linkplain Optional#empty() empty}
     *  after
     *  {@link #m_Value} is initialised.
     */
    @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" )
    private Optional<AutoLock> m_Lock;

    /**
     *  The supplier for the value of this {@code Lazy} instance. It will be
     *  set to {@code null} after
     *  {@link #m_Value}
     *  is initialised.
     */
    private Supplier<T> m_Supplier;

    /**
     *  The value of this {@code Lazy} instance; it is {@code null} if it was
     *  not yet initialised.
     */
    private T m_Value;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code Lazy} instance.
     *
     *  @param  supplier    The supplier that initialises the value for this
     *      instance on the first call to
     *      {@link #get()}.
     */
    public LazyImpl( final Supplier<T> supplier )
    {
        m_Supplier = requireNonNullArgument( supplier, "supplier" );
        m_Lock = Optional.of( AutoLock.of() );
        m_Value = null;
    }   //  LazyImpl()

    /**
     *  <p>{@summary Creates a new {@code Lazy} instance that is already
     *  initialised.}</p>
     *  <p>This allows to use {@code Lazy} instances also for
     *  {@linkplain java.lang.Cloneable cloneable}
     *  objects, given that {@code T} is either cloneable itself or
     *  immutable.</p>
     *
     *  @param  value   The value; can be {@code null}.
     *
     *  @see Object#clone()
     */
    public LazyImpl( final T value )
    {
        m_Supplier = null;
        m_Lock = Optional.empty();
        m_Value = value;
    }   //  LazyImpl()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean equals( final Object obj )
    {
        var retValue = this == obj;
        if( !retValue && nonNull( obj ) )
        {
            if( obj instanceof Lazy other )
            {
                retValue = get().equals( other.get() );
            }
            else
            {
                retValue = get().equals( obj );
            }
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  equals()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final T get()
    {
        m_Lock.ifPresent( l ->
        {
            try( @SuppressWarnings( "unused" ) final var lock = l.lock() )
            {
                /*
                 * When the lock is present, the supplier must have been not
                 * null; this is enforced by the respective constructor.
                 * So when the supplier is NOW null, another thread has
                 * performed the initialisation while this one was waiting for
                 * the lock.
                 */
                if( nonNull( m_Supplier ) )
                {
                    m_Value = m_Supplier.get();
                    m_Lock = Optional.empty();
                    m_Supplier = null;
                }
            }
        } );

        //---* Done *----------------------------------------------------------
        return m_Value;
    }   //  get()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final int hashCode() { return get().hashCode(); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public final boolean isPresent() { return isNull( m_Supplier ); }

    /**
     *  {@inheritDoc}
     */
    @Override
    public <X extends Throwable> T orElseThrow( final Supplier<? extends X> exceptionSupplier ) throws X
    {
        if( !isPresent() ) throw exceptionSupplier.get();

        //---* Done *----------------------------------------------------------
        return m_Value;
    }   //  orElseThrow()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final var retValue = Objects.toString( m_Value, isPresent() ? NULL_STRING : "[Not initialized]" );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()
}
//  class LazyImpl

/*
 *  End of File
 */