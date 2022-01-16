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

import static java.lang.Boolean.getBoolean;
import static java.lang.System.out;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.CommonConstants.PROPERTY_IS_DEBUG;
import static org.tquadrat.foundation.lang.CommonConstants.PROPERTY_IS_TEST;
import static org.tquadrat.foundation.lang.Objects.nonNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;

import java.io.PrintStream;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.UtilityClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;

/**
 *  <p>{@summary Some functions for DEBUG and TEST output to the console.}</p>
 *
 *  @version $Id: DebugOutput.java 992 2022-01-16 19:51:31Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@UtilityClass
@ClassVersion( sourceVersion = "$Id: DebugOutput.java 992 2022-01-16 19:51:31Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class DebugOutput
{
        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  The DEBUG flag.
     *
     *  @see org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_DEBUG
     */
    private static final boolean m_IsDebug;

    /**
     *  The TEST flag.
     *
     *  @see org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_TEST
     */
    private static final boolean m_IsTest;

    static
    {
        m_IsDebug = getBoolean( PROPERTY_IS_DEBUG );
        m_IsTest = getBoolean( PROPERTY_IS_TEST );
    }

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance is allowed for this class!
     */
    private DebugOutput() { throw new PrivateConstructorForStaticClassCalledError( DebugOutput.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_DEBUG}
     *  is set, execute the given
     *  {@link Function}
     *  and write the result to
     *  {@link System#out System.out}.
     *
     *  @param  supplier    The {@code Function} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     */
    public static final void debugOutput( final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() ) out.printf( "DEBUG: %s%n", message );
        }
    }   //  debugOutput()

    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_DEBUG}
     *  is set and the given
     *  {@link BooleanSupplier condition}
     *  resolves to {@code true}, execute the given
     *  {@link Function}
     *  and write the result to
     *  {@link System#out System.out}.
     *
     *  @param  condition   Only if {@code true}, there will be an output.
     *  @param  supplier    The {@code Supplier} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     */
    public static final void debugOutput( final BooleanSupplier condition, final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug && requireNonNullArgument( condition, "condition" ).getAsBoolean() )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() ) out.printf( "DEBUG: %s%n", message );
        }
    }   //  debugOutput()

    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_DEBUG}
     *  is set and the given condition resolves to {@code true}, execute the
     *  given
     *  {@link Function}
     *  and write the result to
     *  {@link System#out System.out}.
     *
     *  @param  condition   Only if {@code true}, there will be an output.
     *  @param  supplier    The {@code Supplier} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     */
    public static final void debugOutput( final boolean condition, final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug && condition )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() ) out.printf( "DEBUG: %s%n", message );
        }
    }   //  debugOutput()

    /**
     *  <p>{@summary If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value CommonConstants#PROPERTY_IS_DEBUG}
     *  is set, a call to
     *  {@link Throwable#printStackTrace(PrintStream) e.printStackTrace( out )}
     *  is made.}</p>
     *  <p>Use this to get a view to an otherwise ignored exception.</p>
     *
     *  @param  e   The exception.
     */
    public static final void debugOutput( final Throwable e )
    {
        if( m_IsDebug && nonNull( e ) )
        {
            out.print( "DEBUG: " );
            e.printStackTrace( out );
        }
    }   //  debugOutput()

    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_TEST}
     *  is set, execute the given
     *  {@link Function}
     *  and write the result to
     *  {@link System#out System.out}.
     *
     *  @param  supplier    The {@code Supplier} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     */
    public static final void testOutput( final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsTest )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() ) out.printf( "TEST_: %s%n", message );
        }
    }   //  testOutput()

    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_TEST}
     *  is set and the given
     *  {@link BooleanSupplier condition}
     *  resolves to {@code true}, execute the given
     *  {@link Function}
     *  and write the result to
     *  {@link System#out System.out}.
     *
     *  @param  condition   Only if {@code true}, there will be an output.
     *  @param  supplier    The {@code Supplier} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     */
    public static final void testOutput( final BooleanSupplier condition, final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug && requireNonNullArgument( condition, "condition" ).getAsBoolean() )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() ) out.printf( "TEST_: %s%n", message );
        }
    }   //  testOutput()

    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_TEST}
     *  is set and the given condition resolves to {@code true}, execute the
     *  given
     *  {@link Supplier}
     *  and write the result to
     *  {@link System#out System.out}.
     *
     *  @param  condition   Only if {@code true}, there will be an output.
     *  @param  supplier    The {@code Supplier} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     */
    public static final void testOutput( final boolean condition, final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug && condition )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() ) out.printf( "TEST_: %s%n", message );
        }
    }   //  testOutput()

    /**
     *  <p>{@summary If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value CommonConstants#PROPERTY_IS_TEST}
     *  is set, a call to
     *  {@link Throwable#printStackTrace(PrintStream) e.printStackTrace( out )}
     *  is made.}</p>
     *  <p>Use this to get a view to an otherwise ignored exception.</p>
     *
     *  @param  e   The exception.
     */
    public static final void testOutput( final Throwable e )
    {
        if( m_IsDebug && nonNull( e ) )
        {
            out.print( "TEST_: " );
            e.printStackTrace( out );
        }
    }   //  testOutput()
}
//  class DebugOutput

/*
 *  End of File
 */