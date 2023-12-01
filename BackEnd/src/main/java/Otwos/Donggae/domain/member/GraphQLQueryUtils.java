package Otwos.Donggae.domain.member;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GraphQLQueryUtils {
    public static String readGraphQLQuery(String filename) {
        try {
            // 클래스패스에서 파일을 읽어온다
            InputStream inputStream = GraphQLQueryUtils.class.getClassLoader().getResourceAsStream(filename);
            if (inputStream == null) {
                throw new IOException("Resource not found: " + filename);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the GraphQL query file", e);
        }
    }
}

