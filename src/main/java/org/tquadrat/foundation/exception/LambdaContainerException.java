/*
 * ============================================================================
 *  Copyright © 2002-2020 by Thomas Thrien.
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

package org.tquadrat.foundation.exception;

import static java.util.Arrays.stream;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.internal.SharedFormatter.format;

import java.io.Serial;
import java.util.Collection;
import java.util.stream.Stream;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.function.Functions;

/**
 *  <p>{@summary A &quot;container&quot; exception for exception thrown within
 *  lambda expressions.}</p>
 *  <p>When a method that emits a checked exception is called inside a lambda
 *  function, this has to be caught <i>inside</i> that function; it is not
 *  possible to declare that exception for the method, as long as the
 *  underlying
 *  {@linkplain FunctionalInterface functional interface}
 *  have not done that already.</p>
 *  <p>Unfortunately, the methods from the interfaces in the package
 *  {@link java.util.function}
 *  do not declare any exception, for good reason. So the code below is not
 *  possible:</p>
 *  <pre><code>  &hellip;
 *  Appendable appendable = &hellip;
 *  Consumer appender = s -&gt; appendable.append( s );
 *  appender.accept( "&hellip;" );
 *  &hellip;</code></pre>
 *  <p>because
 *  {@link Appendable#append(CharSequence)}
 *  declares to throw an
 *  {@link java.io.IOException}.</p>
 *  <p>This class now is meant to wrap those exceptions and to allow them to
 *  bubble up to the caller:</p>
 *  <pre><code>  &hellip;
 *  Appendable appendable = &hellip;
 *  Consumer appender =
 *  {
 *    try
 *    {
 *      s -&gt; appendable.append( s );
 *    }
 *    catch( IOException e )
 *    {
 *      throw new LambdaContainerException( e );
 *    }
 *  }
 *
 *  try
 *  {
 *    appender.accept( "&hellip;" );
 *  }
 *  catch( LambdaContainerException e )
 *  {
 *    throw (IOException) e.getCause();
 *  }
 *  &hellip;</code></pre>
 *  <p>When said above that the methods from the functional interfaces in the
 *  {@code java.util.function} <i>unfortunately</i> do not declare any
 *  exception, this was only focused on their use with code that may emit
 *  checked exceptions. But in fact it is a good thing that the methods in
 *  these interfaces do not declare any exceptions, as this would have polluted
 *  any of the APIs that make use of these functional interfaces. And using the
 *  pattern above would be an alternative. Another would be the methods
 *  provided in the class
 *  {@link Functions}.</p>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: LambdaContainerException.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( "removal" )
@ClassVersion( sourceVersion = "$Id: LambdaContainerException.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public sealed class LambdaContainerException extends RuntimeException
    /*
     *  LambdaContainerException will allow to be extended by the deprecated
     *  ExceptionContainer until that class is finally removed.
     */
    permits org.tquadrat.foundation.function.ExceptionContainer
{
        /*---------------*\
    ====** Inner Classes **====================================================
        \*---------------*/
    /**
     *  The exception that is thrown when the container holds an unexpected
     *  exception.
     */
    private class UnexpectedException extends RuntimeException
    {
            /*------------------------*\
        ====** Static Initialisations **=======================================
            \*------------------------*/
        /**
         *  The serial version UID for objects of this class: {@value}.
         */
        @Serial
        private static final long serialVersionUID = 1L;

            /*--------------*\
        ====** Constructors **=================================================
            \*--------------*/
        /**
         *  Creates a new {@code UnexpectedException} instance.
         */
        public UnexpectedException()
        {
            super( format( "The Exception '%s' was not expected", LambdaContainerException.this.getCause().getClass().getName() ) );
            setStackTrace( LambdaContainerException.this.getCause().getStackTrace() );
        }   //  UnexpectedException()

            /*---------*\
        ====** Methods **======================================================
            \*---------*/
        /**
         *  {@inheritDoc}
         *
         *  @see Throwable#getCause()
         */
        @Override
        public final synchronized Throwable getCause() { return LambdaContainerException.this.getCause(); }
    }
    //  class UnexpectedException

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
     *  Creates a new {@code LambdaContainerException} instance for the given
     *  exception.
     *
     *  @param  e   The exception to wrap; <i>cannot</i> be {@code null}.
     */
    public LambdaContainerException( final Exception e )
    {
        super( null, requireNonNullArgument( e, "e" ) );
    }   //  LambdaContainerException()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Checks whether the contained Exception is somehow expected.
     *
     *  @param  expected    The expected exceptions.
     *  @return {@code true} if the contained Exception is among the list of
     *      expected exceptions, {@code false} otherwise.
     */
    public final boolean checkIfExpected( final Stream<Class<? extends Exception>> expected )
    {
        final var cause = getCause();
        final var retValue = requireNonNullArgument( expected, "expected" ).anyMatch( e -> e.isInstance( cause ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  checkIfExpected()

    /**
     *  Checks whether the contained Exception is somehow expected.
     *
     *  @param  expected    The expected exceptions.
     *  @return {@code true} if the contained Exception is among the list of
     *      expected exceptions, {@code false} otherwise.
     */
    public final boolean checkIfExpected( final Collection<Class<? extends Exception>> expected )
    {
        final var retValue = checkIfExpected( requireNonNullArgument( expected, "expected" ).stream() );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  checkIfExpected()

    /**
     *  Checks whether the contained Exception is somehow expected.
     *
     *  @param  expected    The expected exceptions.
     *  @return {@code true} if the contained Exception is among the list of
     *      expected exceptions, {@code false} otherwise.
     */
    @SafeVarargs
    public final boolean checkIfExpected( final Class<? extends Exception>... expected )
    {
        final var retValue = checkIfExpected( stream( requireNonNullArgument( expected, "expected" ) ) );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  checkIfExpected()

    /**
     *  Returns the contained Exception if it is of the given type; otherwise
     *  a
     *  {@link RuntimeException}
     *  is thrown.
     *
     *  @param  <T> The expected Exception type.
     *  @param  exceptionType   The expected type.
     *  @return The contained Exception.
     *  @throws RuntimeException    This is in fact an
     *      {@link UnexpectedException} that is thrown when the contained
     *      Exception was not expected.
     */
    @SuppressWarnings( "ProhibitedExceptionDeclared" )
    public final <T> T getCheckedCause( final Class<T> exceptionType ) throws RuntimeException
    {
        final var cause = getCause();
        if( !requireNonNullArgument( exceptionType, "exceptionType" ).isInstance( cause ) )
        {
            throw new UnexpectedException();
        }

        final var retValue = exceptionType.cast( cause );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  getCheckedCause()
}
//  class LambdaContainerException

/*
 *  End of File
 */