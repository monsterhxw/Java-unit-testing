package hxw.test.springbootunittesting.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hxw.test.springbootunittesting.persistence.entity.UserEntity;
import hxw.test.springbootunittesting.persistence.repository.UserRepository;
import hxw.test.springbootunittesting.web.vo.UserResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.flyway.enabled=false",
    "spring.liquibase.enabled=false"
})
@AutoConfigureMockMvc
class RegisterUseCaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void registrationWorksThroughAllLayers() throws Exception {
        UserResource user = new UserResource("huangxuewei", "huangxueweihxw@gmail.com");

        mockMvc.perform(post("/forums/{forumId}/register", 42L)
            .contentType(MediaType.APPLICATION_JSON)
            .param("sendWelcomeMail", "true")
            .content(objectMapper.writeValueAsBytes(user)))
            .andExpect(status().isOk());

        UserEntity userEntity = userRepository.findByName("huangxuewei");

        assertThat(userEntity.getEmail()).isEqualTo("huangxueweihxw@gmail.com");
    }
}
