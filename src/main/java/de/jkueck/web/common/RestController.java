package de.jkueck.web.common;

import de.jkueck.NewOperationInfo;
import de.jkueck.domain.NewOperationInfoEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("application")
public class RestController {


    @Inject
    private Event<NewOperationInfoEvent> events;

    @POST
    @Path("info")
    @Consumes(MediaType.APPLICATION_JSON)
    public void informNewOperation(NewOperationInfo newOperationInfo) {
        this.events.fire(NewOperationInfoEvent.builder().newOperationInfo(newOperationInfo).build());
    }

}
