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

package org.tquadrat.foundation.scratch;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.scratch.Status.ResultCode.SUCCESS;

import java.util.Optional;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.UnsupportedEnumError;

/**
 *  Comment
 *
 *  @param  resultCode  The result code.
 *  @param  data    The result data.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Status.java 1084 2024-01-03 15:31:20Z tquadrat $
 *  @since 0.1.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: Status.java 1084 2024-01-03 15:31:20Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public record Status( ResultCode resultCode, Optional<Data> data )
{
        /*---------------*\
    ====** Inner Classes **====================================================
        \*---------------*/
    /** */
    public enum ResultCode
    {
        SUCCESS, NO_DATA, FAILURE, TIMEOUT, INCOMPLETE
    }
    //  enum ResultCode

    @SuppressWarnings( {"EmptyClass", "NewClassNamingConvention"} )
    public static class Data {}

        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    public Status( final ResultCode rc, final Data dataArg )
    {
        this( rc, Optional.ofNullable( dataArg ) );
    }   //  Status()

    public Status( final ResultCode resultCode, @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" ) final Optional<Data> data )
    {
        this.resultCode = requireNonNullArgument( resultCode, "resultCode" );
        this.data = requireNonNullArgument( data, "data" );
    }   //  Status()

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    public static final void main( final String... args )
    {
        var proceed = true;
        while( proceed )
        {
            final var status = new Status( SUCCESS, new Data() );
            //noinspection SwitchStatementWithTooFewBranches
            switch( status.resultCode() )
            {
                case NO_DATA -> proceed = false;
                default -> throw new UnsupportedEnumError( status.resultCode() );
            }
        }
    }   //  main()
}
//  record Status

/*
 *  End of File
 */