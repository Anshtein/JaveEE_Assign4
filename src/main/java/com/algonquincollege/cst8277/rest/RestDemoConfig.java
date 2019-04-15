/********************************************************************egg***m******a**************n************
 * File: RestDemoConfig.java
 * Course materials (19W) CST 8277
 * @author Elena Soukhanov 040871451
 * @author Ksenia Lopukhina 040892102
 * @author Svetlana Netchaeva 040858724
 * @author Anna Shteyngart 040883547
 * @author Pavel Jilinski 040878295
 * @date 2019 04
 */
package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.SERVER_API_DESC;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.SERVER_URL;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.USER_ROLENAME;

//import java.util.Set;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@ApplicationPath("/api/v1")
@OpenAPIDefinition(info = @Info(
    title = "Rest'ful Demo API",
    version = "1.0.0",
    description = SERVER_API_DESC
    ),
    servers = {
        @Server(url = SERVER_URL)
    }
)
/**
 * class used to be in web.xml
 */
@DeclareRoles({USER_ROLENAME, ADMIN_ROLENAME})
public class RestDemoConfig extends Application {

    //default behaviour of 'empty' Application is to scan for all Resource classes annotated with @Path

    /*
     * if you wish to specify directly the Resource classes, you need to provide an override impl of getClasses
    @Override
    public Set<Class<?>> getClasses() {
        // TODO Auto-generated method stub
        return super.getClasses();
    }
    */
}