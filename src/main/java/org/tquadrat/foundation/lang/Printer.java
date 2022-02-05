/*
 * ============================================================================
 *  Copyright © 2002-2022 by Thomas Thrien.
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

import java.util.Locale;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  <p>{@summary Prints a formatted message.}</p>
 *  <p>This is a functional interface whose functional method is
 *  {@link #printf(Locale,String,Object...)}.</p>
 *
 *  @version $Id: Printer.java 1005 2022-02-03 12:40:52Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: Printer.java 1005 2022-02-03 12:40:52Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public interface Printer
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Prints the given message.
     *
     *  @param  locale  The local to use for the formatting of the message.
     *  @param  message The message; this is a format String as defined for
     *      {@link java.util.Formatter}.
     *  @param  args    The optional arguments.
     *
     *  @see    java.util.Formatter#format(Locale,String,Object...)
     *  @see    java.io.PrintWriter#printf(Locale,String,Object...)
     */
    public void printf( final Locale locale, final String message, final Object... args );

    /**
     *  Prints the given message, using the current locale.
     *
     *  @param  message The message; this is a format String as defined for
     *      {@link java.util.Formatter}.
     *  @param  args    The optional arguments.
     *
     *  @see    java.util.Formatter#format(Locale,String,Object...)
     *  @see    java.io.PrintWriter#printf(Locale,String,Object...)
     */
    public default void printf( final String message, final Object... args )
    {
        printf( Locale.getDefault(), message, args );
    }   //  printf()

    /**
     *  Prints the given object instance after converting it to a String.
     *
     *  @param  object  The object to print.
     */
    public default void print( final Object object ) { printf( "%s", Objects.toString( object ) ); }

    /**
     *  Prints the given object instance after converting it to a String,
     *  followed by a new-line.
     *
     *  @param  object  The object to print.
     */
    public default void println( final Object object ) { printf( "%s%n", Objects.toString( object ) ); }

    /**
     *  Prints a new-line.
     */
    public default void println() { printf( "%n" ); }
}
//  interface Printer

/*
 *  End of File
 */