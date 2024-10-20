package com.demo.rest.controller.servlet;

import com.demo.rest.models.image.controller.api.ImageController;
import com.demo.rest.models.owner.controller.api.OwnerController;
import com.demo.rest.models.owner.dto.PutOwnerRequest;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {
        ApiServlet.Paths.API + "/*"
})
@MultipartConfig(maxFileSize = 200 * 1024)
public class ApiServlet extends HttpServlet {

    private final OwnerController ownerController;
    private final ImageController imageController;


    public class Paths {
        public static final String API = "/api";
    }

    public static final class Patterns {

        private static final Pattern UUID = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

        public static final Pattern OWNERS = Pattern.compile("/owners/?");
        public static final Pattern OWNER = Pattern.compile("/owners/(%s)".formatted(UUID.pattern()));

        public static final Pattern IMAGE = Pattern.compile("/images/(%s)".formatted(UUID.pattern()));

    }

    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    public ApiServlet(OwnerController ownerController, ImageController imageController) {
        this.ownerController = ownerController;
        this.imageController = imageController;
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.OWNERS.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(ownerController.getOwners()));
                return;
            } else if (path.matches(Patterns.OWNER.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUuid(Patterns.OWNER, path);
                response.getWriter().write(jsonb.toJson(ownerController.getOwner(uuid)));
                return;
            } else if (path.matches(Patterns.IMAGE.pattern())) {
                response.setContentType("image/png");//could be dynamic but atm we support only one format
                UUID uuid = extractUuid(Patterns.IMAGE, path);
                byte[] image = imageController.getImage(uuid);
                response.setContentLength(image.length);
                response.getOutputStream().write(image);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.OWNER.pattern())) {
                UUID uuid = extractUuid(Patterns.OWNER, path);
                PutOwnerRequest putOwnerRequest = jsonb.fromJson(request.getReader(), PutOwnerRequest.class);
                putOwnerRequest.setId(uuid);
                ownerController.putOwner(putOwnerRequest);
                response.addHeader("Location", createUrl(request, Paths.API, "owners", uuid.toString()));
                return;
            } else if (path.matches(Patterns.IMAGE.pattern())) {
                UUID uuid = extractUuid(Patterns.IMAGE, path);
                try (InputStream is = request.getPart("image").getInputStream()) {
                    byte[] imageBytes = is.readAllBytes();
                    imageController.putImage(uuid, imageBytes);
                }
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.OWNER.pattern())) {
                UUID uuid = extractUuid(Patterns.OWNER, path);
                ownerController.deleteOwner(uuid);
                return;
            } else if (path.matches(Patterns.IMAGE.pattern())) {
                UUID uuid = extractUuid(Patterns.IMAGE, path);
                System.out.println("in delete");
                imageController.deleteImage(uuid);
                return;
            }
        }

        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private static UUID extractUuid(Pattern pattern, String path) {
        Matcher matcher = pattern.matcher(path);
        if (matcher.matches()) {
            return UUID.fromString(matcher.group(1));
        }
        throw new IllegalArgumentException("No UUID in path.");
    }

    private String parseRequestPath(HttpServletRequest request) {
        String path = request.getPathInfo();
        path = path != null ? path : "";
        return path;
    }

    public static String createUrl(HttpServletRequest request, String... paths) {
        StringBuilder builder = new StringBuilder();
        builder.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath());
        for (String path : paths) {
            builder.append("/")
                    .append(path, path.startsWith("/") ? 1 : 0, path.endsWith("/") ? path.length() - 1 : path.length());
        }
        return builder.toString();
    }


}
