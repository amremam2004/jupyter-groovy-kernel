package org.lappsgrid.jupyter.groovy.output;

import org.lappsgrid.jupyter.groovy.msg.Message

interface OutPublisher{
    void publish(Message message);

    // Most things go to the shell socket so that is the default.
    void send(Message message);
    
    void setLastMessage(Message message);
    Message getLastMessage();
    
}