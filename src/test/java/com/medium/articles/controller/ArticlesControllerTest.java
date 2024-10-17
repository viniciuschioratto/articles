package com.medium.articles.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medium.articles.entity.Articles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.hamcrest.Matchers.is;

@WebMvcTest(ArticlesController.class)
@ExtendWith(SpringExtension.class)
public class ArticlesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Should create a new article")
    @Test
    public void should_create_article() throws Exception {
        Articles newArticle = Articles.builder()
                .id(UUID.randomUUID())
                .title("Test title")
                .description("Test description")
                .build();

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newArticle))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", is(newArticle.getTitle())));
    }

    @DisplayName("Should get a article by id")
    @Test
    public void should_get_article_byId() throws Exception {

        UUID id = UUID.randomUUID();

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/articles/{id}", id)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(id.toString())));
    }

    @DisplayName("Should update a article by id")
    @Test
    public void should_update_article_byId() throws Exception {
        UUID id = UUID.randomUUID();
        Articles updateArticle = Articles.builder()
                .id(id)
                .title("Test title")
                .description("Test description")
                .build();

        this.mockMvc.perform(
                        MockMvcRequestBuilders.put("/articles/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateArticle))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", is(updateArticle.getTitle())));
    }

    @DisplayName("Should delete a article by id")
    @Test
    public void should_delete_article_byId() throws Exception {

        UUID id = UUID.randomUUID();

        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/articles/{id}", id)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", is("Article deleted")));
    }
}
