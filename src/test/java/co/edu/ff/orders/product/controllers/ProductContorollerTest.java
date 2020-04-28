package co.edu.ff.orders.product.controllers;

import co.edu.ff.orders.product.domain.*;
import com.google.gson.Gson;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ProductContorollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;


    @Test
    void createProduct() throws Exception {
        // organizar....
        Product productCreated = Product.of(
                0L,
                Name.of("producto1"),
                Description.of("descripcion1"),
                BasePrice.of(new BigDecimal("21313")),
                TaxRate.of(new BigDecimal("0.7")),
                ProductStatus.BORRADOR,
                InventoryQueantity.of(3)
        );

        ProductoperationRequest productRequest= ProductoperationRequest.of(
                Name.of("producto1"),
                Description.of("descripcion1"),
                BasePrice.of(new BigDecimal("21313")),
                TaxRate.of(new BigDecimal("0.7")),
                ProductStatus.BORRADOR,
                InventoryQueantity.of(3)
        );

        String productJson = this.gson.toJson(ProductOperationSuccess.of(productCreated));
        String productRequestJ=this.gson.toJson(productRequest);
        /*when(services.createProduct(productRequest))
                .thenReturn(ProductOperationSuccess.of(productCreated));*/
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestJ)
                ;

        mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(productJson))
        ;
    }

    @Test
    void yfindProductById() throws Exception {
        // organizar....
        Product productCreated = Product.of(
                1L,
                Name.of("producto1"),
                Description.of("descripcion1"),
                BasePrice.of(new BigDecimal("21313.0")),
                TaxRate.of(new BigDecimal("0.7")),
                ProductStatus.BORRADOR,
                InventoryQueantity.of(3)
        );
        String productJson = this.gson.toJson(ProductOperationSuccess.of(productCreated));
        /*when(services.findProductById(ProductId.of(1L)))
                .thenReturn(ProductOperationSuccess.of(productCreated));*/
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/products/1");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(productJson));
    }

    @Test
    void zfindAllProducts() throws Exception {
        // organizar....
        Product productCreated1= Product.of(
                1L,
                Name.of("producto1"),
                Description.of("descripcion1"),
                BasePrice.of(new BigDecimal("21313.0")),
                TaxRate.of(new BigDecimal("0.7")),
                ProductStatus.BORRADOR,
                InventoryQueantity.of(3)
        );

        Product productCreated2 = Product.of(
                2L,
                Name.of("producto2"),
                Description.of("descripcion2"),
                BasePrice.of(new BigDecimal("21313.0")),
                TaxRate.of(new BigDecimal("0.7")),
                ProductStatus.BORRADOR,
                InventoryQueantity.of(3)
        );
        List<Product> products = new ArrayList<>();
        products.add(productCreated1);
        products.add(productCreated2);

        String productJson = this.gson.toJson(products);
       /* when(services.findAll())
                .thenReturn(products);*/
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/products/");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(productJson));

    }

    @Test
    void updateProducts() throws Exception {
        // organizar....
        Product productCreated = Product.of(
                1L,
                Name.of("producto3"),
                Description.of("descripcion3"),
                BasePrice.of(new BigDecimal("21313.12")),
                TaxRate.of(new BigDecimal("0.5")),
                ProductStatus.BORRADOR,
                InventoryQueantity.of(3)
        );

        ProductoperationRequest productRequest= ProductoperationRequest.of(
                Name.of("producto3"),
                Description.of("descripcion3"),
                BasePrice.of(new BigDecimal("21313.12")),
                TaxRate.of(new BigDecimal("0.5")),
                ProductStatus.BORRADOR,
                InventoryQueantity.of(3)
        );

        String productJson = this.gson.toJson(ProductOperationSuccess.of(productCreated));
        String productRequestJ=this.gson.toJson(productRequest);
        /*when(services.updateProduct(ProductId.of(1L),productRequest))
                .thenReturn(ProductOperationSuccess.of(productCreated));*/
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestJ)
                ;

        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(productJson));
    }

    @Test
    void deleteProducts() throws Exception {
        Product productCreated = Product.of(
                2L,
                Name.of("producto2"),
                Description.of("descripcion2"),
                BasePrice.of(new BigDecimal("21313.0")),
                TaxRate.of(new BigDecimal("0.7")),
                ProductStatus.BORRADOR,
                InventoryQueantity.of(3)
        );

        String productJson= this.gson.toJson(ProductOperationSuccess.of(productCreated));
        /*when(services.deleteProduct(ProductId.of(1L)))
                .thenReturn(ProductOperationSuccess.of(productCreated));*/
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.delete("/api/v1/products/2");
        this.mockMvc.perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(productJson));
    }


}