#include <Angel_commons/Angel.h>


#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
#include "shader_m.h"
#include "Camera.h"
using namespace std;
void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void processInput(GLFWwindow *window);

// settings
void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void mouse_callback(GLFWwindow* window, double xpos, double ypos);
void scroll_callback(GLFWwindow* window, double xoffset, double yoffset);
void processInput(GLFWwindow *window);

// settings
const unsigned int SCR_WIDTH = 600;
const unsigned int SCR_HEIGHT = 600;

// camera
Camera camera(vec3(0.0f, 0.0f, 1.0f));
float lastX = SCR_WIDTH / 2.0f;
float lastY = SCR_HEIGHT / 2.0f;
bool firstMouse = true;

// timing
float deltaTime = 0.0f;	// time between current frame and last frame
float lastFrame = 0.0f;

///user input
///degree of rotation
float angle = 0.0f;
vec3 lightPos(1.2f, 1.0f, 2.0f);
float  speed = 0.005;



int main()
{
	// glfw: initialize and configure
	// ------------------------------
	glfwInit();
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

#ifdef __APPLE__
	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); // uncomment this statement to fix compilation on OS X
#endif

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
	glfwSetScrollCallback(window, scroll_callback);

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

	//Shader ourShader("vertex.glsl", "fragment.glsl");

	/////////////////////////read file

	Shader ourShader("5.3.light_casters.vs", "5.3.light_casters.fs");
	

	//Reading File 


	vector<vec4> modelvbo;
	vector<GLuint> modelebo;



	std::ifstream file("dragon_10k.obj");
	std::string temp;
	while (std::getline(file, temp)) {
		stringstream ss(temp);
		vector<string> result;
		while (ss.good()) {
			string substr;
			getline(ss, substr, ' ');
			result.push_back(substr);

		}
		if (result.at(0) == "v") {
			vec4 myvec;
			myvec.x = (float)std::stof(result.at(1));
			myvec.y = (float)std::stof(result.at(2));
			myvec.z = (float)std::stof(result.at(3));
			myvec.w = 1.0f;
			modelvbo.push_back(myvec);

		}
		else if (result.at(0) == "f")
		{
			modelebo.push_back((GLuint)std::stoi(result.at(1)) - 1);
			modelebo.push_back((GLuint)std::stoi(result.at(2)) - 1);
			modelebo.push_back((GLuint)std::stoi(result.at(3)) - 1);
		}
		result.clear();

	}

	unsigned int VBO, VAO, EBO;
	glGenVertexArrays(1, &VAO);
	glGenBuffers(1, &VBO);
	glGenBuffers(1, &EBO);
	// bind the Vertex Array Object first, then bind and set vertex buffer(s), and then configure vertex attributes(s).
	glBindVertexArray(VAO);

	glBindBuffer(GL_ARRAY_BUFFER, VBO);
	glBufferData(GL_ARRAY_BUFFER, modelvbo.size() * sizeof(vec4), &modelvbo[0], GL_STATIC_DRAW);

	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, modelebo.size() * sizeof(GLuint), &modelebo[0], GL_STATIC_DRAW);
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(0, 4, GL_FLOAT, GL_FALSE, 4 * sizeof(float), (void*)0);
	
	glEnableVertexAttribArray(1);
	glVertexAttribPointer(1, 4, GL_FLOAT, GL_FALSE, 4 * sizeof(float), (void*)(3*sizeof(float)));
	

	// note that this is allowed, the call to glVertexAttribPointer registered VBO as the vertex attribute's bound vertex buffer object so afterwards we can safely unbind
	glBindBuffer(GL_ARRAY_BUFFER, 0);

	// remember: do NOT unbind the EBO while a VAO is active as the bound element buffer object IS stored in the VAO; keep the EBO bound.
	//glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

	// You can unbind the VAO afterwards so other VAO calls won't accidentally modify this VAO, but this rarely happens. Modifying other
	// VAOs requires a call to glBindVertexArray anyways so we generally don't unbind VAOs (nor VBOs) when it's not directly necessary.
	glBindVertexArray(0);

	/////////////////////
	float planeVertices[] = {
		// positions            // normals        
		 10.0f, -0.5f,  10.0f,  0.0f, 1.0f, 0.0f,  
		-10.0f, -0.5f,  10.0f,  0.0f, 1.0f, 0.0f,   
		-10.0f, -0.5f, -10.0f,  0.0f, 1.0f, 0.0f,   

		 10.0f, -0.5f,  10.0f,  0.0f, 1.0f, 0.0f,  
		-10.0f, -0.5f, -10.0f,  0.0f, 1.0f, 0.0f,   
		 10.0f, -0.5f, -10.0f,  0.0f, 1.0f, 0.0f, 
	};
	// plane VAO
	unsigned int planeVAO, planeVBO;
	glGenVertexArrays(1, &planeVAO);
	glGenBuffers(1, &planeVBO);
	glBindVertexArray(planeVAO);
	glBindBuffer(GL_ARRAY_BUFFER, planeVBO);
	glBufferData(GL_ARRAY_BUFFER, sizeof(planeVertices), planeVertices, GL_STATIC_DRAW);
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void*)0);
	glEnableVertexAttribArray(1);
	glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void*)(3 * sizeof(float)));
	glBindVertexArray(0);
	
	

	////
	

	while (!glfwWindowShouldClose(window))
	{
		// input
		// -----



		processInput(window);



		mat4  rotate = {
		{cos(angle),-sin(angle),0.0f,0.0f},
		{0.0f,1.0f,0.0f,0.0f},
		{sin(angle),cos(angle),1.0f,0.0f},
		{0.0f,0.0f,0.0f,1.0f},
		};


		mat4  scale = {
		{0.02f,0.0f,0.0f,0.0f},
		{0.0f,0.02f,0.0f,0.0f},
		{0.0f,0.0f,0.02f,0.0f},
		{0.0f,0.0f,0.0f,1.0f},
		};


		
		glUniformMatrix4fv(glGetUniformLocation(ourShader.ID, "rotate"), 1, GL_FALSE,rotate*scale);
		



		angle = angle +speed;
		// render
		// ------
		float currentFrame = glfwGetTime();
		deltaTime = currentFrame - lastFrame;
		lastFrame = currentFrame;

		// input
		
		glClearColor(1.0f, 0.6f, 0.8f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	

		
		////////////////
		ourShader.use();
		ourShader.setVec3("objectColor", 0.0f, 1.0f, 0.0f);
		ourShader.setVec3("lightColor", 1.0f, 1.0f, 1.0f);
		ourShader.setVec3("lightPos", lightPos);
		ourShader.setVec3("viewPos", camera.Position);
		//////////////

		  // floor
		
		mat4 projection = transpose(Perspective(DegreesToRadians*(camera.Zoom), (float)SCR_WIDTH / (float)SCR_HEIGHT, 0.1f, 100.0f));
		
		ourShader.setMat4("projection", projection);

		// camera/view transformation
		mat4 view = transpose(camera.GetViewMatrix());
		ourShader.setMat4("view", view);
		glBindVertexArray(VAO); // seeing as we only have a single VAO there's no need to bind it every time, but we'll do so to keep things a bit more organized
		glDrawElements(GL_TRIANGLES, modelebo.size(), GL_UNSIGNED_INT, 0);
		
		
		
		
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	// optional: de-allocate all resources once they've outlived their purpose:
	// ------------------------------------------------------------------------
	glDeleteVertexArrays(1, &planeVAO);
	glDeleteBuffers(1, &planeVBO);

	// glfw: terminate, clearing all previously allocated GLFW resources.
	// ------------------------------------------------------------------
	glfwTerminate();
	return 0;
}

void processInput(GLFWwindow *window)
{
	if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
		glfwSetWindowShouldClose(window, true);

	if (glfwGetKey(window, GLFW_KEY_DOWN) == GLFW_PRESS)
		camera.ProcessKeyboard(FORWARD, deltaTime);
	if (glfwGetKey(window, GLFW_KEY_UP) == GLFW_PRESS)
		camera.ProcessKeyboard(BACKWARD, deltaTime);
	if (glfwGetKey(window, GLFW_KEY_LEFT) == GLFW_PRESS)
		camera.ProcessKeyboard(LEFT, deltaTime);
	if (glfwGetKey(window, GLFW_KEY_RIGHT) == GLFW_PRESS)
		camera.ProcessKeyboard(RIGHT, deltaTime);
	if (glfwGetKey(window, GLFW_KEY_PAGE_UP) == GLFW_PRESS)
		camera.ProcessKeyboard(UP, deltaTime);
	if (glfwGetKey(window, GLFW_KEY_PAGE_DOWN) == GLFW_PRESS)
		camera.ProcessKeyboard(DOWN, deltaTime);
	if (glfwGetKey(window, GLFW_KEY_KP_ADD) == GLFW_PRESS)
		speed += 0.0001f;
	if (glfwGetKey(window, GLFW_KEY_KP_SUBTRACT) == GLFW_PRESS)
		speed -= 0.0001f;
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
		lastX = xpos;
		lastY = ypos;
		firstMouse = false;
	}

	float xoffset = xpos - lastX;
	float yoffset = lastY - ypos; // reversed since y-coordinates go from bottom to top

	lastX = xpos;
	lastY = ypos;

	camera.ProcessMouseMovement(xoffset, yoffset);
}

// glfw: whenever the mouse scroll wheel scrolls, this callback is called
// ----------------------------------------------------------------------
void scroll_callback(GLFWwindow* window, double xoffset, double yoffset)
{
	
}