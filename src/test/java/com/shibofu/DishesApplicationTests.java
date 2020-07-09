package com.shibofu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest

public class DishesApplicationTests {

    @Autowired
    private WebApplicationContext context;

    //mock api 模拟http请求
    private MockMvc mockMvc;

    //初始化Web环境
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getMenuList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu?pageNo=2")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJvcGVuSWQiOiJvazZFbzQ5N3ZmS0FVVDVQTXNhM21BSWpwN1NBIiwiaWQiOiIxIn0.JM3eDUzo3ni-SV1cmm6o3r2U3i3DYPPi4V7d5gUglBXf_2_etyYqrMv5bgpGPgSp9kUa2LOVbfY3vUTCkmbg8Q")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getMenu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu/1")
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJvcGVuSWQiOiJvazZFbzQ5N3ZmS0FVVDVQTXNhM21BSWpwN1NBIiwiaWQiOiIxIn0.JM3eDUzo3ni-SV1cmm6o3r2U3i3DYPPi4V7d5gUglBXf_2_etyYqrMv5bgpGPgSp9kUa2LOVbfY3vUTCkmbg8Q")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
