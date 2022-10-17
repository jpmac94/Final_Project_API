package com.PI.API.service.impl;

import com.PI.API.exception.BookingException;
import com.PI.API.exception.ResourceNotFoundException;
import com.PI.API.model.Category;
import com.PI.API.model.Product;
import com.PI.API.model.dto.CategoryDTO;
import com.PI.API.model.dto.ProductDTO;
import com.PI.API.repository.IProductRepository;

import com.PI.API.repository.IReservationRepository;
import com.PI.API.service.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ProductService implements IProductService {

    //private static  final Logger logger = Logger.getLogger(ProductService.class);

    @Autowired
    IProductRepository productRepository;

    @Autowired
    ObjectMapper mapper;


    public ProductDTO saveProduct(ProductDTO productDto){

        Product product = mapperClass(productDto);
        Product product1 = productRepository.save(product);

        return mapperDTO(product1);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {

        //logger.info("Product " + productDTO.getName()+" saved");
                return saveProduct(productDTO);
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product","id",id));
        ProductDTO productDTO = mapperDTO(product);
                //logger.info("Product " + productDTO.getName() + " has been found");
        return productDTO;
    }

    @Override
    public Set<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();

        Set<ProductDTO> productsDTO = new HashSet<>();

        for (Product product : products) {
            productsDTO.add(mapperDTO(product));
            //logger.info("Product " + product.getName() + " has been found");
        }
        return productsDTO;
    }

    @Override
    public ProductDTO update(ProductDTO productDTO, Long id) {

        ProductDTO productDTOUpdate=saveProduct(productDTO);
        //logger.info("Product " + productDTO.getName() + " has been modificated");
        return productDTOUpdate;
    }

    @Override
    public void delete(Long id) {
        productRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Product","id",id));
        productRepository.deleteById(id);
        //logger.info("Product has been eliminated");

    }

    @Override
    public Set<ProductDTO> findProductByCity(String city) {
        Set<Product>products=productRepository.findProductByCity(city);
        Set<ProductDTO> productDTOSet=new HashSet<>();
        for (Product product1 : products) {
            productDTOSet.add(mapperDTO(product1));
        }
        if (products.isEmpty()){
            throw new BookingException(HttpStatus.NOT_FOUND,"No products have been found for the indicated city.");
        }
        return productDTOSet;
    }

    @Override
    public Set<ProductDTO> findProductByCategory(Long id) {
        Set<Product> productSet=productRepository.findProductByCategory(id);
        Set<ProductDTO>productDTOSet=new HashSet<>();
        if (productSet.isEmpty())
            throw new BookingException(HttpStatus.NOT_FOUND,"No products have been found for the indicated id category.");
        for (Product product : productSet) {
            productDTOSet.add(mapperDTO(product));
        }
        return productDTOSet;
    }

   @Override
    public Set<ProductDTO> findProductByNameCityAndDate(String nameCity, String startDate, String endDate) {
        Set<Product> productSet=productRepository.findProductByNameCityAndDate(nameCity,startDate,endDate);
        Set<ProductDTO> productDTOSet=new HashSet<>();
        if (productSet.isEmpty())
            throw new BookingException(HttpStatus.NOT_FOUND,"No products have been found for the indicated nameCity and dates.");
        for (Product product : productSet) {
            productDTOSet.add(mapperDTO(product));
        }
        return productDTOSet;
    }

    @Override
    public Set<ProductDTO> findProductByDate(String startDate, String endDate) {
        Set<Product> productSet=productRepository.findProductByDate(startDate,endDate);
        Set<ProductDTO> productDTOSet=new HashSet<>();
        if (productSet.isEmpty())
            throw new BookingException(HttpStatus.NOT_FOUND,"No products have been found for the indicated dates.");
        for (Product product : productSet) {
            productDTOSet.add(mapperDTO(product));
        }
        return productDTOSet;
    }

    @Override
    public List<ProductDTO> findProductByIdCategoryAndNameCityAndDates(Long idCategory, String nameCity, String startDate, String endDate) {
        List<Product> productList=productRepository.findProductByIdCategoryAndNameCityAndDates(idCategory,nameCity,startDate,endDate);
        List<ProductDTO> productDTOList=new ArrayList<>();
        if (productList.isEmpty())
            throw new BookingException(HttpStatus.NOT_FOUND,"No products have been found for the indicated idCategory, nameCity and dates.");
        for (Product product : productList) {
            productDTOList.add(mapperDTO(product));
        }
        return productDTOList;
    }



    @Override
    public List<ProductDTO> findProductByIdCategoryAndCity(Long idCategory, String nameCity) {
        List<Product>productList=productRepository.findProductByIdCategoryAndCity(idCategory,nameCity);
        List<ProductDTO>productDTOList=new ArrayList<>();
        if (productList.isEmpty())
            throw new BookingException(HttpStatus.NOT_FOUND,"No products have been found for the indicated idCategory y nameCity.");
        for (Product product : productList) {
            productDTOList.add(mapperDTO(product));
        }
        return productDTOList;

    }

    @Override
    public List<ProductDTO> findProductByIdCategoryAndDates(Long idCategory, String startDate, String endDate) {
        List<Product> productList=productRepository.findProductByIdCategoryAndDates(idCategory,startDate,endDate);
        List<ProductDTO> productDTOList=new ArrayList<>();
        if (productList.isEmpty())
            throw new BookingException(HttpStatus.NOT_FOUND,"No products have been found for the indicated idCategory and dates.");
        for (Product product : productList) {
            productDTOList.add(mapperDTO(product));

        }
        return productDTOList;
    }

    //mappers

    private ProductDTO mapperDTO(Product product){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        ProductDTO productDTO=mapper.convertValue(product,ProductDTO.class);
        return productDTO;
    }

    private Product mapperClass(ProductDTO productDTO){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        Product product=mapper.convertValue(productDTO,Product.class);
        return product;
    }
}
