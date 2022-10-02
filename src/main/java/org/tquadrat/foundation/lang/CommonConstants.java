/*
 * ============================================================================
 * Copyright © 2002-2022 by Thomas Thrien.
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

package org.tquadrat.foundation.lang;

import static java.lang.System.getProperty;
import static org.apiguardian.api.API.Status.DEPRECATED;
import static org.apiguardian.api.API.Status.STABLE;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.TimeZone;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.UtilityClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;

/**
 *  <p>{@summary This class provides a bunch of commonly used constants.}</p>
 *  <p>The constants are arranged into several categories.</p>
 *  <ul>
 *      <li>Physical constants like gravity and speed of light.</li>
 *      <li>XML constants, like often used names for entities and
 *      attributes.</li>
 *      <li>Miscellaneous constants like the empty string or the null
 *      char.</li>
 *  </ul>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: CommonConstants.java 1031 2022-04-07 22:43:02Z tquadrat $
 *  @since 0.0.5
 *
 *  @UMLGraph.link
 */
@SuppressWarnings( {"ClassWithTooManyFields", "UnnecessaryUnicodeEscape"} )
@ClassVersion( sourceVersion = "$Id: CommonConstants.java 1031 2022-04-07 22:43:02Z tquadrat $" )
@UtilityClass
public final class CommonConstants
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    //---* Some character constants *------------------------------------------
    /**
     *  The horizontal ellipsis: &hellip;
     *  (&amp;hellip;/&amp;#8230;/&amp;#x2026;/\u2026).
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final char CHAR_ELLIPSIS = '\u2026';

    /**
     *  The hyphen character (&amp;#8208;/&amp;#x2010;/\u2010/HYPHEN).<br>
     *  <br>This is different from the character '&#x002D;' (HYPHEN-MINUS),
     *  although it looks similar. This character can be used as a replacement
     *  for the HYPHEN-MINUS in contexts where HYPHEN-MINUS has a special
     *  meaning.
     */
    /*
     * For some reason, JavaDoc refuses to accept both &#8208; and &#x2010;
     * as valid entities.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final char CHAR_HYPHEN = '\u2010';

    /**
     *  The non-breaking space character (&amp;#x00A0;/\u00A0).
     */
    @SuppressWarnings( "SpellCheckingInspection" )
    @API( status = STABLE, since = "0.0.5" )
    public static final char CHAR_NBSP = '\u00A0';

    /**
     *  The  small non-breaking space character (&amp;#x202F;/\u202F).
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final char CHAR_NNBSP = '\u202F';

    /**
     *  The zero-width non-breaking space character; in fact, the 'word joiner'
     *  character that should be used instead of the original character.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final char CHAR_ZWNBSP = '\u2060';

    //---* Empty Array Constants *---------------------------------------------
    /**
     *  An empty array of {@code boolean}s.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final boolean [] EMPTY_boolean_ARRAY =  new boolean [0];

    /**
     *  An empty array of
     *  {@link Boolean}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Boolean [] EMPTY_Boolean_ARRAY = new Boolean [0];

    /**
     *  An empty array of {@code byte}s.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final byte [] EMPTY_byte_ARRAY = new byte [0];

    /**
     *  An empty array of
     *  {@link Byte}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Byte [] EMPTY_Byte_ARRAY = new Byte [0];

    /**
     *  An empty array of {@code char}s.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final char [] EMPTY_char_ARRAY = new char [0];

    /**
     *  An empty array of
     *  {@link Character}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Character [] EMPTY_Character_ARRAY = new Character [0];

    /**
     *  An empty array of
     *  {@link CharSequence}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final CharSequence [] EMPTY_CharSequence_ARRAY = new CharSequence [0];

    /**
     *  An empty array of
     *  {@link Class}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Class<?> [] EMPTY_Class_ARRAY = new Class<?> [0];

    /**
     *  An empty array of
     *  {@link Comparable}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Comparable<?> [] EMPTY_Comparable_ARRAY = new Comparable<?> [0];

    /**
     *  An empty array of
     *  {@link java.util.Date}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @SuppressWarnings( "UseOfObsoleteDateTimeApi" )
    @API( status = STABLE, since = "0.0.5" )
    public static final Date [] EMPTY_Date_ARRAY = new Date [0];

    /**
     *  An empty array of {@code double}s.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final double [] EMPTY_double_ARRAY = new double [0];

    /**
     *  An empty array of
     *  {@link Double}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Double [] EMPTY_Double_ARRAY = new Double [0];

    /**
     *  An empty array of
     *  {@link Exception}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Exception [] EMPTY_Exception_ARRAY = new Exception [0];

    /**
     *  An empty array of
     *  {@link Field}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Field [] EMPTY_Field_ARRAY = new Field [0];

    /**
     *  An empty array of
     *  {@link File}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final File [] EMPTY_File_ARRAY = new File [0];

    /**
     *  An empty array of {@code float}s.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final float [] EMPTY_float_ARRAY = new float [0];

    /**
     *  An empty array of
     *  {@link Float}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Float [] EMPTY_Float_ARRAY = new Float [0];

    /**
     *  An empty array of InetAddress objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final InetAddress [] EMPTY_InetAddress_ARRAY = new InetAddress [0];

    /**
     *  An empty array of
     *  {@link Instant}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Instant [] EMPTY_Instant_ARRAY = new Instant [0];

    /**
     *  An empty array of ints.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final int [] EMPTY_int_ARRAY = new int [0];

    /**
     *  An empty array of
     *  {@link Integer}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Integer [] EMPTY_Integer_ARRAY = new Integer [0];

    /**
     *  An empty array of
     *  {@link List}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final List<?> [] EMPTY_List_ARRAY = new List<?> [0];

    /**
     *  An empty array of
     *  {@link Locale}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Locale [] EMPTY_Locale_ARRAY = new Locale [0];

    /**
     *  An empty array of {@code long} elements.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final long [] EMPTY_long_ARRAY = new long [0];

    /**
     *  An empty array of
     *  {@link Long}
     *   objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Long [] EMPTY_Long_ARRAY = new Long [0];

    /**
     *  An empty array of
     *  {@link Map}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Map<?,?> [] EMPTY_Map_ARRAY = new Map<?,?> [0];

    /**
     *  An empty array of
     *  {@link java.util.Map.Entry}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Map.Entry<?,?> [] EMPTY_MapEntry_ARRAY = new Map.Entry<?,?> [0];

    /**
     *  An empty array of
     *  {@link Method}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Method [] EMPTY_Method_ARRAY = new Method [0];

    /**
     *  An empty array of
     *  {@link Number}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Number [] EMPTY_Number_ARRAY = new Number [0];

    /**
     *  An empty array of
     *  {@link Object}
     *  objects.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Object [] EMPTY_Object_ARRAY = new Object [0];

    /**
     *  An empty array of
     *  {@link Pattern}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Pattern [] EMPTY_Pattern_ARRAY = new Pattern [0];

    /**
     *  An empty array of {@code short}.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final short [] EMPTY_short_ARRAY = new short [0];

    /**
     *  An empty array of
     *  {@link Short}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Short [] EMPTY_Short_ARRAY = new Short [0];

    /**
     *  An empty array of
     *  {@link StackTraceElement}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final StackTraceElement [] EMPTY_StackTraceElement_ARRAY = new StackTraceElement [0];

    /**
     *  An empty array of
     *  {@link String}
     *  instances.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String [] EMPTY_String_ARRAY = new String [0];

    /**
     *  An empty array of
     *  {@link Throwable}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final Throwable [] EMPTY_Throwable_ARRAY = new Throwable [0];

    /**
     *  An empty array of
     *  {@link URI}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final URI [] EMPTY_URI_ARRAY = new URI [0];

    /**
     *  An empty array of
     *  {@link URL}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final URL [] EMPTY_URL_ARRAY = new URL [0];

    /**
     *  An empty array of
     *  {@link UUID}
     *  objects.
     *
     *  @deprecated The empty arrays were created to optimize the use of the
     *      method {@code toArray(Object[])} of the collection types. This got
     *      obsolete when Java&nbsp;11 introduced the method
     *      {@code toArray(IntFunction)}; the {@code IntFunction} could be the
     *      call to {@code new []}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final UUID [] EMPTY_UUID_ARRAY = new UUID [0];

    //---* Numerical constants that are not in Math *--------------------------
    /**
     *  The Gravity constant: 6.672E-11&nbsp;(N&nbsp;*&nbsp;m^2)/(kg^2). An
     *  alternative unit for the constant is (m^3)/(kg&nbsp;*&nbsp;s^2).
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final BigDecimal GRAVITY_CONSTANT = new BigDecimal( "6.672E-11" );

    /**
     *  The speed of light in km per second = 299792.458&nbsp;km/s.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final BigDecimal SPEED_OF_LIGHT =  new BigDecimal( "299792.458" );

    /**
     *  The value for 1 G: 9.80665&nbsp;m/(s^2)
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final BigDecimal STANDARD_G =  new BigDecimal( "9.80665" );

    /**
     *  <p>{@summary The time between 1582-10-15T00:00 (the start of the
     *  Gregorian calendar) and 1970-01-01T00:00 (the beginning of the "Epoch")
     *  in seconds: {@value}.}</p>
     *  <p>This constant is used for example to determine the number of
     *  nanoseconds since the beginning of the Gregorian calendar, and that is
     *  used to create UUIDs based on time and location.</p>
     *
     *  @see java.util.UUID
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final long TIME_DELTA_BEGINGREGORIAN2BEGINEPOCH = 12219292800L;

    /**
     *  The length of a (tropical) year in days, according to SI:
     *  365.242190517&nbsp;d.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final BigDecimal TROPICAL_YEAR =  new BigDecimal( "365.242190517" );

    //---* Names of System Environment variables *-----------------------------
    /*
     * Most of these depend on the underlying operating environment, but some
     * are commonly used on all operating systems.
     */
    /**
     *  The Java installation directory: {@value}.
     *
     *  @see #PROPERTY_JAVA_HOME
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String ENV_JAVA_HOME = "JAVA_HOME";

    //---* Names of System Properties *----------------------------------------
    /**
     *  <p>{@summary The system property for the application id as used by the
     *  logging sub-system: {@value}.}</p>
     *  <p>For details on this refer to the project {@code logging}.</p>
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_APPLICATIONID = "org.tquadrat.logging.applicationId";

    /**
     *  The vested system property for the current class path: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_CLASSPATH = "java.class.path";

    /**
     *  The vested system property for the architecture of the current CPU:
     *  {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_CPUARCHITECTURE = "os.arch";

    /**
     *  The optional system property for enabling java mail session debug:
     *  {@value}.<br>
     *  <br>This flag will not be recognised automatically. Refer to the
     *  documentation of {@code javax.mail.Session}.
     *
     *  @deprecated Replaced by
     *      {@link #PROPERTY_MAIL_SESSION_DEBUG}.
     */
    @Deprecated( since = "0.1.0", forRemoval = true )
    @API( status = DEPRECATED, since = "0.0.5" )
    public static final String PROPERTY_ENABLE_JAVA_MAIL_SESSION_DEBUG = "javax.mail.Session.debug";

    /**
     *  The vested system property for the file encoding used by the JVM:
     *  {@value}.
     */
    @API( status = STABLE, since = "0.0.6" )
    public static final String PROPERTY_FILE_ENCODING = "file.encoding";

    /**
     *  <p>{@summary The system property providing the flag that indicates
     *  whether the program is running in a headless mode, meaining that is
     *  does not have any kind of a graphical user interface: {@value}.}</p>
     *  <p>Instead of checking the property directly, a call to
     *  {@code java.awt.GraphicsEnvironment#isHeadless()} will return the same
     *  result, but it would require to add the module {@code java.desktop} to
     *  the dependencies of the current module.</p>
     *  <p>JavaFX does not have a headless mode as such (although there are
     *  several solutions for headless testing of JavaFX applications), but
     *  this flag can still be used to decide whether a JavaFX can run or not:
     *  basically, AWT, Swing and JavaFX need the same resources, a keyboard, a
     *  mouse and a graphical display.</p>
     *
     *  @see <a href="https://www.oracle.com/technical-resources/articles/javase/headless.html">Using Headless Mode in the Java SE Platform</a>
     */
    @SuppressWarnings( "JavadocReference" )
    @API( status = STABLE, since = "0.1.0" )
    public static final String PROPERTY_HEADLESS = "java.awt.headless";

    /**
     *  The system property that is used to enable the debug mode: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_IS_DEBUG = "isDebug";

    /**
     *  The system property that is used to enable the test mode: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_IS_TEST = "isTest";

    /**
     *  The vested system property for the Java installation directory:
     *  {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_JAVA_HOME = "java.home";

    /**
     *  The system property for the name of the Java runtime: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_JAVA_RUNTIME = "java.runtime.name";

    /**
     *  The vested system property for the Java vendor: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_JAVA_VENDOR = "java.vendor";

    /**
     *  The vested system property for the homepage URL of the Java vendor:
     *  {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_JAVA_VENDOR_URL = "java.vendor.url";

    /**
     *  The vested system property for the Java version: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_JAVA_VERSION = "java.version";

    /**
     *  The vested system property for the name of the current JVM: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_JVM_NAME = "java.vm.name";

    /**
     *  The vested system property for the name of the vendor for the current
     *  JVM: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_JVM_VENDOR = "java.vm.vendor";

    /**
     *  The vested system property for the version of the current JVM:
     *  {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_JVM_VERSION = "java.vm.version";

    /**
     *  The vested system property for the line separator: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_LINE_SEPARATOR = "line.separator";

    /**
     *  The optional system property for the name of the class that provides
     *  the configuration the JDK logger: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_LOGGING_CONFIG_CLASS = "java.util.logging.config.class";

    /**
     *  The optional system property for the name of the configuration file for
     *  the JDK logger: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_LOGGING_CONFIG_FILE = "java.util.logging.config.file";

    /**
     *  <p>{@summary The optional system property for enabling java mail
     *  session debug: {@value}.}</p>
     *  <p>This flag will not be recognised automatically. Refer to the
     *  documentation of {@code javax.mail.Session}.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String PROPERTY_MAIL_SESSION_DEBUG = "javax.mail.Session.debug";

    /**
     *  The (vested [?]) system property for the current module path: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_MODULEPATH = "jdk.module.path";

    /**
     *  The system property for the name of the packages with URL protocol
     *  handlers: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_NET_PROTOCOL_PKGS = "java.protocol.handler.pkgs";

    /**
     *  The vested system property for the name of the current operating
     *  system: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_OSNAME = "os.name";

    /**
     *  The vested system property for the version of the current operating
     *  system: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_OSVERSION = "os.version";

    /**
     *  The system property for the location of the system preferences files on
     *  a UNIX/Linux system: {@value}.<br>
     *  <br>If not set, the location is {@code /etc/}, resulting to
     *  {@code /etc/.java/.systemPrefs}.<br>
     *  <br>If the default location is used, the respective folder has to be
     *  created by a user with {@code root} permissions; the files in there
     *  should have the rights 544, while the folder and its sub-folders should
     *  have 755.
     */
    @API( status = STABLE, since = "0.0.6" )
    public static final String PROPERTY_PREFS_ROOT_SYSTEM = "java.util.prefs.systemRoot";

    /**
     *  The system property for the location of the user preferences files on
     *  a UNIX/Linux system: {@value}.<br>
     *  <br>If not set, the location is {@code ~/}, resulting to
     *  {@code ~/.java/.userPrefs/}.
     */
    @API( status = STABLE, since = "0.0.6" )
    public static final String PROPERTY_PREFS_ROOT_USER = "java.util.prefs.userRoot";

    /**
     *  The system property for the synchronisation interval of the preferences
     *  on a UNIX/Linux system, in seconds: {@value}.<br>
     *  <br>If not set, the interval is 30 seconds. The minimum value is one
     *  second.
     */
    @API( status = STABLE, since = "0.0.6" )
    public static final String PROPERTY_PREFS_SYNC = "java.util.prefs.syncInterval";

    /**
     *  <p>{@summary The system property that is used to force the encoding for a
     *  {@link PropertyResourceBundle}:
     *  {@value}.} Valid values are &quot;{@code UTF-8}&quot; and
     *  &quot;{@code ISO-8859-1}&quot;.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String PROPERTY_RESOURCEBUNDLE_ENCODING = "java.util.PropertyResourceBundle.encoding";

    /**
     *  The system property for the class name of the SAX XML parser factory:
     *  {@value}.<br>
     */
    @API( status = STABLE, since = "0.0.7" )
    public static final String PROPERTY_SAX_PARSER_FACTORY = "javax.xml.parsers.SAXParserFactory";

    /**
     *  The system property for the name of the file with the SSL key store:
     *  {@value}.
     */
    @API( status = STABLE, since = "0.0.7" )
    public static final String PROPERTY_SSL_KEYSTORE = "javax.net.ssl.keyStore";

    /**
     *  The system property for the password for the SSL key store: {@value}.
     *
     *  @see #PROPERTY_SSL_KEYSTORE
     */
    @API( status = STABLE, since = "0.0.7" )
    public static final String PROPERTY_SSL_KEYSTORE_PASSWORD = "javax.net.ssl.keyStorePassword";

    /**
     *  The vested system property for the name of the {@code temp} folder that
     *  is used by the current user: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_TEMPFOLDER = "java.io.tmpdir";

    /**
     *  The system property for the country code setting for the current user:
     *  {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_USER_COUNTRY = "user.country";

    /**
     *  The vested system property for the name of the current working
     *  directory of the current user: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_USER_DIR = "user.dir";

    /**
     *  The vested system property for the name of the home directory of the
     *  current user: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_USER_HOME = "user.home";

    /**
     *  The system property for the language code setting the current user:
     *  {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_USER_LANG = "user.language";

    /**
     *  The vested system property for the name of the current user: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String PROPERTY_USER_NAME = "user.name";

    /**
     *  The system property for the time zone setting the current user:
     *  {@value}.
     */
    @API( status = STABLE, since = "0.0.6" )
    public static final String PROPERTY_USER_TIMEZONE = "user.timezone";

    //---* Character sets *----------------------------------------------------
    /**
     *  The reference to the ASCII character set.
     *
     *  @see StandardCharsets
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Charset ASCII = StandardCharsets.US_ASCII;

    /**
     *  The reference to the ISO-8859-1 character set.
     *
     *  @see StandardCharsets
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Charset ISO8859_1 = StandardCharsets.ISO_8859_1;

    /**
     *  The reference to the US-ASCII character set; this is the same as the
     *  {@linkplain #ASCII}
     *  character set.
     *
     *  @see StandardCharsets
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Charset US_ASCII = StandardCharsets.US_ASCII;

    /**
     *  The reference to the UTF-8 character set.
     *
     *  @see StandardCharsets
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Charset UTF8 = StandardCharsets.UTF_8;

    //---* Timezones *---------------------------------------------------------
    /**
     *  The basic time zone (the time zone for the 'Universal Time Code').
     */
    @SuppressWarnings( "UseOfObsoleteDateTimeApi" )
    @API( status = STABLE, since = "0.0.5" )
    public static final TimeZone UTC;

    /**
     *  The basic time zone (the time zone for the 'Universal Time Code').
     */
    @API( status = STABLE, since = "0.0.6" )
    public static final ZoneId ZONE_UTC = ZoneId.of( "UTC" );

    //---* Constants that are useful in conjunction with XML *-----------------
    /**
     *  The lead-in for a CDATA wrapped String: {@value}
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String CDATA_LEADIN = "<![CDATA[";

    /**
     *  The lead-out for a CDATA wrapped String: {@value}
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String CDATA_LEADOUT = "]]>";

    //---* The names of some system thread groups *----------------------------
    /**
     *  The name of the main thread group: {@value}.<br>
     *  <br>This is the group of the main thread.
     */
    @API( status = STABLE, since = "0.0.6" )
    public static final String THREADGROUP_MAIN = "main";

    /**
     *  The name of the system thread group: {@value}.<br>
     *  <br>This is the group of several system threads and the parent thread
     *  group for the main thread group.
     */
    @API( status = STABLE, since = "0.0.6" )
    public static final String THREADGROUP_SYSTEM = "system";

    //---* Often used XML Element and Attribute names *------------------------
    /**
     *  The attribute name for an XML attribute holding a category of something:
     *  {@value}.
     *
     *  @see #XMLATTRIBUTE_Type
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_Category = "category";

    /**
     *  The attribute name for an XML attribute holding a class name: {@value}.
     *
     *  @see #XMLATTRIBUTE_Type
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_Class = "class";

    /**
     *  The attribute name for an XML attribute holding a date, usually without
     *  the time information: {@value}.
     *
     *  @see #XMLATTRIBUTE_Time
     *  @see #XMLATTRIBUTE_Timestamp
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_Date = "date";

    /**
     *  The attribute name for an XML attribute holding a file name: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_File = "file";

    /**
     *  The attribute name for the XML id attribute: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_Id = "xml:id";

    /**
     *  The attribute name for the XML idref attribute: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_IdRef = "idref";

    /**
     *  <p>{@summary The attribute name for the XML language attribute:
     *  {@value}.}</p>
     *  <p>This reserved attribute takes an ISO639 language identifier as
     *  value. It indicates the language of the body of the element.</p>
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String XMLATTRIBUTE_Language = "xml:lang";

    /**
     *  The attribute name for the XML name attribute: {@value}.<br>
     *  <br>Only names that follow exact rules for their character set can be
     *  stored in XML attributes, but free names have to be stored as XML
     *  elements.
     *
     *  @see #XMLELEMENT_Name
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_Name = "name";

    /**
     *  The name for the node id XML attribute: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_NodeId = "node";

    /**
     *  The name for the PID XML attribute: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_PID = "pid";

    /**
     *  The name for the reference XML attribute: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_Reference = "reference";

    /**
     *  The name for the sequence number XML attribute: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_SequenceNumber = "sequenceNumber";

    /**
     *  The name for the status XML attribute: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_Status = "status";

    /**
     *  The attribute name for an XML attribute holding a time, usually without
     *  the date information: {@value}.
     *
     *  @see #XMLATTRIBUTE_Date
     *  @see #XMLATTRIBUTE_Timestamp
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_Time = "time";

    /**
     *  The name for an XML attribute holding a date/time: {@value}.
     *
     *  @see #XMLATTRIBUTE_Date
     *  @see #XMLATTRIBUTE_Time
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_Timestamp = "timestamp";

    /**
     *  The name for an XML attribute holding a type, in the sense of a
     *  <i>category</i> or <i>model</i>, and <i>usually</i> not in the sense
     *  this term is used in the context of programming languages: {@value}.
     *
     *  @see #XMLATTRIBUTE_Category
     *  @see #XMLATTRIBUTE_Class
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_Type = "type";

    /**
     *  The name for the user id XML attribute: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_UserId = "userId";

    /**
     *  The name for the version XML attribute: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLATTRIBUTE_Version = "version";

    /**
     *  <p>{@summary The attribute name for the XML space attribute:
     *  {@value}.}</p>
     *  <p>This reserved attribute indicates whether any whitespace inside the
     *  element is significant and should not be altered by the XML processor.
     *  The attribute can take one of two enumerated values:</p>
     *  <dl>
     *      <dt>{@code preserve}</dt>
     *          <dd>The XML application preserves all whitespace (newlines,
     *          spaces, and tabs) present within the element.</dd>
     *      <dt>{@code default}</dt>
     *          <dd>The XML processor uses its default processing rules when
     *          deciding to preserve or discard the whitespace inside the
     *          element.</dd>
     *  </dl>
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String XMLATTRIBUTE_Whitespace = "xml:space";

    /**
     *  The element name for an XML element representing a comment:
     *  {@value}.<br>
     *  <br>This is not an XML comment (they will be written as
     *  <code>&lt;!--&nbsp;&hellip;&nbsp;--&gt;</code> in the XML stream) but
     *  data that is semantically a <i>comment</i> (like a {@code description},
     *  see
     *  {@linkplain #XMLELEMENT_Description below}).
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLELEMENT_Comment = "comment";

    /**
     *  The element name for an XML element holding a date, usually without
     *  the time information: {@value}.
     *
     *  @see #XMLELEMENT_Time
     *  @see #XMLELEMENT_Timestamp
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLELEMENT_Date = "date";

    /**
     *  The element name for an XML element representing a description of some
     *  kind: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLELEMENT_Description = "description";

    /**
     *  The element name for an XML element containing a message: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLELEMENT_Message = "message";

    /**
     *  The element name for an XML element representing a name of some
     *  kind: {@value}.<br>
     *  <br>Only names that follow exact rules for their character set can be
     *  stored in XML attributes, but free names have to be stored as XML
     *  elements.
     *
     *  @see #XMLATTRIBUTE_Name
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLELEMENT_Name = "name";

    /**
     *  The element name for an XML element holding text: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLELEMENT_Text = "text";

    /**
     *  The element name for an XML element holding a time, usually without
     *  the date information: {@value}.
     *
     *  @see #XMLELEMENT_Date
     *  @see #XMLELEMENT_Timestamp
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLELEMENT_Time = "time";

    /**
     *  The name for an XML element holding a date/time: {@value}.
     *
     *  @see #XMLELEMENT_Date
     *  @see #XMLELEMENT_Time
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String XMLELEMENT_Timestamp = "timestamp";

    //---* Other useful constants *--------------------------------------------
    /**
     *  The line terminator as defined by the underlying operating system.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String CR;

    /**
     *  The decimal separator for the current locale.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final char DECIMAL_SEPARATOR;

    /**
     *  The default application id if nothing else could be used: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String DEFAULT_APPLICATION_ID = "Default Program Main";

    /**
     *  The empty string.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String EMPTY_STRING;

    /**
     *  An empty char sequence.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final CharSequence EMPTY_CHARSEQUENCE;

    /**
     *  The End-Of-File marker for streams: {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final int EOF = -1;

    /**
     *  The index value indicating that nothing was found: {@value}. See for
     *  example
     *  {@link String#indexOf(int)}.
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final int NOT_FOUND = -1;

    /**
     *  A String containing only {@code NUL} (the
     *  {@link #NULL_CHAR}).
     */
    @API( status = STABLE, since = "0.1.0" )
    public static final String NUL;

    /**
     *  The null character.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final char NULL_CHAR;

    /**
     *  A String containing the sequence &quot;null&quot;.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String NULL_STRING;

    /**
     *  The grouping separator for the current locale, used with large numbers
     *  to separate thousands.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final char THOUSANDS_SEPARATOR;

    /**
     *  A String containing the sequence {@value}.
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final String UNKNOWN_STRING = "<UNKNOWN>";

    //---* Lambdas *-----------------------------------------------------------
    /**
     *  Returns {@code true} if the provided reference is {@code null}
     *  otherwise returns {@code false}.
     *
     *  @see Objects#isNull(Object)
     *  @see Predicate
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Predicate<? extends Object> IS_NULL = Objects::isNull;

    /**
     *  Returns {@code true} if the provided reference is non-{@code null}
     *  otherwise returns {@code false}.
     *
     *  @see Objects#nonNull(Object)
     *  @see Predicate
     */
    @API( status = STABLE, since = "0.0.5" )
    public static final Predicate<? extends Object> NON_NULL = Objects::nonNull;

    //---* Initialise ... *----------------------------------------------------
    static
    {
        CR = getProperty( PROPERTY_LINE_SEPARATOR );

        final var format = (DecimalFormat) NumberFormat.getInstance();
        final var symbols = format.getDecimalFormatSymbols();
        DECIMAL_SEPARATOR = symbols.getDecimalSeparator();
        THOUSANDS_SEPARATOR = symbols.getGroupingSeparator();

        EMPTY_STRING = "";
        EMPTY_CHARSEQUENCE = EMPTY_STRING;
        NULL_CHAR = '\u0000';
        NUL = Character.toString( NULL_CHAR );

        /*
         * The cast is required because if omitted, String.valueOf( char [] ) would
         * be called. This in turn would cause an unwanted NullPointerException.
         */
        NULL_STRING = String.valueOf( (Object) null ).intern();

        UTC = TimeZone.getTimeZone( ZONE_UTC );
    }

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance of this class is allowed.
     */
    private CommonConstants() { throw new PrivateConstructorForStaticClassCalledError( CommonConstants.class ); }
}
//  class CommonConstants

/*
 *  End of File
 */