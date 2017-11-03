package com.rmane.xenon.practice;

import com.vmware.xenon.common.Operation;
import com.vmware.xenon.common.ServiceDocument;
import com.vmware.xenon.common.StatefulService;
import org.apache.commons.lang3.StringUtils;

public class EmployeeService extends StatefulService
{
   public static class Employee extends ServiceDocument
   {
       public String name;
       public String managerName;
   }

    public EmployeeService()
    {
        super(Employee.class);
        toggleOption(ServiceOption.PERSISTENCE,true);
    }

    @Override
    public void handleCreate(Operation post) {
        Employee s = getBody(post);
        if(s == null)
        {
            post.fail(new IllegalArgumentException("body is null"));
            return;
        }
        if(StringUtils.isEmpty(s.name))
        {
            post.fail(new IllegalArgumentException("name is null"));
            return;
        }
        post.complete();
    }

    @Override
    public void handlePut(Operation put) {
        Employee newState = getBody(put);
        Employee currState = getState(put);

        if(newState==null)
        {
            put.fail(new IllegalArgumentException("body is null"));
            return;
        }
        if(StringUtils.isEmpty(newState.name))
        {
            put.fail(new IllegalArgumentException("name is null"));
            return;
        }
        if(!StringUtils.equals(newState.name, currState.name))
        {
            put.fail(new IllegalArgumentException("employee name cant be changed"));
            return;
        }
        setState(put,newState);
        put.complete();
    }
}
