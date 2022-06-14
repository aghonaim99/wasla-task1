package com.example.waslatask1.ControllersTests;

import com.example.waslatask1.Controllers.DataSourcesController;
import com.example.waslatask1.Services.DataSourcesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DataSourcesControllerTests {
    private MockMvc mockMvc;
    @InjectMocks
    DataSourcesController dsController = new DataSourcesController();
    @Mock
    DataSourcesService dsService;


    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(dsController).build();
    }

    @Test
    public void controllerGetsDataFromDataSourcesAndReturnsSuccess() throws Exception {
        when(dsService.saveAhramPosts()).thenReturn("Posts saved successfully");
        when(dsService.saveMasryYoumPosts()).thenReturn("Posts saved successfully");

        mockMvc.perform(get("/ds/getposts"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Success"));
    }

    @Test
    public void saveAhramPostsFails_And_ControllerReturnsFailure() throws Exception {
        when(dsService.saveAhramPosts()).thenReturn("");
        when(dsService.saveMasryYoumPosts()).thenReturn("Posts saved successfully");

        mockMvc.perform(get("/ds/getposts"))
                .andExpect(status().isOk())
                .andExpect(content().string("One (or more) failed"));
    }

    @Test
    public void saveMasryYoumPostsFails_And_ControllerReturnsFailure() throws Exception {
        when(dsService.saveAhramPosts()).thenReturn("Posts saved successfully");
        when(dsService.saveMasryYoumPosts()).thenReturn("");

        mockMvc.perform(get("/ds/getposts"))
                .andExpect(status().isOk())
                .andExpect(content().string("One (or more) failed"));
    }

    @Test
    public void controllerFails() throws Exception {
        when(dsService.saveAhramPosts()).thenReturn("");
        when(dsService.saveMasryYoumPosts()).thenReturn("");

        mockMvc.perform(get("/ds/getposts"))
                .andExpect(status().isNoContent())
                .andExpect(content().string("Failed"));
    }
}
