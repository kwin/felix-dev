/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.felix.sandbox.preferences;

import java.util.HashSet;
import java.util.Set;

/**
 * This class keeps track of the changes to a preferences node.
 */
public class ChangeSet {

    /** Do we have changes at all? */
    protected boolean hasChanges = false;

    /** A set of changed/added properties. */
    protected final Set changedProperties = new HashSet();

    /** A set of removed properties. */
    protected final Set removedProperties = new HashSet();

    /** A set of added children. */
    protected final Set addedChildren = new HashSet();

    /** A set of removed children. */
    protected final Set removedChildren = new HashSet();

    /**
     * Do we have changes?
     * @return
     */
    public boolean hasChanges() {
        return this.hasChanges;
    }

    /**
     * Inform that a property has been added/changed.
     * @param name The name of the property.
     */
    public void propertyChanged(String name) {
        this.hasChanges = true;
        this.removedProperties.remove(name);
        this.changedProperties.add(name);
    }

    /**
     * Inform that a property has removed.
     * @param name The name of the property.
     */
    public void propertyRemoved(String name) {
        this.hasChanges = true;
        this.changedProperties.remove(name);
        this.removedProperties.add(name);
    }

    /**
     * Inform that a child has been added.
     * @param name The name of the child.
     */
    public void childAdded(String name) {
        this.hasChanges = true;
        this.removedChildren.remove(name);
        this.addedChildren.add(name);
    }

    /**
     * Inform that a child has been removed.
     * @param name The name of the child.
     */
    public void childRemoved(String name) {
        this.hasChanges = true;
        this.addedChildren.remove(name);
        this.removedChildren.add(name);
    }

    /**
     * Reset state to unchanged.
     */
    public void clear() {
        this.hasChanges = false;
        this.removedChildren.clear();
        this.removedProperties.clear();
        this.addedChildren.clear();
        this.changedProperties.clear();
    }
}
