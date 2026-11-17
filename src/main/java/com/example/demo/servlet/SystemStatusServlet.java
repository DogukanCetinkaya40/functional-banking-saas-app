package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SystemStatusServlet", urlPatterns = "api/system/status")
public class SystemStatusServlet extends HttpServlet {

    @Override
    protected void doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("application/json");
            response.setStatus(200);
            PrintWriter writer = response.getWriter();
        }
    }
