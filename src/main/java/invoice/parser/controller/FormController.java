/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoice.parser.controller;

import invoice.parser.entity.Form;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author konstantinos
 */
@RestController
@RequestMapping("/form")
public class FormController {
        
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public void addForm(@RequestBody Form form) {
        System.out.println(form.getCustomer().getFullName());
    }
    
}
