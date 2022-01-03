/*
 * ============================================================================
 * Copyright © 20002-2020 by Thomas Thrien.
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

import java.util.Formatter;
import java.util.IllegalFormatException;
import java.util.Locale;
import java.util.function.Supplier;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.UtilityClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;

/**
 *  This class provides an implementation of
 *  {@link java.lang.String#format(Locale,String,Object...) String.format()}
 *  that uses a shared instance of
 *  {@link java.util.Formatter}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: SharedFormatter.java 820 2020-12-29 20:34:22Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: SharedFormatter.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = INTERNAL, since = "0.1.0" )
@UtilityClass
public final class SharedFormatter
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  The initial buffer size that is used by
     *  {@link #format(String, Object...)}
     *  and
     *  {@link #format(Locale, String, Object...)}
     *  (per thread): {@value}.
     *
     *  @since 0.1.0
     */
    @API( status = INTERNAL, since = "0.1.0" )
    public static final int FORMAT_INITIAL_BUFFERSIZE = 2048;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The cached
     *  {@link Formatter}
     *  instance.
     */
    private static final ThreadLocal<Formatter> m_Formatter;

    static
    {
        //---* The cached Formatter instance *---------------------------------
        final Supplier<Formatter> initializer = () -> new Formatter( new StringBuilder( FORMAT_INITIAL_BUFFERSIZE ), Locale.getDefault( Locale.Category.FORMAT ) );
        //noinspection RedundantTypeArguments
        m_Formatter = ThreadLocal.<Formatter>withInitial( initializer );
    }

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance of this class is allowed.
     */
    private SharedFormatter() { throw new PrivateConstructorForStaticClassCalledError( SharedFormatter.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Returns a formatted String using the specified format String and
     *  arguments.<br>
     *  <br>The implementation uses internally the
     *  {@link Locale}
     *  that is returned by
     *  {@link java.util.Locale#getDefault() Locale.getDefault()}.<br>
     *  <br>This method is meant as a replacement for the method
     *  {@link java.lang.String#format(String, Object...)};
     *  that implementation always uses a new instance of
     *  {@link Formatter}
     *  for each invocation. The implementation here uses a cached instance
     *  instead, and as {@code Formatter} is not inherently thread-safe, this
     *  cached instance is created and stored per thread, therefore no
     *  synchronisation is needed.<br>
     *  <br>This implementation is about as twice as fast as
     *  {@code java.lang.String.format()}.
     *
     *  @param  format  A format String with the syntax as described for the
     *      {@link Formatter}
     *      class.
     *  @param  args    The arguments referenced by the format specifiers in
     *      the {@code format} String. If there are more arguments than format
     *      specifiers, the extra arguments are ignored. The number of
     *      arguments is variable and may be zero. The maximum number of
     *      arguments is limited by the maximum dimension of a Java array as
     *      defined by <cite>The Java&trade; Virtual Machine
     *      Specification</cite>. The behaviour on a {@code null} argument
     *      depends on the conversion.
     *  @throws IllegalFormatException A format string contains an illegal
     *      syntax, a format specifier that is incompatible with the given
     *      arguments, insufficient arguments given the format string, or other
     *      illegal conditions occurred. For specification of all possible
     *      formatting errors, see the &quot;Details&quot; section of the
     *      {@code Formatter} class specification.
     *  @return A formatted String.
     *
     *  @see  java.util.Formatter
     *
     *  @since 0.1.0
     */
    @API( status = INTERNAL, since = "0.1.0" )
    public static final String format( final String format, final Object... args ) throws IllegalFormatException
    {
        return format( Locale.getDefault( Locale.Category.FORMAT ), format, args );
    }   //  format()

    /**
     *  Returns a formatted String using the specified
     *  {@link Locale},
     *  format String, and arguments.<br>
     *  <br>This method is meant as a replacement for the method
     *  {@link java.lang.String#format(String, Object...)};
     *  that implementation always uses a new instance of
     *  {@link Formatter}
     *  for each invocation. The implementation here uses a cached instance
     *  instead, and as {@code Formatter} is not inherently thread-safe, this
     *  cached instance is created and stored per thread, therefore no
     *  synchronisation is needed.<br>
     *  <br>The performance gain for this implementation is not that impressive
     *  when compared with that of
     *  {@link #format(String, Object...)},
     *  but still in the 10% range.
     *  {@code java.lang.String.format()}.
     *
     *  @param  locale  The
     *      {@link Locale}
     *      that will be applied during formatting. If {@code locale} is
     *      {@code null} then no localisation is applied.
     *  @param  format  A format String with the syntax as described for the
     *      {@link Formatter}
     *      class.
     *  @param  args    The arguments referenced by the format specifiers in
     *      the {@code format} String. If there are more arguments than format
     *      specifiers, the extra arguments are ignored. The number of
     *      arguments is variable and may be zero. The maximum number of
     *      arguments is limited by the maximum dimension of a Java array as
     *      defined by <cite>The Java&trade; Virtual Machine
     *      Specification</cite>. The behaviour on a {@code null} argument
     *      depends on the conversion.
     *  @throws IllegalFormatException A format string contains an illegal
     *      syntax, a format specifier that is incompatible with the given
     *      arguments, insufficient arguments given the format string, or other
     *      illegal conditions occurred. For specification of all possible
     *      formatting errors, see the &quot;Details&quot; section of the
     *      {@code Formatter} class specification.
     *  @return A formatted String.
     *
     *  @see  java.util.Formatter
     *
     *  @since 0.1.0
     */
    @SuppressWarnings( "resource" )
    @API( status = INTERNAL, since = "0.1.0" )
    public static final String format( final Locale locale, final String format, final Object... args ) throws IllegalFormatException
    {
        /*
         * When this method StringUtils.format() is used inside an
         * implementation of Formattable.formatTo() that in turn was called by
         * this method already, the internal out buffer of the formatter
         * contains already some text that has to be preserved.
         *
         * Therefore, the current length of the out buffer is kept before new
         * contents is added by the call to formatter.format(), and reset after
         * the new formatted text was retrieved from that buffer.
         */
        final var formatter = m_Formatter.get();
        final var result = (StringBuilder) formatter.out();
        final var currentPos = result.length();
        formatter.format( locale, format, args );
        final var retValue = result.substring( currentPos );

        if( (currentPos == 0) && (result.capacity() > FORMAT_INITIAL_BUFFERSIZE) )
        {
            /*
             * The intention of this code is to reduce the memory  footprint of
             * the formatter that is mainly determined by the StringBuilder
             * that is used for the output.
             */
            result.setLength( FORMAT_INITIAL_BUFFERSIZE );
            result.trimToSize();
        }
        result.setLength( currentPos );

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  format()
}
//  class SharedFormatter

/*
 *    End of File
 */