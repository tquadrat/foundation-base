/*
 * ============================================================================
 * Copyright © 2002-2026 by Thomas Thrien.
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
import static org.apiguardian.api.API.Status.DEPRECATED;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
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
 *  @version $Id: Objects.java 1258 2026-06-04 18:33:06Z tquadrat $
 *  @since 0.0.1
 *
 *  @UMLGraph.link
 */
@UtilityClass
@SuppressWarnings( {"ClassWithTooManyMethods", "UseOfObsoleteDateTimeApi", "OverlyComplexClass"} )
@ClassVersion( sourceVersion = "$Id: Objects.java 1258 2026-06-04 18:33:06Z tquadrat $" )
@API( status = STABLE, since = "0.0.1" )
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
     *
     *  @since 0.0.5
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
     *
     *  @since 0.0.5
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
     *
     *  @since 0.0.5
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
     *  condition resolves to {@false}.}</p>
     *  <p>Basically, this method is a replacement for the code sequence
     *  below:</p>
     *  <div class="source-container"><pre>…
     *  if( !&lt;<i>condition</i>&gt; )
     *  {
     *      throw new &lt;<i>WhatEver</i>&gt;Exception( &lt;<i>WhatEverMessage</i>&gt; );
     *  }
     *  …</pre></div>
     *  <p>Code using this method may be easier to read than the {@code if}
     *  statement above:</p>
     *  <div class="source-container"><pre>…
     *  checkState( &lt;<i>condition</i>&gt;, () -> new &lt;<i>WhatEver</i>&gt;Exception( &lt;<i>WhatEverMessage</i>&gt; ) );
     *  …</pre></div>
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
     *  <p>Consequently, if both arguments are {@null}, 0 is returned.</p>
     *  <p>Calls
     *  {@link java.util.Objects#compare(Object,Object,Comparator) java.util.Objects#compare()}
     *  internally, but different from that method, this implementation will
     *  throw a
     *  {@link NullArgumentException}
     *  in case the {@code comparator} is {@null}.</p>
     *
     *  @param  <T> The type of the objects being compared.
     *  @param  object  An object.
     *  @param  other   Another object to be compared with the first object.
     *  @param  comparator  The
     *      {@link Comparator}
     *      to compare the first two arguments.
     *  @return 0 if the arguments are identical and +1, 0, or -1, based on the
     *      return value of {@code c.compare(a, b)} otherwise.
     *  @throws NullArgumentException   The {@code comparator} is {@null}.
     *
     *  @see Comparable
     *  @see Comparator
     *
     *  @since 0.0.5
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> int compare( final T object, final T other, final Comparator<? super T> comparator ) throws NullArgumentException
    {
        final var retValue = object == other ? 0 : signum( java.util.Objects.compare( object, other, requireNonNullArgument( comparator, "comparator" ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  compare()

    /**
     *  <p>{@summary Returns {@true} if the arguments are deeply equal to
     *  each other and {@false} otherwise.}</p>
     *  <p>Two {@null} values are deeply equal. If both arguments are
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
     *  @param  object  An object.
     *  @param  other   Another object to be compared with the first object for
     *      deep equality.
     *  @return {@true} if the arguments are deeply equal to each other
     *      and {@false} otherwise.
     *
     *  @see    Arrays#deepEquals(Object[],Object[])
     *  @see    Objects#equals(Object,Object)
     *
     *  @since 0.0.5
     */
    @SuppressWarnings( "BooleanMethodNameMustStartWithQuestion" )
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean deepEquals( final Object object, final Object other ) { return java.util.Objects.deepEquals( object, other ); }

    /**
     *  <p>{@summary Returns {@true} if the arguments are equal to each
     *  other and {@false} otherwise.}</p>
     *  <p> Consequently, if both arguments are {@null}, {@true} is
     *  returned and if exactly one argument is {@null}, {@false} is
     *  returned.  Otherwise, equality is determined by using the
     *  {@link Object#equals(Object) equals()}
     *  method of the first argument.</p>
     *  <p>Calls
     *  {@link java.util.Objects#equals(Object, Object)}
     *  internally.</p>
     *
     *  @param  object  An object.
     *  @param  other   Another object to be compared with the first one for
     *      equality.
     *  @return {@true} if the arguments are equal to each other and
     *      {@false} otherwise.
     *
     *  @see    Object#equals(Object)
     *
     *  @since 0.0.5
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean equals( final Object object, final Object other ) { return java.util.Objects.equals( object, other ); }

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
     *
     *  @since 0.0.5
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int hash( final Object... values ) { return Arrays.hashCode( values ); }

    /**
     *  <p>{@summary Returns the hash code of a non-{@null} argument and 0
     *  for a {@null} argument.}</p>
     *  <p>Calls
     *  {@link java.util.Objects#hashCode(Object) java.util.Objects.hashCode(Object)}
     *  internally.</p>
     *
     *  @param o   An object.
     *  @return The hash code of an argument that is not {@null}, and 0
     *      for a {@null} argument,
     *
     *  @see    Object#hashCode
     *
     *  @since 0.0.5
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int hashCode( final Object o ) { return java.util.Objects.hashCode( o ); }

    /**
     *  <p>{@summary Returns {@true} if the provided reference is
     *  {@null}, otherwise returns {@false}.}</p>
     *  <p>This method can be used as a
     *  {@link java.util.function.Predicate},
     *  {@code filter(Objects::isNull)}.</p>
     *  <p>Calls
     *  {@link java.util.Objects#isNull(Object) java.util.Objects.isNull()}
     *  internally.</p>
     *
     *  @param  obj A reference to be checked against {@null}.
     *  @return {@true} if the provided reference is {@null},
     *      otherwise {@false}
     *
     *  @see    java.util.function.Predicate
     *  @see    org.tquadrat.foundation.lang.CommonConstants#IS_NULL
     *
     *  @since 0.0.5
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final boolean isNull( final Object obj ) { return java.util.Objects.isNull( obj ); }

    /**
     *  <p>{@summary Provides a replacement value if the given value is
     *  {@null}.}</p>
     *  <p>This is basically a shortcut to</p>
     *  <div class="source-container"><pre>Optional.ofNullable( value ).orElseGet( supplier );</pre></div>
     *
     *  @param  <T> The type of the object to map.
     *  @param  value   The object to map; can be {@null} (obviously).
     *  @param  supplier    The supplier for the replacement function.
     *  @return The provided object if that is not {@null}, or the result
     *      from the supplier method. Keep in mind that this result can be
     *      {@null}!
     *
     *  @see Optional
     *  @see Optional#orElseGet(Supplier)
     *
     *  @since 0.2.2
     */
    @API( status = STABLE, since = "0.2.2" )
    public static final <T> T mapFromNull( final T value, final Supplier<? extends T> supplier )
    {
        requireNonNullArgument( supplier, "supplier" );
        final var retValue = isNull( value )
            ? supplier.get()
            : value;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  mapFromNull()

    /**
     *  <p>{@summary Provides a replacement value if the given value is
     *  {@null}.}</p>
     *  <p>This is basically a shortcut to</p>
     *  <div class="source-container"><pre>Optional.ofNullable( value ).orElse( replacement );</pre></div>
     *
     *  @param  <T> The type of the object to map.
     *  @param  value   The object to map; can be {@null}.
     *  @param  replacement  The replacement value; it may not be {@null}.
     *  @return The provided object if that is not {@null}, or the
     *      replacement value.
     *
     *  @see Optional
     *  @see Optional#orElse(Object)
     *
     *  @since 0.4.2
     */
    @API( status = STABLE, since = "0.4.2" )
    public static final <T> T mapFromNull( final T value, final T replacement )
    {
        requireNonNullArgument( replacement, "replacement" );
        final var retValue = isNull( value )
                             ? replacement
                             : value;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  mapFromNull()

    /**
     *  <p>{@summary Maps (converts) the given object instance by applying the
     *  provided mapper if the instance is not {@null}.}</p>
     *  <p>The mapper function will not be called at all if the given instance
     *  is {@null}.</p>
     *
     *  @param  <T> The type of the object to map.
     *  @param  <R> The type of the result.
     *  @param  o   The object to map; can be {@null}.
     *  @param  mapper  The mapping function.
     *  @return The result of the mapping, or {@null} if the given object
     *      instance was already {@null}. Keep in mind that the result of
     *      the mapping can be {@null}!
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
     *  provided mapper if the instance is not {@null} or returns the
     *  given default value.}</p>
     *  <p>The mapper function will not be called at all if the given instance
     *  is {@null}.</p>
     *
     *  @param  <T> The type of the object to map.
     *  @param  <R> The type of the result.
     *  @param  o   The object to map; can be {@null}.
     *  @param  mapper  The mapping function.
     *  @param  defaultValue    The default value; can be {@null}.
     *  @return The result of the mapping, or the default value if the given
     *      object instance is {@null}. Keep in mind that the result of
     *      the mapping can be {@null}!
     */
    public static final <T,R> R mapNonNull( final T o, final Function<T,? extends R> mapper, final R defaultValue )
    {
        @SuppressWarnings( "RedundantExplicitVariableType" )
        final R retValue = nonNull( o ) ? requireNonNullArgument( mapper, "mapper" ).apply( o ) : defaultValue;

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  mapNonNull()

    /**
     *  <p>{@summary Returns {@true} if the provided reference is not
     *  {@null}, otherwise returns {@false}.}</p>
     *  <p>This method exists to be used as a
     *  {@link java.util.function.Predicate},
     *  {@code filter(Objects::nonNull)}</p>
     *  <p>Calls
     *  {@link java.util.Objects#nonNull(Object) java.util.Objects.nonNull()}
     *  internally.</p>
     *
     *  @param  obj A reference to be checked against {@null}
     *  @return {@false} if the provided reference is {@null},
     *      otherwise {@true}
     *
     *  @see java.util.function.Predicate
     *  @see org.tquadrat.foundation.lang.CommonConstants#NON_NULL
     *
     *  @since 0.0.5
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
     *  @param  obj The value to check; can be {@null}.
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
     *  @param  obj The value to check; can be {@null}.
     *  @param  message The message that is set to the thrown exception.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code obj} failed the validation.
     *  @throws NullArgumentException   {@code message} is {@null}.
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
     *  @param  obj The value to check; can be {@null}.
     *  @param  messageSupplier The supplier of the detail message to be used
     *      in the event that {@code ValidationException} is thrown. If
     *      {@null} or if it returns {@null}, no detail message is
     *      provided to the exception.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws NullArgumentException   The validation is {@null}.
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
     *  must accept {@null} as a valid argument.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  obj The value to check; can be {@null}.
     *  @param  messageSupplier The supplier of the detail message to be used
     *      in the event that a {@code ValidationException} is thrown. If
     *      {@null} or if it returns {@null}, no detail message is
     *      provided.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws NullArgumentException   The validation is {@null}.
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
     *  <p>{@summary Checks if the given value {@code obj} is {@null} and
     *  throws a
     *  {@link NullArgumentException}
     *  if it is {@null}.}</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  obj The value to check.
     *  @return The value if it is not {@null}.
     *  @throws NullArgumentException   {@code obj} is {@null}.
     *
     *  @see java.util.Objects#requireNonNull(Object)
     *
     *  @since 0.0.5
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
     *  <p>{@summary Checks if the given value {@code obj} is {@null} and
     *  throws a
     *  {@link ValidationException}
     *  with the specified message if it is {@null}.}</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  obj The value to check.
     *  @param  message The message that is set to the thrown exception.
     *  @return The value if it is not {@null}.
     *  @throws NullArgumentException   {@code message} or {@code obj} is
     *      {@null}.
     *  @throws EmptyArgumentException  {@code message} is the empty String.
     *
     *  @see java.util.Objects#requireNonNull(Object,String)
     *
     *  @since 0.0.5
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
     *  {@null} and throws a customized
     *  {@link ValidationException}
     *  if it is.}</p>
     *  <p>Unlike the method
     *  {@link #requireNonNull(Object,String)},
     *  this method allows to defer the creation of the message until after the
     *  null check failed. While this may confer a performance advantage in the
     *  non-{@null} case, when deciding to call this method care should be
     *  taken that the costs of creating the message supplier are less than the
     *  cost of just creating the String message directly.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  obj The value to check.
     *  @param  messageSupplier The supplier of the detail message to be used
     *      in the event that a {@code NullArgumentException} is thrown. If
     *      {@null}, no detail message is provided.
     *  @return The value if it is not {@null}.
     *  @throws ValidationException    {@code obj} is {@null}
     *
     *  @since 0.0.5
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
     *  Checks if the given argument {@code a} is {@null} and throws a
     *  {@link NullArgumentException}
     *  if it is {@null}.
     *
     *  @param  <T> The type of the argument to check.
     *  @param  arg The argument to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The argument if it is not {@null}.
     *  @throws NullArgumentException   {@code arg} is {@null}.
     *
     *  @since 0.0.5
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNullArgument( final T arg, final String name )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );
        if( name.isBlank() ) throw new BlankArgumentException( "name" );
        if( isNull( arg ) ) throw new NullArgumentException( name );

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireNonNullArgument()

    /**
     *  <p>{@summary Checks if not both of the given arguments {@code arg} and
     *  {@code otherArg} are {@null} and throws a
     *  {@link NullArgumentException}
     *  if both are {@null}.} Otherwise, it returns {@code arg}.</p>
     *
     *  @param  <T> The type of the first argument to check.
     *  @param  arg The first argument to check; it will be returned in case of
     *      success, even if {@null}.
     *  @param  otherArg    The other argument to check.
     *  @param  name    The name of the first argument; this is used for the
     *      error message.
     *  @param  otherName   The name of the other argument; this is used for
     *      the error message.
     *  @return The first argument, even that might be {@null}.
     *  @throws NullArgumentException   Both arguments are {@null}.
     *
     *  @since 0.0.7
     */
    @API( status = STABLE, since = "0.0.7" )
    public static final <T> T requireNonNullArgument( final T arg, final Object otherArg, final String name, final String otherName )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );
        if( name.isBlank() ) throw new BlankArgumentException( "name" );
        if( isNull( otherName ) ) throw new NullArgumentException( "otherName" );
        if( otherName.isEmpty() ) throw new EmptyArgumentException( "otherName" );
        if( otherName.isBlank() ) throw new BlankArgumentException( "otherName" );
        if( isNull( arg ) && isNull( otherArg ) )
        {
            throw new NullArgumentException( name, otherName );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireNonNullArgument()

    /**
     *  <p>{@summary Checks if the given String argument {@code arg} is
     *  {@null}, empty or blank and throws a
     *  {@link NullArgumentException}
     *  if it is {@null}, an
     *  {@link EmptyArgumentException}
     *  if it is empty, or a
     *  {@link BlankArgumentException}
     *  if it is blank.}</p>
     *
     *  @param  <T> The type of the argument to check.
     *  @param  arg The argument to check; may be {@null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The argument if it is not {@null}, empty or blank.
     *  @throws NullArgumentException   {@code arg} is {@null}.
     *  @throws EmptyArgumentException   {@code arg} is empty.
     *  @throws BlankArgumentException   {@code arg} is blank.
     *
     *  @see    String#isBlank()
     *
     *  @since 0.1.0
     */
    @SuppressWarnings( "OverlyComplexMethod" )
    @API( status = STABLE, since = "0.1.0" )
    public static final <T extends CharSequence> T requireNotBlankArgument( final T arg, final String name )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );
        if( name.isBlank() ) throw new BlankArgumentException( "name" );

        switch( arg )
        {
            case null -> throw new NullArgumentException( name );
            case final String string ->
            {
                if( string.isEmpty() ) throw new EmptyArgumentException( name );
                if( string.isBlank() ) throw new BlankArgumentException( name );
            }
            case final CharSequence charSequence ->
            {
                if( charSequence.isEmpty() ) throw new EmptyArgumentException( name );
                if( charSequence.toString().isBlank() ) throw new BlankArgumentException( name );
            }
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireNotBlankArgument()

    /**
     *  <p>{@summary Checks if the given argument {@code arg} is {@null} or
     *  empty and throws a
     *  {@link NullArgumentException}
     *  if it is {@null}, or an
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
     *  will return {@false} after all elements have been taken from
     *  the {@code Enumeration} instance), the result for arguments of this
     *  type has to be taken with caution.</p>
     *  <p>For instances of
     *  {@link java.util.stream.Stream},
     *  this method will only check for {@null} (like
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
     *  @param  arg The argument to check; may be {@null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The argument if it is not {@null} or empty.
     *  @throws NullArgumentException   {@code arg} is {@null}.
     *  @throws EmptyArgumentException   {@code arg} is empty.
     *
     *  @since 0.0.5
     */
    @SuppressWarnings( "OverlyComplexMethod" )
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNotEmptyArgument( final T arg, final String name )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );
        if( name.isBlank() ) throw new BlankArgumentException( "name" );

        switch( arg )
        {
            /*
             * When using guarding expressions, the code would not get better
             * to read and to understand, as the positive cases will be handled
             * all by the default case.
             */
            case null -> throw new NullArgumentException( name );
            case final CharSequence charSequence ->
            {
                if( charSequence.isEmpty() ) throw new EmptyArgumentException( name );
            }
            case final Collection<?> collection ->
            {
                if( collection.isEmpty() ) throw new EmptyArgumentException( name );
            }
            case final Map<?,?> map ->
            {
                if( map.isEmpty() ) throw new EmptyArgumentException( name );
            }
            case final Enumeration<?> enumeration ->
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
            case final Optional<?> optional ->
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
     *  is {@null} or
     *  {@linkplain Optional#empty() empty}
     *  and throws a
     *  {@link NullArgumentException}
     *  if it is {@null}, or a
     *  {@link EmptyArgumentException}
     *  if it is empty.}</p>
     *  <p>Otherwise it returns the value of the {@code Optional}.</p>
     *  <p>This is different from the behaviour of
     *  {@link #requireNotEmptyArgument(Object,String)}
     *  with an instance of {@code Optional} as the argument to test.</p>
     *
     *  @param  <T> The type of the given {@code Optional} to check.
     *  @param  optional    The argument to check; can be {@null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @return The value of the argument if {@code optional} is not
     *      {@null}
     *      and not
     *      {@linkplain Optional#empty() empty}. This could be the empty
     *      string!
     *  @throws NullArgumentException   {@code optional} is {@null}.
     *  @throws EmptyArgumentException   {@code optional} is empty.
     *
     *  @since 0.0.5
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNotEmptyArgument( @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" ) final Optional<T> optional, final String name )
    {
        if( isNull( name ) ) throw new NullArgumentException( "name" );
        if( name.isEmpty() ) throw new EmptyArgumentException( "name" );
        if( name.isBlank() ) throw new BlankArgumentException( "name" );

        //---* Check for null *------------------------------------------------
        if( isNull( optional ) ) throw new NullArgumentException( name );
        final var retValue = optional.orElseThrow( () -> new EmptyArgumentException( name ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  requireNotEmptyArgument()

    /**
     *  <p>{@summary Returns the first argument if it is not {@null},
     *  otherwise it returns the non-{@null} second argument.}</p>
     *  <p>This implementation behaves different from that in
     *  {@link java.util.Objects#requireNonNullElse(Object,Object) java.util.Objects.requireNonNullElse(Object,Object)}
     *  as it will always check that the default is not {@null}.</p>
     *
     *  @param <T>  The type of the references.
     *  @param  obj An object reference.
     *  @param  defaultObj  Another object reference to be returned if the
     *      first argument is {@null}.
     *  @return The first argument if it is not {@null}, otherwise the
     *      second argument if it is not {@null}.
     *  @throws NullArgumentException   The {@code defaultObj} is {@null}.
     *
     *  @see    java.util.Objects#requireNonNullElse(Object, Object)
     *
     *  @since 0.0.5
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNullElse( final T obj, final T defaultObj ) throws NullArgumentException
    {
        return java.util.Objects.requireNonNullElse( obj, requireNonNullArgument( defaultObj, "defaultObj" ) );
    }   //  requireNonNullElse()

    /**
     *  <p>{@summary Returns the first argument if it is not {@null},
     *  otherwise it returns the non-{@null} value returned by
     *  {@link Supplier#get() supplier.get()}.}</p>
     *  <p>This implementation behaves different from that in
     *  {@link java.util.Objects#requireNonNullElseGet(Object,Supplier) java.util.Objects.requireNonNullElseGet(Object,Supplier)}
     *  as it will always check that the supplier is not {@null}.</p>
     *
     *  @note   Although the provided {@code Supplier} may not be {@null},
     *      it may <i>return</i> {@null}.
     *
     *  @param <T>  The type of the reference.
     *  @param  obj An object reference.
     *  @param  supplier    The supplier of a non-{@null} object of type
     *      {code T} to return if the first argument is {@null}.
     *  @return The first argument if it is not {@null}, otherwise the
     *      value returned by a call to {@code supplier.get()} if it is not
     *      {@null}.
     *  @throws NullArgumentException   The {@code supplier} is {@null}.
     *  @throws NullPointerException    {@code obj} is {@null} and the
     *      return value of {@code supplier.get()} value is {@null}, too.
     *
     *  @since 0.0.5
     */
    @SuppressWarnings( "ProhibitedExceptionDeclared" )
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> T requireNonNullElseGet( final T obj, final Supplier<? extends T> supplier ) throws NullArgumentException, NullPointerException
    {
        return java.util.Objects.requireNonNullElseGet( obj, requireNonNullArgument( supplier, "supplier" ) );
    }   //  requireNonNullElseGet()

    /**
     *  <p>{@summary Applies the given validation on the given value, and if
     *  that fails, an
     *  {@link ValidationException}
     *  with a default message is thrown.} The validation is also responsible
     *  for the {@null}-check; that means, the method
     *  {@link Predicate#test(Object) test()}
     *  of the validation may be called with {@null} as the argument.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  arg The value to check; can be {@null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name} or {@code validation} is
     *      {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T requireValidArgument( final T arg, final String name, final Predicate<? super T> validation )
    {
        requireNotBlankArgument( name, "name" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( "Validation failed for '%s'".formatted( name ) );
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
     *  <p>The validation is also responsible for the {@null}-check; that
     *  means, the method
     *  {@link Predicate#test(Object) test()}
     *  of the validation may be called with {@null} as the argument.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  arg The value to check; can be {@null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name}, {@code validation} or
     *      {@code messageProvider} is {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     *  @deprecated Consider to migrate to
     *      {@link #requireValidArgument(Object,String,Predicate,BiFunction)}.
     */
    @Deprecated( since = "0.25.11", forRemoval = true )
    @API( status = DEPRECATED, since = "0.1.0" )
    public static final <T> T requireValidArgument( final T arg, final String name, final Predicate<? super T> validation, final UnaryOperator<String> messageSupplier )
    {
        requireNotBlankArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( messageSupplier.apply( name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value, and if
     *  that fails, a
     *  {@link ValidationException}
     *  is thrown.} The message for the exception will be provided by the given
     *  {@code messageSupplier} that takes the {@code name} as the first
     *  argument and the value ({@code arg}) as the second argument to compose
     *  the message for the {@code ValidationException} in case the validation
     *  failed.</p>
     *  <p>The validation is also responsible for the {@null}-check; that
     *  means, the method
     *  {@link Predicate#test(Object) test()}
     *  of the validation may be called with {@null} as the argument.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  arg The value to check; can be {@null}.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name}, {@code validation} or
     *      {@code messageProvider} is {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.25.11
     */
    @API( status = STABLE, since = "0.25.11" )
    public static final <T> T requireValidArgument( final T arg, final String name, final Predicate<? super T> validation, final BiFunction<String,T,String> messageSupplier )
    {
        requireNotBlankArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( messageSupplier.apply( name, arg ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidArgument()

    /**
     *  Applies the given validation on the given value, and if that fails, an
     *  {@link ValidationException}
     *  with a default message is thrown.
     *
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name} or {@code validation} is
     *      {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.2.0
     */
    @API( status = STABLE, since = "0.2.0" )
    public static final double requireValidDoubleArgument( final double arg, final String name, final DoublePredicate validation )
    {
        requireNotBlankArgument( name, "name" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( "Validation failed for '%s'".formatted( name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidDoubleArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value, and if
     *  that fails, a
     *  {@link ValidationException}
     *  is thrown.} The message for the exception will be provided by the given
     *  message supplier that takes the name of the argument as an
     *  argument.</p>
     *
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name}, {@code validation} or
     *      {@code messageProvider} is {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.2.0
     *  @deprecated Consider to migrate to
     *      {@link #requireValidDoubleArgument(double,String,DoublePredicate,BiFunction)}
     */
    @API( status = DEPRECATED, since = "0.2.0" )
    @Deprecated( since = "0.25.11", forRemoval = true )
    public static final double requireValidDoubleArgument( final double arg, final String name, final DoublePredicate validation, final UnaryOperator<String> messageSupplier )
    {
        requireNotBlankArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( messageSupplier.apply( name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidDoubleArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value, and if
     *  that fails, a
     *  {@link ValidationException}
     *  is thrown.} The message for the exception will be provided by the given
     *  {@code messageSupplier} that takes the {@code name} as the first
     *  argument and the value ({@code arg}) as the second argument to compose
     *  the message for the {@code ValidationException} in case the validation
     *  failed.</p>
     *
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name}, {@code validation} or
     *      {@code messageProvider} is {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.25.11
     */
    @API( status = STABLE, since = "0.25.11" )
    public static final double requireValidDoubleArgument( final double arg, final String name, final DoublePredicate validation, final BiFunction<String,Double,String> messageSupplier )
    {
        requireNotBlankArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( messageSupplier.apply( name, Double.valueOf( arg ) ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidDoubleArgument()

    /**
     *  Applies the given validation on the given value, and if that fails, an
     *  {@link ValidationException}
     *  with a default message is thrown.
     *
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name} or {@code validation} is
     *      {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.2.0
     */
    @API( status = STABLE, since = "0.2.0" )
    public static final int requireValidIntegerArgument( final int arg, final String name, final IntPredicate validation )
    {
        requireNotBlankArgument( name, "name" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( "Validation failed for '%s'".formatted( name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidIntegerArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value, and if
     *  that fails, a
     *  {@link ValidationException}
     *  is thrown.} The message for the exception will be provided by the given
     *  message supplier that takes the name of the argument as an
     *  argument.</p>
     *
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name}, {@code validation} or
     *      {@code messageProvider} is {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.2.0
     *  @deprecated Consider the migration to
     *      {@link #requireValidIntegerArgument(int,String,IntPredicate,BiFunction)}
     */
    @API( status = DEPRECATED, since = "0.2.0" )
    @Deprecated( since = "0.25.11", forRemoval = true )
    public static final int requireValidIntegerArgument( final int arg, final String name, final IntPredicate validation, final UnaryOperator<String> messageSupplier )
    {
        requireNotBlankArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( messageSupplier.apply( name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidIntegerArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value, and if
     *  that fails, a
     *  {@link ValidationException}
     *  is thrown.} The message for the exception will be provided by the given
     *  {@code messageSupplier} that takes the {@code name} as the first
     *  argument and the value ({@code arg}) as the second argument to compose
     *  the message for the {@code ValidationException} in case the validation
     *  failed.</p>
     *
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name}, {@code validation} or
     *      {@code messageProvider} is {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.25.11
     */
    @API( status = STABLE, since = "0.25.11" )
    public static final int requireValidIntegerArgument( final int arg, final String name, final IntPredicate validation, final BiFunction<String,Integer,String> messageSupplier )
    {
        requireNotBlankArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( messageSupplier.apply( name, Integer.valueOf( arg ) ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidIntegerArgument()

    /**
     *  Applies the given validation on the given value, and if that fails, an
     *  {@link ValidationException}
     *  with a default message is thrown.
     *
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name} or {@code validation} is
     *      {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.2.0
     */
    @API( status = STABLE, since = "0.2.0" )
    public static final long requireValidLongArgument( final long arg, final String name, final LongPredicate validation )
    {
        requireNotBlankArgument( name, "name" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( "Validation failed for '%s'".formatted( name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidLongArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value, and if
     *  that fails, a
     *  {@link ValidationException}
     *  is thrown.} The message for the exception will be provided by the given
     *  message supplier that takes the name of the argument as an
     *  argument.</p>
     *
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name}, {@code validation} or
     *      {@code messageProvider} is {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.2.0
     *  @deprecated Consider the migration to
     *      {@link #requireValidLongArgument(long,String,LongPredicate,BiFunction)}.
     */
    @API( status = DEPRECATED, since = "0.2.0" )
    @Deprecated( since = "0.25.11" )
    public static final long requireValidLongArgument( final long arg, final String name, final LongPredicate validation, final UnaryOperator<String> messageSupplier )
    {
        requireNotBlankArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( messageSupplier.apply( name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidLongArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value, and if
     *  that fails, a
     *  {@link ValidationException}
     *  is thrown.} The message for the exception will be provided by the given
     *  {@code messageSupplier} that takes the {@code name} as the first
     *  argument and the value ({@code arg}) as the second argument to compose
     *  the message for the {@code ValidationException} in case the validation
     *  failed.</p>
     *
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @param  messageSupplier The function that generates the message for the
     *      exception.
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code arg} failed the validation.
     *  @throws NullArgumentException   {@code name}, {@code validation} or
     *      {@code messageProvider} is {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.25.11
     */
    @API( status = STABLE, since = "0.25.11" )
    public static final long requireValidLongArgument( final long arg, final String name, final LongPredicate validation, final BiFunction<String,Long,String> messageSupplier )
    {
        requireNotBlankArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( arg ) )
        {
            throw new ValidationException( messageSupplier.apply( name, Long.valueOf( arg ) ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidLongArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value (that must
     *  not be {@null}), and if that fails, an
     *  {@link ValidationException}
     *  with a default message is thrown.}</p>
     *  <p>If the value is {@null}, the validation is never triggered.</p>
     *
     *  @param  <T> The type of the value to check.
     *  @param  arg The value to check.
     *  @param  name    The name of the argument; this is used for the error
     *      message.
     *  @param  validation  The validation
     *  @return The value if the validation succeeds.
     *  @throws ValidationException {@code a} failed the validation.
     *  @throws NullArgumentException   {@code arg}, {@code name} or
     *      {@code validation} is {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final <T> T requireValidNonNullArgument( final T arg, final String name, final Predicate<? super T> validation )
    {
        requireNotBlankArgument( name, "name" );

        if( !requireNonNullArgument( validation, "validation" ).test( requireNonNullArgument( arg, "name" ) ) )
        {
            throw new ValidationException( "Validation failed for '%s'".formatted( name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidNonNullArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value (that must
     *  not be {@null}), and if that fails, a
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
     *      {@code validation} or {@code messageProvider} is {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.1.0
     *  @deprecated Consider the migration to
     *      {@link #requireValidNonNullArgument(Object,String,Predicate,BiFunction)}
     */
    @API( status = DEPRECATED, since = "0.1.0" )
    @Deprecated( since = "0.25.11", forRemoval = true )
    public static final <T> T requireValidNonNullArgument( final T arg, final String name, final Predicate<? super T> validation, final UnaryOperator<String> messageSupplier )
    {
        requireNotBlankArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( requireNonNullArgument( arg, "name" ) ) )
        {
            throw new ValidationException( messageSupplier.apply( name ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidNonNullArgument()

    /**
     *  <p>{@summary Applies the given validation on the given value (that must
     *  not be {@null}), and if that fails, a
     *  {@link ValidationException}
     *  is thrown.} The message for the exception will be provided by the given
     *  {@code messageSupplier} that takes the {@code name} as the first
     *  argument and the value ({@code arg}) as the second argument to compose
     *  the message for the {@code ValidationException} in case the validation
     *  failed.</p>
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
     *      {@code validation} or {@code messageProvider} is {@null}.
     *  @throws EmptyArgumentException  {@code name} is the empty String.
     *
     *  @since 0.25.11
     */
    @API( status = STABLE, since = "0.25.11" )
    public static final <T> T requireValidNonNullArgument( final T arg, final String name, final Predicate<? super T> validation, final BiFunction<String,T,String> messageSupplier )
    {
        requireNotBlankArgument( name, "name" );
        requireNonNullArgument( messageSupplier, "messageSupplier" );

        if( !requireNonNullArgument( validation, "validation" ).test( requireNonNullArgument( arg, "name" ) ) )
        {
            throw new ValidationException( messageSupplier.apply( name, arg ) );
        }

        //---* Done *----------------------------------------------------------
        return arg;
    }   //  requireValidNonNullArgument()

    /**
     *  <p>{@summary Converts the given argument {@code object} into a
     *  {@link String},
     *  usually by calling its
     *  {@link Object#toString() toString()}
     *  method.} If the value of the argument is {@null}, the text
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
     *  @param  object  The object; may be {@null}.
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
     *
     *  @since 0.0.5
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
     *  method.} If the value of the argument is {@null}, the text
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
     *  @param  object  The object; may be {@null}.
     *  @param  nullDefault The text that should be returned if {@code object}
     *      is {@null}.
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
     *
     *  @since 0.0.5
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
     *  If the value of the argument is {@null}, the text
     *  provided as the {@code nullDefault} argument will be returned
     *  instead.</p>
     *
     *  @param  <T> The type of the object.
     *  @param  value   The object; may be {@null}.
     *  @param  stringer    The method that is used to convert the given object
     *      to a String.
     *  @param  nullDefault The text that should be returned if {@code object}
     *      is {@null}.
     *  @return The object's string representation.
     *
     *  @see    Stringer
     *
     *  @since 0.0.5
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