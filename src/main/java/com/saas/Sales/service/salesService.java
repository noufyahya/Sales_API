package com.saas.Sales.service;

import com.saas.Sales.Entity.Sale;
import com.saas.Sales.repo.salesRepo;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// OpenCSV Specific (The "Strategy" parts)
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

@NoArgsConstructor
@Service
public class salesService {
    @Autowired
    private salesRepo repo;
    private static final Logger logger = LoggerFactory.getLogger(salesService.class);

    @Transactional
    public void importSalesCsv(InputStream inputStream) {
        System.out.println(">>> IMPORT STARTED <<<"); // If you don't see this, the function isn't being called!
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            HeaderColumnNameMappingStrategy<Sale> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Sale.class);

            CsvToBean<Sale> csvToBean = new CsvToBeanBuilder<Sale>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // 3. Convert CSV rows to Java Objects
            List<Sale> salesList = csvToBean.parse();
            System.out.println(">>> ROWS PARSED: " + salesList.size());
            // 4. Batch save to your Dockerized Postgres
            repo.saveAll(salesList);

        } catch (Exception e) {
            // Log the error - very important for Docker debugging
            System.err.println("Error during CSV import: " + e.getMessage());
        }

    }
    public List<Object[]> getTopProducts(){
    return repo.findTopProducts();
   }

    public List<Object[]> getTopSegments(){
        return repo.findTopSegments();
    }
//    public products addProduct(products product1) {
//        logger.info("record added");
//        return repo.save(product1);
//    }

    public List<Sale> showProducts() {
        return repo.findAll();
    }


    public Sale showProductById(Integer id) {
        return repo.findById(id).orElse(null);
    }
}
