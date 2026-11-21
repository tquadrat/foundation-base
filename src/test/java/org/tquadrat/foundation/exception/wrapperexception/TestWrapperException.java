/*
 * ============================================================================
 * Copyright © 2002-2026 by Thomas Thrien.
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

package org.tquadrat.foundation.exception.wrapperexception;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tquadrat.foundation.annotation.ClassVersion;
import org.tquadrat.foundation.exception.NullArgumentException;
import org.tquadrat.foundation.exception.WrapperException;
import org.tquadrat.foundation.testutil.TestBaseClass;

/**
 *  <p>{@summary Some tests for
 *  {@link WrapperException}.}</p>
 *
 *  @author Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: TestWrapperException.java 1299 2026-11-21 11:18:41Z tquadrat $
 */
@SuppressWarnings( "NonExceptionNameEndsWithException" )
@ClassVersion( sourceVersion = "$Id: TestWrapperException.java 1299 2026-11-21 11:18:41Z tquadrat $" )
@DisplayName( "org.tquadrat.foundation.exception.wrapperexception.TestWrapperException" )
public class TestWrapperException extends TestBaseClass
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  <p>{@summary Some tests for
     *  {@link WrapperException}.}</p>
     *  <p>The test method uses the implementation
     *  {@link WrapperTestException}
     *  for the tests.</p>
     *
     *  @throws Exception   Something went wrong unexpectedly.
     */
    @SuppressWarnings( "ThrowableNotThrown" )
    @Test
    final void testWrapperException() throws Exception
    {
        skipThreadTest();

        assertThrows( NullArgumentException.class, () -> new WrapperTestException( null ) );
        assertThrows( NullArgumentException.class, () -> new WrapperTestException( "message", null ) );

        WrapperException candidate;
        String message;
        Throwable cause;

        cause = new Exception();
        candidate = new WrapperTestException( cause );
        assertNotNull( candidate );
        message = candidate.getMessage();
        assertNotNull( message );
        assertEquals( cause.toString(), message );

        cause = new Exception( "message" );
        candidate = new WrapperTestException( cause );
        assertNotNull( candidate );
        message = candidate.getMessage();
        assertNotNull( message );
        assertEquals( cause.toString(), message );

        cause = new Exception( "message", new Error() );
        candidate = new WrapperTestException( cause );
        assertNotNull( candidate );
        message = candidate.getMessage();
        assertNotNull( message );
        assertEquals( cause.toString(), message );

        candidate = new WrapperTestException( "message", cause );
        assertNotNull( candidate );
        message = candidate.getMessage();
        assertNotNull( message );
        assertEquals( "message", message );

        candidate.addInfo( "key", "info" );
        message = candidate.getMessage();
        assertNotNull( message );
        assertEquals( """
            message
            key: [info]\
            """, message );

        candidate.addInfo( "key", "more info" );
        message = candidate.getMessage();
        assertNotNull( message );
        assertEquals( """
            message
            key: [more info], [info]\
            """, message );

        candidate.addInfo( "key1", "info1" );
        message = candidate.getMessage();
        assertNotNull( message );
        assertEquals( """
            message
            key: [more info], [info]
            key1: [info1]\
            """, message );

        var wrapper = new WrapperTestException( candidate );
        assertNotNull( wrapper );
        assertNotNull( wrapper.getCause() );
        assertInstanceOf( Exception.class, wrapper.getCause() );
        message = wrapper.getMessage();
        assertNotNull( message );
        assertEquals( """
            message
            key: [more info], [info]
            key1: [info1]\
            """, message );

        wrapper = new WrapperTestException( "wrapper message", candidate );
        assertNotNull( wrapper );
        assertNotNull( wrapper.getCause() );
        assertInstanceOf( Exception.class, wrapper.getCause() );
        message = wrapper.getMessage();
        assertNotNull( message );
        assertEquals( """
            wrapper message
            Additional Message: [org.tquadrat.foundation.exception.wrapperexception.WrapperTestException: message]
            key: [more info], [info]
            key1: [info1]\
            """, message );
        wrapper.printStackTrace( out );
    }   //  testWrapperException()
}
//  class TestWrapperException

/*
 *  End of File
 */