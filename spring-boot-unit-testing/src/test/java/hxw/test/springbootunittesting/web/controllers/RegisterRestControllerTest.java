package hxw.test.springbootunittesting.web.controllers;

import static hxw.test.springbootunittesting.web.utils.ResponseBodyMatchers.responseBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hxw.test.springbootunittesting.domain.User;
import hxw.test.springbootunittesting.service.UserService;
import hxw.test.springbootunittesting.web.advice.ErrorResult;
import hxw.test.springbootunittesting.web.vo.UserResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RegisterRestController.class)
class RegisterRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void whenValidInput_thenReturns200() throws Exception {

        UserResource user = new UserResource("huangxuewei", "huangxueweihxw@gmail.com");

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
            .contentType(MediaType.APPLICATION_JSON)
            .param("sendWelcomeMail", "true")
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isOk());
    }

    @Test
    void whenNullValue_thenReturns400() throws Exception {
        UserResource user = new UserResource(null, "huangxuueweihxw@gmail.com");

        MvcResult mvcResult = mockMvc.perform(post("/forums/{forumId}/register", 42L)
            .contentType(MediaType.APPLICATION_JSON)
            .param("sendWelcomeMail", "true")
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isBadRequest())
            .andReturn();

        ErrorResult expectedErrorResponse = new ErrorResult("name", "must not be null");

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        String expectedResponseBody = objectMapper.writeValueAsString(expectedErrorResponse);

        assertThat(expectedResponseBody).isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    void whenValidInput_thenMapsToBusinessModel() throws Exception {

        UserResource user = new UserResource("huangxuewei", "huangxueweihxw@gmail.com");

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
            .contentType(MediaType.APPLICATION_JSON)
            .param("sendWelcomeMail", "true")
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isOk());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(userService, times(1)).registerUser(userCaptor.capture(), eq(true));

        assertThat(userCaptor.getValue().getName()).isEqualTo("huangxuewei");
        assertThat(userCaptor.getValue().getEmail()).isEqualTo("huangxueweihxw@gmail.com");
    }

    @Test
    void whenValidInput_thenReturnsUserResource() throws Exception {

        UserResource user = new UserResource("huangxuewei", "huangxueweihxw@gmail.com");

        MvcResult mvcResult = mockMvc.perform(post("/forums/{forumId}/register", 42L)
            .contentType(MediaType.APPLICATION_JSON)
            .param("sendWelcomeMail", "true")
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isOk())
            .andReturn();

        UserResource expectedResponseBody = user;

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(expectedResponseBody))
            .isEqualToIgnoringWhitespace(actualResponseBody);
    }

    @Test
    void whenValidInput_thenReturnsUserResource_withFluentApi() throws Exception {
        UserResource user = new UserResource("huangxuewei", "huangxueweihxw@gmail.com");

        UserResource expectedResponseBody = user;

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
            .contentType(MediaType.APPLICATION_JSON)
            .param("sendWelcomeMail", "true")
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isOk())
            .andExpect(
                responseBody().containsObjectAsJson(expectedResponseBody, UserResource.class));
    }

    @Test
    void whenNullValue_thenReturns400AndErrorResult_withFluentApi() throws Exception {
        UserResource user = new UserResource(null, "huangxueweihxw@gmail.com");

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
            .contentType(MediaType.APPLICATION_JSON)
            .param("sendWelcomeMail", "true")
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isBadRequest())
            .andExpect(responseBody().containsError("name", "must not be null"));
    }
}