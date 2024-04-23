package com.sp.ecomm;

import com.sp.ecomm.dto.ProductDTO;
import com.sp.ecomm.entity.Product;
import com.sp.ecomm.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductAPITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @Test
    public void findAll() throws Exception {
        ProductDTO product = new ProductDTO();

        List<ProductDTO> products = Arrays.asList(product);
        given(productService.findAll(any(), any())).willReturn(products);

        this.mockMvc.perform(get("/api/v1/products?name=size&value=M"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1,'name': 'Stock 1';'quantity': 1;'size'}]"));
    }
}