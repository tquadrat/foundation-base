/*
 * ============================================================================
 * Copyright © 2002-2022 by Thomas Thrien.
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
import java.util.Collections;
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
import org.tquadrat.foundation.exception.BlankArgumentException;
import org.tquadrat.foundation.exception.EmptyArgumentException;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;
import org.tquadrat.foundation.exception.ValidationException;

/**
 *  <p>{@summary This class consists of several utility methods working on
 *  {@link Object}
 *  instances, similar to those on
 *  {@link Arrays}
 *  or
 *  {@link Collections}.}</p>
 *  <p>The class was originally inspired by the class of the same name that
 *  was finally introduced with the Java&nbsp;7 release; some of its methods
 *  will delegate to
 *  {@link java.util.Objects java.util.Objects},
 *  others will extend the functionality of the methods with the same
 *  name from {@code java.util.Objects}.</p>
 *  <p>If a method from {@code java.util.Objects} would throw a
 *  {@link NullPointerException},
 *  the method with the same name from this class would throw a
 *  {@link ValidationException}
 *  instead.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Objects.java 1041 2022-12-18 22:14:52Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@UtilityClass
@SuppressWarnings( {"ClassWithTooManyMethods", "UseOfObsoleteDateTimeApi", "OverlyComplexClass"} )
@ClassVersion( sourceVersion = "$Id: Objects.java 1041 2022-12-18 22:14:52Z tquadrat $" )
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
     *  <p>{@summary Checks if the sub-range from {@code fromIndex} (inclusive)
     *  to {@code fromIndex + size} (exclusive) is within the bounds of range
     *  from {@code 0} (inclusive) to {@code length} (exclusive).}</p>
     *  <p>The sub-range is defined to be out-of-bounds if any of the following
     *  inequalities is true:</p>
     *  <ul>
     *    <li>{@code fromIndex < 0}</li>
     *    <li>{@code size < 0}</li>
     *    <li>{@code fromIndex + size > length}, taking into account integer
     *    overflow</li>
     *    <li>{@code length < 0}, which is implied from the former
     *    inequalities</li>
     *  </ul>
     *  <p>Calls
     *  {@link java.util.Objects#checkFromIndexSize(int,int,int) java.util.Objects.checkFromIndexSize(int,int,int)}
     *  internally.</p>
     *
     *  @param  fromIndex   The lower-bound (inclusive) of the sub-interval.
     *  @param  size    The size of the sub-range.
     *  @param  length  The upper-bound (exclusive) of the range.
     *  @return The {@code fromIndex} if the sub-range is within bounds of the
     *      range.
     *  @throws IndexOutOfBoundsException   The sub-range is out-of-bounds.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int checkFromIndexSize( final int fromIndex, final int size, final int length )
    {
        final var retValue = java.util.Objects.checkFromIndexSize( fromIndex, size, length );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  checkFromIndexSize()

    /**
     *  <p>{@summary Checks if the sub-range from {@code fromIndex} (inclusive)
     *  to {@code toIndex} (exclusive) is within the bounds of range from
     *  {@code 0} (inclusive) to {@code length} (exclusive).}</p>
     *  <p>The sub-range is defined to be out-of-bounds if any of the following
     *  inequalities is true:</p>
     *  <ul>
     *    <li>{@code fromIndex < 0}</li>
     *    <li>{@code fromIndex > toIndex}</li>
     *    <li>{@code toIndex > length}</li>
     *    <li>{@code length < 0}, which is implied from the former
     *    inequalities</li>
     *  </ul>
     *  <p>Calls
     *  {@link java.util.Objects#checkFromToIndex(int,int,int) java.util.Objects.checkFromToIndex(int,int,int)}
     *  internally.</p>
     *
     *  @param  fromIndex   The lower-bound (inclusive) of the sub-range.
     *  @param  toIndex The upper-bound (exclusive) of the sub-range.
     *  @param  length  The upper-bound (exclusive) the range.
     *  @return The {@code fromIndex} if the sub-range is within bounds of the
     *      range.
     *  @throws IndexOutOfBoundsException   The sub-range is out-of-bounds.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int checkFromToIndex( final int fromIndex, final int toIndex, final int length )
    {
        final var retValue = java.util.Objects.checkFromToIndex( fromIndex, toIndex, length );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  checkFromToIndex()

    /**
     *  <p>{@summary Checks if the {@code index} is within the bounds of the
     *  range from {@code 0} (inclusive) to {@code length} (exclusive).}</p>
     *  <p>The {@code index} is defined to be out-of-bounds if any of the
     *  following inequalities is true:</p>
     *  <ul>
     *    <li>{@code index < 0}</li>
     *    <li>{@code index >= length}</li>
     *    <li>{@code length < 0}, which is implied from the former
     *    inequalities</li>
     *  </ul>
     *  <p>Calls
     *  {@link java.util.Objects#checkIndex(int,int) java.util.Objects.checkIndex(int,int)}
     *  internally.</p>
     *
     *  @param  index   The index.
     *  @param  length  The upper-bound (exclusive) of the range.
     *  @return The {@code index} if it is within bounds of the range.
     *  @throws IndexOutOfBoundsException   The {@code index} is out-of-bounds.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int checkIndex( final int index, final int length )
    {
        final var retValue = java.util.Objects.checkIndex( index, length );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  checkIndex()

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
     *  <p>that may be easier to read than the {@code if} statement:</p>
     *  <pre><code>  …
     *  checkState( !&lt;<i>condition</i>&gt;, () -> new &lt;<i>WhatEver</i>&gt;Exception( &lt;<i>WhatEverMessage</i>&gt; ) );
     *  …</code></pre>
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
     *  <p>{@summary Returns 0 if the arguments are identical and
     *  {@code comparator.compare(a, b)} otherwise.}</p>
     *  <p>Consequently, if both arguments are {@code null}, 0 is returned.</p>
     *  <p>Calls
     *  {@link java.util.Objects#compare(Object,Object,Comparator) java.util.Objects#compare()}
     *  internally, but different from that method, this implementation will
     *  throw a
     *  {@link NullArgumentException}
     *  in case the {@code comparator} is {@code null}.</p>
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
     *  <p>{@summary Returns {@code true} if the arguments are deeply equal to
     *  each other and {@code false} otherwise.}</p>
     *  <p>Two {@code null} values are deeply equal. If both arguments are
     *  arrays, the algorithm in
     *  {@link Arrays#deepEquals(Object[], Object[]) Arrays.deepEquals()}
     *  is used to determine equality. Otherwise, equality is determined by
     *  using the
     *  {@link Object#equals(Object) equals()}
     *  method of the first argument.</p>
     *  <p>Calls
     *  {@link java.util.Objects#deepEquals(Object,Object) java.util.Objects#deepEquals()}
     *  internally.</p>
     *
     *  @param  a   An object.
     *  @param  b   An object to be compared with {@code a} for deep equality.
     *  @return {@code true} if the arguments are deeply equal to each other
     *      and {@code false} otherwise.
     *
     *  @see    Arrays#deepEquals(Object[],Object[])
     *  @see    Objects#equals(Object,Object)
     */
    @SuppressWarnings( "BooleanMethodNameMustStartWithQuestion" )
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean deepEquals( final Object a, final Object b ) { return java.util.Objects.deepEquals( a, b ); }

    /**
     *  <p>{@summary Returns {@code true} if the arguments are equal to each
     *  other and {@code false} otherwise.}</p>
     *  <p> Consequently, if both arguments are {@code null}, {@code true} is
     *  returned and if exactly one argument is {@code null}, {@code false} is
     *  returned.  Otherwise, equality is determined by using the
     *  {@link Object#equals(Object) equals()}
     *  method of the first argument.</p>
     *  <p>Calls
     *  {@link java.util.Objects#equals(Object, Object)}
     *  internally.</p>
     *
     *  @param  a   An object.
     *  @param  b   An object to be compared with {@code a} for equality.
     *  @return {@code true} if the arguments are equal to each other and
     *      {@code false} otherwise.
     *
     *  @see    Object#equals(Object)
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean equals( final Object a, final Object b ) { return java.util.Objects.equals( a, b ); }

    /**
     *  <p>{@summary Generates a hash code for a sequence of input values.} The
     *  hash code is generated as if all the input values were placed into an
     *  array, and that array is hashed by calling
     *  {@link Arrays#hashCode(Object[])}.</p>
     *  <p>Calls
     *  {@link java.util.Arrays#hashCode(Object[]) java.util.Arrays.hashCode()}
     *  internally.</p>
     *
     *  @param  values  The values to be hashed.
     *  @return A hash value of the sequence of input values.
     *
     *  @see    List#hashCode
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int hash( final Object... values ) { return Arrays.hashCode( values ); }

    /**
     *  <p>{@summary Returns the hash code of a non-{@code null} argument and 0
     *  for a {@code null} argument.}</p>
     *  <p>Calls
     *  {@link java.util.Objects#hashCode(Object) java.util.Objects.hashCode(Object)}
     *  internally.</p>
     *
     *  @param o   An object.
     *  @return The hash code of an argument that is not {@code null}, and 0
     *      for a {@code null} argument,
     *
     *  @see    Object#hashCode
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int hashCode( final Object o ) { return java.util.Objects.hashCode( o ); }

    /**
     *  <p>{@summary Returns {@code true} if the provided reference is
     *  {@code null}, otherwise returns {@code false}.}</p>
     *  <p>This method can be used as a
     *  {@link java.util.function.Predicate},
     *  {@code filter(Objects::isNull)}.</p>
     *  <p>Calls
     *  {@link java.util.Objects#isNull(Object) java.util.Objects.isNull()}
     *  internally.</p>
     *
     *  @param  obj A reference to be checked against {@code null}.
     *  @return {@code true} if the provided reference is {@code null},
     *      otherwise {@code false}
     *
     *  @see    java.util.function.Predicate
     *  @see    org.tquadrat.foundation.lang.CommonConstants#IS_NULL
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean isNull( final Object obj ) { return java.util.Objects.isNull( obj ); }

    /**
     *  <p>{@summary Maps (converts) the given object instance by applying the
     *  provided mapper if the instance is not {@code null}.}</p>
     *  <p>The mapper function will not be called at all if the given instance
     *  is {@code null}.</p>
     *
     *  @param  <T> The type of the object to map.
     *  @param  <R> The type of the result.
     *  @param  o   The object to map; can be {@code null}.
     *  @param  mapper  The mapping function.
     *  @return The result of the mapping, or {@code null} if the given object
     *      instance was already {@code null}. Keep in mind that the result of
     *      the mapping can be {@code null}!
     */
    public static final <T,R> R mapNonNull( final T o, final Function<T,? extends R> mapper )
    {
        @SuppressWarnings( "RedundantExplicitVariableType" )
        final R retValue = nonNull( o ) ? requireNonNullArgument( mapper, "mapper" ).apply( o ) : null;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  mapNonNull()

    /**
     *  <p>{@summary Maps (converts) the given object instance by applying the
     *  provided mapper if the instance is not {@code null} or returns the
     *  given default value.}</p>
     *  <p>The mapper function will not be called at all if the given instance
     *  is {@code null}.</p>
     *
     *  @param  <T> The type of the object to map.
     *  @param  <R> The type of the result.
     *  @param  o   The object to map; can be {@code null}.
     *  @param  mapper  The mapping function.
     *  @param  defaultValue    The default value; can be {@code null}.
     *  @return The result of the mapping, or the default value if the given
     *      object instance is {@code null}. Keep in mind that the result of
     *      the mapping can be {@code null}!
     */
    public static final <T,R> R mapNonNull( final T o, final Function<T,? extends R> mapper, final R defaultValue )
    {
        @SuppressWarnings( "RedundantExplicitVariableType" )
        final R retValue = nonNull( o ) ? requireNonNullArgument( mapper, "mapper" ).apply( o ) : defaultValue;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  mapNonNull()

    /**
     *  <p>{@summary Returns {@code true} if the provided reference is not
     *  {@code null}, otherwise returns {@code false}.}</p>
     *  <p>This method exists to be used as a
     *  {@link java.util.function.Predicate},
     *  {@code filter(Objects::nonNull)}</p>
     *  <p>Calls
     *  {@link java.util.Objects#nonNull(Object) java.util.Objects.nonNull()}
     *  internally.</p>
     *
     *  @param  obj A reference to be checked against {@code null}
     *  @return {@code false} if the provided reference is {@code null},
     *      otherwise {@code true}
     *
     *  @see java.util.function.Predicate
     *  @see org.tquadrat.foundation.lang.CommonConstants#NON_NULL
     */
    @SuppressWarnings( "BooleanMethodNameMustStartWithQuestion" )
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean nonNull( final Object obj ) { return java.util.Objects.nonNull( obj ); }

    /**
     *  Applies the given validation on the given value, and if that fails, an
     *  {@link ValidationException}
     *  is thrown.
     *
     *  @param  <T> The type of the value to check.
     *  @param  obj The value to check; can be {@code null}.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code obj} failed the validation.
     *
     *  @since 0.1.0
     */
    @SuppressWarnings( "NewExceptionWithoutArguments" )
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T require( final T obj, final Predicate<? super T> validation ) throws ValidationException
    {
        if( !requireNonNullArgument( validation, "validation" ).test( obj ) )
        {
            throw new ValidationException();
        }

        //---* Done *----------------------------------------------------------
        return obj;
    }   //  require()

    /**
     *  Applies the given validation on the given value, and if that fails, an
     *  {@link ValidationException}
     *  with the specified message is thrown.
     *
     *  @param  <T> The type of the value to check.
     *  @param  obj The value to check; can be {@code null}.
     *  @param  message The message that is set to the thrown exception.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code obj} failed the validation.
     *  @throws NullArgumentException   {@code message} is {@code null}.
     *  @throws EmptyArgumentException  {@code message} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T require( final T obj, final String message, final Predicate<? super T> validation ) throws ValidationException, NullArgumentException, EmptyArgumentException
    {
        requireNotEmptyArgument( message, "message" );

        if( !requireNonNullArgument( validation, "validation" ).test( obj ) )
        {
            throw new ValidationException( message );
        }

        //---* Done *----------------------------------------------------------
        return obj;
    }   //  require()

    /**
     *  <p>{@summary Applies the given validation on the given value, and if
     *  that fails, a customized
     *  {@link ValidationException}
     *  is thrown.}</p>
     *  <p>Unlike the method
     *  {@link #require(Object,String,Predicate)},
     *  this method allows to defer the creation of the message until after the
     *  validation was performed (and failed). While this may confer a
     *  performance advantage in the success case, some care should be taken
     *  that the costs for the creation of the message supplier are less than
     *  the cost of just creating the String message directly.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  obj The value to check; can be {@code null}.
     *  @param  messageSupplier The supplier of the detail message to be used
     *      in the event that an {@code ValidationException} is thrown. If
     *      {@code null} or if it returns {@code null}, no detail message is
     *      provided to the exception.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws NullArgumentException   The validation is {@code null}.
     *  @throws ValidationException {@code obk} failed the validation.
     *
     *  @since 0.1.0
     */
    @SuppressWarnings( "NewExceptionWithoutArguments" )
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T require( final T obj, final Supplier<String> messageSupplier, final Predicate<? super T> validation ) throws ValidationException
    {
        if( !requireNonNullArgument( validation, "validation" ).test( obj ) )
        {
            final var exception = nonNull( messageSupplier )
                ? new ValidationException( messageSupplier.get() )
                : new ValidationException();
            throw exception;
        }

        //---* Done *----------------------------------------------------------
        return obj;
    }   //  require()

    /**
     *  <p>{@summary Applies the given validation on the given value, and if
     *  that fails, a customized
     *  {@link ValidationException}
     *  is thrown.}</p>
     *  <p>Unlike the method
     *  {@link #require(Object,String,Predicate)},
     *  this method allows to defer the creation of the message until after the
     *  validation was performed (and failed). While this may confer a
     *  performance advantage in the success case, some care  should be taken
     *  that the costs the creation of the message supplier are less than the
     *  cost of just creating the String message directly.</p>
     *  <p>This implementation is different from
     *  {@link #requireNonNull(Object, Supplier)}
     *  as it takes an instance of
     *  {@link Function}
     *  for the {@code messageSupplier}. That function is called with
     *  {@code obj} as the argument; this allows to add the invalid value to
     *  the exception detail message. The provided message supplier function
     *  must accept {@code null} as a valid argument.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  obj The value to check; can be {@code null}.
     *  @param  messageSupplier The supplier of the detail message to be used
     *      in the event that an {@code ValidationException} is thrown. If
     *      {@code null} or if it returns {@code null}, no detail message is
     *      provided.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws NullArgumentException   The validation is {@code null}.
     *  @throws ValidationException {@code obj} failed the validation.
     *
     *  @since 0.1.0
     */
    @SuppressWarnings( "NewExceptionWithoutArguments" )
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T require( final T obj, final Function<? super T,String> messageSupplier, final Predicate<? super T> validation ) throws ValidationException
    {
        if( !requireNonNullArgument( validation, "validation" ).test( obj ) )
        {
            final var exception = nonNull( messageSupplier )
                ? new ValidationException( messageSupplier.apply( obj ) )
                : new NullArgumentException();
            throw exception;
        }

        //---* Done *----------------------------------------------------------
        return obj;
    }   //  require()

    /**
     *  <p>{@summary Checks if the given value {@code obj} is {@code null} and
     *  throws a
     *  {@link NullArgumentException}
     *  if it is {@code null}.}</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  obj The value to check.
     *  @return The value if it is not {@code null}.
     *  @throws NullArgumentException   {@code obj} is {@code null}.
     *
     *  @see java.util.Objects#requireNonNull(Object)
     */
    @SuppressWarnings( "NewExceptionWithoutArguments" )
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNull( final T obj ) throws NullArgumentException
    {
        if( isNull( obj ) ) throw new NullArgumentException();

        //---* Done *----------------------------------------------------------
        return obj;
    }   //  requireNonNull()

    /**
     *  <p>{@summary Checks if the given value {@code obj} is {@code null} and
     *  throws a
     *  {@link ValidationException}
     *  with the specified message if it is {@code null}.}</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  obj The value to check.
     *  @param  message The message that is set to the thrown exception.
     *  @return The value if it is not {@code null}.
     *  @throws NullArgumentException   {@code message} is {@code null}.
     *  @throws NullArgumentException   {@code obj} is {@code null}.
     *  @throws EmptyArgumentException  {@code message} is the empty String.
     *
     *  @see java.util.Objects#requireNonNull(Object,String)
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNull( final T obj, final String message ) throws ValidationException, NullArgumentException, EmptyArgumentException
    {
        requireNotEmptyArgument( message, "message" );
        if( isNull( obj ) ) throw new ValidationException( message );

        //---* Done *----------------------------------------------------------
        return obj;
    }   //  requireNonNull()

    /**
     *  <p>{@summary Checks that the specified object reference is not
     *  {@code null} and throws a customized
     *  {@link ValidationException}
     *  if it is.}</p>
     *  <p>Unlike the method
     *  {@link #requireNonNull(Object,String)},
     *  this method allows to defer the creation of the message until after the
     *  null check failed. While this may confer a performance advantage in the
     *  non-{@code null} case, when deciding to call this method care should be
     *  taken that the costs of creating the message supplier are less than the
     *  cost of just creating the String message directly.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  obj The value to check.
     *  @param  messageSupplier The supplier of the detail message to be used
     *      in the event that a {@code NullArgumentException} is thrown. If
     *      {@code null}, no detail message is provided.
     *  @return The value if it is not {@code null}.
     *  @throws ValidationException    {@code obj} is {@code null}
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNull( final T obj, final Supplier<String> messageSupplier) throws ValidationException
    {
        if( isNull( obj ) )
        {
            final var message = nonNull( messageSupplier ) ? messageSupplier.get() : null;
            @SuppressWarnings( "NewExceptionWithoutArguments" )
            final var exception = isNull( message ) ? new NullArgumentException() : new ValidationException( message );
            throw exception;
        }

        //---* Done *----------------------------------------------------------
        return obj;
    }   //  requireNonNull()

    /**
     *  Checks if the given argument {@code a} is {@code null} and throws a
     *  {@link NullArgumentException}
     *  if it is {@code null}.
     *
     *  @param  <T> The type of the argument to check.
     *  @param  arg The argument to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The argument if it is not {@code null}.
     *  @throws NullArgumentException   {@code arg} is {@code null}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNullArgument( final T arg, final String name )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );
        if( isNull( arg ) ) throw new NullArgumentException( name );

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireNonNullArgument()

    /**
     *  <p>{@summary Checks if not both of the given arguments {@code arg} and
     *  {@code otherArg} are {@code null} and throws a
     *  {@link NullArgumentException}
     *  if both are {@code null}.} Otherwise, it returns {@code arg}.</p>
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
     *  <p>{@summary Checks if the given String argument {@code arg} is
     *  {@code null}, empty or blank and throws a
     *  {@link NullArgumentException}
     *  if it is {@code null}, an
     *  {@link EmptyArgumentException}
     *  if it is empty, or a
     *  {@link BlankArgumentException}
     *  if it is blank.}</p>
     *
     *  @param  <T> The type of the argument to check.
     *  @param  arg The argument to check; may be {@code null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The argument if it is not {@code null}, empty or blank.
     *  @throws NullArgumentException   {@code arg} is {@code null}.
     *  @throws EmptyArgumentException   {@code arg} is empty.
     *  @throws BlankArgumentException   {@code arg} is blank.
     *
     *  @see    String#isBlank()
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T extends CharSequence> T requireNotBlankArgument( final T arg, final String name )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );

        switch( arg )
        {
            case null -> throw new NullArgumentException( name );
            //noinspection QuestionableName
            case String string ->
            {
                if( string.isEmpty() ) throw new EmptyArgumentException( name );
                if( string.isBlank() ) throw new BlankArgumentException( name );
            }
            case CharSequence charSequence ->
            {
                if( charSequence.isEmpty() ) throw new EmptyArgumentException( name );
                if( charSequence.toString().isBlank() ) throw new BlankArgumentException( name );
            }
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireNotBlankArgument()

    /**
     *  <p>{@summary Checks if the given argument {@code arg} is {@code null} or
     *  empty and throws a
     *  {@link NullArgumentException}
     *  if it is {@code null}, or an
     *  {@link EmptyArgumentException}
     *  if it is empty.}</p>
     *  <p>Strings, arrays, instances of
     *  {@link java.util.Collection} and
     *  {@link java.util.Map}
     *  as well as instances of
     *  {@link java.lang.StringBuilder},
     *  {@link java.lang.StringBuffer},
     *  and
     *  {@link java.lang.CharSequence}
     *  will be checked on being empty.</p>
     *  <p>For an instance of
     *  {@link java.util.Optional},
     *  the presence of a value is checked in order to determine whether the
     *  {@link Optional} is empty or not.</p>
     *  <p>Because the interface
     *  {@link java.util.Enumeration}
     *  does not provide an API for the check on emptiness
     *  ({@link java.util.Enumeration#hasMoreElements() hasMoreElements()}
     *  will return {@code false} after all elements have been taken from
     *  the {@code Enumeration} instance), the result for arguments of this
     *  type has to be taken with caution.</p>
     *  <p>For instances of
     *  {@link java.util.stream.Stream},
     *  this method will only check for {@code null} (like
     *  {@link #requireNonNullArgument(Object,String)}.
     *  This is because any operation on the stream itself would render it
     *  unusable for later processing.</p>
     *  <p>In case the argument is of type
     *  {@link Optional},
     *  this method behaves different from
     *  {@link #requireNotEmptyArgument(Optional,String)};
     *  this one will return the {@code Optional} instance, while the other
     *  method will return the contents of the {@code Optional}.</p>
     *  <p>This method will not work properly for instances of
     *  {@link java.util.StringJoiner}, because its method
     *  {@link java.util.StringJoiner#length() length()}
     *  will not return 0 when a prefix, suffix, or an
     *  &quot;{@linkplain java.util.StringJoiner#setEmptyValue(CharSequence) empty value}&quot;
     *  was provided.</p>
     *
     *  @param  <T> The type of the argument to check.
     *  @param  arg The argument to check; may be {@code null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The argument if it is not {@code null} or empty.
     *  @throws NullArgumentException   {@code arg} is {@code null}.
     *  @throws EmptyArgumentException   {@code arg} is empty.
     */
    @SuppressWarnings( "OverlyComplexMethod" )
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNotEmptyArgument( final T arg, final String name )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );

        switch( arg )
        {
            /*
             * When using guarding expressions, the code would not get better
             * to read and to understand, as the positive cases will be handled
             * all by the default case.
             */
            case null -> throw new NullArgumentException( name );
            case CharSequence charSequence ->
            {
                if( charSequence.isEmpty() ) throw new EmptyArgumentException( name );
            }
            case Collection<?> collection ->
            {
                if( collection.isEmpty() ) throw new EmptyArgumentException( name );
            }
            case Map<?,?> map ->
            {
                if( map.isEmpty() ) throw new EmptyArgumentException( name );
            }
            case Enumeration<?> enumeration ->
            {
                /*
                 * The funny thing with an Enumeration is that it could have
                 * been not empty in the beginning, but it may be empty
                 * (= having no more elements) now.
                 * The good thing is that Enumeration.hasMoreElements() will
                 * not change the state of the Enumeration - at least it should
                 * not do so.
                 */
                if( !enumeration.hasMoreElements() ) throw new EmptyArgumentException( name );
            }
            case Optional<?> optional ->
            {
                /*
                 * This is different from the behaviour of
                 * requireNotEmptyArgument(Optional,String) as the Optional
                 * will be returned here.
                 */
                if( optional.isEmpty() ) throw new EmptyArgumentException( name );
            }
            default ->
            {
                if( arg.getClass().isArray() )
                {
                    if( Array.getLength( arg ) == 0 ) throw new EmptyArgumentException( name );
                }
                else
                {
                    /*
                     * Other data types are not further processed; in
                     * particular, instances of Stream cannot be checked on
                     * being empty. This is because any operation on the Stream
                     * itself will change its state and may make the Stream
                     * unusable.
                     */
                }
            }
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireNotEmptyArgument()

    /**
     *  <p>{@summary Checks if the given argument {@code optional} of type
     *  {@link Optional}
     *  is {@code null} or
     *  {@linkplain Optional#empty() empty}
     *  and throws a
     *  {@link NullArgumentException}
     *  if it is {@code null}, or a
     *  {@link EmptyArgumentException}
     *  if it is empty.}</p>
     *  <p>Otherwise it returns the value of the {@code Optional}.</p>
     *  <p>This is different from the behaviour of
     *  {@link #requireNotEmptyArgument(Object,String)}
     *  with an instance of {@code Optional} as the argument to test.</p>
     *
     *  @param  <T> The type of the given {@code Optional} to check.
     *  @param  optional    The argument to check; can be {@code null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The value of the argument if {@code optional} is not
     *      {@code null}
     *      and not
     *      {@linkplain Optional#empty() empty}. This could be the empty
     *      string!
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
     *  <p>{@summary Returns the first argument if it is not {@code null},
     *  otherwise it returns the non-{@code null} second argument.}</p>
     *  <p>This implementation behaves different from that in
     *  {@link java.util.Objects#requireNonNullElse(Object,Object) java.util.Objects.requireNonNullElse(Object,Object)}
     *  as it will always check that the default is not {@code null}.</p>
     *
     *  @param <T>  The type of the references.
     *  @param  obj An object reference.
     *  @param  defaultObj  Another object reference to be returned if the
     *      first argument is {@code null}.
     *  @return The first argument if it is not {@code null}, otherwise the
     *      second argument if it is not {@code null}.
     *  @throws NullArgumentException   The {@code defaultObj} is {@code null}.
     *
     *  @see    java.util.Objects#requireNonNullElse(Object, Object)
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNullElse( final T obj, final T defaultObj ) throws NullArgumentException
    {
        return java.util.Objects.requireNonNullElse( obj, requireNonNullArgument( defaultObj, "defaultObj" ) );
    }   //  requireNonNullElse()

    /**
     *  <p>{@summary Returns the first argument if it is not {@code null},
     *  otherwise it returns the non-{@code null} value returned by
     *  {@link Supplier#get() supplier.get()}.}</p>
     *  <p>This implementation behaves different from that in
     *  {@link java.util.Objects#requireNonNullElseGet(Object,Supplier) java.util.Objects.requireNonNullElseGet(Object,Supplier)}
     *  as it will always check that the supplier is not {@code null}.</p>
     *
     *  @note   Although the provided {@code Supplier} may not be {@code null},
     *      it may <i>return</i> {@code null}.
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
     *  @param  arg The value to check; can be {@code null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name} or {@code validation} is
     *      {@code null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T requireValidArgument( final T arg, final String name, final Predicate<? super T> validation )
    {
        requireNotEmptyArgument( name, "name" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( format( "Validation failed for '%s'", name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value, and if
     *  that fails, a
     *  {@link ValidationException}
     *  is thrown.} The message for the exception will be provided by the given
     *  message supplier that takes the name of the argument as an
     *  argument.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  arg The value to check; can be {@code null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name}, {@code validation} or
     *      {@code messageProvider} is {@code null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T requireValidArgument( final T arg, final String name, final Predicate<? super T> validation, final UnaryOperator<String> messageSupplier )
    {
        requireNotEmptyArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( messageSupplier.apply( name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value (that must
     *  not be {@code null}), and if that fails, an
     *  {@link ValidationException}
     *  with a default message is thrown.}</p>
     *  <p>If the value is {@code null}, the validation is never triggered.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code a} failed the validation.
     *  @throws NullArgumentException   {@code arg}, {@code name} or
     *      {@code validation} is {@code null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T requireValidNonNullArgument( final T arg, final String name, final Predicate<? super T> validation )
    {
        requireNotEmptyArgument( name, "name" );

        if( !requireNonNullArgument( validation, "validation" ).test( requireNonNullArgument( arg, "name" ) ) )
        {
            throw new ValidationException( format( "Validation failed for '%s'", name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidNonNullArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value (that must
     *  not be {@code null}), and if that fails, a
     *  {@link ValidationException}
     *  is thrown.} The message for the exception will be provided by the given
     *  message supplier that takes the name of the argument as an
     *  argument.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code arg}, {@code name},
     *      {@code validation} or {@code messageProvider} is {@code null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T requireValidNonNullArgument( final T arg, final String name, final Predicate<? super T> validation, final UnaryOperator<String> messageSupplier )
    {
        requireNotEmptyArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( requireNonNullArgument( arg, "name" ) ) )
        {
            throw new ValidationException( messageSupplier.apply( name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidNonNullArgument()

    /**
     *  <p>{@summary Converts the given argument {@code object} into a
     *  {@link String},
     *  usually by calling its
     *  {@link Object#toString() toString()}
     *  method.} If the value of the argument is {@code null}, the text
     *  &quot;{@link org.tquadrat.foundation.lang.CommonConstants#NULL_STRING null}&quot;
     *  will be returned instead. Arrays will be converted to a String through
     *  calling the respective {@code toString()} method from
     *  {@link java.util.Arrays}
     *  (this distinguishes this implementation from
     *  {link java.util.Objects#toString(Object, String) java.util.Objects.toString()}).
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
     *  <p>{@summary Converts the given argument {@code object} into a
     *  {@link String},
     *  usually by calling its
     *  {@link Object#toString() toString()}
     *  method.} If the value of the argument is {@code null}, the text
     *  provided as the {@code nullDefault} argument will be returned
     *  instead.</p>
     *  <p>Arrays will be converted to a string through calling the respective
     *  {@code toString()} method from
     *  {@link java.util.Arrays}
     *  (this distinguishes this implementation from
     *  {link java.util.Objects#toString(Object,String) java.util.Objects.toString(Object,String)}).</p>
     *  <p>Values of type
     *  {@link java.util.Date} or
     *  {@link java.util.Calendar}
     *  will be translated based on the
     *  {@link java.util.Locale#getDefault() default locale}
     *  – whatever that is.</p>
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
    @SuppressWarnings( {"IfStatementWithTooManyBranches", "ChainOfInstanceofChecks", "OverlyComplexMethod"} )
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
     *  <p>{@summary Converts the given argument into a
     *  {@link String}
     *  using the given instance of
     *  {@link Stringer}.}
     *  If the value of the argument is {@code null}, the text
     *  provided as the {@code nullDefault} argument will be returned
     *  instead.</p>
     *
     *  @param  <T> The type of the object.
     *  @param  value   The object; may be {@code null}.
     *  @param  stringer    The method that is used to convert the given object
     *      to a String.
     *  @param  nullDefault The text that should be returned if {@code object}
     *      is {@code null}.
     *  @return The object's string representation.
     *
     *  @see    Stringer
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> String toString( final T value, final Stringer<? super T> stringer, final String nullDefault )
    {
        requireNonNullArgument( nullDefault, "nullDefault" );

        final var retValue = nonNull( value ) ? requireNonNullArgument( stringer, "stringer" ).toString( value ) : nullDefault;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  toString()
}
//  class Objects

/*
 *  End of File
 */