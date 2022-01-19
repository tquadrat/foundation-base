/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
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

package org.tquadrat.foundation.lang;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.internal.StringConverterService.listInstances;
import static org.tquadrat.foundation.lang.internal.StringConverterService.retrieveConverterForClass;
import static org.tquadrat.foundation.lang.internal.StringConverterService.retrieveConverterForEnum;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary Defines the conversion between Strings and object instances,
 *  where the concrete behaviour is defined by the implementation of the
 *  interface.}</p>
 *  <p>Implementations of {@code StringConverter} are meant as a tool to
 *  translate Strings to Objects for technical purposes, not necessarily for
 *  human convenience.</p>
 *  <p>Each implementation should ensure that</p>
 *  <pre><code>  StringConverter&lt;T&gt; c = &hellip;
 *  T v = &hellip;
 *  true == ( v.equals( c.fromString( c.toString( v ) );</code></pre>
 *  <p>is always valid.</p>
 *  <p>If that condition could not be met, this must be clearly
 *  documented.</p>
 *  <p>For the other direction (from String to object back to String), this
 *  condition is weaker, as long as the result is equivalent to the original,
 *  as for numbers that can be represented as decimal, octal or
 *  hexadecimal.</p>
 *  <p>An implementation of {@code StringConverter} usually holds no state and
 *  it is possible to reuse an instance. Therefore it is recommended to provide
 *  a {@code public static final} field {@code INSTANCE} that is initialised
 *  with a reference for a single instance of the implementation.
 *
 *  @note Both
 *      {@code fromString(CharSequence)} and {@code toString(Object)} will
 *      return {@code null} if called with a {@code null} argument.
 *
 *  @note Usually the {@code java.util.Locale.ROOT} locale is used when locale
 *      specific conversion or parsing is required; this means in particular
 *      that numbers will use the decimal point as separator.
 *
 *  @note In order to make an implementation of this interface visible for
 *      {@code forClass()}, {@code forEnum()} and {@code list()}, that
 *      implementation needs to be exposed as a service provider for
 *      {@code org.tquadrat.foundation.lang.StringConverter}.
 *
 *  @note If the implementation is published as a service provider, and if it
 *      has a static {@code INSTANCE} field holding the reference to an
 *      instance of the converter, the implementation should also provide a
 *      static final method {@code provider()} that returns that reference.
 *
 *  @note Two implementations of {@code StringConverter} for the same subject
 *      class may cause unpredictable results if both are exposed as service
 *      providers.
 *
 *  @note When an implementation of {@code StringConverter} is published as a
 *      service, the subject class usually is guessed from the return value of
 *      {@code fromString()}, but sometimes this does not work reliably. In
 *      that case it is required that the implementation of
 *      {@code StringConverter} provides a method
 *      {@code public Collection<Class<?>> getSubjectClass()} that returns the
 *      respective classes.<br>
 *      This is definitely required, when the concrete implementation is
 *      derived from an abstract class; this abstract class must be visible for
 *      this module, too.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: StringConverter.java 993 2022-01-19 22:26:20Z tquadrat $
 *  @since 0.1.0
 *
 *  @param  <T> The Object type for the conversion.
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "InterfaceMayBeAnnotatedFunctional" )
@ClassVersion( sourceVersion = "$Id: StringConverter.java 993 2022-01-19 22:26:20Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public interface StringConverter<T> extends Serializable
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The name for the method that returns the subject classes: {@value}.
     */
    @SuppressWarnings( "StaticMethodOnlyUsedInOneClass" )
    public static final String METHOD_NAME_GetSubjectClass = "getSubjectClass";

    /**
     *  The name for the method that returns the instance: {@value}.
     */
    @SuppressWarnings( "StaticMethodOnlyUsedInOneClass" )
    public static final String METHOD_NAME_Provider = "provider";

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns an instance of {@code StringConverter} for the given
     *  {@link Class}.
     *  If there is no converter for the given type, or the type is
     *  {@code null}, the return value is
     *  {@link Optional#empty()}.
     *
     *  @param  <C> The class a converter is needed for.
     *  @param  type    The instance of the class a converter is needed for.
     *  @return An instance of
     *      {@link Optional}
     *      that holds the instance of {@code StringConverter}.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static <C> Optional<StringConverter<C>> forClass( final Class<C> type )
    {
        return retrieveConverterForClass( type );
    }   //  forClass()

    /**
     *  Returns an instance of {@code StringConverter} for the given
     *  {@link Enum} type.
     *  If there is no converter for the given type in the registry, a new
     *  instance of {@code StringConverter} will be created, based on a
     *  default implementation.
     *
     *  @param  <E> The class a converter is needed for.
     *  @param  type    The instance of the class a converter is needed for.
     *  @return The requested instance of {@code StringConverter}.
     */
    @API( status = STABLE, since = "0.0.6" )
    public static <E extends Enum<E>> StringConverter<E> forEnum( final Class<E> type )
    {
        return retrieveConverterForEnum( type );
    }   //  forEnum()

    /**
     *  Converts the given String to an object instance.
     *
     *  @param  source  The String representation for the object instance;
     *      can be {@code null}.
     *  @return The resulting object instance; will be {@code null} if
     *      {@code source} was already {@code null}.
     *  @throws IllegalArgumentException    The format of the given String is
     *      invalid and cannot be parsed into the object instance.
     */
    public T fromString( final CharSequence source ) throws IllegalArgumentException;

    /**
     *  Converts the given object instance to a String.
     *
     *  @note Even if an implementation of
     *      {@link org.tquadrat.foundation.lang.Stringer}
     *      might exist for {@code T}, it cannot be used to implement this
     *      method;
     *      {@link org.tquadrat.foundation.lang.Stringer#toString(Object)}
     *      will never return {@code null} (if implemented accordingly), for a
     *      {@code null} argument, it will return the String &quot;null&quot;.
     *      That contradicts the contract for this method.
     *
     *  @param  source  The object to convert; can be {@code null}.
     *  @return The resulting String; will be {@code null} if {@code source}
     *      was already {@code null}.
     */
    public default String toString( final T source )
    {
        final var retValue = isNull( source ) ? null : source.toString();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  Returns the classes for that an instance of {@code StringConverter} is
     *  already registered,
     *
     *  @return The classes with a string converter.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static Collection<Class<?>> list() { return listInstances(); }
}
//  interface StringConverter

/*
 *  End of File
 */