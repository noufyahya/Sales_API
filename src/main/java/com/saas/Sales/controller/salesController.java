package com.saas.Sales.controller;

import com.saas.Sales.Entity.Sale;
import com.saas.Sales.service.salesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class salesController {

    @Autowired
    private salesService salesservice;

    @GetMapping("/top-products")
    public List<Object []> getTopProducts(){
        //This gets top products based on revenue
        return salesservice.getTopProducts();

    }
    @GetMapping("/top-segments")
    public List<Object []> getTopSegments(){
        //This gets top products based on revenue
        return salesservice.getTopSegments();

    }
    @GetMapping("/show_sales")
    public List<Sale> showSales(){
        return salesservice.showProducts();

    }
    @GetMapping("/product/{id}")
    public Sale showProduct(@PathVariable Integer id){
        return salesservice.showProductById(id);

    }


}
