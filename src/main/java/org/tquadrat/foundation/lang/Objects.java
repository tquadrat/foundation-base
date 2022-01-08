/*
 * ============================================================================
 * Copyright © 2002-2021 by Thomas Thrien.
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

import static java.lang.Integer.signum;
import static java.util.Arrays.deepToString;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;
import static org.tquadrat.foundation.lang.internal.SharedFormatter.format;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.UtilityClass;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;
import org.tquadrat.foundation.exception.ValidationException;

/**
 *  This class consists of several utility methods working on
 *  {@link Object}
 *  instances, similar to those on
 *  {@link java.util.Arrays}
 *  or
 *  {@link java.util.Collections}. <br>
 *  <br>The class was originally inspired by the class of the same name that
 *  was finally introduced with the Java&nbsp;7 release; some of its methods
 *  will delegate to
 *  {@link java.util.Objects}.
 *  others will extend the functionality of the methods with the same
 *  name from {@code java.util.Objects}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Objects.java 980 2022-01-06 15:29:19Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( {"ClassWithTooManyMethods", "UseOfObsoleteDateTimeApi", "OverlyComplexClass"} )
@ClassVersion( sourceVersion = "$Id: Objects.java 980 2022-01-06 15:29:19Z tquadrat $" )
@UtilityClass
public final class Objects
{
        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance allowed for this class.
     */
    private Objects() { throw new PrivateConstructorForStaticClassCalledError( Objects.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Checks if the sub-range from {@code fromIndex} (inclusive) to
     *  {@code fromIndex + size} (exclusive) is within the bounds of range from
     *  {@code 0} (inclusive) to {@code length} (exclusive).<br>
     *  <br>The sub-range is defined to be out-of-bounds if any of the
     *  following inequalities is true:
     *  <ul>
     *    <li>{@code fromIndex < 0}</li>
     *    <li>{@code size < 0}</li>
     *    <li>{@code fromIndex + size > length}, taking into account integer
     *    overflow</li>
     *    <li>{@code length < 0}, which is implied from the former
     *    inequalities</li>
     *  </ul>
     *  Calls
     *  {@link java.util.Objects#checkFromIndexSize(int, int, int)}
     *  internally.
     *
     *  @param  fromIndex   The lower-bound (inclusive) of the sub-interval.
     *  @param  size    The size of the sub-range.
     *  @param  length  The upper-bound (exclusive) of the range.
     *  @return The {@code fromIndex} if the sub-range is within bounds of the
     *      range.
     *  @throws IndexOutOfBoundsException   The sub-range is out-of-bounds.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int checkFromIndexSize( final int fromIndex, final int size, final int length ) { return java.util.Objects.checkFromIndexSize( fromIndex, size, length ); }

    /**
     *  Checks if the sub-range from {@code fromIndex} (inclusive) to
     *  {@code toIndex} (exclusive) is within the bounds of range from
     *  {@code 0} (inclusive) to {@code length} (exclusive).<br>
     *  <br>The sub-range is defined to be out-of-bounds if any of the
     *  following inequalities is true:
     *  <ul>
     *    <li>{@code fromIndex < 0}</li>
     *    <li>{@code fromIndex > toIndex}</li>
     *    <li>{@code toIndex > length}</li>
     *    <li>{@code length < 0}, which is implied from the former
     *    inequalities</li>
     *  </ul>
     *  Calls
     *  {@link java.util.Objects#checkFromToIndex(int, int, int)}
     *  internally.
     *
     *  @param  fromIndex   The lower-bound (inclusive) of the sub-range.
     *  @param  toIndex The upper-bound (exclusive) of the sub-range.
     *  @param  length  The upper-bound (exclusive) the range.
     *  @return The {@code fromIndex} if the sub-range is within bounds of the
     *      range.
     *  @throws IndexOutOfBoundsException   The sub-range is out-of-bounds.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int checkFromToIndex( final int fromIndex, final int toIndex, final int length ) { return java.util.Objects.checkFromToIndex( fromIndex, toIndex, length ); }

    /**
     *  Checks if the {@code index} is within the bounds of the range from
     *  {@code 0} (inclusive) to {@code length} (exclusive).<br>
     *  <br>The {@code index} is defined to be out-of-bounds if any of the
     *  following inequalities is true:
     *  <ul>
     *    <li>{@code index < 0}</li>
     *    <li>{@code index >= length}</li>
     *    <li>{@code length < 0}, which is implied from the former
     *    inequalities</li>
     *  </ul>
     *  Calls
     *  {@link java.util.Objects#checkIndex(int, int)}
     *  internally.
     *
     *  @param  index   The index.
     *  @param  length  The upper-bound (exclusive) of the range.
     *  @return The {@code index} if it is within bounds of the range.
     *  @throws IndexOutOfBoundsException   The {@code index} is out-of-bounds.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int checkIndex( final int index, final int length ) { return java.util.Objects.checkIndex( index, length ); }

    /**
     *  <p>{@summary Throws the exception provided by the given supplier if the
     *  condition resolves to {@code false}.}</p>
     *  <p>Basically, this method is a replacement for the code sequence
     *  below:</p>
     *  <pre><code>  …
     *  if( !&lt;<i>condition</i>&gt; )
     *  {
     *      throw new &lt;<i>WhatEver</i>&gt;Exception( &lt;<i>WhatEverMessage</i>&gt; );
     *  }
     *  …</code></pre>
     *  <p>that may be easier to read that the {@code if} statement.</p>
     *
     *  @param  <E> The type of the exception that is thrown in case the
     *      condition is not met.
     *  @param  condition   The condition to check.
     *  @param  exception   The exception to throw.
     *  @throws E   The condition was not met.
     */
    @SuppressWarnings( "CheckedExceptionClass" )
    public static final <E extends Exception> void checkState( final boolean condition, final Supplier<E> exception ) throws E
    {
        if( !condition ) throw requireNonNullArgument( exception, "exception" ).get();
    }   //  checkState()
    /**
     *  Returns 0 if the arguments are identical and {@code c.compare(a, b)}
     *  otherwise.<br>
     *  <br>Consequently, if both arguments are {@code null}, 0 is
     *  returned.<br>
     *  <br>Calls
     *  {@link java.util.Objects#compare(Object, Object, Comparator)}
     *  internally, but different from that method, this implementation will
     *  throw a
     *  {@link NullArgumentException}
     *  in case the {@code comparator} is {@code null}.
     *
     *  @param  <T> The type of the objects being compared.
     *  @param  a   An object.
     *  @param  b   An object to be compared with {@code a}.
     *  @param  comparator  The
     *      {@link Comparator}
     *      to compare the first two arguments.
     *  @return 0 if the arguments are identical and +1, 0, or -1, based on the
     *      return value of {@code c.compare(a, b)} otherwise.
     *  @throws NullArgumentException   The {@code comparator} is {@code null}.
     *
     *  @see Comparable
     *  @see Comparator
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> int compare( final T a, final T b, final Comparator<? super T> comparator ) throws NullArgumentException
    {
        final var retValue = a == b ? 0 : signum( java.util.Objects.compare( a, b, requireNonNullArgument( comparator, "comparator" ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  compare()

    /**
     *  Returns {@code true} if the arguments are deeply equal to each other
     *  and {@code false} otherwise.<br>
     *  <br>Two {@code null} values are deeply equal. If both arguments are
     *  arrays, the algorithm in
     *  {@link Arrays#deepEquals(Object[], Object[]) Arrays.deepEquals()}
     *  is used to determine equality. Otherwise, equality is determined by
     *  using the
     *  {@link Object#equals(Object) equals()}
     *  method of the first argument.<br>
     *  <br>Calls
     *  {@link java.util.Objects#deepEquals(Object, Object)}
     *  internally.
     *
     *  @param  a   An object.
     *  @param  b   An object to be compared with {@code a} for deep equality.
     *  @return {@code true} if the arguments are deeply equal to each other
     *      and {@code false} otherwise.
     *
     *  @see Arrays#deepEquals(Object[], Object[])
     *  @see Objects#equals(Object, Object)
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean deepEquals( final Object a, final Object b ) { return java.util.Objects.deepEquals( a, b ); }

    /**
     *  Returns {@code true} if the arguments are equal to each other and
     *  {@code false} otherwise.<br>
     *  <br> Consequently, if both arguments are {@code null}, {@code true} is
     *  returned and if exactly one argument is {@code null}, {@code false} is
     *  returned.  Otherwise, equality is determined by using the
     *  {@link Object#equals(Object) equals()}
     *  method of the first argument.<br>
     *  <br>Calls
     *  {@link java.util.Objects#equals(Object, Object)}
     *  internally.
     *
     *  @param  a   An object.
     *  @param  b   An object to be compared with {@code a} for equality.
     *  @return {@code true} if the arguments are equal to each other and
     *      {@code false} otherwise.
     *
     *  @see Object#equals(Object)
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean equals( final Object a, final Object b ) { return java.util.Objects.equals( a, b ); }

    /**
     *  Generates a hash code for a sequence of input values. The hash code is
     *  generated as if all the input values were placed into an array, and
     *  that array were hashed by calling
     *  {@link Arrays#hashCode(Object[])}.<br>
     *  <br>Calls
     *  {@link java.util.Arrays#hashCode(Object[])}
     *  internally.
     *
     *  @param  values  The values to be hashed.
     *  @return A hash value of the sequence of input values.
     *
     *  @see List#hashCode
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int hash( final Object... values ) { return Arrays.hashCode( values ); }

    /**
     *  Returns the hash code of a non-{@code null} argument and 0 for
     *  a {@code null} argument.<br>
     *  <br>Calls
     *  {@link java.util.Objects#hashCode(Object)}
     *  internally.
     *
     *  @param o   An object.
     *  @return The hash code of an argument that is not {@code null}, and 0
     *      for a {@code null} argument,
     *
     *  @see Object#hashCode
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int hashCode( final Object o ) { return java.util.Objects.hashCode( o ); }

    /**
     *  Returns {@code true} if the provided reference is {@code null},
     *  otherwise returns {@code false}.<br>
     *  <br>This method exists to be used as a
     *  {@link java.util.function.Predicate},
     *  {@code filter(Objects::isNull)}.<br>
     *  <br>Calls
     *  {@link java.util.Objects#isNull(Object)}
     *  internally.
     *
     *  @param  obj A reference to be checked against {@code null}.
     *  @return {@code true} if the provided reference is {@code null},
     *      otherwise {@code false}
     *
     *  @see java.util.function.Predicate
     *  @see org.tquadrat.foundation.lang.CommonConstants#IS_NULL
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean isNull( final Object obj ) { return java.util.Objects.isNull( obj ); }

    /**
     *  Returns {@code true} if the provided reference is not {@code null},
     *  otherwise returns {@code false}.<br>
     *  <br>This method exists to be used as a
     *  {@link java.util.function.Predicate},
     *  {@code filter(Objects::nonNull)}<br>
     *  <br>Calls
     *  {@link java.util.Objects#nonNull(Object)}
     *  internally.
     *
     *  @param  obj A reference to be checked against {@code null}
     *  @return {@code false} if the provided reference is {@code null},
     *      otherwise {@code true}
     *
     *  @see java.util.function.Predicate
     *  @see org.tquadrat.foundation.lang.CommonConstants#NON_NULL
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean nonNull( final Object obj ) { return java.util.Objects.nonNull( obj ); }

    /**
     *  Applies the given validation on the given value, and if that fails, an
     *  {@link IllegalArgumentException}
     *  is thrown.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check; can be {@code null}.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws IllegalArgumentException    {@code a} failed the validation.
     *
     *  @since 0.1.0
     */
    @SuppressWarnings( "NewExceptionWithoutArguments" )
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T require( final T a, final Predicate<? super T> validation ) throws IllegalArgumentException
    {
        if( !requireNonNullArgument( validation, "validation" ).test( a ) )
        {
            throw new IllegalArgumentException();
        }

        //---* Done *----------------------------------------------------------
        return a;
    }   //  require()

    /**
     *  Applies the given validation on the given value, and if that fails, an
     *  {@link IllegalArgumentException}
     *  with the specified message is thrown.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check; can be {@code null}.
     *  @param  message The message that is set to the thrown exception.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws IllegalArgumentException    {@code a} failed the validation.
     *  @throws NullArgumentException   {@code message} is {@code null}.
     *  @throws EmptyArgumentException  {@code message} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T require( final T a, final String message, final Predicate<? super T> validation ) throws IllegalArgumentException, NullArgumentException, EmptyArgumentException
    {
        requireNotEmptyArgument( message, "message" );

        if( !requireNonNullArgument( validation, "validation" ).test( a ) )
        {
            throw new IllegalArgumentException( message );
        }

        //---* Done *----------------------------------------------------------
        return a;
    }   //  require()

    /**
     *  Applies the given validation on the given value, and if that fails, a
     *  customized
     *  {@link IllegalArgumentException}
     *  is thrown.<br>
     *  <br>Unlike the method
     *  {@link #require(Object,String,Predicate)},
     *  this method allows creation of the message to be deferred until after
     *  the validation was performed. While this may confer a performance
     *  advantage in the success case, when deciding to call this method care
     *  should be taken that the costs of creating the message supplier are
     *  less than the cost of just creating the String message directly.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check; can be {@code null}.
     *  @param  messageSupplier The supplier of the detail message to be used
     *      in the event that an {@code IllegalArgumentException} is thrown. If
     *      {@code null} or if it returns {@code null}, no detail message is
     *      provided.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws IllegalArgumentException    {@code a} failed the validation.
     *
     *  @since 0.1.0
     */
    @SuppressWarnings( "NewExceptionWithoutArguments" )
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T require( final T a, final Supplier<String> messageSupplier, final Predicate<? super T> validation ) throws IllegalArgumentException
    {
        if( !requireNonNullArgument( validation, "validation" ).test( a ) )
        {
            final var exception = nonNull( messageSupplier )
                ? new IllegalArgumentException( messageSupplier.get() )
                : new IllegalArgumentException();
            throw exception;
        }

        //---* Done *----------------------------------------------------------
        return a;
    }   //  require()

    /**
     *  Applies the given validation on the given value, and if that fails, a
     *  customized
     *  {@link IllegalArgumentException}
     *  is thrown.<br>
     *  <br>Unlike the method
     *  {@link #require(Object,String,Predicate)},
     *  this method allows creation of the message to be deferred until after
     *  the validation was performed. While this may confer a performance
     *  advantage in the success case, when deciding to call this method care
     *  should be taken that the costs of creating the message supplier are
     *  less than the cost of just creating the String message directly.
     *
     *  @note This implementation is different from
     *      {@link #requireNonNull(Object, Supplier)}
     *      as it takes an instance of
     *      {@link Function}
     *      for the {@code messageSupplier}. That function is called with
     *      {@code a} as the argument; this allows to add the invalid value to
     *      the exception message. The provided function must accept
     *      {@code null} as a valid argument.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check; can be {@code null}.
     *  @param  messageSupplier The supplier of the detail message to be used
     *      in the event that an {@code IllegalArgumentException} is thrown. If
     *      {@code null} or if it returns {@code null}, no detail message is
     *      provided.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws IllegalArgumentException    {@code a} failed the validation.
     *
     *  @since 0.1.0
     */
    @SuppressWarnings( "NewExceptionWithoutArguments" )
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T require( final T a, final Function<? super T,String> messageSupplier, final Predicate<? super T> validation ) throws IllegalArgumentException
    {
        if( !requireNonNullArgument( validation, "validation" ).test( a ) )
        {
            final var exception = nonNull( messageSupplier )
                ? new IllegalArgumentException( messageSupplier.apply( a ) )
                : new IllegalArgumentException();
            throw exception;
        }

        //---* Done *----------------------------------------------------------
        return a;
    }   //  require()

    /**
     *  Checks if the given value {@code a} is {@code null} and throws a
     *  {@link NullPointerException}
     *  if it is {@code null}.<br>
     *  <br>Calls
     *  {@link java.util.Objects#requireNonNull(Object)}
     *  internally.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check.
     *  @return The value if it is not {@code null}.
     *  @throws NullPointerException   {@code a} is {@code null}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNull( final T a ) { return java.util.Objects.requireNonNull( a ); }

    /**
     *  Checks if the given value {@code a} is {@code null} and throws a
     *  {@link NullPointerException}
     *  with the specified message if it is {@code null}.<br>
     *  <br>Calls
     *  {@link java.util.Objects#requireNonNull(Object, String)}
     *  internally, but requires that the given {@code message} is not
     *  {@code null} or empty.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check.
     *  @param  message The message that is set to the thrown exception.
     *  @return The value if it is not {@code null}.
     *  @throws NullPointerException    {@code a} is {@code null}.
     *  @throws NullArgumentException   {@code message} is {@code null}.
     *  @throws EmptyArgumentException  {@code message} is the empty String.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNull( final T a, final String message ) throws NullArgumentException, EmptyArgumentException
    {
        return java.util.Objects.requireNonNull( a, requireNotEmptyArgument( message, "message" ) );
    }   //  requireNonNull()

    /**
     *  Checks that the specified object reference is not {@code null} and
     *  throws a customized
     *  {@link NullPointerException}
     *  if it is.<br>
     *  <br>Unlike the method
     *  {@link #requireNonNull(Object, String)},
     *  this method allows creation of the message to be deferred until after
     *  the null check is made. While this may confer a performance advantage
     *  in the non-null case, when deciding to call this method care should be
     *  taken that the costs of creating the message supplier are less than the
     *  cost of just creating the String message directly.<br>
     *  <br>Calls
     *  {@link java.util.Objects#requireNonNull(Object, Supplier)}
     *  internally.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check.
     *  @param  messageSupplier The supplier of the detail message to be used
     *      in the event that a {@code NullPointerException} is thrown. If
     *      {@code null}, no detail message is provided.
     *  @return The value if it is not {@code null}.
     *  @throws NullPointerException    {@code A} is {@code null}
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNull(final T a, final Supplier<String> messageSupplier) { return java.util.Objects.requireNonNull( a, messageSupplier ); }

    /**
     *  Checks if the given argument {@code a} is {@code null} and throws a
     *  {@link NullArgumentException}
     *  if it is {@code null}.
     *
     *  @param  <T> The type of the argument to check.
     *  @param  a   The argument to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The argument if it is not {@code null}.
     *  @throws NullArgumentException   {@code a} is {@code null}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNullArgument( final T a, final String name )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );
        if( isNull( a ) ) throw new NullArgumentException( name );

        //---* Done *----------------------------------------------------------
        return a;
    }   //  requireNonNullArgument()

    /**
     *  Checks if not both of the given arguments {@code a} and
     *  {@code other} are {@code null} and throws a
     *  {@link NullArgumentException}
     *  if both are {@code null}. Otherwise, it returns {@code a}.
     *
     *  @param  <T> The type of the first argument to check.
     *  @param  arg The first argument to check; it will be returned in case of
     *      success, even if {@code null}.
     *  @param  otherArg    The other argument to check.
     *  @param  name    The name of the first argument; this is used for the
     *      error message.
     *  @param  otherName   The name of the other argument; this is used for
     *      the error message.
     *  @return The first argument, even that might be {@code null}.
     *  @throws NullArgumentException   Both arguments are {@code null}.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static final <T> T requireNonNullArgument( final T arg, final Object otherArg, final String name, final String otherName )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );
        if( isNull( otherName ) ) throw new NullArgumentException( "otherName" );
        if( otherName.isEmpty() ) throw new EmptyArgumentException( "otherName" );
        if( isNull( arg ) && isNull( otherArg ) )
        {
            throw new NullArgumentException( name, otherName );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireNonNullArgument()

    /**
     *  Checks if the given argument {@code a} is {@code null} or empty and
     *  throws a
     *  {@link NullArgumentException}
     *  if it is {@code null}, or a
     *  {@link EmptyArgumentException}
     *  if it is empty.<br>
     *  <br>Strings, arrays,
     *  {@link java.util.Collection}s, and
     *  {@link java.util.Map}s
     *  will be checked on being empty; this includes instances of
     *  {@link java.lang.StringBuilder},
     *  {@link java.lang.StringBuffer},
     *  and
     *  {@link java.lang.CharSequence}.<br>
     *  <br>For an instance of
     *  {@link java.util.Optional},
     *  the presence of a value is checked in order to determine whether it is
     *  empty or not.<br>
     *  <br>Because the interface
     *  {@link java.util.Enumeration}
     *  does not provide an API for the check on emptiness
     *  ({@link java.util.Enumeration#hasMoreElements() hasMoreElements()}
     *  will return {@code false} after all elements have been taken from
     *  the {@code Enumeration} instance), the result for arguments of this
     *  type has to be taken with caution.<br>
     *  <br>For instances of
     *  {@link java.util.stream.Stream},
     *  this method will only check for {@code null} (like
     *  {@link #requireNonNullArgument(Object,String)}.
     *  This is because any operation on the stream would render it unusable
     *  for later processing.<br>
     *  <br>In case the argument is of type
     *  {@link Optional},
     *  this method behaves different from
     *  {@link #requireNotEmptyArgument(Optional,String)};
     *  this one will return the {@code Optional} instance, while the other
     *  method will return its contents.
     *
     *  @param  <T> The type of the argument to check.
     *  @param  a   The argument to check; may be {@code null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The argument if it is not {@code null}.
     *  @throws NullArgumentException   {@code a} is {@code null}.
     *  @throws EmptyArgumentException   {@code a} is empty.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNotEmptyArgument( final T a, final String name )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );

        //---* Check for null *------------------------------------------------
        if( isNull( a ) ) throw new NullArgumentException( name );

        //---* Check the type *------------------------------------------------
        //noinspection IfStatementWithTooManyBranches
        if( a instanceof CharSequence charSequence )
        {
            if( charSequence.isEmpty() ) throw new EmptyArgumentException( name );
        }
        else if( a.getClass().isArray() )
        {
            if( Array.getLength( a ) == 0 ) throw new EmptyArgumentException( name );
        }
        else if( a instanceof Collection<?> collection )
        {
            if( collection.isEmpty() ) throw new EmptyArgumentException( name );
        }
        else if( a instanceof Map<?,?> )
        {
            if( ((Map<?,?>) a).isEmpty() ) throw new EmptyArgumentException( name );
        }
        else if( a instanceof Enumeration<?> )
        {
            /*
             * The funny thing with an Enumeration is that it could have been
             * not empty in the beginning, but it may be empty (= having no
             * more elements) now.
             * The good thing is that Enumeration.hasMoreElements() will not
             * change the state of the Enumeration - at least it should not do
             * so.
             */
            if( !((Enumeration<?>) a).hasMoreElements() ) throw new EmptyArgumentException( name );
        }
        else if( a instanceof Optional<?> optional)
        {
            /*
             * This is different from the behaviour of
             * requireNotEmptyArgument(Optional,String) as the Optional will be
             * returned here.
             */
            if( optional.isEmpty() ) throw new EmptyArgumentException( name );
        }
        else
        {
            /*
             * Other data types are not further processed; in particular,
             * instances of Stream cannot be checked on being empty. This is
             * because any operation on the Stream itself will change its state
             * and may make the Stream unusable.
             */
        }

        //---* Done *----------------------------------------------------------
        return a;
    }   //  requireNotEmptyArgument()

    /**
     *  Checks if the given argument {@code a} of type
     *  {@link Optional}
     *  is {@code null} or empty and
     *  throws a
     *  {@link NullArgumentException}
     *  if it is {@code null}, or a
     *  {@link EmptyArgumentException}
     *  if it is empty.<br>
     *  <br>Otherwise it returns the value of the {@code Optional}.<br>
     *  <br>This is different from the behaviour of
     *  {@link #requireNotEmptyArgument(Object,String)}
     *  with an instance of {@code Optional} as the argument to test.
     *
     *  @param  <T> The type of the given {@code Optional} to check.
     *  @param  optional    The argument to check; can be {@code null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The value of the argument if {@code optional} is not
     *      {@code null}.
     *  @throws NullArgumentException   {@code optional} is {@code null}.
     *  @throws EmptyArgumentException   {@code optional} is empty.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNotEmptyArgument( @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" ) final Optional<T> optional, final String name )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );

        //---* Check for null *------------------------------------------------
        if( isNull( optional ) ) throw new NullArgumentException( name );
        final var retValue = optional.orElseThrow( () -> new EmptyArgumentException( name ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  requireNotEmptyArgument()

    /**
     *  Returns the first argument if it is not {@code null}, otherwise it
     *  returns the non-{@code null} second argument.<br>
     *  <br>This implementation behaves different from that in
     *  {@link java.util.Objects#requireNonNullElse(Object,Object)}
     *  as it will always check that the default is not {@code null}.
     *
     *  @param <T>  The type of the references.
     *  @param  obj An object reference.
     *  @param  defaultObj  Another object reference to be returned if the
     *      first argument is {@code null}.
     *  @return The first argument if it is not {@code null}, otherwise the
     *      second argument if it is not {@code null}.
     *  @throws NullArgumentException   The {@code defaultObj} is {@code null}.
     *
     *  @see java.util.Objects#requireNonNullElse(Object, Object)
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNullElse( final T obj, final T defaultObj ) throws NullArgumentException
    {
        return java.util.Objects.requireNonNullElse( obj, requireNonNullArgument( defaultObj, "defaultObj" ) );
    }   //  requireNonNullElse()

    /**
     *  Returns the first argument if it is not {@code null}, otherwise it
     *  returns the non-{@code null} value returned by
     *  {@link Supplier#get() supplier.get()}.<br>
     *  <br>This implementation behaves different from that in
     *  {@link java.util.Objects#requireNonNullElseGet(Object,Supplier)}
     *  as it will always check that the supplier is not {@code null}.
     *
     *  @param <T>  The type of the reference.
     *  @param  obj An object reference.
     *  @param  supplier    The supplier of a non-{@code null} object of type
     *      {code T} to return if the first argument is {@code null}.
     *  @return The first argument if it is not {@code null}, otherwise the
     *      value returned by a call to {@code supplier.get()} if it is not
     *      {@code null}.
     *  @throws NullArgumentException   The {@code supplier} is {@code null}.
     *  @throws NullPointerException    {@code obj} is {@code null} and the
     *      return value of {@code supplier.get()} value is {@code null}, too.
     */
    @SuppressWarnings( "ProhibitedExceptionDeclared" )
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNullElseGet( final T obj, final Supplier<? extends T> supplier ) throws NullArgumentException, NullPointerException
    {
        return java.util.Objects.requireNonNullElseGet( obj, requireNonNullArgument( supplier, "supplier" ) );
    }   //  requireNonNullElseGet()

    /**
     *  Applies the given validation on the given value, and if that fails, an
     *  {@link ValidationException}
     *  with a default message is thrown.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check; can be {@code null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code a} failed the validation.
     *  @throws NullArgumentException   {@code name} or {@code validation} is
     *      {@code null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T requireValidArgument( final T a, final String name, final Predicate<? super T> validation )
    {
        requireNotEmptyArgument( name, "name" );

        if( !requireNonNullArgument( validation, "validation" ).test( a ) )
        {
            throw new ValidationException( format( "Validation failed for '%s'", name ) );
        }

        //---* Done *----------------------------------------------------------
        return a;
    }   //  requireValidArgument()

    /**
     *  Applies the given validation on the given value, and if that fails, an
     *  {@link ValidationException}
     *  is thrown. The message for the exception will be provided by the given
     *  message supplier that takes the name of the argument as an argument.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check; can be {@code null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code a} failed the validation.
     *  @throws NullArgumentException   {@code name}, {@code validation} or
     *      {@code messageProvider} is {@code null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T requireValidArgument( final T a, final String name, final Predicate<? super T> validation, final UnaryOperator<String> messageSupplier )
    {
        requireNotEmptyArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( a ) )
        {
            throw new ValidationException( messageSupplier.apply( name ) );
        }

        //---* Done *----------------------------------------------------------
        return a;
    }   //  requireValidArgument()

    /**
     *  Applies the given validation on the given value (that may not be
     *  {@code null}), and if that fails, an
     *  {@link ValidationException}
     *  with a default message is thrown.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code a} failed the validation.
     *  @throws NullArgumentException   {@code a}, {@code name} or
     *      {@code validation} is {@code null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T requireValidNonNullArgument( final T a, final String name, final Predicate<? super T> validation )
    {
        requireNotEmptyArgument( name, "name" );

        if( !requireNonNullArgument( validation, "validation" ).test( requireNonNullArgument( a, "name" ) ) )
        {
            throw new ValidationException( format( "Validation failed for '%s'", name ) );
        }

        //---* Done *----------------------------------------------------------
        return a;
    }   //  requireValidNonNullArgument()

    /**
     *  Applies the given validation on the given value (that may not be
     *  {@code null}), and if that fails, an
     *  {@link ValidationException}
     *  is thrown. The message for the exception will be provided by the given
     *  message supplier that takes the name of the argument as an argument.
     *
     *  @param  <T> The type of the value to check.
     *  @param  a   The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code a} failed the validation.
     *  @throws NullArgumentException   {@code a}, {@code name},
     *      {@code validation} or {@code messageProvider} is {@code null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T requireValidNonNullArgument( final T a, final String name, final Predicate<? super T> validation, final UnaryOperator<String> messageSupplier )
    {
        requireNotEmptyArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( requireNonNullArgument( a, "name" ) ) )
        {
            throw new ValidationException( messageSupplier.apply( name ) );
        }

        //---* Done *----------------------------------------------------------
        return a;
    }   //  requireValidNonNullArgument()

    /**
     *  Converts the given argument {@code object} into a
     *  {@link String},
     *  usually by calling its
     *  {@link Object#toString() toString()}
     *  method. If the value of the argument is {@code null}, the text
     *  &quot;{@link org.tquadrat.foundation.lang.CommonConstants#NULL_STRING null}&quot;
     *  will be returned instead. Arrays will be converted to a string through
     *  calling the respective {@code toString()} method from
     *  {@link java.util.Arrays}
     *  (this distinguishes this implementation from
     *  {link java.util.Objects#toString(Object, String)}).
     *  Values of type
     *  {@link java.util.Date} or
     *  {@link java.util.Calendar}
     *  will be translated based on the default locale - whatever that is.
     *
     *  @param  object  The object; may be {@code null}.
     *  @return The object's string representation.
     *
     *  @see java.util.Arrays#toString(boolean[])
     *  @see java.util.Arrays#toString(byte[])
     *  @see java.util.Arrays#toString(char[])
     *  @see java.util.Arrays#toString(double[])
     *  @see java.util.Arrays#toString(float[])
     *  @see java.util.Arrays#toString(int[])
     *  @see java.util.Arrays#toString(long[])
     *  @see java.util.Arrays#toString(Object[])
     *  @see java.util.Arrays#toString(short[])
     *  @see java.util.Arrays#deepToString(Object[])
     *  @see java.util.Locale#getDefault()
     *  @see org.tquadrat.foundation.lang.CommonConstants#NULL_STRING
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String toString( final Object object )
    {
        return toString( object, NULL_STRING );
    }   //  toString()

    /**
     *  Converts the given argument {@code object} into a
     *  {@link String},
     *  usually by calling its
     *  {@link Object#toString() toString()}
     *  method. If the value of the argument is {@code null}, the text
     *  provided as the {@code nullDefault} argument will be returned instead.
     *  Arrays will be converted to a string through calling the respective
     *  {@code toString()} method from
     *  {@link java.util.Arrays}
     *  (this distinguishes this implementation from
     *  {link java.util.Objects#toString(Object, String)}).
     *  Values of type
     *  {@link java.util.Date} or
     *  {@link java.util.Calendar}
     *  will be translated based on the default locale - whatever that is.
     *
     *  @param  object  The object; may be {@code null}.
     *  @param  nullDefault The text that should be returned if {@code object}
     *      is {@code null}.
     *  @return The object's string representation.
     *
     *  @see java.util.Arrays#toString(boolean[])
     *  @see java.util.Arrays#toString(byte[])
     *  @see java.util.Arrays#toString(char[])
     *  @see java.util.Arrays#toString(double[])
     *  @see java.util.Arrays#toString(float[])
     *  @see java.util.Arrays#toString(int[])
     *  @see java.util.Arrays#toString(long[])
     *  @see java.util.Arrays#toString(Object[])
     *  @see java.util.Arrays#toString(short[])
     *  @see java.util.Arrays#deepToString(Object[])
     *  @see java.util.Locale#getDefault()
     */
    @SuppressWarnings( {"IfStatementWithTooManyBranches", "ChainOfInstanceofChecks"} )
    @API( status = STABLE, since = "0.0.5" )
    public static final String toString( final Object object, final String nullDefault )
    {
        var retValue = requireNonNullArgument( nullDefault, "nullDefault" );
        if( nonNull( object ) )
        {
            final var objectClass = object.getClass();
            if( objectClass.isArray() )
            {
                if( objectClass == byte [].class )
                {
                    retValue = Arrays.toString( (byte []) object );
                }
                else if( objectClass == short [].class )
                {
                    retValue = Arrays.toString( (short []) object );
                }
                else if( objectClass == int [].class )
                {
                    retValue = Arrays.toString( (int []) object );
                }
                else if( objectClass == long [].class )
                {
                    retValue = Arrays.toString( (long []) object );
                }
                else if( objectClass == char [].class )
                {
                    retValue = Arrays.toString( (char []) object );
                }
                else if( objectClass == float [].class )
                {
                    retValue = Arrays.toString( (float []) object );
                }
                else if( objectClass == double [].class )
                {
                    retValue = Arrays.toString( (double []) object );
                }
                else if( objectClass == boolean [].class )
                {
                    retValue = Arrays.toString( (boolean []) object );
                }
                else
                {
                    retValue = deepToString( (Object []) object );
                }
            }
            else
            {
                retValue = object.toString();
            }
        }

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()

    /**
     *  Converts the given argument {@code object} into a
     *  {@link String}
     *  using the given instance of
     *  {@link Stringer}.
     *  If the value of the argument is {@code null}, the text
     *  provided as the {@code nullDefault} argument will be returned instead.
     *
     *  @param  <T> The type of the object.
     *  @param  object  The object; may be {@code null}.
     *  @param  stringer    The method that is used to convert the given object
     *      to a String.
     *  @param  nullDefault The text that should be returned if {@code object}
     *      is {@code null}.
     *  @return The object's string representation.
     *
     *  @see Stringer
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> String toString( final T object, final Stringer<? super T> stringer, final String nullDefault )
    {
        requireNonNullArgument( nullDefault, "nullDefault" );

        final var retValue = nonNull( object ) ? requireNonNullArgument( stringer, "stringer" ).toString( object ) : nullDefault;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()
}
//  class Objects

/*
 *  End of File
 */