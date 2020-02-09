#version 330 core

uniform mat4 rotate;
uniform mat4 view;
uniform mat4 projection;
layout (location = 0) in vec3 aPos;
void main()
{
   gl_Position = projection*view*vec4(aPos,1.00f);
}