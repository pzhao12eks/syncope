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
package org.apache.syncope.core.persistence.jpa.entity.task;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.apache.syncope.common.lib.types.TaskType;
import org.apache.syncope.common.lib.types.TraceLevel;
import org.apache.syncope.core.persistence.api.entity.task.NotificationTask;

@Entity
@DiscriminatorValue("NotificationTask")
public class JPANotificationTask extends JPATask implements NotificationTask {

    private static final long serialVersionUID = 95731573485279180L;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "address")
    @CollectionTable(name = "NotificationTask_recipients",
            joinColumns =
            @JoinColumn(name = "notificationTask_id", referencedColumnName = "id"))
    private Set<String> recipients;

    @NotNull
    private String sender;

    @NotNull
    private String subject;

    @NotNull
    @Lob
    private String textBody;

    @NotNull
    @Lob
    private String htmlBody;

    @Basic
    @Min(0)
    @Max(1)
    private Integer executed;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TraceLevel traceLevel;

    public JPANotificationTask() {
        super();

        type = TaskType.NOTIFICATION;
        recipients = new HashSet<>();
        executed = getBooleanAsInteger(false);
    }

    @Override
    public Set<String> getRecipients() {
        return recipients;
    }

    @Override
    public boolean addRecipient(final String recipient) {
        return recipients.add(recipient);
    }

    @Override
    public boolean removeRecipient(final String recipient) {
        return recipients.remove(recipient);
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public void setSender(final String sender) {
        this.sender = sender;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public void setSubject(final String subject) {
        this.subject = subject;
    }

    @Override
    public String getTextBody() {
        return textBody;
    }

    @Override
    public void setTextBody(final String textBody) {
        this.textBody = textBody;
    }

    @Override
    public String getHtmlBody() {
        return htmlBody;
    }

    @Override
    public void setHtmlBody(final String htmlBody) {
        this.htmlBody = htmlBody;
    }

    @Override
    public boolean isExecuted() {
        return isBooleanAsInteger(executed);
    }

    @Override
    public void setExecuted(final boolean executed) {
        this.executed = getBooleanAsInteger(executed);
    }

    @Override
    public TraceLevel getTraceLevel() {
        return traceLevel;
    }

    @Override
    public void setTraceLevel(final TraceLevel traceLevel) {
        this.traceLevel = traceLevel;
    }
}