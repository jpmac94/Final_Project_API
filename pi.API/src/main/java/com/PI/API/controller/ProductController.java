package com.PI.API.controller;

import com.PI.API.model.dto.ProductDTO;
import com.PI.API.repository.IReservationRepository;
import com.PI.API.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO){

        return new ResponseEntity<>( productService.save(productDTO), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }
    @GetMapping("/findAll")
    public ResponseEntity<Set<ProductDTO>> findAll(){
        Set<ProductDTO> productsDTO = productService.findAll();
        return new ResponseEntity<>(productsDTO, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateProduct (@Valid @RequestBody ProductDTO productDTO,@PathVariable("id") Long id){
       ProductDTO productDTOUpdate=productService.update(productDTO,id);
        return new ResponseEntity<>("Product with id " + productDTOUpdate.getId() + " has been Updated",HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteProduct (@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>("Product with id " + id + " has been deleted",HttpStatus.OK);
    }
    @GetMapping("/findByCity/{city}")
    public ResponseEntity<Set<ProductDTO>> findByCity(@PathVariable("city") String city){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductByCity(city));
    }
    @GetMapping("/findProductByIdCategory/{id}")
    public ResponseEntity<Set<ProductDTO>> findProductByIdCategory(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.findProductByCategory(id),HttpStatus.OK);
    }
    @GetMapping("/findProductByNameCityAndDate")
    public ResponseEntity<Set<ProductDTO>> findProductByNameCityAndDate(@RequestParam("nameCity") String nameCity, @RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate){
        return new ResponseEntity<>(productService.findProductByNameCityAndDate(nameCity,startDate,endDate),HttpStatus.OK);
    }
    @GetMapping("/findProductByDate/{startDate}/{endDate}")
    public ResponseEntity<Set<ProductDTO>> findProductByDate(@PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate){
        return new ResponseEntity<>(productService.findProductByDate(startDate,endDate),HttpStatus.OK);
    }
    @GetMapping("/findProductByIdCategoryAndNameCityAndDates")
    public ResponseEntity<List<ProductDTO>> findProductByIdCategoryAndNameCityAndDates(@RequestParam("idCategory") Long idCategory,@RequestParam("nameCity") String nameCity, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        return new ResponseEntity<>(productService.findProductByIdCategoryAndNameCityAndDates(idCategory,nameCity,startDate,endDate),HttpStatus.OK);
    }
    @GetMapping("/findProductByIdCategoryAndCity")
    public ResponseEntity<List<ProductDTO>> findProductByIdCategoryAndCity(@RequestParam("idCategory") Long idCategory,@RequestParam("nameCity") String nameCity){
        return new ResponseEntity<>(productService.findProductByIdCategoryAndCity(idCategory, nameCity),HttpStatus.OK);
    }
    @GetMapping("/findProductByIdCategoryAndDates")
    public ResponseEntity<List<ProductDTO>> findProductByIdCategoryAndDates(@RequestParam("idCategory") Long idCategory, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        return new ResponseEntity<>(productService.findProductByIdCategoryAndDates(idCategory, startDate, endDate),HttpStatus.OK);
    }

}
