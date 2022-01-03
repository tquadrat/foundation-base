/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
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

package org.tquadrat.foundation.lang;

import static java.util.function.Function.identity;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.nonNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.internal.SharedFormatter.format;

import java.io.File;
import java.net.URL;
import java.util.Formattable;
import java.util.Optional;
import java.util.function.Function;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  This interface defines a method to compose a String representation from
 *  arbitrary objects. <br>
 *  <br>It will also provide several convenience implementations for the
 *  interface that can be used with special argument types. Currently, these
 *  are
 *  <ul>
 *  <li>{@link #OBJECT_STRINGER}
 *  as a catch-all</li>
 *  <li>{@link #BASE_STRINGER}
 *  when the output of the default implementation of
 *  {@link Object#toString()}
 *  is desired even when a more sophisticated implementation of
 *  {@code toString()} is available</li>
 *  <li>{@link #OBJECTCLASS_STRINGER}
 *  when only the object's class should be returned instead of its
 *  contents/value.</li>
 *  <li>{@link #CLASS_STRINGER}
 *  for instances of
 *  {@link Class}</li>
 *  <li>{@link #FILEOBJECT_STRINGER}
 *  for instances of
 *  {@link File}</li>
 *  <li>{@link #OPTIONAL_STRINGER}
 *  for instances of
 *  {@link Optional}</li>
 *  <li>{@link #STRING_STRINGER}
 *  for instances of
 *  {@link String}</li>
 *  <li>{@link #URL_STRINGER}
 *  for instances of
 *  {@link URL}
 *  (There is no {@code URI_STRINGER} as
 *  {@link java.net.URI URI}
 *  provides a sufficient implementation of
 *  {@link Object#toString()})</li>
 *  </ul>
 *  There are no explicit stringers for the classes from the {@code java.time}
 *  package as instances of these classes have sufficient implementations of
 *  {@link Object#toString()}
 *  and are therefore already well covered by
 *  {@link #OBJECT_STRINGER}. Same is true for the class
 *  {@link java.nio.file.Path}.<br>
 *  <br>This is a functional interface whose functional method is
 *  {@link #toString(Object)}.
 *
 *  @note The method
 *      {@link #toString(Object)}
 *      will <i>never</i> return {@code null}; if called with a {@code null}
 *      argument, it will return the String "null" (see
 *      {@link CommonConstants#NULL_STRING}).
 *      This is different to
 *      {@link org.tquadrat.foundation.lang.StringConverter#toString(Object)}
 *      that return {@code null} for a {@code null} argument.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Stringer.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 *
 *  @param  <T> The type of the argument value.
 */
@ClassVersion( sourceVersion = "$Id: Stringer.java 820 2020-12-29 20:34:22Z tquadrat $" )
@FunctionalInterface
@API( status = STABLE, since = "0.1.0" )
public interface Stringer<T>
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  An implementation for
     *  {@link Stringer}
     *  that produces an output that looks like that from the default implementation of
     *  {@link Object#toString()}:
     *  it executes<pre><code>  o.getClass().getName() + "@" + Integer.toHexString( o.hashCode() );</code></pre>.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static final Stringer<? super Object> BASE_STRINGER = o -> isNull( o ) ? NULL_STRING : format( "%s@%x", o.getClass().getName(), o.hashCode() );

    /**
     *  An implementation for
     *  {@link Stringer}
     *  for {@code byte} arrays.<br>
     *  <br>The result looks like
     *  this:<pre><code>byte [&lt;<i>length</i>&gt;]</code></pre>where
     *  <code><i>length</i></code> is the array length of the given argument.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Stringer<byte []> BYTEARRAY_STRINGER = a -> isNull( a ) ? NULL_STRING : format( "byte [%d]", a.length );

    /**
     *  An implementation for
     *  {@link Stringer}
     *  for instances of
     *  {@link Class}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Stringer<Class<?>> CLASS_STRINGER = e -> isNull( e ) ? NULL_STRING : e.getName();

    /**
     *  A generic
     *  {@link Stringer}
     *  implementation that delegates to
     *  {@link Objects#toString(Object)}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Stringer<Object> DEFAULT_STRINGER = o -> isNull( o ) ? NULL_STRING : o.toString();

    /**
     *  An implementation for
     *  {@link Stringer}
     *  for instances of
     *  {@link File}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Stringer<File> FILEOBJECT_STRINGER = f -> isNull( f ) ? NULL_STRING : f.getAbsolutePath();

    /**
     *  A
     *  {@link Stringer}
     *  implementation that calls
     *  {@link org.tquadrat.foundation.lang.internal.SharedFormatter#format(String, Object...) StringUtils.format( "%s", o )}
     *  for an object {@code o} if that implements the interface
     *  {@link Formattable},
     *  otherwise it delegates to
     *  {@link Objects#toString(Object)}.
     *
     *  @see #DEFAULT_STRINGER
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Stringer<? super Object> OBJECT_STRINGER = o -> o instanceof Formattable ? format( "%s", o ) : Objects.toString( o );

    /**
     *  An implementation for
     *  {@link Stringer}
     *  that returns the name of the object's class instead of a representation
     *  of the object's value.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Stringer<? super Object> OBJECTCLASS_STRINGER = o -> isNull( o ) ? NULL_STRING : o.getClass().getName();

    /**
     *  An implementation for
     *  {@link Stringer}
     *  for instances of
     *  {@link Optional}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Stringer<Optional<?>> OPTIONAL_STRINGER = o -> isNull( o ) ? NULL_STRING : format( "Optional: %s", o.map( v -> format( "%s = %s", v.getClass().getName(), OBJECT_STRINGER.toString( v ) ) ).orElse( "[empty]" ) );

    /**
     *  An implementation for
     *  {@link Stringer}
     *  for instances of
     *  {@link String}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Stringer<String> STRING_STRINGER = wrapFunction( identity() );

    /**
     *  An implementation for
     *  {@link Stringer}
     *  for instances of
     *  {@link URL}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Stringer<URL> URL_STRINGER = u -> isNull( u ) ? NULL_STRING : u.toExternalForm();

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns the given {@code Stringer} as an instance of
     *  {@link Function}.
     *
     *  @param  <A> The type of the input for the stringer.
     *  @param  stringer    The instance to wrap.
     *  @return The function.
     */
    @SuppressWarnings( "StaticMethodOnlyUsedInOneClass" )
    @API( status = STABLE, since = "0.0.5" )
    public static <A> Function<A,String> asFunction( final Stringer<? super A> stringer )
    {
        final Function<A,String> retValue = requireNonNullArgument( stringer, "stringer" )::toString;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  asFunction()

    /**
     *  Creates a {@code Stringer} for the given instance of
     *  {@link StringConverter}.<br>
     *  <br>A String converter cannot be used directly as a {@code Stringer}
     *  because {@code Stringer}'s
     *  {@link #toString(Object) toString()}
     *  method will never return {@code null} (in case the argument is
     *  {@code null}, it returns the
     *  {@link CommonConstants#NULL_STRING NULL_STRING}),
     *  while
     *  {@link StringConverter#toString(Object) StringConverter.toString()}
     *  returns {@code null} for a {@code null} argument.
     *
     *  @param  <A> The type of the input for the stringer.
     *  @param  stringConverter The {@code StringConverter} instance.
     *  @return The {@code Stringer}.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <A> Stringer<A> fromStringConverter( final StringConverter<? super A> stringConverter )
    {
        final var sc = requireNonNullArgument( stringConverter, "stringConverter" );
        final Stringer<A> retValue = a -> nonNull( a ) ? sc.toString( a ) : NULL_STRING;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  fromStringConverter()

    /**
     *  Returns this {@code Stringer} instance as an instance of
     *  {@link Function}.
     *
     *  @return The function.
     */
    @API( status = STABLE, since = "0.0.7" )
    public default Function<T,String> toFunction()
    {
        final Function<T,String> retValue = this::toString;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toFunction()

    /**
     *  Returns a String representation of the given argument, or
     *  {@code null} if the given {@code value} is {@code null}.
     *
     *  @param  value   The value.
     *  @return The String representation.
     *  @throws NullPointerException    The given value is {@code null}.
     */
    public String toString( final T value );

    /**
     *  Wraps a function that returns a String to an argument stringer.
     *
     *  @param  <A> The type of the input for the function.
     *  @param function The function to wrap.
     *  @return The argument stringer that wraps the function.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static <A> Stringer<A> wrapFunction( final Function<? super A, String> function )
    {
        final Stringer<A> retValue = requireNonNullArgument( function, "function" )::apply;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  wrapFunction()
}
//  interface Stringer

/*
 *  End of File
 */