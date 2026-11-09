package com.jira_clone.task_management_system.Constant;

public final class ApiUrlConstant {

    private ApiUrlConstant() {
        // Prevent object creation
    }

    // API Version
    public static final String API_V1 = "/api/v1";

    //=================COMMON API===================

    public static final String CREATE = "/create";
    public static final String GET_ALL = "/list";
    public static final String GET_BY_ID = "/get";
    public static final String GET_BY_EMAIL = "/get-email";
    public static final String GET_BY_NAME = "/get-name";
    public static final String UPDATE = "/update";
    public static final String DELETE ="/delete";
    public static final String TASK = "/task";
    public static final String USER ="/user";

    public static final String STATUS = "/{id}/status";
    public static final String PROJECT = "/project";
    public static final String SPRINT ="/sprint";
    public static final String ASSIGNEE = "/assignee";

    // ================= AUTH =================
    public static final String AUTH = API_V1 + "/auth";
    public static final String LOGIN =  "/login";
    public static final String REGISTER =  "/register";
    public static final String REFRESH_TOKEN =  "/refresh-token";
    public static final String LOGOUT =  "/logout";

    // ================= USER =================
    public static final String USERS = API_V1 + "/users";

    // ================= ROLE =================
    public static final String ROLES = API_V1 + "/roles";

    // ================= PROJECT =================
    public static final String PROJECTS = API_V1 + "/projects";

    // ================= SPRINT =================
    public static final String SPRINTS = API_V1 + "/sprints";

    // ================= TASK =================
    public static final String TASKS = API_V1 + "/tasks";

    // ================= COMMENT =================
    public static final String COMMENTS = API_V1 + "/comments";

    // ================= ATTACHMENT =================
    public static final String ATTACHMENTS = API_V1 + "/attachments";

}
