/*
 * ============================================================================
 *  Copyright © 2002-2020 by Thomas Thrien.
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

package org.tquadrat.foundation.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import static org.apiguardian.api.API.Status.STABLE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;

/**
 *  This is the marker annotation for a &quot;Main&quot; class. That is a class
 *  that has a method<br>
 *  <pre><code>    public static final void main( String... )</code></pre>
 *  <br>and is meant either as the starting point of a larger application or as
 *  a standalone program.<br>
 *  <br>Particular the latter will have often just static methods – like
 *  {@linkplain UtilityClass utility classes} – and therefore causes warnings
 *  in some tools. This annotation allows to suppress these warnings.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ProgramClass.java 820 2020-12-29 20:34:22Z tquadrat $
 *
 *  @UMLGraph.link
 *  @since 0.1.0
 */
@ClassVersion( sourceVersion = "$Id: ProgramClass.java 820 2020-12-29 20:34:22Z tquadrat $" )
@API( status = STABLE, since = "0.1.0" )
@Documented
@Retention( SOURCE )
@Target( TYPE )
public @interface ProgramClass
{ /* Empty */ }
//  @interface ProgramClass

/*
 *  End of File
 */