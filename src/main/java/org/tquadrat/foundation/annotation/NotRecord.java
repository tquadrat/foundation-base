/*
 * ============================================================================
 *  Copyright © 2002-2025 by Thomas Thrien.
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

import static org.apiguardian.api.API.Status.STABLE;

import org.apiguardian.api.API;

/**
 *  Marks a class as a 'standard' class that IntelliJ suggests to be a record
 *  class.
 *
 *  @extauthor Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: NotRecord.java 1151 2025-10-01 21:32:15Z tquadrat $
 *  @since 0.25.0
 *
 *  @UMLGraph.link
 */
@ClassVersion( sourceVersion = "$Id: NotRecord.java 1151 2025-10-01 21:32:15Z tquadrat $" )
@API( status = STABLE, since = "0.25.0" )
public @interface NotRecord
{ /* This annotation is a marker annotation without a body */ }
//  @interface NotRecord

/*
 *  End of File
 */