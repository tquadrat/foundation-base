/*
 * ============================================================================
 * Copyright © 2014 by Dominic Fox.
 * All Rights Reserved.
 * ============================================================================
 * The MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package org.tquadrat.foundation.stream;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.function.Function;

import org.apiguardian.api.API;
import org.tquadrat.foundation.annotation.ClassVersion;

/**
 *  A selector function takes an array of values and returns the respective
 *  array index for the selected value. See
 *  {@link Selectors}
 *  for some implementations of this interface.
 *
 *  @author Dominic Fox
 *  @modified Thomas Thrien - thomas.thrien@tquadrat.org
 *  @version $Id: Selector.java 995 2022-01-23 01:09:35Z tquadrat $
 *  @since 0.0.7
 *
 *  @param  <T> The type of the values to select from.
 *
 *  @UMLGraph.link
 */
@FunctionalInterface
@ClassVersion( sourceVersion = "$Id: Selector.java 995 2022-01-23 01:09:35Z tquadrat $" )
@API( status = STABLE, since = "0.0.7" )
public interface Selector<T> extends Function<T [],Integer>
{
        /*---------*\
    ====** Methods **==========================================================
        \*---------*/
    /**
     *  {@inheritDoc}
     */
    @SuppressWarnings( "AbstractMethodOverridesAbstractMethod" )
    @Override
    Integer apply( T [] t );
}
//  interface Selector

/*
 *  End of File
 */