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

package org.tquadrat.foundation.logging;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

import java.util.logging.Level;

import static org.apiguardian.api.API.Status.STABLE;
import static org.tquadrat.foundation.lang.Objects.requireNonNullArgument;
import static org.tquadrat.foundation.lang.Objects.requireNotBlankArgument;

/**
 *  This class provides alias names for the
 *  {@linkplain Level log levels}
 *  <ul>
 *      <li>{@link Level#FINE}</li>
 *      <li>{@link Level#FINEST}</li>
 *      <li>{@link Level#SEVERE}</li>
 *  </ul>
 *  so that the log levels for the JDK logging are more aligned with those from
 *  Log4j.
 *
 *  @version $Id: LogLevel.java 1119 2024-03-16 09:03:57Z tquadrat $
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@SuppressWarnings( "ClassWithTooManyFields" )
@ClassVersion( sourceVersion = "$Id: LogLevel.java 1119 2024-03-16 09:03:57Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
public class LogLevel extends Level
{
        /*-----------*\
    ====** Constants **========================================================
        \*-----------*/
    /**
     *  {@code ALL} indicates that all messages should be logged.
     *
     *  @see Level#ALL
     */
    public static final LogLevel ALL = (LogLevel) Level.ALL;

    /**
     *  {@code CONFIG} is a message level for static configuration messages.
     *
     *  @see Level#CONFIG
     */
    public static final LogLevel CONFIG = (LogLevel) Level.CONFIG;

    /**
     *  {@code INFO} is a message level for informational messages.
     *
     *  @see Level#INFO
     */
    public static final LogLevel INFO = (LogLevel) Level.INFO;

    /**
     *  {@code OFF} is a special level that can be used to turn off logging.
     *
     * @see Level#OFF
     */
    public static final LogLevel OFF = (LogLevel) Level.OFF;

    /**
     *  {@code SEVERE} is a message level indicating a serious failure.
     *
     *  @see Level#SEVERE
     */
    public static final LogLevel SEVERE = (LogLevel) Level.SEVERE;

    /**
     *  {@code WARNING} is a message level indicating a potential problem.
     *
     *  @see Level#WARNING
     */
    public static final LogLevel WARNING = (LogLevel) Level.WARNING;

    /**
     *  {@code FINE} is a message level providing tracing information.
     *
     *  @see Level#FINE
     */
    public static final LogLevel FINE = (LogLevel) Level.FINE;

    /**
     *  {@code FINER} indicates a fairly detailed tracing message.
     *
     *  @see Level#FINER
     */
    public static final LogLevel FINER = (LogLevel) Level.FINER;

    /**
     *  {@code FINEST} indicates a highly detailed tracing message.
     *
     *  @see Level#FINEST
     */
    public static final LogLevel FINEST = (LogLevel) Level.FINEST;

        /*------------------------*\
    ====** Static Initialisations **===========================================
        \*------------------------*/
    /**
     *  {@code DEBUG} is the alias for
     *  {@link Level#FINE}.
     */
    public static final LogLevel DEBUG = new LogLevel( "DEBUG", FINE );

    /**
     *  {@code ERROR} is the alias for
     *  {@link Level#SEVERE}.
     */
    public static final LogLevel ERROR = new LogLevel( "ERROR", SEVERE );

    /**
     *  {@code TRACE} is the alias for
     *  {@link Level#FINEST}.
     */
    public static final LogLevel TRACE = new LogLevel( "TRACE", FINEST );

        /*--------------*\
    ====** Constructors **=====================================================
        \*--------------*/
    /**
     * Creates a named LogLevel with a given integer value.
     *
     *  @param  name    The name of the LogLevel.
     *  @param  value   An integer value for the LogLevel.
     */
    protected LogLevel( final String name, final int value )
    {
        super( requireNotBlankArgument( name, "name" ), value );
    }   //  LogLevel()

    /**
     * Creates a named LogLevel as an alias of the given level.
     *
     *  @param  name    The name of the LogLevel.
     *  @param  level   The log level to rename.
     */
    protected LogLevel( final String name, final Level level )
    {
        super( requireNotBlankArgument( name, "name" ), requireNonNullArgument( level, "level" ).intValue() );
    }   //  LogLevel()
}
//  class LogLevel

/*
 *  End of File
 */