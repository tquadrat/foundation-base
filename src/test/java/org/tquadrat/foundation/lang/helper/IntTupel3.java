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

package org.tquadrat.foundation.lang.helper;

import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  A wrapper for integer arrays with the length 3.
 *
 *  @param  v1  The first value.
 *  @param  v2  The second value.
 *  @param  v3  The third value.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: IntTupel3.java 820 2020-12-29 20:34:22Z tquadrat $
 */
@SuppressWarnings( {"hiding", "javadoc"} )
@ClassVersion( sourceVersion = "$Id: IntTupel3.java 820 2020-12-29 20:34:22Z tquadrat $" )
public record IntTupel3( int v1, int v2, int v3 )
{ /* Empty block */ }
//  record IntTupel3

/*
 *  End of File
 */