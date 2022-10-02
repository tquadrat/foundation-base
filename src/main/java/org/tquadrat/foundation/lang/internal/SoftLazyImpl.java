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
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.lang.ref.SoftReference;
import java.util.Optional;
import java.util.function.Supplier;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.lang.SoftLazy;

/**
 *  The implementation for the
 *  {@link SoftLazy}
 *  interface.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: SoftLazyImpl.java 1031 2022-04-07 22:43:02Z tquadrat $
 *  @since 0.1.0
 *
 *  @param  <T> The type of the cached data.
 */
@API( status = INTERNAL, since = "0.1.0" )
@ClassVersion( sourceVersion = "$Id: SoftLazyImpl.java 1031 2022-04-07 22:43:02Z tquadrat $" )
public final class SoftLazyImpl<T> implements SoftLazy<T>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  An empty array of {@code SoftLazyImpl<T>} objects.
     */
    @SuppressWarnings( "rawtypes" )
    public static final SoftLazyImpl [] EMPTY_SoftLazyImpl_ARRAY = new SoftLazyImpl [0];

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The initializer method.
     */
    private final Supplier<T> m_Initializer;

    /**
     *  The data reference.
     */
    @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" )
    private Optional<SoftReference<T>> m_Reference = Optional.empty();

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code SoftLazyImpl<T>} instance.
     *
     *  @param  initializer The initializer method.
     */
    public SoftLazyImpl( final Supplier<T> initializer )
    {
        m_Initializer = requireNonNullArgument( initializer, "initializer" );
    }   //  SoftLazyImpl<T>()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Creates the reference to the data.
     *
     *  @return The reference.
     */
    private final SoftReference<T> createReference()
    {
        final var data = m_Initializer.get();
        final var retValue = new SoftReference<>( data );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  createReference()

    /**
     *  {@inheritDoc}
     */
    @Override
    public final T get()
    {
        var updateReference = m_Reference.isEmpty();
        var reference = m_Reference.orElseGet( this::createReference );
        var retValue = reference.get();
        if( isNull( retValue ) )
        {
            updateReference = true;
            reference = createReference();
            retValue = reference.get();
        }
        if( updateReference ) m_Reference = Optional.of( reference );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  get()
}
//  class SoftLazyImpl

/*
 *  End of File
 */