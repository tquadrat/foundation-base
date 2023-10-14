/*
 * ============================================================================
 *  Copyright © 2002-2023 by Thomas Thrien.
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
import static java.lang.String.format;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.nio.charset.Charset.defaultCharset;
import static org.apiguardian.api.API.Status.DEPRECATED;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.CommonConstants.PROPERTY_IS_DEBUG;
import static org.tquadrat.foundation.lang.CommonConstants.PROPERTY_IS_TEST;
import static org.tquadrat.foundation.lang.Objects.nonNull;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotEmptyArgument;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;
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
 *  @version $Id: DebugOutput.java 1044 2023-02-04 09:58:19Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@UtilityClass
@ClassVersion( sourceVersion = "$Id: DebugOutput.java 1044 2023-02-04 09:58:19Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class DebugOutput
{
        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The printer.
     */
    @SuppressWarnings( "UseOfSystemOutOrSystemErr" )
    private static Printer m_Printer = out::printf;

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
     *  <p>{@summary This method will find the caller for the method that is
     *  identified by its name and class, and returns the appropriate stack
     *  trace element.}</p>
     *  <p>The return value is
     *  {@linkplain Optional#empty() empty}
     *  when the provided method is not on the stack trace.</p>
     *
     *  @param  methodName  The name of the method that we need the caller for.
     *  @param  owningClass The class for the called method.
     *  @return An instance of
     *      {@link Optional}
     *      that holds the stack trace element for the caller.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final Optional<StackTraceElement> findCaller( final String methodName, final Class<?> owningClass )
    {
        requireNotEmptyArgument( methodName, "methodName" );
        final var className = requireNonNullArgument( owningClass, "owningClass" ).getName();

        //---* Retrieve the stack *--------------------------------------------
        final var stackTraceElements = currentThread().getStackTrace();
        final var len = stackTraceElements.length;

        //---* Search the stack *----------------------------------------------
        Optional<StackTraceElement> retValue = Optional.empty();
        FindLoop: for( var i = 0; (i < len) && retValue.isEmpty(); ++i )
        {
            /*
             * This loop searches the stack until it will find the entry for
             * that matches that for the provided arguments on it. It assumes
             * then that the next entry on the stack will belong to the caller
             * for this method.
             */
            if( className.equals( stackTraceElements [i].getClassName() ) && methodName.equals( stackTraceElements [i].getMethodName() ) )
            {
                if( i + 1 < len ) retValue = Optional.of( stackTraceElements [i + 1] );
            }
        }   //  FindLoop:

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  findCaller()

    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_DEBUG}
     *  is set, call the specified
     *  {@link Printer}
     *  to write the given message.
     *
     *  @param  message The message; it is a format as defined for
     *      {@link java.util.Formatter}.
     *  @param  args    Optional argument for the {@code supplier}.
     *
     *  @see #setPrinter(Printer)
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifDebug( final String message, final Object... args )
    {
        if( m_IsDebug && nonNull( message ) && !message.isBlank() )
        {
            findCaller( "ifDebug", DebugOutput.class )
                .ifPresentOrElse( stackTraceElement -> m_Printer.printf( "DEBUG - %2$s: %1$s%n", format( message, args ), stackTraceElement.toString() ),
                    () -> m_Printer.printf( "DEBUG: %s%n", format( message, args ) ) );
        }
    }   //  ifDebug()

    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_DEBUG}
     *  is set, call the specified
     *  {@link Printer}
     *  to write the given message.
     *
     *  @param  supplier    The {@code Function} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     *
     *  @see #setPrinter(Printer)
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifDebug( final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug )
        {
            final var message = requireNonNullArgument( supplier, "supplier" )
                .apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() )
            {
                findCaller( "ifDebug", DebugOutput.class )
                    .ifPresentOrElse( stackTraceElement -> m_Printer.printf( "DEBUG - %2$s: %1$s%n", message, stackTraceElement.toString() ),
                        () -> m_Printer.printf( "DEBUG: %s%n", message ) );
            }
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
     *  and call the specified
     *  {@link Printer}
     *  to write result.
     *
     *  @param  condition   Only if {@code true}, there will be an output.
     *  @param  supplier    The {@code Supplier} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     *
     *  @see #setPrinter(Printer)
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifDebug( final BooleanSupplier condition, final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug && requireNonNullArgument( condition, "condition" ).getAsBoolean() )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() )
            {
                findCaller( "ifDebug", DebugOutput.class )
                    .ifPresentOrElse( stackTraceElement -> m_Printer.printf( "DEBUG - %2$s: %1$s%n", message, stackTraceElement.toString() ),
                        () -> m_Printer.printf( "DEBUG: %s%n", message ) );
            }
        }
    }   //  debugOutput()

    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_DEBUG}
     *  is set and the given condition resolves to {@code true}, execute the
     *  given
     *  {@link Function}
     *  and call the specified
     *  {@link Printer}
     *  to write the result.
     *
     *  @param  condition   Only if {@code true}, there will be an output.
     *  @param  supplier    The {@code Supplier} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     *
     *  @see #setPrinter(Printer)
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifDebug( final boolean condition, final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug && condition )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() )
            {
                findCaller( "ifDebug", DebugOutput.class )
                    .ifPresentOrElse( stackTraceElement -> m_Printer.printf( "DEBUG - %2$s: %1$s%n", message, stackTraceElement.toString() ),
                        () -> m_Printer.printf( "DEBUG: %s%n", message ) );
            }
        }
    }   //  ifDebug()

    /**
     *  <p>{@summary If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value CommonConstants#PROPERTY_IS_DEBUG}
     *  is set, a call to
     *  {@link Throwable#printStackTrace(PrintStream) e.printStackTrace()}
     *  is made.}</p>
     *  <p>Use this to provide a view to an otherwise ignored exception.</p>
     *
     *  @param  e   The exception.
     *
     *  @see #setPrinter(Printer)
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifDebug( final Throwable e )
    {
        if( m_IsDebug && nonNull( e ) )
        {
            m_Printer.print( "DEBUG: " );
            final var bos = new ByteArrayOutputStream();
            e.printStackTrace( new PrintStream( bos, true, defaultCharset() ) );
            m_Printer.println( bos.toString() );
        }
    }   //  ifDebug()

    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_TEST}
     *  is set, call the specified
     *  {@link Printer}
     *  to write the given message.
     *
     *  @param  message The message; it is a format as defined for
     *      {@link java.util.Formatter}.
     *  @param  args    Optional argument for the {@code supplier}.
     *
     *  @see #setPrinter(Printer)
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifTest( final String message, final Object... args )
    {
        if( m_IsTest && nonNull( message ) && !message.isBlank() )
        {
            findCaller( "ifTest", DebugOutput.class )
                .ifPresentOrElse( stackTraceElement -> m_Printer.printf( "TEST - %2$s: %1$s%n", format( message, args ), stackTraceElement ),
                    () -> m_Printer.printf( "TEST: %s%n", format( message, args ) ) );
        }
    }   //  ifDebug()

    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_TEST}
     *  is set, execute the given
     *  {@link Function}
     *  and call the specified
     *  {@link Printer}
     *  to write the result.
     *
     *  @param  supplier    The {@code Supplier} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     *
     *  @see #setPrinter(Printer)
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifTest( final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsTest )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() )
            {
                findCaller( "ifTest", DebugOutput.class )
                    .ifPresentOrElse( stackTraceElement -> m_Printer.printf( "TEST - %2$s: %1$s%n", message, stackTraceElement.toString() ),
                        () -> m_Printer.printf( "TEST: %s%n", message ) );
            }
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
     *  and call the specified
     *  {@link Printer}
     *  to write the result.
     *
     *  @param  condition   Only if {@code true}, there will be an output.
     *  @param  supplier    The {@code Supplier} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     *
     *  @see #setPrinter(Printer)
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifTest( final BooleanSupplier condition, final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug && requireNonNullArgument( condition, "condition" ).getAsBoolean() )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() )
            {
                findCaller( "ifTest", DebugOutput.class )
                    .ifPresentOrElse( stackTraceElement -> m_Printer.printf( "TEST - %2$s: %1$s%n", message, stackTraceElement.toString() ),
                        () -> m_Printer.printf( "TEST: %s%n", message ) );
            }
        }
    }   //  ifTest()

    /**
     *  If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value org.tquadrat.foundation.lang.CommonConstants#PROPERTY_IS_TEST}
     *  is set and the given condition resolves to {@code true}, execute the
     *  given
     *  {@link Supplier}
     *  and call the specified
     *  {@link Printer}
     *  to write the result.
     *
     *  @param  condition   Only if {@code true}, there will be an output.
     *  @param  supplier    The {@code Supplier} for the output.
     *  @param  args    Optional argument for the {@code supplier}.
     *
     *  @see #setPrinter(Printer)
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifTest( final boolean condition, final Function<Object [],String> supplier, final Object... args )
    {
        if( m_IsDebug && condition )
        {
            final var message = requireNonNullArgument( supplier, "supplier" ).apply( requireNonNullArgument( args, "args" ) );
            if( nonNull( message ) && !message.isBlank() )
            {
                findCaller( "ifTest", DebugOutput.class )
                    .ifPresentOrElse( stackTraceElement -> m_Printer.printf( "TEST - %2$s: %1$s%n", message, stackTraceElement.toString() ),
                        () -> m_Printer.printf( "TEST: %s%n", message ) );
            }
        }
    }   //  ifTest()

    /**
     *  <p>{@summary If the
     *  {@linkplain System#getProperty(String) System property}
     *  {@value CommonConstants#PROPERTY_IS_TEST}
     *  is set, a call to
     *  {@link Throwable#printStackTrace(PrintStream) e.printStackTrace()}
     *  is made.}</p>
     *  <p>Use this to get a view to an otherwise ignored exception.</p>
     *
     *  @param  e   The exception.
     *
     *  @see #setPrinter(Printer)
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final void ifTest( final Throwable e )
    {
        if( m_IsDebug && nonNull( e ) )
        {
            m_Printer.print( "TEST: " );
            final var bos = new ByteArrayOutputStream();
            e.printStackTrace( new PrintStream( bos, true, defaultCharset() ) );
            m_Printer.println( bos.toString() );
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
     *  <p>{@summary Assigns the
     *  {@link Printer}
     *  for the DEBUG/TEST output.}</p>
     *  <p>The default implementation writes to
     *  {@link System#out}.</p>
     *
     *  @param  printer The printer
     */
    public static final void setPrinter( final Printer printer )
    {
        m_Printer = requireNonNullArgument( printer, "printer" );
    }   //  setPrinter()

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
     *  <p>Use this to provide a view to an otherwise ignored exception.</p>
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