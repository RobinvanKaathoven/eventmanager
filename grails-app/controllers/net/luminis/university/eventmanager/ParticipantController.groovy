package net.luminis.university.eventmanager

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class ParticipantController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def participantInstanceList = Participant.list(params)
        def participantInstanceTotal = Participant.count()

        if(params.contentType == "json") {
            render participantInstanceList as JSON
            return
        }

        [participantInstanceList: participantInstanceList, participantInstanceTotal: participantInstanceTotal]
    }

    def create() {
        [participantInstance: new Participant(params)]
    }

    def save() {
        def participantInstance = new Participant(params)
        if (!participantInstance.save(flush: true)) {
            render(view: "create", model: [participantInstance: participantInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'participant.label', default: 'Participant'), participantInstance.id])
        redirect(action: "show", id: participantInstance.id)
    }

    def show(Long id) {
        def participantInstance
        def foundByAttributesLabel

        participantInstance = Participant.get(id)
        foundByAttributesLabel = "id"


        if (!participantInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'participant.label', default: 'Participant'), id])
            redirect(action: "list")
            return
        }

        if(params.contentType == "json") {
            render participantInstance as JSON
            return
        }

        [participantInstance: participantInstance, foundByAttributesLabel : foundByAttributesLabel]
    }

    def edit(Long id) {
        def participantInstance = Participant.get(id)
        if (!participantInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'participant.label', default: 'Participant'), id])
            redirect(action: "list")
            return
        }

        [participantInstance: participantInstance]
    }

    def update(Long id, Long version) {
        def participantInstance = Participant.get(id)
        if (!participantInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'participant.label', default: 'Participant'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (participantInstance.version > version) {
                participantInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'participant.label', default: 'Participant')] as Object[],
                          "Another user has updated this Participant while you were editing")
                render(view: "edit", model: [participantInstance: participantInstance])
                return
            }
        }

        participantInstance.properties = params

        if (!participantInstance.save(flush: true)) {
            render(view: "edit", model: [participantInstance: participantInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'participant.label', default: 'Participant'), participantInstance.id])
        redirect(action: "show", id: participantInstance.id)
    }

    def delete(Long id) {
        def participantInstance = Participant.get(id)
        if (!participantInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'participant.label', default: 'Participant'), id])
            redirect(action: "list")
            return
        }

        try {
            participantInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'participant.label', default: 'Participant'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'participant.label', default: 'Participant'), id])
            redirect(action: "show", id: id)
        }
    }
}
