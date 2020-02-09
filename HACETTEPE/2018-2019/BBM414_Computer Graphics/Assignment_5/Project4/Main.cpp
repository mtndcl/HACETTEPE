
#include <Angel_commons/Angel.h>
#include <iostream>






#define STB_IMAGE_IMPLEMENTATION
#include <stb_image.h>

using namespace std;



unsigned int loadTexture(const char *path);
void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void mouse_callback(GLFWwindow* window, double xpos, double ypos);
void processInput(GLFWwindow *window);

// settings
const unsigned int SCR_WIDTH = 1000;
const unsigned int SCR_HEIGHT = 800;

// camera
vec3 cameraPos = vec3(0.0f, 4.0f, 0.0f);
vec3 cameraFront = vec3(0.0f, 0.0f, -1.0f);
vec3 cameraUp = vec3(0.0f, 1.0f, 0.0f);



bool firstMouse = true;
float yaw = 410.0f;	// yaw is initialized to -90.0 degrees since a yaw of 0.0 results in a direction vector pointing to the right so we initially rotate a bit to the left.
float pitch = -18.0f;
float lastX = SCR_WIDTH / 2.0;
float lastY = SCR_HEIGHT / 2.0;
float fov = 15.0f;



// timing
float passedTime = 0.0f;	// time between current frame and last frame
float lastFrame = 0.0f;
bool statu = true;
bool shadowsKeyPressed = false;
int main()
{
	// glfw: initialize and configure
	// ------------------------------
	glfwInit();
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);


	// glfw: initialize and configure
	// ------------------------------
	glfwInit();
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);


	// glfw window creation
	// --------------------
	GLFWwindow* window = glfwCreateWindow(SCR_WIDTH, SCR_HEIGHT, "LearnOpenGL", NULL, NULL);
	if (window == NULL)
	{
		std::cout << "Failed to create GLFW window" << std::endl;
		glfwTerminate();
		return -1;
	}
	glfwMakeContextCurrent(window);
	glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);
	glfwSetCursorPosCallback(window, mouse_callback);

	// tell GLFW to capture our mouse
	glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

	// glad: load all OpenGL function pointers
	// ---------------------------------------
	if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress))
	{
		std::cout << "Failed to initialize GLAD" << std::endl;
		return -1;
	}

	// configure global opengl state
	// -----------------------------
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

	// build and compile our shader program
	// ------------------------------------
	GLuint ourShader = InitShader("vertex.glsl", "fragment.glsl");

	// set up vertex data (and buffer(s)) and configure vertex attributes for Dragon Object
	// ------------------------------------------------------------------
	
	
		
	
	
	//----------------------------------------------------------------------------------------------
	// set up vertex data (and buffer(s)) and configure vertex attributes for surface
	// ------------------------------------------------------------------

	

	float vertices[] = {
		// positions          // normals           // texture coords
		-0.5f, -0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  0.0f,  0.0f,
		 0.5f, -0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  1.0f,  0.0f,
		 0.5f,  0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  1.0f,  1.0f,
		 0.5f,  0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  1.0f,  1.0f,
		-0.5f,  0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  0.0f,  1.0f,
		-0.5f, -0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  0.0f,  0.0f,

		-0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  0.0f,  0.0f,
		 0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  1.0f,  0.0f,
		 0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  1.0f,  1.0f,
		 0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  1.0f,  1.0f,
		-0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  0.0f,  1.0f,
		-0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  0.0f,  0.0f,

		-0.5f,  0.5f,  0.5f, -1.0f,  0.0f,  0.0f,  1.0f,  0.0f,
		-0.5f,  0.5f, -0.5f, -1.0f,  0.0f,  0.0f,  1.0f,  1.0f,
		-0.5f, -0.5f, -0.5f, -1.0f,  0.0f,  0.0f,  0.0f,  1.0f,
		-0.5f, -0.5f, -0.5f, -1.0f,  0.0f,  0.0f,  0.0f,  1.0f,
		-0.5f, -0.5f,  0.5f, -1.0f,  0.0f,  0.0f,  0.0f,  0.0f,
		-0.5f,  0.5f,  0.5f, -1.0f,  0.0f,  0.0f,  1.0f,  0.0f,

		 0.5f,  0.5f,  0.5f,  1.0f,  0.0f,  0.0f,  1.0f,  0.0f,
		 0.5f,  0.5f, -0.5f,  1.0f,  0.0f,  0.0f,  1.0f,  1.0f,
		 0.5f, -0.5f, -0.5f,  1.0f,  0.0f,  0.0f,  0.0f,  1.0f,
		 0.5f, -0.5f, -0.5f,  1.0f,  0.0f,  0.0f,  0.0f,  1.0f,
		 0.5f, -0.5f,  0.5f,  1.0f,  0.0f,  0.0f,  0.0f,  0.0f,
		 0.5f,  0.5f,  0.5f,  1.0f,  0.0f,  0.0f,  1.0f,  0.0f,

		-0.5f, -0.5f, -0.5f,  0.0f, -1.0f,  0.0f,  0.0f,  1.0f,
		 0.5f, -0.5f, -0.5f,  0.0f, -1.0f,  0.0f,  1.0f,  1.0f,
		 0.5f, -0.5f,  0.5f,  0.0f, -1.0f,  0.0f,  1.0f,  0.0f,
		 0.5f, -0.5f,  0.5f,  0.0f, -1.0f,  0.0f,  1.0f,  0.0f,
		-0.5f, -0.5f,  0.5f,  0.0f, -1.0f,  0.0f,  0.0f,  0.0f,
		-0.5f, -0.5f, -0.5f,  0.0f, -1.0f,  0.0f,  0.0f,  1.0f,

		-0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f,  0.0f,  1.0f,
		 0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f,  1.0f,  1.0f,
		 0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f,  1.0f,  0.0f,
		 0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f,  1.0f,  0.0f,
		-0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f,  0.0f,  0.0f,
		-0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f,  0.0f,  1.0f
	};
	// first, configure the cube's VAO (and VBO)
	unsigned int  cubeVAO, cubeVBO;
	glGenVertexArrays(1, &cubeVAO);
	glGenBuffers(1, &cubeVBO);

	glBindBuffer(GL_ARRAY_BUFFER, cubeVBO);
	glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);

	glBindVertexArray(cubeVAO);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 8 * sizeof(float), (void*)0);
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 8 * sizeof(float), (void*)(3 * sizeof(float)));
	glEnableVertexAttribArray(1);
	glVertexAttribPointer(2, 2, GL_FLOAT, GL_FALSE, 8 * sizeof(float), (void*)(6 * sizeof(float)));
	glEnableVertexAttribArray(2);




	float planeVertices[] = {
		// positions            // normals         // texcoords
		 1.0f, -0.5f,  1.0f,  0.0f, 1.0f, 0.0f,  1.0f,  1.0f,
		1.0f, -0.5f,  -49.0f,  0.0f, 1.0f, 0.0f,   1.0f,  -49.0f,
		-49.0f, -0.5f, 1.0f,  0.0f, 1.0f, 0.0f,   -49.0f, 1.0f,

		-49.0f, -0.5f,  -49.0f,  0.0f, 1.0f, 0.0f,  -49.0f, -49.0f,
		1.0f, -0.5f,  -49.0f,  0.0f, 1.0f, 0.0f,   1.0f,  -49.0f,
		-49.0f, -0.5f, 1.0f,  0.0f, 1.0f, 0.0f,   -49.0f, 1.0f,
	};
	// plane VAO
	unsigned int planeVAO, planeVBO;
	glGenVertexArrays(1, &planeVAO);
	glGenBuffers(1, &planeVBO);
	glBindVertexArray(planeVAO);
	glBindBuffer(GL_ARRAY_BUFFER, planeVBO);
	glBufferData(GL_ARRAY_BUFFER, sizeof(planeVertices), planeVertices, GL_STATIC_DRAW);
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 8 * sizeof(float), (void*)0);
	glEnableVertexAttribArray(1);
	glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 8 * sizeof(float), (void*)(3 * sizeof(float)));
	glEnableVertexAttribArray(2);
	glVertexAttribPointer(2, 2, GL_FLOAT, GL_FALSE, 8 * sizeof(float), (void*)(6 * sizeof(float)));
	glBindVertexArray(0);

	unsigned int floorTexture = loadTexture("sand.bmp");
	unsigned int diffuseMap = loadTexture("pp.png");
	//ourShader.setInt("floorTexture", 0);
	//Settting Light Position
	///
	

	///glUseProgram(ourShader);
	glUniform1i(glGetUniformLocation(ourShader, "material.diffuse"), 0);
	glUniform1i(glGetUniformLocation(ourShader, "material.specular"), 1);
	


	


	while (!glfwWindowShouldClose(window))
	{
		// per-frame time logic
		// --------------------
		float currentFrame = glfwGetTime();
		passedTime = currentFrame - lastFrame;
		lastFrame = currentFrame;

		// input
		// -----
		processInput(window);
		glUseProgram(ourShader);
		// render
		// ------
		glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		//This setting for beginnig camera angle
		vec3 front;
		front.x = cos(DegreesToRadians * (yaw)) * cos(DegreesToRadians * (pitch));
		front.y = sin(DegreesToRadians * (pitch));
		front.z = sin(DegreesToRadians * (yaw)) * cos(DegreesToRadians * (pitch));
		cameraFront = normalize(front);
		//------------------------------

		
		//Angel perspective function
		mat4 projection = Perspective(fov, (float)SCR_WIDTH / (float)SCR_HEIGHT, 0.1f, 100.0f);
		//Angel use transpose so we have to transpose again
		projection = transpose(projection);

		// camera/view transformation
		mat4 view = LookAt(cameraPos, cameraPos + cameraFront, cameraUp);
		//Angel use transpose so we have to transpose again
		view = transpose(view);

		//Send these matrix to vertex shader
		glUniformMatrix4fv(glGetUniformLocation(ourShader, "view"), 1, GL_FALSE, &view[0][0]);
		glUniformMatrix4fv(glGetUniformLocation(ourShader, "projection"), 1, GL_FALSE, &projection[0][0]);

		
		
		
	
		
		//lightingShader.setVec3("light.position", camera.Position);
		glUniform3fv(glGetUniformLocation(ourShader, "light.position"), 1, cameraPos + cameraFront);
		//lightingShader.setVec3("light.direction", camera.Front);
		glUniform3fv(glGetUniformLocation(ourShader, "light.direction"), 1, cameraFront);
		//lightingShader.setFloat("light.cutOff", glm::cos(glm::radians(12.5f)));
		glUniform1f(glGetUniformLocation(ourShader, "light.cutOff"), cos(DegreesToRadians*(6.5f)));
		//lightingShader.setFloat("light.outerCutOff", glm::cos(glm::radians(17.5f)));
		glUniform1f(glGetUniformLocation(ourShader, "light.outerCutOff"), cos(DegreesToRadians*(8.5f)));
		//lightingShader.setVec3("viewPos", camera.Position);
		glUniform3fv(glGetUniformLocation(ourShader, "viewPos"), 1, cameraPos );

		// light properties
		//lightingShader.setVec3("light.ambient", 0.1f, 0.1f, 0.1f);
		glUniform3f(glGetUniformLocation(ourShader, "light.ambient"), 0.1f, 0.1f, 0.1f);
		//lightingShader.setVec3("light.diffuse", 0.8f, 0.8f, 0.8f);
		glUniform3f(glGetUniformLocation(ourShader, "light.diffuse"), 0.8f, 0.8f, 0.8f);
		//lightingShader.setVec3("light.specular", 1.0f, 1.0f, 1.0f);
		glUniform3f(glGetUniformLocation(ourShader, "light.specular"), 1.0f, 1.0f, 1.0f);
		//lightingShader.setFloat("light.constant", 1.0f);
		glUniform1f(glGetUniformLocation(ourShader, "light.constant"), 1.0f);
		//lightingShader.setFloat("light.linear", 0.09f);
		glUniform1f(glGetUniformLocation(ourShader, "light.linear"), 0.90f);
		//lightingShader.setFloat("light.quadratic", 0.032f);
		glUniform1f(glGetUniformLocation(ourShader, "light.quadratic"), 0.032f);

		// material properties
		//lightingShader.setFloat("material.shininess", 32.0f);
		glUniform1f(glGetUniformLocation(ourShader, "material.shininess"), 32.0f);
	
	
		
		glBindVertexArray(planeVAO);

		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, floorTexture);
		glDrawArrays(GL_TRIANGLES, 0, 6);

		glBindVertexArray(cubeVAO);
		for (float  i =0; i < 25; i++)
		{
			


			for (float  j = 0;j < 25;j++)
			{
				

				mat4  model = {
				0.25,0,0,i/2,
				0,0.25,0,1,
				0,0,0.25,j/2,
				0,0,0,1,
				};


			
				glUniformMatrix4fv(glGetUniformLocation(ourShader, "model"), 1, GL_FALSE, transpose(model));

				glActiveTexture(GL_TEXTURE0);
				glBindTexture(GL_TEXTURE_2D, diffuseMap);

				glDrawArrays(GL_TRIANGLES, 0, 36);

			}
				
			
			
		}


	


		glUniform1i(glGetUniformLocation(ourShader, "statu"), statu);
		
		

		// glfw: swap buffers and poll IO events (keys pressed/released, mouse moved etc.)
		// -------------------------------------------------------------------------------
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	// optional: de-allocate all resources once they've outlived their purpose:
	// ------------------------------------------------------------------------
	glDeleteVertexArrays(1, &planeVAO);
	glDeleteBuffers(1, &planeVBO);
	glDeleteVertexArrays(1, &cubeVAO);
	glDeleteBuffers(1, &cubeVBO);

	// glfw: terminate, clearing all previously allocated GLFW resources.
	// ------------------------------------------------------------------
	glfwTerminate();
	return 0;
}

// process all input: query GLFW whether relevant keys are pressed/released this frame and react accordingly
// ---------------------------------------------------------------------------------------------------------


void processInput(GLFWwindow *window)
{
	if (pitch > 89.0f)
		pitch = 89.0f;
	if (pitch < -89.0f)
		pitch = -89.0f;

	vec3 front;
	front.x = cos(DegreesToRadians * (yaw)) * cos(DegreesToRadians * (pitch));
	front.y = sin(DegreesToRadians * (pitch));
	front.z = sin(DegreesToRadians * (yaw)) * cos(DegreesToRadians * (pitch));

	if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS) {
		glfwSetWindowShouldClose(window, true);
	}
	//It's too slow for camera movement so I multiplyied with 3
	float cameraSpeed = 3 * passedTime;
	if (glfwGetKey(window, GLFW_KEY_UP) == GLFW_PRESS) {
		cameraPos += cameraSpeed * cameraFront;
	}
	if (glfwGetKey(window, GLFW_KEY_DOWN) == GLFW_PRESS) {
		cameraPos -= cameraSpeed * cameraFront;
	}
	if (glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS) {
		cameraPos -= normalize(cross(cameraFront, cameraUp)) * cameraSpeed;
	}
	if (glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS) {
		cameraPos += normalize(cross(cameraFront, cameraUp)) * cameraSpeed;
	}		
	
	if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) {
		cameraPos += cameraSpeed * cameraUp;
	}
	if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) {
		cameraPos -= cameraSpeed * cameraUp;
	}
	if (glfwGetKey(window, GLFW_KEY_O) == GLFW_PRESS && !shadowsKeyPressed)
	{
		statu= !statu;
		shadowsKeyPressed = true;
	}
	if (glfwGetKey(window, GLFW_KEY_O) == GLFW_RELEASE)
	{
		shadowsKeyPressed = false;
	}

}

// glfw: whenever the window size changed (by OS or user resize) this callback function executes
// ---------------------------------------------------------------------------------------------
void framebuffer_size_callback(GLFWwindow* window, int width, int height)
{
	// make sure the viewport matches the new window dimensions; note that width and 
	// height will be significantly larger than specified on retina displays.
	glViewport(0, 0, width, height);
}

// glfw: whenever the mouse moves, this callback is called
// -------------------------------------------------------
void mouse_callback(GLFWwindow* window, double xpos, double ypos)
{
	if (firstMouse)
	{
		//This positions start to middle of screen and calculate from center
		lastX = xpos;
		lastY = ypos;
		firstMouse = false;
	}

	float xoffset = xpos - lastX;
	float yoffset = lastY - ypos; // reversed since y-coordinates go from bottom to top
	lastX = xpos;
	lastY = ypos;

	float sensitivity = 0.1f; // Mouse sensitivity
	xoffset *= sensitivity;
	yoffset *= sensitivity;

	yaw += xoffset;
	pitch += yoffset;

	// make sure that when pitch is out of bounds, screen doesn't get flipped
	if (pitch > 89.0f)
		pitch = 89.0f;
	if (pitch < -89.0f)
		pitch = -89.0f;

	vec3 front;
	front.x = cos(DegreesToRadians * (yaw)) * cos(DegreesToRadians * (pitch));
	front.y = sin(DegreesToRadians * (pitch));
	front.z = sin(DegreesToRadians * (yaw)) * cos(DegreesToRadians * (pitch));
	cameraFront = normalize(front);
}

unsigned int loadTexture(char const * path)
{
	unsigned int textureID;
	glGenTextures(1, &textureID);

	int width, height, nrComponents;
	unsigned char *data = stbi_load(path, &width, &height, &nrComponents, 0);
	if (data)
	{
		GLenum format;
		if (nrComponents == 1)
			format = GL_RED;
		else if (nrComponents == 3)
			format = GL_RGB;
		else if (nrComponents == 4)
			format = GL_RGBA;

		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexImage2D(GL_TEXTURE_2D, 0, format, width, height, 0, format, GL_UNSIGNED_BYTE, data);
		glGenerateMipmap(GL_TEXTURE_2D);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, format == GL_RGBA ? GL_CLAMP_TO_EDGE : GL_REPEAT); // for this tutorial: use GL_CLAMP_TO_EDGE to prevent semi-transparent borders. Due to interpolation it takes texels from next repeat 
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, format == GL_RGBA ? GL_CLAMP_TO_EDGE : GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

		stbi_image_free(data);
	}
	else
	{
		std::cout << "Texture failed to load at path: " << path << std::endl;
		stbi_image_free(data);
	}

	return textureID;
}