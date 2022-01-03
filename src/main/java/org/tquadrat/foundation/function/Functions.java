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

package org.tquadrat.foundation.function;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.UtilityClass;
import org.tquadrat.foundation.exception.LambdaContainerException;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;
import org.tquadrat.foundation.function.tce.TCEBiConsumer;
import org.tquadrat.foundation.function.tce.TCEBiFunction;
import org.tquadrat.foundation.function.tce.TCEBinaryOperator;
import org.tquadrat.foundation.function.tce.TCEConsumer;
import org.tquadrat.foundation.function.tce.TCEFunction;
import org.tquadrat.foundation.function.tce.TCEPredicate;
import org.tquadrat.foundation.function.tce.TCESupplier;
import org.tquadrat.foundation.function.tce.TCETriConsumer;
import org.tquadrat.foundation.function.tce.TCETriFunction;

/**
 *  Some helper methods for the use with lambdas and functional interfaces.
 *
 *  <h2>TCE Wrapper</h2>
 *  The methods of the
 *  {@linkplain java.lang.FunctionalInterface functional interfaces}
 *  in the package
 *  {@link java.util.function}
 *  do not declare any checked exceptions - for good reasons, of course. But
 *  sometimes, it would be nice to have that capability.<br>
 *  <br>Using the wrapper methods in this class, you can achieve that like
 *  this:<pre><code>  &hellip;
 *  import static org.tquadrat.foundation.function.Functions.*;
 *  &hellip;
 *
 *  &hellip;
 *  Appendable appendable = &hellip;
 *  Consumer appender = wrapConsumer( s -&gt; appendable.append( s ) );
 *  &hellip;
 *
 *  &hellip;
 *  try
 *  {
 *    appender.accept( "&hellip;" );
 *  }
 *  catch( LambdaContainerException e )
 *  {
 *    throw (IOException) e.getCause();
 *  }
 *  &hellip;</code></pre>
 *  &quot;<i>TCE</i>&quot; stands for &quot;<b>T</b>hrows <b>C</b>hecked
 *  <b>E</b>xception&quot;.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Functions.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@UtilityClass
@ClassVersion( sourceVersion = "$Id: Functions.java 820 2020-12-29 20:34:22Z tquadrat $" )
public final class Functions
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  An empty array of
     *  {@link BinaryOperator}
     *  objects.
     */
    @API( status = STABLE, since = "0.0.5" )
    @SuppressWarnings( "rawtypes" )
    public static final BinaryOperator [] EMPTY_BinaryOperator_ARRAY = new BinaryOperator [0];

    /**
     *  An empty array of
     *  {@link BiConsumer}
     *  objects.
     */
    @API( status = STABLE, since = "0.0.5" )
    @SuppressWarnings( "rawtypes" )
    public static final BiConsumer [] EMPTY_BiConsumer_ARRAY = new BiConsumer [0];

    /**
     *  An empty array of
     *  {@link BiFunction}
     *  objects.
     */
    @API( status = STABLE, since = "0.0.5" )
    @SuppressWarnings( "rawtypes" )
    public static final BiFunction [] EMPTY_BiFunction_ARRAY = new BiFunction [0];

    /**
     *  An empty array of
     *  {@link Consumer}
     *  objects.
     */
    @API( status = STABLE, since = "0.0.5" )
    @SuppressWarnings( "rawtypes" )
    public static final Consumer [] EMPTY_Consumer_ARRAY = new Consumer [0];

    /**
     *  An empty array of
     *  {@link Function}
     *  objects.
     */
    @API( status = STABLE, since = "0.0.5" )
    @SuppressWarnings( "rawtypes" )
    public static final Function [] EMPTY_Function_ARRAY = new Function [0];

    /**
     *  An empty array of
     *  {@link Predicate}
     *  objects.
     */
    @API( status = STABLE, since = "0.0.5" )
    @SuppressWarnings( "rawtypes" )
    public static final Predicate [] EMPTY_Predicate_ARRAY = new Predicate [0];

    /**
     *  An empty array of
     *  {@link Supplier}
     *  objects.
     */
    @API( status = STABLE, since = "0.0.5" )
    @SuppressWarnings( "rawtypes" )
    public static final Supplier [] EMPTY_Supplier_ARRAY = new Supplier [0];

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance allowed for this class.
     */
    private Functions() { throw new PrivateConstructorForStaticClassCalledError( Functions.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Wraps an instance of
     *  {@link TCEBiConsumer}
     *  to an instance of
     *  {@link BiConsumer}
     *  that throws an instance of
     *  {@link LambdaContainerException}
     *  with the causing exception, in case the wrapped instance emitted a
     *  {@linkplain Exception checked exception}.
     *
     *  @param  <T1> The type of the first argument taken by the consumer.
     *  @param  <T2> The type of the second argument taken by the consumer.
     *  @param  consumer    The consumer to wrap.
     *  @return The wrapped consumer.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T1,T2> BiConsumer<T1,T2> wrapBiConsumer( final TCEBiConsumer<? super T1, ? super T2> consumer )
    {
        final BiConsumer<T1,T2> retValue = (arg1, arg2) ->
        {
            try
            {
                consumer.accept( arg1, arg2 );
            }
            catch( final Exception e )
            {
                throw new LambdaContainerException( e );
            }
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  wrapBiConsumer()

    /**
     *  Wraps an instance of
     *  {@link TCEBiFunction}
     *  to an instance of
     *  {@link BiFunction}
     *  that throws an instance of
     *  {@link LambdaContainerException}
     *  with the causing exception, in case the wrapped instance emitted a
     *  {@linkplain Exception checked exception}.
     *
     *  @param  <T1>    The type of the first argument to the function.
     *  @param  <T2>    The type of the second argument to the function.
     *  @param  <R> The type of the result of the function.
     *  @param  function    The function to wrap.
     *  @return The wrapped function.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T1,T2,R> BiFunction<T1,T2,R> wrapBiFunction( final TCEBiFunction<? super T1, ? super T2, ? extends R> function )
    {
        final BiFunction<T1,T2,R> retValue = (arg1,arg2) ->
        {
            try
            {
                return function.apply( arg1, arg2 );
            }
            catch( final Exception e )
            {
                throw new LambdaContainerException( e );
            }
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  wrapBiFunction()

    /**
     *  Wraps an instance of
     *  {@link TCEBinaryOperator}
     *  to an instance of
     *  {@link BinaryOperator}
     *  that throws an instance of
     *  {@link LambdaContainerException}
     *  with the causing exception, in case the wrapped instance emitted a
     *  {@linkplain Exception checked exception}.
     *
     *  @param  <T> The type of the operands and result of the operator.
     *  @param  function    The function to wrap.
     *  @return The wrapped function.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> BinaryOperator<T> wrapBinaryOperator( final TCEBinaryOperator<T> function )
    {
        final BinaryOperator<T> retValue = (arg1,arg2) ->
        {
            try
            {
                return function.apply( arg1, arg2 );
            }
            catch( final Exception e )
            {
                throw new LambdaContainerException( e );
            }
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  wrapBinaryOperator()

    /**
     *  Wraps an instance of
     *  {@link TCEConsumer}
     *  to an instance of
     *  {@link Consumer}
     *  that throws an instance of
     *  {@link LambdaContainerException}
     *  with the causing exception, in case the wrapped instance emitted a
     *  {@linkplain Exception checked exception}.
     *
     *  @param  <T> The type of arguments taken by the consumer.
     *  @param  consumer    The consumer to wrap.
     *  @return The wrapped consumer.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> Consumer<T> wrapConsumer( final TCEConsumer<? super T> consumer )
    {
        final Consumer<T> retValue = arg ->
        {
            try
            {
                consumer.accept( arg );
            }
            catch( final Exception e )
            {
                throw new LambdaContainerException( e );
            }
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  wrapConsumer()

    /**
     *  Wraps an instance of
     *  {@link TCEFunction}
     *  to an instance of
     *  {@link Function}
     *  that throws an instance of
     *  {@link LambdaContainerException}
     *  with the causing exception, in case the wrapped instance emitted a
     *  {@linkplain Exception checked exception}.
     *
     *  @param  <T> The type of the input to the function.
     *  @param  <R> The type of the result of the function.
     *  @param  function    The function to wrap.
     *  @return The wrapped function.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T,R> Function<T,R> wrapFunction( final TCEFunction<? super T, ? extends R> function )
    {
        final Function<T,R> retValue = arg ->
        {
            try
            {
                return function.apply( arg );
            }
            catch( final Exception e )
            {
                throw new LambdaContainerException( e );
            }
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  wrapFunction()

    /**
     *  Wraps an instance of
     *  {@link TCEPredicate}
     *  to an instance of
     *  {@link Predicate}
     *  that throws an instance of
     *  {@link LambdaContainerException}
     *  with the causing exception, in case the wrapped instance emitted a
     *  {@linkplain Exception checked exception}.
     *
     *  @param  <T> The type of the input to the predicate.
     *  @param  predicate   The predicate to wrap.
     *  @return The wrapped predicate.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> Predicate<T> wrapPredicate( final TCEPredicate<? super T> predicate )
    {
        final Predicate<T> retValue = arg ->
        {
            try
            {
                return predicate.test( arg );
            }
            catch( final Exception e )
            {
                throw new LambdaContainerException( e );
            }
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  wrapPredicate()

    /**
     *  Wraps an instance of
     *  {@link TCESupplier}
     *  to an instance of
     *  {@link Supplier}
     *  that throws an instance of
     *  {@link LambdaContainerException}
     *  with the causing exception, in case the wrapped instance emitted a
     *  {@linkplain Exception checked exception}.
     *
     *  @param  <T> The type of results supplied by the supplier.
     *  @param  supplier    The supplier to wrap.
     *  @return The wrapped supplier.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T> Supplier<T> wrapSupplier( final TCESupplier<? extends T> supplier )
    {
        final Supplier<T> retValue = () ->
        {
            try
            {
                return supplier.get();
            }
            catch( final Exception e )
            {
                throw new LambdaContainerException( e );
            }
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  wrapSupplier()

    /**
     *  Wraps an instance of
     *  {@link TCETriConsumer}
     *  to an instance of
     *  {@link TriConsumer}
     *  that throws an instance of
     *  {@link LambdaContainerException}
     *  with the causing exception, in case the wrapped instance emitted a
     *  {@linkplain Exception checked exception}.
     *
     *  @param  <T1> The type of the first argument taken by the consumer.
     *  @param  <T2> The type of the second argument taken by the consumer.
     *  @param  <T3> The type of the third argument taken by the consumer.
     *  @param  consumer    The consumer to wrap.
     *  @return The wrapped consumer.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T1,T2,T3> TriConsumer<T1,T2,T3> wrapTriConsumer( final TCETriConsumer<? super T1, ? super T2, ? super T3> consumer )
    {
        final TriConsumer<T1,T2,T3> retValue = (arg1, arg2, arg3) ->
        {
            try
            {
                consumer.accept( arg1, arg2, arg3 );
            }
            catch( final Exception e )
            {
                throw new LambdaContainerException( e );
            }
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  wrapTriConsumer()

    /**
     *  Wraps an instance of
     *  {@link TCETriFunction}
     *  to an instance of
     *  {@link TriFunction}
     *  that throws an instance of
     *  {@link LambdaContainerException}
     *  with the causing exception, in case the wrapped instance emitted a
     *  {@linkplain Exception checked exception}.
     *
     *  @param  <T1> The type of the first argument taken by the function.
     *  @param  <T2> The type of the second argument taken by the function.
     *  @param  <T3> The type of the third argument taken by the function.
     *  @param  <R> The type of the result of the function.
     *  @param  function    The function to wrap.
     *  @return The wrapped function.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final <T1,T2,T3,R> TriFunction<T1,T2,T3,R> wrapTriFunction( final TCETriFunction<? super T1, ? super T2, ? super T3, ? extends R> function )
    {
        final TriFunction<T1,T2,T3,R> retValue = (arg1, arg2, arg3) ->
        {
            try
            {
                return function.apply( arg1, arg2, arg3 );
            }
            catch( final Exception e )
            {
                throw new LambdaContainerException( e );
            }
        };

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  wrapTriFunction()
}
//  class Functions

/*
 *  End of File
 */