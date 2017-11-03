package com.rmane.xenon.practice;

import com.vmware.xenon.common.ServiceHost;
import com.vmware.xenon.services.common.RootNamespaceService;

import java.nio.file.Paths;

public class DemoHost extends ServiceHost
{
    public static void main(String[] args) throws Throwable{
        ServiceHost.Arguments defaultArgs = new ServiceHost.Arguments();
        defaultArgs.sandbox = Paths.get("/Users/rmane/xenon");
        DemoHost host = new DemoHost();
        Runtime.getRuntime().addShutdownHook(new Thread(()->host.stop()));
        host.initialize(args, defaultArgs);
        host.start();
    }

    @Override
    public ServiceHost start() throws Throwable {
        super.start();
        startDefaultCoreServicesSynchronously();

        //start enumeration service
        this.startService(new RootNamespaceService());

        //start the employee factory service
        this.startService(new EmployeeFactoryService());
//        this.startFactory(new EmployeeService());

        return this;
    }
}
