/*
 * ============================================================================
 * Copyright © 2002-2024 by Thomas Thrien.
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

package org.tquadrat.foundation.lang.helper;

import org.tquadrat.foundation.annotation.ClassVersion;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Boolean.TRUE;
import static java.util.Collections.*;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;

/**
 *  The value providers for the unit tests in the base module.
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ValueProviders.java 1119 2024-03-16 09:03:57Z tquadrat $
 */
@SuppressWarnings( {"UtilityClassWithoutPrivateConstructor", "UtilityClass", "UtilityClassCanBeEnum", "unused"} )
@ClassVersion( sourceVersion = "$Id: ValueProviders.java 1119 2024-03-16 09:03:57Z tquadrat $" )
public final class ValueProviders
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  An empty array of {@code boolean}s.
     */
    public static final boolean [] EMPTY_boolean_ARRAY =  new boolean [0];

    /**
     *  An empty array of {@code byte}s.
     */
    public static final byte [] EMPTY_byte_ARRAY = new byte [0];

    /**
     *  An empty array of {@code char}s.
     */
    public static final char [] EMPTY_char_ARRAY = new char [0];

    /**
     *  An empty array of {@code double}s.
     */
    public static final double [] EMPTY_double_ARRAY = new double [0];

    /**
     *  An empty array of {@code float}s.
     */
    public static final float [] EMPTY_float_ARRAY = new float [0];

    /**
     *  An empty array of integer values.
     */
    public static final int [] EMPTY_int_ARRAY = new int [0];

    /**
     *  An empty array of {@code long} elements.
     */
    public static final long [] EMPTY_long_ARRAY = new long [0];

    /**
     *  An empty array of {@code short}.
     */
    public static final short [] EMPTY_short_ARRAY = new short [0];

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  The argument provider for the methods that test on empty; provides
     *  empty arguments.
     *
     *  @return A stream of values that are not {@code null} and not empty, but
     *      blank.
     */
    static final Stream<CharSequence> blankTestArgumentProvider()
    {
        final var builder = Stream.<CharSequence>builder();
        final var value = IntStream.rangeClosed( 0, Character.MAX_CODE_POINT )
            .filter( Character::isValidCodePoint )
            .filter( Character::isWhitespace )
            .mapToObj( Character::toString )
            .collect( Collectors.joining() );
        builder.add( value );
        builder.add( new StringBuilder( value ) );
        builder.add( new StringBuffer( value ) );

        IntStream.rangeClosed( 0, Character.MAX_CODE_POINT )
            .filter( Character::isValidCodePoint )
            .filter( Character::isWhitespace )
            .mapToObj( Character::toString )
            .forEach( s ->
            {
                builder.add( s );
                builder.add( new StringBuilder( s ) );
                builder.add( new StringBuffer( s ) );
            });
        final var retValue = builder.build();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  blankTestArgumentProvider()

    /**
     *  The argument provider for the methods that test on empty; provides
     *  empty arguments.
     *
     *  @return A stream of values that are not {@code null}, but empty.
     *
     *  @see org.tquadrat.foundation.lang.objects.TestRequireNotEmptyArgument#testRequireNotEmptyArgument1WithEmptyArgument(Object)
     */
    static final Stream<Object> emptyTestArgumentProvider()
    {
        /*
         *  WARNING! With this test, String arrays do not work!!
         */
        return Stream.of
            (
                EMPTY_STRING,
                new StringBuilder(),
                new StringBuffer(),
                EMPTY_boolean_ARRAY,
                emptyList(),
                emptyMap(),
                emptySet(),
                emptyEnumeration(),
                Optional.empty()
            );
    }   //  emptyTestArgumentProvider()

    /**
     *  The argument provider for the index test methods in
     *  {@link org.tquadrat.foundation.lang.Objects}.
     *
     *  @return A stream of values that can be used as arguments for the tests.
     */
    public static final Stream<IntTupel3> indexArgumentProvider()
    {
        return Stream.of
        (
            /*
             *  The first entry is the index to test, the second is the size or
             *  the to index, while the last entry is the length.
             *  Add additional value sets if you want.
             */
            new IntTupel3(   0,   0,    0 ),
            new IntTupel3(   1,   2,    3 ),
            new IntTupel3(   2,   1,    3 ),
            new IntTupel3(   4,  77,  999 ),
            new IntTupel3( -10,  60,  450 ),
            new IntTupel3(   0,  -1,   10 ),
            new IntTupel3( 340, 230, 4350 ),
            new IntTupel3(   0,  30,  -10 )
        );
    }   //  indexArgumentProvider()

    /**
     *  The argument provider for the methods that test on blank; provides
     *  not blank, not empty arguments.
     *
     *  @return A stream of values that are not {@code null}, not empty and not blank.
     */
    static final Stream<CharSequence> notBlankTestArgumentProvider()
    {
        /*
         *  WARNING! With this test, Object arrays do not work!!
         */
        return Stream.of
        (
            NULL_STRING,
            "String",
            new StringBuilder().append( TRUE ),
            new StringBuilder().append( NULL_STRING ),
            new StringBuffer().append( TRUE ),
            new StringBuffer().append( NULL_STRING )
        );
    }   //  notBlankTestArgumentProvider()

    /**
     *  The argument provider for the methods that test on empty; provides
     *  not empty arguments.
     *
     *  @return A stream of values that are not {@code null}, and not empty.
     *
     *  @see org.tquadrat.foundation.lang.objects.TestRequireNotEmptyArgument#testRequireNotEmptyArgument1(Object)
     *  @see org.tquadrat.foundation.lang.objects.TestRequireNotEmptyArgument#testRequireNotEmptyArgument2(Object)
     */
    static final Stream<Object> notEmptyTestArgumentProvider()
    {
        /*
         *  WARNING! With this test, Object arrays do not work!!
         */
        return Stream.of
            (
                NULL_STRING,
                "String",
                " ",
                new StringBuilder().append( TRUE ),
                new StringBuilder().append( NULL_STRING ),
                new StringBuffer().append( TRUE ),
                new StringBuffer().append( NULL_STRING ),
                new boolean [] {true, false},
                List.of( NULL_STRING, EMPTY_STRING ),
                Set.of( EMPTY_STRING, NULL_STRING ),
                Map.of( "key", "value" ),
                enumeration( List.of( NULL_STRING, EMPTY_STRING ) ),
                Optional.of( EMPTY_STRING ),
                Optional.of( NULL_STRING )
            );
    }   //  notEmptyTestArgumentProvider()

    /**
     *  The argument provider for the {@code null} test methods.
     *
     *  @return A stream of values that are not {@code null}.
     *
     *  @see org.tquadrat.foundation.lang.objects.TestHashCode#testHashCode(Object)
     *  @see org.tquadrat.foundation.lang.objects.TestToString#testToStringObject(Object)
     */
    static final Stream<Object> nullTestArgumentProvider()
    {
        /*
         *  WARNING! With this test, Object arrays do not work!!
         */
        return Stream.of
        (
            EMPTY_STRING, // The empty String is not null …
            NULL_STRING, // … and this is also not null
            new Object(),
            Instant.now(),
            ((Supplier<Object>) () -> null), // Although the lambda will return
            // null, it *is* not null
            EMPTY_boolean_ARRAY,
            EMPTY_byte_ARRAY,
            EMPTY_char_ARRAY,
            EMPTY_double_ARRAY,
            EMPTY_float_ARRAY,
            EMPTY_int_ARRAY,
            EMPTY_long_ARRAY,
            EMPTY_short_ARRAY
        );
    }   //  nullTestArgumentProvider()
}
//  class ValueProviders

/*
 *  End of File
 */