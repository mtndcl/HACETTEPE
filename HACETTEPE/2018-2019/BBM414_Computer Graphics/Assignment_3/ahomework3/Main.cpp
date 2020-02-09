#include <glad/glad.h>
#include <GLFW/glfw3.h>
#include <iostream>





void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void processInput(GLFWwindow *window);
void drawcircle(GLfloat x, GLfloat y, GLfloat  radius);
void drawsquare();
void amazingmove(GLuint  scaleLocation);
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
float rotangle = 0.0f;
float   speed = 0.005;
int goright = 1;

bool   rot = true;
bool heart = false;
float  heartspeed = 0.5f;
float amazingspeed = 0.005f;
bool  spirial = false;
float aa = 0.0f;

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


		 if (rot){

			 Rotate(scaleLocation);
		 }
		 else if (heart) {
			 Scale(scaleLocation);
		 }
		 else if (spirial)
		 {
			 amazingmove(scaleLocation);
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
void amazingmove(GLuint  scaleLocation) {


	float  scale[4][4] = {
	{0.5f,0.0f,0.0f,0.0f},
		{0.0f,0.5f,0.0f,0.0f},
	{0.0f,0.0f,0.5f,0.0f},
	{0.0f,0.0f,0.0f,1.0f},
	};
	float  rotate[4][4] = {
	{cos(aa),-sin(aa),0.0f,0.0f},
	{sin(aa),cos(aa),0.0f,0.0f},
	{0.0f,0.0f,1.0f,0.0f},
	{0.0f,0.0f,0.0f,1.0f},
	};


	

	float x = 0.0f;
	float y = 0.0f;
	float theta = 2.0f * 3.1415926f * angle / 10; //the current angle
	x = (0.260f / 10)*angle * cosf(theta); //the x component
	y = (0.260f / 10)*angle * sinf(theta); //the y component
	if (goright) {
		if (angle > 15.0f) {
			goright = false;
		}
		angle = angle + 0.05f;
		x = (0.260f / 10)*angle *cosf(theta); //the x component
		y = (0.260f / 10)*angle * -sinf(theta); //the y component
	}
	else
	{
		if (angle < -15.0f) {
			goright = true;
		}
		angle = angle - 0.05f;
	}
	//take uniform variable from vertexshader
	float matrixTranslated[4][4] = { {0.5f,0.0f,0.0f,0.0f},
									{0.0f,0.5f,0.0f,0.0f},
									{0.0f,0.0f,0.5f,0.0f},
									{x,y,0.0f,1.0f} };



	float temp[4][4];
	int k, r, c;
	for (c = 0; c < 4; ++c) for (r = 0; r < 4; ++r) {
		temp[c][r] = 0.f;
		for (k = 0; k < 4; ++k)
			temp[c][r] += matrixTranslated[k][r] * rotate[c][k];
	}
	aa =aa+ amazingspeed  ;
	

	glUniformMatrix4fv(scaleLocation, 1, GL_FALSE, (const GLfloat*)temp);
	


	

}
// process all input: query GLFW whether relevant keys are pressed/released this frame and react accordingly
// ---------------------------------------------------------------------------------------------------------
void processInput(GLFWwindow *window)
{
	if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) {


		if (rot)
		{
			rot = false;
		}
		else if (heart)
		{
			heart = false;
		}
		else if (spirial)
		{
			spirial = false;
		}
		

	}else if (glfwGetKey(window, GLFW_KEY_R) == GLFW_PRESS) {

		rot = true;
		heart = false;
		spirial = false;
		
	}
	else if (glfwGetKey(window, GLFW_KEY_H) == GLFW_PRESS) {


		rot = false;
		heart = true;
		spirial = false;
		

	}
	else if (glfwGetKey(window, GLFW_KEY_M) == GLFW_PRESS) {


		rot = false;
		heart = false;
		spirial = true;
		
		

	}
	else if (glfwGetKey(window, GLFW_KEY_U) == GLFW_PRESS) {



		if (rot) {

			if (speed < 0.05) {

				speed = speed + 0.00005;

			}
		}else if (spirial) {

			if (amazingspeed < 0.05) {

				amazingspeed = amazingspeed + 0.0005;
				
			}
		}
		
		


	}
	else if (glfwGetKey(window, GLFW_KEY_L) == GLFW_PRESS) {


		if (rot) {

			if (speed > -0.05) {

				speed = speed - 0.00005;

			}
		}
		else if (spirial) {

			if (amazingspeed > -0.05) {

				amazingspeed = amazingspeed -0.0005;

			}
		}
		
		

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
			{heartspeed,0.0f,0.0f,0.0f},
				{0.0f,heartspeed,0.0f,0.0f},
			{0.0f,0.0f,heartspeed,0.0f},
			{0.0f,0.0f,0.0f,1.0f},
	};


	if (goright)
	{
		heartspeed = heartspeed +  0.005;
		if (heartspeed >0.75)
		{
			
			goright = false;
		}
	}
	else {
		heartspeed = heartspeed - 0.005;
		if (heartspeed < 0.25)
		{
			
			goright = true;
		}
	}

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
	{cos(rotangle),-sin(rotangle),0.0f,0.0f},
	{sin(rotangle),cos(rotangle),0.0f,0.0f},
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



	
	rotangle = rotangle +speed;
		
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