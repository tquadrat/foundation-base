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

package org.tquadrat.foundation.scratch;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.CommonConstants.EMPTY_STRING;
import static org.tquadrat.foundation.lang.Objects.isNull;
import static org.tquadrat.foundation.lang.Objects.requireValidArgument;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.UnexpectedExceptionError;

/**
 *  Comment
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: MyClass.java 1052 2023-03-06 06:30:36Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: MyClass.java 1052 2023-03-06 06:30:36Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public class MyClass
{
        /*---------------*\
    ====** Inner Classes **====================================================
        \*---------------*/
    public static class Data{}
    public static class ProcessContext{}

    public record PersonId( String id ) implements Cloneable, Comparable<PersonId>
    {
            /*--------------*\
        ====** Constructors **===========================================
            \*--------------*/
        public PersonId { requireValidArgument( id, "id", this::validate ); }

            /*---------*\
        ====** Methods **================================================
            \*---------*/
        @Override
        public final PersonId clone()
        {
            final PersonId retValue;
            try
            {
                retValue = (PersonId) super.clone();
            }
            catch( final CloneNotSupportedException e )
            {
                throw new UnexpectedExceptionError( e );
            }

            //---* Done *----------------------------------------------------------
            return retValue;
        }   //  clone()

        @Override
        public final int compareTo( final PersonId o )
        {
            return id.compareTo( o.id );
        }   //  compareTo()

        @Override
        public final String toString() { return id; }

        private final boolean validate( final String id )
        {
            return true;
        }   //  validate()
    }
//  record PersonId

    @FunctionalInterface
    public interface DataSupplier
    {
            /*---------*\
        ====** Methods **======================================================
            \*---------*/
        /**
         *  Returns an instance of
         *  {@link Data}.
         *
         *  @return The data.
         *  @throws IOException   Something went wrong.
         */
        public Data get() throws IOException;
    }
    //  interface DataSupplier

        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/

        /*------------*\
    ====** Attributes **=======================================================
        \*------------*/
    /**
     *  The filter method.
     */
    private final BiFunction<Data,ProcessContext,Data> m_FilterMethod;

    /**
     *  The formatter method.
     */
    private final BiFunction<Data,ProcessContext,String> m_FormatterMethod;

    /**
     *  The data supplier method.
     */
    private final DataSupplier m_DataSupplier;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  Creates a new instance of {@code MyClass}.
     */
    public MyClass() { this( null, null, null ); }

    /**
     *  Creates a new instance of {@code MyClass}.
     *
     *  @param  dataSupplier    The data supplier method; can be {@code null}.
     *  @param  filter  The filter method; can be {@code null}.
     *  @param  formatter   The formatter method; can be {@code null}.
     */
    public MyClass( final DataSupplier dataSupplier, final BiFunction<Data,ProcessContext,Data> filter, final BiFunction<Data,ProcessContext,String> formatter )
    {
        m_DataSupplier = isNull( dataSupplier ) ? this::obtainInputData : dataSupplier;
        m_FilterMethod = isNull( filter ) ? this::filterData : filter;
        m_FormatterMethod = isNull( formatter ) ? this::formatData : formatter;
    }   //  MyClass()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  Filters the input data. This implementation does nothing.
     *
     *  @param  data    The raw input data.
     *  @param  context The process context.
     *  @return	The filtered data.
     */
    private final Data filterData( final Data data, final ProcessContext context )
    {
        /* Does nothing */

        //---* Done *------------------------------------------------
        return data;
    }   //  filterData()

    /**
     *  Formats the output data.
     *
     *  @param  data    The data to prepare for the output.
     *  @param  context The process context.
     *  @return	The formatted data.
     */
    private final String formatData( final Data data, final ProcessContext context )
    {
        final var retValue = EMPTY_STRING; // Do something with data

        //---* Done *------------------------------------------------
        return retValue;
    }   //  formatData()

    public final void aMethod( final String... args )
    {
        args [0] = "NewValue";
        final var c1 = args.clone();

        final var c2 = new String [args.length];
        System.arraycopy( args, 0, c2, 0, args.length );

        final var c3 = Arrays.copyOf( args, args.length );

        final var c4 = Arrays.stream( args ).toArray( String []::new );

        final var strings = List.of( "eins", "zwei", "drei", "vier" );
        final var result = strings.stream()
            .collect( groupingBy( s -> s, counting() ) )
            .entrySet()
            .stream()
            .max( Map.Entry.comparingByValue() )
            .map( Map.Entry::getKey );
    }   //  aMethod()

//    public final void aMethod( final String [] args )
//    {
//        args [0] = "NewValue";
//    }   //  aMethod()

    /**
     *  Obtains the input data from somewhere; the default
     *  implementation reads it from the file {@code inputFileName}.
     *
     *  @return The input data.
     *  @throws IOException An I/O error occurred while gathering the
     *      data.
     */
    private final Data obtainInputData() throws IOException
    {
        final Data retValue;
        try( final var inputFile = new FileInputStream( "inputFileName" ) )
        {
            retValue = readStream( inputFile );
        }

        //---* Done *------------------------------------------------
        return retValue;
    }   //  obtainInputData()

    public final String process() throws IOException
    {
        //---* Initialise the process context *----------------------
        final var context = new ProcessContext();

        //---* Gather the input data *-------------------------------
        final var inputData = m_DataSupplier.get();

        //---* Validate and filter the data *------------------------
        validateData( inputData, context );
        final var filteredData = m_FilterMethod.apply( inputData, context );
        if( !inputData.equals( filteredData ) ) validateData( filteredData, context );

        //---* Process the filtered data to the result data *--------
        final var resultData = filteredData; // Do something with filteredData

        //---* Format the data *-------------------------------------
        final var retValue = m_FormatterMethod.apply( resultData, context );

        //---* Cleanup *---------------------------------------------
        // Do whatever is necessary …

        //---* Done *------------------------------------------------
        /*
         * Let the caller write the data to wherever it should end up.
         */
        return retValue;
    }   //  process()

    /**
     *  Read the input stream and returns a
     *  {@link Data}
     *  instance.
     *
     *  @param  inputStream The input stream.
     *  @return The data.
     */
    private final Data readStream( final InputStream inputStream )
    {
        final var retValue = new Data();

        //---* Done *----------------------------------------------------------
        return retValue;
    }   //  readStream()

    /**
     *  Validates the input data.
     *
     *  @param  data    The input data.
     *  @param  context The process context.
     *  @throws IllegalArgumentException  The input data is invalid.
     */
    private final void validateData( final Data data, final ProcessContext context )
    {
        if( /* data is not valid */isNull( data ) ) throw new IllegalArgumentException();
    }   //  validateData()
}
//  class MyClass

/*
 *  End of File
 */