package com.pcdd.sonovel.web;
import cn.hutool.core.lang.Console;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceFactory;

import java.util.List;

public class WebServer {
    Server server;

    public void init(){
        int port = 7765;
        server = new Server(port);

        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();

        // 设置静态资源
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setBaseResource(ResourceFactory.of(resourceHandler).newResource(WebServer.class.getClassLoader().getResource("static")));
        resourceHandler.setDirAllowed(false);
        resourceHandler.setWelcomeFiles(List.of("index.html"));
        resourceHandler.setAcceptRanges(true);
        contextHandlerCollection.addHandler(resourceHandler);

        server.setHandler(contextHandlerCollection);
        Console.print("Server start on port {} \n", port);
        new Thread(()->{
            try {
                server.start();
                server.join();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
