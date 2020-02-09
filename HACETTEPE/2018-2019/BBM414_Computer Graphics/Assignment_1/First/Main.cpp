
#include <glad/glad.h>
#include <GLFW/glfw3.h>
#include <math.h>


#define SCREEN_WIDTH 500
#define SCREEN_HEIGHT 500
void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void drawCircle(GLfloat x, GLfloat y, GLfloat z, GLfloat radius, GLint sides);
const char *vertexShaderSource = "#version 330 core\n"
"layout (location = 0) in vec3 aPos;\n"
"void main()\n"
"{\n"
"   gl_Position = vec4(aPos, 1);\n"
"}\0";
const char *fragmentShaderSource = "#version 330 core\n"
"out vec4 FragColor;\n"
"void main()\n"
"{\n"
"   FragColor = vec4(1.0f, 0.0f, 0.0f, 1.0f);\n"
"}\n\0";
const char *fragmentShaderSource1 = "#version 330 core\n"
"out vec4 FragColor;\n"
"void main()\n"
"{\n"
"   FragColor = vec4(0.0f, 1.0f, 0.0f, 1.0f);\n"
"}\n\0";
int main(void)
{

	///our wimdow
	GLFWwindow *window;

	// Initialize the library
	if (!glfwInit())
	{
		return -1;
	}
	
	// Create a windowed mode window and its OpenGL context
	window = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Assignment1", NULL, NULL);

	if (!window)
	{
		glfwTerminate();
		return -1;
	}
	
	// Make the window's context current
	glfwMakeContextCurrent(window);
	//İf  resized window
	glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);



	///Create window center of device
	GLFWmonitor* monitor = glfwGetPrimaryMonitor();
	const GLFWvidmode* mode = glfwGetVideoMode(monitor);
	glfwSetWindowPos(window, (mode->width - SCREEN_WIDTH) / 2, (mode->height - SCREEN_HEIGHT) / 2);
	///All  time update window size a square
	glfwSetWindowAspectRatio(window, 1, 1);
	///window limit size dont let pass height otherwise, it can not be a square 
	glfwSetWindowSizeLimits(window, GLFW_DONT_CARE, GLFW_DONT_CARE, mode->height, mode->height);
	if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress))
	{
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
	//glAttachShader(shaderProgram, vertexShader);
	glAttachShader(shaderProgram, fragmentShader);
	glLinkProgram(shaderProgram);
	// check for linking errors
	glGetProgramiv(shaderProgram, GL_LINK_STATUS, &success);



	int fragmentShader1 = glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fragmentShader1, 1, &fragmentShaderSource1, NULL);
	glCompileShader(fragmentShader1);

	glGetShaderiv(fragmentShader1, GL_COMPILE_STATUS, &success);

	// link shaders
	int shaderProgram1 = glCreateProgram();
	//glAttachShader(shaderProgram, vertexShader);
	glAttachShader(shaderProgram1, fragmentShader1);
	glLinkProgram(shaderProgram1);
	// check for linking errors
	glGetProgramiv(shaderProgram1, GL_LINK_STATUS, &success);






	glDeleteShader(fragmentShader);
	glDeleteShader(fragmentShader1);


	
	glViewport(0.0f, 0.0f, SCREEN_WIDTH, SCREEN_HEIGHT); // specifies the part of the window to which OpenGL will draw (in pixels), convert from normalised to pixels
	glMatrixMode(GL_PROJECTION); // projection matrix defines the properties of the camera that views the objects in the world coordinate frame. Here you typically set the zoom factor, aspect ratio and the near and far clipping planes
	glLoadIdentity(); // replace the current matrix with the identity matrix and starts us a fresh because matrix transforms such as glOrpho and glRotate cumulate, basically puts us at (0, 0, 0)
	glOrtho(0, SCREEN_WIDTH, 0, SCREEN_HEIGHT, 0, 1); // essentially set coordinate system
	glMatrixMode(GL_MODELVIEW); // (default matrix mode) modelview matrix defines how your objects are transformed (meaning translation, rotation and scaling) in your world
	glLoadIdentity(); // same as above comment

	///my square coordinat
	GLfloat square[] =
	{
		SCREEN_WIDTH /4, SCREEN_WIDTH / 4, 0,
		SCREEN_WIDTH / 4, SCREEN_WIDTH*3 / 4, 0,
		SCREEN_WIDTH *3/ 4, SCREEN_WIDTH *3/ 4, 0,
		SCREEN_WIDTH *3/ 4, SCREEN_WIDTH / 4, 0
	};
	// Loop until the user closes the window
	while (!glfwWindowShouldClose(window)){
		// ------
		glClear(GL_COLOR_BUFFER_BIT);

		///my window background 
		glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		///my circles shader
		glUseProgram(shaderProgram);
		

		//draw  circles
		drawCircle(SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4, 0, SCREEN_WIDTH / 4, 360);
		drawCircle(SCREEN_WIDTH*3 / 4, SCREEN_HEIGHT / 4, 0, SCREEN_WIDTH / 4, 360);
		drawCircle(SCREEN_WIDTH / 4, SCREEN_HEIGHT *3/ 4, 0, SCREEN_WIDTH / 4, 360);
		drawCircle(SCREEN_WIDTH *3/ 4, SCREEN_HEIGHT *3/ 4, 0, SCREEN_WIDTH / 4, 360);
		
		//my square color
		glUseProgram(shaderProgram1);
		glColor3f(0.0f, 1.0f, 0.0f);
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(3, GL_FLOAT, 0, square);
		glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
		glDisableClientState(GL_VERTEX_ARRAY);
		

		//my small  squares color
		glUseProgram(shaderProgram);
		//My small circles coordinats
		drawCircle(SCREEN_WIDTH / 4, SCREEN_WIDTH / 4, 0, SCREEN_WIDTH / 16, 360);
		drawCircle(SCREEN_WIDTH / 4, SCREEN_WIDTH *3/ 4, 0, SCREEN_WIDTH / 16, 360);
		drawCircle(SCREEN_WIDTH *3/ 4, SCREEN_WIDTH / 4, 0, SCREEN_WIDTH / 16, 360);
		drawCircle(SCREEN_WIDTH*3/ 4, SCREEN_WIDTH * 3 / 4, 0, SCREEN_WIDTH / 16, 360);
		drawCircle(SCREEN_WIDTH / 2, SCREEN_WIDTH * 3 / 16, 0, SCREEN_WIDTH / 16, 360);
		drawCircle(SCREEN_WIDTH / 2, SCREEN_WIDTH *13 / 16, 0, SCREEN_WIDTH / 16, 360);
		drawCircle(SCREEN_WIDTH *3/16, SCREEN_WIDTH /2, 0, SCREEN_WIDTH / 16, 360);
		drawCircle(SCREEN_WIDTH *13/ 16, SCREEN_WIDTH /2, 0, SCREEN_WIDTH / 16, 360);
	
		glfwSwapBuffers(window);

		// Poll for and process events
		glfwPollEvents();



	}

	glfwTerminate();

	return 0;
}


////Drawing circle function
void drawCircle(GLfloat x, GLfloat y, GLfloat z, GLfloat radius, GLint sides)
{
	int numberOfVertices = 362;

	GLfloat mypi = 2.0f * 3.14159265358979323846;

	GLfloat circleVerticesX[362];
	GLfloat circleVerticesY[362];
	GLfloat circleVerticesZ[362];

	circleVerticesX[0] = x;
	circleVerticesY[0] = y;
	circleVerticesZ[0] = z;

	for (int i = 1; i < numberOfVertices; i++)
	{
		circleVerticesX[i] = x + (radius * cos(i *  mypi / sides));
		circleVerticesY[i] = y + (radius * sin(i * mypi / sides));
		circleVerticesZ[i] = z;
	}

	GLfloat allCircleVertices[(362) * 3];

	for (int i = 0; i < 362; i++)
	{
		allCircleVertices[i * 3] = circleVerticesX[i];
		allCircleVertices[(i * 3) + 1] = circleVerticesY[i];
		allCircleVertices[(i * 3) + 2] = circleVerticesZ[i];
	}

	glEnableClientState(GL_VERTEX_ARRAY);
	glVertexPointer(3, GL_FLOAT, 0, allCircleVertices);
	glDrawArrays(GL_TRIANGLE_FAN, 0, numberOfVertices);
	glDisableClientState(GL_VERTEX_ARRAY);
}

//when resized window
void framebuffer_size_callback(GLFWwindow* window, int width, int height)
{
	// make sure the viewport matches the new window dimensions; note that width and 
	// height will be significantly larger than specified on retina displays.
	glViewport(0, 0, width, height);
}