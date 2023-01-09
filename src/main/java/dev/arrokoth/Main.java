package dev.arrokoth;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {
    public static void main(String[] args) {
        /*
         * TODO: A simple lwjgl demo made by Arrokoth233
         */

        // Init
        if (!glfwInit()) {
            throw new IllegalStateException("Could not initialize GLFW!");
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        long window = glfwCreateWindow(800, 480, "ko~ko~da~yo~", NULL, NULL);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        glfwShowWindow(window);

        long startTime = System.currentTimeMillis();

        // Loop
        while (!glfwWindowShouldClose(window)) {
            glViewport(0, 0, 800, 450);
            glRotatef(1.0f, 0.0f, 0.0f, (System.currentTimeMillis() - startTime) / 1000f);
            glClearColor(0.6f, 0.8f, 1.0f, 0.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glBegin(GL_TRIANGLES);

            glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
            glVertex2d(0.0f, 0.5f);

            glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
            glVertex2d(-0.5f, -0.5f);

            glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
            glVertex2d(0.5f, -0.5f);

            glEnd();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        // Destroy
        glfwDestroyWindow(window);
    }
}