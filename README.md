# Spring Framework Overview

The Spring Framework is a comprehensive and modular framework for building enterprise-grade applications in Java. It provides a wide range of features and tools to simplify the development process, promote best practices, and enhance the maintainability and scalability of applications.

## Application Context in Spring Framework

### Overview
In the Spring Framework, the `ApplicationContext` is a central interface to provide configuration for an application. It is a container for beans (objects), and it provides the basic functionality for the Spring IoC (Inversion of Control) container.

### Example
```java
package com.springframework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
   public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
       Doctor doctor = context.getBean(Doctor.class);
       doctor.assist();
   }
}
```

## XML Configuration in Spring

### Why Use XML Configuration?
- **Declarative Setup**: Clear, declarative bean definitions.
- **Centralized Configuration**: All settings in one place.
- **Compatibility**: Works with legacy systems.
- **Flexibility**: Handles complex configurations.

### Example XML Configuration File
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="myBean" class="com.example.MyBean">
        <property name="property" value="value"/>
    </bean>

</beans>
```

## Setter and Getter Injection in Spring

### 1. Setter Injection
Setter injection is a dependency injection method where the Spring container injects dependencies via the setter methods of the bean. It is a flexible and commonly used approach.

#### Example
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd">
   <bean id="doctor" class="com.springframework.Doctor">
       <property name="qualification" value="MBBS"></property>
   </bean>
   <bean id="nurse" class="com.springframework.Nurse"></bean>
</beans>
```

#### Main.java
```java
package com.springframework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
   public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
       Doctor doctor = context.getBean(Doctor.class);
       doctor.assist();
       System.out.println(doctor.getQualification());
   }
}
```

#### Doctor.java
```java
package com.springframework;

public class Doctor implements Staff {
   private String qualification;

   public String getQualification() {
       return qualification;
   }

   public void setQualification(String qualification) {
       this.qualification = qualification;
   }

   public void assist(){
       System.out.println("Doctor is assisting");
   }

   @Override
   public String toString() {
       return "Doctor{" +
               "qualification='" + qualification + '\'' +
               '}';
   }
}
```

### 2. Getter Injection
Getter injection, although less common, involves injecting dependencies via getter methods. It is not a standard practice in Spring and is generally not recommended because it can lead to less readable and maintainable code.

## Constructor Injection in Spring

### Overview
Constructor injection is a method of dependency injection where the Spring container injects dependencies into a bean via its constructor. It is a preferred approach for mandatory dependencies and ensures that the bean is always in a fully initialized state.

### Advantages
- **Immutability**: Promotes immutability by making dependencies final.
- **Mandatory Dependencies**: Ensures that all required dependencies are provided during bean creation.
- **Simpler Testing**: Easier to write unit tests as dependencies are provided through the constructor.

### Example
#### Doctor.java
```java
package com.springframework;

public class Doctor implements Staff {
   private String qualification;

   public Doctor(String qualification) {
       this.qualification = qualification;
   }

   public String getQualification() {
       return qualification;
   }

   public void setQualification(String qualification) {
       this.qualification = qualification;
   }

   public void assist(){
       System.out.println("Doctor is assisting");
   }

   @Override
   public String toString() {
       return "Doctor{" +
               "qualification='" + qualification + '\'' +
               '}';
   }
}
```

#### XML Configuration
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd">
   <bean id="doctor" class="com.springframework.Doctor">
       <constructor-arg value="MBBS"></constructor-arg>
   </bean>
   <bean id="nurse" class="com.springframework.Nurse"></bean>
</beans>
```

## Annotation-Based Configuration in Spring

### Overview
Annotation-based configuration in Spring simplifies the configuration process by using annotations to define beans and their dependencies directly in the Java classes. It reduces the need for extensive XML configuration and promotes a more declarative programming model.

### Key Annotations
- `@Configuration`: Indicates that a class declares one or more `@Bean` methods and may be processed by the Spring container to generate bean definitions.
- `@Bean`: Marks a method as a bean producer, letting Spring manage and inject it as needed.
- `@Component`: Marks a class as a Spring component, making it a candidate for component scanning and automatic bean detection.
- `@Autowired`: Allows Spring to resolve and inject collaborating beans into a bean.
- `@ComponentScan`: Configures component scanning directives for use with `@Configuration` classes.

### Example
#### XML Configuration
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:context="http://www.springframework.org/schema/context"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context.xsd">

   <!-- Enable component scanning -->
   <context:component-scan base-package="com.springframework"/>

</beans>
```

#### Main.java
```java
package com.springframework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
   public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
       Doctor doctor = context.getBean(Doctor.class);
       doctor.assist();
   }
}
```

#### Doctor.java
```java
package com.springframework;

import org.springframework.stereotype.Component;

@Component
public class Doctor implements Staff {
   private String qualification;

   public void assist(){
       System.out.println("Doctor is assisting");
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
}
```

### Java-Based Configuration in Spring

### Overview
Java-based configuration in Spring involves using Java classes and annotations to configure beans and their dependencies. It provides a type-safe and refactor-friendly way to manage Spring configurations, reducing the need for XML configuration files.

### Example
#### BeanConfig.java
```java
package com.springframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.springframework")
public class BeanConfig {

   @Bean
   public Doctor doctor(){
       return new Doctor();
   }
}
```

#### Main.java
```java
package com.springframework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
   public static void main(String[] args) {
       ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
       Doctor doctor = context.getBean(Doctor.class);
       doctor.assist();
   }
}
```

## Scope in Prototype

### Example
```java
package com.springframework;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class Doctor implements Staff {
   private String qualification;

   public void assist(){
       System.out.println("Doctor is assisting");
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
}
```

## Spring Bean Lifecycle

### Overview
The lifecycle of a Spring bean is managed by the Spring IoC container. Understanding the lifecycle phases and the various callbacks available allows developers to hook into different stages of bean creation and destruction.

### Bean Lifecycle Phases
1. **Instantiation**: The Spring container instantiates the bean using its constructor.
2. **Populating Properties**: The container sets the bean's properties and dependencies.
3. **BeanNameAware**: If the bean implements `BeanNameAware`, the container calls `setBeanName(String name)` with the bean's ID.
4. **BeanFactoryAware**: If the bean implements `BeanFactoryAware`, the container calls `setBeanFactory(BeanFactory beanFactory)`.
5. **ApplicationContextAware**: If the bean implements `ApplicationContextAware`, the container calls `setApplicationContext(ApplicationContext context)`.
6. **Pre-Initialization (BeanPostProcessor)**: The container calls the `postProcessBeforeInitialization` method of any `BeanPostProcessor` beans.
7. **InitializingBean**: If the bean implements `InitializingBean`, the container calls `afterPropertiesSet()`.
8. **Custom Init Method**: If a custom initialization method is specified, the container calls it.
9. **Post-Initialization (BeanPostProcessor)**: The container calls the `postProcessAfterInitialization` method of any `BeanPostProcessor` beans.
10. **Bean Ready for Use**: The bean is now fully initialized and ready for use by the application.
11. **DisposableBean**: If the bean implements `DisposableBean`, the container calls `destroy()`.
12. **Custom Destroy Method**: If a custom destroy method is specified, the container calls it.

### Example
#### Configuration Class
```java
package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public MyBean myBean() {
        return new MyBean();
    }
}
```

#### Bean Class
```java
package com.example;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MyBean implements InitializingBean, DisposableBean {

    public MyBean() {
        System.out.println("Bean instantiated");
    }

    public void setProperty(String property) {
        System.out.println("Property set: " + property);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean: afterPropertiesSet");
    }

    public void customInit() {
        System.out.println("Custom init method");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean: destroy");
    }

    public void customDestroy() {
        System.out.println("Custom destroy method");
    }
}
```

#### Main Class
```java
package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        MyBean myBean = context.getBean(MyBean.class);
        myBean.setProperty("Example property");
        
        ((AnnotationConfigApplicationContext) context).close();
    }
}
```

## Conclusion
Understanding the Spring bean lifecycle helps in leveraging various lifecycle callbacks to perform custom initialization and cleanup. This ensures that beans are properly managed throughout their lifecycle, leading to more robust and maintainable applications.
