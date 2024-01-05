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

package org.tquadrat.foundation.lang;

import static java.lang.System.err;
import static java.lang.System.out;
import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.DebugOutput.ifDebug;
import static org.tquadrat.foundation.lang.DebugOutput.ifTest;
import static org.tquadrat.foundation.lang.DebugOutput.isDebug;
import static org.tquadrat.foundation.lang.DebugOutput.isTest;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.annotation.PlaygroundClass;
import org.tquadrat.foundation.exception.PrivateConstructorForStaticClassCalledError;

/**
 *  Playing around with
 *  {@link DebugOutput}.
 *
 *  @version $Id: DebugOutputTester.java 1084 2024-01-03 15:31:20Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 */
@PlaygroundClass
@ClassVersion( sourceVersion = "$Id: DebugOutputTester.java 1084 2024-01-03 15:31:20Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public final class DebugOutputTester
{
        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     *  No instance allowed for this class.
     */
    private DebugOutputTester() { throw new PrivateConstructorForStaticClassCalledError( DebugOutputTester.class ); }

        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  The program entry point.
     *
     *  @param  args    The command line arguments.
     */
    public static final void main( final String... args )
    {
        try
        {
            out.printf( "isDebug: %b%n", isDebug() );
            out.printf( "isTest : %b%n", isTest() );

            ifDebug( _ -> "DebugOutput" );
            ifTest( _ -> "TestOutput" );

            ifDebug( () -> true, _ -> "DebugOutput" );
            ifTest( () -> true, _ -> "TestOutput" );

            ifDebug( true, "DebugOutput: %s, %s"::formatted, "value1", "value2" );
            ifTest( true, "TestOutput: %s, %s"::formatted, "value1", "value2" );
        }
        catch( final Throwable t )
        {
            t.printStackTrace( err );
        }
    }   //  main()
}
//  class DebugOutputTester

/*
 *  End of File
 */