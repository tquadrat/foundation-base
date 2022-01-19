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
import static org.apiguardian.api.API.Status.DEPRECATED;
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
 *  @version $Id: DebugOutput.java 993 2022-01-19 22:26:20Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@UtilityClass
@ClassVersion( sourceVersion = "$Id: DebugOutput.java 993 2022-01-19 22:26:20Z tquadrat $" )
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
     *
     *  @deprecated Use
     *      {@link #ifDebug(Function, Object...)}
     *      instead.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.1.0" )
    public static final void debugOutput( final Function<Object [],String> supplier, final Object... args ) { ifDebug( supplier, args ); }

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
     *
     *  @deprecated Use
     *      {@link #ifDebug(BooleanSupplier, Function, Object...)}
     *      instead.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.1.0" )
    public static final void debugOutput( final BooleanSupplier condition, final Function<Object [],String> supplier, final Object... args ) { ifDebug( condition, supplier, args ); }

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
     *
     *  @deprecated Use
     *      {@link #ifDebug(boolean, Function, Object...)}
     *      instead.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.1.0" )
    public static final void debugOutput( final boolean condition, final Function<Object [],String> supplier, final Object... args ) { ifDebug( condition, supplier, args ); }

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
     *
     *  @deprecated Use
     *      {@link #ifDebug(Throwable)}
     *      instead.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.1.0" )
    public static final void debugOutput( final Throwable e ) { ifDebug( e ); }

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
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifDebug( final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() ) out.printf( "DEBUG: %s%n", message );
        }
    }   //  ifDebug()

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
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifDebug( final BooleanSupplier condition, final Function<Object [],String> supplier, final Object... args )
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
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifDebug( final boolean condition, final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug && condition )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() ) out.printf( "DEBUG: %s%n", message );
        }
    }   //  ifDebug()

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
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifDebug( final Throwable e )
    {
        if( m_IsDebug && nonNull( e ) )
        {
            out.print( "DEBUG: " );
            e.printStackTrace( out );
        }
    }   //  ifDebug()

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
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifTest( final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsTest )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() ) out.printf( "TEST_: %s%n", message );
        }
    }   //  ifTest()

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
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifTest( final BooleanSupplier condition, final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug && requireNonNullArgument( condition, "condition" ).getAsBoolean() )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() ) out.printf( "TEST_: %s%n", message );
        }
    }   //  ifTest()

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
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifTest( final boolean condition, final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug && condition )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() ) out.printf( "TEST_: %s%n", message );
        }
    }   //  ifTest()

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
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifTest( final Throwable e )
    {
        if( m_IsDebug && nonNull( e ) )
        {
            out.print( "TEST_: " );
            e.printStackTrace( out );
        }
    }   //  ifTest()

    /**
     *  Returns the DEBUG flag.
     *
     *  @return {@code true} if the DEBUG flag is set, {@code false} otherwise.
     *
     *  @see CommonConstants#PROPERTY_IS_DEBUG
     */
    public static final boolean isDebug() { return m_IsDebug; }

    /**
     *  Returns the TEST flag.
     *
     *  @return {@code true} if the TEST flag is set, {@code false} otherwise.
     *
     *  @see CommonConstants#PROPERTY_IS_TEST
     */
    public static final boolean isTest() { return m_IsTest; }

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
     *
     *  @deprecated Use
     *      {@link #ifTest(Function, Object...)}
     *      instead.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.1.0" )
    public static final void testOutput( final Function<Object [],String> supplier, final Object... args ) { ifTest( supplier, args ); }

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
     *
     *  @deprecated Use
     *      {@link #ifTest(BooleanSupplier, Function, Object...)}  }
     *      instead.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.1.0" )
    public static final void testOutput( final BooleanSupplier condition, final Function<Object [],String> supplier, final Object... args ) { ifTest( condition, supplier, args ); }

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
     *
     *  @deprecated Use
     *      {@link #ifTest(boolean, Function, Object...)}
     *      instead.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.1.0" )
    public static final void testOutput( final boolean condition, final Function<Object [],String> supplier, final Object... args ) { ifTest( condition, supplier, args ); }

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
     *
     *  @deprecated Use
     *      {@link #ifTest(Throwable)}
     *      instead.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.1.0" )
    public static final void testOutput( final Throwable e ) { ifTest( e ); }
}
//  class DebugOutput

/*
 *  End of File
 */