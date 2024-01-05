/*
 * ============================================================================
 *  Copyright © 2002-2024 by Thomas Thrien.
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

package org.tquadrat.foundation.lang;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.nonNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  Instances of this record are meant to be used as the return values for
 *  methods that should either return a proper result, or an error code. <br>
 *  <br>A sample use case may look like this:
 *  <pre><code>  …
 *  public final Status&lt;Result,ErrorCode&gt; processInput( final InputStream input ) {…}
 *  …
 *
 *  …
 *  private final Throwable errorHandler( ErrorCode ) {…}
 *  …
 *
 *  …
 *  public final Result execute( final File inputFile ) throws Throwable
 *  {
 *      ErrorHandler&lt;ErrorCode&gt; errorHandler =  this::errorHandler;
 *      try( var inputStream = new FileInputStream( inputFile ) )
 *      {
 *          return processInput( inputStream ).getOrElse( errorHandler );
 *      }
 *  }
 *  …</code></pre>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Status.java 1084 2024-01-03 15:31:20Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 *
 *  @param  <V>  The type of the result value.
 *  @param  <C>  The type of the error code.
 *  @param  result  The result value; can be {@code null}.
 *  @param  errorCode   The error code; a value of {@code null} indicates a
 *      success.
 */
@SuppressWarnings( {"ProhibitedExceptionDeclared", "ProhibitedExceptionThrown"} )
@ClassVersion( sourceVersion = "$Id: Status.java 1084 2024-01-03 15:31:20Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public record Status<V,C>( V result, C errorCode )
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns the result in case of a success, otherwise executes the given
     *  error handler and throws the exception determined by it.
     *
     *  @param  errorHandler    The error handler.
     *  @return The result.
     *  @throws RuntimeException    Any exception that is determined by the
     *      error handler.
     *
     *  @since 0.2.1
     */
    @API( status = STABLE, since = "0.2.1" )
    public final V getOrElseThrow( final ErrorHandler<? super C> errorHandler ) throws RuntimeException
    {
        requireNonNullArgument( errorHandler, "errorHandler" );

        if( isFailure() ) throw errorHandler.handleError( errorCode );

        //---* Done *----------------------------------------------------------
        return result;
    }   //  getOrElseThrow()

    /**
     *  Returns whether this {@code Status} instance indicates a failure.
     *
     *  @return {@code true} if the status indicates a failure, {@code false}
     *      otherwise.
     */
    public final boolean isFailure() { return nonNull( errorCode ); }

    /**
     *  Returns whether this {@code Status} instance indicates a success.
     *
     *  @return {@code true} if the status indicates a success, {@code false}
     *      otherwise.
     */
    public final boolean isSuccess() { return isNull( errorCode ); }

    /**
     *  Performs the given conversion on success, otherwise returns
     *  {@link Optional#empty()}.
     *
     *  @param  <R> The type of the conversion result.
     *  @param  conversion  The conversion.
     *  @return An instance of
     *      {@link Optional}
     *      that holds the converted result in case of a success, or it will
     *      be
     *      {@linkplain Optional#empty() empty}
     *      otherwise.
     *
     *  @note As the result can be {@code null}, too (or the result of the
     *      conversion is {@code null}, an empty return value does not
     *      necessarily indicate a failure.
     */
    public final <R> Optional<R> map( final Function<? super V,? extends R> conversion )
    {
        requireNonNullArgument( conversion, "conversion" );

        final Optional<R> retValue = Optional.ofNullable( isSuccess() ? conversion.apply( result ) : null );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  map()

    /**
     *  Performs the given action on success, otherwise does nothing.
     *
     *  @param  action  The action.
     */
    public final void onSuccess( final Consumer<? super V> action )
    {
        requireNonNullArgument( action, "action" );
        if( isSuccess() ) action.accept( result );
    }   //  onSuccess()

    /**
     *  Performs the given action on success, otherwise throws the exception
     *  determined by the error handler.
     *
     *  @param  action  The action.
     *  @param  errorHandler    The error handler.
     *  @throws RuntimeException    Any exception that is determined by the
     *      error handler.
     */
    public final void onSuccess( final Consumer<? super V> action, final ErrorHandler<? super C> errorHandler ) throws RuntimeException
    {
        requireNonNullArgument( action, "action" );
        requireNonNullArgument( errorHandler, "errorHandler" );

        if( isFailure() ) throw errorHandler.handleError( errorCode );
        action.accept( result );
    }   //  onSuccess()

    /**
     *  Performs the given conversion on success, otherwise throws the
     *  exception determined by the error handler.
     *
     *  @param  <R> The type of the conversion result.
     *  @param  conversion  The conversion.
     *  @param  errorHandler    The error handler.
     *  @return The converted result.
     *  @throws RuntimeException    Any exception that is determined by the
     *      error handler.
     */
    public final <R> R onSuccess( final Function<? super V,R> conversion, final ErrorHandler<? super C> errorHandler ) throws RuntimeException
    {
        requireNonNullArgument( conversion, "conversion" );
        requireNonNullArgument( errorHandler, "errorHandler" );

        if( isFailure() ) throw errorHandler.handleError( errorCode );
        final var retValue = conversion.apply( result );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  onSuccess()
}
//  record Status

/*
 *  End of File
 */