package com.lsnlabs.api;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class ApplicationMain {


    private static void serverStart(){

        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(8081);

        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        // The @ApplicationPath equivalent, api URI
        ctx.setContextPath("/api");
        server.setHandler(ctx);
        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/*"); // Your exposed resource subpath  .../api/[resource_name]


        // Point to resources package for servlet container scan your REST resources.
        serHol.setInitOrder(1);
        serHol.setInitParameter("jersey.config.server.provider.packages","com.lsnlabs.api.resources");

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            //Logger.getLogger(ApplicationMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            server.destroy();
        }


    }

    public static void main(String[] args){

        serverStart();


    }




}
