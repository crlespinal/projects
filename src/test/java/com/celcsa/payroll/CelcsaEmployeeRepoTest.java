package com.celcsa.payroll;

//import static org.junit.jupiter.api.Assertions.assertTrue;

import com.celcsa.payroll.domain.employee.CelcsaEmployee;
import com.celcsa.payroll.domain.employee.FirstName;
import com.celcsa.payroll.domain.employee.LastName;
import com.celcsa.payroll.domain.employee.MiddleName;
import com.celcsa.payroll.domain.employee.Username;
import com.celcsa.payroll.repositories.CelcsaEmployeeRepo;
import com.celcsa.payroll.services.CelcsaEmployeeServiceImpl;

//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import reactor.core.publisher.Mono;

/**
 * CelcsaEmployeeRepoTest
 */
/*@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CelcsaEmployeeRepoTest.class })
@SpringBootTest
public class CelcsaEmployeeRepoTest {

    @Mock
    CelcsaEmployeeServiceImpl service;

    @Mock
    CelcsaEmployeeRepo repo;

    @Test
    public void testFind() {
        Mono<CelcsaEmployee> found = service.findByUsernameLike("carlose");
        assertTrue(found==null);
    }

    @Test
    public void testPersist(){
        /*CelcsaEmployee e = new CelcsaEmployee(
            new Username("carlose"), 
        new FirstName("Carlos"),
        new LastName("Espinal"),
        new MiddleName("R"));
        
        repo.save(e);

        assertTrue(service.findByUsernameLike("carlose")!=null);*/

  //  }
    
//}