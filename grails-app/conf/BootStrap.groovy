import net.luminis.university.eventmanager.Entry
import net.luminis.university.eventmanager.Event
import net.luminis.university.eventmanager.Participant
import net.luminis.university.eventmanager.Role
import net.luminis.university.eventmanager.User
import net.luminis.university.eventmanager.UserRole

class BootStrap {

    def init = { servletContext ->

        //Add some events
        def grails101 = new Event(name: "Luminis University Grails 101").save()
        def grails102 = new Event(name: "Luminis University Grails 102").save()
        def koningsdag = new Event(name: "Koningsdag").save()

        //Add some participants
        def robin = new Participant(firstName: "Robin", lastName: "van Kaathoven", email: "Robin.vanKaathoven@luminis.eu").save()
        def frank = new Participant(firstName: "Frank", lastName: "Wolferink", email: "Frank.Wolferink@luminis.eu").save()

        //Add some entries
        def robinGrails101Entry = new Entry(participant: robin, event: grails101).save()
        def robinGrails102Entry = new Entry(participant: robin, event: grails102).save()
        def frankGrails101Entry = new Entry(participant: frank, event: grails101).save()

        def adminUser = new User(username: "admin", password: "admin").save()
        def adminRole = new Role(authority: "ROLE_ADMIN").save()
        UserRole.create adminUser, adminRole, true

    }
    def destroy = {
    }
}
