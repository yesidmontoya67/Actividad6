package co.edu.ff.orders.product.controllers;

import co.edu.ff.orders.product.repositories.ProductRepository;
import co.edu.ff.orders.product.services.ProductService;
import co.edu.ff.orders.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerSystemTest {

    @Autowired
    private MockMvc mockMvc;

   /* @MockBean
    ProductRepository productRepository;

    @MockBean
    UserRepository userRepository;*/

    @Test
    void systemTest() throws Exception{
        String producto1 = "{\"name\":\"producto1\",\"description\":\"descripcion1\",\"basePrice\":21313.0,\"taxRate\":0.7,\"productStatus\":\"BORRADOR\",\"inventoryQueantity\":3}";
        String producto2 = "{\"name\":\"producto2\",\"description\":\"descripcion2\",\"basePrice\":25484.0,\"taxRate\":0.5,\"productStatus\":\"BORRADOR\",\"inventoryQueantity\":3}";
        String createdJson1 = "{\"value\":{\"id\":0,\"name\":\"producto1\",\"description\":\"descripcion1\",\"basePrice\":21313.0,\"taxRate\":0.7,\"productStatus\":\"BORRADOR\",\"inventoryQueantity\":3}}";
        String createdJson2 = "{\"value\":{\"id\":0,\"name\":\"producto2\",\"description\":\"descripcion2\",\"basePrice\":25484.0,\"taxRate\":0.5,\"productStatus\":\"BORRADOR\",\"inventoryQueantity\":3}}";
        String findJson = "{\"value\":{\"id\":1,\"name\":\"producto1\",\"description\":\"descripcion1\",\"basePrice\":21313.0,\"taxRate\":0.7,\"productStatus\":\"BORRADOR\",\"inventoryQueantity\":3}}";
        String findJson2= "{\"value\":{\"id\":1,\"name\":\"producto2\",\"description\":\"descripcion2\",\"basePrice\":25484.0,\"taxRate\":0.5,\"productStatus\":\"BORRADOR\",\"inventoryQueantity\":3}}";
        String findJsonArray = "[{\"id\":1,\"name\":\"producto1\",\"description\":\"descripcion1\",\"basePrice\":21313.0,\"taxRate\":0.7,\"productStatus\":\"BORRADOR\",\"inventoryQueantity\":3}," +
                "{\"id\":2,\"name\":\"producto2\",\"description\":\"descripcion2\",\"basePrice\":25484.0,\"taxRate\":0.5,\"productStatus\":\"BORRADOR\",\"inventoryQueantity\":3}]";
        mockMvc.perform(
                post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(producto1)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(createdJson1));
        mockMvc.perform(
                post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(producto2)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(createdJson2));
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(findJson));
        mockMvc.perform(get("/api/v1/products/"))
                .andExpect(status().isOk())
                .andExpect(content().json(findJsonArray));
        mockMvc.perform(delete("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(findJson));
        mockMvc.perform(put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(producto2))
                .andExpect(status().isOk())
                .andExpect(content().json(findJson2));

    }





}
