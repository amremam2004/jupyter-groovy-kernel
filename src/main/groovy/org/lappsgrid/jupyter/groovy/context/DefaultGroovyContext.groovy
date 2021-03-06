/*
 * Copyright (c) 2016 The Language Application Grid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.lappsgrid.jupyter.groovy.context

import org.codehaus.groovy.control.CompilerConfiguration

//import org.lappsgrid.jupyter.groovy.context.GroovyContext

/**
 * The DefaultGroovyContext implements the GroovyContext interface and returns default
 * CompilerConfiguration and ExpandoMetaClass objects.
 *
 * Users implementing a Jupyter kernel for a custom Groovy DSL can extend the DefaultGroovyContext
 * if they only need/want to implement one of the methods.
 *
 * @author Keith Suderman
 */
class DefaultGroovyContext implements GroovyContext {
    @Override
    CompilerConfiguration getCompilerConfiguration() {
        return new CompilerConfiguration()
    }

    @Override
    MetaClass getMetaClass(Class aClass, boolean initialize) {
        MetaClass mc = new ExpandoMetaClass(aClass, false)
        if (initialize) {
            mc.initialize()
        }
        return mc
    }
}
