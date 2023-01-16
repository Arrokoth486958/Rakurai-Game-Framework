package dev.arrokoth.rakurai;

import dev.arrokoth.rakurai.render.Mesh;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
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

//            glVertex2d(0.0f, 0.5f);
//
//            glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
//            glVertex2d(-0.5f, -0.5f);
//
//            glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
//            glVertex2d(0.5f, -0.5f);
            try {
                glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
                Mesh mesh = new Mesh(new float[] {0f, 0f, 0f, 1f, 0f, 0f, 1f, 1f, 1f});
                glBindVertexArray(mesh.getVaoId());
                glDrawArrays(GL_TRIANGLES, 0, mesh.getVertexCount());
                glBindVertexArray(0);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            glEnd();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        // Destroy
        glfwDestroyWindow(window);
    }
}