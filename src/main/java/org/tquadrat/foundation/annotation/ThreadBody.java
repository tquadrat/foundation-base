/*
 * ============================================================================
 *  Copyright © 2002-2026 by Thomas Thrien.
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

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import static org.apiguardian.api.API.Status.STABLE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;

/**
 *  <p>{@summary The marker annotation for methods that will be executed inside
 *  a dedicated thread, either as explicit implementations of
 *  {@link Runnable}
 *  or inside a
 *  {@link Runnable#run()}
 *  method.}</p>
 *  <p>This can look like this</p>
 *  <div class="source-container"><pre>
 *  public final class MyClass
 *  {
 *      &#64;ThreadBody
 *      private static final void worker() {…}
 *
 *      public static final void main( final String... args )
 *      {
 *          new Thread( MyClass::worker ).start();
 *      }   //  main()
 *  }
 *  //  class MyClass
 *  </pre></div>
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: ThreadBody.java 1259 2026-06-05 20:29:42Z tquadrat $
 *  @since 0.25.12
 */
@ClassVersion( sourceVersion = "$Id: ThreadBody.java 1259 2026-06-05 20:29:42Z tquadrat $" )
@API( status = STABLE, since = "0.25.12" )
@Documented
@Retention( SOURCE )
@Target( METHOD )
public @interface ThreadBody
{}
//  @interface ThreadBody

/*
 *  End of File
 */