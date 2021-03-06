#ifndef CAMERA_H
#define CAMERA_H

#include <Angel_commons/Angel.h>

#include <vector>

// Defines several possible options for camera movement. Used as abstraction to stay away from window-system specific input methods
enum Camera_Movement {
	FORWARD,
	BACKWARD,
	LEFT,
	RIGHT,
	UP,
	DOWN
};

// Default camera values
const float YAW = -90.0f;
const float PITCH = 0.1f;
const float SPEED = 1.0f;
const float SENSITIVITY = 0.001f;
const float ZOOM = 30.0f;


// An abstract camera class that processes input and calculates the corresponding Euler Angles, Vectors and Matrices for use in OpenGL
class Camera
{
public:
	// Camera Attributes
	vec3 Position;
	vec3 Front;
	vec3 Up;
	vec3 horizantal;
	
	vec3 WorldUp;
	
	// Euler Angles
	float Yaw;
	float Pitch;
	// Camera options
	float MovementSpeed;
	float MouseSensitivity;
	float Zoom;

	// Constructor with vectors
	Camera(vec3 position = vec3(0.0f, 0.0f, 0.0f), vec3 up = vec3(0.0f, 1.0f, 0.0f), float yaw = YAW, float pitch = PITCH) : Front(vec3(0.0f, 0.0f, -1.0f)), MovementSpeed(SPEED), MouseSensitivity(SENSITIVITY), Zoom(ZOOM)
	{
		Position = position;
		WorldUp = up;
		Yaw = yaw;
		Pitch = pitch;
		updateCameraVectors();
	}
	// Constructor with scalar values
	Camera(float posX, float posY, float posZ, float upX, float upY, float upZ, float yaw, float pitch) : Front(vec3(0.0f, 0.0f, -1.0f)), MovementSpeed(SPEED), MouseSensitivity(SENSITIVITY), Zoom(ZOOM)
	{
		Position = vec3(posX, posY, posZ);
		WorldUp = vec3(upX, upY, upZ);
		Yaw = yaw;
		Pitch = pitch;
		updateCameraVectors();
	}

	// Returns the view matrix calculated using Euler Angles and the LookAt Matrix
	mat4 GetViewMatrix()
	{

		return LookAt(Position, Position + Front, Up);
	}

	// Processes input received from any keyboard-like input system. Accepts input parameter in the form of camera defined ENUM (to abstract it from windowing systems)
	void ProcessKeyboard(Camera_Movement direction, float deltaTime)
	{
		float velocity = MovementSpeed * deltaTime;
		if (direction == BACKWARD)
			Position += Front * velocity;
		if (direction == FORWARD)
			Position -= Front * velocity;
		if (direction == LEFT)
			Position -= horizantal * velocity;
		if (direction == RIGHT)
			Position += horizantal * velocity;
		if (direction == UP)
			Position += 0.01 * velocity*Up;
			
			
		if (direction == DOWN)
			Position -= 0.01 * velocity*Up;

	}

	// Processes input received from a mouse input system. Expects the offset value in both the x and y direction.
	void ProcessMouseMovement(float xoffset, float yoffset, GLboolean constrainPitch = true)
	{
		xoffset *= MouseSensitivity;
		yoffset *= MouseSensitivity;

		Yaw += xoffset;
		Pitch += yoffset;

		// Make sure that when pitch is out of bounds, screen doesn't get flipped
		if (constrainPitch)
		{
			if (Pitch > 89.0f)
				Pitch = 89.0f;
			if (Pitch < -89.0f)
				Pitch = -89.0f;
		}

		// Update Front, Right and Up Vectors using the updated Euler angles
		updateCameraVectors();
	}

	// Processes input received from a mouse scroll-wheel event. Only requires input on the vertical wheel-axis


private:
	// Calculates the front vector from the Camera's (updated) Euler Angles
	void updateCameraVectors()
	{
		// Calculate the new Front vector
		vec3 front;
		front.x = cos(DegreesToRadians*(Yaw)) * cos(DegreesToRadians*(Pitch));
		front.y = sin(DegreesToRadians*(Pitch));
		front.z = sin(DegreesToRadians*(Yaw)) * cos(DegreesToRadians*(Pitch));
		Front = normalize(front);
		// Also re-calculate the Right and Up vector
		horizantal = 0.01f * normalize(cross(Front, WorldUp));  // Normalize the vectors, because their length gets closer to 0 the more you look up or down which results in slower movement.
		Up = normalize(cross(horizantal, Front));

		
		
	}
};
#endif