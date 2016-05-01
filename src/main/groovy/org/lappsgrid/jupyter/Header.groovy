package org.lappsgrid.jupyter

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.lappsgrid.serialization.Serializer

/**
 * @author Keith Suderman
 */
@JsonPropertyOrder(['id', 'username', 'session', 'date', 'type', 'version'])
class Header {
    String date
    @JsonProperty('msg_id')
    String id
    String username
    String session
    @JsonProperty('msg_type')
    String type
    String version

    public Header() { }
    public Header(Header header) {
        this.id = header.id
        this.date = header.date
        this.username = header.username
        this.session = header.session
        this.type = header.type
        this.version = '5.0'
    }

    public Header(Map header) {
        this.id = header.id
        this.date = header.date
        this.username = header.username
        this.session = header.session
        this.type = header.type
        this.version = '5.0'
    }

    public Header(String type, Message message) {
        date = GroovyKernel.timestamp()
        id = GroovyKernel.uuid()
        username = 'kernel'
        this.type = type
        this.session = message.header.session
        this.version = '5.0'
    }

    String asJson() { return Serializer.toJson(this) }
}