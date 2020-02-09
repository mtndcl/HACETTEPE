#include <glad/glad.h>
#include <GLFW/glfw3.h>
#include <iostream>





void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void processInput(GLFWwindow *window);
void drawcircle(GLfloat x, GLfloat y, GLfloat  radius);
void drawsquare();
void Scale(GLuint  scaleLocation);
void  Rotate(GLuint  scaleLocation);
void changecolor(GLuint  color, int data);
// settings
const unsigned int SCR_WIDTH = 500;
const unsigned int SCR_HEIGHT = 500;

////vertex shader
const char *vertexShaderSource = "#version 330 core\n"

"uniform mat4 model;\n"

"layout (location = 0) in vec3 aPos;\n"
"void main()\n"
"{\n"
"   gl_Position = model*vec4(aPos,1.0f);\n"
"}\0";
///fragment shader
const char *fragmentShaderSource = "#version 330 core\n"
"out vec4 FragColor;\n"
"uniform vec4 color;\n"
"void main()\n"
"{\n"
"   FragColor =color;\n"
"}\n\0";

///user input
int data = 0;
///degree of rotation
float angle = 0.0f;

int goright = 1;



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

	// glad: load all OpenGL function pointers
	// ---------------------------------------
	if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress))
	{
		std::cout << "Failed to initialize GLAD" << std::endl;
		return -1;
	}


	///
	int vertexShader = glCreateShader(GL_VERTEX_SHADER);
	glShaderSource(vertexShader, 1, &vertexShaderSource, NULL);
	glCompileShader(vertexShader);
	// check for shader compile errors
	int success;
	char infoLog[512];
	glGetShaderiv(vertexShader, GL_COMPILE_STATUS, &success);
	if (!success)
	{
		glGetShaderInfoLog(vertexShader, 512, NULL, infoLog);
	}
	// fragment shader
	int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fragmentShader, 1, &fragmentShaderSource, NULL);
	glCompileShader(fragmentShader);
	glGetShaderiv(fragmentShader, GL_COMPILE_STATUS, &success);

	// link shaders
	int shaderProgram = glCreateProgram();
	glAttachShader(shaderProgram, vertexShader);
	glAttachShader(shaderProgram, fragmentShader);
	glLinkProgram(shaderProgram);
	// check for linking errors
	glGetProgramiv(shaderProgram, GL_LINK_STATUS, &success);

	glDeleteShader(fragmentShader);

	///get uniform value fragment shader
	GLuint color = glGetUniformLocation(shaderProgram, "color");
	///get uniform value vertex shader
	GLuint scaleLocation = glGetUniformLocation(shaderProgram, "model");

	while (!glfwWindowShouldClose(window))
	{
		// input
		// -----
		processInput(window);

		// render
		// ------
		glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT);
	
		////user input results
		if (data == 0)
		{
			float  scale[4][4] = {
			{1.0f,0.0f,0.0f,0.0f},
			{0.0f,1.0f,0.0f,0.0f},
			{0.0f,0.0f,1.0f,0.0f},
			{0.0f,0.0f,0.0f,1.0f},
			};


			glUniformMatrix4fv(scaleLocation, 1, GL_FALSE, (const GLfloat*)scale);
		}
		else if (data == 1)
		{
			Scale(scaleLocation);

		}
		else  if (data == 2)
		{

			Rotate(scaleLocation);

		}
		else  if (data == 3)
		{

			Rotate(scaleLocation);

		}

		glUseProgram(shaderProgram);



		changecolor(color, data);


		drawcircle(-0.5f, 0.5f, 0.5);
		drawcircle(-0.5f, -0.5f, 0.5);
		drawcircle(0.5f, 0.5f, 0.5);
		drawcircle(0.5f, -0.5f, 0.5);
		glUniform4f(color, 0.0f, 1.0f, 0.0f, 1.0f);
		drawsquare();
		changecolor(color, data);
		drawcircle(-0.625f, 0.0f, 0.125);
		drawcircle(0.625f, 0.0f, 0.125);

		drawcircle(0.0f, 0.625f, 0.125);
		drawcircle(0.0f, -0.625f, 0.125);

		drawcircle(-0.5f, 0.5f, 0.125);
		drawcircle(-0.5f, -0.5f, 0.125);
		drawcircle(0.5f, 0.5f, 0.125);
		drawcircle(0.5f, -0.5f, 0.125);


		//glDrawElements(GL_TRIANGLE_FAN, 362*3, GL_UNSIGNED_INT, 0);
		// glBindVertexArray(0); // no need to unbind it every time 

		// glfw: swap buffers and poll IO events (keys pressed/released, mouse moved etc.)
		// -------------------------------------------------------------------------------
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	// optional: de-allocate all resources once they've outlived their purpose:
	// ------------------------------------------------------------------------


	// glfw: terminate, clearing all previously allocated GLFW resources.
	// ------------------------------------------------------------------
	glfwTerminate();
	return 0;
}

// process all input: query GLFW whether relevant keys are pressed/released this frame and react accordingly
// ---------------------------------------------------------------------------------------------------------
void processInput(GLFWwindow *window)
{
	if (glfwGetKey(window, GLFW_KEY_1) == GLFW_PRESS) {

		data = 1;
		printf("1  clicked\n");

	}
	else if (glfwGetKey(window, GLFW_KEY_2) == GLFW_PRESS) {
		data = 2;
		angle = 0;
		printf("2  clicked\n");
	}
	else if (glfwGetKey(window, GLFW_KEY_3) == GLFW_PRESS) {
		data = 3;
		angle = 0;
		printf("2  clicked\n");
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

////Draw the circles
void  drawcircle(GLfloat x, GLfloat y, GLfloat  radius) {


	int i;
	GLfloat mypi = 2.0f * 3.14159265358979323846;
	GLfloat circleVerticesX[362];
	GLfloat circleVerticesY[362];
	GLfloat circleVerticesZ[362];

	circleVerticesX[0] = 0.5f;
	circleVerticesY[0] = 0.5f;
	circleVerticesZ[0] = 0;


	for (int i = 0; i < 362; i++)
	{
		circleVerticesX[i] = x + (radius * cos(i *  mypi / 360));
		circleVerticesY[i] = y + (radius * sin(i * mypi / 360));
		circleVerticesZ[i] = 0;
	}
	GLfloat allCircleVertices[(362) * 3];

	for (int i = 0; i < 362; i++)
	{
		allCircleVertices[i * 3] = circleVerticesX[i];
		allCircleVertices[(i * 3) + 1] = circleVerticesY[i];
		allCircleVertices[(i * 3) + 2] = circleVerticesZ[i];
	}

	unsigned int VBO, VAO, EBO;
	glGenVertexArrays(1, &VAO);
	glGenBuffers(1, &VBO);
	glGenBuffers(1, &EBO);
	// bind the Vertex Array Object first, then bind and set vertex buffer(s), and then configure vertex attributes(s).
	glBindVertexArray(VAO);

	glBindBuffer(GL_ARRAY_BUFFER, VBO);
	glBufferData(GL_ARRAY_BUFFER, sizeof(allCircleVertices), allCircleVertices, GL_STATIC_DRAW);

	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(362 * 3), allCircleVertices, GL_STATIC_DRAW);

	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), (void*)0);
	glEnableVertexAttribArray(0);

	// note that this is allowed, the call to glVertexAttribPointer registered VBO as the vertex attribute's bound vertex buffer object so afterwards we can safely unbind
	glBindBuffer(GL_ARRAY_BUFFER, 0);

	// remember: do NOT unbind the EBO while a VAO is active as the bound element buffer object IS stored in the VAO; keep the EBO bound.
	//glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

	// You can unbind the VAO afterwards so other VAO calls won't accidentally modify this VAO, but this rarely happens. Modifying other
	// VAOs requires a call to glBindVertexArray anyways so we generally don't unbind VAOs (nor VBOs) when it's not directly necessary.
	glBindVertexArray(0);


	// uncomment this call to draw in wireframe polygons.
	//glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

	// render loop
	// -----------

	glBindVertexArray(VAO); // seeing as we only have a single VAO there's no need to bind it every time, but we'll do so to keep things a bit more organized
	glDrawArrays(GL_TRIANGLE_FAN, 0, 362);
	glDeleteVertexArrays(1, &VAO);
	glDeleteBuffers(1, &VBO);
	glDeleteBuffers(1, &EBO);
}

///Draw square
void drawsquare() {


	float vertices[] = {
		0.5f,  0.5f, 0.0f,  // top right
		0.5f, -0.5f, 0.0f,  // bottom right
	   -0.5f, -0.5f, 0.0f,  // bottom left
	   -0.5f,  0.5f, 0.0f   // top left 
	};
	unsigned int indices[] = {  // note that we start from 0!
		0, 1, 3,  // first Triangle
		1, 2, 3   // second Triangle
	};
	unsigned int VBO, VAO, EBO;
	glGenVertexArrays(1, &VAO);
	glGenBuffers(1, &VBO);
	glGenBuffers(1, &EBO);
	// bind the Vertex Array Object first, then bind and set vertex buffer(s), and then configure vertex attributes(s).
	glBindVertexArray(VAO);

	glBindBuffer(GL_ARRAY_BUFFER, VBO);
	glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);

	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(indices), indices, GL_STATIC_DRAW);

	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), (void*)0);
	glEnableVertexAttribArray(0);

	// note that this is allowed, the call to glVertexAttribPointer registered VBO as the vertex attribute's bound vertex buffer object so afterwards we can safely unbind
	glBindBuffer(GL_ARRAY_BUFFER, 0);

	// remember: do NOT unbind the EBO while a VAO is active as the bound element buffer object IS stored in the VAO; keep the EBO bound.
	//glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

	// You can unbind the VAO afterwards so other VAO calls won't accidentally modify this VAO, but this rarely happens. Modifying other
	// VAOs requires a call to glBindVertexArray anyways so we generally don't unbind VAOs (nor VBOs) when it's not directly necessary.


	glBindVertexArray(VAO); // seeing as we only have a single VAO there's no need to bind it every time, but we'll do so to keep things a bit more organized
	glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
	glDeleteVertexArrays(1, &VAO);
	glDeleteBuffers(1, &VBO);
	glDeleteBuffers(1, &EBO);

}

///scale the shape
void Scale(GLuint  scaleLocation) {


	float  scale[4][4] = {
			{0.5f,0.0f,0.0f,0.0f},
				{0.0f,0.5f,0.0f,0.0f},
			{0.0f,0.0f,0.5f,0.0f},
			{0.0f,0.0f,0.0f,1.0f},
	};

	glUniformMatrix4fv(scaleLocation, 1, GL_FALSE, (const GLfloat*)scale);
}


///Rotate the shape
void  Rotate(GLuint  scaleLocation) {

	float  scale[4][4] = {
	{0.5f,0.0f,0.0f,0.0f},
		{0.0f,0.5f,0.0f,0.0f},
	{0.0f,0.0f,0.5f,0.0f},
	{0.0f,0.0f,0.0f,1.0f},
	};
	float  rotate[4][4] = {
	{cos(angle),-sin(angle),0.0f,0.0f},
	{sin(angle),cos(angle),0.0f,0.0f},
	{0.0f,0.0f,1.0f,0.0f},
	{0.0f,0.0f,0.0f,1.0f},
	};


	float temp[4][4];
	int k, r, c;
	for (c = 0; c < 4; ++c) for (r = 0; r < 4; ++r) {
		temp[c][r] = 0.f;
		for (k = 0; k < 4; ++k)
			temp[c][r] += scale[k][r] * rotate[c][k];
	}

	glUniformMatrix4fv(scaleLocation, 1, GL_FALSE, (const GLfloat*)temp);



	if (goright)
	{
		angle = angle + 0.005;
		if (angle > 1)
		{
			goright = false;
		}
	}
	else {
		angle = angle - 0.005;
		if (angle < -1)
		{
			goright = true;
		}
	}
}
//change the color when rotate
void changecolor(GLuint  color, int data) {

	if (data == 3)
	{

		float  blue;

		blue = abs(angle / 2);

		glUniform4f(color, 1, blue, blue, 1.0f);

	}
	else
	{
		glUniform4f(color, 1.0f, 0.0f, 0.0f, 1.0f);
	}


}