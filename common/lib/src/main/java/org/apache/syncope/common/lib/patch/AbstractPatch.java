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
package org.apache.syncope.common.lib.patch;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.apache.syncope.common.lib.AbstractBaseBean;
import org.apache.syncope.common.lib.types.PatchOperation;

@XmlType
@XmlSeeAlso({ AbstractPatchItem.class, AttrPatch.class, MembershipPatch.class, RelationshipPatch.class })
public abstract class AbstractPatch extends AbstractBaseBean {

    private static final long serialVersionUID = -4729181508529829580L;

    protected abstract static class Builder<P extends AbstractPatch, B extends Builder<P, B>> {

        protected P instance;

        protected abstract P newInstance();

        protected P getInstance() {
            if (instance == null) {
                instance = newInstance();
            }
            return instance;
        }

        @SuppressWarnings("unchecked")
        public B operation(final PatchOperation operation) {
            getInstance().setOperation(operation);
            return (B) this;
        }

        public P build() {
            if (getInstance().getOperation() == null) {
                instance.setOperation(PatchOperation.ADD_REPLACE);
            }
            return getInstance();
        }
    }

    private PatchOperation operation;

    public PatchOperation getOperation() {
        return operation;
    }

    public void setOperation(final PatchOperation operation) {
        this.operation = operation;
    }

}
