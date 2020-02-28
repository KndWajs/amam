package com.aws.codestar.projecttemplates;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Tth implements Runnable{
private Integer someInt = null;


    @Override
    public void run()
    {
        someInt = 5;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        someInt = 10;
    }
}
