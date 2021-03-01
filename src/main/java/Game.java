import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class Game {

    private static final float[] vertices = {
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            -0.5f, 0.5f, 0.0f,
            0.5f, 0.5f, 0.0f
    };

    private static int vboId;
    private static int vaoId;

    static void init(long window) {
        Shaders.initShaders();

        vaoId = GL33.glGenVertexArrays();
        vboId = GL33.glGenBuffers();

        GL33.glBindVertexArray(vaoId);
            GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vboId);
            FloatBuffer fb = BufferUtils.createFloatBuffer(vertices.length)
                .put(vertices)
                .flip();

            GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);
            GL33.glVertexAttribPointer(0,3, GL33.GL_FLOAT, false, 0, 0);
            GL33.glEnableVertexAttribArray(0);

            MemoryUtil.memFree(fb);
    }

    static void render(long window) {
        GL33.glUseProgram(Shaders.shaderId);
        GL33.glBindVertexArray(vaoId);
        GL33.glDrawArrays(GL33.GL_TRIANGLES, 0, vertices.length / 3);
    }

    static void update(long window) {
        
    }
}
