package net.luminis.university.eventmanager

import grails.converters.JSON

class JsonMarshallingService {

    def marshall(Participant participantInstance) {
        return [
                "id" : participantInstance.id,
                "name" : participantInstance.toString()
        ] as JSON
    }
}
