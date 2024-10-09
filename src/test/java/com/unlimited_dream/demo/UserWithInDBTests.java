package com.unlimited_dream.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unlimited_dream.demo.controller.Controller;
import com.unlimited_dream.demo.dao.UserRepository;
import com.unlimited_dream.demo.dto.UserDTO;
import com.unlimited_dream.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserWithInDBTests {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	Controller controller;

	@SpyBean
	UserService userService;

	@Autowired
	UserRepository userRepository;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setup() {
		userRepository.deleteAll();
	}



	@Test
	void testPostUserInvalidRequest() throws Exception {
		UserDTO userDTO = new UserDTO("Iron man", 15);
		mockMvc.perform(post("/api/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userDTO)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testPostUserSuccessfully() throws Exception {
		UserDTO userDTO = new UserDTO("Iron man", 18);
		mockMvc.perform(post("/api/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Iron man"))
				.andExpect(jsonPath("$.age").value(18));
	}

}
