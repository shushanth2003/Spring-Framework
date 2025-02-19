package com.springframework;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class Doctor implements Staff,BeanNameAware {
    private String qualification;
   public void assist(){
       System.out.println("Doctor is assist");
   }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "qualification='" + qualification + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name Aware is setted");
    }


    public void postconstruct(){
        System.out.println("Post Construct is created");
    }
}
