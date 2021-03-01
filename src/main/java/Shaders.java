import org.lwjgl.opengl.GL33;

public class Shaders {
    private static final String fragmentShader = "#version 330 core\n" +
            "\n" +
            "out vec4 FragColor;\n" +
            "\n" +
            "void main() {\n" +
            "   FragColor = vec4(1.0, 1.0, 0.0, 1.0);\n" +
            "}\n";

    private static final String vertexShader = "#version 330 core\n" +
            "\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "\n" +
            "// Transformations\n" +
            "void main() {\n" +
            "   gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n" +
            "}\n";

    private static int vertexId;
    private static int fragmentId;
    public static int shaderId;


    public static void initShaders() {
        vertexId = GL33.glCreateShader(GL33.GL_VERTEX_SHADER);
        fragmentId = GL33.glCreateShader(GL33.GL_FRAGMENT_SHADER);

        GL33.glShaderSource(vertexId, vertexShader);
        GL33.glCompileShader(vertexId);

        System.out.println(GL33.glGetShaderInfoLog(vertexId));

        GL33.glShaderSource(fragmentId, fragmentShader);
        GL33.glCompileShader(fragmentId);

        System.out.println(GL33.glGetShaderInfoLog(fragmentId));

        shaderId = GL33.glCreateProgram();
        GL33.glAttachShader(shaderId, vertexId);
        GL33.glAttachShader(shaderId, fragmentId);
        GL33.glLinkProgram(shaderId);

        System.out.println(GL33.glGetShaderInfoLog(shaderId));

        GL33.glDeleteShader(vertexId);
        GL33.glDeleteShader(fragmentId);
    }
}
