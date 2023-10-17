/*
 * ============================================================================
 * Copyright © 2002-2023 by Thomas Thrien.
 * All Rights Reserved.
 * ============================================================================
 *
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

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static org.apiguardian.api.API.Status.INTERNAL;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.nonNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.MountPoint;
import org.tquadrat.foundation.lang.StringConverter;

/**
 *  The default implementation of
 *  {@link StringConverter}
 *  for types that are derived from
 *  {@link Enum}.<br>
 *  <br>The implementation of
 *  {@link #fromString(CharSequence)}
 *  provided here uses
 *  {@link Class#getEnumConstants()}
 *  to find the {@code enum} value:
 *  <pre><code>  &hellip;
 *  T result = stream( m_EnumType.getEnumConstants() )
 *      .filter( constant -&gt; value.equals( constant.name() ) )
 *      .findFirst()
 *      .orElseThrow( () -&gt; new IllegalArgumentException( &hellip; );
 *  &hellip;</code></pre>
 *  <br>The implementation of
 *  {@link #toString(Enum)}
 *  in this class will return the value of
 *  {@link Enum#name()}.
 *
 *  @param  <T> The concrete data type that is handled by this string converter
 *      implementation.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: DefaultEnumStringConverter.java 1060 2023-09-24 19:21:40Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: DefaultEnumStringConverter.java 1060 2023-09-24 19:21:40Z tquadrat $" )
@API( status = INTERNAL, since = "0.1.0" )
public class DefaultEnumStringConverter<T extends Enum<T>> implements StringConverter<T>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The error message for the name of an unknown class on the command line:
     *  {@value}.
     */
    public static final String MSG_UnknownValue = "Unknown/invalid value: %1$s";

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The data type of the property to set.
     *
     *  @serial
     */
    private final Class<T> m_EnumType;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The serial version UID for objects of this class: {@value}.
     *
     *  @hidden
     */
    @Serial
    private static final long serialVersionUID = 1L;

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new {@code EnumValueHandler} instance.
     *
     *  @param  enumType    The data type for the property.
     */
    public DefaultEnumStringConverter( final Class<T> enumType )
    {
        m_EnumType = requireNonNullArgument( enumType, "enumType" );
    }   //  EnumValueHandler()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public T fromString( final CharSequence source ) throws IllegalArgumentException
    {
        T retValue = null;
        if( nonNull( source ) )
        {
            if( isNull( source ) || source.isEmpty() ) throw new IllegalArgumentException( format( MSG_UnknownValue, source ) );
            retValue = stream( m_EnumType.getEnumConstants() )
                .filter( constant -> source.toString().equals( constant.name() ) )
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException( format( MSG_UnknownValue, source ) ) );
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  fromString()

    /**
     *  Provides the subject class for this converter.
     *
     * @return The subject class.
     */
    @SuppressWarnings( "PublicMethodNotExposedInInterface" )
    public final Collection<Class<T>> getSubjectClass() { return List.of( m_EnumType ); }

    /**
     *  {@inheritDoc}
     */
    @MountPoint
    @Override
    public String toString( final T source )
    {
        final var retValue = isNull( source ) ? null : source.name();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()
}
//  class DefaultEnumStringConverter

/*
 *  End of File
 */