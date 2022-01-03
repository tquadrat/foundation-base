/*
 * ============================================================================
 * Copyright © 2002-2020 by Thomas Thrien.
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

package org.tquadrat.foundation.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.tquadrat.foundation.lang.CommonConstants.NULL_STRING;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.function.tce.TCEBiConsumer;
import org.tquadrat.foundation.function.tce.TCEBinaryOperator;
import org.tquadrat.foundation.function.tce.TCEConsumer;
import org.tquadrat.foundation.function.tce.TCEFunction;
import org.tquadrat.foundation.function.tce.TCEPredicate;
import org.tquadrat.foundation.function.tce.TCESupplier;
import org.tquadrat.foundation.function.tce.TCETriConsumer;
import org.tquadrat.foundation.function.tce.TCETriFunction;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  Tests for the class
 *  {@link Functions}.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestFunctions.java 884 2021-03-22 18:02:51Z tquadrat $
 */
@SuppressWarnings( {"OverlyCoupledClass","MisorderedAssertEqualsArguments"} )
@ClassVersion( sourceVersion = "$Id: TestFunctions.java 884 2021-03-22 18:02:51Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.function.TestFunctions" )
public class TestFunctions extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Uses the functional interfaces in the package
     *  {@link org.tquadrat.foundation.function}
     *  mainly to gain the required coverage ratio.
     */
    @Test
    final void cover()
    {
        skipThreadTest();

        final Getter<String> getter = () -> NULL_STRING;
        assertEquals( NULL_STRING, getter.get() );
        @SuppressWarnings( "unused" )
        final var getterArray = Getter.EMPTY_Getter_ARRAY;

        final NumberSupplier supplier = () -> Integer.MAX_VALUE;
        assertEquals( Integer.MAX_VALUE, supplier.get() );
        assertEquals( Integer.MAX_VALUE, supplier.getAsNumber() );
        @SuppressWarnings( "unused" )
        final var numberSupplierArray = NumberSupplier.EMPTY_NumberSupplier_ARRAY;

        final Setter<String> setter = s -> {/* Does nothing */};
        setter.set( NULL_STRING );
        @SuppressWarnings( "unused" )
        final var setterArray = Setter.EMPTY_Setter_ARRAY;

        final TriConsumer<Integer,Integer,Integer> consumer = ( v1, v2, v3) -> {/* Does nothing */};
        consumer.accept( 1, 2, 3 );
        consumer.andThen( (v1, v2, v3) -> {/* Does nothing */} ).accept( 1, 2, 3 );
        @SuppressWarnings( "unused" )
        final var triConsumerArray = TriConsumer.EMPTY_TriConsumer_ARRAY;

        final TriFunction<Integer,Integer,Integer,Integer> function = ( v1, v2, v3) -> v1 + v2 + v3;
        assertEquals( 6, function.apply( 1, 2, 3 ).intValue() );
        @SuppressWarnings( "unused" )
        final var triFunctionArray = TriFunction.EMPTY_TriFunction_ARRAY;

        @SuppressWarnings( "unused" )
        final var tceBiConsumerArray = TCEBiConsumer.EMPTY_TCEBiConsumer_ARRAY;
        @SuppressWarnings( "unused" )
        final var tceBinaryOperatorArray = TCEBinaryOperator.EMPTY_TCEBinaryOperator_ARRAY;
        @SuppressWarnings( "unused" )
        final var tceConsumerArray = TCEConsumer.EMPTY_TCEConsumer_ARRAY;
        @SuppressWarnings( "unused" )
        final var tceFunctionArray = TCEFunction.EMPTY_TCEFunction_ARRAY;
        @SuppressWarnings( "unused" )
        final var tcePredicateArray = TCEPredicate.EMPTY_TCEPredicate_ARRAY;
        @SuppressWarnings( "unused" )
        final var tceSupplierArray = TCESupplier.EMPTY_TCESupplier_ARRAY;
        @SuppressWarnings( "unused" )
        final var tceTriConsumerArray = TCETriConsumer.EMPTY_TCETriConsumer_ARRAY;
        @SuppressWarnings( "unused" )
        final var tceTriFunctionArray = TCETriFunction.EMPTY_TCETriFunction_ARRAY;
    }   //  cover()

    /**
     *  Validates whether the class is static.
     */
    @Test
    final void validateClass()
    {
        assertTrue( validateAsStaticClass( Functions.class ) );
    }   //  validateClass()
}
//  class TestFunctions

/*
 *  End of File
 */